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
 * $Id: SequenceValue.java,v 1.1.2.2 2010/12/26 15:21:28 ewillink Exp $
 */
package org.eclipse.ocl.examples.pivot.values;


public interface SequenceValue extends OrderedCollectionValue, NonUniqueCollectionValue
{
    SequenceValue subSequence(int lower, int upper);
}