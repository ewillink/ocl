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
 * $Id: OrderedCollectionAtOperation.java,v 1.1.2.5 2010/12/26 15:20:28 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.collection;

import org.eclipse.ocl.examples.pivot.OperationCallExp;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.examples.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.examples.pivot.values.Value;

/**
 * OrderedCollectionAtOperation realises the OrderedCollection::at() library operation.
 * 
 * @since 3.1
 */
public class OrderedCollectionAtOperation extends AbstractOrderedCollectionBinaryOperation
{
	public static final OrderedCollectionAtOperation INSTANCE = new OrderedCollectionAtOperation();

	@Override
	protected Value evaluateCollection(OrderedCollectionValue sourceVal, Value argVal) {
		Integer atValue = argVal.asInteger();
		if (atValue == null) {
			return createInvalidValue(argVal, null, "at", null);
		}
		return sourceVal.at(atValue.intValue());
	}

	@Override
	public Value evaluate(EvaluationVisitor evaluationVisitor, Value sourceVal, OperationCallExp operationCall) {
		try {
			return super.evaluate(evaluationVisitor, sourceVal, operationCall);
		}
		catch (IndexOutOfBoundsException e) {
			return createInvalidValue(sourceVal, operationCall, "Bad Index", e);
		}
	}
}