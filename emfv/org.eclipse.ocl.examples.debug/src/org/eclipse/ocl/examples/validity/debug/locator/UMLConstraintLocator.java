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
package org.eclipse.ocl.examples.validity.debug.locator;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.examples.emf.validation.debug.ValidityManager;
import org.eclipse.ocl.examples.emf.validation.debug.ValidityModel;
import org.eclipse.ocl.examples.emf.validation.debug.locator.AbstractConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Result;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ValidatableNode;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.domain.evaluation.DomainModelManager;
import org.eclipse.ocl.examples.pivot.Environment;
import org.eclipse.ocl.examples.pivot.EnvironmentFactory;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.OpaqueExpression;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.utilities.PivotEnvironmentFactory;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.PackageImport;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.Type;

public class UMLConstraintLocator extends AbstractConstraintLocator
{
	protected void appendPath(@NonNull StringBuilder s, @NonNull NamedElement eObject) {
		EObject eContainer = eObject.eContainer();
		if (eContainer instanceof NamedElement) {
			appendPath(s, (NamedElement)eContainer);
			s.append("::");
		}
		s.append(eObject.getName());
	}

	@Override
	public @NonNull Set<URI> getAllTypes(@NonNull EModelElement constrainingType) {
		if (constrainingType instanceof org.eclipse.uml2.uml.Class) {
			Set<URI> allTypes = new HashSet<URI>();
			allTypes.add(EcoreUtil.getURI(constrainingType));
			if (constrainingType instanceof org.eclipse.uml2.uml.Class) {
				getAllTypes(allTypes, ((org.eclipse.uml2.uml.Class)constrainingType).getSuperClasses());
			}
			return allTypes;
		}
		else {
			return super.getAllTypes(constrainingType);
		}
	}

	private void getAllTypes(Set<URI> knownTypes, Iterable<org.eclipse.uml2.uml.Class> moreTypes) {
		for (org.eclipse.uml2.uml.Class anotherType : moreTypes) {
			if (knownTypes.add(EcoreUtil.getURI(anotherType))) {
				getAllTypes(knownTypes, anotherType.getSuperClasses());
			}
		}
	}

	public @Nullable Map<EModelElement, List<LeafConstrainingNode>> getConstraints(@NonNull ValidityModel validityModel, @NonNull EPackage ePackage, @NonNull Set<Resource> resources) {
			Map<EModelElement, List<LeafConstrainingNode>> map = null;
			for (Resource resource : resources) {
				for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					if (eObject instanceof Constraint) {
						Constraint umlConstraint = (Constraint)eObject;
						Element contextElement = umlConstraint.getContext();
						if (contextElement instanceof Type) {
							String label = validityModel.getLabel(umlConstraint);
	/*					LeafConstrainingNode constraint = validityModel.createLeafConstrainingNode();
						constraint.setConstraintLocator(this);
						constraint.setConstrainingObject(umlConstraint);
						constraint.setLabel(label);
						ConstrainingNode constrainingNode = validityModel.getConstrainingNode(constrainedElement);
						constrainingNode.getChildren().add(constraint);
						if (map == null) {
							map = new HashMap<EModelElement, List<LeafConstrainingNode>>();
						}
						List<LeafConstrainingNode> constraints = map.get(constrainedElement);
						if (constraints == null) {
							constraints = new ArrayList<LeafConstrainingNode>();
							map.put(constrainedElement, constraints);
						}
						constraints.add(constraint); */
	//						EClass eC = constrainedElement.eClass();
							UMLConstraintDefinition definition = new UMLConstraintDefinition(umlConstraint, resource);
							map = createLeafConstrainingNode(map, validityModel, contextElement, umlConstraint, definition, label);
						}
					}
				}
			}
			return map;
		}

	@Override
	public @Nullable Collection<Resource> getImports(@NonNull EPackage ePackage, @NonNull Resource resource) {
		Set<Resource> imports = new HashSet<Resource>();
		for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
			@SuppressWarnings("null")@NonNull EObject eObject = tit.next();
			if (eObject instanceof PackageImport) {
				PackageImport umlPackageImport = (PackageImport)eObject;
				Package importedPackage = umlPackageImport.getImportedPackage();
				if (importedPackage != null) {
					Resource eResource = importedPackage.eResource();
					if (eResource != null) {
						imports.add(eResource);
					}
				}
//				tit.prune();
			}
//			else if (eObject instanceof Type) {
//				tit.prune();
//			}
			Resource eResource = eObject.eClass().eResource();
			if (eResource != null) {
				imports.add(eResource);
			}
		}
		return imports;
	}

	@Override
	public @NonNull String getLabel(@NonNull EModelElement eObject) {
		if (eObject instanceof NamedElement) {			// FIXME debugging - remove UML dependency
			StringBuilder s = new StringBuilder();
			appendPath(s, (NamedElement)eObject);
			@SuppressWarnings("null") @NonNull String string = s.toString();
			return string;
		}
		else {
			return super.getLabel(eObject);
		}
	}

	@Override
	public @Nullable URI getURI(@NonNull EObject eObject) {
		EObject eContainer = eObject;
		for ( ; true; eContainer = eContainer.eContainer()) {
			if (eContainer == null) {
				return null;
			}
			if (eContainer instanceof Package) {
				break;
			}
		}
		Stereotype appliedStereotype = ((Package)eContainer).getAppliedStereotype("Ecore::EPackage");
		Object value = ((Package)eContainer).getValue(appliedStereotype, "nsURI");
		String nsURI;
		if (value != null) {
			nsURI = value.toString();
		}
		else {
			nsURI = ((Package)eContainer).getURI();
			if (nsURI == null) {
				return null;
			}
		}
		Resource resource = eObject.eResource();
		if (resource == null) {
			return null;
		}
		String uriFragment = resource.getURIFragment(eObject);
		if (!uriFragment.startsWith("//")) {
			uriFragment = "//" + uriFragment;		// FIXME regularize this ?? UML2Ecore
		}
		return URI.createURI(nsURI).appendFragment(uriFragment);
	}

	@Override
	public void validate(@NonNull Result result, @NonNull ValidityManager validityManager) {
		ValidatableNode validatableNode = result.getValidatableNode();
		EObject constrainedObject = validatableNode.getConstrainedObject();
		LeafConstrainingNode leafConstrainingNode = result.getLeafConstrainingNode();
		org.eclipse.uml2.uml.Constraint umlConstraint = (org.eclipse.uml2.uml.Constraint)leafConstrainingNode.getConstrainingObject();
		if (umlConstraint == null) {
			return;
		}
		MetaModelManager metaModelManager = PivotUtil.findMetaModelManager(umlConstraint);
		if (metaModelManager == null) {
			Resource eResource = umlConstraint.eResource();
			if (eResource == null) {
				return;
			}
			metaModelManager = PivotUtil.getMetaModelManager(eResource);
		}
		try {
			org.eclipse.ocl.examples.pivot.Constraint pivotConstraint = metaModelManager.getPivotOf(org.eclipse.ocl.examples.pivot.Constraint.class, umlConstraint);
			if (pivotConstraint == null) {
				throw new ParserException("Failed to create pivot Constraint");
			}
			OpaqueExpression specification = pivotConstraint.getSpecification();
			if (specification == null) {
				throw new ParserException("Failed to create pivot Specification");
			}
			ExpressionInOCL query = PivotUtil.getExpressionInOCL(pivotConstraint, specification);
			if (query == null) {
				throw new ParserException("Failed to create pivot Query");
			}
			EnvironmentFactory environmentFactory = new PivotEnvironmentFactory(null, metaModelManager);
			Environment rootEnvironment = environmentFactory.createEnvironment();
			EvaluationEnvironment evaluationEnvironment = environmentFactory.createEvaluationEnvironment();
			evaluationEnvironment.add(query.getContextVariable(), constrainedObject);
			DomainModelManager modelManager = evaluationEnvironment.createModelManager(constrainedObject);
			EvaluationVisitor evaluationVisitor = environmentFactory.createEvaluationVisitor(rootEnvironment, evaluationEnvironment, modelManager);
			Object verdict = evaluationVisitor.evaluate(query);
			if (verdict == Boolean.TRUE) {
				result.setSeverity(Severity.OK);
			}
			else if (verdict == Boolean.FALSE) {
				result.setSeverity(Severity.WARNING);
			}
			else if (verdict == null) {
				result.setSeverity(Severity.ERROR);
			}
			else {
				result.setSeverity(Severity.FATAL);
			}
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setSeverity(Severity.FATAL);
		}
	}
}