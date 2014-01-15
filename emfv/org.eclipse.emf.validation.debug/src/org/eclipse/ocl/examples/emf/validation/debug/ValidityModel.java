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
 *  Obeo - Manage the Navigation from the ValidityView -> to the Editor
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.emf.validation.debug;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ocl.examples.emf.validation.debug.locator.ConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Result;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ResultConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ResultSet;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ResultValidatableNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.RootValidatableNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Severity;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ValidatableNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityFactory;
import org.eclipse.ocl.examples.emf.validation.debug.validity.markers.GoToConstrainingMarker;
import org.eclipse.ocl.examples.emf.validation.debug.validity.markers.GoToModelElementMarker;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class ValidityModel
{
	private static final Logger logger = Logger.getLogger(ValidityManager.class);
	private static Comparator<AbstractNode> natureComparator = new Comparator<AbstractNode>()
	{
		public int compare(AbstractNode o1, AbstractNode o2) {
			if (o1.eClass() == o2.eClass()) {
				return o1.getLabel().compareTo(o2.getLabel());
			}
			String l1 = o1.eClass().getName();
			String l2 = o2.eClass().getName();
			return l1.compareTo(l2);
		}
	};
	private static @Nullable Set<ConstraintLocator> badConstraintLocators = null;
	
	protected final @NonNull ValidityManager validityManager;
	private final @SuppressWarnings("null")@NonNull RootNode rootNode = ValidityFactory.eINSTANCE.createRootNode();
	private final @NonNull Map<URI, ConstrainingNode> allConstrainingNodes = new HashMap<URI, ConstrainingNode>();
	private final @NonNull Map<EObject, ValidatableNode> allValidatableNodes = new HashMap<EObject, ValidatableNode>();
	
	private final @NonNull Map<EObject, GoToModelElementMarker> allModelElementsMarkers = new HashMap<EObject, GoToModelElementMarker>();
	private final @NonNull Map<ValidatableNode, GoToModelElementMarker>  allValidatableNodesMarkers = new HashMap<ValidatableNode, GoToModelElementMarker>();
	
	private final @NonNull Map<EModelElement, Set<URI>> typeClosures = new HashMap<EModelElement, Set<URI>>();
	private final @NonNull Set<Resource> resources;
	
	/**
	 * The Constructor.
	 * 
	 * @param validityManager
	 *            The ValidityManager
	 * @param newResources
	 *            All resources found in the resourceSet
	 */
	public ValidityModel(@NonNull ValidityManager validityManager, @NonNull Set<Resource> newResources) {
		this.validityManager = validityManager;
		this.resources = newResources;
	}

	/**
	 * Looks for all EPackages in the source Resources.
	 * 
	 * @param resources
	 *            the Collection of all resources in the resourceSet
	 * @return a map containing all EPackages of all resources
	 */
	protected @NonNull Map<EPackage,Set<Resource>> analyzeResources(@NonNull Collection<Resource> resources) {
		List<Resource> allResources = new ArrayList<Resource>(resources);
		Map<EPackage,Set<Resource>> ePackage2resources = new HashMap<EPackage,Set<Resource>>();
		for (int i = 0; i < allResources.size(); i++) {
			Resource resource = allResources.get(i);
			Set<EClass> eClasses = new HashSet<EClass>();
			for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
				@SuppressWarnings("null")@NonNull EObject eObject = tit.next();
				@SuppressWarnings("null")@NonNull EClass eClass = eObject.eClass();
				eClasses.add(eClass);
			}
			Set<EPackage> ePackages = new HashSet<EPackage>();
			for (@SuppressWarnings("null")@NonNull EClass eClass : eClasses) {
				ePackages.add(eClass.getEPackage());
				for (@SuppressWarnings("null")@NonNull EClass eSuperClass : eClass.getEAllSuperTypes()) {
					ePackages.add(eSuperClass.getEPackage());
				}
			}
			for (@SuppressWarnings("null")@NonNull EPackage ePackage : ePackages) {
				Set<Resource> ePackageResources = ePackage2resources.get(ePackage);
				if (ePackageResources == null) {
					ePackageResources = new HashSet<Resource>();
					ePackage2resources.put(ePackage, ePackageResources);
				}
				ePackageResources.add(resource);
				String nsURI = ePackage.getNsURI();
				if (nsURI !=null){
					List<ConstraintLocator> list = ValidityManager.getConstraintLocators(nsURI);
					if (list != null) {
						for (ConstraintLocator constraintLocator : list) {
							try {
								Collection<Resource> moreResources = constraintLocator.getImports(ePackage, resource);
								if (moreResources != null) {
									for (Resource anotherResource : moreResources) {
										if (!allResources.contains(anotherResource)) {
											allResources.add(anotherResource);
										}
									}
								}
							}
							catch (Exception e) {
								Set<ConstraintLocator> badConstraintLocators2 = badConstraintLocators;
								if (badConstraintLocators2 == null) {
									synchronized (this) {
										badConstraintLocators = badConstraintLocators2 = new HashSet<ConstraintLocator>();
									}
								}
								if (!badConstraintLocators2.contains(constraintLocator)) {
									synchronized (badConstraintLocators2) {
										if (badConstraintLocators2.add(constraintLocator)) {
											logger.error("ConstraintLocator " + constraintLocator + " failed", e);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return ePackage2resources;
	}

	/**
	 * Creates a ConstrainingNode.
	 * 
	 * @return the created ConstrainingNode
	 */
	@SuppressWarnings("null")
	protected @NonNull ConstrainingNode createConstrainingNode() {
		return ValidityFactory.eINSTANCE.createConstrainingNode();
	}

	/**
	 * creates a LeafConstrainingNode
	 * 
	 * @return the created LeafConstrainingNode
	 */
	@SuppressWarnings("null")
	public @NonNull LeafConstrainingNode createLeafConstrainingNode() {
		return ValidityFactory.eINSTANCE.createLeafConstrainingNode();
	}

	/**
	 * Create the child LeafConstrainingNodes for each EClass
	 * 
	 * @param allConstraints
	 *            the map of all model elements and their LeafConstrainingNodes
	 */
	protected void createLeafConstrainingNodes(@NonNull Map<EModelElement, Set<LeafConstrainingNode>> allConstraints) {
		for (@SuppressWarnings("null")@NonNull EModelElement constrainedType : allConstraints.keySet()) {
			ConstrainingNode classConstrainingNode = getConstrainingNode(constrainedType);
			List<ConstrainingNode> children = classConstrainingNode.getChildren();
				Set<LeafConstrainingNode> someConstraints = allConstraints.get(constrainedType);
				if (someConstraints != null) {
					children.addAll(someConstraints);
				}
		}
	}

	/**
	 * Return a new Result object, or return null if the creation process is to be aborted.
	 * <p>
	 * The default implementation always return an object. Derived implementations may cancel
	 * in response to a progress monitor request.
	 * 
	 * @param monitor the corresponding monitor
	 * @return the created new Result object
	 */
	protected @Nullable Result createResult(@Nullable IProgressMonitor monitor) {
		return ValidityFactory.eINSTANCE.createResult();
	}	

	/**
	 * Creates a ResultConstrainingNode.
	 * 
	 * @return the created ResultConstrainingNode
	 */
	@SuppressWarnings("null")
	protected @NonNull ResultConstrainingNode createResultConstrainingNode() {
		return ValidityFactory.eINSTANCE.createResultConstrainingNode();
	}

	/**
	 * Create the ResultValidatableNode,ResultConstrainingNode cross-linkage
	 * between constrainedObject and each child constraint of constrainingType.
	 * 
	 * @param constrainedObject
	 *            the constraining object
	 * @param constrainingType
	 *            the uri of the constrainingNode
	 */
	protected void createResultNodes(@NonNull EObject constrainedObject, @NonNull URI constrainingType) {
		Set<ConstrainingNode> constrainingNodes = getConstrainedNodesForURI(constrainingType);
		for (ConstrainingNode constrainingNode : constrainingNodes) {
			if (constrainingNode != null){
				List<ConstrainingNode> children = constrainingNode.getChildren();
				if (children.size() > 0) {
					ValidatableNode validatable = getValidatableNode(constrainedObject);
					for (@SuppressWarnings("null")@NonNull ConstrainingNode leafConstrainingNode : children) {
						ResultConstrainingNode resultConstrainingNode = createResultConstrainingNode();
						ResultValidatableNode resultValidatableNode = createResultValidatableNode();
						resultConstrainingNode.setResultValidatableNode(resultValidatableNode);
						resultConstrainingNode.setLabel(validatable.getLabel());
						resultValidatableNode.setLabel(leafConstrainingNode.getLabel());
						leafConstrainingNode.getChildren().add(resultConstrainingNode);
						validatable.getChildren().add(resultValidatableNode);
					}
				}
			}
		}
	}

	/**
	 * Returns the set of corresponding constraining nodes.
	 * 
	 * @param constrainingType
	 *            the uri of the constrainingNode
	 * @return the set of corresponding constraining nodes
	 */
	private Set<ConstrainingNode> getConstrainedNodesForURI(@NonNull URI constrainingType) {
		Set<ConstrainingNode> constrainingNodes = new LinkedHashSet<ConstrainingNode>();
		for (Map.Entry<URI, ConstrainingNode> entry : allConstrainingNodes.entrySet()) {
			if (entry.getKey().toString().startsWith(constrainingType.toString())) {
				constrainingNodes.add(entry.getValue());
			}
		}
		return constrainingNodes;
	}

	/**
	 * Creates a ResultSet.
	 * 
	 * @return the created ResultSet
	 */
	@SuppressWarnings("null")
	protected @NonNull ResultSet createResultSet() {
		return ValidityFactory.eINSTANCE.createResultSet();
	}

	/**
	 * Return a new ResultSet object containing an initial result for every
	 * enabled combination of ValidatableNode and ConstrainingNode. Returns null
	 * if the creation process was aborted.
	 * 
	 * @param monitor
	 *            the corresponding monitor
	 * @return the ResultSet
	 */
	public /*synchronized*/ @Nullable ResultSet createResultSet(@Nullable IProgressMonitor monitor) {
		ResultSet resultSet = createResultSet();
		List<Result> results = resultSet.getResults();
		if (!createResults(results, rootNode.getValidatableNodes(), monitor)) {
			return null;
		}
		else {
			rootNode.getResultSets().add(resultSet);
			return resultSet;
		}
	}

	/**
	 * Creates a ResultValidatableNode
	 * 
	 * @return the created ResultValidatableNode
	 */
	@SuppressWarnings("null")
	protected @NonNull ResultValidatableNode createResultValidatableNode() {
		return ValidityFactory.eINSTANCE.createResultValidatableNode();
	}

	/**
	 * Created Results of all validatableNodes.
	 * 
	 * @param results
	 *            the created results
	 * @param validatableNodes
	 *            the validatableNodes
	 * @param monitor
	 *            the corresponding monitor
	 * @return true if the results are created well, false otherwise
	 */
	protected boolean createResults(@NonNull List<Result> results, @NonNull Iterable<? extends ValidatableNode> validatableNodes, @Nullable IProgressMonitor monitor) {
		for (ValidatableNode validatable : validatableNodes) {
			AbstractNode parent = validatable.getParent();
			if (validatable.isEnabled() && (parent == null || parent.isEnabled())) {
				if (validatable instanceof ResultValidatableNode) {
					ResultValidatableNode resultValidatableNode = (ResultValidatableNode) validatable;
					LeafConstrainingNode constraint = (LeafConstrainingNode) resultValidatableNode.getResultConstrainingNode().getParent();
					Result result = createResult(monitor);
					if (result == null) {
						return false;
					}
					result.setResultValidatableNode(resultValidatableNode);
					
					ResultConstrainingNode resultConstrainingNode = resultValidatableNode.getResultConstrainingNode();
					
					if (!constraint.isEnabled() || !resultConstrainingNode.isEnabled()) {
						result.setSeverity(Severity.UNKNOWN);
					} else {
						results.add(result);
					}
				}
			}
			if (!createResults(results, validatable.getChildren(), monitor)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Create the ResultValidatableNode,ResultConstrainingNode cross-linkage for
	 * all validateableObject,constraint pairs.
	 * 
	 * @param resources
	 *            the resources
	 */
	protected void createResults(@NonNull Set<Resource> resources) {
		for (Resource resource : resources) {
			IFile containingFile = findFile(resource);
			
			for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
				@SuppressWarnings("null")@NonNull EObject eObject = tit.next();
				// create a go to Marker for a each eObject
				if (containingFile != null) {
					GoToModelElementMarker gotoMarker = new GoToModelElementMarker(containingFile, eObject);
					allModelElementsMarkers.put(eObject, gotoMarker);
				}
				
				EClass eClass = eObject.eClass();
				EAnnotation eAnnotation = eClass.getEAnnotation("http://www.eclipse.org/uml2/2.0.0/UML");
				if ((eAnnotation != null) && (eAnnotation.getReferences().size() > 0)) { // Stereotype application
					EObject umlClass = eAnnotation.getReferences().get(0);
					if (umlClass != null) {
						createResultNodes(eObject, getURI(umlClass));
					}
				}
				else if (eClass != null) {
					for (@SuppressWarnings("null")@NonNull URI aType : getTypeClosure(eClass)) {
						createResultNodes(eObject, aType);
					}
				}
			}
		}
	}

	/**
	 * Returns the {@link IFile} in which the provided {@link Resource} can be
	 * found, if any.
	 * 
	 * @param resource
	 *            the {@link Resource} for which we search the enclosing file
	 * @return the corresponding {@link IFile} or <code>null</code> if one of the following occurs:
	 *         <ul>
	 *         <li>the given resource is null </li>
	 *         <li>the resource {@link URI} cannot be determined</li>
	 *         <li>the resource {@link URI} is not platform oriented</li>
	 *         <li>the project supposed to contain the resource does not actually exist</li>
	 *         <li>the project containing the resource is closed</li>
	 *         </ul>
	 * 
	 */
	private static @Nullable IFile findFile(Resource resource) {
		if (resource == null || resource.getURI() == null || !resource.getURI().isPlatform()) {
			return null;
		}
		
		IPath resourcePath = new Path(resource.getURI().toPlatformString(true));
		IFile iFile = ResourcesPlugin.getWorkspace().getRoot().getFile(resourcePath);

		IProject iProject = iFile.getProject();
		if (!iProject.isAccessible() || !iFile.exists()) {
			return null;
		}
		return iFile;
	}

	/**
	 * @return the created RootConstrainingNode
	 */
	@SuppressWarnings("null")
	protected @NonNull RootConstrainingNode createRootConstrainingNode() {
		return ValidityFactory.eINSTANCE.createRootConstrainingNode();
	}

	/**
	 * @return the created RootValidatableNode
	 */
	@SuppressWarnings("null")
	protected @NonNull RootValidatableNode createRootValidatableNode() {
		return ValidityFactory.eINSTANCE.createRootValidatableNode();
	}

	/**
	 * @return the created ValidatableNode
	 */
	@SuppressWarnings("null")
	protected @NonNull ValidatableNode createValidatableNode() {
		return ValidityFactory.eINSTANCE.createValidatableNode();
	}

	/**
	 * Return the ConstrainingNode node for EObject creating any parent
	 * ConstrainingNodes that are required to ensure that the returned
	 * ConstrainingNode is installed in the root.
	 * 
	 * @param eObject
	 * @return the ConstrainingNode node for EObject
	 */
	public @NonNull ConstrainingNode getConstrainingNode(@NonNull EObject eObject) {
		URI uri = getURI(eObject);
		URI trimmedURI = trimDuplicateContextSuffix(uri);
		
		ConstrainingNode constrainingNode = allConstrainingNodes.get(trimmedURI);
		if (constrainingNode == null) {
			EObject eContainer = eObject.eContainer();
			if (eContainer == null) {
				RootConstrainingNode rootConstrainingNode = createRootConstrainingNode();
				rootNode.getConstrainingNodes().add(rootConstrainingNode);
				constrainingNode = rootConstrainingNode;
			}
			else {
				constrainingNode = createConstrainingNode();
				ConstrainingNode parentConstrainingNode = getConstrainingNode(eContainer);
				parentConstrainingNode.getChildren().add(constrainingNode);
			}
			constrainingNode.setConstrainingObject(eObject);
			constrainingNode.setLabel(validityManager.getLabel(eObject));
			constrainingNode.setEnabled(true);
			allConstrainingNodes.put(trimmedURI, constrainingNode);
		}
		return constrainingNode;
	}
	
	/**
	 * It is possible to have multiple "identical" contexts defined in an OCL file :
	 * <p>
	 * <pre>
	 * context EClass
	 *   inv invariant1 : not name.oclIsUndefined()
	 * 
	 * context EClass
	 *   inv invariant2 : if interface then name.startsWith('I') else true endif;
	 * </pre>
	 * </p>
	 * 
	 * In such a case, the URI of the first will be <code>http://www.eclipse.org/emf/2002/Ecore#//EClass</code> while the URI of the second will be <code>http://www.eclipse.org/emf/2002/Ecore#//EClass.1</code>. We wish to "regroup" both invariants
	 * under the same context in the validity results.
	 * 
	 * @param uri
	 * @return
	 */
	private @NonNull URI trimDuplicateContextSuffix(URI uri) {
		String fragment = uri.fragment();
		// This should always be called on types, so we should be able to safely remove the trailing ".1" from the fragment
		if (fragment.matches(".*\\.[0-9]+$")){
			String trimmedFragment = fragment.replaceFirst("\\.[0-9]+$", "");
			URI trimmedURI = uri.trimFragment().appendFragment(trimmedFragment);
			assert trimmedURI != null;
			return trimmedURI;
		}
		return uri;
	}

	/**
	 * Returns the eObject label
	 * 
	 * @param eObject
	 * @return The eObject label
	 */
	public @NonNull String getLabel(@NonNull EObject eObject) {
		return validityManager.getLabel(eObject);
	}

	/**
	 * @return all resources
	 */
	public @NonNull Set<Resource> getResources() {
		return resources;
	}

	/**
	 * @return the root node
	 */
	public @NonNull RootNode getRootNode() {
		return rootNode;
	}

	/**
	 * Return all types that may provide constraints to an instance if aType.
	 * 
	 * @param aType
	 *            a modelElement
	 * @return all types that may provide constraints to an instance if aType.
	 */
	protected @NonNull Set<URI> getTypeClosure(@NonNull EModelElement aType) {
		Set<URI> typeClosure = typeClosures.get(aType);
		if (typeClosure == null) {
			typeClosure = new HashSet<URI>();
			String nsURI = aType.eClass().getEPackage().getNsURI();
			if (nsURI !=null){
				List<ConstraintLocator> constraintLocators = ValidityManager.getConstraintLocators(nsURI);
				if (constraintLocators != null) {
					for (ConstraintLocator constraintLocator : constraintLocators) {
						typeClosure.addAll(constraintLocator.getAllTypes(aType));
					}
				}
				typeClosures.put(aType, typeClosure);
			}
		}
		return typeClosure;
	}
	
	/**
	 * Returns the eObject uri
	 * 
	 * @param eObject
	 * @return the eObject uri
	 */
	private @NonNull URI getURI(@NonNull EObject eObject) {
		ConstraintLocator constraintLocator = ValidityManager.getConstraintLocator(eObject);
		if (constraintLocator != null) {
			URI uri = constraintLocator.getURI(eObject);
			if (uri != null) {
				return uri;
			}
		}
		URI uri = EcoreUtil.getURI(eObject);
		assert uri != null;
		return uri;
	}

	/**
	 * Return the GoToModelElementMarker of a ValidatableNode.
	 * 
	 * @param validatableNode
	 *            the selected ValidatableNode
	 * @return the GoToModelElementMarker of a ValidatableNode.
	 */
	public GoToModelElementMarker getModelElementMarker(@NonNull ValidatableNode validatableNode){
		return allValidatableNodesMarkers.get(validatableNode);
	}
	
	/**
	 * Return the GoToModelElementMarker of a LeafConstrainingNode.
	 * 
	 * @param leafConstrainingNode
	 *            the selected leafConstrainingNode
	 * @return the GoToModelElementMarker of a LeafConstrainingNode.
	 */
	public GoToConstrainingMarker getLeafConstrainingNodeMarker(@NonNull LeafConstrainingNode leafConstrainingNode){
		Resource resource = leafConstrainingNode.getConstraintDefinition().getResource();
		
		if (resource != null) {
			IFile file = findFile(resource);
			if (file != null) {
				return new GoToConstrainingMarker(file);
			}
		}
		return null;
	}
	
	
	/**
	 * Return the ValidatableNode node for EObject creating any ValidatableNodes
	 * that are required to ensure that the returned ValidatableNode is
	 * installed in the root.
	 * 
	 * @param eObject
	 *            the modelElement
	 * @return the ValidatableNode node for EObject
	 */
	protected @NonNull ValidatableNode getValidatableNode(@NonNull EObject eObject) {
		ValidatableNode validatable = allValidatableNodes.get(eObject);
		if (validatable == null) {
			EObject eContainer = eObject.eContainer();
			if (eContainer == null && eObject instanceof DynamicEObjectImpl) {
				EClass eClass = eObject.eClass();
				for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures()) {
					String featureName = eStructuralFeature.getName();
					if ((featureName != null) && featureName.startsWith("base_")  //org.eclipse.uml2.uml.Extension.METACLASS_ROLE_PREFIX)
					  && (eStructuralFeature instanceof EReference)
					  && eObject.eIsSet(eStructuralFeature)) { // Unset for an applicable stereotype that has not been applied
						eContainer = (EObject) eObject.eGet(eStructuralFeature);
						break;
					}
				}
			}
			if (eContainer != null) {
				validatable = createValidatableNode();
				ValidatableNode parentValidatableNode = getValidatableNode(eContainer);
				parentValidatableNode.getChildren().add(validatable);
			}
			else {
				RootValidatableNode rootValidatableNode = createRootValidatableNode();
				rootNode.getValidatableNodes().add(rootValidatableNode);
				validatable = rootValidatableNode;
			}
			validatable.setEnabled(true);
			validatable.setLabel(validityManager.getLabel(eObject));
			validatable.setConstrainedObject(eObject);
			allValidatableNodes.put(eObject, validatable);
			allValidatableNodesMarkers.put(validatable, allModelElementsMarkers.get(eObject));
		}
		return validatable;
	}

	/**
	 * Initialize the ValidityModel
	 */
	public void init() {
		Map<EPackage,Set<Resource>> ePackage2resources = analyzeResources(resources);			//	Find all EClasses and EPackages in the source Resources
		Map<EModelElement, Set<LeafConstrainingNode>> allConstraints = locateConstraints(ePackage2resources);

		if (allConstraints != null) {
			createLeafConstrainingNodes(allConstraints);
		}
		createResults(resources);
		sortNodes(rootNode.getConstrainingNodes());
		sortNodes(rootNode.getValidatableNodes());
	}

	/**
	 * Find all constraints for each EClass
	 * 
	 * @param ePackage2resources
	 *            the map of all ePackages and their resources
	 * @return all constraints for each EClass
	 */
	protected Map<EModelElement, Set<LeafConstrainingNode>> locateConstraints(@NonNull Map<EPackage,Set<Resource>> ePackage2resources) {
		Map<EModelElement, Set<LeafConstrainingNode>> allConstraints = new HashMap<EModelElement, Set<LeafConstrainingNode>>();
		for (@SuppressWarnings("null")@NonNull EPackage ePackage : ePackage2resources.keySet()) {
			String nsURI = ePackage.getNsURI();
			
			if (nsURI !=null){
				List<ConstraintLocator> list = ValidityManager.getConstraintLocators(nsURI);
				if (list != null) {
					@SuppressWarnings("null")@NonNull Set<Resource> ePackageResources = ePackage2resources.get(ePackage);
					for (ConstraintLocator constraintLocator : list) {
						try {
							Map<EModelElement, List<LeafConstrainingNode>> availableConstraints = constraintLocator.getConstraints(this, ePackage, ePackageResources);
							if (availableConstraints != null) {
								assert !availableConstraints.containsKey(null);
								for (EModelElement constrainedType : availableConstraints.keySet()) {
									Set<LeafConstrainingNode> typeConstraints = allConstraints.get(constrainedType);
									if (typeConstraints == null) {
										typeConstraints = new HashSet<LeafConstrainingNode>();
										allConstraints.put(constrainedType, typeConstraints);
									}
									typeConstraints.addAll(availableConstraints.get(constrainedType));
								}
							}
						}
						catch (Exception e) {
							Set<ConstraintLocator> badConstraintLocators2 = badConstraintLocators;
							if (badConstraintLocators2 == null) {
								synchronized (this) {
									badConstraintLocators = badConstraintLocators2 = new HashSet<ConstraintLocator>();
								}
							}
							if (!badConstraintLocators2.contains(constraintLocator)) {
								synchronized (badConstraintLocators2) {
									if (badConstraintLocators2.add(constraintLocator)) {
										logger.error("ConstraintLocator " + constraintLocator + " failed", e);
									}
								}
							}
						}
					}
				}
			}
		}
		return allConstraints;
	}
	
	/**
	 * Sorts the list.
	 * 
	 * @param nodes
	 *            the list of nodes needing to be sorted.
	 */
	protected <T extends AbstractNode> void sortEList(@NonNull EList<T> nodes) {
		List<T> sortedList = new ArrayList<T>(nodes);
		Collections.sort(sortedList, natureComparator);
		for (int i = 0; i < sortedList.size(); i++) {
			nodes.move(i, sortedList.get(i));
		}
	}

	/**
	 * Sorts the list.
	 * 
	 * @param nodes
	 *            the list of nodes needing to be sorted.
	 */
	protected <T extends AbstractNode> void sortNodes(@NonNull EList<T> nodes) {
		sortEList(nodes);
		for (AbstractNode node : nodes) {
			sortNodes(node.getChildren());
		}
	}
}