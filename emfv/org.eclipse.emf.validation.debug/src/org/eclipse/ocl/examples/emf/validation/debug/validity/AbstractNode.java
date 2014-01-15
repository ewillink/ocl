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
package org.eclipse.ocl.examples.emf.validation.debug.validity;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AbstractNode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode#getWorstResult <em>Worst Result</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode#isAllChildrenEnabled <em>All Children Enabled</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode#isAllChildrenDisabled <em>All Children Disabled</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityPackage#getAbstractNode()
 * @model abstract="true"
 * @generated
 */
public interface AbstractNode extends EObject {
	/**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityPackage#getAbstractNode_Enabled()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isEnabled();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityPackage#getAbstractNode_Label()
	 * @model default="" required="true"
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Worst Result</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Worst Result</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Worst Result</em>' reference.
	 * @see #setWorstResult(Result)
	 * @see org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityPackage#getAbstractNode_WorstResult()
	 * @model resolveProxies="false" transient="true" derived="true"
	 * @generated
	 */
	@Nullable Result getWorstResult();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.emf.validation.debug.validity.AbstractNode#getWorstResult <em>Worst Result</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Worst Result</em>' reference.
	 * @see #getWorstResult()
	 * @generated
	 */
	void setWorstResult(Result value);

	/**
	 * Returns the value of the '<em><b>All Children Enabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Children Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Children Enabled</em>' attribute.
	 * @see org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityPackage#getAbstractNode_AllChildrenEnabled()
	 * @model default="true" required="true" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	boolean isAllChildrenEnabled();

	/**
	 * Returns the value of the '<em><b>All Children Disabled</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Children Disabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Children Disabled</em>' attribute.
	 * @see org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityPackage#getAbstractNode_AllChildrenDisabled()
	 * @model default="true" required="true" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	boolean isAllChildrenDisabled();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	@Nullable AbstractNode getParent();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated NOT
	 */
	@NonNull EList<? extends AbstractNode> getChildren();

	void refreshAllChidrenEnablement();

} // AbstractNode
