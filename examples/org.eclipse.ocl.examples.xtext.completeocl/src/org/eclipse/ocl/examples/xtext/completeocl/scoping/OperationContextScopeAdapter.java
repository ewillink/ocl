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
 * $Id: OperationContextScopeAdapter.java,v 1.1 2010/05/03 05:58:37 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.completeocl.scoping;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.examples.xtext.base.baseCST.ClassifierCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ClassifierRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.OperationCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.OperationRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.QualifiedClassifierRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.QualifiedOperationRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.SimpleClassifierRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.SimpleOperationRefCS;
import org.eclipse.ocl.examples.xtext.base.scope.AbstractScopeAdapter;
import org.eclipse.ocl.examples.xtext.base.scope.FilteredAccesses;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.CompleteOCLCSTPackage;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.OperationContextDeclCS;
import org.eclipse.ocl.examples.xtext.essentialocl.scoping.EssentialOCLScopeAdapter;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

public class OperationContextScopeAdapter extends EssentialOCLScopeAdapter<OperationContextDeclCS>
{
	public OperationContextScopeAdapter(OperationContextDeclCS csElement) {
		super(csElement);
	}

	@Override
	public void createContents(FilteredAccesses filteredAccesses, EStructuralFeature containmentFeature) {
		if (containmentFeature == null) {
		}
		else if (containmentFeature == CompleteOCLCSTPackage.Literals.OPERATION_CONTEXT_DECL_CS__OPERATION) {
		}
		else {
			OperationRefCS csOperationRef = getTarget().getOperation();
			while (csOperationRef instanceof QualifiedOperationRefCS) {
				csOperationRef = ((QualifiedOperationRefCS)csOperationRef).getOperation();
			}
			if (csOperationRef instanceof SimpleOperationRefCS) {
				SimpleOperationRefCS csSimpleOperationRef = (SimpleOperationRefCS)csOperationRef;
				OperationCS operation = csSimpleOperationRef.getOperation();
				filteredAccesses.addElementsOfScope(operation);
				filteredAccesses.addElement("result", operation);	// FIXME transient VariableCS		
				filteredAccesses.addElement("self", (ElementCS) operation.eContainer());
			}
		}
	}
}