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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.emf.common.ui.CommonUIPlugin;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.validity.internal.debug.ui.OCLDebugUIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

public class OCLQueryLauncherTab extends AbstractLaunchTab
{    
//    private final ITransformationMaker myTransformationMaker; 
	private final ResourceSet myValidationRS;
    private Text myOCLFile;
    private ExpressionInOCL myQuery;
//    private OptionalFileGroup myTraceFile;
//    private boolean myTraceNameNonChanged;
//    private TransformationSignatureLaunchControl myTransfSignatureControl;
//    private final List<IUriGroup.IModifyListener> myUriListeners;
    
    private final ModifyListener oclFileModifyListener = new ModifyListener() {
        public void modifyText(ModifyEvent e) {
            validateOCLFile();
//            myTransfSignatureControl.setTransformation(myQuery, myUriListeners);
//            myTraceNameNonChanged = myTraceFile.getText().equals(getTraceFileName());
            updateLaunchConfigurationDialog();            
        }
    };

    public OCLQueryLauncherTab(ResourceSet validationRS) {
//		myTransformationMaker = transformationMaker;
		myValidationRS = validationRS;

/*        myUriListeners = new ArrayList<IUriGroup.IModifyListener>(1);
        myUriListeners.add(new IUriGroup.IModifyListener() {
			public void modified() {
				initTraceFileText();
				updateLaunchConfigurationDialog();
			}
		}); */
	}
	
	@Override
	public void createControl(Composite parent) {
        super.createControl(parent);
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, MDAConstants.QVTO_TRANSFORMATION_CONTEXTID);
    }
	
	@Override
	public void dispose() {
		super.dispose();
		for (Resource res : myValidationRS.getResources()) {
			res.unload();
		}
	}
    
	@Override
	protected void createTransformationSection(Composite parent) {
        TransformationControls.createLabel(parent, "Complete OCL module:", TransformationControls.GRID);
        
        myOCLFile = TransformationControls.createText(parent, 1);
        myOCLFile.addModifyListener(oclFileModifyListener);
        
        createBrowseFileSystemButton(parent);
        createBrowseWorkspaceButton(parent);
/*        Button button = TransformationControls.createButton(parent, "Browse...");
        button.addSelectionListener(new SelectionAdapter(){
            @Override
			public void widgetSelected(SelectionEvent e) {
            	UniSelectTransformationControl.IResourceFilter resourceFilter = new UniSelectTransformationControl.IResourceFilter() {
                    public boolean accept(IResource resource) {
                        return resource instanceof IFile && "ocl".equalsIgnoreCase(resource.getFileExtension());
                    }
            	};
            	BrowseInterpretedTransformationDialog.ISelectionListener selectionListener = new BrowseInterpretedTransformationDialog.ISelectionListener() {
					public IStatus selectionChanged(URI selectedUri) {
						String transfName = ""; //$NON-NLS-1$
				        try {
				            if (selectedUri == null) {
				            	return TransformationControls.makeStatus(IStatus.ERROR, "No query");
				            }
				            QvtTransformation transformation = new QvtInterpretedTransformation(TransformationUtil.getQvtModule(selectedUri));
				            
				            List<TransformationParameter> parameters = transformation.getParameters();
				            if (parameters.isEmpty()) {
				            	return TransformationControls.makeStatus(IStatus.ERROR, "Empty query");
				            }
				            transfName = transformation.getModuleName();
				        }
				        catch (Exception e) {
				        	return TransformationControls.makeStatus(IStatus.ERROR, e.getMessage());
				        }
				        return TransformationControls.makeStatus(IStatus.OK, NLS.bind(Messages.QvtLauncherTab_TransformationSelected, transfName));
					}
            	};
            	EditingDomain editingDomain = new AdapterFactoryEditingDomain((AdapterFactory)null, (CommandStack)null, myValidationRS);
            	ExtendedLoadResourceDialog dialog = new ExtendedLoadResourceDialog(getShell(), editingDomain);
            	dialog.setMultipleSelection(false);
            	dialog.open();
//            	BrowseInterpretedTransformationDialog dialog = new BrowseInterpretedTransformationDialog(getShell(), resourceFilter,
//                		new QvtCompiledTransformationLabelProvider(), QvtTransformationRegistry.getInstance(), 
//                		myOCLFile.getText(), selectionListener);
                dialog.create();
                PlatformUI.getWorkbench().getHelpSystem().setHelp(dialog.getShell(), MDAConstants.QVTO_TRANSFORMATION_CONTEXTID);
                if (dialog.open() == Window.OK) {
                    URI selectedUri = dialog.getSelectedUri();
                    if (selectedUri != null){
                    	myOCLFile.setText(selectedUri.toString());
                    }
                }
            } 
        }); */

/*        myTraceFile = new OptionalFileGroup(parent, Messages.QvtLauncherTab_TraceFile);
        myTraceFile.addModifyListener(new OptionalFileGroup.IModifyListener() {
            public void modified() {
            	myTraceNameNonChanged = myTraceFile.getText().equals(getTraceFileName());
                updateLaunchConfigurationDialog();
            }});

        TransformationControls.createLabel(parent, Messages.QvtLauncherTab_ParametersLabel, TransformationControls.GRID);
        myTransfSignatureControl = new TransformationSignatureLaunchControl(parent, SWT.NONE|SWT.BORDER, myValidationRS);
*/
	}

	protected void createBrowseFileSystemButton(Composite parent) {
		Button browseFileSystemButton = new Button(parent, SWT.PUSH);
	    browseFileSystemButton.setText(CommonUIPlugin.INSTANCE.getString("_UI_BrowseFileSystem_label"));
	    browseFileSystemButton.addSelectionListener(new SelectionAdapter() {
	    	@Override public void widgetSelected(SelectionEvent ev) {
				FileDialog fileDialog = new FileDialog(getShell(), SWT.NONE);
//				fileDialog.setFilterPath(fileHandle.getAbsoluteName());
				if (fileDialog.open() == null)
					return;
				String filterPath = fileDialog.getFilterPath();
				String fileName = fileDialog.getFileName();
				if (fileName == null)
					return;
				URI ecoreURI = URI.createFileURI(filterPath + File.separator + fileName);
				setResourceURI(ecoreURI);
			 }
		});
//		ModelRegistryHelper.setHelp(browseFileSystemButton, ModelRegistryHelpIds.RegistrationDialog.BROWSE_FILE_SYSTEM);
	}

	protected void createBrowseWorkspaceButton(Composite parent) {
		Button browseWorkspaceButton = new Button(parent, SWT.PUSH);
	    browseWorkspaceButton.setText(CommonUIPlugin.INSTANCE.getString("_UI_BrowseWorkspace_label"));
	    browseWorkspaceButton.addSelectionListener(new SelectionAdapter() {
	    	@Override public void widgetSelected(SelectionEvent ev) {
	    		String fileHandle = null;
				IFile[] files = WorkspaceResourceDialog.openFileSelection(getShell(), null, null, false, new Object[] { fileHandle }, null);
			   	IFile file = files.length != 0 ? files[0] : null;
			   	if (file == null)
			   		return;
			   	String filePath = file.getFullPath().toString();
				URI ecoreURI = URI.createPlatformResourceURI(filePath, true);
				setResourceURI(ecoreURI);
			}
		});
//		ModelRegistryHelper.setHelp(browseWorkspaceButton, ModelRegistryHelpIds.RegistrationDialog.BROWSE_WORKSPACE);
	}

/*    private void initTraceFileText() {
    	if (myTraceNameNonChanged || myTraceFile.getText().length() == 0) {
	        String traceFileName = getTraceFileName();
	        myTraceFile.setText(traceFileName);
	        myTraceFile.setUseFileFlag(traceFileName != null);
	        if (traceFileName != null) {
	            IPath path = Path.fromOSString(traceFileName);
	        	myTraceFile.update(path.lastSegment().replaceAll(MDAConstants.QVTO_TRACEFILE_EXTENSION_WITH_DOT, ""), //$NON-NLS-1$
	        			MDAConstants.QVTO_TRACEFILE_EXTENSION);
	        }
    	}
    } */
    
/*    private String getTraceFileName() {
        URI targetUri = URI.createURI(myTransfSignatureControl.getTraceName());
        return QvtLaunchUtil.getTraceFileName(targetUri);
    } */
	
    @Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
        IFile file = getFileContext();
        if(file == null) {
            return;
        }
        
        // FIXME - strange error condition processing
/*        try {
        	URI uri = URIUtils.getResourceURI(file);
        	UnitProxy unit = UnitResolverFactory.Registry.INSTANCE.getFactory(file).findUnit(uri);
        	if(unit == null) {
        		return;
        	}
            CompiledUnit compiledUnit = QvtEngine.getInstance(file).compileUnit(unit, null);
            if(compiledUnit != null && compiledUnit.getModules().size() == 1) {
	            Module module = compiledUnit.getModules().get(0);
	            ImperativeOperation mainOperation = QvtOperationalParserUtil.getMainOperation(module);
				if(mainOperation != null) {
	                initializeName(configuration, compiledUnit.getName());
	                URI transfUri = URI.createPlatformResourceURI(file.getFullPath().toString(), false);
	                configuration.setAttribute(IQvtLaunchConstants.MODULE, transfUri.toString());
	            }
            }
        }
        catch (MdaException e) {
            Logger.getLogger().log(Logger.INFO, e.getMessage(), e);
        } */
    }
    
    private void initializeName(ILaunchConfigurationWorkingCopy config, String name) {
        if (name == null) {
            name = ""; //$NON-NLS-1$
        }

        if (name.length() > 0) {
            name = getLaunchConfigurationDialog().generateName(name);
            config.rename(name);
        }
    }
	
	public void initializeFrom(ILaunchConfiguration configuration) {
		try {
			myOCLFile.setText(configuration.getAttribute(IOCLLaunchConstants.OCL_FILE, "")); //$NON-NLS-1$
		} catch (CoreException e) {
			myOCLFile.setText(""); //$NON-NLS-1$
		}
		
/*		try {
//			myTransfSignatureControl.initializeFrom(configuration);
		} catch (CoreException e) {
		}

        try {
//            myTraceFile.setText(configuration.getAttribute(IQvtLaunchConstants.TRACE_FILE, "")); //$NON-NLS-1$
        } catch (CoreException e) {
        }

        try {
//            myTraceFile.setUseFileFlag(configuration.getAttribute(IQvtLaunchConstants.USE_TRACE_FILE, false)); 
        } catch (CoreException e) {
        } */
	}

	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		configuration.setAttribute(IOCLLaunchConstants.OCL_FILE, myOCLFile.getText());
//		myTransfSignatureControl.performApply(configuration);
//        configuration.setAttribute(IQvtLaunchConstants.TRACE_FILE, myTraceFile.getText());
//        configuration.setAttribute(IQvtLaunchConstants.USE_TRACE_FILE, myTraceFile.getUseFileFlag());
	}
    
    @Override
	public boolean isValid(ILaunchConfiguration unused) {
    	if (myQuery == null) {
            return false;
        }
        else{
            setMessage(null);
            setErrorMessage(null);
            String moduleName;
/*            try {
            	moduleName = myQuery.getModuleName();
            }
            catch (MdaException e) {
            	IStatus status = StatusUtil.makeErrorStatus(e.getMessage(), e);
            	return TransformationControls.statusToTab(status, SET_MESSAGE);
            }
            if (myTraceFile.getText().length() == 0) {
            	myTraceFile.update(moduleName, MDAConstants.QVTO_TRACEFILE_EXTENSION);
            }
            IStatus status = myTransfSignatureControl.validate(moduleName, getShell(), myTraceFile.getText(), myTraceFile.getUseFileFlag());
            return TransformationControls.statusToTab(status, SET_MESSAGE); */
        } 
    	return true;
    }

	private void setResourceURI(URI ecoreURI) {
//		ResourceSet resourceSet = accessorRegistry.getProjectRegistry().getResourceSet();
		URIConverter uriConverter = myValidationRS.getURIConverter();
		try {
			InputStream inputStream = uriConverter.createInputStream(ecoreURI);
			inputStream.close();
		} catch (IOException e) {
			showError("Bad resource error", "Selected file '" + ecoreURI.toString() + "' does not exist", e);
			return;
		}
		myOCLFile.setText(ecoreURI.toString());
//		doValidation();
	}

	private void showError(String title, String message, Exception e) {
		IStatus status = new Status(Status.ERROR, OCLDebugUIPlugin.PLUGIN_ID, message, e);
		ErrorDialog.openError(getShell(), "OCL Debug Launch Error", title, status);
	}
    
    private boolean validateOCLFile() {
        myQuery = null;

        String fileName = myOCLFile.getText();
        if (fileName == null || fileName.length() == 0) {
            setErrorMessage("No query");
            return false;
        }
        
/*        URI uri = EmfUtil.makeUri(fileName);
        if (uri == null) {
            setErrorMessage("Invalid URI " + fileName);
            return false;
        } */
        
/*        try {
        	myQuery = myTransformationMaker.makeTransformation(fileName);
        }
        catch (MdaException e) {
            setErrorMessage(e.getMessage());
            return false;
        } */
        return true;
    }
    
    private final ISetMessage SET_MESSAGE = new ISetMessage() {
        public void setErrorMessage(String message) {
        	OCLQueryLauncherTab.this.setErrorMessage(message);
        }

        public void setMessage(String message) {
        	OCLQueryLauncherTab.this.setMessage(message);
        }
    };
}
