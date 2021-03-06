/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: OCLExamplesPlugin.java,v 1.1 2010/03/11 10:16:55 ewillink Exp $
 */

package org.eclipse.ocl.examples.internal;

import org.eclipse.ui.plugin.AbstractUIPlugin;


public class OCLExamplesPlugin
	extends AbstractUIPlugin {

	// The shared instance.
	private static OCLExamplesPlugin plugin;
	
	public OCLExamplesPlugin() {
		super();
		plugin = this;
	}
	
	/**
	 * Returns the shared instance.
	 */
	public static OCLExamplesPlugin getDefault() {
		return plugin;
	}
}
