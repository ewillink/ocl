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
 *  Obeo - Add new Icon for disabled Nodes
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.emf.validation.debug.ui.view;

import java.util.Collections;
import java.util.List;

import org.eclipse.ocl.examples.emf.validation.debug.ui.ValidityUIPlugin;
import org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Result;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ValidatableNode;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.swt.graphics.Image;

public class SeveritiesDecorator extends SideBySideImageDecorator
{
	public static Object getSeverityImage(Object element) {
		String imageName = "query.gif";
		Result worstResult = ((AbstractNode)element).getWorstResult();
		Severity worst = worstResult != null ? worstResult.getSeverity() : Severity.UNKNOWN;
		switch (worst) {
			case UNKNOWN: imageName = "disabled.gif"; break;
			case OK: imageName = "success.gif"; break;
			case INFO: imageName = "info.gif"; break;
			case WARNING: imageName = "warning.gif"; break;
			case ERROR: imageName = "error.gif"; break;
			case FATAL: imageName = "failed.gif"; break;
			default: imageName = "unknown.gif"; break;
		}
		Object image2 = ValidityUIPlugin.INSTANCE.getImage(imageName);
		return image2;
	}

	protected final @NonNull IDEValidityManager validityManager;
	
	public SeveritiesDecorator(@NonNull IDEValidityManager validityManager) {
		super(2);
		this.validityManager = validityManager;
	}

	public Image decorateImage(Image image, Object element) {
		Object image2 = getSeverityImage(element);
		return composeImages(image, image2);
	}

	public List<Result> getResults(Object element) {
		if (element instanceof ConstrainingNode) {
			return validityManager.getConstrainingNodeResults((ConstrainingNode)element);
		}
		else if (element instanceof ValidatableNode) {
			return validityManager.getValidatableNodeResults((ValidatableNode)element);
		}
		else {
			return Collections.emptyList();
		}
	}
}
