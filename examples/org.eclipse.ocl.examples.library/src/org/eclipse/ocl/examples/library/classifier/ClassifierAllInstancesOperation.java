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
 * $Id: ClassifierAllInstancesOperation.java,v 1.4 2011/04/25 09:48:56 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.classifier;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.ocl.examples.domain.elements.DomainCallExp;
import org.eclipse.ocl.examples.domain.evaluation.DomainEvaluator;
import org.eclipse.ocl.examples.domain.evaluation.InvalidValueException;
import org.eclipse.ocl.examples.domain.evaluation.DomainModelManager;
import org.eclipse.ocl.examples.domain.library.AbstractUnaryOperation;
import org.eclipse.ocl.examples.domain.types.DomainCollectionType;
import org.eclipse.ocl.examples.domain.values.TypeValue;
import org.eclipse.ocl.examples.domain.values.Value;
import org.eclipse.ocl.examples.domain.values.ValueFactory;

/**
 * ClassifierAllInstancesOperation realises the Classifier::allInstances() library operation.
 * 
 */
public class ClassifierAllInstancesOperation extends AbstractUnaryOperation
{
	public static final ClassifierAllInstancesOperation INSTANCE = new ClassifierAllInstancesOperation();

	public Value evaluate(DomainEvaluator evaluator, DomainCallExp callExp, Value sourceVal) throws InvalidValueException {
		ValueFactory valueFactory = evaluator.getValueFactory();
		TypeValue typeVal = sourceVal.asTypeValue();
		DomainModelManager modelManager = evaluator.getModelManager();
		Set<Value> results = new HashSet<Value>();
		Set<?> instances = modelManager.get(typeVal.getInstanceType());
		if (instances != null) {
			for (Object instance : instances) {
				results.add(valueFactory.valueOf(instance));	// FIXME Move to model manager
			}
		}
		return valueFactory.createSetValue((DomainCollectionType)callExp.getType(), results);
	}
}
