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
 * $Id: CollectionIncludingOperation.java,v 1.3 2011/02/21 08:37:46 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.collection;

import org.eclipse.ocl.examples.domain.evaluation.DomainEvaluator;
import org.eclipse.ocl.examples.domain.evaluation.InvalidValueException;
import org.eclipse.ocl.examples.domain.library.AbstractBinaryOperation;
import org.eclipse.ocl.examples.domain.types.DomainType;
import org.eclipse.ocl.examples.domain.values.CollectionValue;
import org.eclipse.ocl.examples.domain.values.Value;

/**
 * CollectionIncludingOperation realises the Collection::including() library operation.
 * 
 */
public class CollectionIncludingOperation extends AbstractBinaryOperation
{
	public static final CollectionIncludingOperation INSTANCE = new CollectionIncludingOperation();

	public Value evaluate(DomainEvaluator evaluator, DomainType returnType, Value left, Value right) throws InvalidValueException {
		CollectionValue leftCollectionValue = left.asCollectionValue();
		Value rightValue = right.asValidValue();
		return leftCollectionValue.including(rightValue);
	}
}
