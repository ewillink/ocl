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
package org.eclipse.ocl.examples.emf.validation.debug.locator;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.examples.emf.validation.debug.ValidityManager;
import org.eclipse.ocl.examples.emf.validation.debug.ValidityModel;
import org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Result;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A ConstraintLocator supports location of a particular category of Constraint and dispatch of those constraints for validation.
 * <p>
 * The org.eclipse.ocl.examples.emf.validation.debug.constraint_locator extension point is used to register ConstraintLocators.
 */
public interface ConstraintLocator
{
	public static interface Descriptor
	{
		/**
		 * Return the ConstraintLocator described by this.
		 */
		@NonNull ConstraintLocator getConstraintLocator();
	}

	/**
	 * Return all constrainingTypes for a given tyoe; typically this returns the supertype closeure.
	 */
	@NonNull Set<URI> getAllTypes(@NonNull EModelElement constrainingType);

	/**
	 * Return a constrainedType-to-constraint map for all types in the given resources that have an ePackage whose URI complies with
	 * the registration of this ConstraintLocator. The validityModel is used to create the LeafConstrainingNodes for each constraint.
	 */
	@Nullable Map<EModelElement, List<LeafConstrainingNode>> getConstraints(@NonNull ValidityModel validityModel, @NonNull EPackage ePackage, @NonNull Set<Resource> resources);

	/**
	 * Return an icon to identify this kind of ConstraintLocator.
	 */
	@Nullable Object getImage();

	/**
	 * Return any resources imported from within resource.
	 */
	@Nullable Collection<Resource> getImports(@NonNull EPackage ePackage, @NonNull Resource resource);

	/**
	 * Return a diagnostic label for eObject.
	 */
	@NonNull String getLabel(@NonNull EModelElement eObject);

	@Nullable URI getURI(@NonNull EObject eObject);
	
	/**
	 * Update the validation result to include the verdict of the validation using validityManager to provide shared services.
	 */
	void validate(@NonNull Result result, @NonNull ValidityManager validityManager);
}