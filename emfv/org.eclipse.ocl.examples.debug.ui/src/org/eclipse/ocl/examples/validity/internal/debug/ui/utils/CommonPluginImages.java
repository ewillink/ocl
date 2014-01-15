/*******************************************************************************
 * Copyright (c) 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.internal.debug.ui.utils;

import java.net.URL;

import org.eclipse.ocl.examples.validity.internal.debug.ui.OCLDebugUIPlugin;



public class CommonPluginImages extends PluginImages {
    private CommonPluginImages() {
        super(BASE_URL);
        declareImages();
    }
    
    public static final String OCL_QUERY = "oclQuery"; //$NON-NLS-1$
    
/*    public static final String WARNING_CO = "warning-co"; //$NON-NLS-1$
    public static final String ERROR_CO = "error-co"; //$NON-NLS-1$
    
    public static final String REGISTRY = "registry"; //$NON-NLS-1$
    public static final String TRANSFORMATION = "pr-Model_to_model"; //$NON-NLS-1$
    
    public static final String METAMODELS = "metamodels"; //$NON-NLS-1$
    public static final String METAMODEL_FILE = "metamodelfile"; //$NON-NLS-1$    

    public static final String COLLAPSE_ALL = "collapseall"; //$NON-NLS-1$
    public static final String SHOW_INHERITED_FEATURES = "showinherf"; //$NON-NLS-1$
    public static final String SHOW_FULL_QUALIFIED_NAMES = "showfullq"; //$NON-NLS-1$
    public static final String OPEN_CLASSIFIER = "opentype"; //$NON-NLS-1$ */

    public static CommonPluginImages getInstance() {
        return ourInstance;
    }
    
    @Override
	protected void declareImages() {
        declareRegistryImage(OCL_QUERY, "icons/OCLModelFile.gif"); //$NON-NLS-1$
/*        declareRegistryImage(WARNING_CO, "icons/warning-co.gif"); //$NON-NLS-1$
        declareRegistryImage(ERROR_CO, "icons/error-co.gif"); //$NON-NLS-1$
        declareRegistryImage(REGISTRY, "icons/registry.gif"); //$NON-NLS-1$
        declareRegistryImage(TRANSFORMATION, "icons/pr-Model_to_model.gif"); //$NON-NLS-1$        
        declareRegistryImage(METAMODELS, "icons/metamodels.gif"); //$NON-NLS-1$        
        declareRegistryImage(METAMODEL_FILE, "icons/metamodelfile.gif"); //$NON-NLS-1$
        declareRegistryImage(COLLAPSE_ALL, "icons/collapseall.gif"); //$NON-NLS-1$
        declareRegistryImage(SHOW_INHERITED_FEATURES, "icons/showinherf.gif"); //$NON-NLS-1$
        declareRegistryImage(SHOW_FULL_QUALIFIED_NAMES, "icons/showfullq.gif"); //$NON-NLS-1$
        declareRegistryImage(OPEN_CLASSIFIER, "icons/opentype.gif"); //$NON-NLS-1$ */
    }
    
    private final static URL BASE_URL = OCLDebugUIPlugin.getDefault().getBundle().getEntry("/"); //$NON-NLS-1$
    
    private static final CommonPluginImages ourInstance = new CommonPluginImages();
}

