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
package org.eclipse.ocl.examples.emf.validation.debug.ui.filters;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Result;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Severity;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class NodesViewerFilter extends ViewerFilter {
	private Set<Severity> acceptedSeverities = new LinkedHashSet<Severity>();
	
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		// if we haven't activated any filtering, then all should be displayed
		if (acceptedSeverities.isEmpty()) {
			return true;
		}
		
		if (element instanceof AbstractNode) {
			return isAcceptedNode((AbstractNode) element);
		}
		return false;
	}
	
	public void addFilteredSeverity(Severity severity) {
		acceptedSeverities.add(severity);
	}
	
	public void removeFilteredSeverity(Severity severity) {
		acceptedSeverities.remove(severity);
	}
	
	private boolean isAcceptedNode(AbstractNode node) {
		Result worstResultForNode = node.getWorstResult();
		if (worstResultForNode != null && acceptedSeverities.contains(worstResultForNode.getSeverity())) {
			return true;
		} else {
			for (AbstractNode child : node.getChildren()) {
				if (isAcceptedNode(child)) {
					return true;
				}
			}
		}
		return false;
	}
}
