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
 * $Id: AbstractOperation.java,v 1.3 2011/02/21 08:37:47 ewillink Exp $
 */
package org.eclipse.ocl.examples.library;

import java.util.List;

import org.eclipse.ocl.examples.pivot.CallExp;
import org.eclipse.ocl.examples.pivot.InvalidValueException;
import org.eclipse.ocl.examples.pivot.OclExpression;
import org.eclipse.ocl.examples.pivot.OperationCallExp;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.examples.pivot.values.Value;

/**
 * @since 3.1
 */
public abstract class AbstractOperation extends AbstractFeature implements LibraryOperation
{
	public Value evaluate(EvaluationVisitor evaluationVisitor, Value sourceValue, CallExp callExp) {
		try {
			return evaluate(evaluationVisitor, sourceValue, (OperationCallExp) callExp);
		} catch (InvalidValueException e) {
			return evaluationVisitor.throwInvalidEvaluation(e);
		}
	}
	
	protected Value evaluateArgument(EvaluationVisitor evaluationVisitor,
			OperationCallExp operationCall, int i) {
		List<OclExpression> args = operationCall.getArguments();
		if ((i < 0) || (args.size() <= i)) {
			return null;
		}
		return args.get(i).accept(evaluationVisitor);
	}

	protected int getNumArguments(OperationCallExp operationCall) {
		List<?> args = operationCall.getArguments();
		return args.size();
	}
}
