package org.eclipse.ocl.examples.validity.internal.debug.ui.validations;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;

public class UMLConstraintLocator implements ConstraintLocator
{
	public @Nullable Iterable<Constraint> getConstraints(@NonNull MetaModelManager metaModelManager, @NonNull EObject eObject) {
		Constraint constraint;
		try {
			constraint = metaModelManager.getPivotOf(Constraint.class, eObject);
		} catch (ParserException e) {
			return null;
		}
		return Collections.singletonList(constraint);
	}
}