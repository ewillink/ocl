/**
 * <copyright>
 *
 * Copyright (c) 2010 E.D.Willink and others.
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
 * $Id: NumericValue.java,v 1.1.2.3 2011/01/08 15:35:07 ewillink Exp $
 */
package org.eclipse.ocl.examples.pivot.values;

import java.math.BigDecimal;


public interface NumericValue extends Value, Comparable<NumericValue>
{
	NumericValue abs();
	BigDecimal bigDecimalValue();
	NumericValue negate();
	double doubleValue();
	int signum();
	IntegerValue toIntegerValue();
}