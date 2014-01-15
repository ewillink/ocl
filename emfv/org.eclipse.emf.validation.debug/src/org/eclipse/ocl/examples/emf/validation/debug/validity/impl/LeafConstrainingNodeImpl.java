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
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.emf.validation.debug.validity.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.ocl.examples.emf.validation.debug.locator.ConstraintDefinition;
import org.eclipse.ocl.examples.emf.validation.debug.locator.ConstraintLocator;
import org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Leaf ConstrainingNode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.impl.LeafConstrainingNodeImpl#getConstraintLocator <em>Constraint Locator</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.impl.LeafConstrainingNodeImpl#getConstraintDefinition <em>Constraint Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LeafConstrainingNodeImpl extends ConstrainingNodeImpl implements LeafConstrainingNode {
	/**
	 * The default value of the '{@link #getConstraintLocator() <em>Constraint Locator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintLocator()
	 * @generated
	 * @ordered
	 */
	protected static final ConstraintLocator CONSTRAINT_LOCATOR_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getConstraintLocator() <em>Constraint Locator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintLocator()
	 * @generated
	 * @ordered
	 */
	protected ConstraintLocator constraintLocator = CONSTRAINT_LOCATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getConstraintDefinition() <em>Constraint Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintDefinition()
	 * @generated
	 * @ordered
	 */
	protected static final ConstraintDefinition CONSTRAINT_DEFINITION_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getConstraintDefinition() <em>Constraint Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintDefinition()
	 * @generated
	 * @ordered
	 */
	protected ConstraintDefinition constraintDefinition = CONSTRAINT_DEFINITION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LeafConstrainingNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValidityPackage.Literals.LEAF_CONSTRAINING_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintLocator getConstraintLocator() {
		return constraintLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintLocator(ConstraintLocator newConstraintLocator) {
		ConstraintLocator oldConstraintLocator = constraintLocator;
		constraintLocator = newConstraintLocator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_LOCATOR, oldConstraintLocator, constraintLocator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintDefinition getConstraintDefinition() {
		return constraintDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintDefinition(ConstraintDefinition newConstraintDefinition) {
		ConstraintDefinition oldConstraintDefinition = constraintDefinition;
		constraintDefinition = newConstraintDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_DEFINITION, oldConstraintDefinition, constraintDefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_LOCATOR:
				return getConstraintLocator();
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_DEFINITION:
				return getConstraintDefinition();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_LOCATOR:
				setConstraintLocator((ConstraintLocator)newValue);
				return;
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_DEFINITION:
				setConstraintDefinition((ConstraintDefinition)newValue);
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
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_LOCATOR:
				setConstraintLocator(CONSTRAINT_LOCATOR_EDEFAULT);
				return;
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_DEFINITION:
				setConstraintDefinition(CONSTRAINT_DEFINITION_EDEFAULT);
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
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_LOCATOR:
				return CONSTRAINT_LOCATOR_EDEFAULT == null ? constraintLocator != null : !CONSTRAINT_LOCATOR_EDEFAULT.equals(constraintLocator);
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_DEFINITION:
				return CONSTRAINT_DEFINITION_EDEFAULT == null ? constraintDefinition != null : !CONSTRAINT_DEFINITION_EDEFAULT.equals(constraintDefinition);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return super.toString();
	}
} //LeafConstrainingNodeImpl
