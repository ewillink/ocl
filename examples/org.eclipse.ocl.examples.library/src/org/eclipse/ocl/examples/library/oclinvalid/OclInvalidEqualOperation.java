/**
 * <copyright>
 *
 * Copyright (c) 2009,2010 E.D.Willink and others.
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
 * $Id: OclInvalidEqualOperation.java,v 1.1.2.1 2010/10/01 13:28:37 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.oclinvalid;

import org.eclipse.ocl.examples.library.AbstractBinaryOperation;

/**
 * OclInvalidEqualOperation realises the OclInvalid::=() library operation.
 * 
 * @since 3.1
 */
public class OclInvalidEqualOperation extends AbstractBinaryOperation
{
	public static final OclInvalidEqualOperation INSTANCE = new OclInvalidEqualOperation();

	public Boolean evaluate(Object left, Object right) {
		if (isInvalid(right)) {
			return true;
		}
		return false;
	}
}