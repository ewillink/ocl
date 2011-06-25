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
 * $Id: OclVoidAllInstancesOperation.java,v 1.2 2011/01/24 19:56:31 ewillink Exp $
 */
package org.eclipse.ocl.examples.library.oclvoid;

import org.eclipse.ocl.examples.domain.elements.DomainCallExp;
import org.eclipse.ocl.examples.domain.evaluation.DomainEvaluator;
import org.eclipse.ocl.examples.domain.evaluation.InvalidValueException;
import org.eclipse.ocl.examples.domain.library.AbstractUnaryOperation;
import org.eclipse.ocl.examples.domain.types.DomainCollectionType;
import org.eclipse.ocl.examples.domain.values.Value;
import org.eclipse.ocl.examples.domain.values.ValueFactory;

/**
 * OclVoidAllInstancesOperation realises the OclVoid::allInstances() library operation.
 * 
 */
public class OclVoidAllInstancesOperation extends AbstractUnaryOperation
{
	public static final OclVoidAllInstancesOperation INSTANCE = new OclVoidAllInstancesOperation();

	public Value evaluate(DomainEvaluator evaluator, DomainCallExp callExp, Value sourceVal) throws InvalidValueException {
		ValueFactory valueFactory = evaluator.getValueFactory();
		// OclVoid has a single instance: null
		return valueFactory.createSetValue((DomainCollectionType)callExp.getType(), valueFactory.getNull());
	}
}
