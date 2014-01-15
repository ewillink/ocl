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
package org.eclipse.ocl.examples.emf.validation.debug.ui.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.examples.emf.validation.debug.ui.messages.ValidationDebugMessages;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.export.ExportResultsDescriptor;
import org.eclipse.ocl.examples.emf.validation.export.IValidatorExport;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

/**
 * Wizard allowing the user to export a validation results file.
 */
public class ExportValidationResultsFileWizard extends Wizard implements INewWizard
{
	/** The only page contributing to the wizard */
	private ExportValidationResultsFileWizardPage wizardPage;

	/** The selected Resource */
	private IResource initialResource;
	
	/** The only export descriptor contributing to the wizard */
	private final ExportResultsDescriptor exportDescriptor;

	/** The results root node contributing to the wizard */
	private final RootNode rootNode;

	/**
	 * Constructor
	 */
	public ExportValidationResultsFileWizard(@NonNull IWorkbench workbench, @NonNull IStructuredSelection initialSelection, 
			@NonNull RootNode rootNode, @NonNull ExportResultsDescriptor exportDescriptor) {
		super();
		setWindowTitle(ValidationDebugMessages.NewWizardPage_pageTitle);
		this.exportDescriptor = exportDescriptor;
		this.rootNode = rootNode;
		init(workbench, initialSelection);
	}
	
	/**
	 * Creates a new Exported validation results file resource in the selected container and
	 * with the selected name. Creates any missing resource containers along the
	 * path; does nothing if the container resources already exist.
	 * <p>
	 * In normal usage, this method is invoked after the user has pressed Finish
	 * on the wizard; the enablement of the Finish button implies that all
	 * controls on on this page currently contain valid values.
	 * </p>
	 * <p>
	 * This method should be called within a workspace modify operation since it
	 * creates resources.
	 * </p>
	 */
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		final IResource selectedResource2 = initialResource;
		final RootNode rootNode2 = rootNode;
		final IPath path = wizardPage.getNewExportedFilePath();
		final IValidatorExport selectedExporter = exportDescriptor.getExportExtension();
		
		if (selectedExporter != null && selectedResource2 != null && rootNode2 != null && path != null) {
			selectedExporter.export(selectedResource2, rootNode2, path);
		}
	
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench, org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		IResource initialResource2 = null;
		Object selected = selection.getFirstElement();
		if (selected instanceof Resource) {
			initialResource2 = getIResource((Resource) selected);
		}
		String expectedExtension = exportDescriptor.getExtensionAttribute();
		if (expectedExtension != null && initialResource2 != null) {
			initialResource = initialResource2;
			wizardPage = new ExportValidationResultsFileWizardPage(expectedExtension, initialResource2);
			addPage(wizardPage);
		}
	}

	/**
	 * Returns the file if exists in the workspace
	 * 
	 * @param resource
	 * @return the file if exists in the workspace, null otherwise
	 */
	private IFile getIResource(Resource resource) {
		if (resource == null)
			return null;
	
		URI resourceURI = resource.getURI();
		if (resourceURI == null)
			return null;
		if (resourceURI.isPlatform()) {
			IPath resourcePath = new Path(resourceURI.toPlatformString(true));
			return ResourcesPlugin.getWorkspace().getRoot().getFile(resourcePath);
		}
		return null;
	}
}