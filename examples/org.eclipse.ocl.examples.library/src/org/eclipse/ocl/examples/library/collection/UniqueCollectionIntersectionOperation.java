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
 * $Id: UniqueCollectionIntersectionOperation.java,v 1.3 2011/02/21 08:37:46 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.collection;

import org.eclipse.ocl.examples.domain.elements.DomainCallExp;
import org.eclipse.ocl.examples.domain.evaluation.DomainEvaluator;
import org.eclipse.ocl.examples.domain.evaluation.InvalidValueException;
import org.eclipse.ocl.examples.domain.library.AbstractBinaryOperation;
import org.eclipse.ocl.examples.domain.values.CollectionValue;
import org.eclipse.ocl.examples.domain.values.Value;

/**
 * UniqueCollectionIntersectionOperation realises the UniqueCollection::intersection() library operation.
 * 
 */
public class UniqueCollectionIntersectionOperation extends AbstractBinaryOperation
{
	public static final UniqueCollectionIntersectionOperation INSTANCE = new UniqueCollectionIntersectionOperation();

	public Value evaluate(DomainEvaluator evaluator, DomainCallExp callExp, Value left, Value right) throws InvalidValueException {
		CollectionValue leftCollectionValue = left.asCollectionValue();
		CollectionValue rightCollectionValue = right.asCollectionValue();
		return leftCollectionValue.intersection(rightCollectionValue);
	}
}
