/**
 * <copyright>
 *
 * Copyright (c) 2009 Eclipse Modeling Project and others.
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
 * $Id: StringEqualOperation.java,v 1.1.2.1 2010/01/31 22:23:45 ewillink Exp $
 */
package org.eclipse.ocl.library.features.string;

import org.eclipse.ocl.library.features.AbstractBinaryOperation;

/**
 * StringEqualOperation realises the String::=() library operation.
 * 
 * @since 3.0
 */
public class StringEqualOperation extends AbstractBinaryOperation
{
	public Boolean evaluate(Object left, Object right) {
		if (isString(left) && isString(right)) {
			return String.valueOf(left).equals(String.valueOf(right));
		}
		if (isInvalid(left) && isInvalid(right)) {
			return Boolean.TRUE;
		}
		if (isNull(left) && isNull(right)) {
			return Boolean.TRUE;
		}			
		return Boolean.FALSE;
	}
}