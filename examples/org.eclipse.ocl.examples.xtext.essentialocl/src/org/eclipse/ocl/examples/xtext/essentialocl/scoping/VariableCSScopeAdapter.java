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
 * $Id: VariableCSScopeAdapter.java,v 1.1.2.2 2010/12/11 10:45:57 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.essentialocl.scoping;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.utilities.PivotManager;
import org.eclipse.ocl.examples.xtext.base.baseCST.ElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TypeRefCS;
import org.eclipse.ocl.examples.xtext.base.scope.EnvironmentView;
import org.eclipse.ocl.examples.xtext.base.scope.ScopeView;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.EssentialOCLCSTPackage;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.VariableCS;

public class VariableCSScopeAdapter extends EssentialOCLCSScopeAdapter<VariableCS, Variable>
{
	public VariableCSScopeAdapter(PivotManager pivotManager, VariableCS csElement) {
		super(pivotManager, csElement, Variable.class);
	}

	@Override
	public ScopeView computeLookup(EnvironmentView environmentView, ScopeView scopeView) {
		EStructuralFeature containmentFeature = scopeView.getContainmentFeature();
		if (containmentFeature == EssentialOCLCSTPackage.Literals.VARIABLE_CS__OWNED_TYPE) {			
			return scopeView.getOuterScope();
		}
		else {
			TypeRefCS type = getTarget().getOwnedType();
			Type libType = getLibraryType(type);
			environmentView.addElementsOfScope(pivotManager, libType, scopeView);
			return scopeView.getOuterScope();
		}
	}

	@Override
	public Type getLibraryType(ElementCS csElement) {
		// TODO Auto-generated method stub
		return super.getLibraryType(csElement);
	}
}