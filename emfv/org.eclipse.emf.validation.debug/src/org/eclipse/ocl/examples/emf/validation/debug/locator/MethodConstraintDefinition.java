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
package org.eclipse.ocl.examples.emf.validation.debug.locator;

import java.lang.reflect.Method;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;


public class MethodConstraintDefinition
		implements ConstraintDefinition {

	private final Method definition;
	
	MethodConstraintDefinition (@NonNull Method definition){
		this.definition = definition;
	}
	
	public Resource getResource() {
		return null;
	}

	public Object getDefinition() {
		return definition;
	}

	public String getExpression() {
		return null;
	}
}
