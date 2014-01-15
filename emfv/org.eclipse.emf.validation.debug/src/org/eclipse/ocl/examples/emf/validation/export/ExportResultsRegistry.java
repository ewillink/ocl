/**
 * <copyright>
 *
 * Copyright (c) 2014 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Obeo - initial API and implementation 
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.emf.validation.export;

import java.util.ArrayList;
import java.util.List;

/**
 * This will contain all ocl-export extension that have been parsed from the
 * extension point.
 */
public class ExportResultsRegistry {

	/** List of extensions created from the extension point contributions. */
	private static final List<ExportResultsDescriptor> EXTENSIONS = new ArrayList<ExportResultsDescriptor>();

	/**
	 * Utility classes don't need a default constructor.
	 */
	private ExportResultsRegistry() {
		// hides constructor
	}

	/**
	 * Adds an extension to the registry.
	 * 
	 * @param extension
	 *            The extension that is to be added to the registry.
	 */
	public static void addExtension(ExportResultsDescriptor extension) {
		EXTENSIONS.add(extension);
	}

	/**
	 * Removes all extensions from the registry. This will be called at plugin
	 * stopping.
	 */
	public static void clearRegistry() {
		EXTENSIONS.clear();
	}

	/**
	 * Returns a copy of the registered extensions list.
	 * 
	 * @return A copy of the registered extensions list.
	 */
	public static List<ExportResultsDescriptor> getRegisteredExtensions() {
		return new ArrayList<ExportResultsDescriptor>(EXTENSIONS);
	}

	/**
	 * Removes a phantom from the registry.
	 * 
	 * @param extensionClassName
	 *            Qualified class name of the extension point which corresponding
	 *            phantom is to be removed from the registry.
	 */
	public static void removeExtension(String extensionClassName) {
		for (ExportResultsDescriptor extension : getRegisteredExtensions()) {
			if (extension.getExtensionClassName().equals(extensionClassName)) {
				EXTENSIONS.remove(extension);
			}
		}
	}
}
