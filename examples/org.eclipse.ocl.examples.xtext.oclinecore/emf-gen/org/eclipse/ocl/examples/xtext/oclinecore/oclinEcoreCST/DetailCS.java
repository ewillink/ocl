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
 * $Id: DetailCS.java,v 1.1 2010/04/13 06:44:11 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST;

import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Detail CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.DetailCS#getIdName <em>Id Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.DetailCS#getStringName <em>String Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.DetailCS#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.OCLinEcoreCSTPackage#getDetailCS()
 * @model
 * @generated
 */
public interface DetailCS extends EObject {
	/**
	 * Returns the value of the '<em><b>Id Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Name</em>' attribute.
	 * @see #setIdName(String)
	 * @see org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.OCLinEcoreCSTPackage#getDetailCS_IdName()
	 * @model
	 * @generated
	 */
	String getIdName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.DetailCS#getIdName <em>Id Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Name</em>' attribute.
	 * @see #getIdName()
	 * @generated
	 */
	void setIdName(String value);

	/**
	 * Returns the value of the '<em><b>String Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>String Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>String Name</em>' attribute.
	 * @see #setStringName(String)
	 * @see org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.OCLinEcoreCSTPackage#getDetailCS_StringName()
	 * @model
	 * @generated
	 */
	String getStringName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.DetailCS#getStringName <em>String Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>String Name</em>' attribute.
	 * @see #getStringName()
	 * @generated
	 */
	void setStringName(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.OCLinEcoreCSTPackage#getDetailCS_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.oclinecore.oclinEcoreCST.DetailCS#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // DetailCS