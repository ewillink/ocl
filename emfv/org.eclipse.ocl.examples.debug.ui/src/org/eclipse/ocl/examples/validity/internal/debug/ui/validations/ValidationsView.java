/**
 * <copyright>
 *
 * Copyright (c) 2010 E.D.Willink and others.
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
 * $Id: RegistrationsView.java,v 1.2 2010/04/13 16:04:12 ewillink Exp $
 */
package org.eclipse.ocl.examples.validity.internal.debug.ui.validations;


import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.DecoratingColumLabelProvider;
import org.eclipse.emf.edit.ui.provider.DiagnosticDecorator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.ocl.examples.pivot.util.Pivotable;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchGraphicConstants;
import org.eclipse.ui.internal.WorkbenchImages;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.parser.IParseResult;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class ValidationsView extends ViewPart implements ISelectionListener{

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.eclipse.ocl.modelregistry.registrations";

	protected final ComposedAdapterFactory adapterFactory;
	protected ResourceSet modelResourceSet = null;
	private TreeViewer modelViewer;
	private TreeViewer constraintViewer;
	private Action lockSelection;
	private Action selectAllModelElements;
	private Action selectAllConstraints;
	private Action doubleClickAction;
	private final @NonNull AdapterFactoryLabelProvider labelProvider;
	private @NonNull DiagnosticDecorator diagnosticDecorator;
	private @NonNull DecoratingColumLabelProvider modelLabelProvider;
	private final @NonNull AdapterFactoryContentProvider modelContentProvider;
	private final @NonNull ConstraintContentProvider constraintContentProvider;

	/*
	 * The content provider class is responsible for
	 * providing objects to the view.
	 */
	 
	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public ValidationsView() {
	    adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
	    adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
	    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	    modelResourceSet = new ResourceSetImpl();
	    labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
	    modelContentProvider = new AdapterFactoryContentProvider(adapterFactory);
	    constraintContentProvider = new ConstraintContentProvider(labelProvider);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	@Override
	public void createPartControl(Composite parent) {
		System.out.println("createPartControl  " + parent);
        Composite composite = new Composite(parent, SWT.NONE);
        {
	        composite.setLayout(new GridLayout(2, true));        
	        GridData gridData = new GridData(GridData.FILL_BOTH);
	        gridData.grabExcessHorizontalSpace = true;
	        gridData.grabExcessVerticalSpace = true;
	        composite.setLayoutData(gridData);
        }
        {
			Tree modelTree = new Tree(composite, SWT.CHECK | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	        GridData gridData = new GridData(GridData.FILL_BOTH);
	        gridData.grabExcessHorizontalSpace = true;
	        gridData.grabExcessVerticalSpace = true;
	        modelTree.setLayoutData(gridData);
		    modelViewer = new TreeViewer(modelTree);
        }
		{
			Tree constraintTree = new Tree(composite, SWT.CHECK | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
	        GridData gridData = new GridData(GridData.FILL_BOTH);
	        gridData.grabExcessHorizontalSpace = true;
	        gridData.grabExcessVerticalSpace = true;
	        constraintTree.setLayoutData(gridData);
		    constraintViewer = new TreeViewer(constraintTree);
		}
		
	    diagnosticDecorator = new DiagnosticDecorator(modelResourceSet, modelViewer);
		modelLabelProvider = new DecoratingColumLabelProvider(labelProvider, diagnosticDecorator);
		
		
//	    modelViewer.setContentProvider(new ValidationsContentProvider());
//	    modelViewer.setLabelProvider(new ValidationsLabelProvider());
		modelViewer.setContentProvider(modelContentProvider);
		modelViewer.setLabelProvider(modelLabelProvider);
//		modelViewer.setLabelProvider(labelProvider);

	    
		constraintViewer.setContentProvider(constraintContentProvider);
	    constraintViewer.setLabelProvider(labelProvider);
/*		Tree tree = viewer.getTree();
		TableLayout layout = new TableLayout();
		tree.setLayout(layout);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		TreeColumn c1 = new TreeColumn(tree, SWT.LEFT);
		layout.addColumnData(new ColumnWeightData(1, 100, true));
		c1.setText("name"); //getString("c1"));
//		c1.setResizable(true);
		TreeColumn c2 = new TreeColumn(tree, SWT.LEFT);
		layout.addColumnData(new ColumnWeightData(4, 100, true));
		c2.setText("value"); //getString("c2"));
//		c2.setResizable(true);
		TreeColumn c3 = new TreeColumn(tree, SWT.LEFT);
		layout.addColumnData(new ColumnWeightData(4, 100, true));
		c3.setText("resolution"); //getString("c3"));
//		c3.setResizable(true);
//		viewer.setColumnProperties(new String[] { "a", "b", "c" });
//		viewer.setSorter(new NameSorter());
		
		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.eclipse.ocl.modelregistry.explorer.viewer"); */
		makeActions();
		hookContextMenu();
//		hookDoubleClickAction();
		contributeToActionBars();
		
/*		for (int i = 0; i < 3; i++)
			viewer.getLabelProvider(i); */

		ISelectionService service = (ISelectionService) getSite().getService(ISelectionService.class);
		service.addSelectionListener(this);
		modelViewer.setInput(service.getSelection());
        Dialog.applyDialogFont(composite);
	}

	@Override
	public void dispose() {
		ISelectionService service = (ISelectionService) getSite().getService(ISelectionService.class);
		service.removeSelectionListener(this);
		super.dispose();
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		System.out.println("selectionChanged to " + selection);
		if (lockSelection.isChecked()) {
			return;
		}
		Notifier input = null;
		if (selection instanceof IStructuredSelection) {
			input = getStructureSelection((IStructuredSelection) selection, part);
		}
		else if (selection instanceof ITextSelection) {
			input = getTextSelection((ITextSelection) selection, part);
		}

		if (input instanceof EObject) {
			Resource eResource = ((EObject)input).eResource();
			if (eResource != null) {
				input = eResource;
			}
		}
		if (input instanceof Resource) {
			ResourceSet resourceSet = ((Resource)input).getResourceSet();
			if (resourceSet != null) {
				input = resourceSet;
			}
		}
		if (input instanceof ResourceSet) {
			modelResourceSet = (ResourceSet) input;
		}
		if (modelViewer.getInput() != input) {
			modelViewer.setInput(input);
			constraintViewer.setInput(input);
		}
//		modelViewer.setSelection(new StructuredSelection(editingDomain.getResourceSet().getResources().get(0)), true);
		modelViewer.setSelection(selection);
	}

	private @Nullable Notifier getTextSelection(final @NonNull ITextSelection selection, IWorkbenchPart part) {
		if (selection.getStartLine() < 0 || selection.getEndLine() < 0) {
			return null;
		}
		if (!(part instanceof XtextEditor)) {
			return null;
		}
		IXtextDocument document = ((XtextEditor)part).getDocument();
		Notifier selectedObject = document.readOnly(new IUnitOfWork<Notifier, XtextResource>() {
			public Notifier exec(XtextResource xtextResource) {
				if (xtextResource == null) {
					return null;
				}
				IParseResult parseResult = xtextResource.getParseResult();
				if (parseResult == null)
					throw new NullPointerException("parseResult is null");
				ICompositeNode rootNode = parseResult.getRootNode();
//				INode lastVisibleNode = NodeModelUtils.getLastCompleteNodeByOffset(rootNode, selection.getOffset());
//				EObject currentModel = NodeModelUtils.getNearestSemanticObject(lastVisibleNode);						
				INode lastVisibleNode = NodeModelUtils.findLeafNodeAtOffset(rootNode, selection.getOffset());
				if (lastVisibleNode == null) {
					return null; 
				}		
				EObject currentModel = NodeModelUtils.findActualSemanticObjectFor(lastVisibleNode);	
				if (currentModel instanceof Pivotable) {
					return ((Pivotable)currentModel).getPivot();
				}
				else {
					return currentModel;
				}
//				if (!(currentModel instanceof ClassCS)) {
//					return null; 
//				}		
//				ClassCS oclInEcoreClass = (ClassCS) currentModel;
//				return PivotUtil.getPivot(org.eclipse.ocl.examples.pivot.Class.class, oclInEcoreClass);
			}					
		});
		return selectedObject;
	}

	protected @Nullable Notifier getStructureSelection(@NonNull IStructuredSelection selection, IWorkbenchPart part) {
		if (selection.size() == 1) {
			Object firstElement = selection.getFirstElement();
			if (firstElement instanceof Notifier)
				return (Notifier) firstElement;
			if (firstElement instanceof IAdaptable) {
				Object adaptedElement = ((IAdaptable)firstElement).getAdapter(Notifier.class);
				if (adaptedElement instanceof Notifier)
					return (Notifier) adaptedElement;
			}
			if (firstElement instanceof EObjectNode) {
				return getXtextOutlineSelection((EObjectNode) firstElement, part);
			}
		}
		return null;
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				ValidationsView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(modelViewer.getControl());
		modelViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, modelViewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private @Nullable Notifier getXtextOutlineSelection(@NonNull EObjectNode selection, IWorkbenchPart part) {
		if (!(part instanceof XtextEditor)) { // it's a ContentOutline
			return null;
		}
		// Find matching editor
		final URI selectedURI = selection.getEObjectURI();
		IXtextDocument document = ((XtextEditor)part).getDocument();
		Notifier selectedObject = document.readOnly(new IUnitOfWork<Notifier, XtextResource>() {
			public Notifier exec(XtextResource xtextResource) {
				if (xtextResource == null) {
					return null;
				}
				EObject eObject = xtextResource.getEObject(selectedURI.fragment());
				return eObject;
			}					
		});
		return selectedObject;
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(lockSelection);
		manager.add(new Separator());
		manager.add(selectAllModelElements);
		manager.add(selectAllConstraints);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(lockSelection);
		manager.add(selectAllModelElements);
		manager.add(selectAllConstraints);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(lockSelection);
		manager.add(selectAllModelElements);
		manager.add(selectAllConstraints);
	}

	private void makeActions() {
		lockSelection = new Action("Lock Model Elements to current selection", IAction.AS_CHECK_BOX) {
//			@Override
//			public void run() {
//				setEnabled(!isEnabled());
//			}
		};
		lockSelection.setToolTipText("Inhibit tracking to the current selection");
		lockSelection.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJ_ELEMENT));
		lockSelection.setImageDescriptor(WorkbenchImages
				.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR));
//		lockSelection.setDisabledImageDescriptor(WorkbenchImages
//				.getImageDescriptor(IWorkbenchGraphicConstants.IMG_ETOOL_PIN_EDITOR_DISABLED));
		selectAllModelElements = new Action() {
			@Override
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		selectAllModelElements.setText("Select All Model Elements");
		selectAllModelElements.setToolTipText("Select All Model Elements for Validation");
		selectAllModelElements.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
			getImageDescriptor(ISharedImages.IMG_OBJ_ELEMENT));
		
		selectAllConstraints = new Action() {
			@Override
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		selectAllConstraints.setText("Select All Constraints");
		selectAllConstraints.setToolTipText("Select All Constraints for Validation");
		selectAllConstraints.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
				getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			@Override
			public void run() {
				ISelection selection = modelViewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				showMessage("Double-click detected on "+obj.toString());
			}
		};
	}

/*	private void hookDoubleClickAction() {
		modelViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	} */

	private void showMessage(String message) {
		MessageDialog.openInformation(
			modelViewer.getControl().getShell(),
			"Validations View",
			message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		modelViewer.getControl().setFocus();
	}
}