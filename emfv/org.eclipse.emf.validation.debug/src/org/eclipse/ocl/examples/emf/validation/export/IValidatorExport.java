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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootNode;
import org.eclipse.jdt.annotation.NonNull;

/**
 * This interface defines the general contract of the ocl export mechanism.
 * <p>
 * Clients may also extends AbstractExport instead.
 * </p>
 */
public interface IValidatorExport {
	/**
	 * This will be called in order to export the ocl validation results.
	 * 
	 * @param validatedResource
	 *            The resource to validate
	 * @param rootNode
	 *            The Root Node to export
	 * @param savePath
	 *            the fullPath of the exportedFile
	 */
	void export(@NonNull IResource validatedResource, @NonNull RootNode rootNode, @NonNull IPath savePath);
}