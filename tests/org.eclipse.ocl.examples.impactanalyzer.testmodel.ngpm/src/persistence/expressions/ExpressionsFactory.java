/**
 * <copyright>
 * </copyright>
 *
 * $Id: ExpressionsFactory.java,v 1.1 2011/02/07 17:21:36 auhl Exp $
 */
package persistence.expressions;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see persistence.expressions.ExpressionsPackage
 * @generated
 */
public interface ExpressionsFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ExpressionsFactory eINSTANCE = persistence.expressions.impl.ExpressionsFactoryImpl.init();

	/**
     * Returns a new object of class '<em>All</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>All</em>'.
     * @generated
     */
	All createAll();

	/**
     * Returns a new object of class '<em>Commit</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Commit</em>'.
     * @generated
     */
	Commit createCommit();

	/**
     * Returns a new object of class '<em>Snapshot</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Snapshot</em>'.
     * @generated
     */
	Snapshot createSnapshot();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	ExpressionsPackage getExpressionsPackage();

} //ExpressionsFactory