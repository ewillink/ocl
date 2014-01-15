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
 *	 Obeo - initial API and implementation
 *  
 * </copyright>
 */
package org.eclipse.ocl.examples.emf.validation.debug.ui.actions;

import java.net.URL;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.ocl.examples.emf.validation.debug.ValidityModel;
import org.eclipse.ocl.examples.emf.validation.debug.ui.ValidityUIPlugin;
import org.eclipse.ocl.examples.emf.validation.debug.ui.messages.ValidationDebugMessages;
import org.eclipse.ocl.examples.emf.validation.debug.ui.utils.Logger;
import org.eclipse.ocl.examples.emf.validation.debug.ui.view.IDEValidityManager;
import org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ResultConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ResultValidatableNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ValidatableNode;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public final class ShowElementInEditorAction extends Action
{
	private final @NonNull IDEValidityManager validityManager;
	
	private final @NonNull ISelectionProvider selectionProvider;
	
	public ShowElementInEditorAction(@NonNull IDEValidityManager validityManager, @NonNull ISelectionProvider selectionProvider) {
		super(ValidationDebugMessages.ValidityView_Action_ShowInEditor_Title);
		this.validityManager = validityManager;
		this.selectionProvider = selectionProvider;
		setToolTipText(ValidationDebugMessages.ValidityView_Action_ShowInEditor_ToolTipText);
		URL image = (URL) ValidityUIPlugin.INSTANCE.getImage(ValidationDebugMessages.ValidityView_Action_ShowInEditor_ImageLocation);
		setImageDescriptor(ImageDescriptor.createFromURL(image));
	}

	@Override
	public void run() {
		ISelection selection = selectionProvider.getSelection();
		if (selection instanceof StructuredSelection) {
			Object selectedObject = ((StructuredSelection) selection).getFirstElement();
			ValidityModel model = validityManager.getModel();
			IWorkbenchPage activePage = getActivePage();
			if (!(selectedObject instanceof AbstractNode) || activePage == null || model == null) {
				return;
			}
			
			IMarker goToMarker = findGoToMarker(model, (AbstractNode) selectedObject);
			if (goToMarker != null) {
				try {
					IDE.openEditor(activePage, goToMarker);
				} catch (PartInitException exception) {
					Logger.getLogger().log(Logger.SEVERE, "Failed to open in the Editor ", exception); //$NON-NLS-1$
					EcorePlugin.INSTANCE.log(exception);
				}
			}
		}
	}
	
	private @Nullable IMarker findGoToMarker(ValidityModel model, AbstractNode node) {
		IMarker goToMarker = null;
		if (node instanceof ResultConstrainingNode) {
			ResultConstrainingNode resultConstrainingNode = (ResultConstrainingNode) node;
			ValidatableNode validatableNode = resultConstrainingNode.getResultValidatableNode().getParent();
			if (validatableNode != null && model.getModelElementMarker(validatableNode) != null) {
				goToMarker = model.getModelElementMarker(validatableNode).getIMarker();
			}
		} else if (node instanceof ResultValidatableNode) {
			ResultValidatableNode validatableNode = (ResultValidatableNode) node;
			ConstrainingNode constrainingNode = validatableNode.getResultConstrainingNode().getParent();
			if (constrainingNode instanceof LeafConstrainingNode && model.getLeafConstrainingNodeMarker((LeafConstrainingNode) constrainingNode) != null) {
				goToMarker = model.getLeafConstrainingNodeMarker((LeafConstrainingNode) constrainingNode).getIMarker();
			}
		} else if (node instanceof LeafConstrainingNode && model.getLeafConstrainingNodeMarker((LeafConstrainingNode) node) !=null){
			goToMarker = model.getLeafConstrainingNodeMarker((LeafConstrainingNode) node).getIMarker();
		} else if (node instanceof ValidatableNode && model.getModelElementMarker((ValidatableNode) node) !=null){
			goToMarker = model.getModelElementMarker((ValidatableNode) node).getIMarker();
		}
		return goToMarker;
	}
	
	private @Nullable IWorkbenchPage getActivePage() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench != null) {
			IWorkbenchWindow activeWindow = workbench.getActiveWorkbenchWindow();
			if (activeWindow != null) {
				return activeWindow.getActivePage();
			}
		}
		return null;
	}
}