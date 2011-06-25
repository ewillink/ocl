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
 * $Id: NumericGreaterThanEqualOperation.java,v 1.2 2011/01/24 19:56:31 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.numeric;

import org.eclipse.ocl.examples.domain.evaluation.DomainEvaluator;
import org.eclipse.ocl.examples.domain.values.BooleanValue;
import org.eclipse.ocl.examples.domain.values.IntegerValue;
import org.eclipse.ocl.examples.domain.values.RealValue;
import org.eclipse.ocl.examples.domain.values.Value;
import org.eclipse.ocl.examples.domain.values.ValueFactory;


/**
 * GreaterThanEqualOperation realises the >=() library operation.
 * 
 */
public class NumericGreaterThanEqualOperation extends AbstractNumericBinaryOperation
{
	public static final NumericGreaterThanEqualOperation INSTANCE = new NumericGreaterThanEqualOperation();

	@Override
	protected BooleanValue evaluateInteger(DomainEvaluator evaluator, IntegerValue left, IntegerValue right) {
		ValueFactory valueFactory = evaluator.getValueFactory();
		return valueFactory.booleanValueOf(left.compareTo(right) >= 0);
	}

	@Override
	protected BooleanValue evaluateReal(DomainEvaluator evaluator, RealValue left, RealValue right) {
		ValueFactory valueFactory = evaluator.getValueFactory();
		return valueFactory.booleanValueOf(left.compareTo(right) >= 0);
	}

	@Override
	protected BooleanValue evaluateUnlimited(DomainEvaluator evaluator, Value left, Value right) {
		ValueFactory valueFactory = evaluator.getValueFactory();
		return valueFactory.booleanValueOf(left.isUnlimited());
	}
}
