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
 * $Id: BaseScopeProvider.java,v 1.1 2010/05/03 05:24:44 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.base.scoping;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.ocl.examples.xtext.base.baseCST.BaseCSTPackage;
import org.eclipse.ocl.examples.xtext.base.baseCST.ElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.ImportCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.QualifiedTypeRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.QualifiedClassifierRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.QualifiedOperationRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.QualifiedPackageRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.QualifiedStructuralFeatureRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.SimpleClassifierRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.SimpleOperationRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.SimplePackageRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.SimpleStructuralFeatureRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TypedTypeRefCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.util.BaseCSTSwitch;
import org.eclipse.ocl.examples.xtext.base.scope.AbstractScopeAdapter;
import org.eclipse.ocl.examples.xtext.base.scope.ScopeAccessor;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;

/**
 * This class contains custom scoping description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#scoping
 * on how and when to use it 
 *
 */
public class BaseScopeProvider extends AbstractDeclarativeScopeProvider
{
	static {
		AbstractScopeAdapter.addSwitch(BaseCSTPackage.eINSTANCE, new BaseScopeSwitch());
	}
	
	public static class BaseScopeSwitch 
		extends BaseCSTSwitch<AbstractScopeAdapter<? extends EObject>>
		implements AbstractScopeAdapter.ISwitch
	{
		@Override
		public AbstractScopeAdapter<? extends EObject> caseImportCS(ImportCS eObject) {
			return new ImportScopeAdapter(eObject);
		}

		@Override
		public AbstractScopeAdapter<? extends EObject> caseQualifiedClassifierRefCS(QualifiedClassifierRefCS eObject) {
			return new QualifiedClassifierRefScopeAdapter(eObject);
		}

		@Override
		public AbstractScopeAdapter<? extends EObject> caseQualifiedOperationRefCS(QualifiedOperationRefCS eObject) {
			return new QualifiedOperationRefScopeAdapter(eObject);
		}

		@Override
		public AbstractScopeAdapter<? extends EObject> caseQualifiedPackageRefCS(QualifiedPackageRefCS eObject) {
			return new QualifiedPackageRefScopeAdapter(eObject);
		}

		@Override
		public AbstractScopeAdapter<? extends EObject> caseQualifiedStructuralFeatureRefCS(QualifiedStructuralFeatureRefCS eObject) {
			return new QualifiedStructuralFeatureRefScopeAdapter(eObject);
		}

		@Override
		public AbstractScopeAdapter<? extends EObject> caseQualifiedTypeRefCS(QualifiedTypeRefCS eObject) {
			return new QualifiedTypeRefScopeAdapter(eObject);
		}

		@Override
		public AbstractScopeAdapter<? extends EObject> caseSimpleClassifierRefCS(SimpleClassifierRefCS eObject) {
			return new EmptyScopeAdapter(eObject);
		}

		@Override
		public AbstractScopeAdapter<? extends EObject> caseSimpleOperationRefCS(SimpleOperationRefCS eObject) {
			return new EmptyScopeAdapter(eObject);
		}

		@Override
		public AbstractScopeAdapter<? extends EObject> caseSimplePackageRefCS(SimplePackageRefCS eObject) {
			return new EmptyScopeAdapter(eObject);
		}

		@Override
		public AbstractScopeAdapter<? extends EObject> caseSimpleStructuralFeatureRefCS(SimpleStructuralFeatureRefCS eObject) {
			return new EmptyScopeAdapter(eObject);
		}

		@Override
		public AbstractScopeAdapter<? extends EObject> caseTypedTypeRefCS(TypedTypeRefCS eObject) {
			return new TypedTypeRefScopeAdapter(eObject);
		}

		@Override
		public AbstractScopeAdapter<?> defaultCase(EObject eObject) {
			return new DefaultScopeAdapter((ElementCS) eObject);
		}

		public AbstractScopeAdapter<?> doInPackageSwitch(EObject eObject) {
			return doSwitch(eObject.eClass(), eObject);
		}
	}

	@Override
	public ScopeAccessor getScope(EObject context, EReference reference) {
		AbstractScopeAdapter<?> scopeAdapter = AbstractScopeAdapter.getScopeAdapter(context);
		if (scopeAdapter == null) {
			return null;
		}
		return scopeAdapter.getExclusiveScopeAccessor(reference);
	}
}