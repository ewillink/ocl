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
 *  Obeo - Optimize View Input Refresh
 * </copyright>
 */
package org.eclipse.ocl.examples.emf.validation.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.ocl.examples.emf.validation.debug.locator.ConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Result;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ResultConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ResultSet;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ResultValidatableNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ValidatableNode;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class ValidityManager
{	
	private static final @NonNull Map<String, List<ConstraintLocator.Descriptor>> constraintLocatorDescriptors = new HashMap<String, List<ConstraintLocator.Descriptor>>();
	private static final @NonNull Map<String, List<ConstraintLocator>> constraintLocators = new HashMap<String, List<ConstraintLocator>>();


	private final @NonNull Set<Resource> newResources = new HashSet<Resource>();

	private final @NonNull Set<Resource> oldResources = new HashSet<Resource>();
	
	/**
	 * This add the corresponding constraint locator if it exists in the list of
	 * defined descriptors.
	 * 
	 * @param nsURI
	 *            the nsURI of the validated resource
	 * @param constraintLocator
	 *            the corresponding constraint locator
	 */
	public static synchronized void addConstraintLocator(/*@NonNull*/ String nsURI, @NonNull ConstraintLocator.Descriptor constraintLocator) {
		List<ConstraintLocator.Descriptor> list = constraintLocatorDescriptors.get(nsURI);
		if (list == null) {
			list = new ArrayList<ConstraintLocator.Descriptor>();
			constraintLocatorDescriptors.put(nsURI, list);
		}
		if (!list.contains(constraintLocator)) {
			list.add(constraintLocator);
			constraintLocators.remove(nsURI);
		}
	}

	public static synchronized @Nullable ConstraintLocator getConstraintLocator(@NonNull EObject eObject) {
		return getConstraintLocator(eObject.eResource());
	}

	public static synchronized @Nullable ConstraintLocator getConstraintLocator(@Nullable Resource resource) {
		if (resource != null) {
			for (EObject eObject : resource.getContents()) {
				EClass eClass = eObject.eClass();
				if (eClass != null) {
					EPackage ePackage = eClass.getEPackage();
					if (ePackage != null) {
						String nsURI = ePackage.getNsURI();
						if (nsURI != null) {
							List<ConstraintLocator> list = getConstraintLocators(nsURI);
							if ((list != null) && (list.size() > 0)) {
								return list.get(0);
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static synchronized @Nullable List<ConstraintLocator> getConstraintLocators(@NonNull String nsURI) {
		List<ConstraintLocator> list = constraintLocators.get(nsURI);
		if (list == null) {
			List<ConstraintLocator.Descriptor> descriptors = constraintLocatorDescriptors.get(nsURI);
			if (descriptors == null) {
				return null;
			}
			list = new ArrayList<ConstraintLocator>();
			constraintLocators.put(nsURI, list);
			for (ConstraintLocator.Descriptor descriptor : descriptors) {
				list.add(descriptor.getConstraintLocator());
			}
		}
		return list;
	}
	
	protected final @NonNull ComposedAdapterFactory adapterFactory;
	protected final @NonNull Map<ResultValidatableNode, Result> resultsMap = new HashMap<ResultValidatableNode, Result>();
	protected final @SuppressWarnings("null")@NonNull Map<Object, Object> context = Diagnostician.INSTANCE.createDefaultContext();
	private @Nullable ValidityModel model = null;
	protected @Nullable ResultSet lastResultSet = null;

	public ValidityManager() {
	    adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
	    adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
	    adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
	    adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
	}

	public @NonNull Map<Object, Object> createDefaultContext() {
		return context;
	}

	@SuppressWarnings("null")
	public @NonNull BasicDiagnostic createDefaultDiagnostic(@NonNull EObject eObject) {
		return Diagnostician.INSTANCE.createDefaultDiagnostic(eObject);
	}

	protected @NonNull ValidityModel createModel(@NonNull Set<Resource> newResources) {
		return new ValidityModel(this, newResources);
	}

	public /*synchronized*/ @Nullable ResultSet createResultSet(@Nullable IProgressMonitor monitor) {
		ValidityModel model2 = model;
		return model2 != null ? model2.createResultSet(monitor) : null;
	}

	public void dispose() {
		model = null;
		lastResultSet = null;
		resultsMap.clear();
	}
	
	protected @Nullable Set<ConstraintLocator> gatherConstraintLocators(@Nullable Set<ConstraintLocator> set, @NonNull List<ConstraintLocator> list) {
		if (set == null) {
			set = new HashSet<ConstraintLocator>();
		}
		set.addAll(list);
		return set;
	}

	public @NonNull AdapterFactory getAdapterFactory() {
		return adapterFactory;
	}

	/**
	 * Return the ConstrainingNode node for EObject creating any ConstrainingNodes that
	 * are required to ensure that the returned ConstrainingNode is installed in the root.
	 */
	public @NonNull ConstrainingNode getConstrainingNode(@NonNull EObject eObject) {
		ValidityModel model2 = model;
		if (model2 == null) {
			throw new IllegalStateException();
		}
		return model2.getConstrainingNode(eObject);
	}

	public List<Result> getConstrainingNodeResults(@NonNull ConstrainingNode element) {
		List<Result> results = new ArrayList<Result>();
		if (element.getLabel().startsWith("EOperation")) {
			getAllConstrainingNodeResults(results, element);
		}
		else {
			getAllConstrainingNodeResults(results, element);
		}
		return results;
	}

	private void getAllConstrainingNodeResults(List<Result> results, @NonNull ConstrainingNode element) {
		if (element instanceof ResultConstrainingNode) {
			ResultValidatableNode resultValidatableNode = ((ResultConstrainingNode)element).getResultValidatableNode();
			Result result = resultsMap.get(resultValidatableNode);
			if (result != null) {
				results.add(result);
			}
		}
		else {
			for (@SuppressWarnings("null")@NonNull ConstrainingNode child : element.getChildren()) {
				getAllConstrainingNodeResults(results, child);
			}
		}
	}

	private void getAllValidatableNodeResults(List<Result> results, @NonNull ValidatableNode element) {
		if (element instanceof ResultValidatableNode) {
			ResultValidatableNode resultValidatableNode = (ResultValidatableNode)element;
			Result result = resultsMap.get(resultValidatableNode);
			if (result != null) {
				results.add(result);
			}
		}
		else {
			for (@SuppressWarnings("null")@NonNull ValidatableNode child : element.getChildren()) {
				getAllValidatableNodeResults(results, child);
			}
		}
	}

	public @NonNull String getLabel(@NonNull EObject eObject) {
	    IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(eObject, IItemLabelProvider.class);
		String label = itemLabelProvider != null ? itemLabelProvider.getText(eObject) : eObject.toString();
		return label != null ? label : "";
	}

	public @Nullable RootNode getRootNode() {
		ValidityModel model2 = model;
		return model2 != null ? model2.getRootNode() : null;
	}
	
	public @Nullable ValidityModel getModel() {
		return model;
	}

	public List<Result> getValidatableNodeResults(@NonNull ValidatableNode element) {
		List<Result> results = new ArrayList<Result>();
		getAllValidatableNodeResults(results, element);
		return results;
	}

	public void setInput(Object newInput) {
		ResourceSet selectedResourceSet = null;
		Resource selectedResource = null;
		EObject selectedObject = null;
		newResources.clear();
		
		if(newInput == null){
			oldResources.removeAll(oldResources);
			model = null;
			return;
		}
		
		if (newInput instanceof ResourceSet) {
			selectedResourceSet = (ResourceSet) newInput;
			newResources.addAll(selectedResourceSet.getResources());
		} else if (newInput instanceof Resource) {
			selectedResource = (Resource) newInput;
			selectedResourceSet = selectedResource.getResourceSet();
			if (selectedResourceSet == null) {
				newResources.add(selectedResource);
			}
		} else if (newInput instanceof EObject) {
			selectedObject = (EObject) newInput;
			selectedResource = selectedObject.eResource();
			selectedResourceSet = selectedResource != null ? selectedResource.getResourceSet() : null;
		}

		if (selectedResourceSet != null) {
			newResources.addAll(selectedResourceSet.getResources());
		}
		
		if (newResources.isEmpty()) {
			return;
		}
		
		if (!oldResources.isEmpty() && oldResources.equals(newResources)) {
			return;
		}

		ValidityModel model2 = model = createModel(newResources);
		model2.init();

		oldResources.clear();
		oldResources.addAll(newResources);
	}
}
