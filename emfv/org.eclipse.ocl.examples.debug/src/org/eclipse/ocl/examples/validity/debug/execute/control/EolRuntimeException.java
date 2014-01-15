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
package org.eclipse.ocl.examples.validity.debug.execute.control;

import org.eclipse.ocl.examples.domain.elements.DomainExpression;
import org.eclipse.ocl.examples.validity.debug.execute.context.IEolContext;
import org.eclipse.ocl.examples.validity.debug.util.ASTUtil;


public class EolRuntimeException extends Exception{
	
	protected DomainExpression ast = null;
	protected String reason = "";
	protected IEolContext context = null;
	
	public EolRuntimeException(){
		
	}

	public EolRuntimeException(String reason){
		super(reason);
		this.reason = reason;
	}

	public EolRuntimeException(String reason, DomainExpression ast) {
		super(reason);
		this.ast = ast;
		this.reason = reason;
	}
	
	public DomainExpression getAst() {
		return ast;
	}

	
	public void setAst(DomainExpression ast) {
		this.ast = ast;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public int getLine(){
		if (getAst() != null) return ASTUtil.getLine(getAst());
		else return 0;
	}
	
	public int getColumn(){
		if (getAst() != null) return ASTUtil.getColumn(getAst());
		else return 0;
	}
	
	@Override
	public String getMessage() {
		String str = getReason().replace('(','[').replace(')',']');
		if (ast != null) {
			str += " (";
			if (ASTUtil.getFile(ast) != null) {
				str += ASTUtil.getFile(ast).getAbsolutePath() + "@" ;
			}
			else if (ASTUtil.getUri(ast) != null) {
				str += ASTUtil.getUri(ast) + "@";
			}
			str += getLine() + ":" + getColumn() + ")";
		}
		return str;
	}
	
	@Override
	public String toString(){
		return getMessage();
	}
	
	public static EolRuntimeException wrap(Throwable t) {
		if (t instanceof EolRuntimeException) return (EolRuntimeException) t;
		else return new EolInternalException(t);
	}
	
	public static void propagate(Throwable t) throws EolRuntimeException {
		throw wrap(t);
	}
}
