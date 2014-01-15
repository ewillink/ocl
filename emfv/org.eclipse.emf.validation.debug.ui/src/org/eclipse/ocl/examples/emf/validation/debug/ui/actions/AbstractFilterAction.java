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
import org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredViewer;

public abstract class AbstractFilterAction extends Action {
	protected final @NonNull ValidityManager validityManager;
	protected final @NonNull StructuredViewer filteredViewer;
	protected final boolean isValidatableFilterAction;
	
	public AbstractFilterAction(String text, String toolTip, String imageLocation, @NonNull ValidityManager validityManager, @NonNull StructuredViewer filteredViewer, boolean isValidatableFilterAction) {
		super(text);
		this.validityManager = validityManager;
		this.filteredViewer = filteredViewer;
		this.isValidatableFilterAction = isValidatableFilterAction;
		setToolTipText(toolTip);
		URL imageURL = (URL) ValidityUIPlugin.INSTANCE.getImage(imageLocation);
		setImageDescriptor(ImageDescriptor.createFromURL(imageURL));
	}
	
	public AbstractFilterAction(String text, int style, String toolTip, String imageLocation, @NonNull ValidityManager validityManager, @NonNull StructuredViewer filteredViewer, boolean isValidatableFilterAction) {
		super(text, style);
		this.validityManager = validityManager;
		this.filteredViewer = filteredViewer;
		this.isValidatableFilterAction = isValidatableFilterAction;
		setToolTipText(toolTip);
		URL imageURL = (URL) ValidityUIPlugin.INSTANCE.getImage(imageLocation);
		setImageDescriptor(ImageDescriptor.createFromURL(imageURL));
	}
	
	protected StructuredViewer getFilteredViewer() {
		return filteredViewer;
	}
	
	protected void deselectAll(@NonNull Iterable<? extends AbstractNode> nodes) {
		for (AbstractNode node : nodes) {
			node.setEnabled(false);
			deselectAll(node.getChildren());
		}
	}
	
	protected void selectAll(@NonNull Iterable<? extends AbstractNode> nodes) {
		for (AbstractNode node : nodes) {
			node.setEnabled(true);
			selectAll(node.getChildren());
		}
	}
}
