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
 * This code is auto-generated
 * from: /org.eclipse.ocl.examples.pivot/model/pivot.ecore
 * by: org.eclipse.ocl.examples.build.acceleo.GenerateOclMetaModel.java
 * defined by: org.eclipse.ocl.examples.build.acceleo.generateOclMetaModel.mtl
 * invoked by: org.eclipse.ocl.examples.build.utilities.OclMetaModelCodeGenerator.java
 * from: org.eclipse.ocl.examples.build.GenerateOclMetaModel.mwe2
 *
 * Do not edit it.
 *
 * $Id: OclMetaModel.java,v 1.1.2.3 2011/01/07 12:14:08 ewillink Exp $
 */
package	org.eclipse.ocl.examples.pivot.model;

import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.ocl.examples.pivot.*;
import org.eclipse.ocl.examples.pivot.Class;
import org.eclipse.ocl.examples.pivot.Package;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;

/**
 * This is the http://www.eclipse.org/ocl/3.1.0/Pivot Pivot Model of the Pivot Model
 * auto-generated from /org.eclipse.ocl.examples.pivot/model/pivot.ecore.
 * It facilitates efficient model loading without the overheads of model reading.
 */
@SuppressWarnings("nls")
public class OclMetaModel extends XMLResourceImpl
{
	/**
	 *	Create and return a pivot model of the Pivot Model.
	 */
	public static Package create(Class classifierType)
	{
		Package symbol_1 = PivotFactory.eINSTANCE.createPackage(); // pivot
		Class symbol_2 = PivotFactory.eINSTANCE.createClass(); // pivot|Annotation
		
		Class symbol_3 = PivotFactory.eINSTANCE.createClass(); // pivot|AnyType
		
		Class symbol_4 = PivotFactory.eINSTANCE.createClass(); // pivot|AssociationClass
		
		Class symbol_5 = PivotFactory.eINSTANCE.createClass(); // pivot|AssociationClassCallExp
		
		Class symbol_6 = PivotFactory.eINSTANCE.createClass(); // pivot|AssociativityKind
		
		Class symbol_7 = PivotFactory.eINSTANCE.createClass(); // pivot|BagType
		
		Class symbol_8 = PivotFactory.eINSTANCE.createClass(); // pivot|Boolean
		
		Class symbol_9 = PivotFactory.eINSTANCE.createClass(); // pivot|BooleanLiteralExp
		
		Class symbol_10 = PivotFactory.eINSTANCE.createClass(); // pivot|CallExp
		
		Class symbol_11 = PivotFactory.eINSTANCE.createClass(); // pivot|CallOperationAction
		
		Class symbol_12 = PivotFactory.eINSTANCE.createClass(); // pivot|CallableImplementation
		
		Class symbol_13 = PivotFactory.eINSTANCE.createClass(); // pivot|Class
		
		Class symbol_14 = PivotFactory.eINSTANCE.createClass(); // pivot|CollectionItem
		
		Class symbol_15 = PivotFactory.eINSTANCE.createClass(); // pivot|CollectionKind
		
		Class symbol_16 = PivotFactory.eINSTANCE.createClass(); // pivot|CollectionLiteralExp
		
		Class symbol_17 = PivotFactory.eINSTANCE.createClass(); // pivot|CollectionLiteralPart
		
		Class symbol_18 = PivotFactory.eINSTANCE.createClass(); // pivot|CollectionRange
		
		Class symbol_19 = PivotFactory.eINSTANCE.createClass(); // pivot|CollectionType
		
		Class symbol_20 = PivotFactory.eINSTANCE.createClass(); // pivot|Comment
		
		Class symbol_21 = PivotFactory.eINSTANCE.createClass(); // pivot|CompleteEnvironment
		
		Class symbol_22 = PivotFactory.eINSTANCE.createClass(); // pivot|CompleteOperation
		
		Class symbol_23 = PivotFactory.eINSTANCE.createClass(); // pivot|CompletePackage
		
		Class symbol_24 = PivotFactory.eINSTANCE.createClass(); // pivot|CompleteProperty
		
		Class symbol_25 = PivotFactory.eINSTANCE.createClass(); // pivot|CompleteType
		
		Class symbol_26 = PivotFactory.eINSTANCE.createClass(); // pivot|Constraint
		
		Class symbol_27 = PivotFactory.eINSTANCE.createClass(); // pivot|DataType
		
		Class symbol_28 = PivotFactory.eINSTANCE.createClass(); // pivot|Detail
		
		Class symbol_29 = PivotFactory.eINSTANCE.createClass(); // pivot|Element
		
		Class symbol_30 = PivotFactory.eINSTANCE.createClass(); // pivot|EnumLiteralExp
		
		Class symbol_31 = PivotFactory.eINSTANCE.createClass(); // pivot|Enumeration
		
		Class symbol_32 = PivotFactory.eINSTANCE.createClass(); // pivot|EnumerationLiteral
		
		Class symbol_33 = PivotFactory.eINSTANCE.createClass(); // pivot|ExpressionInOcl
		
		Class symbol_34 = PivotFactory.eINSTANCE.createClass(); // pivot|Feature
		
		Class symbol_35 = PivotFactory.eINSTANCE.createClass(); // pivot|FeatureCallExp
		
		Class symbol_36 = PivotFactory.eINSTANCE.createClass(); // pivot|IfExp
		
		Class symbol_37 = PivotFactory.eINSTANCE.createClass(); // pivot|ImplementableElement
		
		Class symbol_38 = PivotFactory.eINSTANCE.createClass(); // pivot|Int
		
		Class symbol_39 = PivotFactory.eINSTANCE.createClass(); // pivot|Integer
		
		Class symbol_40 = PivotFactory.eINSTANCE.createClass(); // pivot|IntegerLiteralExp
		
		Class symbol_41 = PivotFactory.eINSTANCE.createClass(); // pivot|InvalidLiteralExp
		
		Class symbol_42 = PivotFactory.eINSTANCE.createClass(); // pivot|InvalidType
		
		Class symbol_43 = PivotFactory.eINSTANCE.createClass(); // pivot|IterateExp
		
		Class symbol_44 = PivotFactory.eINSTANCE.createClass(); // pivot|Iteration
		
		Class symbol_45 = PivotFactory.eINSTANCE.createClass(); // pivot|IteratorExp
		
		Class symbol_46 = PivotFactory.eINSTANCE.createClass(); // pivot|LetExp
		
		Class symbol_47 = PivotFactory.eINSTANCE.createClass(); // pivot|Library
		
		Class symbol_48 = PivotFactory.eINSTANCE.createClass(); // pivot|LiteralExp
		
		Class symbol_49 = PivotFactory.eINSTANCE.createClass(); // pivot|LoopExp
		
		Class symbol_50 = PivotFactory.eINSTANCE.createClass(); // pivot|MessageExp
		
		Class symbol_51 = PivotFactory.eINSTANCE.createClass(); // pivot|MessageType
		
		Class symbol_52 = PivotFactory.eINSTANCE.createClass(); // pivot|MonikeredElement
		
		Class symbol_53 = PivotFactory.eINSTANCE.createClass(); // pivot|MultiplicityElement
		
		Class symbol_54 = PivotFactory.eINSTANCE.createClass(); // pivot|Nameable
		
		Class symbol_55 = PivotFactory.eINSTANCE.createClass(); // pivot|NamedElement
		
		Class symbol_56 = PivotFactory.eINSTANCE.createClass(); // pivot|Namespace
		
		Class symbol_57 = PivotFactory.eINSTANCE.createClass(); // pivot|NavigationCallExp
		
		Class symbol_58 = PivotFactory.eINSTANCE.createClass(); // pivot|NullLiteralExp
		
		Class symbol_59 = PivotFactory.eINSTANCE.createClass(); // pivot|NumericLiteralExp
		
		Class symbol_60 = PivotFactory.eINSTANCE.createClass(); // pivot|Object
		
		Class symbol_61 = PivotFactory.eINSTANCE.createClass(); // pivot|OclExpression
		
		Class symbol_62 = PivotFactory.eINSTANCE.createClass(); // pivot|OpaqueExpression
		
		Class symbol_63 = PivotFactory.eINSTANCE.createClass(); // pivot|Operation
		
		Class symbol_64 = PivotFactory.eINSTANCE.createClass(); // pivot|OperationCallExp
		
		Class symbol_65 = PivotFactory.eINSTANCE.createClass(); // pivot|OperationTemplateParameter
		
		Class symbol_66 = PivotFactory.eINSTANCE.createClass(); // pivot|OrderedSetType
		
		Class symbol_67 = PivotFactory.eINSTANCE.createClass(); // pivot|Package
		
		Class symbol_68 = PivotFactory.eINSTANCE.createClass(); // pivot|PackageableElement
		
		Class symbol_69 = PivotFactory.eINSTANCE.createClass(); // pivot|Parameter
		
		Class symbol_70 = PivotFactory.eINSTANCE.createClass(); // pivot|ParameterableElement
		
		Class symbol_71 = PivotFactory.eINSTANCE.createClass(); // pivot|Pivotable
		
		Class symbol_72 = PivotFactory.eINSTANCE.createClass(); // pivot|Precedence
		
		Class symbol_73 = PivotFactory.eINSTANCE.createClass(); // pivot|PrimitiveLiteralExp
		
		Class symbol_74 = PivotFactory.eINSTANCE.createClass(); // pivot|PrimitiveType
		
		Class symbol_75 = PivotFactory.eINSTANCE.createClass(); // pivot|Property
		
		Class symbol_76 = PivotFactory.eINSTANCE.createClass(); // pivot|PropertyCallExp
		
		Class symbol_77 = PivotFactory.eINSTANCE.createClass(); // pivot|Real
		
		Class symbol_78 = PivotFactory.eINSTANCE.createClass(); // pivot|RealLiteralExp
		
		Class symbol_79 = PivotFactory.eINSTANCE.createClass(); // pivot|SendSignalAction
		
		Class symbol_80 = PivotFactory.eINSTANCE.createClass(); // pivot|SequenceType
		
		Class symbol_81 = PivotFactory.eINSTANCE.createClass(); // pivot|SetType
		
		Class symbol_82 = PivotFactory.eINSTANCE.createClass(); // pivot|Signal
		
		Class symbol_83 = PivotFactory.eINSTANCE.createClass(); // pivot|State
		
		Class symbol_84 = PivotFactory.eINSTANCE.createClass(); // pivot|StateExp
		
		Class symbol_85 = PivotFactory.eINSTANCE.createClass(); // pivot|String
		
		Class symbol_86 = PivotFactory.eINSTANCE.createClass(); // pivot|StringLiteralExp
		
		Class symbol_87 = PivotFactory.eINSTANCE.createClass(); // pivot|TemplateBinding
		
		Class symbol_88 = PivotFactory.eINSTANCE.createClass(); // pivot|TemplateParameter
		
		Class symbol_89 = PivotFactory.eINSTANCE.createClass(); // pivot|TemplateParameterSubstitution
		
		Class symbol_90 = PivotFactory.eINSTANCE.createClass(); // pivot|TemplateParameterType
		
		Class symbol_91 = PivotFactory.eINSTANCE.createClass(); // pivot|TemplateSignature
		
		Class symbol_92 = PivotFactory.eINSTANCE.createClass(); // pivot|TemplateableElement
		
		Class symbol_93 = PivotFactory.eINSTANCE.createClass(); // pivot|Throwable
		
		Class symbol_94 = PivotFactory.eINSTANCE.createClass(); // pivot|TupleLiteralExp
		
		Class symbol_95 = PivotFactory.eINSTANCE.createClass(); // pivot|TupleLiteralPart
		
		Class symbol_96 = PivotFactory.eINSTANCE.createClass(); // pivot|TupleType
		
		Class symbol_97 = PivotFactory.eINSTANCE.createClass(); // pivot|Type
		
		Class symbol_98 = PivotFactory.eINSTANCE.createClass(); // pivot|TypeExp
		
		Class symbol_99 = PivotFactory.eINSTANCE.createClass(); // pivot|TypeTemplateParameter
		
		Class symbol_100 = PivotFactory.eINSTANCE.createClass(); // pivot|TypedElement
		
		Class symbol_101 = PivotFactory.eINSTANCE.createClass(); // pivot|TypedMultiplicityElement
		
		Class symbol_102 = PivotFactory.eINSTANCE.createClass(); // pivot|UnlimitedNatural
		
		Class symbol_103 = PivotFactory.eINSTANCE.createClass(); // pivot|UnlimitedNaturalLiteralExp
		
		Class symbol_104 = PivotFactory.eINSTANCE.createClass(); // pivot|UnspecifiedValueExp
		
		Class symbol_105 = PivotFactory.eINSTANCE.createClass(); // pivot|ValueSpecification
		
		Class symbol_106 = PivotFactory.eINSTANCE.createClass(); // pivot|Variable
		
		Class symbol_107 = PivotFactory.eINSTANCE.createClass(); // pivot|VariableDeclaration
		
		Class symbol_108 = PivotFactory.eINSTANCE.createClass(); // pivot|VariableExp
		
		Class symbol_109 = PivotFactory.eINSTANCE.createClass(); // pivot|Visitable
		
		Class symbol_110 = PivotFactory.eINSTANCE.createClass(); // pivot|Visitor{R,C}
		TemplateSignature symbol_111 = PivotFactory.eINSTANCE.createTemplateSignature(); // pivot|Visitor{R,C}|
		TypeTemplateParameter symbol_112 = PivotFactory.eINSTANCE.createTypeTemplateParameter();
		Class symbol_113 = PivotFactory.eINSTANCE.createClass(); // pivot|Visitor{R,C}?R
		TypeTemplateParameter symbol_114 = PivotFactory.eINSTANCE.createTypeTemplateParameter();
		Class symbol_115 = PivotFactory.eINSTANCE.createClass(); // pivot|Visitor{R,C}?C
		
		
		Class symbol_116 = PivotFactory.eINSTANCE.createClass(); // pivot|VoidType
		

		symbol_1.setName("pivot");
		symbol_1.setNsPrefix("pivot");
		symbol_1.setNsURI("http://www.eclipse.org/ocl/3.1.0/Pivot");
		//
		// pivot.ecore::pivot::Annotation pivot|Annotation
		//
		symbol_2.setName("Annotation");
		symbol_2.getSuperClasses().add(symbol_55);
		{ // pivot.ecore::pivot::Annotation::ownedContent pivot|Annotation|ownedContent
			Property symbol_117 = PivotFactory.eINSTANCE.createProperty();
			symbol_117.setName("ownedContent");
			symbol_117.setType(symbol_29);  // pivot|Element
			symbol_2.getOwnedAttributes().add(symbol_117);
		}
		{ // pivot.ecore::pivot::Annotation::ownedDetail pivot|Annotation|ownedDetail
			Property symbol_118 = PivotFactory.eINSTANCE.createProperty();
			symbol_118.setName("ownedDetail");
			symbol_118.setType(symbol_28);  // pivot|Detail
			symbol_2.getOwnedAttributes().add(symbol_118);
		}
		{ // pivot.ecore::pivot::Annotation::reference pivot|Annotation|reference
			Property symbol_119 = PivotFactory.eINSTANCE.createProperty();
			symbol_119.setName("reference");
			symbol_119.setType(symbol_29);  // pivot|Element
			symbol_2.getOwnedAttributes().add(symbol_119);
		}
		symbol_1.getOwnedTypes().add(symbol_2);
		//
		// pivot.ecore::pivot::AnyType pivot|AnyType
		//
		symbol_3.setName("AnyType");
		symbol_3.getSuperClasses().add(symbol_13);
		symbol_1.getOwnedTypes().add(symbol_3);
		//
		// pivot.ecore::pivot::AssociationClass pivot|AssociationClass
		//
		symbol_4.setName("AssociationClass");
		symbol_4.getSuperClasses().add(symbol_13);
		{ // pivot.ecore::pivot::AssociationClass::unownedAttribute pivot|AssociationClass|unownedAttribute
			Property symbol_120 = PivotFactory.eINSTANCE.createProperty();
			symbol_120.setName("unownedAttribute");
			symbol_120.setType(symbol_75);  // pivot|Property
			symbol_4.getOwnedAttributes().add(symbol_120);
		}
		symbol_1.getOwnedTypes().add(symbol_4);
		//
		// pivot.ecore::pivot::AssociationClassCallExp pivot|AssociationClassCallExp
		//
		symbol_5.setName("AssociationClassCallExp");
		symbol_5.getSuperClasses().add(symbol_57);
		{ // pivot.ecore::pivot::AssociationClassCallExp::referredAssociationClass pivot|AssociationClassCallExp|referredAssociationClass
			Property symbol_121 = PivotFactory.eINSTANCE.createProperty();
			symbol_121.setName("referredAssociationClass");
			symbol_121.setType(symbol_4);  // pivot|AssociationClass
			symbol_5.getOwnedAttributes().add(symbol_121);
		}
		symbol_1.getOwnedTypes().add(symbol_5);
		//
		// pivot.ecore::pivot::AssociativityKind pivot|AssociativityKind
		//
		symbol_6.setName("AssociativityKind");
		symbol_1.getOwnedTypes().add(symbol_6);
		//
		// pivot.ecore::pivot::BagType pivot|BagType
		//
		symbol_7.setName("BagType");
		symbol_7.getSuperClasses().add(symbol_19);
		symbol_1.getOwnedTypes().add(symbol_7);
		//
		// pivot.ecore::pivot::Boolean pivot|Boolean
		//
		symbol_8.setName("Boolean");
		symbol_1.getOwnedTypes().add(symbol_8);
		//
		// pivot.ecore::pivot::BooleanLiteralExp pivot|BooleanLiteralExp
		//
		symbol_9.setName("BooleanLiteralExp");
		symbol_9.getSuperClasses().add(symbol_73);
		{ // pivot.ecore::pivot::BooleanLiteralExp::booleanSymbol pivot|BooleanLiteralExp|booleanSymbol
			Property symbol_122 = PivotFactory.eINSTANCE.createProperty();
			symbol_122.setName("booleanSymbol");
			symbol_122.setType(symbol_8);  // pivot|Boolean
			symbol_9.getOwnedAttributes().add(symbol_122);
		}
		symbol_1.getOwnedTypes().add(symbol_9);
		//
		// pivot.ecore::pivot::CallExp pivot|CallExp
		//
		symbol_10.setName("CallExp");
		symbol_10.getSuperClasses().add(symbol_61);
		{ // pivot.ecore::pivot::CallExp::implicit pivot|CallExp|implicit
			Property symbol_123 = PivotFactory.eINSTANCE.createProperty();
			symbol_123.setName("implicit");
			symbol_123.setType(symbol_8);  // pivot|Boolean
			symbol_10.getOwnedAttributes().add(symbol_123);
		}
		{ // pivot.ecore::pivot::CallExp::source pivot|CallExp|source
			Property symbol_124 = PivotFactory.eINSTANCE.createProperty();
			symbol_124.setName("source");
			symbol_124.setType(symbol_61);  // pivot|OclExpression
			symbol_10.getOwnedAttributes().add(symbol_124);
		}
		symbol_1.getOwnedTypes().add(symbol_10);
		//
		// pivot.ecore::pivot::CallOperationAction pivot|CallOperationAction
		//
		symbol_11.setName("CallOperationAction");
		symbol_11.getSuperClasses().add(symbol_55);
		{ // pivot.ecore::pivot::CallOperationAction::operation pivot|CallOperationAction|operation
			Property symbol_125 = PivotFactory.eINSTANCE.createProperty();
			symbol_125.setName("operation");
			symbol_125.setType(symbol_63);  // pivot|Operation
			symbol_11.getOwnedAttributes().add(symbol_125);
		}
		symbol_1.getOwnedTypes().add(symbol_11);
		//
		// pivot.ecore::pivot::CallableImplementation pivot|CallableImplementation
		//
		symbol_12.setName("CallableImplementation");
		symbol_1.getOwnedTypes().add(symbol_12);
		//
		// pivot.ecore::pivot::Class pivot|Class
		//
		symbol_13.setName("Class");
		symbol_13.getSuperClasses().add(symbol_97);
		symbol_13.getSuperClasses().add(symbol_56);
		{ // pivot.ecore::pivot::Class::isAbstract pivot|Class|isAbstract
			Property symbol_126 = PivotFactory.eINSTANCE.createProperty();
			symbol_126.setName("isAbstract");
			symbol_126.setType(symbol_8);  // pivot|Boolean
			symbol_13.getOwnedAttributes().add(symbol_126);
		}
		{ // pivot.ecore::pivot::Class::isInterface pivot|Class|isInterface
			Property symbol_127 = PivotFactory.eINSTANCE.createProperty();
			symbol_127.setName("isInterface");
			symbol_127.setType(symbol_8);  // pivot|Boolean
			symbol_13.getOwnedAttributes().add(symbol_127);
		}
		{ // pivot.ecore::pivot::Class::ownedAttribute pivot|Class|ownedAttribute
			Property symbol_128 = PivotFactory.eINSTANCE.createProperty();
			symbol_128.setName("ownedAttribute");
			symbol_128.setType(symbol_75);  // pivot|Property
			symbol_13.getOwnedAttributes().add(symbol_128);
		}
		{ // pivot.ecore::pivot::Class::ownedOperation pivot|Class|ownedOperation
			Property symbol_129 = PivotFactory.eINSTANCE.createProperty();
			symbol_129.setName("ownedOperation");
			symbol_129.setType(symbol_63);  // pivot|Operation
			symbol_13.getOwnedAttributes().add(symbol_129);
		}
		{ // pivot.ecore::pivot::Class::subClass pivot|Class|subClass
			Property symbol_130 = PivotFactory.eINSTANCE.createProperty();
			symbol_130.setName("subClass");
			symbol_130.setType(symbol_13);  // pivot|Class
			symbol_13.getOwnedAttributes().add(symbol_130);
		}
		{ // pivot.ecore::pivot::Class::superClass pivot|Class|superClass
			Property symbol_131 = PivotFactory.eINSTANCE.createProperty();
			symbol_131.setName("superClass");
			symbol_131.setType(symbol_13);  // pivot|Class
			symbol_13.getOwnedAttributes().add(symbol_131);
		}
		symbol_1.getOwnedTypes().add(symbol_13);
		//
		// pivot.ecore::pivot::CollectionItem pivot|CollectionItem
		//
		symbol_14.setName("CollectionItem");
		symbol_14.getSuperClasses().add(symbol_17);
		{ // pivot.ecore::pivot::CollectionItem::item pivot|CollectionItem|item
			Property symbol_132 = PivotFactory.eINSTANCE.createProperty();
			symbol_132.setName("item");
			symbol_132.setType(symbol_61);  // pivot|OclExpression
			symbol_14.getOwnedAttributes().add(symbol_132);
		}
		symbol_1.getOwnedTypes().add(symbol_14);
		//
		// pivot.ecore::pivot::CollectionKind pivot|CollectionKind
		//
		symbol_15.setName("CollectionKind");
		symbol_1.getOwnedTypes().add(symbol_15);
		//
		// pivot.ecore::pivot::CollectionLiteralExp pivot|CollectionLiteralExp
		//
		symbol_16.setName("CollectionLiteralExp");
		symbol_16.getSuperClasses().add(symbol_48);
		{ // pivot.ecore::pivot::CollectionLiteralExp::kind pivot|CollectionLiteralExp|kind
			Property symbol_133 = PivotFactory.eINSTANCE.createProperty();
			symbol_133.setName("kind");
			symbol_133.setType(symbol_15);  // pivot|CollectionKind
			symbol_16.getOwnedAttributes().add(symbol_133);
		}
		{ // pivot.ecore::pivot::CollectionLiteralExp::part pivot|CollectionLiteralExp|part
			Property symbol_134 = PivotFactory.eINSTANCE.createProperty();
			symbol_134.setName("part");
			symbol_134.setType(symbol_17);  // pivot|CollectionLiteralPart
			symbol_16.getOwnedAttributes().add(symbol_134);
		}
		symbol_1.getOwnedTypes().add(symbol_16);
		//
		// pivot.ecore::pivot::CollectionLiteralPart pivot|CollectionLiteralPart
		//
		symbol_17.setName("CollectionLiteralPart");
		symbol_17.getSuperClasses().add(symbol_100);
		symbol_1.getOwnedTypes().add(symbol_17);
		//
		// pivot.ecore::pivot::CollectionRange pivot|CollectionRange
		//
		symbol_18.setName("CollectionRange");
		symbol_18.getSuperClasses().add(symbol_17);
		{ // pivot.ecore::pivot::CollectionRange::first pivot|CollectionRange|first
			Property symbol_135 = PivotFactory.eINSTANCE.createProperty();
			symbol_135.setName("first");
			symbol_135.setType(symbol_61);  // pivot|OclExpression
			symbol_18.getOwnedAttributes().add(symbol_135);
		}
		{ // pivot.ecore::pivot::CollectionRange::last pivot|CollectionRange|last
			Property symbol_136 = PivotFactory.eINSTANCE.createProperty();
			symbol_136.setName("last");
			symbol_136.setType(symbol_61);  // pivot|OclExpression
			symbol_18.getOwnedAttributes().add(symbol_136);
		}
		symbol_1.getOwnedTypes().add(symbol_18);
		//
		// pivot.ecore::pivot::CollectionType pivot|CollectionType
		//
		symbol_19.setName("CollectionType");
		symbol_19.getSuperClasses().add(symbol_27);
		{ // pivot.ecore::pivot::CollectionType::elementType pivot|CollectionType|elementType
			Property symbol_137 = PivotFactory.eINSTANCE.createProperty();
			symbol_137.setName("elementType");
			symbol_137.setType(symbol_97);  // pivot|Type
			symbol_19.getOwnedAttributes().add(symbol_137);
		}
		symbol_1.getOwnedTypes().add(symbol_19);
		//
		// pivot.ecore::pivot::Comment pivot|Comment
		//
		symbol_20.setName("Comment");
		symbol_20.getSuperClasses().add(symbol_29);
		{ // pivot.ecore::pivot::Comment::annotatedElement pivot|Comment|annotatedElement
			Property symbol_138 = PivotFactory.eINSTANCE.createProperty();
			symbol_138.setName("annotatedElement");
			symbol_138.setType(symbol_29);  // pivot|Element
			symbol_20.getOwnedAttributes().add(symbol_138);
		}
		{ // pivot.ecore::pivot::Comment::body pivot|Comment|body
			Property symbol_139 = PivotFactory.eINSTANCE.createProperty();
			symbol_139.setName("body");
			symbol_139.setType(symbol_85);  // pivot|String
			symbol_20.getOwnedAttributes().add(symbol_139);
		}
		symbol_1.getOwnedTypes().add(symbol_20);
		//
		// pivot.ecore::pivot::CompleteEnvironment pivot|CompleteEnvironment
		//
		symbol_21.setName("CompleteEnvironment");
		symbol_21.getSuperClasses().add(symbol_67);
		{	// pivot.ecore::pivot::CompleteEnvironment::getCompleteOperation() pivot|CompleteEnvironment|getCompleteOperation(pivot|Operation)
			Operation symbol_140 = PivotFactory.eINSTANCE.createOperation();
			symbol_140.setName("getCompleteOperation");
			symbol_140.setType(symbol_22);  // pivot|CompleteOperation
			Parameter symbol_141 = PivotFactory.eINSTANCE.createParameter();
			symbol_141.setName("model");
			symbol_141.setType(symbol_63);  // pivot|Operation
			symbol_140.getOwnedParameters().add(symbol_141);
			symbol_21.getOwnedOperations().add(symbol_140);
		}
		{	// pivot.ecore::pivot::CompleteEnvironment::getCompletePackage() pivot|CompleteEnvironment|getCompletePackage(pivot|Package)
			Operation symbol_142 = PivotFactory.eINSTANCE.createOperation();
			symbol_142.setName("getCompletePackage");
			symbol_142.setType(symbol_23);  // pivot|CompletePackage
			Parameter symbol_143 = PivotFactory.eINSTANCE.createParameter();
			symbol_143.setName("model");
			symbol_143.setType(symbol_67);  // pivot|Package
			symbol_142.getOwnedParameters().add(symbol_143);
			symbol_21.getOwnedOperations().add(symbol_142);
		}
		{	// pivot.ecore::pivot::CompleteEnvironment::getCompleteProperty() pivot|CompleteEnvironment|getCompleteProperty(pivot|Property)
			Operation symbol_144 = PivotFactory.eINSTANCE.createOperation();
			symbol_144.setName("getCompleteProperty");
			symbol_144.setType(symbol_24);  // pivot|CompleteProperty
			Parameter symbol_145 = PivotFactory.eINSTANCE.createParameter();
			symbol_145.setName("model");
			symbol_145.setType(symbol_75);  // pivot|Property
			symbol_144.getOwnedParameters().add(symbol_145);
			symbol_21.getOwnedOperations().add(symbol_144);
		}
		{	// pivot.ecore::pivot::CompleteEnvironment::getCompleteType() pivot|CompleteEnvironment|getCompleteType(pivot|Type)
			Operation symbol_146 = PivotFactory.eINSTANCE.createOperation();
			symbol_146.setName("getCompleteType");
			symbol_146.setType(symbol_25);  // pivot|CompleteType
			Parameter symbol_147 = PivotFactory.eINSTANCE.createParameter();
			symbol_147.setName("model");
			symbol_147.setType(symbol_97);  // pivot|Type
			symbol_146.getOwnedParameters().add(symbol_147);
			symbol_21.getOwnedOperations().add(symbol_146);
		}
		symbol_1.getOwnedTypes().add(symbol_21);
		//
		// pivot.ecore::pivot::CompleteOperation pivot|CompleteOperation
		//
		symbol_22.setName("CompleteOperation");
		symbol_22.getSuperClasses().add(symbol_63);
		{ // pivot.ecore::pivot::CompleteOperation::completeEnvironment pivot|CompleteOperation|completeEnvironment
			Property symbol_148 = PivotFactory.eINSTANCE.createProperty();
			symbol_148.setName("completeEnvironment");
			symbol_148.setType(symbol_21);  // pivot|CompleteEnvironment
			symbol_22.getOwnedAttributes().add(symbol_148);
		}
		{ // pivot.ecore::pivot::CompleteOperation::completeParameter pivot|CompleteOperation|completeParameter
			Property symbol_149 = PivotFactory.eINSTANCE.createProperty();
			symbol_149.setName("completeParameter");
			symbol_149.setType(symbol_69);  // pivot|Parameter
			symbol_22.getOwnedAttributes().add(symbol_149);
		}
		{ // pivot.ecore::pivot::CompleteOperation::model pivot|CompleteOperation|model
			Property symbol_150 = PivotFactory.eINSTANCE.createProperty();
			symbol_150.setName("model");
			symbol_150.setType(symbol_63);  // pivot|Operation
			symbol_22.getOwnedAttributes().add(symbol_150);
		}
		symbol_1.getOwnedTypes().add(symbol_22);
		//
		// pivot.ecore::pivot::CompletePackage pivot|CompletePackage
		//
		symbol_23.setName("CompletePackage");
		symbol_23.getSuperClasses().add(symbol_67);
		{ // pivot.ecore::pivot::CompletePackage::completeEnvironment pivot|CompletePackage|completeEnvironment
			Property symbol_151 = PivotFactory.eINSTANCE.createProperty();
			symbol_151.setName("completeEnvironment");
			symbol_151.setType(symbol_21);  // pivot|CompleteEnvironment
			symbol_23.getOwnedAttributes().add(symbol_151);
		}
		{ // pivot.ecore::pivot::CompletePackage::completePackage pivot|CompletePackage|completePackage
			Property symbol_152 = PivotFactory.eINSTANCE.createProperty();
			symbol_152.setName("completePackage");
			symbol_152.setType(symbol_23);  // pivot|CompletePackage
			symbol_23.getOwnedAttributes().add(symbol_152);
		}
		{ // pivot.ecore::pivot::CompletePackage::completeType pivot|CompletePackage|completeType
			Property symbol_153 = PivotFactory.eINSTANCE.createProperty();
			symbol_153.setName("completeType");
			symbol_153.setType(symbol_25);  // pivot|CompleteType
			symbol_23.getOwnedAttributes().add(symbol_153);
		}
		{ // pivot.ecore::pivot::CompletePackage::model pivot|CompletePackage|model
			Property symbol_154 = PivotFactory.eINSTANCE.createProperty();
			symbol_154.setName("model");
			symbol_154.setType(symbol_67);  // pivot|Package
			symbol_23.getOwnedAttributes().add(symbol_154);
		}
		symbol_1.getOwnedTypes().add(symbol_23);
		//
		// pivot.ecore::pivot::CompleteProperty pivot|CompleteProperty
		//
		symbol_24.setName("CompleteProperty");
		symbol_24.getSuperClasses().add(symbol_75);
		{ // pivot.ecore::pivot::CompleteProperty::completeEnvironment pivot|CompleteProperty|completeEnvironment
			Property symbol_155 = PivotFactory.eINSTANCE.createProperty();
			symbol_155.setName("completeEnvironment");
			symbol_155.setType(symbol_21);  // pivot|CompleteEnvironment
			symbol_24.getOwnedAttributes().add(symbol_155);
		}
		{ // pivot.ecore::pivot::CompleteProperty::model pivot|CompleteProperty|model
			Property symbol_156 = PivotFactory.eINSTANCE.createProperty();
			symbol_156.setName("model");
			symbol_156.setType(symbol_75);  // pivot|Property
			symbol_24.getOwnedAttributes().add(symbol_156);
		}
		symbol_1.getOwnedTypes().add(symbol_24);
		//
		// pivot.ecore::pivot::CompleteType pivot|CompleteType
		//
		symbol_25.setName("CompleteType");
		symbol_25.getSuperClasses().add(symbol_13);
		{ // pivot.ecore::pivot::CompleteType::completeEnvironment pivot|CompleteType|completeEnvironment
			Property symbol_157 = PivotFactory.eINSTANCE.createProperty();
			symbol_157.setName("completeEnvironment");
			symbol_157.setType(symbol_21);  // pivot|CompleteEnvironment
			symbol_25.getOwnedAttributes().add(symbol_157);
		}
		{ // pivot.ecore::pivot::CompleteType::completeOperation pivot|CompleteType|completeOperation
			Property symbol_158 = PivotFactory.eINSTANCE.createProperty();
			symbol_158.setName("completeOperation");
			symbol_158.setType(symbol_22);  // pivot|CompleteOperation
			symbol_25.getOwnedAttributes().add(symbol_158);
		}
		{ // pivot.ecore::pivot::CompleteType::completeProperty pivot|CompleteType|completeProperty
			Property symbol_159 = PivotFactory.eINSTANCE.createProperty();
			symbol_159.setName("completeProperty");
			symbol_159.setType(symbol_24);  // pivot|CompleteProperty
			symbol_25.getOwnedAttributes().add(symbol_159);
		}
		{ // pivot.ecore::pivot::CompleteType::completeSuperType pivot|CompleteType|completeSuperType
			Property symbol_160 = PivotFactory.eINSTANCE.createProperty();
			symbol_160.setName("completeSuperType");
			symbol_160.setType(symbol_25);  // pivot|CompleteType
			symbol_25.getOwnedAttributes().add(symbol_160);
		}
		{ // pivot.ecore::pivot::CompleteType::model pivot|CompleteType|model
			Property symbol_161 = PivotFactory.eINSTANCE.createProperty();
			symbol_161.setName("model");
			symbol_161.setType(symbol_97);  // pivot|Type
			symbol_25.getOwnedAttributes().add(symbol_161);
		}
		{	// pivot.ecore::pivot::CompleteType::conformsTo() pivot|CompleteType|conformsTo(pivot|CompleteType)
			Operation symbol_162 = PivotFactory.eINSTANCE.createOperation();
			symbol_162.setName("conformsTo");
			symbol_162.setType(symbol_8);  // pivot|Boolean
			Parameter symbol_163 = PivotFactory.eINSTANCE.createParameter();
			symbol_163.setName("aType");
			symbol_163.setType(symbol_25);  // pivot|CompleteType
			symbol_162.getOwnedParameters().add(symbol_163);
			symbol_25.getOwnedOperations().add(symbol_162);
		}
		{	// pivot.ecore::pivot::CompleteType::getCompleteOperations() pivot|CompleteType|getCompleteOperations(pivot|String)
			Operation symbol_164 = PivotFactory.eINSTANCE.createOperation();
			symbol_164.setName("getCompleteOperations");
			symbol_164.setType(symbol_22);  // pivot|CompleteOperation
			Parameter symbol_165 = PivotFactory.eINSTANCE.createParameter();
			symbol_165.setName("name");
			symbol_165.setType(symbol_85);  // pivot|String
			symbol_164.getOwnedParameters().add(symbol_165);
			symbol_25.getOwnedOperations().add(symbol_164);
		}
		{	// pivot.ecore::pivot::CompleteType::getDynamicOperation() pivot|CompleteType|getDynamicOperation(pivot|CompleteOperation)
			Operation symbol_166 = PivotFactory.eINSTANCE.createOperation();
			symbol_166.setName("getDynamicOperation");
			symbol_166.setType(symbol_22);  // pivot|CompleteOperation
			Parameter symbol_167 = PivotFactory.eINSTANCE.createParameter();
			symbol_167.setName("staticOperation");
			symbol_167.setType(symbol_22);  // pivot|CompleteOperation
			symbol_166.getOwnedParameters().add(symbol_167);
			symbol_25.getOwnedOperations().add(symbol_166);
		}
		symbol_1.getOwnedTypes().add(symbol_25);
		//
		// pivot.ecore::pivot::Constraint pivot|Constraint
		//
		symbol_26.setName("Constraint");
		symbol_26.getSuperClasses().add(symbol_55);
		{ // pivot.ecore::pivot::Constraint::constrainedElement pivot|Constraint|constrainedElement
			Property symbol_168 = PivotFactory.eINSTANCE.createProperty();
			symbol_168.setName("constrainedElement");
			symbol_168.setType(symbol_29);  // pivot|Element
			symbol_26.getOwnedAttributes().add(symbol_168);
		}
		{ // pivot.ecore::pivot::Constraint::context pivot|Constraint|context
			Property symbol_169 = PivotFactory.eINSTANCE.createProperty();
			symbol_169.setName("context");
			symbol_169.setType(symbol_55);  // pivot|NamedElement
			symbol_26.getOwnedAttributes().add(symbol_169);
		}
		{ // pivot.ecore::pivot::Constraint::specification pivot|Constraint|specification
			Property symbol_170 = PivotFactory.eINSTANCE.createProperty();
			symbol_170.setName("specification");
			symbol_170.setType(symbol_105);  // pivot|ValueSpecification
			symbol_26.getOwnedAttributes().add(symbol_170);
		}
		{ // pivot.ecore::pivot::Constraint::stereotype pivot|Constraint|stereotype
			Property symbol_171 = PivotFactory.eINSTANCE.createProperty();
			symbol_171.setName("stereotype");
			symbol_171.setType(symbol_85);  // pivot|String
			symbol_26.getOwnedAttributes().add(symbol_171);
		}
		symbol_1.getOwnedTypes().add(symbol_26);
		//
		// pivot.ecore::pivot::DataType pivot|DataType
		//
		symbol_27.setName("DataType");
		symbol_27.getSuperClasses().add(symbol_13);
		{ // pivot.ecore::pivot::DataType::isSerializable pivot|DataType|isSerializable
			Property symbol_172 = PivotFactory.eINSTANCE.createProperty();
			symbol_172.setName("isSerializable");
			symbol_172.setType(symbol_8);  // pivot|Boolean
			symbol_27.getOwnedAttributes().add(symbol_172);
		}
		symbol_1.getOwnedTypes().add(symbol_27);
		//
		// pivot.ecore::pivot::Detail pivot|Detail
		//
		symbol_28.setName("Detail");
		symbol_28.getSuperClasses().add(symbol_55);
		{ // pivot.ecore::pivot::Detail::value pivot|Detail|value
			Property symbol_173 = PivotFactory.eINSTANCE.createProperty();
			symbol_173.setName("value");
			symbol_173.setType(symbol_85);  // pivot|String
			symbol_28.getOwnedAttributes().add(symbol_173);
		}
		symbol_1.getOwnedTypes().add(symbol_28);
		//
		// pivot.ecore::pivot::Element pivot|Element
		//
		symbol_29.setName("Element");
		symbol_29.getSuperClasses().add(symbol_109);
		{ // pivot.ecore::pivot::Element::ownedComment pivot|Element|ownedComment
			Property symbol_174 = PivotFactory.eINSTANCE.createProperty();
			symbol_174.setName("ownedComment");
			symbol_174.setType(symbol_20);  // pivot|Comment
			symbol_29.getOwnedAttributes().add(symbol_174);
		}
		symbol_1.getOwnedTypes().add(symbol_29);
		//
		// pivot.ecore::pivot::EnumLiteralExp pivot|EnumLiteralExp
		//
		symbol_30.setName("EnumLiteralExp");
		symbol_30.getSuperClasses().add(symbol_48);
		{ // pivot.ecore::pivot::EnumLiteralExp::referredEnumLiteral pivot|EnumLiteralExp|referredEnumLiteral
			Property symbol_175 = PivotFactory.eINSTANCE.createProperty();
			symbol_175.setName("referredEnumLiteral");
			symbol_175.setType(symbol_32);  // pivot|EnumerationLiteral
			symbol_30.getOwnedAttributes().add(symbol_175);
		}
		symbol_1.getOwnedTypes().add(symbol_30);
		//
		// pivot.ecore::pivot::Enumeration pivot|Enumeration
		//
		symbol_31.setName("Enumeration");
		symbol_31.getSuperClasses().add(symbol_27);
		{ // pivot.ecore::pivot::Enumeration::ownedLiteral pivot|Enumeration|ownedLiteral
			Property symbol_176 = PivotFactory.eINSTANCE.createProperty();
			symbol_176.setName("ownedLiteral");
			symbol_176.setType(symbol_32);  // pivot|EnumerationLiteral
			symbol_31.getOwnedAttributes().add(symbol_176);
		}
		symbol_1.getOwnedTypes().add(symbol_31);
		//
		// pivot.ecore::pivot::EnumerationLiteral pivot|EnumerationLiteral
		//
		symbol_32.setName("EnumerationLiteral");
		symbol_32.getSuperClasses().add(symbol_55);
		{ // pivot.ecore::pivot::EnumerationLiteral::enumeration pivot|EnumerationLiteral|enumeration
			Property symbol_177 = PivotFactory.eINSTANCE.createProperty();
			symbol_177.setName("enumeration");
			symbol_177.setType(symbol_31);  // pivot|Enumeration
			symbol_32.getOwnedAttributes().add(symbol_177);
		}
		{ // pivot.ecore::pivot::EnumerationLiteral::value pivot|EnumerationLiteral|value
			Property symbol_178 = PivotFactory.eINSTANCE.createProperty();
			symbol_178.setName("value");
			symbol_178.setType(symbol_39);  // pivot|Integer
			symbol_32.getOwnedAttributes().add(symbol_178);
		}
		symbol_1.getOwnedTypes().add(symbol_32);
		//
		// pivot.ecore::pivot::ExpressionInOcl pivot|ExpressionInOcl
		//
		symbol_33.setName("ExpressionInOcl");
		symbol_33.getSuperClasses().add(symbol_62);
		{ // pivot.ecore::pivot::ExpressionInOcl::bodyExpression pivot|ExpressionInOcl|bodyExpression
			Property symbol_179 = PivotFactory.eINSTANCE.createProperty();
			symbol_179.setName("bodyExpression");
			symbol_179.setType(symbol_61);  // pivot|OclExpression
			symbol_33.getOwnedAttributes().add(symbol_179);
		}
		{ // pivot.ecore::pivot::ExpressionInOcl::contextVariable pivot|ExpressionInOcl|contextVariable
			Property symbol_180 = PivotFactory.eINSTANCE.createProperty();
			symbol_180.setName("contextVariable");
			symbol_180.setType(symbol_106);  // pivot|Variable
			symbol_33.getOwnedAttributes().add(symbol_180);
		}
		{ // pivot.ecore::pivot::ExpressionInOcl::parameterVariable pivot|ExpressionInOcl|parameterVariable
			Property symbol_181 = PivotFactory.eINSTANCE.createProperty();
			symbol_181.setName("parameterVariable");
			symbol_181.setType(symbol_106);  // pivot|Variable
			symbol_33.getOwnedAttributes().add(symbol_181);
		}
		{ // pivot.ecore::pivot::ExpressionInOcl::resultVariable pivot|ExpressionInOcl|resultVariable
			Property symbol_182 = PivotFactory.eINSTANCE.createProperty();
			symbol_182.setName("resultVariable");
			symbol_182.setType(symbol_106);  // pivot|Variable
			symbol_33.getOwnedAttributes().add(symbol_182);
		}
		symbol_1.getOwnedTypes().add(symbol_33);
		//
		// pivot.ecore::pivot::Feature pivot|Feature
		//
		symbol_34.setName("Feature");
		symbol_34.getSuperClasses().add(symbol_55);
		symbol_1.getOwnedTypes().add(symbol_34);
		//
		// pivot.ecore::pivot::FeatureCallExp pivot|FeatureCallExp
		//
		symbol_35.setName("FeatureCallExp");
		symbol_35.getSuperClasses().add(symbol_10);
		{ // pivot.ecore::pivot::FeatureCallExp::isPre pivot|FeatureCallExp|isPre
			Property symbol_183 = PivotFactory.eINSTANCE.createProperty();
			symbol_183.setName("isPre");
			symbol_183.setType(symbol_8);  // pivot|Boolean
			symbol_35.getOwnedAttributes().add(symbol_183);
		}
		symbol_1.getOwnedTypes().add(symbol_35);
		//
		// pivot.ecore::pivot::IfExp pivot|IfExp
		//
		symbol_36.setName("IfExp");
		symbol_36.getSuperClasses().add(symbol_61);
		{ // pivot.ecore::pivot::IfExp::condition pivot|IfExp|condition
			Property symbol_184 = PivotFactory.eINSTANCE.createProperty();
			symbol_184.setName("condition");
			symbol_184.setType(symbol_61);  // pivot|OclExpression
			symbol_36.getOwnedAttributes().add(symbol_184);
		}
		{ // pivot.ecore::pivot::IfExp::elseExpression pivot|IfExp|elseExpression
			Property symbol_185 = PivotFactory.eINSTANCE.createProperty();
			symbol_185.setName("elseExpression");
			symbol_185.setType(symbol_61);  // pivot|OclExpression
			symbol_36.getOwnedAttributes().add(symbol_185);
		}
		{ // pivot.ecore::pivot::IfExp::thenExpression pivot|IfExp|thenExpression
			Property symbol_186 = PivotFactory.eINSTANCE.createProperty();
			symbol_186.setName("thenExpression");
			symbol_186.setType(symbol_61);  // pivot|OclExpression
			symbol_36.getOwnedAttributes().add(symbol_186);
		}
		symbol_1.getOwnedTypes().add(symbol_36);
		//
		// pivot.ecore::pivot::ImplementableElement pivot|ImplementableElement
		//
		symbol_37.setName("ImplementableElement");
		{ // pivot.ecore::pivot::ImplementableElement::implementation pivot|ImplementableElement|implementation
			Property symbol_187 = PivotFactory.eINSTANCE.createProperty();
			symbol_187.setName("implementation");
			symbol_187.setType(symbol_12);  // pivot|CallableImplementation
			symbol_37.getOwnedAttributes().add(symbol_187);
		}
		{ // pivot.ecore::pivot::ImplementableElement::implementationClass pivot|ImplementableElement|implementationClass
			Property symbol_188 = PivotFactory.eINSTANCE.createProperty();
			symbol_188.setName("implementationClass");
			symbol_188.setType(symbol_85);  // pivot|String
			symbol_37.getOwnedAttributes().add(symbol_188);
		}
		symbol_1.getOwnedTypes().add(symbol_37);
		//
		// pivot.ecore::pivot::Int pivot|Int
		//
		symbol_38.setName("Int");
		symbol_1.getOwnedTypes().add(symbol_38);
		//
		// pivot.ecore::pivot::Integer pivot|Integer
		//
		symbol_39.setName("Integer");
		symbol_1.getOwnedTypes().add(symbol_39);
		//
		// pivot.ecore::pivot::IntegerLiteralExp pivot|IntegerLiteralExp
		//
		symbol_40.setName("IntegerLiteralExp");
		symbol_40.getSuperClasses().add(symbol_59);
		{ // pivot.ecore::pivot::IntegerLiteralExp::integerSymbol pivot|IntegerLiteralExp|integerSymbol
			Property symbol_189 = PivotFactory.eINSTANCE.createProperty();
			symbol_189.setName("integerSymbol");
			symbol_189.setType(symbol_39);  // pivot|Integer
			symbol_40.getOwnedAttributes().add(symbol_189);
		}
		symbol_1.getOwnedTypes().add(symbol_40);
		//
		// pivot.ecore::pivot::InvalidLiteralExp pivot|InvalidLiteralExp
		//
		symbol_41.setName("InvalidLiteralExp");
		symbol_41.getSuperClasses().add(symbol_48);
		{ // pivot.ecore::pivot::InvalidLiteralExp::expression pivot|InvalidLiteralExp|expression
			Property symbol_190 = PivotFactory.eINSTANCE.createProperty();
			symbol_190.setName("expression");
			symbol_190.setType(symbol_61);  // pivot|OclExpression
			symbol_41.getOwnedAttributes().add(symbol_190);
		}
		{ // pivot.ecore::pivot::InvalidLiteralExp::object pivot|InvalidLiteralExp|object
			Property symbol_191 = PivotFactory.eINSTANCE.createProperty();
			symbol_191.setName("object");
			symbol_191.setType(symbol_60);  // pivot|Object
			symbol_41.getOwnedAttributes().add(symbol_191);
		}
		{ // pivot.ecore::pivot::InvalidLiteralExp::reason pivot|InvalidLiteralExp|reason
			Property symbol_192 = PivotFactory.eINSTANCE.createProperty();
			symbol_192.setName("reason");
			symbol_192.setType(symbol_85);  // pivot|String
			symbol_41.getOwnedAttributes().add(symbol_192);
		}
		{ // pivot.ecore::pivot::InvalidLiteralExp::throwable pivot|InvalidLiteralExp|throwable
			Property symbol_193 = PivotFactory.eINSTANCE.createProperty();
			symbol_193.setName("throwable");
			symbol_193.setType(symbol_93);  // pivot|Throwable
			symbol_41.getOwnedAttributes().add(symbol_193);
		}
		symbol_1.getOwnedTypes().add(symbol_41);
		//
		// pivot.ecore::pivot::InvalidType pivot|InvalidType
		//
		symbol_42.setName("InvalidType");
		symbol_42.getSuperClasses().add(symbol_13);
		symbol_1.getOwnedTypes().add(symbol_42);
		//
		// pivot.ecore::pivot::IterateExp pivot|IterateExp
		//
		symbol_43.setName("IterateExp");
		symbol_43.getSuperClasses().add(symbol_49);
		{ // pivot.ecore::pivot::IterateExp::result pivot|IterateExp|result
			Property symbol_194 = PivotFactory.eINSTANCE.createProperty();
			symbol_194.setName("result");
			symbol_194.setType(symbol_106);  // pivot|Variable
			symbol_43.getOwnedAttributes().add(symbol_194);
		}
		symbol_1.getOwnedTypes().add(symbol_43);
		//
		// pivot.ecore::pivot::Iteration pivot|Iteration
		//
		symbol_44.setName("Iteration");
		symbol_44.getSuperClasses().add(symbol_63);
		{ // pivot.ecore::pivot::Iteration::ownedAccumulator pivot|Iteration|ownedAccumulator
			Property symbol_195 = PivotFactory.eINSTANCE.createProperty();
			symbol_195.setName("ownedAccumulator");
			symbol_195.setType(symbol_69);  // pivot|Parameter
			symbol_44.getOwnedAttributes().add(symbol_195);
		}
		{ // pivot.ecore::pivot::Iteration::ownedIterator pivot|Iteration|ownedIterator
			Property symbol_196 = PivotFactory.eINSTANCE.createProperty();
			symbol_196.setName("ownedIterator");
			symbol_196.setType(symbol_69);  // pivot|Parameter
			symbol_44.getOwnedAttributes().add(symbol_196);
		}
		symbol_1.getOwnedTypes().add(symbol_44);
		//
		// pivot.ecore::pivot::IteratorExp pivot|IteratorExp
		//
		symbol_45.setName("IteratorExp");
		symbol_45.getSuperClasses().add(symbol_49);
		symbol_1.getOwnedTypes().add(symbol_45);
		//
		// pivot.ecore::pivot::LetExp pivot|LetExp
		//
		symbol_46.setName("LetExp");
		symbol_46.getSuperClasses().add(symbol_61);
		{ // pivot.ecore::pivot::LetExp::in pivot|LetExp|in
			Property symbol_197 = PivotFactory.eINSTANCE.createProperty();
			symbol_197.setName("in");
			symbol_197.setType(symbol_61);  // pivot|OclExpression
			symbol_46.getOwnedAttributes().add(symbol_197);
		}
		{ // pivot.ecore::pivot::LetExp::variable pivot|LetExp|variable
			Property symbol_198 = PivotFactory.eINSTANCE.createProperty();
			symbol_198.setName("variable");
			symbol_198.setType(symbol_106);  // pivot|Variable
			symbol_46.getOwnedAttributes().add(symbol_198);
		}
		symbol_1.getOwnedTypes().add(symbol_46);
		//
		// pivot.ecore::pivot::Library pivot|Library
		//
		symbol_47.setName("Library");
		symbol_47.getSuperClasses().add(symbol_67);
		symbol_1.getOwnedTypes().add(symbol_47);
		//
		// pivot.ecore::pivot::LiteralExp pivot|LiteralExp
		//
		symbol_48.setName("LiteralExp");
		symbol_48.getSuperClasses().add(symbol_61);
		symbol_1.getOwnedTypes().add(symbol_48);
		//
		// pivot.ecore::pivot::LoopExp pivot|LoopExp
		//
		symbol_49.setName("LoopExp");
		symbol_49.getSuperClasses().add(symbol_10);
		{ // pivot.ecore::pivot::LoopExp::body pivot|LoopExp|body
			Property symbol_199 = PivotFactory.eINSTANCE.createProperty();
			symbol_199.setName("body");
			symbol_199.setType(symbol_61);  // pivot|OclExpression
			symbol_49.getOwnedAttributes().add(symbol_199);
		}
		{ // pivot.ecore::pivot::LoopExp::iterator pivot|LoopExp|iterator
			Property symbol_200 = PivotFactory.eINSTANCE.createProperty();
			symbol_200.setName("iterator");
			symbol_200.setType(symbol_106);  // pivot|Variable
			symbol_49.getOwnedAttributes().add(symbol_200);
		}
		{ // pivot.ecore::pivot::LoopExp::referredIteration pivot|LoopExp|referredIteration
			Property symbol_201 = PivotFactory.eINSTANCE.createProperty();
			symbol_201.setName("referredIteration");
			symbol_201.setType(symbol_44);  // pivot|Iteration
			symbol_49.getOwnedAttributes().add(symbol_201);
		}
		symbol_1.getOwnedTypes().add(symbol_49);
		//
		// pivot.ecore::pivot::MessageExp pivot|MessageExp
		//
		symbol_50.setName("MessageExp");
		symbol_50.getSuperClasses().add(symbol_61);
		{ // pivot.ecore::pivot::MessageExp::argument pivot|MessageExp|argument
			Property symbol_202 = PivotFactory.eINSTANCE.createProperty();
			symbol_202.setName("argument");
			symbol_202.setType(symbol_61);  // pivot|OclExpression
			symbol_50.getOwnedAttributes().add(symbol_202);
		}
		{ // pivot.ecore::pivot::MessageExp::calledOperation pivot|MessageExp|calledOperation
			Property symbol_203 = PivotFactory.eINSTANCE.createProperty();
			symbol_203.setName("calledOperation");
			symbol_203.setType(symbol_11);  // pivot|CallOperationAction
			symbol_50.getOwnedAttributes().add(symbol_203);
		}
		{ // pivot.ecore::pivot::MessageExp::sentSignal pivot|MessageExp|sentSignal
			Property symbol_204 = PivotFactory.eINSTANCE.createProperty();
			symbol_204.setName("sentSignal");
			symbol_204.setType(symbol_79);  // pivot|SendSignalAction
			symbol_50.getOwnedAttributes().add(symbol_204);
		}
		{ // pivot.ecore::pivot::MessageExp::target pivot|MessageExp|target
			Property symbol_205 = PivotFactory.eINSTANCE.createProperty();
			symbol_205.setName("target");
			symbol_205.setType(symbol_61);  // pivot|OclExpression
			symbol_50.getOwnedAttributes().add(symbol_205);
		}
		symbol_1.getOwnedTypes().add(symbol_50);
		//
		// pivot.ecore::pivot::MessageType pivot|MessageType
		//
		symbol_51.setName("MessageType");
		symbol_51.getSuperClasses().add(symbol_97);
		{ // pivot.ecore::pivot::MessageType::referredOperation pivot|MessageType|referredOperation
			Property symbol_206 = PivotFactory.eINSTANCE.createProperty();
			symbol_206.setName("referredOperation");
			symbol_206.setType(symbol_63);  // pivot|Operation
			symbol_51.getOwnedAttributes().add(symbol_206);
		}
		{ // pivot.ecore::pivot::MessageType::referredSignal pivot|MessageType|referredSignal
			Property symbol_207 = PivotFactory.eINSTANCE.createProperty();
			symbol_207.setName("referredSignal");
			symbol_207.setType(symbol_82);  // pivot|Signal
			symbol_51.getOwnedAttributes().add(symbol_207);
		}
		symbol_1.getOwnedTypes().add(symbol_51);
		//
		// pivot.ecore::pivot::MonikeredElement pivot|MonikeredElement
		//
		symbol_52.setName("MonikeredElement");
		symbol_52.getSuperClasses().add(symbol_29);
		{ // pivot.ecore::pivot::MonikeredElement::moniker pivot|MonikeredElement|moniker
			Property symbol_208 = PivotFactory.eINSTANCE.createProperty();
			symbol_208.setName("moniker");
			symbol_208.setType(symbol_85);  // pivot|String
			symbol_52.getOwnedAttributes().add(symbol_208);
		}
		{	// pivot.ecore::pivot::MonikeredElement::hasMoniker() pivot|MonikeredElement|hasMoniker()
			Operation symbol_209 = PivotFactory.eINSTANCE.createOperation();
			symbol_209.setName("hasMoniker");
			symbol_209.setType(symbol_8);  // pivot|Boolean
			symbol_52.getOwnedOperations().add(symbol_209);
		}
		symbol_1.getOwnedTypes().add(symbol_52);
		//
		// pivot.ecore::pivot::MultiplicityElement pivot|MultiplicityElement
		//
		symbol_53.setName("MultiplicityElement");
		symbol_53.getSuperClasses().add(symbol_52);
		{ // pivot.ecore::pivot::MultiplicityElement::isOrdered pivot|MultiplicityElement|isOrdered
			Property symbol_210 = PivotFactory.eINSTANCE.createProperty();
			symbol_210.setName("isOrdered");
			symbol_210.setType(symbol_8);  // pivot|Boolean
			symbol_53.getOwnedAttributes().add(symbol_210);
		}
		{ // pivot.ecore::pivot::MultiplicityElement::isUnique pivot|MultiplicityElement|isUnique
			Property symbol_211 = PivotFactory.eINSTANCE.createProperty();
			symbol_211.setName("isUnique");
			symbol_211.setType(symbol_8);  // pivot|Boolean
			symbol_53.getOwnedAttributes().add(symbol_211);
		}
		{ // pivot.ecore::pivot::MultiplicityElement::lower pivot|MultiplicityElement|lower
			Property symbol_212 = PivotFactory.eINSTANCE.createProperty();
			symbol_212.setName("lower");
			symbol_212.setType(symbol_39);  // pivot|Integer
			symbol_53.getOwnedAttributes().add(symbol_212);
		}
		{ // pivot.ecore::pivot::MultiplicityElement::upper pivot|MultiplicityElement|upper
			Property symbol_213 = PivotFactory.eINSTANCE.createProperty();
			symbol_213.setName("upper");
			symbol_213.setType(symbol_102);  // pivot|UnlimitedNatural
			symbol_53.getOwnedAttributes().add(symbol_213);
		}
		{	// pivot.ecore::pivot::MultiplicityElement::includesCardinality() pivot|MultiplicityElement|includesCardinality(pivot|Integer)
			Operation symbol_214 = PivotFactory.eINSTANCE.createOperation();
			symbol_214.setName("includesCardinality");
			symbol_214.setType(symbol_8);  // pivot|Boolean
			Parameter symbol_215 = PivotFactory.eINSTANCE.createParameter();
			symbol_215.setName("C");
			symbol_215.setType(symbol_39);  // pivot|Integer
			symbol_214.getOwnedParameters().add(symbol_215);
			symbol_53.getOwnedOperations().add(symbol_214);
		}
		{	// pivot.ecore::pivot::MultiplicityElement::includesMultiplicity() pivot|MultiplicityElement|includesMultiplicity(pivot|MultiplicityElement)
			Operation symbol_216 = PivotFactory.eINSTANCE.createOperation();
			symbol_216.setName("includesMultiplicity");
			symbol_216.setType(symbol_8);  // pivot|Boolean
			Parameter symbol_217 = PivotFactory.eINSTANCE.createParameter();
			symbol_217.setName("M");
			symbol_217.setType(symbol_53);  // pivot|MultiplicityElement
			symbol_216.getOwnedParameters().add(symbol_217);
			symbol_53.getOwnedOperations().add(symbol_216);
		}
		{	// pivot.ecore::pivot::MultiplicityElement::isMultivalued() pivot|MultiplicityElement|isMultivalued()
			Operation symbol_218 = PivotFactory.eINSTANCE.createOperation();
			symbol_218.setName("isMultivalued");
			symbol_218.setType(symbol_8);  // pivot|Boolean
			symbol_53.getOwnedOperations().add(symbol_218);
		}
		{	// pivot.ecore::pivot::MultiplicityElement::lowerBound() pivot|MultiplicityElement|lowerBound()
			Operation symbol_219 = PivotFactory.eINSTANCE.createOperation();
			symbol_219.setName("lowerBound");
			symbol_219.setType(symbol_39);  // pivot|Integer
			symbol_53.getOwnedOperations().add(symbol_219);
		}
		{	// pivot.ecore::pivot::MultiplicityElement::upperBound() pivot|MultiplicityElement|upperBound()
			Operation symbol_220 = PivotFactory.eINSTANCE.createOperation();
			symbol_220.setName("upperBound");
			symbol_220.setType(symbol_102);  // pivot|UnlimitedNatural
			symbol_53.getOwnedOperations().add(symbol_220);
		}
		symbol_1.getOwnedTypes().add(symbol_53);
		//
		// pivot.ecore::pivot::Nameable pivot|Nameable
		//
		symbol_54.setName("Nameable");
		symbol_1.getOwnedTypes().add(symbol_54);
		//
		// pivot.ecore::pivot::NamedElement pivot|NamedElement
		//
		symbol_55.setName("NamedElement");
		symbol_55.getSuperClasses().add(symbol_54);
		symbol_55.getSuperClasses().add(symbol_52);
		{ // pivot.ecore::pivot::NamedElement::isStatic pivot|NamedElement|isStatic
			Property symbol_221 = PivotFactory.eINSTANCE.createProperty();
			symbol_221.setName("isStatic");
			symbol_221.setType(symbol_8);  // pivot|Boolean
			symbol_55.getOwnedAttributes().add(symbol_221);
		}
		{ // pivot.ecore::pivot::NamedElement::name pivot|NamedElement|name
			Property symbol_222 = PivotFactory.eINSTANCE.createProperty();
			symbol_222.setName("name");
			symbol_222.setType(symbol_85);  // pivot|String
			symbol_55.getOwnedAttributes().add(symbol_222);
		}
		{ // pivot.ecore::pivot::NamedElement::ownedAnnotation pivot|NamedElement|ownedAnnotation
			Property symbol_223 = PivotFactory.eINSTANCE.createProperty();
			symbol_223.setName("ownedAnnotation");
			symbol_223.setType(symbol_2);  // pivot|Annotation
			symbol_55.getOwnedAttributes().add(symbol_223);
		}
		{ // pivot.ecore::pivot::NamedElement::ownedRule pivot|NamedElement|ownedRule
			Property symbol_224 = PivotFactory.eINSTANCE.createProperty();
			symbol_224.setName("ownedRule");
			symbol_224.setType(symbol_26);  // pivot|Constraint
			symbol_55.getOwnedAttributes().add(symbol_224);
		}
		symbol_1.getOwnedTypes().add(symbol_55);
		//
		// pivot.ecore::pivot::Namespace pivot|Namespace
		//
		symbol_56.setName("Namespace");
		symbol_56.getSuperClasses().add(symbol_55);
		symbol_1.getOwnedTypes().add(symbol_56);
		//
		// pivot.ecore::pivot::NavigationCallExp pivot|NavigationCallExp
		//
		symbol_57.setName("NavigationCallExp");
		symbol_57.getSuperClasses().add(symbol_35);
		{ // pivot.ecore::pivot::NavigationCallExp::navigationSource pivot|NavigationCallExp|navigationSource
			Property symbol_225 = PivotFactory.eINSTANCE.createProperty();
			symbol_225.setName("navigationSource");
			symbol_225.setType(symbol_75);  // pivot|Property
			symbol_57.getOwnedAttributes().add(symbol_225);
		}
		{ // pivot.ecore::pivot::NavigationCallExp::qualifier pivot|NavigationCallExp|qualifier
			Property symbol_226 = PivotFactory.eINSTANCE.createProperty();
			symbol_226.setName("qualifier");
			symbol_226.setType(symbol_61);  // pivot|OclExpression
			symbol_57.getOwnedAttributes().add(symbol_226);
		}
		symbol_1.getOwnedTypes().add(symbol_57);
		//
		// pivot.ecore::pivot::NullLiteralExp pivot|NullLiteralExp
		//
		symbol_58.setName("NullLiteralExp");
		symbol_58.getSuperClasses().add(symbol_73);
		symbol_1.getOwnedTypes().add(symbol_58);
		//
		// pivot.ecore::pivot::NumericLiteralExp pivot|NumericLiteralExp
		//
		symbol_59.setName("NumericLiteralExp");
		symbol_59.getSuperClasses().add(symbol_73);
		symbol_1.getOwnedTypes().add(symbol_59);
		//
		// pivot.ecore::pivot::Object pivot|Object
		//
		symbol_60.setName("Object");
		symbol_1.getOwnedTypes().add(symbol_60);
		//
		// pivot.ecore::pivot::OclExpression pivot|OclExpression
		//
		symbol_61.setName("OclExpression");
		symbol_61.getSuperClasses().add(symbol_100);
		symbol_1.getOwnedTypes().add(symbol_61);
		//
		// pivot.ecore::pivot::OpaqueExpression pivot|OpaqueExpression
		//
		symbol_62.setName("OpaqueExpression");
		symbol_62.getSuperClasses().add(symbol_105);
		{ // pivot.ecore::pivot::OpaqueExpression::body pivot|OpaqueExpression|body
			Property symbol_227 = PivotFactory.eINSTANCE.createProperty();
			symbol_227.setName("body");
			symbol_227.setType(symbol_85);  // pivot|String
			symbol_62.getOwnedAttributes().add(symbol_227);
		}
		{ // pivot.ecore::pivot::OpaqueExpression::language pivot|OpaqueExpression|language
			Property symbol_228 = PivotFactory.eINSTANCE.createProperty();
			symbol_228.setName("language");
			symbol_228.setType(symbol_85);  // pivot|String
			symbol_62.getOwnedAttributes().add(symbol_228);
		}
		symbol_1.getOwnedTypes().add(symbol_62);
		//
		// pivot.ecore::pivot::Operation pivot|Operation
		//
		symbol_63.setName("Operation");
		symbol_63.getSuperClasses().add(symbol_37);
		symbol_63.getSuperClasses().add(symbol_101);
		symbol_63.getSuperClasses().add(symbol_92);
		symbol_63.getSuperClasses().add(symbol_56);
		symbol_63.getSuperClasses().add(symbol_70);
		symbol_63.getSuperClasses().add(symbol_34);
		{ // pivot.ecore::pivot::Operation::class pivot|Operation|class
			Property symbol_229 = PivotFactory.eINSTANCE.createProperty();
			symbol_229.setName("class");
			symbol_229.setType(symbol_13);  // pivot|Class
			symbol_63.getOwnedAttributes().add(symbol_229);
		}
		{ // pivot.ecore::pivot::Operation::ownedParameter pivot|Operation|ownedParameter
			Property symbol_230 = PivotFactory.eINSTANCE.createProperty();
			symbol_230.setName("ownedParameter");
			symbol_230.setType(symbol_69);  // pivot|Parameter
			symbol_63.getOwnedAttributes().add(symbol_230);
		}
		{ // pivot.ecore::pivot::Operation::precedence pivot|Operation|precedence
			Property symbol_231 = PivotFactory.eINSTANCE.createProperty();
			symbol_231.setName("precedence");
			symbol_231.setType(symbol_72);  // pivot|Precedence
			symbol_63.getOwnedAttributes().add(symbol_231);
		}
		{ // pivot.ecore::pivot::Operation::raisedException pivot|Operation|raisedException
			Property symbol_232 = PivotFactory.eINSTANCE.createProperty();
			symbol_232.setName("raisedException");
			symbol_232.setType(symbol_97);  // pivot|Type
			symbol_63.getOwnedAttributes().add(symbol_232);
		}
		symbol_1.getOwnedTypes().add(symbol_63);
		//
		// pivot.ecore::pivot::OperationCallExp pivot|OperationCallExp
		//
		symbol_64.setName("OperationCallExp");
		symbol_64.getSuperClasses().add(symbol_35);
		{ // pivot.ecore::pivot::OperationCallExp::argument pivot|OperationCallExp|argument
			Property symbol_233 = PivotFactory.eINSTANCE.createProperty();
			symbol_233.setName("argument");
			symbol_233.setType(symbol_61);  // pivot|OclExpression
			symbol_64.getOwnedAttributes().add(symbol_233);
		}
		{ // pivot.ecore::pivot::OperationCallExp::referredOperation pivot|OperationCallExp|referredOperation
			Property symbol_234 = PivotFactory.eINSTANCE.createProperty();
			symbol_234.setName("referredOperation");
			symbol_234.setType(symbol_63);  // pivot|Operation
			symbol_64.getOwnedAttributes().add(symbol_234);
		}
		symbol_1.getOwnedTypes().add(symbol_64);
		//
		// pivot.ecore::pivot::OperationTemplateParameter pivot|OperationTemplateParameter
		//
		symbol_65.setName("OperationTemplateParameter");
		symbol_65.getSuperClasses().add(symbol_88);
		symbol_1.getOwnedTypes().add(symbol_65);
		//
		// pivot.ecore::pivot::OrderedSetType pivot|OrderedSetType
		//
		symbol_66.setName("OrderedSetType");
		symbol_66.getSuperClasses().add(symbol_19);
		symbol_1.getOwnedTypes().add(symbol_66);
		//
		// pivot.ecore::pivot::Package pivot|Package
		//
		symbol_67.setName("Package");
		symbol_67.getSuperClasses().add(symbol_92);
		symbol_67.getSuperClasses().add(symbol_56);
		{ // pivot.ecore::pivot::Package::nestedPackage pivot|Package|nestedPackage
			Property symbol_235 = PivotFactory.eINSTANCE.createProperty();
			symbol_235.setName("nestedPackage");
			symbol_235.setType(symbol_67);  // pivot|Package
			symbol_67.getOwnedAttributes().add(symbol_235);
		}
		{ // pivot.ecore::pivot::Package::nestingPackage pivot|Package|nestingPackage
			Property symbol_236 = PivotFactory.eINSTANCE.createProperty();
			symbol_236.setName("nestingPackage");
			symbol_236.setType(symbol_67);  // pivot|Package
			symbol_67.getOwnedAttributes().add(symbol_236);
		}
		{ // pivot.ecore::pivot::Package::nsPrefix pivot|Package|nsPrefix
			Property symbol_237 = PivotFactory.eINSTANCE.createProperty();
			symbol_237.setName("nsPrefix");
			symbol_237.setType(symbol_85);  // pivot|String
			symbol_67.getOwnedAttributes().add(symbol_237);
		}
		{ // pivot.ecore::pivot::Package::nsURI pivot|Package|nsURI
			Property symbol_238 = PivotFactory.eINSTANCE.createProperty();
			symbol_238.setName("nsURI");
			symbol_238.setType(symbol_85);  // pivot|String
			symbol_67.getOwnedAttributes().add(symbol_238);
		}
		{ // pivot.ecore::pivot::Package::ownedPrecedence pivot|Package|ownedPrecedence
			Property symbol_239 = PivotFactory.eINSTANCE.createProperty();
			symbol_239.setName("ownedPrecedence");
			symbol_239.setType(symbol_72);  // pivot|Precedence
			symbol_67.getOwnedAttributes().add(symbol_239);
		}
		{ // pivot.ecore::pivot::Package::ownedType pivot|Package|ownedType
			Property symbol_240 = PivotFactory.eINSTANCE.createProperty();
			symbol_240.setName("ownedType");
			symbol_240.setType(symbol_97);  // pivot|Type
			symbol_67.getOwnedAttributes().add(symbol_240);
		}
		symbol_1.getOwnedTypes().add(symbol_67);
		//
		// pivot.ecore::pivot::PackageableElement pivot|PackageableElement
		//
		symbol_68.setName("PackageableElement");
		symbol_68.getSuperClasses().add(symbol_70);
		symbol_1.getOwnedTypes().add(symbol_68);
		//
		// pivot.ecore::pivot::Parameter pivot|Parameter
		//
		symbol_69.setName("Parameter");
		symbol_69.getSuperClasses().add(symbol_107);
		symbol_69.getSuperClasses().add(symbol_101);
		{ // pivot.ecore::pivot::Parameter::operation pivot|Parameter|operation
			Property symbol_241 = PivotFactory.eINSTANCE.createProperty();
			symbol_241.setName("operation");
			symbol_241.setType(symbol_63);  // pivot|Operation
			symbol_69.getOwnedAttributes().add(symbol_241);
		}
		symbol_1.getOwnedTypes().add(symbol_69);
		//
		// pivot.ecore::pivot::ParameterableElement pivot|ParameterableElement
		//
		symbol_70.setName("ParameterableElement");
		symbol_70.getSuperClasses().add(symbol_52);
		{ // pivot.ecore::pivot::ParameterableElement::owningTemplateParameter pivot|ParameterableElement|owningTemplateParameter
			Property symbol_242 = PivotFactory.eINSTANCE.createProperty();
			symbol_242.setName("owningTemplateParameter");
			symbol_242.setType(symbol_88);  // pivot|TemplateParameter
			symbol_70.getOwnedAttributes().add(symbol_242);
		}
		{ // pivot.ecore::pivot::ParameterableElement::templateParameter pivot|ParameterableElement|templateParameter
			Property symbol_243 = PivotFactory.eINSTANCE.createProperty();
			symbol_243.setName("templateParameter");
			symbol_243.setType(symbol_88);  // pivot|TemplateParameter
			symbol_70.getOwnedAttributes().add(symbol_243);
		}
		{	// pivot.ecore::pivot::ParameterableElement::isTemplateParameter() pivot|ParameterableElement|isTemplateParameter()
			Operation symbol_244 = PivotFactory.eINSTANCE.createOperation();
			symbol_244.setName("isTemplateParameter");
			symbol_244.setType(symbol_8);  // pivot|Boolean
			symbol_70.getOwnedOperations().add(symbol_244);
		}
		symbol_1.getOwnedTypes().add(symbol_70);
		//
		// pivot.ecore::pivot::Pivotable pivot|Pivotable
		//
		symbol_71.setName("Pivotable");
		symbol_1.getOwnedTypes().add(symbol_71);
		//
		// pivot.ecore::pivot::Precedence pivot|Precedence
		//
		symbol_72.setName("Precedence");
		symbol_72.getSuperClasses().add(symbol_55);
		{ // pivot.ecore::pivot::Precedence::associativity pivot|Precedence|associativity
			Property symbol_245 = PivotFactory.eINSTANCE.createProperty();
			symbol_245.setName("associativity");
			symbol_245.setType(symbol_6);  // pivot|AssociativityKind
			symbol_72.getOwnedAttributes().add(symbol_245);
		}
		{ // pivot.ecore::pivot::Precedence::order pivot|Precedence|order
			Property symbol_246 = PivotFactory.eINSTANCE.createProperty();
			symbol_246.setName("order");
			symbol_246.setType(symbol_39);  // pivot|Integer
			symbol_72.getOwnedAttributes().add(symbol_246);
		}
		symbol_1.getOwnedTypes().add(symbol_72);
		//
		// pivot.ecore::pivot::PrimitiveLiteralExp pivot|PrimitiveLiteralExp
		//
		symbol_73.setName("PrimitiveLiteralExp");
		symbol_73.getSuperClasses().add(symbol_48);
		symbol_1.getOwnedTypes().add(symbol_73);
		//
		// pivot.ecore::pivot::PrimitiveType pivot|PrimitiveType
		//
		symbol_74.setName("PrimitiveType");
		symbol_74.getSuperClasses().add(symbol_27);
		symbol_1.getOwnedTypes().add(symbol_74);
		//
		// pivot.ecore::pivot::Property pivot|Property
		//
		symbol_75.setName("Property");
		symbol_75.getSuperClasses().add(symbol_37);
		symbol_75.getSuperClasses().add(symbol_101);
		symbol_75.getSuperClasses().add(symbol_70);
		symbol_75.getSuperClasses().add(symbol_34);
		{ // pivot.ecore::pivot::Property::association pivot|Property|association
			Property symbol_247 = PivotFactory.eINSTANCE.createProperty();
			symbol_247.setName("association");
			symbol_247.setType(symbol_4);  // pivot|AssociationClass
			symbol_75.getOwnedAttributes().add(symbol_247);
		}
		{ // pivot.ecore::pivot::Property::class pivot|Property|class
			Property symbol_248 = PivotFactory.eINSTANCE.createProperty();
			symbol_248.setName("class");
			symbol_248.setType(symbol_13);  // pivot|Class
			symbol_75.getOwnedAttributes().add(symbol_248);
		}
		{ // pivot.ecore::pivot::Property::default pivot|Property|default
			Property symbol_249 = PivotFactory.eINSTANCE.createProperty();
			symbol_249.setName("default");
			symbol_249.setType(symbol_85);  // pivot|String
			symbol_75.getOwnedAttributes().add(symbol_249);
		}
		{ // pivot.ecore::pivot::Property::isComposite pivot|Property|isComposite
			Property symbol_250 = PivotFactory.eINSTANCE.createProperty();
			symbol_250.setName("isComposite");
			symbol_250.setType(symbol_8);  // pivot|Boolean
			symbol_75.getOwnedAttributes().add(symbol_250);
		}
		{ // pivot.ecore::pivot::Property::isDerived pivot|Property|isDerived
			Property symbol_251 = PivotFactory.eINSTANCE.createProperty();
			symbol_251.setName("isDerived");
			symbol_251.setType(symbol_8);  // pivot|Boolean
			symbol_75.getOwnedAttributes().add(symbol_251);
		}
		{ // pivot.ecore::pivot::Property::isID pivot|Property|isID
			Property symbol_252 = PivotFactory.eINSTANCE.createProperty();
			symbol_252.setName("isID");
			symbol_252.setType(symbol_8);  // pivot|Boolean
			symbol_75.getOwnedAttributes().add(symbol_252);
		}
		{ // pivot.ecore::pivot::Property::isReadOnly pivot|Property|isReadOnly
			Property symbol_253 = PivotFactory.eINSTANCE.createProperty();
			symbol_253.setName("isReadOnly");
			symbol_253.setType(symbol_8);  // pivot|Boolean
			symbol_75.getOwnedAttributes().add(symbol_253);
		}
		{ // pivot.ecore::pivot::Property::isResolveProxies pivot|Property|isResolveProxies
			Property symbol_254 = PivotFactory.eINSTANCE.createProperty();
			symbol_254.setName("isResolveProxies");
			symbol_254.setType(symbol_8);  // pivot|Boolean
			symbol_75.getOwnedAttributes().add(symbol_254);
		}
		{ // pivot.ecore::pivot::Property::isTransient pivot|Property|isTransient
			Property symbol_255 = PivotFactory.eINSTANCE.createProperty();
			symbol_255.setName("isTransient");
			symbol_255.setType(symbol_8);  // pivot|Boolean
			symbol_75.getOwnedAttributes().add(symbol_255);
		}
		{ // pivot.ecore::pivot::Property::isUnsettable pivot|Property|isUnsettable
			Property symbol_256 = PivotFactory.eINSTANCE.createProperty();
			symbol_256.setName("isUnsettable");
			symbol_256.setType(symbol_8);  // pivot|Boolean
			symbol_75.getOwnedAttributes().add(symbol_256);
		}
		{ // pivot.ecore::pivot::Property::isVolatile pivot|Property|isVolatile
			Property symbol_257 = PivotFactory.eINSTANCE.createProperty();
			symbol_257.setName("isVolatile");
			symbol_257.setType(symbol_8);  // pivot|Boolean
			symbol_75.getOwnedAttributes().add(symbol_257);
		}
		{ // pivot.ecore::pivot::Property::keys pivot|Property|keys
			Property symbol_258 = PivotFactory.eINSTANCE.createProperty();
			symbol_258.setName("keys");
			symbol_258.setType(symbol_75);  // pivot|Property
			symbol_75.getOwnedAttributes().add(symbol_258);
		}
		{ // pivot.ecore::pivot::Property::opposite pivot|Property|opposite
			Property symbol_259 = PivotFactory.eINSTANCE.createProperty();
			symbol_259.setName("opposite");
			symbol_259.setType(symbol_75);  // pivot|Property
			symbol_75.getOwnedAttributes().add(symbol_259);
		}
		symbol_1.getOwnedTypes().add(symbol_75);
		//
		// pivot.ecore::pivot::PropertyCallExp pivot|PropertyCallExp
		//
		symbol_76.setName("PropertyCallExp");
		symbol_76.getSuperClasses().add(symbol_57);
		{ // pivot.ecore::pivot::PropertyCallExp::referredProperty pivot|PropertyCallExp|referredProperty
			Property symbol_260 = PivotFactory.eINSTANCE.createProperty();
			symbol_260.setName("referredProperty");
			symbol_260.setType(symbol_75);  // pivot|Property
			symbol_76.getOwnedAttributes().add(symbol_260);
		}
		symbol_1.getOwnedTypes().add(symbol_76);
		//
		// pivot.ecore::pivot::Real pivot|Real
		//
		symbol_77.setName("Real");
		symbol_1.getOwnedTypes().add(symbol_77);
		//
		// pivot.ecore::pivot::RealLiteralExp pivot|RealLiteralExp
		//
		symbol_78.setName("RealLiteralExp");
		symbol_78.getSuperClasses().add(symbol_59);
		{ // pivot.ecore::pivot::RealLiteralExp::realSymbol pivot|RealLiteralExp|realSymbol
			Property symbol_261 = PivotFactory.eINSTANCE.createProperty();
			symbol_261.setName("realSymbol");
			symbol_261.setType(symbol_77);  // pivot|Real
			symbol_78.getOwnedAttributes().add(symbol_261);
		}
		symbol_1.getOwnedTypes().add(symbol_78);
		//
		// pivot.ecore::pivot::SendSignalAction pivot|SendSignalAction
		//
		symbol_79.setName("SendSignalAction");
		symbol_79.getSuperClasses().add(symbol_55);
		{ // pivot.ecore::pivot::SendSignalAction::signal pivot|SendSignalAction|signal
			Property symbol_262 = PivotFactory.eINSTANCE.createProperty();
			symbol_262.setName("signal");
			symbol_262.setType(symbol_82);  // pivot|Signal
			symbol_79.getOwnedAttributes().add(symbol_262);
		}
		symbol_1.getOwnedTypes().add(symbol_79);
		//
		// pivot.ecore::pivot::SequenceType pivot|SequenceType
		//
		symbol_80.setName("SequenceType");
		symbol_80.getSuperClasses().add(symbol_19);
		symbol_1.getOwnedTypes().add(symbol_80);
		//
		// pivot.ecore::pivot::SetType pivot|SetType
		//
		symbol_81.setName("SetType");
		symbol_81.getSuperClasses().add(symbol_19);
		symbol_1.getOwnedTypes().add(symbol_81);
		//
		// pivot.ecore::pivot::Signal pivot|Signal
		//
		symbol_82.setName("Signal");
		symbol_82.getSuperClasses().add(symbol_55);
		symbol_1.getOwnedTypes().add(symbol_82);
		//
		// pivot.ecore::pivot::State pivot|State
		//
		symbol_83.setName("State");
		symbol_83.getSuperClasses().add(symbol_55);
		symbol_1.getOwnedTypes().add(symbol_83);
		//
		// pivot.ecore::pivot::StateExp pivot|StateExp
		//
		symbol_84.setName("StateExp");
		symbol_84.getSuperClasses().add(symbol_61);
		{ // pivot.ecore::pivot::StateExp::referredState pivot|StateExp|referredState
			Property symbol_263 = PivotFactory.eINSTANCE.createProperty();
			symbol_263.setName("referredState");
			symbol_263.setType(symbol_83);  // pivot|State
			symbol_84.getOwnedAttributes().add(symbol_263);
		}
		symbol_1.getOwnedTypes().add(symbol_84);
		//
		// pivot.ecore::pivot::String pivot|String
		//
		symbol_85.setName("String");
		symbol_1.getOwnedTypes().add(symbol_85);
		//
		// pivot.ecore::pivot::StringLiteralExp pivot|StringLiteralExp
		//
		symbol_86.setName("StringLiteralExp");
		symbol_86.getSuperClasses().add(symbol_73);
		{ // pivot.ecore::pivot::StringLiteralExp::stringSymbol pivot|StringLiteralExp|stringSymbol
			Property symbol_264 = PivotFactory.eINSTANCE.createProperty();
			symbol_264.setName("stringSymbol");
			symbol_264.setType(symbol_85);  // pivot|String
			symbol_86.getOwnedAttributes().add(symbol_264);
		}
		symbol_1.getOwnedTypes().add(symbol_86);
		//
		// pivot.ecore::pivot::TemplateBinding pivot|TemplateBinding
		//
		symbol_87.setName("TemplateBinding");
		symbol_87.getSuperClasses().add(symbol_29);
		{ // pivot.ecore::pivot::TemplateBinding::boundElement pivot|TemplateBinding|boundElement
			Property symbol_265 = PivotFactory.eINSTANCE.createProperty();
			symbol_265.setName("boundElement");
			symbol_265.setType(symbol_92);  // pivot|TemplateableElement
			symbol_87.getOwnedAttributes().add(symbol_265);
		}
		{ // pivot.ecore::pivot::TemplateBinding::parameterSubstitution pivot|TemplateBinding|parameterSubstitution
			Property symbol_266 = PivotFactory.eINSTANCE.createProperty();
			symbol_266.setName("parameterSubstitution");
			symbol_266.setType(symbol_89);  // pivot|TemplateParameterSubstitution
			symbol_87.getOwnedAttributes().add(symbol_266);
		}
		{ // pivot.ecore::pivot::TemplateBinding::signature pivot|TemplateBinding|signature
			Property symbol_267 = PivotFactory.eINSTANCE.createProperty();
			symbol_267.setName("signature");
			symbol_267.setType(symbol_91);  // pivot|TemplateSignature
			symbol_87.getOwnedAttributes().add(symbol_267);
		}
		symbol_1.getOwnedTypes().add(symbol_87);
		//
		// pivot.ecore::pivot::TemplateParameter pivot|TemplateParameter
		//
		symbol_88.setName("TemplateParameter");
		symbol_88.getSuperClasses().add(symbol_29);
		{ // pivot.ecore::pivot::TemplateParameter::default pivot|TemplateParameter|default
			Property symbol_268 = PivotFactory.eINSTANCE.createProperty();
			symbol_268.setName("default");
			symbol_268.setType(symbol_70);  // pivot|ParameterableElement
			symbol_88.getOwnedAttributes().add(symbol_268);
		}
		{ // pivot.ecore::pivot::TemplateParameter::ownedDefault pivot|TemplateParameter|ownedDefault
			Property symbol_269 = PivotFactory.eINSTANCE.createProperty();
			symbol_269.setName("ownedDefault");
			symbol_269.setType(symbol_70);  // pivot|ParameterableElement
			symbol_88.getOwnedAttributes().add(symbol_269);
		}
		{ // pivot.ecore::pivot::TemplateParameter::ownedParameteredElement pivot|TemplateParameter|ownedParameteredElement
			Property symbol_270 = PivotFactory.eINSTANCE.createProperty();
			symbol_270.setName("ownedParameteredElement");
			symbol_270.setType(symbol_70);  // pivot|ParameterableElement
			symbol_88.getOwnedAttributes().add(symbol_270);
		}
		{ // pivot.ecore::pivot::TemplateParameter::parameteredElement pivot|TemplateParameter|parameteredElement
			Property symbol_271 = PivotFactory.eINSTANCE.createProperty();
			symbol_271.setName("parameteredElement");
			symbol_271.setType(symbol_70);  // pivot|ParameterableElement
			symbol_88.getOwnedAttributes().add(symbol_271);
		}
		{ // pivot.ecore::pivot::TemplateParameter::signature pivot|TemplateParameter|signature
			Property symbol_272 = PivotFactory.eINSTANCE.createProperty();
			symbol_272.setName("signature");
			symbol_272.setType(symbol_91);  // pivot|TemplateSignature
			symbol_88.getOwnedAttributes().add(symbol_272);
		}
		symbol_1.getOwnedTypes().add(symbol_88);
		//
		// pivot.ecore::pivot::TemplateParameterSubstitution pivot|TemplateParameterSubstitution
		//
		symbol_89.setName("TemplateParameterSubstitution");
		symbol_89.getSuperClasses().add(symbol_29);
		{ // pivot.ecore::pivot::TemplateParameterSubstitution::actual pivot|TemplateParameterSubstitution|actual
			Property symbol_273 = PivotFactory.eINSTANCE.createProperty();
			symbol_273.setName("actual");
			symbol_273.setType(symbol_70);  // pivot|ParameterableElement
			symbol_89.getOwnedAttributes().add(symbol_273);
		}
		{ // pivot.ecore::pivot::TemplateParameterSubstitution::formal pivot|TemplateParameterSubstitution|formal
			Property symbol_274 = PivotFactory.eINSTANCE.createProperty();
			symbol_274.setName("formal");
			symbol_274.setType(symbol_88);  // pivot|TemplateParameter
			symbol_89.getOwnedAttributes().add(symbol_274);
		}
		{ // pivot.ecore::pivot::TemplateParameterSubstitution::ownedActual pivot|TemplateParameterSubstitution|ownedActual
			Property symbol_275 = PivotFactory.eINSTANCE.createProperty();
			symbol_275.setName("ownedActual");
			symbol_275.setType(symbol_70);  // pivot|ParameterableElement
			symbol_89.getOwnedAttributes().add(symbol_275);
		}
		{ // pivot.ecore::pivot::TemplateParameterSubstitution::templateBinding pivot|TemplateParameterSubstitution|templateBinding
			Property symbol_276 = PivotFactory.eINSTANCE.createProperty();
			symbol_276.setName("templateBinding");
			symbol_276.setType(symbol_87);  // pivot|TemplateBinding
			symbol_89.getOwnedAttributes().add(symbol_276);
		}
		symbol_1.getOwnedTypes().add(symbol_89);
		//
		// pivot.ecore::pivot::TemplateParameterType pivot|TemplateParameterType
		//
		symbol_90.setName("TemplateParameterType");
		symbol_90.getSuperClasses().add(symbol_97);
		{ // pivot.ecore::pivot::TemplateParameterType::specification pivot|TemplateParameterType|specification
			Property symbol_277 = PivotFactory.eINSTANCE.createProperty();
			symbol_277.setName("specification");
			symbol_277.setType(symbol_85);  // pivot|String
			symbol_90.getOwnedAttributes().add(symbol_277);
		}
		symbol_1.getOwnedTypes().add(symbol_90);
		//
		// pivot.ecore::pivot::TemplateSignature pivot|TemplateSignature
		//
		symbol_91.setName("TemplateSignature");
		symbol_91.getSuperClasses().add(symbol_52);
		{ // pivot.ecore::pivot::TemplateSignature::ownedParameter pivot|TemplateSignature|ownedParameter
			Property symbol_278 = PivotFactory.eINSTANCE.createProperty();
			symbol_278.setName("ownedParameter");
			symbol_278.setType(symbol_88);  // pivot|TemplateParameter
			symbol_91.getOwnedAttributes().add(symbol_278);
		}
		{ // pivot.ecore::pivot::TemplateSignature::parameter pivot|TemplateSignature|parameter
			Property symbol_279 = PivotFactory.eINSTANCE.createProperty();
			symbol_279.setName("parameter");
			symbol_279.setType(symbol_88);  // pivot|TemplateParameter
			symbol_91.getOwnedAttributes().add(symbol_279);
		}
		{ // pivot.ecore::pivot::TemplateSignature::template pivot|TemplateSignature|template
			Property symbol_280 = PivotFactory.eINSTANCE.createProperty();
			symbol_280.setName("template");
			symbol_280.setType(symbol_92);  // pivot|TemplateableElement
			symbol_91.getOwnedAttributes().add(symbol_280);
		}
		symbol_1.getOwnedTypes().add(symbol_91);
		//
		// pivot.ecore::pivot::TemplateableElement pivot|TemplateableElement
		//
		symbol_92.setName("TemplateableElement");
		symbol_92.getSuperClasses().add(symbol_52);
		{ // pivot.ecore::pivot::TemplateableElement::ownedTemplateSignature pivot|TemplateableElement|ownedTemplateSignature
			Property symbol_281 = PivotFactory.eINSTANCE.createProperty();
			symbol_281.setName("ownedTemplateSignature");
			symbol_281.setType(symbol_91);  // pivot|TemplateSignature
			symbol_92.getOwnedAttributes().add(symbol_281);
		}
		{ // pivot.ecore::pivot::TemplateableElement::templateBinding pivot|TemplateableElement|templateBinding
			Property symbol_282 = PivotFactory.eINSTANCE.createProperty();
			symbol_282.setName("templateBinding");
			symbol_282.setType(symbol_87);  // pivot|TemplateBinding
			symbol_92.getOwnedAttributes().add(symbol_282);
		}
		{	// pivot.ecore::pivot::TemplateableElement::isTemplate() pivot|TemplateableElement|isTemplate()
			Operation symbol_283 = PivotFactory.eINSTANCE.createOperation();
			symbol_283.setName("isTemplate");
			symbol_283.setType(symbol_8);  // pivot|Boolean
			symbol_92.getOwnedOperations().add(symbol_283);
		}
		{	// pivot.ecore::pivot::TemplateableElement::parameterableElements() pivot|TemplateableElement|parameterableElements()
			Operation symbol_284 = PivotFactory.eINSTANCE.createOperation();
			symbol_284.setName("parameterableElements");
			symbol_284.setType(symbol_70);  // pivot|ParameterableElement
			symbol_92.getOwnedOperations().add(symbol_284);
		}
		symbol_1.getOwnedTypes().add(symbol_92);
		//
		// pivot.ecore::pivot::Throwable pivot|Throwable
		//
		symbol_93.setName("Throwable");
		symbol_1.getOwnedTypes().add(symbol_93);
		//
		// pivot.ecore::pivot::TupleLiteralExp pivot|TupleLiteralExp
		//
		symbol_94.setName("TupleLiteralExp");
		symbol_94.getSuperClasses().add(symbol_48);
		{ // pivot.ecore::pivot::TupleLiteralExp::part pivot|TupleLiteralExp|part
			Property symbol_285 = PivotFactory.eINSTANCE.createProperty();
			symbol_285.setName("part");
			symbol_285.setType(symbol_95);  // pivot|TupleLiteralPart
			symbol_94.getOwnedAttributes().add(symbol_285);
		}
		symbol_1.getOwnedTypes().add(symbol_94);
		//
		// pivot.ecore::pivot::TupleLiteralPart pivot|TupleLiteralPart
		//
		symbol_95.setName("TupleLiteralPart");
		symbol_95.getSuperClasses().add(symbol_100);
		{ // pivot.ecore::pivot::TupleLiteralPart::attribute pivot|TupleLiteralPart|attribute
			Property symbol_286 = PivotFactory.eINSTANCE.createProperty();
			symbol_286.setName("attribute");
			symbol_286.setType(symbol_75);  // pivot|Property
			symbol_95.getOwnedAttributes().add(symbol_286);
		}
		symbol_1.getOwnedTypes().add(symbol_95);
		//
		// pivot.ecore::pivot::TupleType pivot|TupleType
		//
		symbol_96.setName("TupleType");
		symbol_96.getSuperClasses().add(symbol_27);
		symbol_1.getOwnedTypes().add(symbol_96);
		//
		// pivot.ecore::pivot::Type pivot|Type
		//
		symbol_97.setName("Type");
		symbol_97.getSuperClasses().add(symbol_55);
		symbol_97.getSuperClasses().add(symbol_92);
		symbol_97.getSuperClasses().add(symbol_70);
		{ // pivot.ecore::pivot::Type::instanceClassName pivot|Type|instanceClassName
			Property symbol_287 = PivotFactory.eINSTANCE.createProperty();
			symbol_287.setName("instanceClassName");
			symbol_287.setType(symbol_85);  // pivot|String
			symbol_97.getOwnedAttributes().add(symbol_287);
		}
		{ // pivot.ecore::pivot::Type::package pivot|Type|package
			Property symbol_288 = PivotFactory.eINSTANCE.createProperty();
			symbol_288.setName("package");
			symbol_288.setType(symbol_67);  // pivot|Package
			symbol_97.getOwnedAttributes().add(symbol_288);
		}
		symbol_1.getOwnedTypes().add(symbol_97);
		//
		// pivot.ecore::pivot::TypeExp pivot|TypeExp
		//
		symbol_98.setName("TypeExp");
		symbol_98.getSuperClasses().add(symbol_61);
		{ // pivot.ecore::pivot::TypeExp::referredType pivot|TypeExp|referredType
			Property symbol_289 = PivotFactory.eINSTANCE.createProperty();
			symbol_289.setName("referredType");
			symbol_289.setType(symbol_97);  // pivot|Type
			symbol_98.getOwnedAttributes().add(symbol_289);
		}
		symbol_1.getOwnedTypes().add(symbol_98);
		//
		// pivot.ecore::pivot::TypeTemplateParameter pivot|TypeTemplateParameter
		//
		symbol_99.setName("TypeTemplateParameter");
		symbol_99.getSuperClasses().add(symbol_88);
		{ // pivot.ecore::pivot::TypeTemplateParameter::allowSubstitutable pivot|TypeTemplateParameter|allowSubstitutable
			Property symbol_290 = PivotFactory.eINSTANCE.createProperty();
			symbol_290.setName("allowSubstitutable");
			symbol_290.setType(symbol_8);  // pivot|Boolean
			symbol_99.getOwnedAttributes().add(symbol_290);
		}
		{ // pivot.ecore::pivot::TypeTemplateParameter::constrainingType pivot|TypeTemplateParameter|constrainingType
			Property symbol_291 = PivotFactory.eINSTANCE.createProperty();
			symbol_291.setName("constrainingType");
			symbol_291.setType(symbol_97);  // pivot|Type
			symbol_99.getOwnedAttributes().add(symbol_291);
		}
		symbol_1.getOwnedTypes().add(symbol_99);
		//
		// pivot.ecore::pivot::TypedElement pivot|TypedElement
		//
		symbol_100.setName("TypedElement");
		symbol_100.getSuperClasses().add(symbol_55);
		{ // pivot.ecore::pivot::TypedElement::type pivot|TypedElement|type
			Property symbol_292 = PivotFactory.eINSTANCE.createProperty();
			symbol_292.setName("type");
			symbol_292.setType(symbol_97);  // pivot|Type
			symbol_100.getOwnedAttributes().add(symbol_292);
		}
		symbol_1.getOwnedTypes().add(symbol_100);
		//
		// pivot.ecore::pivot::TypedMultiplicityElement pivot|TypedMultiplicityElement
		//
		symbol_101.setName("TypedMultiplicityElement");
		symbol_101.getSuperClasses().add(symbol_100);
		symbol_101.getSuperClasses().add(symbol_53);
		symbol_1.getOwnedTypes().add(symbol_101);
		//
		// pivot.ecore::pivot::UnlimitedNatural pivot|UnlimitedNatural
		//
		symbol_102.setName("UnlimitedNatural");
		symbol_1.getOwnedTypes().add(symbol_102);
		//
		// pivot.ecore::pivot::UnlimitedNaturalLiteralExp pivot|UnlimitedNaturalLiteralExp
		//
		symbol_103.setName("UnlimitedNaturalLiteralExp");
		symbol_103.getSuperClasses().add(symbol_59);
		{ // pivot.ecore::pivot::UnlimitedNaturalLiteralExp::unlimitedNaturalSymbol pivot|UnlimitedNaturalLiteralExp|unlimitedNaturalSymbol
			Property symbol_293 = PivotFactory.eINSTANCE.createProperty();
			symbol_293.setName("unlimitedNaturalSymbol");
			symbol_293.setType(symbol_102);  // pivot|UnlimitedNatural
			symbol_103.getOwnedAttributes().add(symbol_293);
		}
		symbol_1.getOwnedTypes().add(symbol_103);
		//
		// pivot.ecore::pivot::UnspecifiedValueExp pivot|UnspecifiedValueExp
		//
		symbol_104.setName("UnspecifiedValueExp");
		symbol_104.getSuperClasses().add(symbol_61);
		symbol_1.getOwnedTypes().add(symbol_104);
		//
		// pivot.ecore::pivot::ValueSpecification pivot|ValueSpecification
		//
		symbol_105.setName("ValueSpecification");
		symbol_105.getSuperClasses().add(symbol_100);
		symbol_105.getSuperClasses().add(symbol_70);
		{	// pivot.ecore::pivot::ValueSpecification::booleanValue() pivot|ValueSpecification|booleanValue()
			Operation symbol_294 = PivotFactory.eINSTANCE.createOperation();
			symbol_294.setName("booleanValue");
			symbol_294.setType(symbol_8);  // pivot|Boolean
			symbol_105.getOwnedOperations().add(symbol_294);
		}
		{	// pivot.ecore::pivot::ValueSpecification::integerValue() pivot|ValueSpecification|integerValue()
			Operation symbol_295 = PivotFactory.eINSTANCE.createOperation();
			symbol_295.setName("integerValue");
			symbol_295.setType(symbol_39);  // pivot|Integer
			symbol_105.getOwnedOperations().add(symbol_295);
		}
		{	// pivot.ecore::pivot::ValueSpecification::isComputable() pivot|ValueSpecification|isComputable()
			Operation symbol_296 = PivotFactory.eINSTANCE.createOperation();
			symbol_296.setName("isComputable");
			symbol_296.setType(symbol_8);  // pivot|Boolean
			symbol_105.getOwnedOperations().add(symbol_296);
		}
		{	// pivot.ecore::pivot::ValueSpecification::isNull() pivot|ValueSpecification|isNull()
			Operation symbol_297 = PivotFactory.eINSTANCE.createOperation();
			symbol_297.setName("isNull");
			symbol_297.setType(symbol_8);  // pivot|Boolean
			symbol_105.getOwnedOperations().add(symbol_297);
		}
		{	// pivot.ecore::pivot::ValueSpecification::stringValue() pivot|ValueSpecification|stringValue()
			Operation symbol_298 = PivotFactory.eINSTANCE.createOperation();
			symbol_298.setName("stringValue");
			symbol_298.setType(symbol_85);  // pivot|String
			symbol_105.getOwnedOperations().add(symbol_298);
		}
		{	// pivot.ecore::pivot::ValueSpecification::unlimitedValue() pivot|ValueSpecification|unlimitedValue()
			Operation symbol_299 = PivotFactory.eINSTANCE.createOperation();
			symbol_299.setName("unlimitedValue");
			symbol_299.setType(symbol_102);  // pivot|UnlimitedNatural
			symbol_105.getOwnedOperations().add(symbol_299);
		}
		symbol_1.getOwnedTypes().add(symbol_105);
		//
		// pivot.ecore::pivot::Variable pivot|Variable
		//
		symbol_106.setName("Variable");
		symbol_106.getSuperClasses().add(symbol_107);
		{ // pivot.ecore::pivot::Variable::implicit pivot|Variable|implicit
			Property symbol_300 = PivotFactory.eINSTANCE.createProperty();
			symbol_300.setName("implicit");
			symbol_300.setType(symbol_8);  // pivot|Boolean
			symbol_106.getOwnedAttributes().add(symbol_300);
		}
		{ // pivot.ecore::pivot::Variable::initExpression pivot|Variable|initExpression
			Property symbol_301 = PivotFactory.eINSTANCE.createProperty();
			symbol_301.setName("initExpression");
			symbol_301.setType(symbol_61);  // pivot|OclExpression
			symbol_106.getOwnedAttributes().add(symbol_301);
		}
		{ // pivot.ecore::pivot::Variable::representedParameter pivot|Variable|representedParameter
			Property symbol_302 = PivotFactory.eINSTANCE.createProperty();
			symbol_302.setName("representedParameter");
			symbol_302.setType(symbol_69);  // pivot|Parameter
			symbol_106.getOwnedAttributes().add(symbol_302);
		}
		symbol_1.getOwnedTypes().add(symbol_106);
		//
		// pivot.ecore::pivot::VariableDeclaration pivot|VariableDeclaration
		//
		symbol_107.setName("VariableDeclaration");
		symbol_107.getSuperClasses().add(symbol_100);
		symbol_1.getOwnedTypes().add(symbol_107);
		//
		// pivot.ecore::pivot::VariableExp pivot|VariableExp
		//
		symbol_108.setName("VariableExp");
		symbol_108.getSuperClasses().add(symbol_61);
		{ // pivot.ecore::pivot::VariableExp::referredVariable pivot|VariableExp|referredVariable
			Property symbol_303 = PivotFactory.eINSTANCE.createProperty();
			symbol_303.setName("referredVariable");
			symbol_303.setType(symbol_107);  // pivot|VariableDeclaration
			symbol_108.getOwnedAttributes().add(symbol_303);
		}
		symbol_1.getOwnedTypes().add(symbol_108);
		//
		// pivot.ecore::pivot::Visitable pivot|Visitable
		//
		symbol_109.setName("Visitable");
		symbol_1.getOwnedTypes().add(symbol_109);
		//
		// pivot.ecore::pivot::Visitor pivot|Visitor{R,C}
		//
		symbol_110.setName("Visitor");
		symbol_113.setName("R");
		symbol_112.setOwnedParameteredElement(symbol_113);
		symbol_111.getOwnedParameters().add(symbol_112);
		symbol_115.setName("C");
		symbol_114.setOwnedParameteredElement(symbol_115);
		symbol_111.getOwnedParameters().add(symbol_114);
		
		symbol_110.setOwnedTemplateSignature(symbol_111);
		symbol_1.getOwnedTypes().add(symbol_110);
		//
		// pivot.ecore::pivot::VoidType pivot|VoidType
		//
		symbol_116.setName("VoidType");
		symbol_116.getSuperClasses().add(symbol_13);
		symbol_1.getOwnedTypes().add(symbol_116);

		Class elementClass = (Class) PivotUtil.getNamedElement(symbol_1.getOwnedTypes(), "Element");
		elementClass.getSuperClasses().clear();
		elementClass.getSuperClasses().add(classifierType);
		
		return symbol_1;
	}
}