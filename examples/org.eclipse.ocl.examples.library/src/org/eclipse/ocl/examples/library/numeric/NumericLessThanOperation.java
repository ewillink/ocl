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
 * $Id: NumericLessThanOperation.java,v 1.1.2.3 2010/12/26 15:20:28 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.numeric;

import org.eclipse.ocl.examples.pivot.values.BooleanValue;
import org.eclipse.ocl.examples.pivot.values.IntegerValue;
import org.eclipse.ocl.examples.pivot.values.RealValue;
import org.eclipse.ocl.examples.pivot.values.Value;


/**
 * LessThanOperation realises the <() library operation.
 * 
 * @since 3.1
 */
public class NumericLessThanOperation extends AbstractNumericBinaryOperation
{
	public static final NumericLessThanOperation INSTANCE = new NumericLessThanOperation();

	@Override
	protected BooleanValue evaluateInteger(IntegerValue left, IntegerValue right) {
		return createBooleanValue(left.compareTo(right) < 0);
	}

	@Override
	protected BooleanValue evaluateReal(RealValue left, RealValue right) {
		return createBooleanValue(left.compareTo(right) < 0);
	}

	@Override
	protected BooleanValue evaluateUnlimited(Value left, Value right) {
		return createBooleanValue(!left.isUnlimited() && right.isUnlimited());
	}
}