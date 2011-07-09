/**
 * <copyright>
 *
 * Copyright (c) 2011 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willlink - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: Bag.java,v 1.2 2011/01/24 20:47:51 ewillink Exp $
 */
package org.eclipse.ocl.examples.domain.evaluation;

import org.eclipse.ocl.examples.domain.elements.DomainVariableDeclaration;
import org.eclipse.ocl.examples.domain.values.Value;

public interface DomainEvaluationEnvironment
{
	void add(DomainVariableDeclaration variable, Value value);
	void replace(DomainVariableDeclaration variable, Value value);
}
