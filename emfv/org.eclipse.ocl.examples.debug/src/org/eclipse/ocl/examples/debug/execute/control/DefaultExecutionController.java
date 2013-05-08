/*******************************************************************************
 * Copyright (c) 2008 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 ******************************************************************************/
package org.eclipse.ocl.examples.debug.execute.control;

import org.eclipse.ocl.examples.debug.execute.context.IEolContext;
import org.eclipse.ocl.examples.domain.elements.DomainExpression;


public class DefaultExecutionController implements ExecutionController{

	public void control(DomainExpression ast, IEolContext context) {}

	public boolean isTerminated() {
		return false;
	}
	
	public void report(IEolContext context) {
		
	}

	public void dispose() {
		// do nothing
	}

	public void done(DomainExpression ast, IEolContext context) {
		// do nothing
	}
}
