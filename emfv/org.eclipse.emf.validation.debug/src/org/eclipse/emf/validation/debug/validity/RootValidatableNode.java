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
package org.eclipse.emf.validation.debug.validity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root ValidatableNode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.validation.debug.validity.RootValidatableNode#getRootNode <em>Root Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.validation.debug.validity.ValidityPackage#getRootValidatableNode()
 * @model
 * @generated
 */
public interface RootValidatableNode extends ValidatableNode {
	/**
	 * Returns the value of the '<em><b>Root Node</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.emf.validation.debug.validity.RootNode#getValidatableNodes <em>Validatable Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Node</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Node</em>' container reference.
	 * @see #setRootNode(RootNode)
	 * @see org.eclipse.emf.validation.debug.validity.ValidityPackage#getRootValidatableNode_RootNode()
	 * @see org.eclipse.emf.validation.debug.validity.RootNode#getValidatableNodes
	 * @model opposite="validatableNodes" required="true" transient="false"
	 * @generated
	 */
	RootNode getRootNode();

	/**
	 * Sets the value of the '{@link org.eclipse.emf.validation.debug.validity.RootValidatableNode#getRootNode <em>Root Node</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root Node</em>' container reference.
	 * @see #getRootNode()
	 * @generated
	 */
	void setRootNode(RootNode value);

} // RootValidatableNode
