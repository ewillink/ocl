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
package org.eclipse.ocl.examples.emf.validation.debug.ui.view;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.viewers.CheckboxTreeViewer;

/**
 * The ValidationViewRefreshJob provides a delayed refresh of the Validation View trees
 * avoiding the heavy UI thrashing that might occur if each model change updated imeediately.
 */
public class ValidityViewRefreshJob extends Job
{
	private class DisplayRefresh implements Runnable
	{
		public void run() {
//			System.out.println(Thread.currentThread().getName() + " - DisplayRefresh.start");
			try {
				// For large models intelligent selective update is costly so just do a full refresh
				validatableNodesViewer.refresh();
				constrainingNodesViewer.refresh();
			}
			finally {
				displayRefresh = null;
//				System.out.println(Thread.currentThread().getName() + " - DisplayRefresh.end");
				synchronized (refreshQueue) {
					if (!refreshQueue.isEmpty()) {
						schedule(IDEValidityManager.SLOW_REFRESH_DELAY);
					}
				}
			}
		}
	}

	private Set<AbstractNode> refreshQueue = new HashSet<AbstractNode>();
	private CheckboxTreeViewer validatableNodesViewer = null;
	private CheckboxTreeViewer constrainingNodesViewer = null;
	private DisplayRefresh displayRefresh = null;

	public ValidityViewRefreshJob() {
		super("Validation View Refresh");
	}

	public void add(@NonNull AbstractNode node) {
		synchronized (refreshQueue) {
			if (refreshQueue.isEmpty()) {
				schedule(IDEValidityManager.FAST_REFRESH_DELAY);
			}
			refreshQueue.add(node);
		}
	}

	public void initViewers(@NonNull CheckboxTreeViewer validatableNodesViewer, @NonNull CheckboxTreeViewer constrainingNodesViewer) {
		this.validatableNodesViewer = validatableNodesViewer;
		this.constrainingNodesViewer = constrainingNodesViewer;
	}
	
	@Override
	protected IStatus run(IProgressMonitor monitor) {
//		System.out.println(Thread.currentThread().getName() + " - RefreshJob.start");
		if (displayRefresh != null) {
//			System.out.println(Thread.currentThread().getName() + " - RefreshJob.skip");
			return Status.CANCEL_STATUS;
		}
		if (monitor.isCanceled()) {
//			System.out.println(Thread.currentThread().getName() + " - RefreshJob.abort");
			return Status.CANCEL_STATUS;
		}
		if ((validatableNodesViewer == null) || (constrainingNodesViewer == null)) {
//			System.out.println(Thread.currentThread().getName() + " - RefreshJob.not-ready");
			return Status.CANCEL_STATUS;
		}
		synchronized (refreshQueue) {
			refreshQueue.clear();
		}
		displayRefresh = new DisplayRefresh();
		validatableNodesViewer.getTree().getDisplay().asyncExec(displayRefresh);
//		System.out.println(Thread.currentThread().getName() + " - RefreshJob.done");
		return Status.OK_STATUS;
	}
}