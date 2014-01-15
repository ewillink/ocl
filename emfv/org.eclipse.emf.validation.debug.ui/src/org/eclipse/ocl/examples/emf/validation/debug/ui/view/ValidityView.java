/**
 * <copyright>
 *
 * Copyright (c) 2013 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *	E.D.Willink (CEA LIST) - initial API and implementation
 *  Obeo - Use Forms and Manage Actions.
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.emf.validation.debug.ui.view;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.DecoratingColumLabelProvider;
import org.eclipse.ocl.examples.emf.validation.debug.ui.actions.DisableAllNodesAction;
import org.eclipse.ocl.examples.emf.validation.debug.ui.actions.DisableAllUnusedNodesAction;
import org.eclipse.ocl.examples.emf.validation.debug.ui.actions.EnableAllNodesAction;
import org.eclipse.ocl.examples.emf.validation.debug.ui.actions.ExportValidationResultAction;
import org.eclipse.ocl.examples.emf.validation.debug.ui.actions.FilterValidationResultAction;
import org.eclipse.ocl.examples.emf.validation.debug.ui.actions.LockValidatableNodesAction;
import org.eclipse.ocl.examples.emf.validation.debug.ui.actions.RunValidityAction;
import org.eclipse.ocl.examples.emf.validation.debug.ui.actions.ShowElementInEditorAction;
import org.eclipse.ocl.examples.emf.validation.debug.ui.filters.NodesViewerFilter;
import org.eclipse.ocl.examples.emf.validation.debug.ui.messages.ValidationDebugMessages;
import org.eclipse.ocl.examples.emf.validation.debug.ui.providers.NodeCheckStateProvider;
import org.eclipse.ocl.examples.emf.validation.debug.ui.providers.NodeLabelProvider;
import org.eclipse.ocl.examples.emf.validation.debug.ui.providers.ConstrainingNodeContentProvider;
import org.eclipse.ocl.examples.emf.validation.debug.ui.providers.ValidatableNodeContentProvider;
import org.eclipse.ocl.examples.emf.validation.debug.ui.ripoffs.FilteredCheckboxTree;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ResultConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ResultValidatableNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ValidatableNode;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.part.ViewPart;

/**
 * The ValidityView provides a dual view of ValidatableNode model elements and
 * ConstrainingNode model classes to browse, filter and control model validation. 
 */
public class ValidityView extends ViewPart implements ISelectionListener
{
	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final @NonNull String ID = "org.eclipse.ocl.examples.emf.validation.debug.ui.validity";//$NON-NLS-1$

	protected FilteredCheckboxTree filteredValidatableNodesTree;
	protected FilteredCheckboxTree filteredConstrainingNodesTree;
	
	private final @NonNull ValidityViewRefreshJob refreshJob = new ValidityViewRefreshJob();

	protected final @NonNull IDEValidityManager validityManager;
	
	/** Keeps a reference to the toolkit used to create our form. */
	private FormToolkit formToolkit;
	
	/** Form that will contain the Validity View itself. */
	private Form validityViewForm;
	
	/** Allows us to display error/warning messages directly on the form. */
	private FormMessageManager messageManager;

	/** Form that will contain the Validatable column View. */
	private SashForm validateableElementsForm;
	
	/** Form that will contain the Constraining column View. */
	private SashForm constrainingElementsForm;
	
	/** We'll create this {@link SashForm} as the main body of the Validity view form. */
	private SashForm formBody;
	
	/**
	 * Keeps a reference to the "validateable Elements" section of the Validity view form.
	 */
	private Section validatableNodesSection;
	
	/** The message key. */
	private final String messageKey = "ValidityViewMessageKey";

	/** Kept as an instance member, this will allow us to set unique identifiers to the status messages. */
	private int messageCount;
	
	/**
	 * Keeps a reference to the "constraining Nodes" section of the Validity view form.
	 */
	private Section constrainingNodesSection;
	
	protected ResourceSet modelResourceSet;
	
	/**Context Menu.*/
	private ShowElementInEditorAction showValidatableElementInEditorAction;
	private ShowElementInEditorAction showConstrainingElementInEditorAction;
	
	/**Local Tool Bar.*/
	private Action runValidationAction;
	private Action lockValidatableNodesAction;
	private Action exportValidationResultAction;
	private IAction filterValidationResultAction;

	/**Validatable Tool Bar.*/
	private Action enableAllValidatableNodesAction;
	private Action disableAllValidatableNodesAction;
	private Action disableAllUnusedValidatableNodesAction;

	/**Constraining Tool Bar.*/
	private Action enableAllConstrainingNodesAction;
	private Action disableAllConstrainingNodesAction;
	private Action disableAllUnusedConstrainingNodesAction;

	/** Double Click action handlers.*/
	private Action constrainingNodesDoubleClickAction;
	private Action validatableNodesDoubleClickAction;
	
	private final NodesViewerFilter constrainingNodesFilterByKind = new NodesViewerFilter();
	private final NodesViewerFilter validatableNodesFilterByKind = new NodesViewerFilter();

	class DecoratingNodeLabelProvider extends DecoratingColumLabelProvider
	{
		public DecoratingNodeLabelProvider(@NonNull ILabelProvider nodeLabelProvider,
				@NonNull SeveritiesDecorator severitiesDecorator) {
			super(nodeLabelProvider, new SeveritiesDecorator(validityManager));
			cellLabelProvider = (CellLabelProvider) nodeLabelProvider;
		}
	}

	public ValidityView() {
		validityManager = new IDEValidityManager(refreshJob);
		modelResourceSet = new ResourceSetImpl();
	}

	private void contributeToActionBars() {
		IToolBarManager toolBarManager = getForm().getToolBarManager();
		fillLocalToolBar(toolBarManager);
		
		// validatable Column
		ToolBarManager validatableSectionToolBarManager = createSectionToolBar(validatableNodesSection);
		fillValidatableColumnToolBar(validatableSectionToolBarManager);

		// constraining Column
		ToolBarManager constrainingSectionToolBarManager = createSectionToolBar(constrainingNodesSection);
		fillConstrainingColumnToolBar(constrainingSectionToolBarManager);
	}

	/**
	 * Creates a tool bar for the given section.
	 * 
	 * @param section
	 *            The section for which we need a tool bar.
	 * @return The created tool bar.
	 */
	protected static final ToolBarManager createSectionToolBar(Section section) {
		final ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT | SWT.HORIZONTAL);
		final ToolBar toolBar = toolBarManager.createControl(section);

		final Cursor handCursor = new Cursor(Display.getCurrent(), SWT.CURSOR_HAND);
		toolBar.setCursor(handCursor);
		// Cursor needs to be explicitly disposed
		toolBar.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				if (!handCursor.isDisposed()) {
					handCursor.dispose();
				}
			}
		});

		section.setTextClient(toolBar);
		toolBar.setData(toolBarManager);
		// Do not keep a reference to the manager when we dispose the tool bar
		toolBar.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				toolBar.setData(null);
			}
		});
		
		return toolBarManager;
	}
	
	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	@Override
	public void createPartControl(Composite parent) {
	    Composite sash = new SashForm(parent, SWT.HORIZONTAL);
	    {
			GridLayout layout = new GridLayout();
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			layout.verticalSpacing = 0;
			layout.horizontalSpacing = 0;
			sash.setLayout(layout);
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.horizontalIndent = 1;
			sash.setLayoutData(gridData);
	    }

		formToolkit = new FormToolkit(sash.getDisplay());
		
		createValidityViewForm(formToolkit, sash);
	}
	
	/**
	 * This will be called in order to create the actual body of the validity view, the "Validity" form.
	 * 
	 * @param toolkit
	 *            Toolkit that can be used to create the form.
	 * @param parent
	 *            Parent composite of the form.
	 */
	protected void createValidityViewForm(FormToolkit toolkit, Composite parent){
		Color blackColor = parent.getDisplay().getSystemColor(SWT.COLOR_BLACK);
		Color blueColor = parent.getDisplay().getSystemColor(SWT.COLOR_BLUE);
	    ILabelProvider labelProvider = new AdapterFactoryLabelProvider(validityManager.getAdapterFactory());
		ILabelProvider nodeLabelProvider = new NodeLabelProvider(labelProvider, blackColor, blueColor);
		IContentProvider validatableContentProvider = new ValidatableNodeContentProvider(validityManager, nodeLabelProvider);
		IContentProvider constrainingNodeContentProvider = new ConstrainingNodeContentProvider(validityManager, nodeLabelProvider);
		ICheckStateProvider nodeCheckStateProvider = new NodeCheckStateProvider();
	    ILabelProvider nodeDecoratingLabelProvider = new DecoratingNodeLabelProvider(nodeLabelProvider, new SeveritiesDecorator(validityManager));
		
		validityViewForm = toolkit.createForm(parent);
		messageManager = new FormMessageManager(getForm());
		messageManager.setDecorationPosition(SWT.LEFT | SWT.TOP);
		toolkit.decorateFormHeading(getForm());
		
		getForm().setText(ValidationDebugMessages.ValidityView_viewTitle);
		messageManager.addMessage(messageKey + messageCount++, ValidationDebugMessages.ValidityView_Messages_NoSelection, IStatus.WARNING, getForm());
		
		Composite mainBody = getForm().getBody();
		mainBody.setLayout(new GridLayout());
		formBody = new SashForm(mainBody, SWT.HORIZONTAL | SWT.SMOOTH);
		toolkit.adapt(formBody);
		formBody.setLayoutData(new GridData(GridData.FILL_BOTH));

		validateableElementsForm = new SashForm(formBody, SWT.VERTICAL | SWT.SMOOTH);
		toolkit.adapt(validateableElementsForm);

		validatableNodesSection = toolkit.createSection(validateableElementsForm, ExpandableComposite.TITLE_BAR);
		validatableNodesSection.setText(ValidationDebugMessages.ValidityView_validatableNodesSectionName);
		
		CheckboxTreeViewer validatableNodesViewer;
		{
			Composite validatableNodesSectionBody = toolkit.createComposite(validatableNodesSection);
			{
				GridLayout layout = new GridLayout(1, true);
				layout.marginBottom = 0;
				layout.marginLeft = 0;
				layout.marginRight = 0;
				layout.marginTop = 0;
				validatableNodesSectionBody.setLayout(layout);
				GridData gridData = new GridData(GridData.FILL_BOTH);
				gridData.grabExcessHorizontalSpace = true;
				gridData.grabExcessVerticalSpace = true;
				validatableNodesSectionBody.setLayoutData(gridData);
			}
			
			PatternFilter filter = new PatternFilter();
			filteredValidatableNodesTree = new FilteredCheckboxTree(validatableNodesSectionBody, SWT.CHECK | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, filter);
			filteredValidatableNodesTree.disableTextWidget();
			
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = true;
			filteredValidatableNodesTree.setLayoutData(gridData);
			
			validatableNodesViewer = getValidatableNodesViewer();
			validatableNodesViewer.getControl().setLayoutData(gridData);
			
			toolkit.paintBordersFor(validatableNodesSectionBody);
			validatableNodesSection.setClient(validatableNodesSectionBody);
		}
		
		constrainingElementsForm = new SashForm(formBody, SWT.VERTICAL | SWT.SMOOTH);
		toolkit.adapt(constrainingElementsForm);

		constrainingNodesSection = toolkit.createSection(constrainingElementsForm, ExpandableComposite.TITLE_BAR);
		constrainingNodesSection.setText(ValidationDebugMessages.ValidityView_constrainingNodesSectionName);

		CheckboxTreeViewer constrainingNodesViewer;
		{
			Composite constrainingNodesSectionBody = toolkit.createComposite(constrainingNodesSection);
			{
				GridLayout layout = new GridLayout(1, true);
				layout.marginBottom = 0;
				layout.marginLeft = 0;
				layout.marginRight = 0;
				layout.marginTop = 0;
				constrainingNodesSectionBody.setLayout(layout);
				GridData gridData = new GridData(GridData.FILL_BOTH);
				gridData.grabExcessHorizontalSpace = true;
				gridData.grabExcessVerticalSpace = true;
				constrainingNodesSectionBody.setLayoutData(gridData);
			}
			
			PatternFilter filter = new PatternFilter();
			filteredConstrainingNodesTree = new FilteredCheckboxTree(constrainingNodesSectionBody, SWT.CHECK | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL, filter);
			filteredConstrainingNodesTree.disableTextWidget();
			
			GridData gridData = new GridData(GridData.FILL_BOTH);
			gridData.grabExcessHorizontalSpace = true;
			gridData.grabExcessVerticalSpace = true;
			filteredConstrainingNodesTree.setLayoutData(gridData);
			
			constrainingNodesViewer = getConstrainingNodesViewer();
			constrainingNodesViewer.getControl().setLayoutData(gridData);
			
			toolkit.paintBordersFor(constrainingNodesSectionBody);
			constrainingNodesSection.setClient(constrainingNodesSectionBody);
		}
		
		ICheckStateListener nodeCheckStateListener = new ValidityNodeCheckStateListener(
			getValidatableNodesViewer(), getConstrainingNodesViewer());
		validatableNodesViewer.setContentProvider(validatableContentProvider);
		validatableNodesViewer.setLabelProvider(nodeDecoratingLabelProvider);
		validatableNodesViewer.setCheckStateProvider(nodeCheckStateProvider);
		validatableNodesViewer.addCheckStateListener(nodeCheckStateListener);
		validatableNodesViewer.addFilter(validatableNodesFilterByKind);
		
		constrainingNodesViewer.setContentProvider(constrainingNodeContentProvider);
		constrainingNodesViewer.setLabelProvider(nodeDecoratingLabelProvider);
		constrainingNodesViewer.setCheckStateProvider(nodeCheckStateProvider);
		constrainingNodesViewer.addCheckStateListener(nodeCheckStateListener);
		constrainingNodesViewer.addFilter(constrainingNodesFilterByKind);
		
		formBody.setWeights(new int[] {1, 1, });
		
		// Create the help context id for the viewer's control
		makeActions();
		hookContextMenu();
		hookConstrainingNodesDoubleClickAction();
		hookValidatableNodesDoubleClickAction();
		contributeToActionBars();
		ISelectionService service = (ISelectionService) getSite().getService(ISelectionService.class);
		if (service != null) {
			service.addSelectionListener(this);
			ISelection selection = service.getSelection();
			Notifier input = SelectionUtil.getNotifierSelection(selection, this);
			validityManager.setInput(input);
		}
		refreshJob.initViewers(validatableNodesViewer, constrainingNodesViewer);
		Dialog.applyDialogFont(parent);
		
		ColumnViewerToolTipSupport.enableFor(validatableNodesViewer);
		ColumnViewerToolTipSupport.enableFor(constrainingNodesViewer);
	}
	
	/**
	 * Returns the validity view form.
	 * 
	 * @return The validity view form.
	 */
	protected Form getForm() {
		return validityViewForm;
	}
	
	@Override
	public void dispose() {
		ISelectionService service = (ISelectionService) getSite().getService(ISelectionService.class);
		if (service != null) {
			service.removeSelectionListener(this);
		}
		filteredValidatableNodesTree.dispose();
		filteredConstrainingNodesTree.dispose();
		super.dispose();
	}

	private void fillValidatableContextMenu(@NonNull IContributionManager manager) {
		// use the same actions as the column tool bar
		manager.add(enableAllValidatableNodesAction);
		manager.add(disableAllValidatableNodesAction);
		manager.add(new Separator());
		manager.add(disableAllUnusedValidatableNodesAction);
		
		// Other plug-ins can contribute their actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		manager.add(new Separator());
		manager.add(runValidationAction);
		manager.add(new Separator());
		manager.add(showValidatableElementInEditorAction);
	}
	
	private void fillConstrainingContextMenu(@NonNull IContributionManager manager) {
		// Use the same actions as the column tool bar
		manager.add(enableAllConstrainingNodesAction);
		manager.add(disableAllConstrainingNodesAction);
		manager.add(new Separator());
		manager.add(disableAllUnusedConstrainingNodesAction);
		
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		manager.add(new Separator());
		manager.add(runValidationAction);
		manager.add(new Separator());
		manager.add(showConstrainingElementInEditorAction);
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(lockValidatableNodesAction);
		manager.add(runValidationAction);
		manager.add(new Separator());
		manager.add(filterValidationResultAction);
		manager.add(new Separator());
		manager.add(exportValidationResultAction);

		manager.update(true);
	}
	
	private void fillConstrainingColumnToolBar(IContributionManager manager) {
		manager.add(enableAllConstrainingNodesAction);
		manager.add(disableAllConstrainingNodesAction);
		manager.add(new Separator());
		manager.add(disableAllUnusedConstrainingNodesAction);

		manager.update(true);
	}

	private void fillValidatableColumnToolBar(IContributionManager manager) {
		manager.add(enableAllValidatableNodesAction);
		manager.add(disableAllValidatableNodesAction);
		manager.add(new Separator());
		manager.add(disableAllUnusedValidatableNodesAction);

		manager.update(true);
	}


	private void hookContextMenu() {
		// Validatable context menu
		MenuManager menuMgrValidatable = new MenuManager("#PopupMenu");//$NON-NLS-1$
		menuMgrValidatable.setRemoveAllWhenShown(true);
		menuMgrValidatable.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				@SuppressWarnings("null")@NonNull IMenuManager manager2 = manager;
				ValidityView.this.fillValidatableContextMenu(manager2);
			}
		});
		Menu menuValidatable = menuMgrValidatable.createContextMenu(getValidatableNodesViewer().getControl());
		getValidatableNodesViewer().getControl().setMenu(menuValidatable);
		getSite().registerContextMenu(menuMgrValidatable, getValidatableNodesViewer());

		// Constraining context menu
		MenuManager menuMgrConstraining = new MenuManager("#PopupMenu");//$NON-NLS-1$
		menuMgrConstraining.setRemoveAllWhenShown(true);
		menuMgrConstraining.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				@SuppressWarnings("null")@NonNull IMenuManager manager2 = manager;
				ValidityView.this.fillConstrainingContextMenu(manager2);
			}
		});
		Menu menuConstraining = menuMgrConstraining.createContextMenu(getConstrainingNodesViewer().getControl());
		getConstrainingNodesViewer().getControl().setMenu(menuConstraining);
		getSite().registerContextMenu(menuMgrConstraining, getConstrainingNodesViewer());
	}

	private void hookConstrainingNodesDoubleClickAction() {
		getConstrainingNodesViewer().addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				constrainingNodesDoubleClickAction.run();
			}
		});
	}

	private void hookValidatableNodesDoubleClickAction() {
		getValidatableNodesViewer().addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				validatableNodesDoubleClickAction.run();
			}
		});
	}

	private void makeActions() {
		lockValidatableNodesAction = new LockValidatableNodesAction();
		showValidatableElementInEditorAction = new ShowElementInEditorAction(validityManager, getValidatableNodesViewer());
		showConstrainingElementInEditorAction = new ShowElementInEditorAction(validityManager, getConstrainingNodesViewer());
		
		/*Toolbar actions*/
		runValidationAction = new RunValidityAction(validityManager);

		exportValidationResultAction = new ExportValidationResultAction(validityManager, this);
		filterValidationResultAction = new FilterValidationResultAction(this);

		/* Validatable Tool bar actions*/
		enableAllValidatableNodesAction = new EnableAllNodesAction(validityManager, getValidatableNodesViewer(), true);	
		disableAllValidatableNodesAction = new DisableAllNodesAction(validityManager, getValidatableNodesViewer(), true);	
		disableAllUnusedValidatableNodesAction = new DisableAllUnusedNodesAction(validityManager, getValidatableNodesViewer(), true);	
		
		/* Constraining Tool bar actions*/	
		enableAllConstrainingNodesAction = new EnableAllNodesAction(validityManager, getConstrainingNodesViewer(), false);
		disableAllConstrainingNodesAction = new DisableAllNodesAction(validityManager, getConstrainingNodesViewer(), false);
		disableAllUnusedConstrainingNodesAction = new DisableAllUnusedNodesAction(validityManager, getConstrainingNodesViewer(), false);	

		/*Double Click actions*/
		constrainingNodesDoubleClickAction = new Action() {
			@Override
			public void run() {
				ISelection selection = getConstrainingNodesViewer().getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				if (obj instanceof ResultConstrainingNode) {
					ResultValidatableNode resultValidatableNode = ((ResultConstrainingNode)obj).getResultValidatableNode();
					getValidatableNodesViewer().setSelection(
							new StructuredSelection(resultValidatableNode),
							true);
				} else if (obj instanceof ConstrainingNode) {
					Set<Object> expanded = new LinkedHashSet<Object>(Arrays.asList(getConstrainingNodesViewer().getExpandedElements()));
					if (expanded.contains(obj)) {
						getConstrainingNodesViewer().setExpandedState(obj, false);
					} else {
						getConstrainingNodesViewer().setExpandedState(obj, true);
					}
				}
			}
		};
		validatableNodesDoubleClickAction = new Action() {
			@Override
			public void run() {
				ISelection selection = getValidatableNodesViewer().getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				if (obj instanceof ResultValidatableNode) {
					ResultConstrainingNode resultConstrainingNode = ((ResultValidatableNode) obj)
							.getResultConstrainingNode();
					getConstrainingNodesViewer().setSelection(
							new StructuredSelection(resultConstrainingNode),
							true);
				} else if (obj instanceof ValidatableNode) {
					Set<Object> expanded = new LinkedHashSet<Object>(Arrays.asList(getValidatableNodesViewer().getExpandedElements()));
					if (expanded.contains(obj)) {
						getValidatableNodesViewer().setExpandedState(obj, false);
					} else {
						getValidatableNodesViewer().setExpandedState(obj, true);
					}
				}
			}
		};
	}
	
	public void addFilteredSeverity(Severity severity) {
		constrainingNodesFilterByKind.addFilteredSeverity(severity);
		validatableNodesFilterByKind.addFilteredSeverity(severity);
		
		getConstrainingNodesViewer().refresh();
		getValidatableNodesViewer().refresh();
	}
	
	public void removeFilteredSeverity(Severity severity) {
		constrainingNodesFilterByKind.removeFilteredSeverity(severity);
		validatableNodesFilterByKind.removeFilteredSeverity(severity);
		
		getConstrainingNodesViewer().refresh();
		getValidatableNodesViewer().refresh();
	}
	
	/**
	 * gets the Validity Manager
	 * 
	 * @return the validityManager
	 */
	public @NonNull IDEValidityManager getValidityManager(){
		return validityManager;
	}
	
	/**
	 * gets the validatable Nodes Viewer
	 * 
	 * @return the validatableNodesViewer
	 */
	public @NonNull CheckboxTreeViewer getValidatableNodesViewer(){
		CheckboxTreeViewer viewer = (CheckboxTreeViewer) filteredValidatableNodesTree.getViewer();
		// a filtered tree never has a null viewer
		assert viewer != null;
		return viewer;
	}
	
	/**
	 * gets the Constraining Nodes Viewer
	 * 
	 * @return the constrainingNodesViewer
	 */
	public @NonNull CheckboxTreeViewer getConstrainingNodesViewer(){
		CheckboxTreeViewer viewer = (CheckboxTreeViewer) filteredConstrainingNodesTree.getViewer();
		// a filtered tree never has a null viewer
		assert viewer != null;
		return viewer;
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (lockValidatableNodesAction.isChecked()) {
			return;
		}
		if (part instanceof EditorPart){
			Notifier input = SelectionUtil.getNotifierSelection(selection, part);
			validityManager.setInput(input);
			RootNode rootNode = validityManager.getRootNode();
			Object validatableNodesViewerInput = getValidatableNodesViewer().getInput();
			if (validatableNodesViewerInput == null || !validatableNodesViewerInput.equals(rootNode)) {
				getValidatableNodesViewer().setInput(rootNode);
				getConstrainingNodesViewer().setInput(rootNode);

				filteredValidatableNodesTree.resetFilter();
				filteredConstrainingNodesTree.resetFilter();
				
				validationRootChanged(rootNode);
			}
		}
	}

	private void validationRootChanged(RootNode rootNode) {
		messageManager.removeMessages(getForm());
		
		String currentMessagekey = null;
		String currentMessageText = null;
		int currentStatus = 0;
		if (rootNode != null) {
			if (rootNode.getValidatableNodes().isEmpty()) {
				filteredValidatableNodesTree.disableTextWidget();
				
				currentMessagekey = messageKey + messageCount++;
				currentMessageText = ValidationDebugMessages.ValidityView_Messages_NoModelElement;
				currentStatus = IStatus.INFO;
			} else {
				filteredValidatableNodesTree.enableTextWidget();
			}
			if (rootNode.getConstrainingNodes().isEmpty()) {
				filteredConstrainingNodesTree.disableTextWidget();

				currentMessagekey = messageKey + messageCount++;
				currentMessageText = ValidationDebugMessages.ValidityView_Messages_NoConstraints;
				currentStatus = IStatus.WARNING;
			} else {
				filteredConstrainingNodesTree.enableTextWidget();
			}
		} else {
			filteredValidatableNodesTree.disableTextWidget();
			filteredConstrainingNodesTree.disableTextWidget();
			
			currentMessagekey = messageKey + messageCount++;
			currentMessageText = ValidationDebugMessages.ValidityView_Messages_NoSelection;
			currentStatus = IStatus.WARNING;
		}
		
		if (currentMessagekey != null) {
			messageManager.addMessage(currentMessagekey, currentMessageText, currentStatus, getForm());
		}
	}

	/**
	 * Passing the focus request to the viewer's control. This will Refresh the
	 * viewers contents.
	 */
	@Override
	public void setFocus() {
		getValidatableNodesViewer().getControl().setFocus();
		
		// Refresh the view
		filteredValidatableNodesTree.resetFilter();
		filteredConstrainingNodesTree.resetFilter();
	}
}
