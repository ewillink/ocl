package org.eclipse.ocl.examples.validity.internal.debug.ui.validations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.PivotPackage;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManagerResourceSetAdapter;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;
import org.eclipse.uml2.uml.UMLPackage;

public class ConstraintContentProvider implements ITreeContentProvider
{
	public static final @NonNull Object[] NO_OBJECTS = new Object[0];
	public static final @NonNull Map<EClass, List<ConstraintLocator>> globalConstraintLocators = new HashMap<EClass, List<ConstraintLocator>>();

	public static void addConstraintLocator(@NonNull EClass eClass, @NonNull ConstraintLocator constraintLocator) {
		List<ConstraintLocator> list = globalConstraintLocators.get(eClass);
		if (list == null) {
			list = new ArrayList<ConstraintLocator>();
			globalConstraintLocators.put(eClass, list);
		}
		if (!list.contains(constraintLocator)) {
			list.add(constraintLocator);
		}
	}
	
	{
		addConstraintLocator(EcorePackage.Literals.ECLASS, new EClassConstraintLocator());
		addConstraintLocator(EcorePackage.Literals.ECLASSIFIER, new EClassifierConstraintLocator());
		addConstraintLocator(UMLPackage.Literals.CONSTRAINT, new UMLConstraintLocator());
		addConstraintLocator(PivotPackage.Literals.CONSTRAINT, new PivotConstraintLocator());
	}
	
	private class ContentNode implements Comparable<ContentNode>
	{
		private final @Nullable ContentNode parent;
		private final @NonNull Notifier target;
		private final @NonNull String text;
		private @Nullable List<ContentNode> children = null;

		public ContentNode(@NonNull ResourceSet target) {
			this.parent = null;
			this.target = target;
			this.text = labelProvider.getText(target);
			contentMap.put(target, this);
		}

		private ContentNode(@NonNull ContentNode parentNode, @NonNull Notifier target) {
			this.parent = parentNode;
			this.target = target;
			this.text = labelProvider.getText(target);
			parent.addChild(this);
			contentMap.put(target, this);
		}

		private void addChild(@NonNull ContentNode childNode) {
			if (children == null) {
				children = new ArrayList<ContentNode>();
			}
			children.add(childNode);
		}

		public int compareTo(ContentNode o) {
			return text.compareTo(o.text);
		}

		public @NonNull ContentNode createChildNode(@NonNull EObject eObject) {
			return new ContentNode(this, eObject);
		}

		public void sort() {
			if (children != null) {
				Collections.sort(children);
				for (ContentNode child : children) {
					child.sort();
				}
			}
		}
	}
	
	private final @NonNull AdapterFactoryLabelProvider labelProvider;
	private Set<MetaModelManager> myMetaModelManagers = null;
	private MetaModelManager metaModelManager = null;
	private ResourceSet selectedResourceSet = null;
	private Resource selectedResource = null;
	private EObject selectedObject = null;
	private final @NonNull List<Resource> resources = new ArrayList<Resource>();
	private Map<Notifier,ContentNode> contentMap = new HashMap<Notifier,ContentNode>();
	private Map<EClass, List<ConstraintLocator>> constraintLocators = new HashMap<EClass, List<ConstraintLocator>>();
	private ContentNode rootNode = null;

	public ConstraintContentProvider(@NonNull AdapterFactoryLabelProvider labelProvider) {
		this.labelProvider = labelProvider;
	}

	public void dispose() {
		System.out.println("dispose ");
		metaModelManager = null;
		selectedResourceSet = null;
		selectedResource = null;
		selectedObject = null;
		resources.clear();
		if (myMetaModelManagers != null) {
			for (MetaModelManager metaModelManager : myMetaModelManagers) {
				metaModelManager.dispose();
			}
			myMetaModelManagers = null;
		}
	}

	public Object[] getElements(Object inputElement) {
		System.out.println("getElements " + inputElement);
/*		ContentNode contentNode = contentMap.get(inputElement);
		if (contentNode == null) {
			return null;
		}
		else {
			return contentNode.children.toArray(new ContentNode[contentNode.children.size()]);
		} */
		if (inputElement != null) {
			return getChildren(inputElement);
		}
		else {
			return NO_OBJECTS;
		}
	}

	public Object[] getChildren(Object parentElement) {
		System.out.println("getChildren " + parentElement);
		ContentNode contentNode = contentMap.get(parentElement);
		if (contentNode == null) {
			return NO_OBJECTS;
		}
		else if (contentNode.children == null) {
			return NO_OBJECTS;
		}
		else {
			int iMax = contentNode.children.size();
			Notifier[] children = new Notifier[iMax];
			for (int i = 0; i < contentNode.children.size(); i++) {
				children[i] = contentNode.children.get(i).target;
			}
			return children;
		}
	}

	public Object getParent(Object element) {
		System.out.println("getParent " + element);
		ContentNode contentNode = contentMap.get(element);
		if (contentNode == null) {
			return null;
		}
		else {
			return contentNode.parent.target;
		}
	}

	public boolean hasChildren(Object element) {
		System.out.println("hasChildren " + element);
		ContentNode contentNode = contentMap.get(element);
		if (contentNode == null) {
			return false;
		}
		else if (contentNode.children == null) {
			return false;
		}
		else {
			return contentNode.children.size() > 0;
		}
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		System.out.println("inputChanged " + newInput);
		if (newInput == oldInput) {
			return;
		}
		contentMap.clear();
		constraintLocators.clear();
		metaModelManager = null;
		selectedResourceSet = null;
		selectedResource = null;
		selectedObject = null;
		resources.clear();
		
		if (newInput instanceof ResourceSet) {
			selectedResourceSet = (ResourceSet) newInput;
			metaModelManager = PivotUtil.findMetaModelManager(selectedResourceSet);
			if (metaModelManager == null) {
				metaModelManager = new MetaModelManager();
				MetaModelManagerResourceSetAdapter.getAdapter(selectedResourceSet, metaModelManager);
				if (myMetaModelManagers == null) {
					myMetaModelManagers = new HashSet<MetaModelManager>();
				}
				myMetaModelManagers.add(metaModelManager);
			}
			resources.addAll(selectedResourceSet.getResources());
		}
		else if (newInput instanceof Resource) {
			selectedResource = (Resource) newInput;
			selectedResourceSet = selectedResource.getResourceSet();
			if (selectedResourceSet == null) {
				resources.add(selectedResource);
			}
			metaModelManager = PivotUtil.findMetaModelManager(selectedResource);			
		}
		else if (newInput instanceof EObject) {
			selectedObject = (EObject) newInput;
			selectedResource = selectedObject.eResource();
			selectedResourceSet = selectedResource != null ? selectedResource.getResourceSet() : null;
			metaModelManager = PivotUtil.findMetaModelManager(selectedObject);			
		}
		if (selectedResourceSet != null) {
			resources.addAll(selectedResourceSet.getResources());
		}
		if (resources.isEmpty()) {
			return;
		}
		rootNode = new ContentNode(selectedResourceSet);
		Set<EClass> eClasses = new HashSet<EClass>();
		for (Resource resource : resources) {
			for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
				EObject eObject = tit.next();
				EClass eClass = eObject.eClass();
				eClasses.add(eClass);
			}
		}
		for (EClass eClass : eClasses) {
			Set<Constraint> allConstraints = null;
			List<ConstraintLocator> list = getConstraintLocators(eClass.eClass());
			if (list != null) {
				for (ConstraintLocator constraintLocator : list) {
					try {
						Iterable<Constraint> someConstraints = constraintLocator.getConstraints(metaModelManager, eClass);
						if (someConstraints != null) {
							if (allConstraints == null) {
								allConstraints = new HashSet<Constraint>();
							}
							for (Constraint constraint : someConstraints) {
								allConstraints.add(constraint);
							}
						}
					}
					catch (Exception e) {
					}
				}
			}
/*			if (allConstraints == null) {
				EClass eMetaClass = eClass.eClass();
				List<ConstraintLocator> metaList = getConstraintLocators(eMetaClass);
				if (metaList != null) {
					for (ConstraintLocator constraintLocator : metaList) {
						try {
							Iterable<Constraint> someConstraints = constraintLocator.getConstraints(metaModelManager, eClass);
							if (someConstraints != null) {
								if (allConstraints == null) {
									allConstraints = new HashSet<Constraint>();
								}
								for (Constraint constraint : someConstraints) {
									if ("validateCompatibleInitialiser".equals(constraint.getName())) {
										allConstraints.add(constraint);
									}
									else {
										allConstraints.add(constraint);
									}
								}
							}
						}
						catch (Exception e) {
						}
					}
				}
			} */
			if (allConstraints != null) {
				for (Constraint constraint : allConstraints) {
					List<Element> constrainedElement = constraint.getConstrainedElement();
					if (constrainedElement.size() > 0) {
						for (Element element : constrainedElement) {
							ContentNode parentNode = getContentNode(element);
							parentNode.createChildNode(constraint);
						}
					}
					else {
						ContentNode parentNode = getContentNode(eClass);
						parentNode.createChildNode(constraint);
					}
				}
			}
		}
		rootNode.sort();
	}

	private @NonNull ContentNode getContentNode(@NonNull EObject eObject) {
		ContentNode contentNode = contentMap.get(eObject);
		if (contentNode == null) {
			ContentNode parentNode;
			EObject eContainer = eObject.eContainer();
			if (eContainer != null) {
				parentNode = getContentNode(eContainer);
			}
			else {
				parentNode = getContentNode(eObject.eResource());
			}
			contentNode = parentNode.createChildNode(eObject);
			System.out.println("ContentNode for " + eObject);
		}
		return contentNode;
	}

	private @NonNull ContentNode getContentNode(@NonNull Resource resource) {
		ContentNode contentNode = contentMap.get(resource);
		if (contentNode == null) {
			contentNode = new ContentNode(rootNode, resource);
			System.out.println("ContentNode for " + resource);
		}
		return contentNode;
	}

	private @Nullable List<ConstraintLocator> getConstraintLocators(@NonNull EClass eClass) {
		List<ConstraintLocator> list = constraintLocators.get(eClass);
		if (list != null) {
			return list;
		}
		if (constraintLocators.containsKey(eClass)) {
			return null;
		}
		Set<ConstraintLocator> set = null;
		list = globalConstraintLocators.get(eClass);
		if (list != null) {
			if (set == null) {
				set = new HashSet<ConstraintLocator>();
			}
			set.addAll(list);
		}
		for (EClass eSuperClass : eClass.getESuperTypes()) {
			List<ConstraintLocator> superList = getConstraintLocators(eSuperClass);
			if (superList != null) {
				if (set == null) {
					set = new HashSet<ConstraintLocator>();
				}
				set.addAll(superList);
			}
		}
		if (set != null) {
			list = new ArrayList<ConstraintLocator>(set);
		}
		constraintLocators.put(eClass, list);
		return list;
	}
}
