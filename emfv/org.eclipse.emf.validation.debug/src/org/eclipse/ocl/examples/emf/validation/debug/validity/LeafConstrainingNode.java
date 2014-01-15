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

import org.eclipse.ocl.examples.emf.validation.debug.locator.ConstraintDefinition;
import org.eclipse.ocl.examples.emf.validation.debug.locator.ConstraintLocator;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Leaf ConstrainingNode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode#getConstraintLocator <em>Constraint Locator</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode#getConstraintDefinition <em>Constraint Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityPackage#getLeafConstrainingNode()
 * @model
 * @generated
 */
public interface LeafConstrainingNode extends ConstrainingNode {
	/**
	 * Returns the value of the '<em><b>Constraint Locator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Locator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Locator</em>' attribute.
	 * @see #setConstraintLocator(ConstraintLocator)
	 * @see org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityPackage#getLeafConstrainingNode_ConstraintLocator()
	 * @model dataType="org.eclipse.ocl.examples.emf.validation.debug.validity.ConstraintLocator" required="true" transient="true"
	 * @generated
	 */
	ConstraintLocator getConstraintLocator();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode#getConstraintLocator <em>Constraint Locator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Locator</em>' attribute.
	 * @see #getConstraintLocator()
	 * @generated
	 */
	void setConstraintLocator(ConstraintLocator value);

	/**
	 * Returns the value of the '<em><b>Constraint Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraint Definition</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraint Definition</em>' attribute.
	 * @see #setConstraintDefinition(ConstraintDefinition)
	 * @see org.eclipse.ocl.examples.emf.validation.debug.validity.ValidityPackage#getLeafConstrainingNode_ConstraintDefinition()
	 * @model dataType="org.eclipse.ocl.examples.emf.validation.debug.validity.ConstraintDefinition" required="true" transient="true"
	 * @generated
	 */
	ConstraintDefinition getConstraintDefinition();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode#getConstraintDefinition <em>Constraint Definition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constraint Definition</em>' attribute.
	 * @see #getConstraintDefinition()
	 * @generated
	 */
	void setConstraintDefinition(ConstraintDefinition value);
} // LeafConstrainingNode
