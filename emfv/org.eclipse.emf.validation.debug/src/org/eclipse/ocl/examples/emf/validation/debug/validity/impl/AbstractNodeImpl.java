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

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Result;
import org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityPackage;
import org.eclipse.jdt.annotation.NonNull;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AbstractNode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.impl.AbstractNodeImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.impl.AbstractNodeImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.impl.AbstractNodeImpl#getWorstResult <em>Worst Result</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.impl.AbstractNodeImpl#isAllChildrenEnabled <em>All Children Enabled</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.impl.AbstractNodeImpl#isAllChildrenDisabled <em>All Children Disabled</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractNodeImpl extends MinimalEObjectImpl.Container implements AbstractNode {
	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWorstResult() <em>Worst Result</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorstResult()
	 * @generated
	 * @ordered
	 */
	protected Result worstResult;

	/**
	 * The default value of the '{@link #isAllChildrenEnabled() <em>All Children Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllChildrenEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALL_CHILDREN_ENABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAllChildrenEnabled() <em>All Children Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllChildrenEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean allChildrenEnabled = ALL_CHILDREN_ENABLED_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllChildrenDisabled() <em>All Children Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllChildrenDisabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALL_CHILDREN_DISABLED_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isAllChildrenDisabled() <em>All Children Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAllChildrenDisabled()
	 * @generated
	 * @ordered
	 */
	protected boolean allChildrenDisabled = ALL_CHILDREN_DISABLED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValidityPackage.Literals.ABSTRACT_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabledGen(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValidityPackage.ABSTRACT_NODE__ENABLED, oldEnabled, enabled));
	}
	public void setEnabled(boolean newEnabled) {
		if (newEnabled != enabled) {
			setEnabledGen(newEnabled);
			AbstractNode parent = getParent();
			if (parent != null) {
				parent.refreshAllChidrenEnablement();
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValidityPackage.ABSTRACT_NODE__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Result getWorstResult() {
		return worstResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorstResultGen(Result newWorstResult) {
		Result oldWorstResult = worstResult;
		worstResult = newWorstResult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ValidityPackage.ABSTRACT_NODE__WORST_RESULT, oldWorstResult, worstResult));
	}
	public void setWorstResult(Result newWorstResult) {
		if (newWorstResult != worstResult) {
			Result oldWorstResult = worstResult;
			setWorstResultGen(newWorstResult);
			if (isWorstResult(newWorstResult, oldWorstResult)) {
				AbstractNode parent = getParent();
				if ((parent != null) && isWorstResult(newWorstResult, parent.getWorstResult())) {
					parent.setWorstResult(newWorstResult);
				}
			}
		}
	}
	private boolean isWorstResult(Result newWorstResult, Result oldWorstResult) {
		if (newWorstResult == null) {
			return false;
		}
		if (oldWorstResult == null) {
			return true;
		}
		return newWorstResult.getSeverity().ordinal() > oldWorstResult.getSeverity().ordinal();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAllChildrenEnabled() {
		return allChildrenEnabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAllChildrenDisabled() {
		return allChildrenDisabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public abstract AbstractNode getParent();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public @NonNull abstract EList<? extends AbstractNode> getChildren();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ValidityPackage.ABSTRACT_NODE__ENABLED:
				return isEnabled();
			case ValidityPackage.ABSTRACT_NODE__LABEL:
				return getLabel();
			case ValidityPackage.ABSTRACT_NODE__WORST_RESULT:
				return getWorstResult();
			case ValidityPackage.ABSTRACT_NODE__ALL_CHILDREN_ENABLED:
				return isAllChildrenEnabled();
			case ValidityPackage.ABSTRACT_NODE__ALL_CHILDREN_DISABLED:
				return isAllChildrenDisabled();
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
			case ValidityPackage.ABSTRACT_NODE__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case ValidityPackage.ABSTRACT_NODE__LABEL:
				setLabel((String)newValue);
				return;
			case ValidityPackage.ABSTRACT_NODE__WORST_RESULT:
				setWorstResult((Result)newValue);
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
			case ValidityPackage.ABSTRACT_NODE__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case ValidityPackage.ABSTRACT_NODE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case ValidityPackage.ABSTRACT_NODE__WORST_RESULT:
				setWorstResult((Result)null);
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
			case ValidityPackage.ABSTRACT_NODE__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case ValidityPackage.ABSTRACT_NODE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case ValidityPackage.ABSTRACT_NODE__WORST_RESULT:
				return worstResult != null;
			case ValidityPackage.ABSTRACT_NODE__ALL_CHILDREN_ENABLED:
				return allChildrenEnabled != ALL_CHILDREN_ENABLED_EDEFAULT;
			case ValidityPackage.ABSTRACT_NODE__ALL_CHILDREN_DISABLED:
				return allChildrenDisabled != ALL_CHILDREN_DISABLED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case ValidityPackage.ABSTRACT_NODE___GET_PARENT:
				return getParent();
			case ValidityPackage.ABSTRACT_NODE___GET_CHILDREN:
				return getChildren();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();
		return String.valueOf(label);
	}

	public void refreshAllChidrenEnablement() {
		boolean newAllChildrenEnabled = true;
		boolean newAllChildrenDisabled = true;
		for (AbstractNode child : getChildren()) {
			if (!child.isAllChildrenEnabled()) {
				newAllChildrenEnabled = false; 
			}
			if (!child.isAllChildrenDisabled()) {
				newAllChildrenDisabled = false; 
			}
			if (child.isEnabled()) {
				newAllChildrenDisabled = false; 
			}
			else {
				newAllChildrenEnabled = false; 
			}
			if (!newAllChildrenDisabled && !newAllChildrenEnabled) {
				break;
			}
		}
		if ((newAllChildrenEnabled != allChildrenEnabled) || (newAllChildrenDisabled != allChildrenDisabled)) {
			allChildrenEnabled = newAllChildrenEnabled;
			allChildrenDisabled = newAllChildrenDisabled;
			AbstractNode parent = getParent();
			if (parent != null) {
				parent.refreshAllChidrenEnablement();
			}
		}
	}	
} //AbstractNodeImpl
