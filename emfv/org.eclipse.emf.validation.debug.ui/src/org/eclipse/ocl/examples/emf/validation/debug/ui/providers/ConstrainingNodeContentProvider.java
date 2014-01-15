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
package org.eclipse.ocl.examples.emf.validation.debug.ui.providers;

import java.util.List;

import org.eclipse.ocl.examples.emf.validation.debug.ValidityManager;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootNode;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ConstrainingNodeContentProvider implements ITreeContentProvider
{
	public static final @NonNull Object[] NO_OBJECTS = new Object[0];
	
	private final @NonNull ValidityManager validityManager;

	public ConstrainingNodeContentProvider(@NonNull ValidityManager validityManager, @NonNull ILabelProvider labelProvider) {
		this.validityManager = validityManager;
	}

	public void dispose() {}

	public Object[] getElements(Object inputElement) {
		RootNode rootNode = validityManager.getRootNode();
		if (rootNode == null) {
			return NO_OBJECTS;
		}
		List<RootConstrainingNode> constrainingNodes = rootNode.getConstrainingNodes();
		return constrainingNodes.toArray(new Object[constrainingNodes.size()]);
	}

	public Object[] getChildren(Object parentElement) {
		List<ConstrainingNode> constrainingNodes = ((ConstrainingNode)parentElement).getChildren();
		return constrainingNodes.toArray(new Object[constrainingNodes.size()]);
	}

	public Object getParent(Object element) {
		return ((ConstrainingNode)element).getParent();
	}

	public boolean hasChildren(Object element) {
		List<ConstrainingNode> constrainingNodes = ((ConstrainingNode)element).getChildren();
		return constrainingNodes.size() > 0;
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
