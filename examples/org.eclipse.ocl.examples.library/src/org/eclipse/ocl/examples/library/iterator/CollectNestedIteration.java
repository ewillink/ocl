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
 * $Id: CollectNestedIteration.java,v 1.1.2.5 2010/12/26 15:20:28 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.iterator;

import java.util.List;

import org.eclipse.ocl.examples.library.AbstractIteration;
import org.eclipse.ocl.examples.library.evaluation.IterationTemplate;
import org.eclipse.ocl.examples.library.evaluation.IterationTemplateCollectNested;
import org.eclipse.ocl.examples.pivot.OclExpression;
import org.eclipse.ocl.examples.pivot.OperationCallExp;
import org.eclipse.ocl.examples.pivot.StandardLibrary;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.examples.pivot.values.CollectionValue;
import org.eclipse.ocl.examples.pivot.values.Value;

/**
 * CollectNestedIteration realises the Collection::collectNested() library iteration.
 * 
 * @since 3.1
 */
public class CollectNestedIteration extends AbstractIteration
{
	public static final CollectNestedIteration INSTANCE = new CollectNestedIteration();

	public Value evaluate(EvaluationVisitor evaluationVisitor, Value sourceVal, OperationCallExp iteratorExp) {
		// get initial result value based on the source type
		StandardLibrary stdlib = evaluationVisitor.getStandardLibrary();
		Type sourceType = stdlib.getTypeOfType(iteratorExp.getSource().getType());
		boolean isOrdered = isOrdered(sourceType);
		Value initResultVal = createCollectionValue(isOrdered, false);
		List<Variable> iterators = getIterators(iteratorExp);
		OclExpression body = getBody(iteratorExp);		
		CollectionValue coll = (CollectionValue) sourceVal;
		// get an iteration template to evaluate the iterator
		IterationTemplate is = IterationTemplateCollectNested.getInstance(evaluationVisitor);
		// generate a name for the result variable and add it to the environment
		String resultName = generateName();
		evaluationVisitor.getEvaluationEnvironment().add(resultName, initResultVal);		
		try {
			// evaluate
			return is.evaluate(coll, iterators, body, resultName);
		} finally {
			// remove result name from environment
			evaluationVisitor.getEvaluationEnvironment().remove(resultName);
		}
	}
}