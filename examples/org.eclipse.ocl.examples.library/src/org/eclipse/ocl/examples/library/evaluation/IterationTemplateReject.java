/**
 * <copyright>
 *
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: IterationTemplateReject.java,v 1.1.2.3 2010/12/23 19:24:48 ewillink Exp $
 */

package org.eclipse.ocl.examples.library.evaluation;

import java.util.List;

import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.examples.pivot.values.CollectionValue;
import org.eclipse.ocl.examples.pivot.values.Value;

/**
 *
 */
public class IterationTemplateReject extends IterationTemplate {
	
	private IterationTemplateReject(EvaluationVisitor evaluationVisitor) {
		super(evaluationVisitor);
	}
	
	public static IterationTemplate getInstance(EvaluationVisitor evaluationVisitor) {
		return new IterationTemplateReject(evaluationVisitor);
	}
	
	@Override
    protected Value evaluateResult(List<Variable> iterators, String resultName, Value bodyVal) {
		EvaluationEnvironment env = getEvalEnvironment();	
		// should be exactly one iterator
		String iterName = iterators.get(0).getName();
		Value currObj = env.getValueOf(iterName);
		
		CollectionValue.Accumulator resultVal = (CollectionValue.Accumulator) env.getValueOf(resultName);
		if (bodyVal.isFalse())
			resultVal.add(currObj);
		return resultVal;
	}
}