/*******************************************************************************
 * Copyright (c) 2009, 2010 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     SAP AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.ocl.examples.eventmanager.tests.filters;

import junit.textui.TestRunner;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.ocl.examples.eventmanager.EventFilter;
import org.eclipse.ocl.examples.eventmanager.EventManagerFactory;
import org.eclipse.ocl.examples.eventmanager.filters.ContainmentFilter;


/**
 * <!-- begin-user-doc --> A test case for the model object '<em><b>Containment Filter</b></em>'. <!-- end-user-doc -->
 * 
 */
public class ContainmentFilterTest extends EventFilterTest {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     */
    public static void main(String[] args) {
        TestRunner.run(ContainmentFilterTest.class);
    }

    private DynamicEObjectImpl containedObject;
    private ENotificationImpl matchingNotification;

    /**
     * Constructs a new Containment Filter test case with the given name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     */
    public ContainmentFilterTest() {
        super();
    }

    /**
     * Returns the fixture for this Containment Filter test case. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     */
    @Override
    protected ContainmentFilter getFixture() {
        return (ContainmentFilter) fixture;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setUp() {
        setFixture(EventManagerFactory.eINSTANCE.createContainmentFilter());
        EReference cFeature = EcoreFactory.eINSTANCE.createEReference();
        cFeature.setName("test");
        cFeature.setContainment(true);
        cFeature.setLowerBound(0);
        cFeature.setUpperBound(-1);
        EPackage p = EcoreFactory.eINSTANCE.createEPackage();
        p.setName("test");
        p.setNsPrefix("test");
        p.setNsURI("uri");
        EClass notiClass = EcoreFactory.eINSTANCE.createEClass();
        p.getEClassifiers().add(notiClass);
        notiClass.setName("testClass");
        cFeature.setEType(notiClass);
        notiClass.getEStructuralFeatures().add(cFeature);
        EObject testObject = new DynamicEObjectImpl(notiClass);
        containedObject = new DynamicEObjectImpl(notiClass);
        ((EList<EObject>) testObject.eGet(cFeature)).add(containedObject);
        matchingNotification = new ENotificationImpl((InternalEObject) testObject, Notification.ADD_MANY, cFeature,
                new BasicEList<EObject>(), testObject.eGet(cFeature));

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    public void tearDown() {
        setFixture(null);
        containedObject = null;
        matchingNotification = null;
    }

    @Override
    public void testMatchesFor__Notification() {
        assertTrue(getFixture().matchesFor(matchingNotification));
    }

	@Override
	EventFilter getFilterFor(Object f) {
		return EventManagerFactory.eINSTANCE.createContainmentFilter();
	}

	@Override
	Object getFilterCriterion1() {
		return null;
	}

	@Override
	Object getFilterCriterion2() {
		return null;
	}
	@Override
	public Notification[] giveMatchingNotifications() {
		EReference r = EcoreFactory.eINSTANCE.createEReference();
		r.setContainment(true);
		Notification n = new ENotificationImpl(null, 0, null, null, null){
			@Override
			public Object getNotifier() {
				return new ResourceImpl();
			}
		};
		return new Notification[]{ new ENotificationImpl(null, 0, r, null, null),
				n };
	}
	@Override
	public Notification giveNotMatchingNotifcation() {
		return new ENotificationImpl(null, 0, EcoreFactory.eINSTANCE.createEReference(), null, null);
	}
	@Override
	public EventFilter giveTestFilter() {
		return EventManagerFactory.eINSTANCE.createContainmentFilter();
	}
} // ContainmentFilterTest
