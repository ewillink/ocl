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
 * $Id: OrderedCollectionAppendOperation.java,v 1.1.2.3 2010/12/23 19:24:48 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.collection;

import org.eclipse.ocl.examples.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.examples.pivot.values.Value;

/**
 * OrderedCollectionAppendOperation realises the OrderedCollection::append() library operation.
 * 
 * @since 3.1
 */
public class OrderedCollectionAppendOperation extends AbstractOrderedCollectionBinaryOperation
{
	public static final OrderedCollectionAppendOperation INSTANCE = new OrderedCollectionAppendOperation();

	@Override
	protected Value evaluateCollection(OrderedCollectionValue sourceVal, Value argVal) {
		return sourceVal.append(argVal);
	}
}