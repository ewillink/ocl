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
 * $Id: StringLessThanOperation.java,v 1.3 2011/02/21 08:37:47 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.string;

import org.eclipse.ocl.examples.library.AbstractBinaryOperation;
import org.eclipse.ocl.examples.pivot.InvalidValueException;
import org.eclipse.ocl.examples.pivot.values.Value;
import org.eclipse.ocl.examples.pivot.values.ValueFactory;

/**
 * StringLessThanOperation realises the String::<() library operation.
 * 
 * @since 3.1
 */
public class StringLessThanOperation extends AbstractBinaryOperation
{
	public static final StringLessThanOperation INSTANCE = new StringLessThanOperation();

	public Value evaluate(ValueFactory valueFactory, Value left, Value right) throws InvalidValueException {
		String leftString = left.asString();
		String rightString = right.asString();
		return valueFactory.booleanValueOf(leftString.compareTo(rightString) < 0);
	}
}
