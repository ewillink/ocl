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
 * $Id: Abstract2Moniker.java,v 1.1.2.2 2010/12/06 17:20:42 ewillink Exp $
 */
package org.eclipse.ocl.examples.pivot.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.ExpressionInOcl;
import org.eclipse.ocl.examples.pivot.MonikeredElement;
import org.eclipse.ocl.examples.pivot.NamedElement;
import org.eclipse.ocl.examples.pivot.Parameter;
import org.eclipse.ocl.examples.pivot.TemplateBinding;
import org.eclipse.ocl.examples.pivot.TemplateParameter;
import org.eclipse.ocl.examples.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.examples.pivot.TemplateSignature;
import org.eclipse.ocl.examples.pivot.TemplateableElement;

public abstract class Abstract2Moniker implements PivotConstants
{			
	private static final Logger logger = Logger.getLogger(Abstract2Moniker.class);

	/**
	 * The CS element for which a moniker is required.
	 */
	protected final EObject target;

	/**
	 * The working buffer for the result.
	 */
	private final StringBuffer s = new StringBuffer();
	
	/**
	 * A pivot 2 moniker conversion visitor, if needed.
	 */
	private Pivot2MonikerVisitor pivotVisitor = null;

	/**
	 * TemplateParameters that already appear in the result and do not need re-qualification.
	 */
	private List<TemplateParameter> emittedParameters = null;
	
	protected Abstract2Moniker(EObject target) {
		this.target = target;
	}

	public void append(char c) {
		s.append(c);
	}
	
	public void append(int i) {
		s.append(i);
	}
	
	public void append(String string) {
		s.append(string != null ? string : "null"); //$NON-NLS-1$
	}
	
	public void appendElement(Element element) {
		if (toString().length() >= MONIKER_OVERFLOW_LIMIT) {
			append(OVERFLOW_MARKER);
		}
		else if (element == null) {
			append(NULL_MARKER);	
		}
		else {
			if (pivotVisitor == null) {
				pivotVisitor = new Pivot2MonikerVisitor(this);
			}
			element.accept(pivotVisitor);
		}		
	}

	public void appendIndex(EObject eObject) {
		if (eObject != null) {
			EObject parent = eObject.eContainer();
			if (parent != null) {
				Object objects = parent.eGet(eObject.eContainingFeature());
				if (objects instanceof List<?>) {
					append(((List<?>)objects).indexOf(eObject));
					return;
				}
			}
		}
		append(0);
	}

	public void appendName(MonikeredElement monikeredElement) {
		if (monikeredElement instanceof org.eclipse.ocl.examples.pivot.Package) {
			String alias = AliasAdapter.getAlias(monikeredElement);
			if (alias != null) {
				append(alias);
				return;
			}
		}
		if (monikeredElement instanceof TemplateableElement) {
			List<TemplateBinding> templateBindings = ((TemplateableElement)monikeredElement).getTemplateBindings();
			if (!templateBindings.isEmpty()) {
				appendName(templateBindings.get(0).getSignature().getTemplate());
				return;
			}
		}
		if (monikeredElement instanceof NamedElement) {
			append(((NamedElement) monikeredElement).getName());
		}
		else if (monikeredElement == null) {
			logger.warn("null for PivotMoniker.appendName()");
			append("/null/");
		}
		else {
			logger.warn("Unsupported PivotMoniker.appendName() for " + monikeredElement.eClass().getName());
			append("/anon/");
		}
	}
	
	public void appendParameters(List<Parameter> parameters) {
		s.append(PARAMETER_PREFIX);
		String prefix = ""; //$NON-NLS-1$
		for (Parameter parameter : parameters) {
			s.append(prefix);
			appendElement(parameter.getType());
			switch (parameter.getIteratorKind()) {
				case ACCUMULATOR: prefix = ACCUMULATOR_SEPARATOR; break;
				case ITERATOR: prefix = ITERATOR_SEPARATOR; break;
				default: prefix = PARAMETER_SEPARATOR; break;
			}
		}
		s.append(PARAMETER_SUFFIX);
	}

	public void appendParent(Element element, String parentSeparator) {
		if (toString().length() >= MONIKER_OVERFLOW_LIMIT) {
			append(OVERFLOW_MARKER);
		}
		else if (element == null) {
			append("<<null-element>>");	
		}
		else {
			EObject parent = element.eContainer();
			if (parent instanceof MonikeredElement) {
				append(((MonikeredElement) parent).getMoniker());	
			}
			else if (parent instanceof Element) {
				appendElement((Element) parent);	
			}
			else if (element.eIsProxy()) {
				append("<<unresolved-proxy>>");	
			}
			else  {
				assert element instanceof org.eclipse.ocl.examples.pivot.Package || element instanceof ExpressionInOcl : element.eClass().getName();	
			}
		}
		append(parentSeparator);
	}

	public void appendRole(Element object) {
		EStructuralFeature eFeature = object.eContainmentFeature();
		if (eFeature != null) {
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
	}
	
	public void appendSignature(TemplateSignature signature, TemplateableElement object) {
		if (signature != null) {
			TemplateableElement template = signature.getTemplate();
//			assert template != object;			// Infinite recursion
//			int savedContext = pushBindings(object);
			try {
				appendElement(template);
			}
			finally {
//				popBindings(savedContext);
			}
		}
	}

	public void appendSignature(TemplateSignature signature, TemplateBinding object) {
		if (signature != null) {
			TemplateableElement template = signature.getTemplate();
//			int savedContext = pushBindings(object);
			try {
				appendElement(template);
			}
			finally {
//				popBindings(savedContext);
			}
		}
	}
	
	public void appendTemplateBindings(TemplateableElement typeRef) {
		List<TemplateBinding> templateBindings = typeRef.getTemplateBindings();
		if (!templateBindings.isEmpty()) {
			s.append(TEMPLATE_BINDING_PREFIX);
			String prefix = ""; //$NON-NLS-1$
			for (TemplateBinding templateBinding : templateBindings) {
				for (TemplateParameterSubstitution templateParameterSubstitution : templateBinding.getParameterSubstitutions()) {
					s.append(prefix);
					appendElement(templateParameterSubstitution.getActual());
					prefix = TEMPLATE_BINDING_SEPARATOR;
				}
			}
			s.append(TEMPLATE_BINDING_SUFFIX);
		}
	}

	public void appendTemplateParameters(TemplateableElement templateableElement) {
		TemplateSignature templateSignature = templateableElement.getOwnedTemplateSignature();
		if (templateSignature != null) {
			List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
			if (!templateParameters.isEmpty()) {
				s.append(TEMPLATE_SIGNATURE_PREFIX);
				String prefix = ""; //$NON-NLS-1$
				for (TemplateParameter templateParameter : templateParameters) {
					s.append(prefix);
					emittedTemplateParameter(templateParameter);
					appendName(templateParameter.getParameteredElement());
					prefix = TEMPLATE_SIGNATURE_SEPARATOR;
				}
				s.append(TEMPLATE_SIGNATURE_SUFFIX);
			}
		}
	}

	protected void emittedTemplateParameter(TemplateParameter templateParameter) {
		if (emittedParameters == null) {
			emittedParameters = new ArrayList<TemplateParameter>();
		}
		emittedParameters.add(templateParameter);
	}
	
	public boolean hasEmitted(TemplateParameter templateParameter) {
		return (emittedParameters != null) && emittedParameters.contains(templateParameter);
	}

/*	public boolean isTemplateParameter(TemplateParameter templateParameter) {
		for (EObject eObject = target; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof TemplateableElement) {
				TemplateSignature templateSignature = ((TemplateableElement)eObject).getOwnedTemplateSignature();
				if (templateSignature != null) {
					for (TemplateParameter targetTemplateParameter : templateSignature.getParameters()) {
						if (templateParameter == targetTemplateParameter) {
							return true;
						}
					}
				}
			}
		}
		return false;
	} */

	/**
	 * Return the length of the moniker so far.
	 */
	protected int length() {
		return s.length();
	}

	@Override
	public String toString() {
		return s.toString();
	}
}