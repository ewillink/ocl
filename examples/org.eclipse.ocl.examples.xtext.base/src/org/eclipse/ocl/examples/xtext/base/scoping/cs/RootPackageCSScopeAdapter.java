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
 * $Id: RootPackageCSScopeAdapter.java,v 1.6 2011/05/11 19:51:16 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.base.scoping.cs;

import org.eclipse.emf.common.util.URI;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.Namespace;
import org.eclipse.ocl.examples.pivot.PivotPackage;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;
import org.eclipse.ocl.examples.pivot.utilities.TypeManager;
import org.eclipse.ocl.examples.xtext.base.baseCST.ImportCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.RootPackageCS;
import org.eclipse.ocl.examples.xtext.base.scope.AbstractRootCSScopeAdapter;
import org.eclipse.ocl.examples.xtext.base.scope.EnvironmentView;
import org.eclipse.ocl.examples.xtext.base.scope.ScopeView;

public class RootPackageCSScopeAdapter extends AbstractRootCSScopeAdapter<RootPackageCS, org.eclipse.ocl.examples.pivot.Package>
{
	public RootPackageCSScopeAdapter(TypeManager typeManager, RootPackageCS csElement) {
		super(typeManager, csElement, org.eclipse.ocl.examples.pivot.Package.class);
	}

	@Override
	public ScopeView computeLookup(EnvironmentView environmentView, ScopeView scopeView) {
		org.eclipse.ocl.examples.pivot.Package pivotPackage = getPivot();
		if (pivotPackage != null) {
			environmentView.addNamedElements(typeManager.getLocalPackages(pivotPackage));
			environmentView.addNamedElements(typeManager.getLocalClasses(pivotPackage));
		}
		if (environmentView.accepts(PivotPackage.Literals.NAMESPACE)) {
			for (ImportCS anImport : getTarget().getOwnedImport()) {
				Namespace namespace = anImport.getNamespace();
				if (!namespace.eIsProxy()) {
					String importName = anImport.getName();
					if (importName == null) {
						if (namespace instanceof org.eclipse.ocl.examples.pivot.Package) {
							environmentView.addNamedElements(typeManager.getLocalPackages((org.eclipse.ocl.examples.pivot.Package)namespace));
						}
					}
					else {
						environmentView.addElement(importName, namespace);
					}
				}
			}
			if ((pivotPackage != null) && !environmentView.hasFinalResult()) {
				URI baseURI = pivotPackage.eResource().getURI();
            	if (baseURI != null) {
                	if (PivotUtil.isPivotURI(baseURI)) {
                		baseURI = PivotUtil.getNonPivotURI(baseURI);
                	}
                	if (baseURI != null) {
                		String name = environmentView.getName();
						URI uri = URI.createURI(name).resolve(baseURI);
						try {
							Element importedElement = typeManager.loadResource(uri, null);				
							environmentView.addElement(name, importedElement);
						} catch (Exception e) {
							// if it doesn't load just treat it as unresolved
						}
                	}
            	}
			}
		}
		if (environmentView.accepts(PivotPackage.Literals.PRECEDENCE)) {
			if (pivotPackage != null) {
				environmentView.addNamedElements(typeManager.getPrecedences(pivotPackage));		// Overrides imports
			}
		}
		return null;
	}
}
