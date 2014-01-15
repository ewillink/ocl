/*******************************************************************************
 * Copyright (c) 2009 Eclipse Modeling Project and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Radek Dvorak - initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.validity.internal.debug.ui.launcher;

import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Display;

public class OCLQueryTabGroup extends AbstractLaunchConfigurationTabGroup
{
    private final ResourceSet myValidationRS;

    public OCLQueryTabGroup() {
        myValidationRS = new ResourceSetImpl();
	}
	
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
        ILaunchConfigurationTab[] tabs = new ILaunchConfigurationTab[] {
                new OCLQueryLauncherTab(myValidationRS),
//                new OCLQueryConfigurationTab(),
//                new JavaArgumentsTab(), 
//                new PluginsTab(), 
//                new ConfigurationTab(), 
//                new TracingTab(), 
//                new EnvironmentTab(), 
//                new RefreshTab(),
                new CommonTab()
		};
		
		setTabs(tabs);
	}
	
	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		final ILaunchConfiguration config = configuration;
		final ILaunchConfigurationTab[] tabs = getTabs();
		BusyIndicator.showWhile(Display.getCurrent(), new Runnable() {
			public void run() {
				for (int i = 0; i < tabs.length; i++) {
					tabs[i].initializeFrom(config);
				}
			}
		});
	}
	
//    protected final ITransformationMaker TRANSFORMATION_MAKER = new ITransformationMaker() {
//		public QvtTransformation makeTransformation(String name) throws MdaException {
//			QvtModule qvtModule = TransformationUtil.getQvtModule(EmfUtil.makeUri(name));
//            return new QvtInterpretedTransformation(qvtModule);
//		}
//    };
}
