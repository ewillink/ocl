/**
 * <copyright>
 *
 * Copyright (c) 2010,2011 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *
 * </copyright>
 *
 * $Id: BaseCSResource.java,v 1.4 2011/03/03 20:09:18 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.base.utilities;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.examples.pivot.utilities.TypeManager;
import org.eclipse.ocl.examples.xtext.base.cs2pivot.CS2Pivot;

public interface BaseCSResource extends Resource
{
	CS2Pivot createCS2Pivot(Map<? extends Resource, ? extends Resource> cs2pivotResourceMap, TypeManager typeManager);
	TypeManager createTypeManager();
	URI resolve(URI uri);
}
