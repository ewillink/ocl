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
 * $Id: OCLstdlibPostOrderVisitor.java,v 1.1.2.1 2010/12/06 18:14:19 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.oclstdlib.cs2pivot;

import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.ExpressionInOcl;
import org.eclipse.ocl.examples.pivot.IteratorKind;
import org.eclipse.ocl.examples.pivot.OclExpression;
import org.eclipse.ocl.examples.pivot.Operation;
import org.eclipse.ocl.examples.pivot.Parameter;
import org.eclipse.ocl.examples.pivot.Property;
import org.eclipse.ocl.examples.pivot.ValueSpecification;
import org.eclipse.ocl.examples.xtext.base.baseCST.ParameterCS;
import org.eclipse.ocl.examples.xtext.base.cs2pivot.CS2PivotConversion;
import org.eclipse.ocl.examples.xtext.base.cs2pivot.Continuation;
import org.eclipse.ocl.examples.xtext.essentialocl.cs2pivot.EssentialOCLPostOrderVisitor;
import org.eclipse.ocl.examples.xtext.oclstdlib.oclstdlibCST.LibAccumulatorCS;
import org.eclipse.ocl.examples.xtext.oclstdlib.oclstdlibCST.LibConstraintCS;
import org.eclipse.ocl.examples.xtext.oclstdlib.oclstdlibCST.LibIteratorCS;
import org.eclipse.ocl.examples.xtext.oclstdlib.oclstdlibCST.LibOperationCS;
import org.eclipse.ocl.examples.xtext.oclstdlib.oclstdlibCST.LibPropertyCS;
import org.eclipse.ocl.examples.xtext.oclstdlib.oclstdlibCST.PrecedenceCS;
import org.eclipse.ocl.examples.xtext.oclstdlib.util.AbstractExtendingDelegatingOCLstdlibCSVisitor;
import org.eclipse.xtext.common.types.JvmType;

public class OCLstdlibPostOrderVisitor
	extends AbstractExtendingDelegatingOCLstdlibCSVisitor<Continuation<?>, CS2PivotConversion, EssentialOCLPostOrderVisitor>
	{
		public OCLstdlibPostOrderVisitor(CS2PivotConversion context) {
			super(new EssentialOCLPostOrderVisitor(context), context);
		}
		  
		@Override
		public Continuation<?> visitLibAccumulatorCS(LibAccumulatorCS csLibAccumulator) {
			Parameter pivotElement = context.getPivotElement(Parameter.class, csLibAccumulator);
			pivotElement.setIteratorKind(IteratorKind.ACCUMULATOR);
			return super.visitLibAccumulatorCS(csLibAccumulator);
		}

		@Override
		public Continuation<?> visitLibConstraintCS(LibConstraintCS csLibConstraint) {
			Continuation<?> continuation = super.visitLibConstraintCS(csLibConstraint);
			Constraint pivotElement = context.getPivotElement(Constraint.class, csLibConstraint);
			ValueSpecification pivotSpecification = pivotElement.getSpecification();
			OclExpression pivotExpression = context.getPivotElement(OclExpression.class, csLibConstraint.getOwnedExpression());
			((ExpressionInOcl)pivotSpecification).setBodyExpression(pivotExpression);
			return continuation;
		}

		@Override
		public Continuation<?> visitLibIteratorCS(LibIteratorCS csLibIterator) {
			Parameter pivotElement = context.getPivotElement(Parameter.class, csLibIterator);
			pivotElement.setIteratorKind(IteratorKind.ITERATOR);
			return super.visitLibIteratorCS(csLibIterator);
		}

		@Override
		public Continuation<?> visitLibOperationCS(LibOperationCS csOperation) {
			Operation pivotElement = context.getPivotElement(Operation.class, csOperation);
			pivotElement.setPrecedence(csOperation.getPrecedence());
			pivotElement.setIsStatic(csOperation.isStatic());
			JvmType implementation = csOperation.getImplementation();
			if (implementation != null) {
				pivotElement.setImplementationClass(implementation.getCanonicalName());
			}
			return super.visitLibOperationCS(csOperation);
		}

		@Override
		public Continuation<?> visitLibPropertyCS(LibPropertyCS csProperty) {
			Property pivotElement = context.getPivotElement(Property.class, csProperty);
			pivotElement.setIsStatic(csProperty.isStatic());
			JvmType implementation = csProperty.getImplementation();
			if (implementation != null) {
				pivotElement.setImplementationClass(implementation.getCanonicalName());
			}
			return super.visitLibPropertyCS(csProperty);
		}

		@Override
		public Continuation<?> visitParameterCS(ParameterCS csParameter) {
			Parameter pivotElement = context.getPivotElement(Parameter.class, csParameter);
			pivotElement.setIteratorKind(IteratorKind.PARAMETER);
			return super.visitParameterCS(csParameter);
		}

		@Override
		public Continuation<?> visitPrecedenceCS(PrecedenceCS csPrecedence) {
			return null;
		}
	}