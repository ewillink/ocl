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
 *  Obeo - Implement constraints validation 
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.validity.debug.locator;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.examples.emf.validation.debug.ValidityManager;
import org.eclipse.ocl.examples.emf.validation.debug.ValidityModel;
import org.eclipse.ocl.examples.emf.validation.debug.locator.AbstractConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Result;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Severity;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.Namespace;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.utilities.BaseResource;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;
import org.eclipse.ocl.examples.pivot.validation.PivotEObjectValidator.ValidationAdapter;

public class PivotConstraintLocator extends AbstractConstraintLocator
{
	public @Nullable Map<EModelElement, List<LeafConstrainingNode>> getConstraints(@NonNull ValidityModel validityModel, @NonNull EPackage ePackage, @NonNull Set<Resource> resources) {
		Map<EModelElement, List<LeafConstrainingNode>> map = null;
		for (Resource resource : resources) {
			Resource asResource = null;
			if (resource instanceof BaseResource) {
				asResource = ((BaseResource) resource).getASResource(null);
			}
			if (asResource != null) {
				MetaModelManager metaModelManager = PivotUtil.findMetaModelManager(asResource);
				if (metaModelManager != null) {
					for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
						EObject eObject = tit.next();
						if (eObject instanceof Constraint) {
							Constraint pConstraint = (Constraint)eObject;
							Namespace constrainedElement = pConstraint.getContext();
							if (constrainedElement != null) {
								String label = validityModel.getLabel(pConstraint);
								EModelElement eTarget = metaModelManager.getEcoreOfPivot(EModelElement.class, constrainedElement);
								if (eTarget != null) {
									assert resource != null;
									PivotConstraintDefinition definition = new PivotConstraintDefinition(pConstraint, resource);
									map = createLeafConstrainingNode(map, validityModel, eTarget, pConstraint, definition, label);
								}
							}
						}
					}
				}
			}
		}
		return map;
	}

	/**
	 * Returns the {@link IFile} in which the provided {@link Resource} can be
	 * found, if any.
	 * 
	 * @param resource
	 *            the {@link Resource} for which we search the enclosing file
	 * @return the corresponding {@link IFile} or <code>null</code> if one of the following occurs:
	 *         <ul>
	 *         <li>the given resource is null </li>
	 *         <li>the resource {@link URI} cannot be determined</li>
	 *         <li>the resource {@link URI} is not platform oriented</li>
	 *         <li>the project supposed to contain the resource does not actually exist</li>
	 *         <li>the project containing the resource is closed</li>
	 *         </ul>
	 * 
	 */
	private static @Nullable IFile findFile(Resource resource) {
		if (resource == null || resource.getURI() == null || !resource.getURI().isPlatform()) {
			return null;
		}
		
		IPath resourcePath = new Path(resource.getURI().toPlatformString(true));
		IFile iFile = ResourcesPlugin.getWorkspace().getRoot().getFile(resourcePath);

		IProject iProject = iFile.getProject();
		if (!iProject.isAccessible() || !iFile.exists()) {
			return null;
		}
		return iFile;
	}

	@Override
	public void validate(@NonNull Result result, @NonNull ValidityManager validityManager) {
		Constraint constraint = (Constraint) result.getLeafConstrainingNode().getConstrainingObject();
		if (constraint !=null){
			EObject eObject = result.getValidatableNode().getConstrainedObject();
			BasicDiagnostic diagnostic = validityManager.createDefaultDiagnostic(eObject);
			try {
				ResourceSet resourceSet = eObject.eResource().getResourceSet();

				if (resourceSet != null) {
					ValidationAdapter validationAdapter = ValidationAdapter.findAdapter(resourceSet);
					diagnostic.add(validationAdapter.validateSingleConstraint(constraint, eObject, validityManager.createDefaultContext()));
					result.setDiagnostic(diagnostic);
					result.setSeverity(getSeverity(diagnostic));
				}
			} catch (Exception e) {
				result.setException(e);
				result.setSeverity(Severity.FATAL);
			}
		}
	}
}