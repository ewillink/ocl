/**
 * <copyright>
 *
 * Copyright (c) 2010 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *
 * </copyright>
 *
 * $Id: ElementUtil.java,v 1.1.2.2 2010/12/06 17:53:58 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.base.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.examples.pivot.ParameterableElement;
import org.eclipse.ocl.examples.pivot.TemplateBinding;
import org.eclipse.ocl.examples.pivot.TemplateParameter;
import org.eclipse.ocl.examples.pivot.TemplateSignature;
import org.eclipse.ocl.examples.pivot.TemplateableElement;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.xtext.base.baseCST.ClassCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.NamedElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.OperationCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ParameterableElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ParameterizedTypeRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateBindingCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TypedElementCS;
import org.eclipse.ocl.examples.xtext.base.scope.ScopeAdapter;
import org.eclipse.ocl.examples.xtext.base.scope.ScopeCSAdapter;
import org.eclipse.ocl.examples.xtext.base.scoping.cs.ModelElementCSScopeAdapter;
import org.eclipse.xtext.parsetree.CompositeNode;
import org.eclipse.xtext.parsetree.NodeAdapter;
import org.eclipse.xtext.parsetree.NodeUtil;

public class ElementUtil
{
	public static boolean conformsTo(EStructuralFeature eStructuralFeature, EClassifier contentType) {
		if (eStructuralFeature == null) {			// Wildcard match all
			return true;
		}
		EClassifier targetType = eStructuralFeature.getEType();
		if (targetType == contentType) {
			return true;
		}
		if (!(targetType instanceof EClass)) {
			return false;
		}
		if (!(contentType instanceof EClass)) {
			return false;
		}
		return ((EClass) targetType).isSuperTypeOf((EClass) contentType);
	}

	public static String getCollectionTypeName(TypedElementCS csTypedElement) {
		String multiplicity = csTypedElement.getMultiplicity();
		if (multiplicity != null) {
			if ("?".equals(multiplicity)) { //$NON-NLS-1$
				return null;
			}
		}
		else {
			int upper = csTypedElement.getUpper();
			if (upper == 1) {
				return null;
			}
		}
		List<String> qualifiers = csTypedElement.getQualifier();
		boolean isOrdered = true;
		boolean isUnique = true;
		if (qualifiers.contains("!ordered")) { //$NON-NLS-1$
			isOrdered = false;
		}
		else if (qualifiers.contains("ordered")) { //$NON-NLS-1$
			isOrdered = true;
		}
		if (qualifiers.contains("!unique")) { //$NON-NLS-1$
			isUnique = false;
		}
		else if (qualifiers.contains("unique")) { //$NON-NLS-1$
			isUnique = true;
		}
		if (isOrdered) {
			return isUnique ? "OrderedSet" : "Sequence"; //$NON-NLS-1$ //$NON-NLS-2$
		}
		else {
			return isUnique ? "Set" : "Bag"; //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	public static TemplateParameter getFormalTemplateParameter(TemplateParameterSubstitutionCS csTemplateParameterSubstitution) {
		TemplateBindingCS csTemplateBinding = csTemplateParameterSubstitution.getOwningTemplateBinding();
		int index = csTemplateBinding.getOwnedParameterSubstitution().indexOf(csTemplateParameterSubstitution);
		if (index < 0) {
			return null;
		}
		TemplateBinding templateBinding = (TemplateBinding) csTemplateBinding.getPivot();
		TemplateSignature templateSignature = templateBinding.getSignature();
		List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
		if (templateParameters.size() <= index) {
			return null;
		}
		return templateParameters.get(index);
	}

	public static <T extends NamedElementCS> T getNamedElementCS(Collection<T> namedElements, String name) {
		for (T namedElement : namedElements) {
			if (name.equals(namedElement.getName())) {
				return namedElement;
			}
		}
		return null;
	}

	public static CompositeNode getParserNode(EObject eObject) {
		NodeAdapter nodeAdapter = NodeUtil.getNodeAdapter(eObject);
		return nodeAdapter != null ? nodeAdapter.getParserNode() : null;
	}

	public static ScopeAdapter getScopeAdapter(EObject element) {
		return ModelElementCSScopeAdapter.getScopeAdapter(element);
	}

	public static ScopeCSAdapter getScopeCSAdapter(EObject element) {
		return ModelElementCSScopeAdapter.getScopeCSAdapter(element);
	}

	public static List<TemplateParameter> getTemplateParameters(TemplateableElement templateableElement) {
		if (templateableElement != null) {
			TemplateSignature ownedTemplateSignature = templateableElement.getOwnedTemplateSignature();
			if (ownedTemplateSignature != null) {
				return ownedTemplateSignature.getParameters();
			}
		}
		return Collections.emptyList();
	}

	public static List<ParameterableElement> getTemplateParameterables(TemplateableElement templateableElement) {
		if (templateableElement == null) {
			return Collections.emptyList();
		}
		TemplateSignature ownedTemplateSignature = templateableElement.getOwnedTemplateSignature();
		if (ownedTemplateSignature == null) {
			return Collections.emptyList();
		}
		List<TemplateParameter> templateParameters = ownedTemplateSignature.getParameters();
		if (templateParameters.size() == 0) {
			return Collections.emptyList();
		}
		if (templateParameters.size() == 1) {
			return Collections.singletonList(templateParameters.get(0).getParameteredElement());
		}
		List<ParameterableElement> results = new ArrayList<ParameterableElement>(templateParameters.size());
		for (TemplateParameter templateParameter : templateParameters) {
			results.add(templateParameter.getParameteredElement());
		}
		return results;
	}

	public static List<Type> getTypeTemplateParameterables(TemplateableElement templateableElement) {
		if (templateableElement == null) {
			return Collections.emptyList();
		}
		TemplateSignature ownedTemplateSignature = templateableElement.getOwnedTemplateSignature();
		if (ownedTemplateSignature == null) {
			return Collections.emptyList();
		}
		List<TemplateParameter> templateParameters = ownedTemplateSignature.getParameters();
		if (templateParameters.size() == 0) {
			return Collections.emptyList();
		}
		if (templateParameters.size() == 1) {
			return Collections.singletonList((Type)templateParameters.get(0).getParameteredElement());
		}
		List<Type> results = new ArrayList<Type>(templateParameters.size());
		for (TemplateParameter templateParameter : templateParameters) {
			results.add((Type) templateParameter.getParameteredElement());
		}
		return results;
	}

/*	public static Collection<TemplateParameterSubstitutionCS> getTemplateParameterSubstitutions(ParameterizedTypeRefCS templateableElement) {
		if (templateableElement != null) {
			TemplateBindingCS ownedTemplateBinding = templateableElement.getOwnedTemplateBinding();
//			if (ownedTemplateBinding != null) {
//				return ownedTemplateBinding.getOwnedTemplateParameter();
//			}
		}
		return ECollections.emptyEList();
	} */
	
/*	public static TypeBindingCS getTypeBinding(TypeParameterCS typeParameter, TypeBindingsCS bindings) {
		if (bindings != null) {
			for (TypeBindingCS binding : bindings.getBindings()) {
				if (binding.getTypeParameter() == typeParameter) {
					return binding;
				}
			}
		}
		return null;
	} */

	public static boolean isInOperation(ElementCS csElement) {
		for (EObject eObject = csElement; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof OperationCS) {
				return true;
			}
			else if (eObject instanceof ClassCS) {
				return false;
			}
		}
		return false;
	}

	public static boolean isSpecialization(TemplateBindingCS csTemplateBinding) {
		ParameterizedTypeRefCS csParameterizedTypeRef = csTemplateBinding.getOwningTemplateBindableElement();
		Type type = csParameterizedTypeRef.getPivot();
		for (TemplateParameterSubstitutionCS csTemplateParameterSubstitution : csTemplateBinding.getOwnedParameterSubstitution()) {
			ParameterableElementCS ownedActualParameter = csTemplateParameterSubstitution.getOwnedActualParameter();
			org.eclipse.ocl.examples.pivot.Class actualParameterClass = (org.eclipse.ocl.examples.pivot.Class) ownedActualParameter.getPivot();
			TemplateParameter owningTemplateParameter = actualParameterClass.getOwningTemplateParameter();
			if (owningTemplateParameter == null) {
				return true;
			}
			TemplateSignature signature = owningTemplateParameter.getSignature();
			TemplateableElement template = signature.getTemplate();
			if (template != type) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isValidIdentifier(String value) {
		int iMax = value.length();
		for (int i = 0; i < iMax; i++) {
			char c = value.charAt(i);
			if (('A' <= c) && (c <= 'Z')) {					
			}
			else if (('a' <= c) && (c <= 'z')) {					
			}
			else if (c == '_') {					
			}
			else if (('0' <= c) && (c <= '9') && (i > 0)) {					
			}
			else {
				return false;
			}
		}
		return true;
	}

	public static Type specializeClass(ClassCS csClass) {
//		if (ElementUtil.getTemplateParameters(csClass).isEmpty()) {
			return (Type) csClass;				
/*		}
		Signature s = new Signature();
		csClass.getSignature(s, bindings);
		String name = s.toString();
		BoundDocumentCS boundDocument = bindings.getBoundDocument();
		ClassifierCS boundClassifier = boundDocument.getClassifier(name);
		if (boundClassifier != null) {
			return boundClassifier;
		}
		BoundClassifierCS boundElement = BaseCSTFactory.eINSTANCE.createBoundClassCS();
		boundElement.setName(name);
		boundElement.setBinds(csClass);
		boundElement.setBindings(bindings);
		boundDocument.getOwnedType().add(boundElement);
		return boundElement; */
	}

/*	public static OperationCS specializeOperation(OperationCS csOperation, TypeBindingsCS bindings) {
		ClassCS owner = csOperation.getOwner();
		List<OperationCS> boundOperations = null;
		if (getTemplateParameters(owner).isEmpty()) {
			if (getTemplateParameters(csOperation).isEmpty()) {
				return csOperation;
			}
			BoundDocumentCS boundDocument = bindings.getBoundDocument();
			String ownerName = owner.getSignature();
			ClassCS boundClass = (ClassCS) boundDocument.getClassifier(ownerName);
			if (boundClass == null) {
				BoundClassCS boundElement = BaseCSTFactory.eINSTANCE.createBoundClassCS();
				boundElement.setName(ownerName);
				boundElement.setBinds(owner);
				boundElement.setBindings(bindings);
				boundDocument.getOwnedType().add(boundElement);
				boundClass = boundElement;
			}
			boundOperations = boundClass.getOwnedOperation();
		}
		else {
			ScopeAdapter scopeAdapter = AbstractScopeAdapter.getScopeAdapter(owner);
			ClassifierCS boundClass = scopeAdapter.getSynthesizedType(bindings);
			boundOperations = ((ClassCS) boundClass).getOwnedOperation();
		}
		Signature s = new Signature();
		csOperation.getSignature(s, bindings);
		String name = s.toString();
		for (OperationCS boundOperation : boundOperations) {
			if (boundOperation.getName().equals(name)) {
				return boundOperation;
			}
		}
		BoundOperationCS boundElement = BaseCSTFactory.eINSTANCE.createBoundOperationCS();
		boundElement.setName(name);
		boundElement.setBinds(csOperation);
		boundElement.setBindings(bindings);
		boundElement.setOwnedType(specializeType(csOperation.getOwnedType(), bindings));
		boundElement.getQualifiers().addAll(csOperation.getQualifiers());
		if (csOperation.eIsSet(BaseCSTPackage.Literals.TYPED_ELEMENT_CS__LOWER)) {
			boundElement.setLower(csOperation.getLower());
		}
		if (csOperation.eIsSet(BaseCSTPackage.Literals.TYPED_ELEMENT_CS__UPPER)) {
			boundElement.setUpper(csOperation.getUpper());
		}
		if (csOperation.eIsSet(BaseCSTPackage.Literals.TYPED_ELEMENT_CS__MULTIPLICITY)) {
			boundElement.setMultiplicity(csOperation.getMultiplicity());
		}
		for (ParameterCS csParameter : csOperation.getOwnedParameter()) {
			ParameterCS boundParameter = BaseCSTFactory.eINSTANCE.createParameterCS();
			boundParameter.setName(csParameter.getName());
			boundParameter.setOwnedType(specializeType(csParameter.getOwnedType(), bindings));
			boundParameter.getQualifiers().addAll(csParameter.getQualifiers());
			if (csOperation.eIsSet(BaseCSTPackage.Literals.TYPED_ELEMENT_CS__LOWER)) {
				boundParameter.setLower(csParameter.getLower());
			}
			if (csOperation.eIsSet(BaseCSTPackage.Literals.TYPED_ELEMENT_CS__UPPER)) {
				boundParameter.setUpper(csParameter.getUpper());
			}
			if (csOperation.eIsSet(BaseCSTPackage.Literals.TYPED_ELEMENT_CS__MULTIPLICITY)) {
				boundParameter.setMultiplicity(csParameter.getMultiplicity());
			}
			boundElement.getOwnedParameter().add(boundParameter);
		}
		boundOperations.add(boundElement);
		return boundElement;
	} */
	
/*	public static TypedRefCS specializeType(TypedRefCS csTypeRef, TypeBindingsCS bindings) {
		if (csTypeRef instanceof PrimitiveTypeRefCS) {
			PrimitiveTypeRefCS boundType = BaseCSTFactory.eINSTANCE.createPrimitiveTypeRefCS();
			boundType.setName(((PrimitiveTypeRefCS)csTypeRef).getName());
			return boundType;
		}
		if (csTypeRef instanceof TypedTypeRefCS) {
			TypeCS csType = ((TypedTypeRefCS)csTypeRef).getType();
			TypedTypeRefCS boundType = BaseCSTFactory.eINSTANCE.createTypedTypeRefCS();
			boundType.setType(csType);
			if (csType instanceof ClassifierCS) {
				Collection<TemplateParameterCS> typeParameters = getTemplateParameters((ClassifierCS)csType);
				List<TypeRefCS> typeArguments = boundType.getTypeArguments();
				specializeTypeParameters(typeArguments, typeParameters, bindings);
			}
			return boundType;
		}
		return null;
	} */
	
/*	public static TypedRefCS specializeType(TypeCS csType, TypeBindingsCS bindings) {
		if (csType instanceof PrimitiveTypeCS) {
			PrimitiveTypeRefCS boundType = BaseCSTFactory.eINSTANCE.createPrimitiveTypeRefCS();
			boundType.setName(((PrimitiveTypeCS)csType).getValue());
			return boundType;
		}
		if (csType instanceof ClassifierCS) {
			ClassifierCS csClassifier = (ClassifierCS)csType;
			TypedTypeRefCS boundType = BaseCSTFactory.eINSTANCE.createTypedTypeRefCS();
			boundType.setType(csClassifier);
			Collection<TemplateParameterCS> typeParameters = getTemplateParameters(csClassifier);
			List<TypeRefCS> typeArguments = boundType.getTypeArguments();
			specializeTypeParameters(typeArguments, typeParameters, bindings);
			return boundType;
		}
		return null;
	} */

/*	public static void specializeTypeParameters(List<TypeRefCS> typeArguments,
			List<TypeParameterCS> typeParameters, TypeBindingsCS bindings) {
		for (TypeParameterCS csTypeParameter : typeParameters) {
			TypeBindingCS csTypeBinding = getTypeBinding(csTypeParameter, bindings);
			if (csTypeBinding != null) {
				typeArguments.add(specializeType(csTypeBinding.getTypeArgument(), bindings));
			}
		}
	} */
}