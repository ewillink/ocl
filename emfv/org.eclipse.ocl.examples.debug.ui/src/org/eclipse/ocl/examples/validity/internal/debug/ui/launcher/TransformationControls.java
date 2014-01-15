package org.eclipse.ocl.examples.validity.internal.debug.ui.launcher;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ocl.examples.validity.internal.debug.ui.OCLDebugUIPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


public class TransformationControls {
    private TransformationControls() {}
    
/*    public static IUriGroup createUriGroup(Composite parent, IModelParameterInfo paramInfo, ResourceSet validationRS) {
    	Group group = new Group(parent, SWT.NONE);
    	group.setText(getParameterName(paramInfo));
    	group.setLayout(new FillLayout());

    	GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = GRID;
        group.setLayoutData(data);
    	
        if (paramInfo.isInParameter()) {
	        UriGroupIn uriGroup = new UriGroupIn(group, Messages.TargetUriSelector_Uri, validationRS);
	        return uriGroup;
        }
        else if (paramInfo.isInOutParameter()) {
        	UriGroupInOut uriGroup = new UriGroupInOut(group, Messages.TargetUriSelector_Uri, validationRS);
            return uriGroup;
        }
        else {
        	UriGroupOut uriGroup = new UriGroupOut(group, Messages.TargetUriSelector_Uri, validationRS);
            return uriGroup;
        }
    } */
    
/*    private static String getParameterName(IModelParameterInfo paramInfo) {
    	String typeName;
    	if (paramInfo.getDirection() == Direction.out
    			|| paramInfo.getEntryParamType() == null) {
    		typeName = paramInfo.getModelTypeName();
    		EPackage metamodel = paramInfo.getMetamodel();
			if (metamodel != null) {
				if(metamodel.getNsURI() != null && metamodel.getNsURI().length() > 0) {
					typeName += " (" + metamodel.getNsURI() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
				}
    		}
    	}
    	else {
    		typeName = EmfUtil.getFullName(paramInfo.getEntryParamType()); 
    		typeName += '@';
	    	typeName += paramInfo.getModelTypeName();
    	}
    	return paramInfo.getDirection().name().toUpperCase() + "  " + paramInfo.getName() //$NON-NLS-1$ 
    			+ " : " + typeName; //$NON-NLS-1$
    } */
    
    public static Button createButton(Composite parent,String text){
        return createButton(parent, text, SWT.PUSH, GridData.BEGINNING);
    }
    
    public static Button createButton(Composite parent,String text,int style,int gridData){
        Button button = new Button(parent,style);
        button.setText(text);
        GridData data = new GridData(gridData);
        button.setLayoutData(data);
        return button;
    }
    
    public static Button createCheckBox(Composite parent,String text){
        Button button = new Button(parent,SWT.CHECK);
        button.setText(text);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = GRID;
        button.setLayoutData(data);
        return button;
    }
    
    public static Label createLabel(Composite parent, String text, int span){
        Label label = new Label(parent, SWT.NONE);
        label.setText(text);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        data.horizontalSpan = span;
        label.setLayoutData(data);
        return label;
    }
    
    public static Text createText(Composite parent, int span) {
        return createText(parent, SWT.BORDER, span);
    }
    
    public static Text createText(Composite parent, int style, int span) {
        final Text text = new Text(parent, style);
        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = span;
        text.setLayoutData(gd);     
        
        return text;
    }
    
    public static IStatus makeStatus(int code,String message){
        return new Status(code,OCLDebugUIPlugin.PLUGIN_ID,code,message,null);
    }
    
    public static boolean statusToTab(IStatus status, ISetMessage tab) {
        tab.setMessage(null);
        tab.setErrorMessage(null);
        
        if(status == null) {
            return true;
        }
        
        switch(status.getSeverity()) {
            case IStatus.ERROR: {
                tab.setErrorMessage(status.getMessage());
                return false;
            }
            
            case IStatus.WARNING: {
            	if (tab instanceof ISetMessageEx) {
                    ((ISetMessageEx) tab).setWarningMessage(status.getMessage());
            	}
            	else {
                    tab.setMessage(status.getMessage());
            	}
                return true;
            }
            
            default: {
            	if (tab instanceof ISetMessageEx) {
                    tab.setMessage(status.getMessage());
            	}
                return true;
            }
        }
    }

    public static final int GRID = 3;
    public static final int TEXT_GRID = 1;
}
