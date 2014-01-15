package org.eclipse.ocl.examples.validity.internal.debug.ui.validations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;

public interface ConstraintLocator
{
	@Nullable Iterable<Constraint> getConstraints(@NonNull MetaModelManager metaModelManager, @NonNull EObject eObject);
}