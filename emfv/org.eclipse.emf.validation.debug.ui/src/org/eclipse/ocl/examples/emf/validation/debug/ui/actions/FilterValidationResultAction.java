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
import java.text.MessageFormat;

import org.eclipse.ocl.examples.emf.validation.debug.ui.ValidityUIPlugin;
import org.eclipse.ocl.examples.emf.validation.debug.ui.messages.ValidationDebugMessages;
import org.eclipse.ocl.examples.emf.validation.debug.ui.view.ValidityView;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Severity;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;

public final class FilterValidationResultAction extends Action implements IMenuCreator
{
	protected final @NonNull ValidityView validityView;

	private Action enableErrorNodesAction;
	private Action enableWarningNodesAction;
	private Action enableSuccessNodesAction;
	private Action enableInfoNodesAction;
	private Action enableFatalNodesAction;
	
	/** Menu manager for this action. */
	private MenuManager menuManager = new MenuManager();
	
	public FilterValidationResultAction(@NonNull ValidityView validityView) {
		super(ValidationDebugMessages.ValidityView_Action_FilterResult_Title);
		this.validityView = validityView;
		setToolTipText(ValidationDebugMessages.ValidityView_Action_FilterResult_ToolTipText);
		URL image = (URL) ValidityUIPlugin.INSTANCE
			.getImage(ValidationDebugMessages.ValidityView_Action_FilterResult_ImageLocation);
		setImageDescriptor(ImageDescriptor.createFromURL(image));
		setMenuCreator(this);

		this.enableFatalNodesAction = createFatalNodesFilterAction();
		this.enableErrorNodesAction = createErrorNodesFilterAction();
		this.enableWarningNodesAction = createWarningNodesFilterAction();
		this.enableInfoNodesAction = createInfoNodesFilterAction();
		this.enableSuccessNodesAction = createSuccessNodesFilterAction();
	}
	
	private Action createFatalNodesFilterAction() {
		String text = MessageFormat.format(
				ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_Title,
				ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_Kind_Failure);
		String toolTipText = ValidationDebugMessages.ValidityView_Action_ShowFailedElementsNodes_ToolTipText;
		return new SwitchFilterAction(text, toolTipText, Severity.FATAL);
	}
	
	private Action createErrorNodesFilterAction() {
		String kind = ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_Kind_Error;
		String text = MessageFormat.format(
				ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_Title, kind);
		String toolTipText = MessageFormat.format(
				ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_ToolTipText, kind);
		return new SwitchFilterAction(text, toolTipText, Severity.ERROR);
	}
	
	private Action createWarningNodesFilterAction() {
		String kind = ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_Kind_Warning;
		String text = MessageFormat.format(
				ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_Title, kind);
		String toolTipText = MessageFormat.format(
				ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_ToolTipText, kind);
		return new SwitchFilterAction(text, toolTipText, Severity.WARNING);
	}
	
	private Action createInfoNodesFilterAction() {
		String kind = ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_Kind_Info;
		String text = MessageFormat.format(
				ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_Title, kind);
		String toolTipText = MessageFormat.format(
				ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_ToolTipText, kind);
		return new SwitchFilterAction(text, toolTipText, Severity.INFO);
	}
	
	private Action createSuccessNodesFilterAction() {
		String kind = ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_Kind_OK;
		String text = MessageFormat.format(
				ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_Title, kind);
		String toolTipText = MessageFormat.format(
				ValidationDebugMessages.ValidityView_Action_ShowNodesByKind_ToolTipText, kind);
		return new SwitchFilterAction(text, toolTipText, Severity.OK);
	}
	
	private class SwitchFilterAction extends Action {
		private final Severity severity;
		
		public SwitchFilterAction(String text, String toolTipText, Severity acceptedSeverity) {
			super(text, IAction.AS_CHECK_BOX);
			setToolTipText(toolTipText);
			this.severity = acceptedSeverity;
		}
		
		@Override
		public void run() {
			if (this.isChecked()) {
				validityView.addFilteredSeverity(severity);
			} else {
				validityView.removeFilteredSeverity(severity);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.IMenuCreator#dispose()
	 */
	public void dispose() {
		menuManager.dispose();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Control)
	 */
	public Menu getMenu(Control parent) {
		// Creates the menu if needed, or removes all elements except for the save action
		if (menuManager.getMenu() == null) {
			menuManager.createContextMenu(parent);
		} else {
			menuManager.removeAll();
		}

		addActionToMenu(enableErrorNodesAction);
		addActionToMenu(enableInfoNodesAction);
		addActionToMenu(enableFatalNodesAction);
		addActionToMenu(enableWarningNodesAction);
		addActionToMenu(enableSuccessNodesAction);

		return menuManager.getMenu();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.action.IMenuCreator#getMenu(org.eclipse.swt.widgets.Menu)
	 */
	public Menu getMenu(Menu parent) {
		if (menuManager.getMenu() != null) {
			return menuManager.getMenu();
		}
		return null;
	}
	
	/**
	 * Adds the given action to this action's menu.
	 * 
	 * @param action
	 *			{@link Action} to add to this action's menu.
	 */
	public void addActionToMenu(IAction action) {
		menuManager.add(new ActionContributionItem(action));
	}
}