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
 * $Id: BooleanAndOperation.java,v 1.1.2.2 2010/10/05 17:29:59 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.logical;

import org.eclipse.ocl.examples.library.AbstractBinaryOperation;
import org.eclipse.ocl.examples.pivot.OperationCallExp;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationVisitor;

/**
 * AndOperation realises the and() library operation.
 * 
 * @since 3.1
 */
public class BooleanAndOperation extends AbstractBinaryOperation
{
	public static final BooleanAndOperation INSTANCE = new BooleanAndOperation();

	@Override
	public Object evaluate(EvaluationVisitor evaluationVisitor, Object sourceVal, OperationCallExp operationCall) {
		if (sourceVal == Boolean.FALSE) {
			return Boolean.FALSE;
		}
		Object argVal = evaluateArgument(evaluationVisitor, operationCall, 0);
		return evaluate(sourceVal, argVal);
	}

	public Object evaluate(Object left, Object right) {
		if (left == Boolean.FALSE) {
			return Boolean.FALSE;
		}
		if (right == Boolean.FALSE) {
			return Boolean.FALSE;
		}
		if ((left == Boolean.TRUE) && (right == Boolean.TRUE)) {
			return Boolean.TRUE;
		}
		return null;
	}
}