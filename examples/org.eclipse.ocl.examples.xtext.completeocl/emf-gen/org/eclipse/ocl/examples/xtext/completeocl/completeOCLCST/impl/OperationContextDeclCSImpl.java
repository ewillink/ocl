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
 * $Id: OperationContextDeclCSImpl.java,v 1.1 2010/04/13 06:38:26 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.BodyCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.CompleteOCLCSTPackage;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.OperationContextDeclCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PostCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.PreCS;

import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.TypeCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.VariableCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Context Decl CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.impl.OperationContextDeclCSImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.impl.OperationContextDeclCSImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.impl.OperationContextDeclCSImpl#getPres <em>Pres</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.impl.OperationContextDeclCSImpl#getPosts <em>Posts</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.completeocl.completeOCLCST.impl.OperationContextDeclCSImpl#getBodies <em>Bodies</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationContextDeclCSImpl extends ContextDeclCSImpl implements OperationContextDeclCS {
	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<VariableCS> parameters;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected TypeCS type;

	/**
	 * The cached value of the '{@link #getPres() <em>Pres</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPres()
	 * @generated
	 * @ordered
	 */
	protected EList<PreCS> pres;

	/**
	 * The cached value of the '{@link #getPosts() <em>Posts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPosts()
	 * @generated
	 * @ordered
	 */
	protected EList<PostCS> posts;

	/**
	 * The cached value of the '{@link #getBodies() <em>Bodies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBodies()
	 * @generated
	 * @ordered
	 */
	protected EList<BodyCS> bodies;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationContextDeclCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CompleteOCLCSTPackage.Literals.OPERATION_CONTEXT_DECL_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VariableCS> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<VariableCS>(VariableCS.class, this, CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeCS getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetType(TypeCS newType, NotificationChain msgs) {
		TypeCS oldType = type;
		type = newType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__TYPE, oldType, newType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TypeCS newType) {
		if (newType != type) {
			NotificationChain msgs = null;
			if (type != null)
				msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__TYPE, null, msgs);
			if (newType != null)
				msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__TYPE, null, msgs);
			msgs = basicSetType(newType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__TYPE, newType, newType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PreCS> getPres() {
		if (pres == null) {
			pres = new EObjectContainmentEList<PreCS>(PreCS.class, this, CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PRES);
		}
		return pres;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PostCS> getPosts() {
		if (posts == null) {
			posts = new EObjectContainmentEList<PostCS>(PostCS.class, this, CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__POSTS);
		}
		return posts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BodyCS> getBodies() {
		if (bodies == null) {
			bodies = new EObjectContainmentEList<BodyCS>(BodyCS.class, this, CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__BODIES);
		}
		return bodies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__TYPE:
				return basicSetType(null, msgs);
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PRES:
				return ((InternalEList<?>)getPres()).basicRemove(otherEnd, msgs);
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__POSTS:
				return ((InternalEList<?>)getPosts()).basicRemove(otherEnd, msgs);
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__BODIES:
				return ((InternalEList<?>)getBodies()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PARAMETERS:
				return getParameters();
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__TYPE:
				return getType();
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PRES:
				return getPres();
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__POSTS:
				return getPosts();
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__BODIES:
				return getBodies();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends VariableCS>)newValue);
				return;
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__TYPE:
				setType((TypeCS)newValue);
				return;
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PRES:
				getPres().clear();
				getPres().addAll((Collection<? extends PreCS>)newValue);
				return;
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__POSTS:
				getPosts().clear();
				getPosts().addAll((Collection<? extends PostCS>)newValue);
				return;
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__BODIES:
				getBodies().clear();
				getBodies().addAll((Collection<? extends BodyCS>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PARAMETERS:
				getParameters().clear();
				return;
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__TYPE:
				setType((TypeCS)null);
				return;
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PRES:
				getPres().clear();
				return;
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__POSTS:
				getPosts().clear();
				return;
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__BODIES:
				getBodies().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__TYPE:
				return type != null;
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__PRES:
				return pres != null && !pres.isEmpty();
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__POSTS:
				return posts != null && !posts.isEmpty();
			case CompleteOCLCSTPackage.OPERATION_CONTEXT_DECL_CS__BODIES:
				return bodies != null && !bodies.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OperationContextDeclCSImpl