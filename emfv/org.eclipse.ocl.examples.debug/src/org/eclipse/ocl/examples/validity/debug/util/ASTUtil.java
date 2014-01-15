/*******************************************************************************
 * Copyright (c) 2008-2012 The University of York, Antonio García-Domínguez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 *     Antonio García-Domínguez - add findIFile(AST) and openEditorAt(AST)
 ******************************************************************************/
package org.eclipse.ocl.examples.validity.debug.util;

import java.io.File;
import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.ocl.examples.domain.elements.DomainExpression;
import org.eclipse.ocl.examples.domain.elements.DomainType;
import org.eclipse.ocl.examples.pivot.Variable;


public class ASTUtil {

	public static final int FOR = 0;
	public static final int WHILE = 1;
	public static final int BLOCK = 2;
	public static final int HELPERMETHOD = 3;

	private ASTUtil() {}

	public static int getLine(DomainExpression astNode) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static int getColumn(DomainExpression astNode) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static File getFile(DomainExpression ast) {
		// TODO Auto-generated method stub
		return null;
	}

	public static URI getUri(DomainExpression ast) {
		// TODO Auto-generated method stub
		return null;
	}

	public static DomainExpression getParent(DomainExpression ast) {
		// TODO Auto-generated method stub
		return null;
	}

	public static int getChildCount(DomainExpression parent) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static DomainExpression getFirstChild(DomainExpression parent) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Object getValue(Variable v) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Variable createReadOnlyVariable(String name, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Variable clone(Variable v) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void dispose(Variable v) {
		// TODO Auto-generated method stub
		
	}

	public static int getType(DomainExpression ast) {
		// TODO Auto-generated method stub
		return 0;
	}

}
