/**
 * <copyright>
 *
 * Copyright (c) 2011 E.D.Willink and others.
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
 * $Id$
 */
package org.eclipse.ocl.examples.xtext.markup.ui.hover;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.examples.pivot.CallExp;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.Namespace;
import org.eclipse.ocl.examples.pivot.OclExpression;
import org.eclipse.ocl.examples.pivot.prettyprint.PrettyPrintExprVisitor;
import org.eclipse.ocl.examples.pivot.prettyprint.PrettyPrintNameVisitor;
import org.eclipse.ocl.examples.pivot.prettyprint.PrettyPrintTypeVisitor;
import org.eclipse.ocl.examples.pivot.util.Pivotable;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;
import org.eclipse.ocl.examples.xtext.base.baseCST.ElementCS;
import org.eclipse.ocl.examples.xtext.markup.Markup;
import org.eclipse.ocl.examples.xtext.markup.MarkupUtils;
import org.eclipse.xtext.ui.editor.hover.html.DefaultEObjectHoverProvider;

public class MarkupHoverProvider extends DefaultEObjectHoverProvider
{
	@Override
	protected String getDocumentation(EObject o) {
		String documentation = super.getDocumentation(o);
		Markup markup = MarkupUtils.decode(documentation);
		if (markup != null) {
			return MarkupUtils.toHTML(markup);
		}
		return null;
	}

	@Override
	protected String getFirstLine(EObject eObject) {
		// TODO Auto-generated method stub
		String firstLine = super.getFirstLine(eObject);
//		return firstLine;
		Element pivotElement = null;
		if (eObject instanceof Pivotable) {
			pivotElement = PivotUtil.getPivot(Element.class, (Pivotable)eObject);
		}
		else if (eObject instanceof Element) {
			pivotElement = (Element)eObject;
		}
		if (pivotElement != null) {
			Namespace namespace = PrettyPrintExprVisitor.getNamespace(pivotElement);
			String description;
			if (pivotElement instanceof CallExp) {
				description = PrettyPrintNameVisitor.prettyPrint(PivotUtil.getReferredFeature((CallExp)pivotElement), namespace);
			}
			else if (pivotElement instanceof OclExpression) {
				description = PrettyPrintTypeVisitor.prettyPrint(((OclExpression)pivotElement).getType(), namespace);
			}
			else {
				description = PrettyPrintExprVisitor.prettyPrint(pivotElement, namespace);
			}
			return firstLine + "\n<br>" + pivotElement.eClass().getName() + " <b>" + description + "</b>";
		}
		else {
			return firstLine + "\n<br>" + eObject.eClass().getName();		// FIXME do better					
		}
	}

	@Override
	protected boolean hasHover(EObject o) {
		if (o instanceof Element) {
			return true;
		}
		if (o instanceof ElementCS) {
			return true;
		}
		return super.hasHover(o);
	}
}