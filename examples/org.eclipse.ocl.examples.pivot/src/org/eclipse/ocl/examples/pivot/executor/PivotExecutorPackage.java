/**
 * <copyright>
 *
 * Copyright (c) 2011 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.ocl.examples.pivot.executor;

import org.eclipse.ocl.examples.library.executor.ExecutorPackage;


public class PivotExecutorPackage extends ExecutorPackage
{
	protected final org.eclipse.ocl.examples.pivot.Package pivotPackage;

	public PivotExecutorPackage(PivotExecutorClass[] classes, org.eclipse.ocl.examples.pivot.Package pivotPackage) {
		super(pivotPackage.getName(), classes);
		this.pivotPackage = pivotPackage;		
	}
	
	public final org.eclipse.ocl.examples.pivot.Package getPivotPackage() {
		return pivotPackage;
	}
}