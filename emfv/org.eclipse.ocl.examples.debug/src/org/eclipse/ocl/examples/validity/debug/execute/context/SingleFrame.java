/*******************************************************************************
 * Copyright (c) 2008-2012 The University of York, Antonio García-Domínguez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 *     Antonio García-Domínguez - split into interface + implementation
 ******************************************************************************/
package org.eclipse.ocl.examples.validity.debug.execute.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.ocl.examples.domain.elements.DomainExpression;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.validity.debug.util.ASTUtil;
import org.eclipse.ocl.examples.validity.debug.util.StringUtil;

/**
 * An individual stack frame for the EOL frame stack.
 */
public class SingleFrame implements Frame {

	private HashMap<String, Variable> storage = new LinkedHashMap<String, Variable>();
	private FrameType type;
	private DomainExpression entryPoint;
	private String label;
	private DomainExpression currentStatement;

	public SingleFrame(FrameType type, DomainExpression entryPoint){
		this.type = type;
		this.entryPoint = entryPoint;
	}
	
	public SingleFrame(FrameType type, DomainExpression entryPoint, String label){
		this.type = type;
		this.entryPoint = entryPoint;
		this.label = label;
	}

	public void dispose() {
		for (Variable v : storage.values()) {
			ASTUtil.dispose(v);
		}
		this.entryPoint = null;
		//this.storage = null;
	}
	
	public void clear() {
		storage.clear();
		currentStatement = null;
	}
	
	public SingleFrame clone() {
		SingleFrame clone = new SingleFrame(type, entryPoint);
		clone.label = label;
		clone.currentStatement = currentStatement;
		for (Variable v : storage.values()) {
			clone.storage.put(v.getName(), ASTUtil.clone(v));
		}
		return clone;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public void put(String name, Object value) {
		put(ASTUtil.createReadOnlyVariable(name, value));
	}
	
	public void remove(String name) {
		storage.remove(name);
	}
	
	public void put(Variable variable){
		storage.put(variable.getName(), variable);
	}
	
	public void putAll(Map<String, Variable> variables) {
		storage.putAll(variables);
	}
	
	public Variable get(String key){
		return storage.get(key);
	}

	public Map<String, Variable> getAll() {
		return storage;
	}

	public boolean contains(String key){
		return storage.containsKey(key);
	}

	public FrameType getType() {
		return type;
	}

	public void setType(FrameType type) {
		this.type = type;
	}
	
	public DomainExpression getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(DomainExpression entryPoint) {
		this.entryPoint = entryPoint;
	}
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("------------"+ type +"-------------\r\n");
		Iterator<String> keyIterator = storage.keySet().iterator();
		while (keyIterator.hasNext()){
			Object key = keyIterator.next();
			buffer.append(key + "     " + StringUtil.toString(storage.get(key), "null") + "\r\n");
		}
		return buffer.toString();
	}

	public void setCurrentStatement(DomainExpression ast) {
		this.currentStatement = ast;
	}

	public DomainExpression getCurrentStatement() {
		return currentStatement;
	}
}
