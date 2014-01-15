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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;

/**
 * 
 * This listener will allow us to be aware of contribution changes against the
 * exporter extension point.
 */
public class ExportResultsListener
		implements IRegistryEventListener {

	/** Name of the extension point to parse for extensions. */
	public static final String VALIDITY_EXPORTER_EXTENSION_POINT = "org.eclipse.ocl.examples.emf.validation.debug.validity_exporter"; //$NON-NLS-1$

	/** Name of the extension point's "EXPORTEXTENSION" tag. */
	private static final String VALIDITY_EXPORTER_TAG_EXTENSION = "exporter"; //$NON-NLS-1$

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtension[])
	 */
	public void added(IExtension[] extensions) {
		for (IExtension extension : extensions) {
			parseExtension(extension);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.IRegistryEventListener#added(org.eclipse.core.runtime.IExtensionPoint[])
	 */
	public void added(IExtensionPoint[] extensionPoints) {
		// no need to listen to this event
	}

	/**
	 * Though this listener reacts to the extension point changes, there could
	 * have been contributions before it's been registered. This will parse
	 * these initial contributions.
	 */
	public void parseInitialContributions() {
		final IExtensionRegistry registry = Platform.getExtensionRegistry();

		for (IExtension extension : registry.getExtensionPoint(
			VALIDITY_EXPORTER_EXTENSION_POINT).getExtensions()) {
			parseExtension(extension);
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtension[])
	 */
	public void removed(IExtension[] extensions) {
		for (IExtension extension : extensions) {
			final IConfigurationElement[] configElements = extension
				.getConfigurationElements();
			for (IConfigurationElement elem : configElements) {
				if (VALIDITY_EXPORTER_TAG_EXTENSION.equals(elem.getName())) {
					final String extensionClassName = elem
						.getAttribute(ExportResultsDescriptor.VALIDITY_EXPORTER_CLASS_ATTRIBUTE);
					ExportResultsRegistry.removeExtension(extensionClassName);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.core.runtime.IRegistryEventListener#removed(org.eclipse.core.runtime.IExtensionPoint[])
	 */
	public void removed(IExtensionPoint[] extensionPoints) {
		// no need to listen to this event
	}

	/**
	 * Parses a single extension contribution.
	 * 
	 * @param extension
	 *            Parses the given extension and adds its contribution to the
	 *            registry.
	 */
	private void parseExtension(IExtension extension) {
		final IConfigurationElement[] configElements = extension
			.getConfigurationElements();
		for (IConfigurationElement elem : configElements) {
			if (VALIDITY_EXPORTER_TAG_EXTENSION.equals(elem.getName())) {
				ExportResultsRegistry.addExtension(new ExportResultsDescriptor(
					elem));
			}
		}
	}
}
