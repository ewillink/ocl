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
package org.eclipse.ocl.examples.validity.internal.debug.ui.launcher;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class OCLQueryConfigurationTab extends AbstractLaunchConfigurationTab
{
//    private final ApplyTransformationData myData;
//    private final QvtTransformationConfigurationUI myUI;
//    private final ITransformationMaker myTransformationMaker;

    public OCLQueryConfigurationTab() {
/*        myTransformationMaker = transformationMaker;
        myData = new ApplyTransformationData();
        myUI = new QvtTransformationConfigurationUI(myData, new ISetMessage() {
            public void setErrorMessage(String message) {
                QvtTransformationConfigurationTab.this
                        .setErrorMessage(message);
            }

            public void setMessage(String message) {
                QvtTransformationConfigurationTab.this.setMessage(message);
            }
        });
        myUI.addPropertyChangeListener(new PropertyChangeListener() {
            public void changePerformed(QvtConfigurationProperty property) {
                updateLaunchConfigurationDialog();
            }
        }); */
    }
    
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(TransformationControls.GRID, false));
        
//        myUI.createControl(composite);
        
        setControl(composite);
        Dialog.applyDialogFont(composite);
    }

    @SuppressWarnings("unchecked")
    public void initializeFrom(ILaunchConfiguration configuration) {
        String fileName = ""; //$NON-NLS-1$
/*        try {
            fileName = configuration.getAttribute(IQvtLaunchConstants.MODULE, ""); //$NON-NLS-1$
        } catch (CoreException e) {
           QvtRuntimeUIPlugin.getDefault().getLog().log(MiscUtil.makeErrorStatus(e));
        }
        
        QvtTransformation qvtTransformation = null;
        try {
        	qvtTransformation = myTransformationMaker.makeTransformation(fileName);
		} catch (MdaException e) {
		}
        myData.setTransformation(qvtTransformation);
        
        Map<String, String> valueMap = Collections.<String, String>emptyMap();
        try {
            valueMap = configuration.getAttribute(IQvtLaunchConstants.CONFIGURATION_PROPERTIES, Collections.<String, String>emptyMap());
        } catch (CoreException e) {
            QvtRuntimeUIPlugin.getDefault().getLog().log(MiscUtil.makeErrorStatus(e));
        }
        myData.getConfiguration().clear();
        myData.getConfiguration().putAll(valueMap);
        
        myUI.loadValues(); */
    }

    public void performApply(ILaunchConfigurationWorkingCopy configuration) {
//        myUI.performApply();
//        Map<String, String> map = new HashMap<String, String>(myData.getConfiguration());
//        configuration.setAttribute(IQvtLaunchConstants.CONFIGURATION_PROPERTIES, map);
   }

    public String getName() {
        return "Configuration"; //Messages.QvtTransformationConfigurationTab_Name;
    }
    
    @Override
    public boolean isValid(ILaunchConfiguration launchConfig) {
//        myUI.validate();
//        return myUI.isValid();
    	return true;
    }
    
    @Override
    public Image getImage() {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_DEF_VIEW);
    }
    
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {

    }
}
