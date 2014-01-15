package org.eclipse.ocl.examples.validity.internal.debug.ui.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.PivotConstants;
import org.eclipse.ocl.examples.pivot.PivotFactory;
import org.eclipse.ocl.examples.pivot.UMLReflection;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;

public class EClassifierConstraintLocator implements ConstraintLocator
	{
		public @Nullable Iterable<Constraint> getConstraints(@NonNull MetaModelManager metaModelManager, @NonNull EObject eObject) {
			EClassifier eClassifier = (EClassifier) eObject;
			List<Constraint> constraints = null;
			EAnnotation oclAnnotation = OCLCommon.getDelegateAnnotation(eClassifier);
			if (oclAnnotation != null) {
				EMap<String, String> oclAnnotationDetails = oclAnnotation.getDetails();
				int iMax = oclAnnotationDetails.size();
				for (int i = 0; i < iMax; i++) {
					Map.Entry<String,String> entry = oclAnnotationDetails.get(i);
					String constraintName = entry.getKey();
					if (constraintName == null) {
						constraintName = "";
					}
					if (!constraintName.endsWith(PivotConstants.MESSAGE_ANNOTATION_DETAIL_SUFFIX)) {
						Constraint constraint = PivotFactory.eINSTANCE.createConstraint();
//						constraint.setStereotype(UMLReflection.INVARIANT);
						constraint.setName(constraintName);
						String value = entry.getValue();
//						OpaqueExpression specification = PivotFactory.eINSTANCE.createOpaqueExpression();	// FIXME ExpressionInOCL
//						specification.getBody().add(value);
//						specification.getLanguage().add(PivotConstants.OCL_LANGUAGE);
//						String message = oclAnnotationDetails.get(constraintName + PivotConstants.MESSAGE_ANNOTATION_DETAIL_SUFFIX);
//						specification.getMessage().add(message != null ? message : "");
//						constraint.setSpecification(specification);
						if (constraints == null) {
							constraints = new ArrayList<Constraint>();
						}
						constraints.add(constraint);
					}
				}				
			}
			return constraints;
		}		
	}