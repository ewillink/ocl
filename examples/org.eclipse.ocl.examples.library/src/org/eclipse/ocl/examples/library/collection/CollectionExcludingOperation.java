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
 * $Id: CollectionExcludingOperation.java,v 1.1.2.3 2010/12/23 19:24:48 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.collection;

import org.eclipse.ocl.examples.pivot.values.CollectionValue;
import org.eclipse.ocl.examples.pivot.values.Value;

/**
 * CollectionExcludingOperation realises the Collection::excluding() library operation.
 * 
 * @since 3.1
 */
public class CollectionExcludingOperation extends AbstractCollectionBinaryOperation
{
	public static final CollectionExcludingOperation INSTANCE = new CollectionExcludingOperation();

	@Override
	protected Value evaluateCollection(CollectionValue sourceVal, Value argVal) {
		return sourceVal.excluding(argVal);
	}
}