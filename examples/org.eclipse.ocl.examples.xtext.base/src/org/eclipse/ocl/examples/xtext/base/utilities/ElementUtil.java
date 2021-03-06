/**
 * <copyright>
 *
 * Copyright (c) 2010,2011 E.D.Willink and others.
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
 * $Id: ElementUtil.java,v 1.10 2011/05/22 21:06:21 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.base.utilities;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.MonikeredElement;
import org.eclipse.ocl.examples.pivot.TemplateBinding;
import org.eclipse.ocl.examples.pivot.TemplateParameter;
import org.eclipse.ocl.examples.pivot.TemplateSignature;
import org.eclipse.ocl.examples.pivot.TemplateableElement;
import org.eclipse.ocl.examples.pivot.utilities.TypeManager;
import org.eclipse.ocl.examples.xtext.base.baseCST.ClassCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.MonikeredElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.NamedElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.OperationCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ParameterableElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateBindingCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TypedElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TypedTypeRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.WildcardTypeRefCS;
import org.eclipse.ocl.examples.xtext.base.cs2pivot.CS2Pivot;
import org.eclipse.ocl.examples.xtext.base.scope.ScopeAdapter;
import org.eclipse.ocl.examples.xtext.base.scope.ScopeCSAdapter;
import org.eclipse.ocl.examples.xtext.base.scoping.cs.ModelElementCSScopeAdapter;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class ElementUtil
{
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
	
	public static MonikeredElementCS getCsElement(MonikeredElement obj) {
		Resource resource = obj.eResource();
		if (resource == null) {
			return null;
		}
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet == null) {
			return null;
		}
		CS2Pivot cs2Pivot = CS2Pivot.findAdapter(resourceSet);
		if (cs2Pivot == null) {
			return null;
		}
		String moniker = obj.getMoniker();
		Map<String, MonikeredElementCS> moniker2CSMap = cs2Pivot.computeMoniker2CSMap();
		MonikeredElementCS csMonikeredElement = moniker2CSMap.get(moniker);
		return csMonikeredElement;
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

	public static ILeafNode getLeafNode(INode node) {
		ILeafNode leafNode = null;
		if (node instanceof ILeafNode) {
			return (ILeafNode) node;
		}
		else {
			for (ILeafNode lNode : node.getLeafNodes()) {
				if (!lNode.isHidden()) {
					leafNode = lNode;
					return leafNode;
				}
			}
		}
		return null;
	}

	public static int getLower(TypedElementCS csTypedElement) {
		String multiplicity = csTypedElement.getMultiplicity();
		if (multiplicity == null) {
			if (csTypedElement.getOwnedType() == null) {		// This is arbitrary; it makes Ecore default serializations work
				return 0;
			}
		}
		else if ("*".equals(multiplicity)) {
			return 0;
		}
		else if ("+".equals(multiplicity)) {
			return 1;
		}
		else if ("?".equals(multiplicity)) {
			return 0;
		}
		return csTypedElement.getLower();
	}

	public static <T extends NamedElementCS> T getNamedElementCS(Collection<T> namedElements, String name) {
		for (T namedElement : namedElements) {
			if (name.equals(namedElement.getName())) {
				return namedElement;
			}
		}
		return null;
	}

	public static boolean getQualifier(List<String> qualifiers, String trueString, String falseString, boolean defaultValue) {
		if (qualifiers.contains(trueString)) {
			return true;
		}
		else if (qualifiers.contains(falseString)) {
			return false;
		}
		else {
			return defaultValue;
		}
	}

	public static ScopeAdapter getScopeAdapter(TypeManager typeManager, Element element) {
		return ModelElementCSScopeAdapter.getScopeAdapter(typeManager, element);
	}

	public static ScopeCSAdapter getScopeCSAdapter(ElementCS csElement) {
		return ModelElementCSScopeAdapter.getScopeCSAdapter(csElement);
	}

	public static String getText(ElementCS csElement) {
		ICompositeNode node = NodeModelUtils.getNode(csElement);
		return NodeModelUtils.getTokenText(node);
	}

	public static String getText(TypedTypeRefCS csElement, EReference feature) {
		List<INode> nodes = NodeModelUtils.findNodesForFeature(csElement, feature);
		assert nodes.size() == 1;
		if ((nodes == null) || nodes.isEmpty()) {
			return null;
		}
		else if (nodes.size() == 1) {
			return NodeModelUtils.getTokenText(nodes.get(0));
		}
		else {
			StringBuffer s = new StringBuffer();
			for (INode node : nodes) {
				s.append(NodeModelUtils.getTokenText(node));
			}
			return s.toString();
		}
	}

	public static int getUpper(TypedElementCS csTypedElement) {
		String multiplicity = csTypedElement.getMultiplicity();
		if (multiplicity == null) {
			if (csTypedElement.getOwnedType() == null) {		// This is arbitrary; it makes Ecore default serializations work
				return 1;
			}
		}
		else if ("*".equals(multiplicity)) {
			return -1;
		}
		else if ("+".equals(multiplicity)) {
			return -1;
		}
		else if ("?".equals(multiplicity)) {
			return 1;
		}
		return csTypedElement.getUpper();
	}

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

	public static boolean isOrdered(TypedElementCS csTypedElement) {
		return csTypedElement.getQualifier().contains("ordered");
	}

	public static boolean isUnique(TypedElementCS csTypedElement) {
		return getQualifier(csTypedElement.getQualifier(), "unique", "!unique", true);
	}

	public static boolean isSpecialization(TemplateBindingCS csTemplateBinding) {
		TypedTypeRefCS csTypedTypeRef = csTemplateBinding.getOwningTemplateBindableElement();
		Element type = csTypedTypeRef.getPivot();
		for (TemplateParameterSubstitutionCS csTemplateParameterSubstitution : csTemplateBinding.getOwnedParameterSubstitution()) {
			ParameterableElementCS ownedActualParameter = csTemplateParameterSubstitution.getOwnedActualParameter();
			if (ownedActualParameter instanceof WildcardTypeRefCS) {
				return true;
			}
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
}
