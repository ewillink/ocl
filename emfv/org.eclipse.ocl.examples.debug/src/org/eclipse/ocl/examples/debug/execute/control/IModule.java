/*******************************************************************************
 * Copyright (c) 2008-2012 The University of York, Antonio García-Domínguez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Dimitrios Kolovos - initial API and implementation
 *     Antonio García-Domínguez - add getSourceURI
 ******************************************************************************/
package org.eclipse.ocl.examples.debug.execute.control;

import java.io.File;
import java.net.URI;
import java.util.List;

public interface IModule extends ModuleElement {
	
	boolean parse(File file) throws Exception;
	
	boolean parse(URI uri) throws Exception;
	
	boolean parse(String code) throws Exception;
	
	boolean parse(String code, File file) throws Exception;

	URI getSourceUri();

	void buildModel() throws Exception;

//	List<ParseProblem> getParseProblems();

	void reset();
	
}
