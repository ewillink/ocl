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
 * $Id: EmptyScopeAdapter.java,v 1.1.2.1 2010/12/11 10:45:32 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.base.scoping.pivot;

import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.utilities.PivotManager;

public class EmptyScopeAdapter extends AbstractPivotScopeAdapter<Element>
{
	public EmptyScopeAdapter(PivotManager pivotManager, Element pivotElement) {
		super(pivotManager, pivotElement);
	}
}