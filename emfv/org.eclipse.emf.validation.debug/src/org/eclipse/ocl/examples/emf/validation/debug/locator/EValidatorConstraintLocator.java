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
 *  Obeo - Manage the Navigation from the ValidityView -> to the Editor
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.emf.validation.debug.locator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.examples.emf.validation.debug.ValidityManager;
import org.eclipse.ocl.examples.emf.validation.debug.ValidityModel;
import org.eclipse.ocl.examples.emf.validation.debug.plugin.ValidityPlugin;
import org.eclipse.ocl.examples.emf.validation.debug.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Result;
import org.eclipse.ocl.examples.emf.validation.debug.validity.Severity;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class EValidatorConstraintLocator extends AbstractConstraintLocator
{
	public @Nullable Map<EModelElement, List<LeafConstrainingNode>> getConstraints(@NonNull ValidityModel validityModel, @NonNull EPackage ePackage, @NonNull Set<Resource> resources) {
		Map<EModelElement, List<LeafConstrainingNode>> map = null;
		Object object = EValidator.Registry.INSTANCE.get(ePackage);
		if (object instanceof EValidator) {
//			Map<String, EClassifier> name2eClassifier = new HashMap<String, EClassifier>();
			Map<Class<?>, List<EClassifier>> javaClass2eClassifiers = new HashMap<Class<?>, List<EClassifier>>();
			for (EClassifier eClassifier : ePackage.getEClassifiers()) {
//				name2eClassifier.put(eClassifier.getName(), eClassifier);
				Class<?> javaClass = eClassifier.getInstanceClass();
				if (javaClass != null) {
					List<EClassifier> eClassifiers = javaClass2eClassifiers.get(javaClass);
					if (eClassifiers == null) {
						eClassifiers = new ArrayList<EClassifier>();
						javaClass2eClassifiers.put(javaClass, eClassifiers);
					}
					eClassifiers.add(eClassifier);
				}
			}
			for (Method method : object.getClass().getDeclaredMethods()) {
//				System.out.println(method);
				Class<?>[] parameterTypes = method.getParameterTypes();
				if (Modifier.isPublic(method.getModifiers())
				 && (parameterTypes.length == 3)
				 && (DiagnosticChain.class == parameterTypes[1])
				 && (Map.class == parameterTypes[2])) {
					String name = method.getName();
					List<EClassifier> eClassifiers = javaClass2eClassifiers.get(parameterTypes[0]);
					if (eClassifiers != null) {
						for (EClassifier eClassifier : eClassifiers) {
							String eClassifierName = eClassifier.getName();
							int index = name.indexOf(eClassifierName);
							if (index > 0) {
								int nameLength = name.length();
								int eClassifierNameLength = eClassifierName.length();
								int separatorIndex = index + eClassifierNameLength;
								String constraintName = null;
								if ((separatorIndex + 1 < nameLength) && ('_' == name.charAt(separatorIndex))) {
									constraintName = name.substring(separatorIndex + 1);
								}
								else if ((separatorIndex == nameLength) && !(eClassifier instanceof EClass))  {
									constraintName = "<datatype>";
								}
								if (constraintName != null) {
									MethodConstraintDefinition definition = new MethodConstraintDefinition(method);
									map = createLeafConstrainingNode(map, validityModel, eClassifier, method, definition, constraintName);
								}
							}
						}
					}
				}
			}
		}
		if (map != null) {
			//
			//	Prune unnamed constraints that have named constraints to delegate to
			//
			for (EObject eClassifier : map.keySet()) {
				List<LeafConstrainingNode> oldList = map.get(eClassifier);
				if (oldList.size() > 1) {
					ArrayList<LeafConstrainingNode> newList = new ArrayList<LeafConstrainingNode>(oldList);
					for (LeafConstrainingNode constraint : newList) {
						if ("".equals(constraint.getLabel())) {
							oldList.remove(constraint);
						}
					}
				}
			}
		}
//		if (map != null) {
//			String s = print(map);
//			System.out.println("EValidator '" + ePackage.getNsURI() + "'\n" + s);
//		}
		return map;
	}

	public Object getImage() {
		return ValidityPlugin.INSTANCE.getImage("methpub_obj.gif");
	}

	protected @NonNull String print(@NonNull Map<EClassifier, List<LeafConstrainingNode>> map) {
		StringBuilder s = new StringBuilder();
		ArrayList<EClassifier> sortedList = new ArrayList<EClassifier>(map.keySet());
		Collections.sort(sortedList, new Comparator<EClassifier>()
		{
			public int compare(EClassifier o1, EClassifier o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (EClassifier eClassifier : sortedList) {
			s.append("\t" + eClassifier.getName() + ":");
			for (LeafConstrainingNode constraint : map.get(eClassifier)) {
				s.append(" \'" + constraint.getLabel() + "'");
			}
			s.append("\n");
		}
		@SuppressWarnings("null")@NonNull String string = s.toString();
		return string;
	}

	@Override
	public void validate(@NonNull Result result, @NonNull ValidityManager validityManager) {
		Method method = (Method) result.getLeafConstrainingNode().getConstrainingObject();
		EObject eObject = result.getValidatableNode().getConstrainedObject();
		BasicDiagnostic diagnostic = validityManager.createDefaultDiagnostic(eObject);
		try {
			Class<?> declaringClass = method.getDeclaringClass();
			Object eValidator;
			try {
				Field field = declaringClass.getField("INSTANCE");
				eValidator = field.get(null);
			}
			catch (Exception e) {
				eValidator = declaringClass.newInstance();
			}
			method.invoke(eValidator, eObject, diagnostic, validityManager.createDefaultContext());
			result.setDiagnostic(diagnostic);
			result.setSeverity(getSeverity(diagnostic));
		} catch (Exception e) {
			result.setException(e);
			result.setSeverity(Severity.FATAL);
		}
	}
}