/**
 * <copyright>
 *
 * Copyright (c) 2009,2011 E.D.Willink and others.
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
 * $Id: StringLessThanEqualOperation.java,v 1.3 2011/02/21 08:37:47 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.string;

import org.eclipse.ocl.examples.domain.elements.DomainCallExp;
import org.eclipse.ocl.examples.domain.evaluation.DomainEvaluator;
import org.eclipse.ocl.examples.domain.evaluation.InvalidValueException;
import org.eclipse.ocl.examples.domain.library.AbstractBinaryOperation;
import org.eclipse.ocl.examples.domain.values.Value;
import org.eclipse.ocl.examples.domain.values.ValueFactory;

/**
 * StringLessThanEqualOperation realises the String::<=() library operation.
 * 
 */
public class StringLessThanEqualOperation extends AbstractBinaryOperation
{
	public static final StringLessThanEqualOperation INSTANCE = new StringLessThanEqualOperation();

	public Value evaluate(DomainEvaluator evaluator, DomainCallExp callExp, Value left, Value right) throws InvalidValueException {
		ValueFactory valueFactory = evaluator.getValueFactory();
		String leftString = left.asString();
		String rightString = right.asString();
		return valueFactory.booleanValueOf(leftString.compareTo(rightString) <= 0);
	}
}
