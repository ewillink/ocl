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
 * $Id: CollectionIncludingOperation.java,v 1.1.2.2 2010/10/05 17:29:59 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.collection;

import java.util.Collection;

import org.eclipse.ocl.examples.pivot.values.CollectionUtil;

/**
 * CollectionIncludingOperation realises the Collection::including() library operation.
 * 
 * @since 3.1
 */
public class CollectionIncludingOperation extends AbstractCollectionBinaryOperation
{
	public static final CollectionIncludingOperation INSTANCE = new CollectionIncludingOperation();

	@Override
	protected Object evaluateCollection(Collection<?> sourceVal, Object argVal) {
		@SuppressWarnings("unchecked")
		Collection<Object> sourceColl = (Collection<Object>)sourceVal;
		return CollectionUtil.including(sourceColl, argVal);
	}
}