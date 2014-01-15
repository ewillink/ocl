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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.examples.emf.validation.debug.ValidityManager;
import org.eclipse.ocl.examples.emf.validation.debug.ValidityModel;
import org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Result;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Severity;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public abstract class AbstractConstraintLocator implements ConstraintLocator, ConstraintLocator.Descriptor
{
	protected @NonNull Map<EModelElement, List<LeafConstrainingNode>> createLeafConstrainingNode(@Nullable Map<EModelElement, List<LeafConstrainingNode>> map,
			@NonNull ValidityModel validityModel, @NonNull EModelElement constrainingType, @NonNull Object constrainingObject, @NonNull ConstraintDefinition definition, @NonNull String label) {
		LeafConstrainingNode constraint = validityModel.createLeafConstrainingNode();
		constraint.setConstraintLocator(this);
		constraint.setLabel(label);
		constraint.setConstrainingObject(constrainingObject);
		constraint.setConstraintDefinition(definition);
		if (map == null) {
			map = new HashMap<EModelElement, List<LeafConstrainingNode>>();
		}
		List<LeafConstrainingNode> constraints = map.get(constrainingType);
		if (constraints == null) {
			constraints = new ArrayList<LeafConstrainingNode>();
			map.put(constrainingType, constraints);
		}
		constraints.add(constraint);
		return map;
	}

	public @NonNull Set<URI> getAllTypes(@NonNull EModelElement constrainingType) {
		Set<URI> allTypes = new HashSet<URI>();
		allTypes.add(EcoreUtil.getURI(constrainingType));
		if (constrainingType instanceof EClass) {
			for (EClass eSuperClass : ((EClass)constrainingType).getEAllSuperTypes()) {
				allTypes.add(EcoreUtil.getURI(eSuperClass));
			}
		}
		return allTypes;
	}

	public @NonNull ConstraintLocator getConstraintLocator() {
		return this;
	}

	public @Nullable Object getImage() {
		return null;
	}

	public @Nullable Collection<Resource> getImports(@NonNull EPackage ePackage, @NonNull Resource resource) {
		return null;
	}

	public @NonNull String getLabel(@NonNull EModelElement eObject) {
		StringBuilder s = new StringBuilder();
		if (eObject instanceof ENamedElement) {
			EObject eContainer = eObject.eContainer();
			if (eContainer instanceof ENamedElement) {
				s.append(((ENamedElement)eContainer).getName() + "::");
			}
			s.append(((ENamedElement)eObject).getName());
		}
		else {
			s.append(String.valueOf(eObject));
		}
		@SuppressWarnings("null") @NonNull String string = s.toString();
		return string;
	}

	protected @NonNull Severity getSeverity(@NonNull Diagnostic diagnostic) {
		Severity severity;
		switch (diagnostic.getSeverity()) {
			case Diagnostic.OK: severity = Severity.OK; break;
			case Diagnostic.INFO: severity = Severity.INFO; break;
			case Diagnostic.WARNING: severity = Severity.WARNING; break;
			case Diagnostic.ERROR: severity = Severity.ERROR; break;
			case Diagnostic.CANCEL: severity = Severity.FATAL; break;
			default: severity = Severity.UNKNOWN; break;
		}
		assert severity != null;
		return severity;
	}

	public @Nullable URI getURI(@NonNull EObject eObject) {
		EObject eContainer = eObject;
		for ( ; true; eContainer = eContainer.eContainer()) {
			if (eContainer == null) {
				return null;
			}
			if (eContainer instanceof EPackage) {
				break;
			}
		}
		String nsURI = ((EPackage)eContainer).getNsURI();
		if (nsURI == null) {
			return null;
		}
		Resource resource = eObject.eResource();
		if (resource == null) {
			return null;
		}
		return URI.createURI(nsURI).appendFragment(resource.getURIFragment(eObject));
	}

	public void validate(@NonNull Result result, @NonNull ValidityManager validityManager) {
		result.setDiagnostic("Unimplemented validate for " + getClass().getName());
		result.setSeverity(Severity.FATAL);
//		System.out.println("Validate '" + result.getValidatableNode() + "' against '" + result.getLeafConstrainingNode() + "'");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {}
	}
}