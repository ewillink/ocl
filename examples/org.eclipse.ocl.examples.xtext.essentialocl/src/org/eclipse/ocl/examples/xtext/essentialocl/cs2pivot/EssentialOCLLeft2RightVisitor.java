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
 * $Id: EssentialOCLLeft2RightVisitor.java,v 1.1.2.7 2011/01/08 11:38:57 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.essentialocl.cs2pivot;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.examples.pivot.AssociativityKind;
import org.eclipse.ocl.examples.pivot.BagType;
import org.eclipse.ocl.examples.pivot.BooleanLiteralExp;
import org.eclipse.ocl.examples.pivot.CallExp;
import org.eclipse.ocl.examples.pivot.CollectionItem;
import org.eclipse.ocl.examples.pivot.CollectionKind;
import org.eclipse.ocl.examples.pivot.CollectionLiteralExp;
import org.eclipse.ocl.examples.pivot.CollectionLiteralPart;
import org.eclipse.ocl.examples.pivot.CollectionRange;
import org.eclipse.ocl.examples.pivot.CollectionType;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.ExpressionInOcl;
import org.eclipse.ocl.examples.pivot.IfExp;
import org.eclipse.ocl.examples.pivot.IntegerLiteralExp;
import org.eclipse.ocl.examples.pivot.InvalidLiteralExp;
import org.eclipse.ocl.examples.pivot.IterateExp;
import org.eclipse.ocl.examples.pivot.Iteration;
import org.eclipse.ocl.examples.pivot.IteratorExp;
import org.eclipse.ocl.examples.pivot.LetExp;
import org.eclipse.ocl.examples.pivot.LoopExp;
import org.eclipse.ocl.examples.pivot.MonikeredElement;
import org.eclipse.ocl.examples.pivot.NamedElement;
import org.eclipse.ocl.examples.pivot.NullLiteralExp;
import org.eclipse.ocl.examples.pivot.OclExpression;
import org.eclipse.ocl.examples.pivot.Operation;
import org.eclipse.ocl.examples.pivot.OperationCallExp;
import org.eclipse.ocl.examples.pivot.OrderedSetType;
import org.eclipse.ocl.examples.pivot.Parameter;
import org.eclipse.ocl.examples.pivot.ParameterableElement;
import org.eclipse.ocl.examples.pivot.PivotEnvironment;
import org.eclipse.ocl.examples.pivot.PivotFactory;
import org.eclipse.ocl.examples.pivot.PivotPackage;
import org.eclipse.ocl.examples.pivot.Precedence;
import org.eclipse.ocl.examples.pivot.Property;
import org.eclipse.ocl.examples.pivot.PropertyCallExp;
import org.eclipse.ocl.examples.pivot.RealLiteralExp;
import org.eclipse.ocl.examples.pivot.SequenceType;
import org.eclipse.ocl.examples.pivot.SetType;
import org.eclipse.ocl.examples.pivot.StringLiteralExp;
import org.eclipse.ocl.examples.pivot.TemplateBinding;
import org.eclipse.ocl.examples.pivot.TemplateParameter;
import org.eclipse.ocl.examples.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.examples.pivot.TemplateSignature;
import org.eclipse.ocl.examples.pivot.TemplateableElement;
import org.eclipse.ocl.examples.pivot.TupleLiteralExp;
import org.eclipse.ocl.examples.pivot.TupleLiteralPart;
import org.eclipse.ocl.examples.pivot.TupleType;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.TypeExp;
import org.eclipse.ocl.examples.pivot.TypedElement;
import org.eclipse.ocl.examples.pivot.UnlimitedNaturalLiteralExp;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.VariableDeclaration;
import org.eclipse.ocl.examples.pivot.VariableExp;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationContext;
import org.eclipse.ocl.examples.pivot.messages.OCLMessages;
import org.eclipse.ocl.examples.pivot.utilities.PivotConstants;
import org.eclipse.ocl.examples.pivot.utilities.PivotManager;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil.PrecedenceComparator;
import org.eclipse.ocl.examples.xtext.base.baseCST.ModelElementCS;
import org.eclipse.ocl.examples.xtext.base.baseCST.TypedRefCS;
import org.eclipse.ocl.examples.xtext.base.cs2pivot.BaseLeft2RightVisitor;
import org.eclipse.ocl.examples.xtext.base.cs2pivot.CS2PivotConversion;
import org.eclipse.ocl.examples.xtext.base.scope.EnvironmentView;
import org.eclipse.ocl.examples.xtext.base.scope.ScopeCSAdapter;
import org.eclipse.ocl.examples.xtext.base.scope.ScopeView;
import org.eclipse.ocl.examples.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.BinaryOperatorCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.BooleanLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.CollectionLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.CollectionLiteralPartCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.CollectionTypeCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.ContextCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.ExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.ExpConstraintCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.IfExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.IndexExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.InfixExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.InvalidLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.LetExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.LetVariableCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NameExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NamedExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NavigatingAccCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NavigatingArgCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NavigatingArgOrBodyCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NavigatingExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NavigationOperatorCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NestedExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NullLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.NumberLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.OperatorCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.PrefixExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.SelfExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.StringLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.TupleLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.TupleLiteralPartCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.TypeLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.UnaryOperatorCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.UnlimitedNaturalLiteralExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialOCLCST.VariableCS;
import org.eclipse.ocl.examples.xtext.essentialocl.util.AbstractExtendingDelegatingEssentialOCLCSVisitor;
import org.eclipse.osgi.util.NLS;
import org.eclipse.xtext.parsetree.CompositeNode;
import org.eclipse.xtext.parsetree.LeafNode;
import org.eclipse.xtext.parsetree.NodeUtil;

public class EssentialOCLLeft2RightVisitor
	extends AbstractExtendingDelegatingEssentialOCLCSVisitor<MonikeredElement, CS2PivotConversion, BaseLeft2RightVisitor>
{
	private static final Logger logger = Logger.getLogger(EssentialOCLLeft2RightVisitor.class);

	protected final PivotManager pivotManager;
	
	public EssentialOCLLeft2RightVisitor(CS2PivotConversion context) {
		super(new BaseLeft2RightVisitor(context), context);
		this.pivotManager = context.getPivotManager();
	}

	public void addError(ModelElementCS csElement, String message) {
		csElement.getError().add(message);
	}

	public void addError(ModelElementCS csElement, String message, Object... bindings) {
		csElement.getError().add(NLS.bind(message, bindings));
	}

	/**
	 * Establish the parent-{source,argument} relationships between all infix
	 * operators in accordance with the precedence and associativity configuration.
	 */
	protected void createInfixOperatorTree(InfixExpCS csInfix) {
		//
		//	Create the per-precedence list of operator indexes, and a
		//	highest precedence first list of all used infix precedences.
		//
		Map<Precedence, List<Integer>> precedenceToOperatorIndexes = createInfixPrecedenceToOperatorIndexesMap(csInfix);
		List<Precedence> sortedPrecedences = new ArrayList<Precedence>(precedenceToOperatorIndexes.keySet());
		Collections.sort(sortedPrecedences, PrecedenceComparator.INSTANCE);
		//
		//	Build the tree leaf-to root precedence at a time.
		//
		List<ExpCS> csExpressions = csInfix.getOwnedExpression();
		List<BinaryOperatorCS> csOperators = csInfix.getOwnedOperator();
		for (Precedence precedence : sortedPrecedences) {
			// null precedence arises when precedence or operation-to-precedence is wrong
			boolean isLeft = precedence == null || (precedence.getAssociativity() == AssociativityKind.LEFT);
			List<Integer> operatorIndexes = precedenceToOperatorIndexes.get(precedence);
			int operatorCount = operatorIndexes.size();
			int iFirst = isLeft ? 0 : operatorCount-1;
			int iIndex = isLeft ? 1 : -1;
			int iLast = isLeft ? operatorCount : -1;
			for (int i = iFirst; i != iLast; i += iIndex) {
				int operatorIndex = operatorIndexes.get(i);
				BinaryOperatorCS csOperator = csOperators.get(operatorIndex);
				//
				//	Establish parent-child relationship of operator source
				//
				ExpCS csSource = csExpressions.get(operatorIndex);
				while ((csSource.getParent() != null) && (csSource.getParent() != csOperator)) {
					csSource = csSource.getParent();
				}
				csSource.setParent(csOperator);
				csOperator.setSource(csSource);
				//
				//	Establish parent-child relationship of operator argument
				//
				ExpCS csArgument = csExpressions.get(operatorIndex+1);
				while ((csArgument.getParent() != null) && (csArgument.getParent() != csOperator)) {
					csArgument = csArgument.getParent();
				}
				csArgument.setParent(csOperator);
				csOperator.setArgument(csArgument);
			}
		}
	}

	/**
	 * Return a map of operator indexes for each used precedence.
	 */
	protected Map<Precedence, List<Integer>> createInfixPrecedenceToOperatorIndexesMap(InfixExpCS csInfix) {
		List<BinaryOperatorCS> csOperators = csInfix.getOwnedOperator();
		int operatorCount = csOperators.size();
		Map<Precedence, List<Integer>> precedenceToOperatorIndex = new HashMap<Precedence, List<Integer>>();
		for (int operatorIndex = 0; operatorIndex < operatorCount; operatorIndex++) {
			BinaryOperatorCS csOperator = csOperators.get(operatorIndex);
			Precedence precedence = pivotManager.getInfixPrecedence(csOperator.getName());
			List<Integer> indexesList = precedenceToOperatorIndex.get(precedence);
			if (indexesList == null) {
				indexesList = new ArrayList<Integer>();
				precedenceToOperatorIndex.put(precedence, indexesList);
			}
			indexesList.add(operatorIndex);
		}
		return precedenceToOperatorIndex;
	}

	protected Type getSourceElementType(NavigatingExpCS csNavigatingExp, OclExpression source) {
		Type sourceType = source.getType();
		boolean isCollectionNavigation = PivotConstants.COLLECTION_NAVIGATION_OPERATOR.equals(csNavigatingExp.getParent().getName());
		if (!isCollectionNavigation) {
			return sourceType;
		}
		if (!pivotManager.conformsTo(sourceType, pivotManager.getCollectionTypeType())) {
			return sourceType;
		}
		return null;		// FIXME elementType
	}

	protected LoopExp handleIterationCall(NavigatingExpCS csNavigatingExp, OclExpression source, Iteration iteration) {
		NamedExpCS csNamedExp = csNavigatingExp.getNamedExp();
//		List<Parameter> iterators = iteration.getOwnedIterators();
		List<Parameter> accumulators = iteration.getOwnedAccumulators();
		LoopExp expression;
		if (accumulators.size() > 0) {
			expression = context.refreshExpression(IterateExp.class, PivotPackage.Literals.ITERATE_EXP, csNamedExp);
		}
		else {
			expression = context.refreshExpression(IteratorExp.class, PivotPackage.Literals.ITERATOR_EXP, csNamedExp);
		}
		expression.setReferredIteration(iteration);
		expression.setSource(source);
		expression.setType(iteration.getType());
		//
		List<Variable> pivotIterators = new ArrayList<Variable>();
		List<Variable> pivotAccumulators = new ArrayList<Variable>();
		List<OclExpression> pivotBodies = new ArrayList<OclExpression>();
		int argsOrBodies = 0;
		int accs = 0;
		int bodies = 0;
		for (NavigatingArgCS csNavigatingArg : csNavigatingExp.getArgument()) {
			if (csNavigatingArg instanceof NavigatingArgOrBodyCS) {
				argsOrBodies++;
			}
			else if (csNavigatingArg instanceof NavigatingAccCS) {
				accs++;
			}
			else {
				bodies++;
			}
		}
		for (NavigatingArgCS csArgument : csNavigatingExp.getArgument()) {
			if ((csArgument instanceof NavigatingArgOrBodyCS) && ((accs+bodies) > 0)) {
				NavigatingArgOrBodyCS csArg = (NavigatingArgOrBodyCS)csArgument;
	//			if (csArg.getInit() != null) {
	//				addError(csArg, "Unexpected initializer for parameter");
	//			}
	//			if (csArg.getOwnedType() == null) {
	//				addError(csArg, "Miss type for parameter");
	//			}
				ExpCS csName = csArg.getName();
				CompositeNode node = NodeUtil.getNode(csName);
				LeafNode leafNode = ElementUtil.getLeafNode(node);
				String varName = leafNode.getText();
				Variable iterator = context.refreshMonikeredElement(Variable.class, PivotPackage.Literals.VARIABLE, csName);
				context.installPivotElement(csName, iterator);
				context.reusePivotElement(csArg, iterator);
				context.refreshName(iterator, varName);
				TypedRefCS csType = csArg.getOwnedType();
				Type varType = csType != null ? PivotUtil.getPivot(Type.class, csType) : null;
				if (varType == null) {
					varType = getSourceElementType(csNavigatingExp, source);
				}
				context.setType(iterator, varType);
				pivotIterators.add(iterator);
			}
			else if (csArgument instanceof NavigatingAccCS) {
				NavigatingAccCS csAcc = (NavigatingAccCS)csArgument;
//				if (csAcc.getInit() == null) {
//					addError(csAcc, "Unexpected initializer for parameter");
//				}
//				if (csAcc.getOwnedType() != null) {
//					addError(csAcc, "Unexpected type for parameter");
//				}
				ExpCS csName = csAcc.getName();
				CompositeNode node = NodeUtil.getNode(csName);
				LeafNode leafNode = ElementUtil.getLeafNode(node);
				String varName = leafNode.getText();
				Variable acc = context.refreshExpTree(Variable.class, csName);
				context.installPivotElement(csName, acc);
				context.reusePivotElement(csAcc, acc);
				context.refreshName(acc, varName);
				context.setType(acc, PivotUtil.getPivot(Type.class, csAcc.getOwnedType()));
				pivotAccumulators.add(acc);
			}
			else {
//				NavigatingArg3CS csExp = (NavigatingArg3CS)csArgument;
//				if (csExp.getInit() != null) {
//					addError(csExp, "Unexpected initializer for parameter");
//				}
//				if (csExp.getOwnedType() != null) {
//					addError(csExp, "Unexpected type for parameter");
//				}
				OclExpression exp = context.refreshExpTree(OclExpression.class, csArgument.getName());
				context.installPivotElement(csArgument, exp);
				pivotBodies.add(exp);
			}
		}
		while (pivotIterators.size() < iteration.getOwnedIterators().size()) {
			String varName = Integer.toString(pivotIterators.size()+1);
			String moniker = csNamedExp.getMoniker() + PivotConstants.MONIKER_SCOPE_SEPARATOR
				+ PivotPackage.Literals.LOOP_EXP__ITERATOR.getName() + PivotConstants.MONIKER_OPERATOR_SEPARATOR + varName;
			Variable iterator = context.refreshMonikeredElement(Variable.class, PivotPackage.Literals.VARIABLE, moniker);
			context.usePivotElement(csNavigatingExp, iterator);
			context.refreshName(iterator, varName);
			Type varType = getSourceElementType(csNavigatingExp, source);
			context.setType(iterator, varType);
			pivotIterators.add(iterator);
		}
		context.refreshList(expression.getIterators(), pivotIterators);
		if (expression instanceof IterateExp) {
			if (pivotAccumulators.size() > 1) {
				addError(csNavigatingExp, "Iterate calls cannot have more than one accumulator");			
			}
			else {
				((IterateExp)expression).setResult(pivotAccumulators.get(0));
			}
		}
		else if (pivotAccumulators.size() > 0) {
			addError(csNavigatingExp, "Iteration calls cannot have an accumulator");			
		}
		if (pivotBodies.size() > 0) {
			if (pivotBodies.size() > 1) {
				addError(csNavigatingExp, "Iteration calls cannot have more than one body");			
			}
			expression.setBody(pivotBodies.get(0));
		}
		context.reusePivotElement(csNavigatingExp, expression);		
		return expression;
	}

	protected CallExp handleNavigatingExpCS(NavigatingExpCS csNavigatingExp) {
		NamedExpCS csNamedExp = csNavigatingExp.getNamedExp();
		NavigationOperatorCS csOperator = (NavigationOperatorCS) csNavigatingExp.getParent();
		ExpCS csSource;
		if (csOperator != null) {
			csSource = csOperator.getSource();
		}
		else {
			csSource = null; // Implicit
		}
		OclExpression source = PivotUtil.getPivot(OclExpression.class, csSource);
		NamedElement namedElement = csNamedExp.getNamedElement();
		if (namedElement.eIsProxy()) {
			addError(csNamedExp, NLS.bind(OCLMessages.ErrorUnresolvedOperationName, csNamedExp));
		}
		else if (namedElement instanceof Iteration) {
			return handleIterationCall(csNavigatingExp, source, (Iteration)namedElement);
		}
		else if (namedElement instanceof Operation) {
			return handleOperationCall(csNavigatingExp, source, (Operation)namedElement);
		}
		else {
			addError(csNamedExp, "Operation name expected");
//			expression.setSource(context.getPivotManager().createInvalidValue(namedElement, null, "not an operation", null));
//			expression.setReferredOperation(operation);
//			expression.setType(context.getPivotManager().getInvalidType());
//			return expression;
		}
		return null;
	}

	protected OperationCallExp handleOperationCall(NavigatingExpCS csNavigatingExp, OclExpression source, Operation operation) {
		NamedExpCS csNamedExp = csNavigatingExp.getNamedExp();
		OperationCallExp expression = context.refreshExpression(OperationCallExp.class, PivotPackage.Literals.OPERATION_CALL_EXP, csNamedExp);
		expression.setSource(source);
		expression.setReferredOperation(operation);
//		if (csNavigatingExp.getAccs().size() > 0) {
//			addError(csNavigatingExp, "Operation calls cannot specify accumulators");			
//		}
//		if (csNavigatingExp.getExps().size() > 0) {
//			addError(csNavigatingExp, "Operation calls cannot specify iterators");			
//		}
		List<OclExpression> pivotArguments = new ArrayList<OclExpression>();
		for (NavigatingArgCS csArgument : csNavigatingExp.getArgument()) {
			if (csArgument instanceof NavigatingArgOrBodyCS) {
				NavigatingArgOrBodyCS csArg = (NavigatingArgOrBodyCS)csArgument;
//				if (csArg.getInit() != null) {
//					addError(csArg, "Unexpected initializer for parameter");
//				}
				if (csArg.getOwnedType() != null) {
					addError(csArg, "Unexpected type for parameter");
				}
				OclExpression arg = context.refreshExpTree(OclExpression.class, csArg.getName());
				if (arg != null) {
					context.installPivotElement(csArg, arg);
					pivotArguments.add(arg);
				}
			}
			else {
				addError(csNavigatingExp, "Operation calls can only specify parameters");			
			}
		}
		if (csNavigatingExp.getArgument().size() < operation.getOwnedParameters().size()) {
			addError(csNavigatingExp, "Operation call has too few parameters");			
		}
		else if (csNavigatingExp.getArgument().size() > operation.getOwnedParameters().size()) {
			addError(csNavigatingExp, "Operation call has too many parameters");			
		}
		context.refreshList(expression.getArguments(), pivotArguments);
		Type returnType = operation.getType();
		Map<TemplateParameter, ParameterableElement> substitutions = getTemplateParameters(operation);
		updateSubstitutions(substitutions, operation.getClass_(), expression.getSource().getType());
		for (int i = 0; i < expression.getArguments().size(); i++) {
			Parameter parameter = operation.getOwnedParameters().get(i);
			OclExpression argument = expression.getArguments().get(i);
			updateSubstitutions(substitutions, parameter.getType(), argument.getType());
		}
		if (substitutions != null) {
			returnType = resolveReturnType(returnType, substitutions);
		}
		expression.setType(returnType);
		context.reusePivotElement(csNavigatingExp, expression);
		return expression;
	}

	private Type resolveReturnType(Type type, Map<TemplateParameter, ParameterableElement> substitutions) {
		TemplateParameter owningTemplateParameter = type.getOwningTemplateParameter();
		if (owningTemplateParameter != null) {
			ParameterableElement parameterableElement = substitutions.get(owningTemplateParameter);
			return parameterableElement != null ? (Type) parameterableElement : type;
		}
		if (type instanceof TupleType) {
			TupleType tupleType = (TupleType) type;
			Map<String, Type> resolutions =  null;
			for (Property property : tupleType.getOwnedAttributes()) {
				Type propertyType = property.getType();
				Type resolvedPropertyType = resolveReturnType(propertyType, substitutions);
				if (resolvedPropertyType != propertyType) {
					if (resolutions == null) {
						resolutions = new HashMap<String, Type>();
					}
					resolutions.put(property.getName(), resolvedPropertyType);
				}
			}
			if (resolutions != null) {
				List<TypedElement> parts = new ArrayList<TypedElement>();
				for (Property property : tupleType.getOwnedAttributes()) {
					TypedElement part = property;
					Type resolvedPropertyType = resolutions.get(property.getName());
					if (resolvedPropertyType != null) {
						part = new PivotManager.TuplePart(property.getName(), resolvedPropertyType);
					}
					parts.add(part);
				}
				tupleType = pivotManager.getTupleType(type.getName(), parts);
			}
			return tupleType;
		}
		List<TemplateParameter> templateParameters = PivotUtil.getAllTemplateParameters(type);
		if ((templateParameters != null) && !templateParameters.isEmpty()) {
			List<ParameterableElement> templateArguments = new ArrayList<ParameterableElement>();
			boolean isSubstituted = false;
			for (TemplateParameter templateParameter : templateParameters) {
				ParameterableElement templateArgument = substitutions.get(templateParameter);
				if (templateArgument != null) {
					isSubstituted = true;
				}
				else {
					templateArgument = (ParameterableElement) templateParameter;
				}
				templateArguments.add(templateArgument);
			}
			if (!isSubstituted) {
				return type;
			}
			return pivotManager.getLibraryType(type, templateArguments, true);
		}
		Map<TemplateParameter, ParameterableElement> templateParameterSubstitutions = PivotUtil.getAllTemplateParameterSubstitutions(type);
		if ((templateParameterSubstitutions != null) && !templateParameterSubstitutions.isEmpty()) {
			Map<TemplateParameter, ParameterableElement> substituteTemplateParameterSubstitutions = new HashMap<TemplateParameter, ParameterableElement>();
			boolean isSubstituted = false;
			for (TemplateBinding templateBinding : type.getTemplateBindings()) {
				for (TemplateParameterSubstitution templateParameterSubstutution : templateBinding.getParameterSubstitutions()) {
					ParameterableElement actual = templateParameterSubstutution.getActual();
					if (actual instanceof Type) {
						Type substitutedActual = resolveReturnType((Type)actual, substitutions);
						if (substitutedActual != actual) {
							isSubstituted = true;						
						}
						substituteTemplateParameterSubstitutions.put(templateParameterSubstutution.getFormal(), substitutedActual);
					}
				}
			}
			if (!isSubstituted) {
				return type;
			}
			List<ParameterableElement> templateArguments = new ArrayList<ParameterableElement>();
			Type unspecializedType = PivotUtil.getUnspecializedTemplateableElement(type);
			for (TemplateParameter templateParameter : PivotUtil.getAllTemplateParameters(unspecializedType)) {
				ParameterableElement parameterableElement = substituteTemplateParameterSubstitutions.get(templateParameter);
				if (parameterableElement == null) {
					parameterableElement = templateParameter.getParameteredElement();
				}
				templateArguments.add(parameterableElement);
			}
			return pivotManager.getLibraryType(unspecializedType, templateArguments, true);
		}
		return type;
	}

	private void updateSubstitutions(Map<TemplateParameter, ParameterableElement> substitutions, Type formalType, Type actualType) {		
		TemplateParameter formalTemplateParameter = formalType.getOwningTemplateParameter();
		if (formalTemplateParameter != null) {
			substitutions.put(formalTemplateParameter, actualType);
		}
		else {
			List<TemplateBinding> templateBindings = formalType.getTemplateBindings();
			TemplateSignature formalTemplateSignature = PivotUtil.getUnspecializedTemplateableElement(formalType).getOwnedTemplateSignature();
			if (formalTemplateSignature != null) {
				for (TemplateParameter formalNestedTemplateParameter : formalTemplateSignature.getParameters()) {
					TemplateParameterSubstitution actualTemplateParameterSubstitution = findFormalParameter(formalNestedTemplateParameter, actualType);
					if (actualTemplateParameterSubstitution != null) {
						TemplateParameter formal = getFormal(templateBindings, actualTemplateParameterSubstitution.getFormal());
						if (formal == null) {	// FIXME Make this work at arbitrary depth
							formal = formalNestedTemplateParameter;
						}
						substitutions.put(formal, actualTemplateParameterSubstitution.getActual());
					}
				}
			}
		}
	}

	private TemplateParameter getFormal(List<TemplateBinding> templateBindings, TemplateParameter templateParameter) {
		for (TemplateBinding templateBinding : templateBindings) {
			for (TemplateParameterSubstitution templateParameterSubstitution : templateBinding.getParameterSubstitutions()) {
				if (templateParameter == templateParameterSubstitution.getFormal()) {
					return templateParameterSubstitution.getActual().getOwningTemplateParameter();
				}
			}
		}
		return null;
	}

	public TemplateParameterSubstitution findFormalParameter(TemplateParameter formalTemplateParameter, Type actualType) {
		for (TemplateBinding actualTemplateBinding : actualType.getTemplateBindings()) {
			for (TemplateParameterSubstitution actualTemplateParameterSubstitution : actualTemplateBinding.getParameterSubstitutions()) {
				TemplateParameter actualFormal = actualTemplateParameterSubstitution.getFormal();
				if (actualFormal == formalTemplateParameter) {
					return actualTemplateParameterSubstitution;
				}
			}
		}
		if (actualType instanceof org.eclipse.ocl.examples.pivot.Class) {
			for (org.eclipse.ocl.examples.pivot.Class superClass : ((org.eclipse.ocl.examples.pivot.Class)actualType).getSuperClasses()) {
				TemplateParameterSubstitution actualTemplateParameterSubstitution = findFormalParameter(formalTemplateParameter, superClass);
				if (actualTemplateParameterSubstitution != null) {
					return actualTemplateParameterSubstitution;
				}
			}
		}
		return null;
	}

/*	private Map<TemplateParameter, ParameterableElement> getParameterSubstitutions(OperationCallExp callExpression) {
		Map<TemplateParameter, ParameterableElement> result = null;
		result = gatherParameterSubstitutions(result, callExpression.getSource().getType());
		for (OclExpression argument : callExpression.getArguments()) {
			result = gatherParameterSubstitutions(result, argument.getType());
		}
		return result;
	}

	private Map<TemplateParameter, ParameterableElement> gatherParameterSubstitutions(
			Map<TemplateParameter, ParameterableElement> result, Type sourceType) {
		for (TemplateBinding templateBinding : sourceType.getTemplateBindings()) {
			for (TemplateParameterSubstitution templateParameterSubstitution : templateBinding.getParameterSubstitutions()) {
				if (result == null) {
					result = new HashMap<TemplateParameter, ParameterableElement>();
				}
				result.put(templateParameterSubstitution.getFormal(), templateParameterSubstitution.getActual());
			}
		}
		return result;
	} */

	private Map<TemplateParameter, ParameterableElement> getTemplateParameters(EObject eObject) {
		Map<TemplateParameter, ParameterableElement> result = null;
		EObject eContainer = eObject.eContainer();
		if (eContainer != null) {
			result = getTemplateParameters(eContainer);
		}
		if (eObject instanceof TemplateableElement) {
			TemplateableElement unspecializedTemplateableElement = PivotUtil.getUnspecializedTemplateableElement((TemplateableElement)eObject);
			TemplateSignature templateSignature = unspecializedTemplateableElement.getOwnedTemplateSignature();
			if (templateSignature != null) {
				if (result == null) {
					result = new HashMap<TemplateParameter, ParameterableElement>();
				}
				for (TemplateParameter templateParameter : templateSignature.getParameters()) {
					result.put(templateParameter, null);
				}
			}
		}
		return result;
	}

	protected PropertyCallExp handlePropertyCall(NameExpCS csNameExp, Property property) {
		PropertyCallExp expression = context.refreshExpression(PropertyCallExp.class, PivotPackage.Literals.PROPERTY_CALL_EXP, csNameExp);
		expression.setReferredProperty(property);
		context.setType(expression, property.getType());		// FIXME resolve template parameter
		return expression;
	}

	protected TypeExp handleTypeExp(ExpCS csExp, Type type) {
		TypeExp expression = context.refreshExpression(TypeExp.class, PivotPackage.Literals.TYPE_EXP, csExp);
		context.setType(expression, pivotManager.getClassifierType());
		expression.setReferredType(type);
		return expression;
	}

	protected VariableExp handleVariableExp(NameExpCS csNameExp, VariableDeclaration variableDeclaration) {
		VariableExp expression = context.refreshExpression(VariableExp.class, PivotPackage.Literals.VARIABLE_EXP, csNameExp);
		expression.setReferredVariable(variableDeclaration);
		context.setType(expression, variableDeclaration.getType());
		return expression;
	}

	protected void initializePrefixOperators(PrefixExpCS prefixExpCS, OperatorCS csParent) {
		prefixExpCS.setParent(null);
		for (UnaryOperatorCS csUnaryOperator : prefixExpCS.getOwnedOperator()) {
			if (csParent instanceof UnaryOperatorCS) {
				csParent.setSource(csUnaryOperator);
			}
			else if (csParent instanceof BinaryOperatorCS) {
				if (csParent.getSource() == prefixExpCS) {
					csParent.setSource(csUnaryOperator);
				}
				else {
					((BinaryOperatorCS) csParent).setArgument(csUnaryOperator);
				}
			}
			csUnaryOperator.setParent(csParent);
			ExpCS csChild = prefixExpCS.getOwnedExpression();
//			if (csChild instanceof NamedExpCS) {
//				csChild = ((NamedExpCS)csChild).getSimpleNamedExp();
//			}
			csUnaryOperator.setSource(csChild);
			csChild.setParent(csUnaryOperator);
			csParent = csUnaryOperator;
		}
	}

	protected void installInfixPivotElement(OperatorCS csOperator) {
		ExpCS csSource = csOperator.getSource();
//		if (csSource != null) {
//			if (csSource instanceof NamedExpCS) {
//				csSource = ((NamedExpCS)csSource).getSimpleNamedExp();
//			}
//			csSource.setParent(csOperator);
//		}
//		if (csOperator instanceof BinaryOperatorCS) {
//			ExpCS csArgument = ((BinaryOperatorCS) csOperator).getArgument();
//			if (csArgument != null) {
//				if (csArgument instanceof NamedExpCS) {
//					csArgument = ((NamedExpCS)csArgument).getSimpleNamedExp();
//				}
//				csArgument.setParent(csOperator);
//			}
//		}						
		if (csOperator instanceof NavigationOperatorCS) {
			NavigationOperatorCS csNavigationOperator = (NavigationOperatorCS) csOperator;
			CallExp pivotElement = PivotUtil.getPivot(CallExp.class, csNavigationOperator.getArgument());
			context.reusePivotElement(csNavigationOperator, pivotElement);
			return;
		}
		OperationCallExp pivotElement = PivotUtil.getPivot(OperationCallExp.class, csOperator);
		if (csSource != null) {
			OclExpression pivotChild = PivotUtil.getPivot(OclExpression.class, csSource);
			pivotElement.setSource(pivotChild);
		}
		if (csOperator instanceof BinaryOperatorCS) {
			ExpCS csArgument = ((BinaryOperatorCS) csOperator).getArgument();
			if (csArgument != null) {
				OclExpression pivotRight = PivotUtil.getPivot(OclExpression.class, csArgument);
				pivotElement.getArguments().add(pivotRight);
			}
		}
		Operation operation = context.resolveOperationCall(pivotElement);
		if (operation == null) {
			addError(csOperator, OCLMessages.ErrorUnresolvedOperationCall, csOperator);
		}
		pivotElement.setReferredOperation(operation);
		if (operation != null) {
			context.setType(pivotElement, operation.getType());
		}
	}

	protected void interleavePrefixes(InfixExpCS csElement) {
		for (ExpCS csExp : csElement.getOwnedExpression()) {
			if (csExp instanceof PrefixExpCS) {
				PrefixExpCS prefixExpCS = (PrefixExpCS)csExp;
				OperatorCS csExpressionParent = prefixExpCS.getParent();
				initializePrefixOperators(prefixExpCS, csExpressionParent);			
				for (UnaryOperatorCS csUnaryOperator : prefixExpCS.getOwnedOperator()) {
					interleaveUnaryOperator(csUnaryOperator);
				}			
			}
		}
	}
	
	protected void interleaveUnaryOperator(UnaryOperatorCS csOperator) {
		OperatorCS csParent = csOperator.getParent();
		ExpCS csExp = csOperator.getSource();
		if (csParent instanceof BinaryOperatorCS) {
			Precedence parentPrecedence = pivotManager.getInfixPrecedence(csParent.getName());
			Precedence unaryPrecedence = pivotManager.getPrefixPrecedence(csOperator.getName());
			if (parentPrecedence.getOrder().compareTo(unaryPrecedence.getOrder()) < 0) {
				OperatorCS csGrandParent = csParent.getParent();
				if (csExp == csParent.getSource()) {
					if (csGrandParent instanceof BinaryOperatorCS) {
						if (csGrandParent.getSource() == csParent) {
							csGrandParent.setSource(csOperator);
						}
						else {
							((BinaryOperatorCS) csGrandParent).setArgument(csOperator);
						}
					}
					else {
						csGrandParent.setSource(csOperator);
					}
					csParent.setParent(csOperator);
					csOperator.setSource(csParent);
					((BinaryOperatorCS) csParent).setSource(csExp);
				}
				else if (csExp == ((BinaryOperatorCS) csParent).getArgument()) {
					if (csGrandParent instanceof BinaryOperatorCS) {
						if (csGrandParent.getSource() == csParent) {
							csGrandParent.setSource(csExp);
							csGrandParent.setParent(csOperator);
							csOperator.setSource(csGrandParent);
						}
					}
				}
			}
		}
	}
	  
	@Override
	public MonikeredElement visitBinaryOperatorCS(BinaryOperatorCS csBinaryOperator) {
		OperationCallExp expression = context.refreshExpression(OperationCallExp.class, PivotPackage.Literals.OPERATION_CALL_EXP, csBinaryOperator);
		context.refreshName(expression, csBinaryOperator.getName());
		return expression;
	}

	@Override
	public MonikeredElement visitBooleanLiteralExpCS(BooleanLiteralExpCS csBooleanLiteralExp) {
		BooleanLiteralExp expression = context.refreshExpression(BooleanLiteralExp.class, PivotPackage.Literals.BOOLEAN_LITERAL_EXP, csBooleanLiteralExp);
		expression.setBooleanSymbol(Boolean.valueOf(csBooleanLiteralExp.getName()));
		expression.setType(pivotManager.getBooleanType());
		return expression;
	}

	@Override
	public MonikeredElement visitCollectionLiteralExpCS(CollectionLiteralExpCS csCollectionLiteralExp) {
		Type commonType = null;
//		InvalidLiteralExp invalidValue = null;
		for (CollectionLiteralPartCS csPart : csCollectionLiteralExp.getOwnedParts()) {
			CollectionLiteralPart pivotPart = context.refreshExpTree(CollectionLiteralPart.class, csPart);
			Type type = pivotPart.getType();
//			if (type instanceof InvalidType) {	// FIXME Use propagated reason via InvalidType
//				if (invalidValue == null) {
//					invalidValue = pivotManager.createInvalidValue(csPart, null, "Invalid Collection content", null);
//				}
//			}
//			else
			if (commonType == null) {
				commonType = type;
			}
			else {
				commonType = pivotManager.getCommonType(commonType, type);
			}
		}
//		if (invalidValue != null) {
//			context.installPivotElement(csCollectionLiteralExp, invalidValue);
//			return invalidValue;
//		}
		CollectionLiteralExp expression = context.refreshExpression(CollectionLiteralExp.class, PivotPackage.Literals.COLLECTION_LITERAL_EXP, csCollectionLiteralExp);
		context.refreshPivotList(CollectionLiteralPart.class, expression.getParts(), csCollectionLiteralExp.getOwnedParts());
		CollectionTypeCS ownedCollectionType = csCollectionLiteralExp.getOwnedType();
		TypedRefCS ownedElementType = ownedCollectionType.getOwnedType();
		if (ownedElementType != null) {
			commonType = (Type) ownedElementType.getPivot();
		}
		if (commonType == null) {
			commonType = pivotManager.getNullType();			// FIXME Use a clearer unspecified type
		}
		String collectionTypeName = ownedCollectionType.getName();
		Type type = pivotManager.getLibraryType(collectionTypeName, Collections.singletonList(commonType));
		context.setType(expression, type);
		if (type instanceof BagType) {
			expression.setKind(CollectionKind.BAG);
		}
		else if (type instanceof OrderedSetType) {
			expression.setKind(CollectionKind.ORDERED_SET);
		}
		else if (type instanceof SequenceType) {
			expression.setKind(CollectionKind.SEQUENCE);
		}
		else if (type instanceof SetType) {
			expression.setKind(CollectionKind.SET);
		}
		else {
			expression.setKind(CollectionKind.COLLECTION);
		}
		return expression;
	}

	@Override
	public MonikeredElement visitCollectionLiteralPartCS(CollectionLiteralPartCS csCollectionLiteralPart) {
		ExpCS csFirst = csCollectionLiteralPart.getExpressionCS();
		OclExpression pivotFirst = context.refreshExpTree(OclExpression.class, csFirst);
		ExpCS csLast = csCollectionLiteralPart.getLastExpressionCS();
		if (csLast == null) {
			CollectionItem expression = context.refreshMonikeredElement(CollectionItem.class, PivotPackage.Literals.COLLECTION_ITEM, csCollectionLiteralPart);	
			context.installPivotElement(csCollectionLiteralPart, expression);
			expression.setItem(pivotFirst);
		}
		else {
			CollectionRange expression = context.refreshMonikeredElement(CollectionRange.class, PivotPackage.Literals.COLLECTION_RANGE, csCollectionLiteralPart);
			context.installPivotElement(csCollectionLiteralPart, expression);
			expression.setFirst(pivotFirst);
			OclExpression pivotLast = context.refreshExpTree(OclExpression.class, csLast);
			expression.setLast(pivotLast);
		}
		Type type = pivotFirst.getType();
		if (csLast != null) {
			OclExpression pivotLast = PivotUtil.getPivot(OclExpression.class, csLast);
			Type secondType = pivotLast.getType();
			type = pivotManager.getCommonType(type, secondType);
		}
		CollectionLiteralPart expression = PivotUtil.getPivot(CollectionLiteralPart.class, csCollectionLiteralPart);
		context.setType(expression, type);
		return expression;
	}

	@Override
	public MonikeredElement visitCollectionTypeCS(CollectionTypeCS object) {
		return null;
	}

	@Override
	public MonikeredElement visitContextCS(ContextCS csContext) {
		ExpressionInOcl pivotElement = context.refreshMonikeredElement(ExpressionInOcl.class,
			PivotPackage.Literals.EXPRESSION_IN_OCL, csContext);

		Variable contextVariable = pivotElement.getContextVariable();
		if (contextVariable == null) {
			contextVariable = PivotFactory.eINSTANCE.createVariable();
			pivotElement.setContextVariable(contextVariable);
		}
		Resource resource = csContext.eResource();
		if (resource instanceof EvaluationContext) {			
			PivotEnvironment environment = ((EvaluationContext)resource).getEnvironment();
			Type contextType = environment.getContextClassifier();
//			Feature contextFeature = ((EvaluationContext)resource).getContextFeature();
			context.setType(contextVariable, contextType);
		}
		context.refreshName(contextVariable, "self");
		context.putPivotElement(contextVariable);
				
		context.installPivotElement(csContext, pivotElement);
		ExpCS csExpression = csContext.getOwnedExpression();
		OclExpression expression = context.refreshExpTree(OclExpression.class, csExpression);
		if (expression != null) {
			pivotElement.setBodyExpression(expression);
			context.setType(pivotElement, expression.getType());
		}
		return pivotElement;
	}

	@Override
	public MonikeredElement visitExpCS(ExpCS object) {
		return null;
	}

	@Override
	public MonikeredElement visitExpConstraintCS(ExpConstraintCS csExpConstraint) {
		ExpCS csExpression = csExpConstraint.getOwnedExpression();
		if (csExpression == null) {
			return null;
		}
		Constraint constraint = PivotUtil.getPivot(Constraint.class, csExpConstraint);
		OclExpression expression = context.refreshExpTree(OclExpression.class, csExpression);
		ExpressionInOcl specification = (ExpressionInOcl) constraint.getSpecification();
		specification.setBodyExpression(expression);
		return expression;
	}

	@Override
	public MonikeredElement visitIfExpCS(IfExpCS csIfExp) {
		IfExp expression = context.refreshExpression(IfExp.class, PivotPackage.Literals.IF_EXP, csIfExp);
		expression.setCondition(context.refreshExpTree(OclExpression.class, csIfExp.getCondition()));
		expression.setThenExpression(context.refreshExpTree(OclExpression.class, csIfExp.getThenExpression()));
		expression.setElseExpression(context.refreshExpTree(OclExpression.class, csIfExp.getElseExpression()));
		return expression;
	}

	@Override
	public MonikeredElement visitIndexExpCS(IndexExpCS csIndexExp) {
		// Navigating completions are orchestrated by the SimpleNamedExpCS.
		return null;
	}

	@Override
	public MonikeredElement visitInfixExpCS(InfixExpCS csInfixExp) {
		// FIXME special case trivial expressions
		//
		//	Establish the Infix tree and the per leaf expression parent operator.
		//
		createInfixOperatorTree(csInfixExp);			
		//
		//	Interleave the Prefix Operators.
		//
		interleavePrefixes(csInfixExp);
		//
		//	Build the tree left to right.
		//
		List<ExpCS> csExpressions = csInfixExp.getOwnedExpression();
		List<BinaryOperatorCS> csOperators = csInfixExp.getOwnedOperator();
		int iMax = csOperators.size();
		context.refreshExpTree(OclExpression.class, csExpressions.get(0));
		for (int i = 0; i < iMax; i++) {
			BinaryOperatorCS csOperator = csOperators.get(i);
			context.refreshExpTree(OclExpression.class, csOperator);
//			if (!(csOperator instanceof NavigationOperatorCS)) {
				context.refreshExpTree(OclExpression.class, csExpressions.get(i+1));
//			}
		}
		//
		for (OperatorCS csOperator :  csOperators) {
			installInfixPivotElement(csOperator);
		}
		//
		//	Map the infix CS to the root of the AS tree.
		//
		ExpCS csRoot = csOperators.get(0);
		for (ExpCS csParent = csRoot.getParent(); csParent != null; csParent = csRoot.getParent()) {
			csRoot = csParent;
		}
		OclExpression pivot = PivotUtil.getPivot(OclExpression.class, csRoot);
		context.reusePivotElement(csInfixExp, pivot);
		return pivot;
	}

	@Override
	public MonikeredElement visitInvalidLiteralExpCS(InvalidLiteralExpCS csInvalidLiteralExp) {
		InvalidLiteralExp expression = pivotManager.createInvalidValue(csInvalidLiteralExp, null, "invalid literal", null);
		context.installPivotElement(csInvalidLiteralExp, expression);
		return expression;
	}

	@Override
	public MonikeredElement visitLetExpCS(LetExpCS csLetExp) {
		// Each CS Let Variable becomes a Pivot LetExpression and Variable
		// The CS Let therefore just re-uses the Pivot of the first CS Let Variable
		String letMoniker = csLetExp.getMoniker();
		LetExp firstLetExp = null;
		LetExp lastLetExp = null;
		for (LetVariableCS csLetVariable : csLetExp.getVariable()) {
			LetExp letExp = context.refreshMonikeredElement(LetExp.class,
				PivotPackage.Literals.LET_EXP, letMoniker);
			Variable variable = context.refreshNamedElement(Variable.class,
				PivotPackage.Literals.VARIABLE, csLetVariable);
			letExp.setVariable(variable);		
			ExpCS csInitExpression = csLetVariable.getInitExpression();
			OclExpression initExpression = context.refreshExpTree(OclExpression.class, csInitExpression);
			variable.setInitExpression(initExpression);
			Type initType = initExpression != null ? initExpression.getType() : null;
			TypedRefCS csVariableType = csLetVariable.getOwnedType();
			Type variableType = csVariableType != null ? PivotUtil.getPivot(Type.class, csVariableType) : null;
			if (variableType == null) {
				variableType = initType;
			}
			context.setType(variable, variableType);
			
			if (lastLetExp != null) {
				lastLetExp.setIn(letExp);
				context.usePivotElement(csLetExp, letExp);
			}
			else {
				firstLetExp = letExp;
				context.installPivotElement(csLetExp, firstLetExp);
			}
			lastLetExp = letExp;
			letMoniker = letMoniker + PivotConstants.MONIKER_SCOPE_SEPARATOR + "in" + PivotConstants.MONIKER_OPERATOR_SEPARATOR + PivotConstants.MONIKER_LET_EXP;
		}
		if (lastLetExp != null) {
			OclExpression in = context.refreshExpTree(OclExpression.class, csLetExp.getIn());
			lastLetExp.setIn(in);
			Type type = in.getType();
			for (OclExpression letExp = firstLetExp; letExp != in; letExp = ((LetExp)letExp).getIn()) {
				context.setType(letExp, type);
			}
		}
		return firstLetExp;
	}

	@Override
	public MonikeredElement visitLetVariableCS(LetVariableCS csLetVariable) {
		return null;	// Handled by parent
	}

	@Override
	public MonikeredElement visitNameExpCS(NameExpCS csNameExp) {
		EObject eContainer = csNameExp.eContainer();
		if (eContainer instanceof NavigatingExpCS) {
			EObject eContainerContainer = eContainer.eContainer();
			if (eContainerContainer instanceof NamedExpCS) {
				logger.warn("Unsupported '" + eContainerContainer.eClass().getName() + "' for () navigation");
			}
			return null;
		}
//		else if (eContainer instanceof IndexExpCS) {
//			EObject eContainerContainer = eContainer.eContainer();
//			if (eContainerContainer instanceof NamedExpCS) {
//				logger.warn("Unsupported '" + eContainerContainer.eClass().getName() + "' for [] navigation");
//			}
//			return new IndexExpCSCompletion(context, (IndexExpCS) eContainer);
//		}
		else {
			NamedElement element = csNameExp.getElement();
			if (element.eIsProxy()) {
				addError(csNameExp, OCLMessages.ErrorUnresolvedName, csNameExp);
//				logger.warn("Unresolved NameExpCS " + csNameExp);		// FIXME
			}
			else if (element instanceof VariableDeclaration) {
				return handleVariableExp(csNameExp, (VariableDeclaration)element);
			}
			else if (element instanceof Property) {
				return handlePropertyCall(csNameExp, (Property) element);
			}
			else if (element instanceof Operation) {
				addError(csNameExp, "No parameters for operation " + element.getName());
			}
			else if (element instanceof Type) {
				return handleTypeExp(csNameExp, (Type) element);
			}
			else {
				logger.warn("Unsupported NameExpCS " + element.eClass().getName());		// FIXME
			}
			return null;
		}
	}

	@Override
	public MonikeredElement visitNavigatingArgCS(NavigatingArgCS csNavigatingArg) {
		OclExpression pivot = PivotUtil.getPivot(OclExpression.class, csNavigatingArg.getName());
		context.installPivotElement(csNavigatingArg, pivot);
		return pivot;
	}

	@Override
	public MonikeredElement visitNavigatingExpCS(NavigatingExpCS csNavigatingExp) {
		if (csNavigatingExp.getParent() instanceof NavigationOperatorCS) {
			return PivotUtil.getPivot(OclExpression.class, csNavigatingExp);
		}
		else {
			return handleNavigatingExpCS(csNavigatingExp);
		}
	}

	@Override
	public CallExp visitNavigationOperatorCS(NavigationOperatorCS csNavigationOperator) {
		CallExp navigatingExp = handleNavigatingExpCS((NavigatingExpCS) csNavigationOperator.getArgument());
		Type requiredSourceType = null;
		if (navigatingExp instanceof LoopExp) {
			requiredSourceType = ((LoopExp)navigatingExp).getReferredIteration().getClass_();
		}
		else if (navigatingExp instanceof OperationCallExp) {
			requiredSourceType = ((OperationCallExp)navigatingExp).getReferredOperation().getClass_();
		}
		else {
			return null;
		}
		OclExpression source = navigatingExp.getSource();
		Type actualSourceType = source.getType();
		if ((requiredSourceType instanceof CollectionType) && !(actualSourceType instanceof CollectionType)) {
			OperationCallExp expression = context.refreshMonikeredElement(OperationCallExp.class, PivotPackage.Literals.OPERATION_CALL_EXP, csNavigationOperator);
			expression.setImplicit(true);
			expression.setSource(source);
			expression.setName("oclAsSet");
			Operation resolvedOperation = context.resolveOperationCall(expression);
			expression.setReferredOperation(resolvedOperation);
			expression.setType(pivotManager.getCollectionType("Set", actualSourceType));
			navigatingExp.setSource(expression);
		}
		context.reusePivotElement(csNavigationOperator, navigatingExp);
		return navigatingExp;
	}

	@Override
	public MonikeredElement visitNestedExpCS(NestedExpCS csNestedExp) {
		OclExpression pivot = context.refreshExpTree(OclExpression.class, csNestedExp.getSource());
		context.reusePivotElement(csNestedExp, pivot);
		return pivot;
	}

	@Override
	public MonikeredElement visitNullLiteralExpCS(NullLiteralExpCS csNullLiteralExp) {
		NullLiteralExp expression = context.refreshExpression(NullLiteralExp.class, PivotPackage.Literals.NULL_LITERAL_EXP, csNullLiteralExp);
		expression.setType(pivotManager.getNullType());
		return expression;
	}

	@Override
	public MonikeredElement visitNumberLiteralExpCS(NumberLiteralExpCS csNumberLiteralExp) {
		Number number = csNumberLiteralExp.getName();
		if (number instanceof BigDecimal) {
			RealLiteralExp expression = context.refreshExpression(RealLiteralExp.class, PivotPackage.Literals.REAL_LITERAL_EXP, csNumberLiteralExp);
			expression.setRealSymbol((BigDecimal) number);
			expression.setType(pivotManager.getRealType());
			return expression;
		}
		BigInteger bigInteger = (BigInteger) number;
		if (bigInteger.signum() < 0) {
			IntegerLiteralExp expression = context.refreshExpression(IntegerLiteralExp.class, PivotPackage.Literals.INTEGER_LITERAL_EXP, csNumberLiteralExp);
			expression.setIntegerSymbol(bigInteger);
			expression.setType(pivotManager.getIntegerType());
			return expression;
		}
		else {
			UnlimitedNaturalLiteralExp expression = context.refreshExpression(UnlimitedNaturalLiteralExp.class, PivotPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP, csNumberLiteralExp);
			expression.setUnlimitedNaturalSymbol(bigInteger);
			expression.setType(pivotManager.getUnlimitedNaturalType());
			return expression;
		}
	}

	@Override
	public MonikeredElement visitOperatorCS(OperatorCS object) {
		return null;
	}

	@Override
	public MonikeredElement visitPrefixExpCS(PrefixExpCS csPrefixExp) {
		if (!(csPrefixExp.eContainer() instanceof InfixExpCS)) {
			initializePrefixOperators(csPrefixExp, null);
		}
		OclExpression pivotChild = context.refreshExpTree(OclExpression.class, csPrefixExp.getOwnedExpression());
		List<UnaryOperatorCS> csOperators = csPrefixExp.getOwnedOperator();
		for (int i = csOperators.size(); --i >= 0; ) {
			UnaryOperatorCS csUnaryOperator = csOperators.get(i);
			ExpCS csChild = csUnaryOperator.getSource();
			if (csChild != null) {
//				csChild.setParent(csUnaryOperator);
				OperationCallExp pivotElement = context.refreshExpTree(OperationCallExp.class, csUnaryOperator);
				pivotElement.setSource(pivotChild);
//				ExpCS csSource = csUnaryOperator.getSource();
//				if (csSource != null) {
//					OclExpression pivotChild = PivotUtil.getPivot(OclExpression.class, csSource);
//					pivotElement.setSource(pivotChild);
//				}
				Operation operation = context.resolveOperationCall(pivotElement);
				if (operation == null) {
					addError(csUnaryOperator, "Failed to resolve " + pivotElement);
				}
				pivotElement.setReferredOperation(operation);
				if (operation != null) {
					context.setType(pivotElement, operation.getType());
				}
				pivotChild = pivotElement;
			}
		}
		context.reusePivotElement(csPrefixExp, pivotChild);
		return pivotChild;
	}

	@Override
	public MonikeredElement visitSelfExpCS(SelfExpCS csSelfExp) {
		VariableExp expression = context.refreshExpression(VariableExp.class, PivotPackage.Literals.VARIABLE_EXP, csSelfExp);
		ScopeCSAdapter scopeAdapter = ElementUtil.getScopeCSAdapter(csSelfExp);
		EnvironmentView environmentView = new EnvironmentView(PivotPackage.Literals.EXPRESSION_IN_OCL__CONTEXT_VARIABLE, "self");
		ScopeView scopeView = scopeAdapter.getOuterScopeView(null);
		scopeView.computeLookup(environmentView);
		VariableDeclaration variableDeclaration = (VariableDeclaration) environmentView.getContent();
		expression.setReferredVariable(variableDeclaration);
		context.setType(expression, variableDeclaration.getType());
		return expression;
	}

	@Override
	public MonikeredElement visitStringLiteralExpCS(StringLiteralExpCS csStringLiteralExp) {
		StringLiteralExp expression = context.refreshExpression(StringLiteralExp.class, PivotPackage.Literals.STRING_LITERAL_EXP, csStringLiteralExp);
		List<String> names = csStringLiteralExp.getName();
		if (names.size() == 0) {
			expression.setStringSymbol("");
		}
		else if (names.size() == 1) {
			expression.setStringSymbol(names.get(0));
		}
		else {
			StringBuffer s = new StringBuffer();
			for (String name : names) {
				s.append(name);
			}
			expression.setStringSymbol(s.toString());
		}
		expression.setType(pivotManager.getStringType());
		return expression;
	}

	@Override
	public MonikeredElement visitTupleLiteralExpCS(TupleLiteralExpCS csTupleLiteralExp) {
		TupleLiteralExp expression = context.refreshMonikeredElement(TupleLiteralExp.class, PivotPackage.Literals.TUPLE_LITERAL_EXP, csTupleLiteralExp);	
		for (TupleLiteralPartCS csPart : csTupleLiteralExp.getOwnedParts()) {
			@SuppressWarnings("unused")
			TupleLiteralPart pivotPart = context.refreshExpTree(TupleLiteralPart.class, csPart);
		}
		context.refreshPivotList(TupleLiteralPart.class, expression.getParts(), csTupleLiteralExp.getOwnedParts());
		String tupleTypeName = "Tuple"; //ownedCollectionType.getName();
		Type type = pivotManager.getTupleType(tupleTypeName, expression.getParts());
		context.setType(expression, type);
		return expression;
	}

	@Override
	public MonikeredElement visitTupleLiteralPartCS(TupleLiteralPartCS csTupleLiteralPart) {
		TupleLiteralPart pivotElement = context.refreshNamedElement(TupleLiteralPart.class, PivotPackage.Literals.TUPLE_LITERAL_PART, csTupleLiteralPart);	
		OclExpression initExpression = context.refreshExpTree(OclExpression.class, csTupleLiteralPart.getInitExpression());
		pivotElement.setInitExpression(initExpression);
		TypedRefCS csType = csTupleLiteralPart.getOwnedType();
		Type type = csType != null ? PivotUtil.getPivot(Type.class, csType) : initExpression.getType();
		context.setType(pivotElement, type);
		return pivotElement;
	}

	@Override
	public MonikeredElement visitTypeLiteralExpCS(TypeLiteralExpCS csTypeLiteralExp) {
		TypedRefCS csType = csTypeLiteralExp.getOwnedType();
//		context.visitInOrder(csType, null);
		Type type = PivotUtil.getPivot(Type.class, csType);
		return handleTypeExp(csTypeLiteralExp, type);
	}

	@Override
	public MonikeredElement visitUnaryOperatorCS(UnaryOperatorCS csUnaryOperator) {
		OperationCallExp expression = context.refreshExpression(OperationCallExp.class, PivotPackage.Literals.OPERATION_CALL_EXP, csUnaryOperator);
		context.refreshName(expression, csUnaryOperator.getName());
		return expression; // new UnaryOperatorCSCompletion(context, csUnaryOperator, expression);
	}

	@Override
	public MonikeredElement visitUnlimitedNaturalLiteralExpCS(UnlimitedNaturalLiteralExpCS csUnlimitedNaturalLiteralExp) {
		UnlimitedNaturalLiteralExp expression = context.refreshExpression(UnlimitedNaturalLiteralExp.class, PivotPackage.Literals.UNLIMITED_NATURAL_LITERAL_EXP, csUnlimitedNaturalLiteralExp);
		expression.setName("*");
		expression.setType(pivotManager.getUnlimitedNaturalType());
		expression.setUnlimitedNaturalSymbol(BigInteger.valueOf(-1));
		return expression;
	}

	@Override
	public MonikeredElement visitVariableCS(VariableCS csVariable) {
		Variable variable = context.refreshNamedElement(Variable.class,
			PivotPackage.Literals.VARIABLE, csVariable);
		OclExpression initExpression = PivotUtil.getPivot(OclExpression.class, csVariable.getInitExpression());
		Type variableType = PivotUtil.getPivot(Type.class, csVariable.getOwnedType());
		// FIXME deduce from initType
		variable.setInitExpression(initExpression);
		variable.setType(variableType);
		return variable;
	}	
}