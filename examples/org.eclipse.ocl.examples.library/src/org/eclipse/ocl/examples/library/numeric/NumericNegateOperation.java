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
 * $Id: NumericNegateOperation.java,v 1.1.2.1 2010/10/01 13:28:36 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.numeric;

import java.math.BigDecimal;
import java.math.BigInteger;


/**
 * NegateOperation realises the unary -() library operation.
 * 
 * @since 3.1
 */
public class NumericNegateOperation extends AbstractNumericUnaryOperation
{
	public static final NumericNegateOperation INSTANCE = new NumericNegateOperation();

	@Override
	protected Object evaluateInteger(BigInteger left) {
		return left.negate();
	}
	
	@Override
	protected Object evaluateReal(BigDecimal left) {
		return left.negate();
	}
}