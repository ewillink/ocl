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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ocl.examples.emf.validation.debug.plugin.ValidityPlugin;

/**
 * Describes an extension as contributed to the validity-exporter extension point.
 */
public class ExportResultsDescriptor {

	/** Configuration element of this descriptor. */
	private final IConfigurationElement element;

	public static final String VALIDITY_EXPORTER_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

	public static final String VALIDITY_EXPORTER_NAME_ATTRIBUTE = "name"; //$NON-NLS-1$

	public static final String VALIDITY_EXPORTER_EXTENSION_ATTRIBUTE = "extension"; //$NON-NLS-1$

	/**
	 * Qualified class name of the exporter extension. This will be used as an
	 * id to remove contributions.
	 */
	private final String extensionClassName;

	/**
	 * The name of the exporter extension.
	 */
	private final String extensionName;

	/**
	 * The extension attribute of the exporter.
	 */
	private final String extensionAttribute;

	/**
	 * We only need to create the instance once, this will keep reference to it.
	 */
	private IValidatorExport extension;

	/**
	 * Instantiates a descriptor with all information.
	 * 
	 * @param configuration
	 *            Configuration element from which to create this descriptor.
	 */
	public ExportResultsDescriptor(IConfigurationElement configuration) {
		element = configuration;
		extensionName = configuration.getAttribute(VALIDITY_EXPORTER_NAME_ATTRIBUTE);
		extensionAttribute = configuration
			.getAttribute(VALIDITY_EXPORTER_EXTENSION_ATTRIBUTE);
		extensionClassName = configuration
			.getAttribute(VALIDITY_EXPORTER_CLASS_ATTRIBUTE);
	}

	/**
	 * Returns this descriptor's "extension" class name.
	 * 
	 * @return This descriptor's "extension" class name.
	 */
	public String getExtensionClassName() {
		return extensionClassName;
	}

	/**
	 * Returns this descriptor's "extension" name.
	 * 
	 * @return This descriptor's "extension" name.
	 */
	public String getExtensionName() {
		return extensionName;
	}

	/**
	 * Returns this descriptor's "exporter" extension.
	 * 
	 * @return This descriptor's "exporter" extension.
	 */
	public String getExtensionAttribute() {
		return extensionAttribute;
	}

	/**
	 * Creates an instance of this descriptor's {@link IValidatorExport}.
	 * 
	 * @return A new instance of this descriptor's {@link IValidatorExport}.
	 */
	public IValidatorExport getExportExtension() {
		if (extension == null) {
			try {
				extension = (IValidatorExport) element
					.createExecutableExtension(VALIDITY_EXPORTER_CLASS_ATTRIBUTE);
			} catch (CoreException e) {
				ValidityPlugin.getPlugin().getLog().log(e.getStatus());
			}
		}
		return extension;
	}
}
