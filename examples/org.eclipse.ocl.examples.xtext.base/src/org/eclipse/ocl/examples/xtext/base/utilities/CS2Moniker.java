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
 * $Id: CS2Moniker.java,v 1.1.2.3 2010/12/08 10:24:34 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.base.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.TemplateParameter;
import org.eclipse.ocl.examples.pivot.utilities.Abstract2Moniker;
import org.eclipse.ocl.examples.xtext.base.baseCST.ElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.MonikeredElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.NamedElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ParameterCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ParameterizedTypeRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateBindingCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateParameterCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateSignatureCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TemplateableElementCS;
import org.eclipse.ocl.examples.xtext.base.util.BaseCSVisitor;
import org.eclipse.ocl.examples.xtext.base.util.VisitableCS;

public class CS2Moniker extends Abstract2Moniker
{	
	private static final Logger logger = Logger.getLogger(CS2Moniker.class);
	
	public static interface Factory
	{
		public abstract BaseCSVisitor<?, ?> create(CS2Moniker moniker);
	}

	public static void addFactory(EPackage ePackage, Factory factory) {
		csFactoryMap.put(ePackage, factory);
	}

	public static String toString(MonikeredElementCS csElement) {
		CS2Moniker moniker = new CS2Moniker(csElement);
		moniker.appendElementCS(csElement);
		String string = moniker.toString();
//		System.out.println(csElement.eClass().getName() + " ==> " + string);
		assert !"".equals(string);
		return string;
	}
	
	/**
	 * The registry of Moniker Visitor Factories for supported EPackages.
	 */
	private static Map<EPackage, Factory> csFactoryMap = new HashMap<EPackage, Factory>();
	
	/**
	 * The template parameters defined by csScope and its ancestors. This is
	 * computed lazily by computeTemplateParameters.
	 */
	private List<TemplateParameterCS> csTemplateParameters = null;

	/**
	 * CS TemplateParameters that already appear in the result and do not need re-qualification.
	 */
	private List<TemplateParameterCS> csEmittedParameters = null;
	
	/**
	 * The Moniker Visitors created for each required EPackage. 
	 */
	private Map<EPackage, BaseCSVisitor<?, ?>> csVisitorMap = new HashMap<EPackage, BaseCSVisitor<?, ?>>();	
	
	public CS2Moniker(MonikeredElementCS target) {
		super(target);
	}
	
	public void appendElementCS(VisitableCS csVisitable) {
		assert csVisitable != null;
		int oldSize = length();
		EPackage ePackage = csVisitable.eClass().getEPackage();
		BaseCSVisitor<?, ?> monikerVisitor = getVisitor(ePackage);
		if (monikerVisitor != null) {
			csVisitable.accept(monikerVisitor);
		}
		assert length() > oldSize;
	}

	public void appendNameCS(NamedElementCS csNamedElement) {
		append(csNamedElement != null ? csNamedElement.getName() : null);
	}
	
	public void appendParametersCS(List<ParameterCS> parameters) {
		append(PARAMETER_PREFIX);
		String prefix = ""; //$NON-NLS-1$
		for (ParameterCS csParameter : parameters) {
			append(prefix);
			appendElementCS(csParameter.getOwnedType());
			switch (csParameter.getIteratorKind()) {
				case ACCUMULATOR: prefix = ACCUMULATOR_SEPARATOR; break;
				case ITERATOR: prefix = ITERATOR_SEPARATOR; break;
				default: prefix = PARAMETER_SEPARATOR; break;
			}
		}
		append(PARAMETER_SUFFIX);
	}

	public void appendParentCS(ElementCS csElement, String parentSeparator) {
		if (toString().length() >= MONIKER_OVERFLOW_LIMIT) {
			append(OVERFLOW_MARKER);
		}
		else {
			EObject parent = csElement != null ? csElement.eContainer() : null;
			if ((parent instanceof MonikeredElementCS) && ((MonikeredElementCS) parent).hasMoniker()) {
				append(((MonikeredElementCS) parent).getMoniker());	
			}
			else if (parent instanceof VisitableCS) {
				appendElementCS((VisitableCS) parent);	
			}
		}
		append(parentSeparator);
	}

	public void appendRoleCS(ElementCS object) {
		EStructuralFeature eFeature = object.eContainmentFeature();
		String roleName = roleNames.get(eFeature);
		if (roleName == null) {
			roleName = eFeature.getName();
		}
		append(roleName);
		if (eFeature.isMany()) {
			int index = ((List<?>)object.eContainer().eGet(object.eContainingFeature())).indexOf(object);
			append(index);
		}
	}
	
	public void appendTemplateBindingsCS(ParameterizedTypeRefCS typeRef) {
		TemplateBindingCS templateBinding = typeRef.getOwnedTemplateBinding();
		if (templateBinding != null) {
			append(TEMPLATE_BINDING_PREFIX);
			List<TemplateParameterSubstitutionCS> templateParameterSubstitutions = templateBinding.getOwnedParameterSubstitution();
			if (!templateParameterSubstitutions.isEmpty()) {
				String prefix = ""; //$NON-NLS-1$
				for (TemplateParameterSubstitutionCS templateParameterSubstitution : templateParameterSubstitutions) {
					append(prefix);
					appendElementCS(templateParameterSubstitution.getOwnedActualParameter());
					prefix = TEMPLATE_BINDING_SEPARATOR;
				}
			}
			append(TEMPLATE_BINDING_SUFFIX);
		}
	}

	public void appendTemplateParametersCS(TemplateableElementCS csTemplateableElement) {
		TemplateSignatureCS csTemplateSignature = csTemplateableElement.getOwnedTemplateSignature();
		if (csTemplateSignature != null) {
			List<TemplateParameterCS> csTemplateParameters = csTemplateSignature.getOwnedTemplateParameter();
			if (!csTemplateParameters.isEmpty()) {
				append(TEMPLATE_SIGNATURE_PREFIX);
				String prefix = ""; //$NON-NLS-1$
				for (TemplateParameterCS csTemplateParameter : csTemplateParameters) {
					append(prefix);
					emittedTemplateParameterCS(csTemplateParameter);
//					appendTemplateParameter((TemplateParameter) csTemplateParameter.getPivot());
					appendNameCS(csTemplateParameter);
					prefix = TEMPLATE_SIGNATURE_SEPARATOR;
				}
				append(TEMPLATE_SIGNATURE_SUFFIX);
			}
		}
	}
	
	private void computeTemplateParametersCS(EObject csElement) {
		if (csTemplateParameters != null) {
			return;
		}
		EObject parent = csElement != null ? csElement.eContainer() : null;
		if (parent != null) {
			computeTemplateParametersCS(parent);
		}
		else {
			csTemplateParameters = Collections.emptyList();
		}
		if (csElement instanceof TemplateableElementCS) {
			TemplateSignatureCS ownedTemplateSignature = ((TemplateableElementCS)csElement).getOwnedTemplateSignature();
			if (ownedTemplateSignature != null) {
				if (csTemplateParameters.isEmpty()) {
					csTemplateParameters = new ArrayList<TemplateParameterCS>();
				}
				csTemplateParameters.addAll(ownedTemplateSignature.getOwnedTemplateParameter());
			}
		}
	}

	public BaseCSVisitor<?, ?> getVisitor(EPackage ePackage) {
		BaseCSVisitor<?, ?> monikerVisitor = csVisitorMap.get(ePackage);
		if ((monikerVisitor == null) && !csVisitorMap.containsKey(ePackage)) {
			Factory factory = csFactoryMap.get(ePackage);
			if (factory != null) {
				monikerVisitor = factory.create(this);
				if (monikerVisitor == null) {
					logger.error("No Moniker Visitor created for " + ePackage.getName());
				}
			}
			else {
				logger.error("No Moniker Visitor Factory registered for " + ePackage.getName());
			}
			csVisitorMap.put(ePackage, monikerVisitor);
		}
		return monikerVisitor;
	}

	protected void emittedTemplateParameterCS(TemplateParameterCS csTemplateParameter) {
		if (csEmittedParameters == null) {
			csEmittedParameters = new ArrayList<TemplateParameterCS>();
		}
		csEmittedParameters.add(csTemplateParameter);
	}
	
	public TemplateParameterCS getTemplateParameterCS(String text) {
		for (TemplateParameterCS templateParameter : getTemplateParametersCS()) {
			if (templateParameter.getName().equals(text)) {
				return templateParameter;
			}
		}
		return null;
	}

	public List<TemplateParameterCS> getTemplateParametersCS() {
		if (csTemplateParameters == null) {
			computeTemplateParametersCS(target);
		}
		return csTemplateParameters;
	}
	
	public boolean hasEmittedCS(TemplateParameterCS csTemplateParameter) {
		if (csEmittedParameters != null) {
			if (csEmittedParameters.contains(csTemplateParameter)) {
				return true;
			}
		}
		Element pivot = csTemplateParameter.getPivot();
		if (pivot instanceof TemplateParameter) {
			if (hasEmitted((TemplateParameter) pivot)) {
				return true;
			}
		}
		return false;
	}

	public boolean isTemplateParameterCS(TemplateParameterCS object) {
		return getTemplateParametersCS().contains(object);
	}
	
//	public void popBindings(int oldSize) {
//		while (substitutions.size() > oldSize) {
//			substitutions.remove(substitutions.size()-1);
//		}	
//	}

//	public int pushBindings(ParameterizedTypeRefCS templateBindable) {
//		if (substitutions == null) {
//			substitutions = new ArrayList<TemplateParameterSubstitutionCS>();
//		}
//		int size = substitutions.size();
//		TemplateBindingCS ownedTemplateBinding = templateBindable.getOwnedTemplateBinding();
//		if (ownedTemplateBinding != null) {
//			substitutions.addAll(ownedTemplateBinding.getOwnedParameterSubstitution());
//		}
//		return size;
//	}
}