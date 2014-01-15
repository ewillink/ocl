package org.eclipse.ocl.examples.validity.internal.debug.ui.launcher;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionStatusDialog;

/**
 * @author sboyko
 */
public class BrowseInterpretedTransformationDialog extends SelectionStatusDialog {

	public static interface ISelectionListener {
        IStatus selectionChanged(URI selectedUri);
    }

    public BrowseInterpretedTransformationDialog(Shell shell,/* UniSelectTransformationControl.IResourceFilter resourceFilter,*/
    		ILabelProvider labelProvider, /*TransformationRegistry registry,*/ String selectedTransId, ISelectionListener selectionListener) {
        super(shell);

		setTitle("Select Complete OCL File");
		setMessage("Select Complete OCL File containing query to execute");

		setShellStyle(getShellStyle() | SWT.RESIZE);
		setStatusLineAboveButtons(true);
		
//        myResourceFilter = resourceFilter;
//        myRegistry = registry;
        myLabelProvider = labelProvider;
        myInitialTransId = selectedTransId;
        mySelectionListener = selectionListener;
    }
    
    public URI getSelectedUri(){
        return mySelectedUri;
    }
    
/*    @Override
	protected Control createDialogArea(Composite parent) {
        Composite composite = (Composite) super.createDialogArea(parent);        
		composite.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.FILL_HORIZONTAL));

		createMessageArea(composite);
        
        myControl = new UniSelectTransformationControl(parent, myResourceFilter,
        		myLabelProvider, myRegistry, TransformationRegistry.EMPTY_FILTER);
        myControl.addSelectionListener(new UniSelectTransformationControl.ISelectionListener() {
            public void selectionChanged(URI uri) {
            	IStatus selStatus = mySelectionListener.selectionChanged(uri);
            	mySelectedUri = selStatus.isOK() ? uri : null;
            	updateStatus(selStatus);
            }
        });
        myControl.selectTransformationByUri(myInitialTransId);
        
        return myControl;
    } */
    
	@Override
	protected void computeResult() {
	}

    private final String myInitialTransId;
//    private final TransformationRegistry myRegistry;
    private final ILabelProvider myLabelProvider;
//    private final UniSelectTransformationControl.IResourceFilter myResourceFilter;
    private final ISelectionListener mySelectionListener;
    
    private URI mySelectedUri;
//    private UniSelectTransformationControl myControl;
}
