package org.eclipse.ocl.examples.emf.validation.debug.ui.launching;

import org.eclipse.core.resources.IFile;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.ocl.examples.emf.validation.debug.ui.utils.CommonPluginImages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public abstract class AbstractLaunchTab extends AbstractLaunchConfigurationTab {
    
    public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
    }
    
    public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(TransformationControls.GRID, false));
        
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        composite.setLayoutData(gridData);
        
        createTransformationSection(composite);
        
        setControl(composite);
        Dialog.applyDialogFont(composite);
    }

    protected abstract void createTransformationSection(Composite parent);
    
    public String getName() {
        return "Query";
    }

    @Override
	public Image getImage() {
        return CommonPluginImages.getInstance().getImage(CommonPluginImages.OCL_QUERY);
    }
    
    public static IFile getFileContext() {
        IWorkbenchPage page = getActivePage();
        if(page == null) {
            return null;
        }
        
        ISelection selection = page.getSelection();
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection ss = (IStructuredSelection)selection;
            if (!ss.isEmpty()) {
                Object obj = ss.getFirstElement();
                if (obj instanceof IFile) {
                    return (IFile)obj;
                }
            }
        }
        
        IEditorPart part = page.getActiveEditor();
        if (part != null) {
            IEditorInput input = part.getEditorInput();
            if(input instanceof IFileEditorInput) {
                return ((IFileEditorInput)input).getFile();
            }
        }
        
        return null;
    }
    
    public static IWorkbenchPage getActivePage() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window == null) {
            return null;
        }
        
        return window.getActivePage();
    }
    
    public boolean isModified() {
        return myModified;
    }
    
    public void setModified(boolean modified) {
        myModified = modified;
    }
    
    private boolean myModified;
}
