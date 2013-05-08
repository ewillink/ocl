/**
 * <copyright>
 *
 * Copyright (c) 2013 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *	E.D.Willink (CEA LIST) - initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.emf.validation.debug.ui.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.debug.ValidityManager;
import org.eclipse.emf.validation.debug.ValidityModel;
import org.eclipse.emf.validation.debug.locator.ConstraintLocator;
import org.eclipse.emf.validation.debug.validity.AbstractNode;
import org.eclipse.emf.validation.debug.validity.LeafConstrainingNode;
import org.eclipse.emf.validation.debug.validity.Result;
import org.eclipse.emf.validation.debug.validity.ResultConstrainingNode;
import org.eclipse.emf.validation.debug.validity.ResultSet;
import org.eclipse.emf.validation.debug.validity.ResultValidatableNode;
import org.eclipse.emf.validation.debug.validity.RootNode;
import org.eclipse.emf.validation.debug.validity.Severity;
import org.eclipse.emf.validation.debug.validity.ValidatableNode;
import org.eclipse.emf.validation.debug.validity.ValidityPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class IDEValidityManager extends ValidityManager
{
	public static final int FAST_REFRESH_DELAY = 250;		// 250 ms delay to aggregate refresh new changes
	public static final int SLOW_REFRESH_DELAY = 2500;		// 2500 ms delay to aggregate refresh changes when busy
	private static final @NonNull List<Job> validityJobs = new ArrayList<Job>();

	public static void stopValidation() {
		while (!validityJobs.isEmpty()) {
			ArrayList<Job> jobs;
			synchronized (validityJobs) {
				jobs = new ArrayList<Job>(validityJobs);
			}
			for (Job job : jobs) {
				job.cancel();
			}
		}
	}
	
	protected class IDEValidityModel extends ValidityModel
	{
		public IDEValidityModel(@NonNull IDEValidityManager validityManager, @NonNull Set<Resource> newResources) {
			super(validityManager, newResources);
 		}

		@Override
		protected @Nullable Result createResult(@Nullable IProgressMonitor monitor) {
			if ((monitor != null) && monitor.isCanceled()) {
				return null;
			}
			Result result = super.createResult(monitor);
			if (result == null) {
				return null;
			}
			result.eAdapters().add(resultAdapter);
			return result;
		}
	}

	private class ValidityViewJob extends Job
	{
		private ValidityViewJob() {
			super("Validity View Validation");
		}

		@Override
		protected IStatus run(/*@NonNull*/ IProgressMonitor monitor) {
			assert monitor != null;
//			System.out.println(Thread.currentThread().getName() + " - ValidationJob.run");
			try {
				final ResultSet resultSet = createResultSet(monitor);
				if (resultSet == null) {
					return Status.CANCEL_STATUS;
				}
				List<Result> results = installResultSet(resultSet, monitor);
				if (results == null) {
					return Status.CANCEL_STATUS;
				}
				try {
					monitor.beginTask("Constraint Validation", results.size());
					int i = 0;
					for (Result result : results) {
						if (monitor.isCanceled()) {
							return Status.CANCEL_STATUS;
						}
						boolean refreshLabels = (i % 100) == 0;
						try {
							ValidatableNode validatable = result.getValidatableNode();
							if (refreshLabels) {
								monitor.setTaskName(i + "/" + results.size() + ": " + validatable.toString());
							}
							LeafConstrainingNode constraint = result.getLeafConstrainingNode();
							ConstraintLocator constraintLocator = constraint.getConstraintLocator();
							constraintLocator.validate(result, IDEValidityManager.this);
						}
						catch (Exception e) {
							result.setException(e);
							result.setSeverity(Severity.FATAL);
						}
						finally {
							if (refreshLabels) {
								monitor.worked(100);
							}
							i++;
						}
					}
					return Status.OK_STATUS;
				}
				finally {
					monitor.done();
//					System.out.println(Thread.currentThread().getName() + " - ValidationJob.end");
//						resultSet.setStable();
				}
			}
			finally {
				synchronized (validityJobs) {
					validityJobs.remove(this);
				}
			}
		}
	}

	public class AbstractNodeAdapter extends AdapterImpl
	{
		@Override
		public void notifyChanged(Notification notification) {
			Object target = notification.getNotifier();
			if (target instanceof AbstractNode) {
				int event = notification.getEventType();
				Object feature = notification.getFeature();
				if (event == Notification.SET) {
					if (feature == ValidityPackage.Literals.ABSTRACT_NODE__ENABLED) {
						refreshJob.add((AbstractNode) target);
					}
					else if (feature == ValidityPackage.Literals.ABSTRACT_NODE__ALL_CHILDREN_DISABLED) {
						refreshJob.add((AbstractNode) target);
					}
					else if (feature == ValidityPackage.Literals.ABSTRACT_NODE__ALL_CHILDREN_ENABLED) {
						refreshJob.add((AbstractNode) target);
					}
					else if (feature == ValidityPackage.Literals.ABSTRACT_NODE__WORST_RESULT) {
						refreshJob.add((AbstractNode) target);
					}
					else if (feature == ValidityPackage.Literals.ABSTRACT_NODE__LABEL) {
						refreshJob.add((AbstractNode) target);
					}
				}
			}
		}	
	}
	
	public class ResultAdapter extends AdapterImpl
	{
		@Override
		public void notifyChanged(Notification notification) {
			Object target = notification.getNotifier();
			if (target instanceof Result) {
				Result result = (Result)target;
				int event = notification.getEventType();
				Object feature = notification.getFeature();
				if (event == Notification.SET) {
					if (feature == ValidityPackage.Literals.RESULT__SEVERITY) {
						ResultConstrainingNode resultConstrainingNode = result.getResultConstrainingNode();
						if (resultConstrainingNode != null) {
							refreshJob.add(resultConstrainingNode);
						}
						ResultValidatableNode resultValidatableNode = result.getResultValidatableNode();
						if (resultValidatableNode != null) {
							refreshJob.add(resultValidatableNode);
						}
					}
				}
			}
		}
	}
	
	private final @NonNull AbstractNodeAdapter nodeAdapter = new AbstractNodeAdapter();
	private final @NonNull ResultAdapter resultAdapter = new ResultAdapter();
	private final @NonNull ValidityViewRefreshJob refreshJob;

	public IDEValidityManager(@NonNull ValidityViewRefreshJob refreshJob) {
		this.refreshJob = refreshJob;
	}

	@Override
	protected @NonNull ValidityModel createModel(@NonNull Set<Resource> newResources) {
		ValidityModel contents = new IDEValidityModel(this, newResources);
		RootNode rootNode = contents.getRootNode();
		installAdapters(rootNode.getConstrainingNodes());
		installAdapters(rootNode.getValidatableNodes());
		return contents;
	}

	private void installAdapters(@NonNull List<? extends AbstractNode> nodes) {
		for (AbstractNode node : nodes) {
			node.eAdapters().add(nodeAdapter);
			installAdapters(node.getChildren());
		}
	}

	private @Nullable List<Result> installResultSet(@NonNull ResultSet resultSet, @NonNull IProgressMonitor monitor) {
		lastResultSet = resultSet;
		resultsMap.clear();
		RootNode rootNode = getRootNode();
		if (rootNode == null) {
			return null;
		}
		resetResults(rootNode.getValidatableNodes());
		resetResults(rootNode.getConstrainingNodes());
		List<Result> results = resultSet.getResults();
		for (Result result : results) {
			ResultValidatableNode resultValidatableNode = result.getResultValidatableNode();
			resultsMap.put(resultValidatableNode, result);
			if (monitor.isCanceled()) {
				return null;
			}
		}
		return results;
	}

	private void resetResults(@NonNull List<? extends AbstractNode> nodes) {
		for (AbstractNode node : nodes) {
			resetResults(node.getChildren());
			node.setWorstResult(null);
		}	
	}

	public void runValidation() {
		Job validationJob = new ValidityViewJob();
		synchronized (validityJobs) {
			validityJobs.add(validationJob);
		}
		validationJob.schedule();
	}
}
