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
 * $Id: AbstractCollectionUnaryOperation.java,v 1.1.2.4 2010/12/26 15:20:28 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.collection;

import org.eclipse.ocl.examples.library.AbstractUnaryOperation;
import org.eclipse.ocl.examples.pivot.values.CollectionValue;
import org.eclipse.ocl.examples.pivot.values.Value;

/**
 * AbstractCollectionUnaryOperation provides the standard null to Bag{}
 * conversion for all unary collection operations.
 * 
 * @since 3.1
 */
public abstract class AbstractCollectionUnaryOperation extends AbstractUnaryOperation
{
	public Value evaluate(Value argument) {
		CollectionValue collectionValue = argument.asCollectionValue();
		if (collectionValue != null) {
			return evaluateCollection(collectionValue);
		}
		else {
			return createInvalidValue(argument, null, "non-collection", null);
		}
	}
	
	protected abstract Value evaluateCollection(CollectionValue sourceVal);
}