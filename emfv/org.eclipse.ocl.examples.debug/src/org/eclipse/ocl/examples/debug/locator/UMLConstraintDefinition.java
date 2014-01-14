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
package org.eclipse.ocl.examples.debug.locator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.validation.debug.locator.ConstraintDefinition;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.uml2.uml.Constraint;


public class UMLConstraintDefinition implements ConstraintDefinition {

	private final Constraint definition;
	private final Resource resource;
	
	public UMLConstraintDefinition(@NonNull Constraint definition, @NonNull Resource resource){
		this.definition = definition;
		this.resource = resource;
	}
	
	public Object getDefinition() {
		return definition;
	}
	
	public String getExpression() {
		return definition.getSpecification().toString();
	}
	
	public Resource getResource() {
		return resource;
	}
}
