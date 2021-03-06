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
 * $Id: DataTypeCSScopeAdapter.java,v 1.3 2011/04/25 09:50:02 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.base.scoping.cs;

import org.eclipse.ocl.examples.pivot.DataType;
import org.eclipse.ocl.examples.pivot.utilities.TypeManager;
import org.eclipse.ocl.examples.xtext.base.baseCST.DataTypeCS;
import org.eclipse.ocl.examples.xtext.base.scope.EnvironmentView;
import org.eclipse.ocl.examples.xtext.base.scope.ScopeView;

public class DataTypeCSScopeAdapter extends BaseCSScopeAdapter<DataTypeCS, DataType>
{
	public DataTypeCSScopeAdapter(TypeManager typeManager, DataTypeCS csElement) {
		super(typeManager, csElement, DataType.class);
	}

	@Override
	public ScopeView computeLookup(EnvironmentView environmentView, ScopeView scopeView) {
		environmentView.addLibContents(typeManager.getOclAnyType(), scopeView);
		return scopeView.getOuterScope();
	}
}
