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

import org.eclipse.ocl.examples.emf.validation.debug.ValidityManager;
import org.eclipse.ocl.examples.emf.validation.debug.ui.ValidityUIPlugin;
import org.eclipse.ocl.examples.emf.validation.debug.ui.filters.HideUnusedNodesViewerFilter;
import org.eclipse.ocl.examples.emf.validation.debug.ui.messages.ValidationDebugMessages;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;

public final class DisableAllUnusedNodesAction extends AbstractFilterAction
{
	private HideUnusedNodesViewerFilter filter = new HideUnusedNodesViewerFilter();
	
	public DisableAllUnusedNodesAction(@NonNull ValidityManager validityManager, @NonNull StructuredViewer filteredViewer, boolean isValidatableFilterAction) {
		super(ValidationDebugMessages.ValidityView_Action_ShowHideUnusedNodes_Title,
				IAction.AS_CHECK_BOX,
				ValidationDebugMessages.ValidityView_Action_HideUnusedNodes_ToolTipText,
				ValidationDebugMessages.ValidityView_Action_ShowUnusedNodes_ImageLocation,
				validityManager, filteredViewer, isValidatableFilterAction);
	}

	@Override
	public void run() {
		if (this.isChecked()){
			setToolTipText(ValidationDebugMessages.ValidityView_Action_ShowUnusedNodes_ToolTipText);
			URL image = (URL) ValidityUIPlugin.INSTANCE.getImage(ValidationDebugMessages.ValidityView_Action_HideUnusedNodes_ImageLocation);
			setImageDescriptor(ImageDescriptor.createFromURL(image));
			filteredViewer.addFilter(filter);
		} else {
			setToolTipText(ValidationDebugMessages.ValidityView_Action_HideUnusedNodes_ToolTipText);
			URL image = (URL) ValidityUIPlugin.INSTANCE.getImage(ValidationDebugMessages.ValidityView_Action_ShowUnusedNodes_ImageLocation);
			setImageDescriptor(ImageDescriptor.createFromURL(image));
			filteredViewer.removeFilter(filter);
		}
	}
}