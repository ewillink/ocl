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
 * $Id: ClassifierContextScopeAdapter.java,v 1.1 2010/05/03 05:58:37 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.completeocl.scoping;

import java.util.Collections;
import java.util.Set;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.examples.xtext.base.baseCST.ClassifierCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ClassifierRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.PackageRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.QualifiedClassifierRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.QualifiedPackageRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.SimpleClassifierRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.SimplePackageRefCS;
import org.eclipse.ocl.examples.xtext.base.scope.AbstractScopeAdapter;
import org.eclipse.ocl.examples.xtext.base.scope.FilteredAccesses;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ClassifierContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.CompleteOCLCSTPackage;
import org.eclipse.ocl.examples.xtext.essentialocl.scoping.EssentialOCLScopeAdapter;
import org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.OCLinEcoreClassifierCS;
import org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.OCLinEcoreDocumentCS;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

public class ClassifierContextScopeAdapter extends EssentialOCLScopeAdapter<ClassifierContextDeclCS>
{
	public ClassifierContextScopeAdapter(ClassifierContextDeclCS csElement) {
		super(csElement);
	}

	@Override
	public void createContents(FilteredAccesses filteredAccesses, EStructuralFeature containmentFeature) {
		if (containmentFeature == null) {
		}
		else if (containmentFeature == CompleteOCLCSTPackage.Literals.CLASSIFIER_CONTEXT_DECL_CS__CLASSIFIER) {
		}
		else {
			ClassifierRefCS csClassifierRef = getTarget().getClassifier();
			while (csClassifierRef instanceof QualifiedClassifierRefCS) {
				csClassifierRef = ((QualifiedClassifierRefCS)csClassifierRef).getClassifier();
			}
			if (csClassifierRef instanceof SimpleClassifierRefCS) {
				SimpleClassifierRefCS csSimpleClassifierRef = (SimpleClassifierRefCS)csClassifierRef;
				ClassifierCS classifier = csSimpleClassifierRef.getClassifier();
				filteredAccesses.addElementsOfScope(classifier);
				filteredAccesses.addElement("self", classifier);
			}
		}
	}
}