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
 * $Id: BinaryExpressionCS.java,v 1.2 2010/04/13 20:35:16 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Binary Expression CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.BinaryExpressionCS#getArgument <em>Argument</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.EssentialOCLCSTPackage#getBinaryExpressionCS()
 * @model
 * @generated
 */
public interface BinaryExpressionCS extends OperationExpressionCS {
	/**
	 * Returns the value of the '<em><b>Argument</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Argument</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Argument</em>' containment reference.
	 * @see #setArgument(OclExpressionCS)
	 * @see org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.EssentialOCLCSTPackage#getBinaryExpressionCS_Argument()
	 * @model containment="true"
	 * @generated
	 */
	OclExpressionCS getArgument();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.BinaryExpressionCS#getArgument <em>Argument</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Argument</em>' containment reference.
	 * @see #getArgument()
	 * @generated
	 */
	void setArgument(OclExpressionCS value);

} // BinaryExpressionCS