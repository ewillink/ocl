/**
 * <copyright>
 * </copyright>
 *
 * $Id: OCLMergedLibraryFactoryImpl.java,v 1.1.2.4 2010/01/31 22:23:46 ewillink Exp $
 */
package org.eclipse.ocl.library.merged.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.ocl.library.merged.*;
import org.eclipse.ocl.library.merged.MergedLibraryOperation;
import org.eclipse.ocl.library.merged.MergedLibraryProperty;
import org.eclipse.ocl.library.merged.MergedMetaModelOperation;
import org.eclipse.ocl.library.merged.MergedMetaModelProperty;
import org.eclipse.ocl.library.merged.MergedOperationDefinition;
import org.eclipse.ocl.library.merged.MergedPropertyDefinition;
import org.eclipse.ocl.library.merged.MergedType;
import org.eclipse.ocl.library.merged.OCLBoundCollectionType;
import org.eclipse.ocl.library.merged.OCLMergedLibraryFactory;
import org.eclipse.ocl.library.merged.OCLMergedLibraryPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * @since 3.0
 * <!-- end-user-doc -->
 * @generated
 */
public class OCLMergedLibraryFactoryImpl extends EFactoryImpl implements OCLMergedLibraryFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OCLMergedLibraryFactory init() {
		try {
			OCLMergedLibraryFactory theOCLMergedLibraryFactory = (OCLMergedLibraryFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/ocl/3.0.0/OCL/MergedLibrary"); //$NON-NLS-1$ 
			if (theOCLMergedLibraryFactory != null) {
				return theOCLMergedLibraryFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new OCLMergedLibraryFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OCLMergedLibraryFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case OCLMergedLibraryPackage.MERGED_LIBRARY_ITERATION: return createMergedLibraryIteration();
			case OCLMergedLibraryPackage.MERGED_LIBRARY_OPERATION: return createMergedLibraryOperation();
			case OCLMergedLibraryPackage.MERGED_LIBRARY_PROPERTY: return createMergedLibraryProperty();
			case OCLMergedLibraryPackage.MERGED_META_MODEL_OPERATION: return createMergedMetaModelOperation();
			case OCLMergedLibraryPackage.MERGED_META_MODEL_PROPERTY: return createMergedMetaModelProperty();
			case OCLMergedLibraryPackage.MERGED_OPERATION_DEFINITION: return createMergedOperationDefinition();
			case OCLMergedLibraryPackage.MERGED_PROPERTY_DEFINITION: return createMergedPropertyDefinition();
			case OCLMergedLibraryPackage.MERGED_TYPE: return createMergedType();
			case OCLMergedLibraryPackage.OCL_BOUND_COLLECTION_TYPE: return createOCLBoundCollectionType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MergedLibraryIteration createMergedLibraryIteration() {
		MergedLibraryIterationImpl mergedLibraryIteration = new MergedLibraryIterationImpl();
		return mergedLibraryIteration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MergedLibraryOperation createMergedLibraryOperation() {
		MergedLibraryOperationImpl mergedLibraryOperation = new MergedLibraryOperationImpl();
		return mergedLibraryOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MergedLibraryProperty createMergedLibraryProperty() {
		MergedLibraryPropertyImpl mergedLibraryProperty = new MergedLibraryPropertyImpl();
		return mergedLibraryProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MergedMetaModelOperation createMergedMetaModelOperation() {
		MergedMetaModelOperationImpl mergedMetaModelOperation = new MergedMetaModelOperationImpl();
		return mergedMetaModelOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MergedMetaModelProperty createMergedMetaModelProperty() {
		MergedMetaModelPropertyImpl mergedMetaModelProperty = new MergedMetaModelPropertyImpl();
		return mergedMetaModelProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MergedOperationDefinition createMergedOperationDefinition() {
		MergedOperationDefinitionImpl mergedOperationDefinition = new MergedOperationDefinitionImpl();
		return mergedOperationDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MergedPropertyDefinition createMergedPropertyDefinition() {
		MergedPropertyDefinitionImpl mergedPropertyDefinition = new MergedPropertyDefinitionImpl();
		return mergedPropertyDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MergedType createMergedType() {
		MergedTypeImpl mergedType = new MergedTypeImpl();
		return mergedType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OCLBoundCollectionType createOCLBoundCollectionType() {
		OCLBoundCollectionTypeImpl oclBoundCollectionType = new OCLBoundCollectionTypeImpl();
		return oclBoundCollectionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OCLMergedLibraryPackage getOCLMergedLibraryPackage() {
		return (OCLMergedLibraryPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static OCLMergedLibraryPackage getPackage() {
		return OCLMergedLibraryPackage.eINSTANCE;
	}

} //OCLMergedLibraryFactoryImpl