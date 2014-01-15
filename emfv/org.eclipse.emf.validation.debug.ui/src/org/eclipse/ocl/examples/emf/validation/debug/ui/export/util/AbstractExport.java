/**
 * <copyright>
 *
 * Copyright (c) 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Obeo - initial API and implementation 
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.emf.validation.debug.ui.export.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.ocl.examples.emf.validation.debug.ui.ValidityUIPlugin;
import org.eclipse.ocl.examples.emf.validation.debug.ui.messages.ValidationDebugMessages;
import org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Result;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ResultValidatableNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ValidatableNode;
import org.eclipse.ocl.examples.emf.validation.export.IValidatorExport;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PlatformUI;

/**
 * Exports ocl validation results.
 */
public abstract class AbstractExport implements IValidatorExport {
	protected List<LeafConstrainingNode> validationFailures = new ArrayList<LeafConstrainingNode>();

	protected List<LeafConstrainingNode> validationErrors = new ArrayList<LeafConstrainingNode>();
	
	protected List<LeafConstrainingNode> validationWarnings = new ArrayList<LeafConstrainingNode>();

	protected List<LeafConstrainingNode> validationInfos = new ArrayList<LeafConstrainingNode>();

	protected List<LeafConstrainingNode> validationSuccess = new ArrayList<LeafConstrainingNode>();

	public void export(@NonNull IResource validatedResource, @NonNull RootNode rootNode, @NonNull IPath savePath) {
		final IFile exportedFile = ResourcesPlugin.getWorkspace().getRoot().getFile(savePath);
		
		populateMaps(rootNode);
		
		final String initialContents = createContents(validatedResource, rootNode, exportedFile);
		final InputStream contentStream = new ByteArrayInputStream(initialContents.getBytes(Charset.forName("UTF-8")));
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) {
				try {
					exportedFile.create(contentStream, true, monitor);
				} catch (final CoreException e) {
					handleError(e.getCause(), true);
				}
			}
		};

		try {
			op.run(new NullProgressMonitor());
		} catch (InvocationTargetException e) {
			handleError(e, false);
		} catch (InterruptedException e) {
			handleError(e, false);
		}
	}
	
	private static void handleError(Throwable t, boolean popup) {
		final String message = NLS.bind(ValidationDebugMessages.NewWizardPage_internalErrorMessage, t.getMessage());
		final IStatus status;
		if (t instanceof CoreException) {
			status = new Status(((CoreException) t).getStatus().getSeverity(), ValidityUIPlugin.PLUGIN_ID, message, t);
		} else {
			status = new Status(IStatus.ERROR, ValidityUIPlugin.PLUGIN_ID, message, t);
		}
		ValidityUIPlugin.getPlugin().getLog().log(status);
		
		if (popup) {
			PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
				public void run() {
					ErrorDialog.openError(PlatformUI.getWorkbench().getDisplay().getActiveShell(),
							ValidationDebugMessages.NewWizardPage_errorTitle, message, status);
					}
				});
		}
	}

	/**
	 * Returns a stream containing the initial contents to be given to new
	 * exported validation results file resource instances.
	 * 
	 * 
	 * @param validatedResource
	 * 							The validated model
	 * @param rootNode
	 * 							The validation result model
	 * @param exportedFile
	 * 							The target file
	 * 
	 * @return exported File contents to be given to new exported file resource
	 *         instances
	 */
	protected abstract String createContents(@NonNull IResource validatedResource, RootNode rootNode, IFile exportedFile);

	private void populateMaps(RootNode rootNode) {
		for (ValidatableNode validatableNode : rootNode.getValidatableNodes()) {
			populateMaps(validatableNode);
		}
	}

	private void populateMaps(ValidatableNode validatableNode) {
		if (validatableNode instanceof ResultValidatableNode){
			ResultValidatableNode resultValidatableNode = (ResultValidatableNode) validatableNode;
			Result result = resultValidatableNode.getWorstResult();
			if (result != null){
				LeafConstrainingNode leafConstrainingNode = result.getLeafConstrainingNode();
				Severity severity = result.getSeverity();
				switch (severity.getValue()) {
					case Severity.OK_VALUE :
						validationSuccess.add(leafConstrainingNode);
						break;
					case Severity.ERROR_VALUE :
						validationErrors.add(leafConstrainingNode);
						break;
					case Severity.FATAL_VALUE :
						validationFailures.add(leafConstrainingNode);
						break;
					case Severity.WARNING_VALUE :
						validationWarnings.add(leafConstrainingNode);
						break;
					case Severity.INFO_VALUE :
						validationInfos.add(leafConstrainingNode);
						break;
					default :
						break;
				}
			}
		} else {
			for(ValidatableNode node : validatableNode.getChildren()){
				populateMaps(node);
			}
		}
	}
	
	protected int getConstraintCount() {
		return validationErrors.size() + validationFailures.size() + validationInfos.size() + validationSuccess.size() + validationWarnings.size();
	}
	
	protected String getSeverity(Result result) {
		if (result != null) {
			return result.getSeverity().getName();
		}
		return null;
	}
	
	protected String getMessage(Result result) {
		if (result == null) {
			return null;
		}
		Object diagnostic = result.getDiagnostic();
		StringWriter message = new StringWriter();
		if (diagnostic == null) {
			message.append("<<null diagnostic message>>");
		} else if (diagnostic instanceof Diagnostic) {
			boolean isFirst = true;
			for (Diagnostic child : ((Diagnostic) diagnostic).getChildren()) {
				if (isFirst) {
					message.append(child.getMessage());
					isFirst = false;
				} else {
					message.append("\n" + child.getMessage());
				}
			}
			return message.toString();
		} else {
			return diagnostic.toString();
		}
		Exception exception = result.getException();
		if (exception != null) {
			message.append('\n');
			exception.printStackTrace(new PrintWriter(message));
		}
		return result.toString();
	}
}
