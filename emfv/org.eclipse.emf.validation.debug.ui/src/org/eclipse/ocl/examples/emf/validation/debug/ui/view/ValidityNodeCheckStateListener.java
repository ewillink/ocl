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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootValidatableNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ValidatableNode;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;

public class ValidityNodeCheckStateListener implements ICheckStateListener {
	private final CheckboxTreeViewer validatableTree;
	
	private final CheckboxTreeViewer constraintsTree;
	
	public ValidityNodeCheckStateListener(CheckboxTreeViewer validatableTree, CheckboxTreeViewer constraintsTree) {
		this.validatableTree = validatableTree;
		this.constraintsTree = constraintsTree;
	}
	
	public void checkStateChanged(CheckStateChangedEvent event) {
		Object element = event.getElement();
		if (element instanceof AbstractNode) {
			AbstractNode abstractNode = (AbstractNode) element;
			boolean checked = event.getChecked();

			// update Selected Element check
			abstractNode.setEnabled(checked);

			// update Selected Element parents checks/grayed
			if (abstractNode instanceof RootValidatableNode || abstractNode instanceof RootConstrainingNode){
				updateRootNode(abstractNode);					
			} else if (abstractNode instanceof ConstrainingNode) {
				ConstrainingNode constrainingNode = (ConstrainingNode) abstractNode;
				updateConstrainingNodeAncestors(constrainingNode);
			} else if (abstractNode instanceof ValidatableNode) {
				ValidatableNode validatableNode = (ValidatableNode) abstractNode;
				updateValidatableNodeAncestors(validatableNode);
			}
		} else {
			// DO NOTHING
		}
	}

	private void updateRootNode(AbstractNode root) {
		List<AbstractNode> checkedChildren = getCheckedAbstractNodeChildren(root);
		if (checkedChildren.size() == root.getChildren().size()) {
			List<AbstractNode> grayedChildren = getGrayedAbstractNodeChildren(root);
			if (grayedChildren.size() == 0) {
				if (root instanceof RootValidatableNode) {
					validatableTree.setGrayed(root, false);
				} else if (root instanceof RootConstrainingNode) {
					constraintsTree.setGrayed(root, false);
				}
			} else {
				if (root instanceof RootValidatableNode) {
					validatableTree.setGrayed(root, true);
				} else if (root instanceof RootConstrainingNode) {
					constraintsTree.setGrayed(root, true);
				}
			}
		} else {
			if (root instanceof RootValidatableNode) {
				validatableTree.setGrayed(root, true);
			} else if (root instanceof RootConstrainingNode) {
				constraintsTree.setGrayed(root, true);
			}
		}
	}
	
	private void updateConstrainingNodeAncestors(ConstrainingNode child) {
		ConstrainingNode parent = child.getParent();
		if (parent != null) {
			List<ConstrainingNode> checkedChildren = getCheckedConstrainingNodeChildren(parent);
			if (checkedChildren.size() == parent.getChildren().size()) {
				List<ConstrainingNode> grayedChildren = getGrayedConstrainingNodeChildren(parent);
				if (grayedChildren.size() == 0) {
					constraintsTree.setGrayed(parent, false);
				} else {
					constraintsTree.setGrayed(parent, true);
				}
			} else {
				constraintsTree.setGrayed(parent, true);
			}
			updateConstrainingNodeAncestors(parent);
		}
	}

	private List<AbstractNode> getCheckedAbstractNodeChildren(
			AbstractNode root) {
		List<AbstractNode> checkedChildren = new ArrayList<AbstractNode>();
		if (root instanceof RootValidatableNode) {
			for (AbstractNode child : root.getChildren()) {
				if (validatableTree.getChecked(child)) {
					checkedChildren.add(child);
				}
			}
		} else if (root instanceof RootConstrainingNode) {
			for (AbstractNode child : root.getChildren()) {
				if (constraintsTree.getChecked(child)) {
					checkedChildren.add(child);
				}
			}
		}
		return checkedChildren;
	}

	private List<AbstractNode> getGrayedAbstractNodeChildren(
			AbstractNode root) {
		List<AbstractNode> grayedChildren = new ArrayList<AbstractNode>();

		if (root instanceof RootValidatableNode) {
			for (AbstractNode child : root.getChildren()) {
				if (validatableTree.getGrayed(child)) {
					grayedChildren.add(child);
				}
			}
		} else if (root instanceof RootConstrainingNode) {
			for (AbstractNode child : root.getChildren()) {
				if (constraintsTree.getGrayed(child)) {
					grayedChildren.add(child);
				}
			}
		}
		return grayedChildren;
	}
	
	private List<ConstrainingNode> getCheckedConstrainingNodeChildren(
			ConstrainingNode parent) {
		List<ConstrainingNode> checkedChildren = new ArrayList<ConstrainingNode>();
		for (ConstrainingNode child : parent.getChildren()) {
			if (constraintsTree.getChecked(child)) {
				checkedChildren.add(child);
			}
		}
		return checkedChildren;
	}
	
	private List<ConstrainingNode> getGrayedConstrainingNodeChildren(
			ConstrainingNode parent) {
		List<ConstrainingNode> grayedChildren = new ArrayList<ConstrainingNode>();
		for (ConstrainingNode child : parent.getChildren()) {
			if (constraintsTree.getGrayed(child)) {
				grayedChildren.add(child);
			}
		}
		return grayedChildren;
	}

	private void updateValidatableNodeAncestors(ValidatableNode child) {
		ValidatableNode parent = child.getParent();
		if (parent != null) {
			List<ValidatableNode> checkedChildren = getCheckedValidatableNodeChildren(parent);
			if (checkedChildren.size() == parent.getChildren().size()) {
				List<ValidatableNode> grayedChildren = getGrayedValidatableNodeChildren(parent);
				if (grayedChildren.size() == 0) {
					validatableTree.setGrayed(parent, false);
				} else {
					validatableTree.setGrayed(parent, true);
				}
			} else {
				validatableTree.setGrayed(parent, true);
			}
			updateValidatableNodeAncestors(parent);
		}
	}

	private List<ValidatableNode> getCheckedValidatableNodeChildren(
			ValidatableNode parent) {
		List<ValidatableNode> checkedChildren = new ArrayList<ValidatableNode>();
		for (ValidatableNode child : parent.getChildren()) {
			if (validatableTree.getChecked(child)) {
				checkedChildren.add(child);
			}
		}
		return checkedChildren;
	}
	
	private List<ValidatableNode> getGrayedValidatableNodeChildren(
			ValidatableNode parent) {
		List<ValidatableNode> grayedChildren = new ArrayList<ValidatableNode>();
		for (ValidatableNode child : parent.getChildren()) {
			if (validatableTree.getGrayed(child)) {
				grayedChildren.add(child);
			}
		}
		return grayedChildren;
	}
}
