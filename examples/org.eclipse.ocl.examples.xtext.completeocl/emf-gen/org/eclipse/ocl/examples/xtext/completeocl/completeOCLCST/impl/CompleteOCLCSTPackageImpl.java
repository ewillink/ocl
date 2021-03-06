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
 * $Id: CompleteOCLCSTPackageImpl.java,v 1.10 2011/05/20 15:26:50 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.examples.pivot.PivotPackage;
import org.eclipse.ocl.examples.xtext.base.baseCST.BaseCSTPackage;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.BodyCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ClassifierContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.CompleteOCLCSTFactory;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.CompleteOCLCSTPackage;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.CompleteOCLDocumentCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ContextConstraintCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.ContextSpecificationCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.DefCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.DerCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.FeatureContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.IncludeCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.InitCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.InvCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.OclMessageArgCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.OperationContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PackageDeclarationCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PostCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PreCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PreExpCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PropertyContextDeclCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.EssentialOCLCSTPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CompleteOCLCSTPackageImpl extends EPackageImpl implements CompleteOCLCSTPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureContextDeclCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass includeCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageDeclarationCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contextDeclCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contextSpecificationCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyContextDeclCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass initCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass derCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classifierContextDeclCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass completeOCLDocumentCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contextConstraintCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass defCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationContextDeclCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass preCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass preExpCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass postCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bodyCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclMessageArgCSEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.CompleteOCLCSTPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CompleteOCLCSTPackageImpl() {
		super(eNS_URI, CompleteOCLCSTFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link CompleteOCLCSTPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CompleteOCLCSTPackage init() {
		if (isInited) return (CompleteOCLCSTPackage)EPackage.Registry.INSTANCE.getEPackage(CompleteOCLCSTPackage.eNS_URI);

		// Obtain or create and register package
		CompleteOCLCSTPackageImpl theCompleteOCLCSTPackage = (CompleteOCLCSTPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CompleteOCLCSTPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CompleteOCLCSTPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EssentialOCLCSTPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theCompleteOCLCSTPackage.createPackageContents();

		// Initialize created meta-data
		theCompleteOCLCSTPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCompleteOCLCSTPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CompleteOCLCSTPackage.eNS_URI, theCompleteOCLCSTPackage);
		return theCompleteOCLCSTPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFeatureContextDeclCS() {
		return featureContextDeclCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFeatureContextDeclCS_Class() {
		return (EReference)featureContextDeclCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getFeatureContextDeclCS_OwnedType()
	{
		return (EReference)featureContextDeclCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIncludeCS()
	{
		return includeCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIncludeCS_Namespace()
	{
		return (EReference)includeCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageDeclarationCS() {
		return packageDeclarationCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageDeclarationCS_Namespace()
	{
		return (EReference)packageDeclarationCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageDeclarationCS_Package() {
		return (EReference)packageDeclarationCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageDeclarationCS_Contexts() {
		return (EReference)packageDeclarationCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContextDeclCS() {
		return contextDeclCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContextDeclCS_Namespace()
	{
		return (EReference)contextDeclCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContextDeclCS_Rules()
	{
		return (EReference)contextDeclCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContextSpecificationCS()
	{
		return contextSpecificationCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyContextDeclCS() {
		return propertyContextDeclCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPropertyContextDeclCS_Property() {
		return (EReference)propertyContextDeclCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInitCS() {
		return initCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDerCS() {
		return derCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getClassifierContextDeclCS() {
		return classifierContextDeclCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getClassifierContextDeclCS_Classifier() {
		return (EReference)classifierContextDeclCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getClassifierContextDeclCS_SelfName() {
		return (EAttribute)classifierContextDeclCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompleteOCLDocumentCS() {
		return completeOCLDocumentCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompleteOCLDocumentCS_Packages() {
		return (EReference)completeOCLDocumentCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompleteOCLDocumentCS_Contexts() {
		return (EReference)completeOCLDocumentCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompleteOCLDocumentCS_OwnedInclude()
	{
		return (EReference)completeOCLDocumentCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContextConstraintCS()
	{
		return contextConstraintCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContextConstraintCS_ContextDecl()
	{
		return (EReference)contextConstraintCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInvCS() {
		return invCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDefCS() {
		return defCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDefCS_Static() {
		return (EAttribute)defCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDefCS_ConstrainedName() {
		return (EAttribute)defCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDefCS_Operation()
	{
		return (EAttribute)defCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDefCS_Parameters() {
		return (EReference)defCSEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDefCS_OwnedType()
	{
		return (EReference)defCSEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOperationContextDeclCS() {
		return operationContextDeclCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationContextDeclCS_Operation() {
		return (EReference)operationContextDeclCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationContextDeclCS_Parameters() {
		return (EReference)operationContextDeclCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOperationContextDeclCS_Result() {
		return (EReference)operationContextDeclCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPreCS() {
		return preCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPreExpCS()
	{
		return preExpCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPostCS() {
		return postCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBodyCS() {
		return bodyCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOclMessageArgCS() {
		return oclMessageArgCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getOclMessageArgCS_Type() {
		return (EReference)oclMessageArgCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompleteOCLCSTFactory getCompleteOCLCSTFactory() {
		return (CompleteOCLCSTFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		bodyCSEClass = createEClass(BODY_CS);

		classifierContextDeclCSEClass = createEClass(CLASSIFIER_CONTEXT_DECL_CS);
		createEReference(classifierContextDeclCSEClass, CLASSIFIER_CONTEXT_DECL_CS__CLASSIFIER);
		createEAttribute(classifierContextDeclCSEClass, CLASSIFIER_CONTEXT_DECL_CS__SELF_NAME);

		completeOCLDocumentCSEClass = createEClass(COMPLETE_OCL_DOCUMENT_CS);
		createEReference(completeOCLDocumentCSEClass, COMPLETE_OCL_DOCUMENT_CS__PACKAGES);
		createEReference(completeOCLDocumentCSEClass, COMPLETE_OCL_DOCUMENT_CS__CONTEXTS);
		createEReference(completeOCLDocumentCSEClass, COMPLETE_OCL_DOCUMENT_CS__OWNED_INCLUDE);

		contextConstraintCSEClass = createEClass(CONTEXT_CONSTRAINT_CS);
		createEReference(contextConstraintCSEClass, CONTEXT_CONSTRAINT_CS__CONTEXT_DECL);

		contextDeclCSEClass = createEClass(CONTEXT_DECL_CS);
		createEReference(contextDeclCSEClass, CONTEXT_DECL_CS__NAMESPACE);
		createEReference(contextDeclCSEClass, CONTEXT_DECL_CS__RULES);

		contextSpecificationCSEClass = createEClass(CONTEXT_SPECIFICATION_CS);

		defCSEClass = createEClass(DEF_CS);
		createEAttribute(defCSEClass, DEF_CS__STATIC);
		createEAttribute(defCSEClass, DEF_CS__CONSTRAINED_NAME);
		createEAttribute(defCSEClass, DEF_CS__OPERATION);
		createEReference(defCSEClass, DEF_CS__PARAMETERS);
		createEReference(defCSEClass, DEF_CS__OWNED_TYPE);

		derCSEClass = createEClass(DER_CS);

		featureContextDeclCSEClass = createEClass(FEATURE_CONTEXT_DECL_CS);
		createEReference(featureContextDeclCSEClass, FEATURE_CONTEXT_DECL_CS__CLASS);
		createEReference(featureContextDeclCSEClass, FEATURE_CONTEXT_DECL_CS__OWNED_TYPE);

		includeCSEClass = createEClass(INCLUDE_CS);
		createEReference(includeCSEClass, INCLUDE_CS__NAMESPACE);

		initCSEClass = createEClass(INIT_CS);

		invCSEClass = createEClass(INV_CS);

		oclMessageArgCSEClass = createEClass(OCL_MESSAGE_ARG_CS);
		createEReference(oclMessageArgCSEClass, OCL_MESSAGE_ARG_CS__TYPE);

		operationContextDeclCSEClass = createEClass(OPERATION_CONTEXT_DECL_CS);
		createEReference(operationContextDeclCSEClass, OPERATION_CONTEXT_DECL_CS__OPERATION);
		createEReference(operationContextDeclCSEClass, OPERATION_CONTEXT_DECL_CS__PARAMETERS);
		createEReference(operationContextDeclCSEClass, OPERATION_CONTEXT_DECL_CS__RESULT);

		packageDeclarationCSEClass = createEClass(PACKAGE_DECLARATION_CS);
		createEReference(packageDeclarationCSEClass, PACKAGE_DECLARATION_CS__NAMESPACE);
		createEReference(packageDeclarationCSEClass, PACKAGE_DECLARATION_CS__PACKAGE);
		createEReference(packageDeclarationCSEClass, PACKAGE_DECLARATION_CS__CONTEXTS);

		postCSEClass = createEClass(POST_CS);

		preCSEClass = createEClass(PRE_CS);

		preExpCSEClass = createEClass(PRE_EXP_CS);

		propertyContextDeclCSEClass = createEClass(PROPERTY_CONTEXT_DECL_CS);
		createEReference(propertyContextDeclCSEClass, PROPERTY_CONTEXT_DECL_CS__PROPERTY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		PivotPackage thePivotPackage = (PivotPackage)EPackage.Registry.INSTANCE.getEPackage(PivotPackage.eNS_URI);
		BaseCSTPackage theBaseCSTPackage = (BaseCSTPackage)EPackage.Registry.INSTANCE.getEPackage(BaseCSTPackage.eNS_URI);
		EssentialOCLCSTPackage theEssentialOCLCSTPackage = (EssentialOCLCSTPackage)EPackage.Registry.INSTANCE.getEPackage(EssentialOCLCSTPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		bodyCSEClass.getESuperTypes().add(this.getContextConstraintCS());
		classifierContextDeclCSEClass.getESuperTypes().add(this.getContextDeclCS());
		completeOCLDocumentCSEClass.getESuperTypes().add(theBaseCSTPackage.getRootPackageCS());
		contextConstraintCSEClass.getESuperTypes().add(theBaseCSTPackage.getConstraintCS());
		contextDeclCSEClass.getESuperTypes().add(theBaseCSTPackage.getMonikeredElementCS());
		contextSpecificationCSEClass.getESuperTypes().add(theEssentialOCLCSTPackage.getExpSpecificationCS());
		defCSEClass.getESuperTypes().add(this.getContextConstraintCS());
		derCSEClass.getESuperTypes().add(this.getContextConstraintCS());
		featureContextDeclCSEClass.getESuperTypes().add(this.getContextDeclCS());
		includeCSEClass.getESuperTypes().add(theBaseCSTPackage.getNamespaceCS());
		initCSEClass.getESuperTypes().add(this.getContextConstraintCS());
		invCSEClass.getESuperTypes().add(this.getContextConstraintCS());
		oclMessageArgCSEClass.getESuperTypes().add(theEssentialOCLCSTPackage.getExpCS());
		operationContextDeclCSEClass.getESuperTypes().add(this.getFeatureContextDeclCS());
		packageDeclarationCSEClass.getESuperTypes().add(theBaseCSTPackage.getMonikeredElementCS());
		postCSEClass.getESuperTypes().add(this.getContextConstraintCS());
		preCSEClass.getESuperTypes().add(this.getContextConstraintCS());
		preExpCSEClass.getESuperTypes().add(theEssentialOCLCSTPackage.getDecoratedNamedExpCS());
		propertyContextDeclCSEClass.getESuperTypes().add(this.getFeatureContextDeclCS());

		// Initialize classes and features; add operations and parameters
		initEClass(bodyCSEClass, BodyCS.class, "BodyCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(classifierContextDeclCSEClass, ClassifierContextDeclCS.class, "ClassifierContextDeclCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getClassifierContextDeclCS_Classifier(), thePivotPackage.getType(), null, "classifier", null, 0, 1, ClassifierContextDeclCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getClassifierContextDeclCS_SelfName(), ecorePackage.getEString(), "selfName", null, 0, 1, ClassifierContextDeclCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(completeOCLDocumentCSEClass, CompleteOCLDocumentCS.class, "CompleteOCLDocumentCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompleteOCLDocumentCS_Packages(), this.getPackageDeclarationCS(), null, "packages", null, 0, -1, CompleteOCLDocumentCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompleteOCLDocumentCS_Contexts(), this.getContextDeclCS(), null, "contexts", null, 0, -1, CompleteOCLDocumentCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCompleteOCLDocumentCS_OwnedInclude(), this.getIncludeCS(), null, "ownedInclude", null, 0, -1, CompleteOCLDocumentCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contextConstraintCSEClass, ContextConstraintCS.class, "ContextConstraintCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContextConstraintCS_ContextDecl(), this.getContextDeclCS(), this.getContextDeclCS_Rules(), "contextDecl", null, 0, 1, ContextConstraintCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contextDeclCSEClass, ContextDeclCS.class, "ContextDeclCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContextDeclCS_Namespace(), thePivotPackage.getNamespace(), null, "namespace", null, 0, -1, ContextDeclCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContextDeclCS_Rules(), this.getContextConstraintCS(), this.getContextConstraintCS_ContextDecl(), "rules", null, 0, -1, ContextDeclCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contextSpecificationCSEClass, ContextSpecificationCS.class, "ContextSpecificationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(defCSEClass, DefCS.class, "DefCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDefCS_Static(), ecorePackage.getEBoolean(), "static", null, 0, 1, DefCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefCS_ConstrainedName(), ecorePackage.getEString(), "constrainedName", null, 0, 1, DefCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDefCS_Operation(), ecorePackage.getEBoolean(), "operation", null, 0, 1, DefCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDefCS_Parameters(), theEssentialOCLCSTPackage.getVariableCS(), null, "parameters", null, 0, -1, DefCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDefCS_OwnedType(), theBaseCSTPackage.getTypedRefCS(), null, "ownedType", null, 0, 1, DefCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(derCSEClass, DerCS.class, "DerCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(featureContextDeclCSEClass, FeatureContextDeclCS.class, "FeatureContextDeclCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFeatureContextDeclCS_Class(), theBaseCSTPackage.getClassCS(), null, "class", null, 0, 1, FeatureContextDeclCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeatureContextDeclCS_OwnedType(), theBaseCSTPackage.getTypedRefCS(), null, "ownedType", null, 0, 1, FeatureContextDeclCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(includeCSEClass, IncludeCS.class, "IncludeCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIncludeCS_Namespace(), thePivotPackage.getNamespace(), null, "namespace", null, 0, 1, IncludeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(initCSEClass, InitCS.class, "InitCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(invCSEClass, InvCS.class, "InvCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(oclMessageArgCSEClass, OclMessageArgCS.class, "OclMessageArgCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOclMessageArgCS_Type(), theBaseCSTPackage.getTypeCS(), null, "type", null, 0, 1, OclMessageArgCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(operationContextDeclCSEClass, OperationContextDeclCS.class, "OperationContextDeclCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperationContextDeclCS_Operation(), thePivotPackage.getOperation(), null, "operation", null, 0, 1, OperationContextDeclCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationContextDeclCS_Parameters(), theEssentialOCLCSTPackage.getVariableCS(), null, "parameters", null, 0, -1, OperationContextDeclCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOperationContextDeclCS_Result(), theEssentialOCLCSTPackage.getVariableCS(), null, "result", null, 0, 1, OperationContextDeclCS.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(packageDeclarationCSEClass, PackageDeclarationCS.class, "PackageDeclarationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackageDeclarationCS_Namespace(), thePivotPackage.getNamespace(), null, "namespace", null, 0, -1, PackageDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageDeclarationCS_Package(), thePivotPackage.getPackage(), null, "package", null, 0, 1, PackageDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPackageDeclarationCS_Contexts(), this.getContextDeclCS(), null, "contexts", null, 0, -1, PackageDeclarationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(postCSEClass, PostCS.class, "PostCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(preCSEClass, PreCS.class, "PreCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(preExpCSEClass, PreExpCS.class, "PreExpCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(propertyContextDeclCSEClass, PropertyContextDeclCS.class, "PropertyContextDeclCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPropertyContextDeclCS_Property(), thePivotPackage.getProperty(), null, "property", null, 0, 1, PropertyContextDeclCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //CompleteOCLCSTPackageImpl
