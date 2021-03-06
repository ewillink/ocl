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
 * $Id: IterateExpScopeAdapter.java,v 1.3 2011/04/20 19:02:27 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.base.scoping.pivot;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.examples.pivot.IterateExp;
import org.eclipse.ocl.examples.pivot.OclExpression;
import org.eclipse.ocl.examples.pivot.PivotPackage;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.utilities.TypeManager;
import org.eclipse.ocl.examples.xtext.base.scope.EnvironmentView;
import org.eclipse.ocl.examples.xtext.base.scope.ScopeView;

public class IterateExpScopeAdapter extends AbstractPivotScopeAdapter<IterateExp>
{
	public IterateExpScopeAdapter(TypeManager typeManager, IterateExp pivotElement) {
		super(typeManager, pivotElement);
	}

	@Override
	public ScopeView computeLookup(EnvironmentView environmentView, ScopeView scopeView) {
		EStructuralFeature containmentFeature = scopeView.getContainmentFeature();
		if (containmentFeature == PivotPackage.Literals.LOOP_EXP__BODY) {
			OclExpression source = target.getSource();
			environmentView.addElementsOfScope(source.getType(), scopeView);
			environmentView.addElements(target.getIterators());
			environmentView.addNamedElement(target.getResult());
		}
		else if (containmentFeature == PivotPackage.Literals.ITERATE_EXP__RESULT) {
			OclExpression source = target.getSource();
			environmentView.addElements(target.getIterators());
			environmentView.addElementsOfScope(source.getType(), scopeView);
		}
		else if (containmentFeature == PivotPackage.Literals.LOOP_EXP__ITERATOR) {
			OclExpression source = target.getSource();
			environmentView.addElementsOfScope(source.getType(), scopeView);
			EObject child = scopeView.getChild();
			for (Variable iterator : target.getIterators()) {
				environmentView.addNamedElement(iterator);
				if (iterator == child) {
					break;
				}
			}
		}
		return scopeView.getOuterScope();
	}
}
