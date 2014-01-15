package org.eclipse.ocl.examples.validity.internal.debug.ui.validations;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.PivotFactory;
import org.eclipse.ocl.examples.pivot.UMLReflection;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;

public class EClassConstraintLocator implements ConstraintLocator
	{
		public @Nullable Iterable<Constraint> getConstraints(@NonNull MetaModelManager metaModelManager, @NonNull EObject eObject) {
			EClass eClass = (EClass) eObject;
			List<Constraint> constraints = null;
			for (EOperation eOperation : eClass.getEOperations()) {
				if (EcoreUtil.isInvariant(eOperation)) {
					Constraint constraint = PivotFactory.eINSTANCE.createConstraint();
//					constraint.setStereotype(UMLReflection.INVARIANT);
					constraint.setName(eOperation.getName());
					constraint.setIsCallable(true);
					String value = null;
/*					String commentBody = EcoreUtil.getAnnotation(eOperation, GenModelPackage.eNS_URI, "documentation");
					if (commentBody != null) {
						Comment pivotComment = PivotFactory.eINSTANCE.createComment();
						pivotComment.setBody(commentBody.replaceAll("\\r", ""));
						constraint.getOwnedComment().add(pivotComment);
					}				
					EAnnotation eAnnotation = OCLCommon.getDelegateAnnotation(eOperation);
					if (eAnnotation == null) {
						eAnnotation = eOperation.getEAnnotation(org.eclipse.uml2.codegen.ecore.genmodel.GenModelPackage.eNS_URI);
					}
					if (eAnnotation == null) {
						eAnnotation = eOperation.getEAnnotation("http://www.eclipse.org/uml2/1.1.0/GenModel");
					}
					if (eAnnotation != null) {
						value = eAnnotation.getDetails().get("body");
					}
					OpaqueExpression specification = PivotFactory.eINSTANCE.createOpaqueExpression();	// FIXME ExpressionInOCL
					specification.getBody().add(value);
					specification.getLanguage().add(PivotConstants.OCL_LANGUAGE);
					constraint.setSpecification(specification);
					pivotConstraints.add(constraint);
					converter.addMapping(eOperation, constraint); */
					if (constraints == null) {
						constraints = new ArrayList<Constraint>();
					}
					constraints.add(constraint);
				}
			}
			return constraints;
		}
	}