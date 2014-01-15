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
package org.eclipse.ocl.examples.emf.validation.debug.ui.actions;

import java.net.URL;

import org.eclipse.ocl.examples.emf.validation.debug.ui.ValidityUIPlugin;
import org.eclipse.ocl.examples.emf.validation.debug.ui.view.IDEValidityManager;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

public final class StopValidityAction extends Action
{
//	protected final @NonNull IDEValidityManager validityManager;
	
	public StopValidityAction(@NonNull IDEValidityManager validityManager) {
		super("Stop Selective Validation");
//		this.validityManager = validityManager;
		setToolTipText("Abort any active validation run");
		URL image = (URL) ValidityUIPlugin.INSTANCE.getImage("stop_nav.png");
		setImageDescriptor(ImageDescriptor.createFromURL(image));
	}

	@Override
	public void run() {
		IDEValidityManager.stopValidation();
	}
}