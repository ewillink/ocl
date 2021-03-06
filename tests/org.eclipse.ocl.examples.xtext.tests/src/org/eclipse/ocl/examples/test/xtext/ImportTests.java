/**
 * <copyright>
 *
 * Copyright (c) 2010,2011 E.D.Willink and others.
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
 * $Id: ImportTests.java,v 1.4 2011/05/22 16:41:51 ewillink Exp $
 */
package org.eclipse.ocl.examples.test.xtext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.ocl.examples.library.AbstractOperation;
import org.eclipse.ocl.examples.pivot.OperationCallExp;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.examples.pivot.library.StandardLibraryContribution;
import org.eclipse.ocl.examples.pivot.messages.OCLMessages;
import org.eclipse.ocl.examples.pivot.utilities.TypeManager;
import org.eclipse.ocl.examples.pivot.utilities.TypeManagerResourceSetAdapter;
import org.eclipse.ocl.examples.pivot.values.Bag;
import org.eclipse.ocl.examples.pivot.values.Value;
import org.eclipse.ocl.examples.pivot.values.impl.BagImpl;
import org.eclipse.ocl.examples.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.examples.xtext.base.utilities.CS2PivotResourceAdapter;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.osgi.util.NLS;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
public class ImportTests extends XtextTestCase
{	
	public static class SpacedOut extends AbstractOperation
	{
		public static final SpacedOut INSTANCE = new SpacedOut();

		public Value evaluate(EvaluationVisitor evaluationVisitor, Value sourceVal, OperationCallExp operationCall) {
			String string = sourceVal == null?  Value.INVALID_NAME : sourceVal.oclToString();
			return evaluationVisitor.getValueFactory().stringValueOf(string);
		}
	}
	
	protected TypeManager typeManager = null;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		configurePlatformResources();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("pivot", new XMIResourceFactoryImpl()); //$NON-NLS-1$
	}

	@Override
	protected void tearDown() throws Exception {
		unloadCS(resourceSet);
		TypeManagerResourceSetAdapter adapter = TypeManagerResourceSetAdapter.findAdapter(resourceSet);
		if (adapter != null) {
			TypeManager typeManager = adapter.getTypeManager();
			if (typeManager != null) {
				typeManager.dispose();
			}
		}
		if (typeManager != null) {
			typeManager.dispose();
			typeManager = null;
		}
		StandardLibraryContribution.REGISTRY.put(TypeManager.DEFAULT_OCL_STDLIB_URI, null);
		super.tearDown();
	}

	protected void createTestImport_OCLinEcore_Bug353793_Files()
			throws IOException {
		TypeManager typeManager = new TypeManager();
		String testFileA =
				"package A1 : A2 = 'A3'{\n" +
				"    class A;\n" +
				"}\n";
		createOCLinEcoreFile("Bug353793A.oclinecore", testFileA);
		String testFileB =
				"package B1 : B2 = 'B3'{\n" +
				"    class B;\n" +
				"}\n";
		createOCLinEcoreFile("Bug353793B.oclinecore", testFileB);
		String testFileE =
				"package E1 : E2 = 'E3'{\n" +
				"    class E;\n" +
				"}\n";
		createEcoreFile(typeManager, "Bug353793E.ecore", testFileE);
		String testFileF =
				"package F1 : F2 = 'F3'{\n" +
				"    class F;\n" +
				"}\n";
		createEcoreFile(typeManager, "Bug353793F.ecore", testFileF);
	}	

	protected void doBadLoadFromString(String fileName, String testFile, Bag<String> expectedErrorMessages) throws Exception {
		if (typeManager == null) {
			typeManager = new TypeManager();
		}
		try {
			TypeManagerResourceSetAdapter.getAdapter(resourceSet, typeManager);
			URI libraryURI = getProjectFileURI(fileName);
			BaseCSResource xtextResource = (BaseCSResource) resourceSet.createResource(libraryURI);
			InputStream inputStream = new ByteArrayInputStream(testFile.getBytes());
			xtextResource.load(inputStream, null);
			Bag<String> actualErrorMessages = new BagImpl<String>();
			for (Resource.Diagnostic actualError : xtextResource.getErrors()) {
				actualErrorMessages.add(actualError.getMessage());
			}
			Set<String> allErrorMessages = new HashSet<String>(expectedErrorMessages);
			allErrorMessages.addAll(actualErrorMessages);
			StringBuffer s = null;
			for (String errorMessage : allErrorMessages) {
				int actualCount = actualErrorMessages.count(errorMessage);
				int expectedCount = expectedErrorMessages.count(errorMessage);
				if (actualCount != expectedCount) {
					if (s == null) {
						s = new StringBuffer();
						s.append("Inconsistent load errors (expected/actual) message");
					}
					s.append("\n  (" + expectedCount + "/" + actualCount + ") " + errorMessage);
				}
			}
			if (s != null) {
				fail(s.toString());
			}
		} finally {
			unloadCS(resourceSet);
//			adapter.dispose();
			unloadPivot(typeManager);
		}
	}

	protected void doLoadFromString(String fileName, String testFile) throws Exception {
		URI libraryURI = getProjectFileURI(fileName);
		if (typeManager == null) {
			typeManager = new TypeManager();
		}
		TypeManagerResourceSetAdapter.getAdapter(resourceSet, typeManager);
		BaseCSResource xtextResource = (BaseCSResource) resourceSet.createResource(libraryURI);
		InputStream inputStream = new ByteArrayInputStream(testFile.getBytes());
		xtextResource.load(inputStream, null);
		assertNoResourceErrors("Load failed", xtextResource);
		CS2PivotResourceAdapter adapter = CS2PivotResourceAdapter.getAdapter(xtextResource, typeManager);
		Resource fileResource = adapter.getPivotResource(xtextResource);
		assertNoResourceErrors("File Model", fileResource);
		assertNoUnresolvedProxies("File Model", fileResource);
		assertNoValidationErrors("File Model", fileResource);
		unloadCS(resourceSet);
		adapter.dispose();
		unloadPivot(typeManager);
	}

	protected String getNoSuchFileMessage() {
		String os = System.getProperty("os.name");
		if ((os != null) && os.startsWith("Windows")) {
			return "{0} (The system cannot find the file specified)";
		}
		else {
			return "{0} (No such file or directory)";
		}
	}
	
	public void testImport_CompleteOCL_CompleteOCL() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String moreCompleteOCL =
			"package ocl\n" +
			"context _'Integer'\n" +
			"def: isPositive() : Boolean = true\n" +
			"endpackage\n";
		createOCLinEcoreFile("more.ocl", moreCompleteOCL);
		String testFile =
			"include 'more.ocl'\n" +
			"package ocl\n" +
			"context _'Integer'\n" +
			"def: signum : Integer = 0\n" +
			"context _'UnlimitedNatural'\n" +
			"inv CheckIt: isPositive() = signum > 0\n" +
			"inv unCheckIt: isNegative() = signum < 0\n" +
			"endpackage\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(NLS.bind(OCLMessages.UnresolvedOperation_ERROR_, "isNegative", "UnlimitedNatural"));
		doBadLoadFromString("string.ocl", testFile, bag);
	}
	
	public void testImport_CompleteOCL_Ecore() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String testFile =
			"import 'Names.ecore'\n" +
			"package names\n" +
			"context Named\n" +
			"inv Bogus: r.toString() = s.toString()\n" +
			"endpackage\n";
		doLoadFromString("string.ocl", testFile);
	}
	
	public void testImport_CompleteOCL_OCLinEcore() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String testFile =
			"import 'Names.oclinecore'\n" +
			"package EMOF\n" +
			"context Class\n" +
			"inv Bogus: isAbstract\n" +
			"endpackage\n";
		doLoadFromString("string.ocl", testFile);
	}
	
	public void testImport_CompleteOCL_OCLstdlib() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String testFile =
			"library 'minimal.oclstdlib'\n" +
			"import 'Names.ecore'\n" +
			"package names\n" +
			"context Named\n" +
			"inv Bogus: r.toString() = s.toString()\n" +
			"endpackage\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(NLS.bind(OCLMessages.UnresolvedOperation_ERROR_, "toString", "OclInvalid"));
		bag.add(NLS.bind(OCLMessages.UnresolvedOperation_ERROR_, "toString", "Real"));
		// There are no precedences so =(s) ratherb than =(s.toString())
		bag.add(NLS.bind(OCLMessages.UnresolvedOperationCall_ERROR_, new Object[]{"=", "OclInvalid", "String"}));
		doBadLoadFromString("string.ocl", testFile, bag);
	}
	
	public void testImport_CompleteOCL_custom_OCLstdlib() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String customLibrary =
			"library lib {\n" +
			"type Real : PrimitiveType {\n" +
			"operation spacedOut() : String => 'org.eclipse.ocl.examples.test.xtext.ImportTests$SpacedOut';\n" +
			"}\n" +
			"}\n";
		createOCLinEcoreFile("custom.oclstdlib", customLibrary);
		String testFile =
			"library 'minimal.oclstdlib'\n" +
			"library 'custom.oclstdlib'\n" +
			"import 'Names.ecore'\n" +
			"package names\n" +
			"context Named\n" +
			"inv Custom_Real_SpacedOut_Exists: r.spacedOut()\n" +
			"inv Standard_Real_ToString_DoesNotExist: r.toString()\n" +
			"endpackage\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(NLS.bind(OCLMessages.UnresolvedOperation_ERROR_, "toString", "Real"));
		doBadLoadFromString("string.ocl", testFile, bag);
	}
	
	public void testImport_CompleteOCL_UML() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String testFile =
			"import 'Names.uml'\n" +
			"package unames\n" +
			"context UNamed\n" +
			"inv Bogus: r.toString() = s.toString()\n" +
			"endpackage\n";
		doLoadFromString("string.ocl", testFile);
	}
	
	public void testImport_CompleteOCL_NoSuchFile() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String testFile =
			"import 'NoSuchFile1'\n" + 
			"import 'NoSuchFile2.ocl'\n" +
			"import 'NoSuchFile1'\n";
		Bag<String> bag = new BagImpl<String>();
		String template1 = "Cannot create a resource for ''{0}''; a registered resource factory is needed";
		String template2 = getNoSuchFileMessage();
		bag.add(NLS.bind(OCLMessages.UnresolvedImport_ERROR_, "NoSuchFile1", NLS.bind(template1, getProjectFileURI("NoSuchFile1"))));
		bag.add(NLS.bind(OCLMessages.UnresolvedImport_ERROR_, "NoSuchFile1", NLS.bind(template1, getProjectFileURI("NoSuchFile1"))));
		bag.add(NLS.bind(OCLMessages.UnresolvedImport_ERROR_, "NoSuchFile2.ocl", NLS.bind(template2, getProjectFileURI("NoSuchFile2.ocl").toFileString())));
		doBadLoadFromString("string.ocl", testFile, bag);
	}

	public void testImport_OCLinEcore_Bug353793_Good() throws Exception {
		createTestImport_OCLinEcore_Bug353793_Files();
		String testFileGood =
				"import 'http://www.eclipse.org/emf/2002/Ecore';\n" +
				"import A0 : 'Bug353793A.oclinecore';\n" +
				"import 'Bug353793B.oclinecore';\n" +
				"import 'Bug353793E.ecore';\n" +
				"import F0 : 'Bug353793F.ecore';\n" +
				"import G0 : 'Bug353793F.ecore#/';\n" +
				"package C1 : C2 = 'C3'\n" +
				"{\n" +
				"    class AD01 extends A0::A1::A;\n" +
				"    class AD011 extends A0::A1::A1::A;\n" +
				"    class BD1 extends B1::B;\n" +
				"    class BD11 extends B1::B1::B;\n" +
				"    class ED1 extends E1::E;\n" +
				"    class FD01 extends F0::F1::F;\n" +
				"    class GD01 extends G0::F1::F;\n" +
				"    class GD0 extends G0::F;\n" +
				"}\n";
		doLoadFromString("Bug353793good.oclinecore", testFileGood);
	}

	public void testImport_OCLinEcore_Bug353793_Bad() throws Exception {
		createTestImport_OCLinEcore_Bug353793_Files();
		String testFileBad =
				"import 'http://www.eclipse.org/emf/2002/Ecore';\n" +
				"import A0 : 'Bug353793A.oclinecore';\n" +
				"import 'Bug353793B.oclinecore';\n" +
				"import 'Bug353793E.ecore';\n" +
				"import F0 : 'Bug353793F.ecore';\n" +
				"import G0 : 'Bug353793F.ecore#/';\n" +
				"package C1 : C2 = 'C3'\n" +
				"{\n" +
				"    class AD0 extends A0::A;\n" +
				"    class AD1 extends A1::A;\n" +
				"    class AD2 extends A2::A;\n" +
				"    class AD3 extends A3::A;\n" +
				"    class BD0 extends B0::B;\n" +
				"    class BD01 extends B0::B1::B;\n" +
				"    class BD2 extends B2::B;\n" +
				"    class BD3 extends B3::B;\n" +
				"    class GDC extends G0::C1::GD01;\n" +
				"}\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "A"));
		bag.add(NLS.bind(OCLMessages.Unresolved_ERROR_, "Namespace", "A1"));
		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "A"));
		bag.add(NLS.bind(OCLMessages.Unresolved_ERROR_, "Namespace", "A2"));
		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "A"));
		bag.add(NLS.bind(OCLMessages.Unresolved_ERROR_, "Namespace", "A3"));
		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "A"));
		bag.add(NLS.bind(OCLMessages.Unresolved_ERROR_, "Namespace", "B0"));
		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "B"));
		bag.add(NLS.bind(OCLMessages.Unresolved_ERROR_, "Namespace", "B0"));
//BUG353966		bag.add(NLS.bind(OCLMessages.Unresolved_ERROR_, "Namespace", "B1"));
//BUG353966		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "B"));
		bag.add(NLS.bind(OCLMessages.Unresolved_ERROR_, "Namespace", "B2"));
		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "B"));
		bag.add(NLS.bind(OCLMessages.Unresolved_ERROR_, "Namespace", "B3"));
		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "B"));
		bag.add(NLS.bind(OCLMessages.Unresolved_ERROR_, "Namespace", "C1"));
		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "GD01"));
		doBadLoadFromString("Bug353793bad.oclinecore", testFileBad, bag);
	}
	
	public void testImport_OCLinEcore_Ecore() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String testFile =
			"import 'Names.ecore';\n" +
			"import nnnn : 'Names.ecore#/';\n" +
			"package moreNames {\n" +
			"class MoreNames {\n" +
			"attribute n1 : names::Named;\n" +
			"attribute n2 : nnnn::Named;\n" +
			"attribute n3 : xyzzy::Named;\n" +
			"attribute n4 : Named;\n" +
			"}\n" +
			"}\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(NLS.bind(OCLMessages.Unresolved_ERROR_, "Namespace", "xyzzy"));
		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "Named"));
		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "Named"));
		doBadLoadFromString("string.oclinecore", testFile, bag);
	}
	
	public void testImport_OCLinEcore_OCLinEcore() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String testFile =
			"import 'Names.oclinecore';\n" +
//FIXME			"import nnnn : 'Names.oclinecore#/';\n" +
			"package moreNames {\n" +
			"class MoreNames {\n" +
			"attribute n1 : EMOF::Class;\n" +
//			"attribute n2 : nnnn::Class;\n" +
//			"attribute n3 : xyzzy::Named;\n" +
//			"attribute n4 : Named;\n" +
			"}\n" +
			"}\n";
		Bag<String> bag = new BagImpl<String>();
//		bag.add(NLS.bind(OCLMessages.Unresolved_ERROR_, "Namespace", "xyzzy"));
//		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "Named"));
//		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "Named"));
		doBadLoadFromString("string.oclinecore", testFile, bag);
	}
	
	public void testImport_OCLinEcore_NoSuchFile() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String testFile =
			"import 'NoSuchFile1';\n" + 
			"import 'NoSuchFile2.ecore';\n" +
			"import 'NoSuchFile1';\n";
		Bag<String> bag = new BagImpl<String>();
		String template1 = "Cannot create a resource for ''{0}''; a registered resource factory is needed";
		String template2 = getNoSuchFileMessage();
		bag.add(NLS.bind(OCLMessages.UnresolvedImport_ERROR_, "NoSuchFile1", NLS.bind(template1, getProjectFileURI("NoSuchFile1"))));
		bag.add(NLS.bind(OCLMessages.UnresolvedImport_ERROR_, "NoSuchFile1", NLS.bind(template1, getProjectFileURI("NoSuchFile1"))));
		bag.add(NLS.bind(OCLMessages.UnresolvedImport_ERROR_, "NoSuchFile2.ecore", NLS.bind(template2, getProjectFileURI("NoSuchFile2.ecore").toFileString())));
		doBadLoadFromString("string.oclinecore", testFile, bag);
	}
	
	public void testImport_OCLstdlib_OCLstdlib() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String customLibrary =
			"library ocl {\n" +
			"type Complex : PrimitiveType {\n" +
			"operation spacedOut() : String => 'org.eclipse.ocl.examples.test.xtext.ImportTests$SpacedOut';\n" +
			"}\n" +
			"}\n";
		createOCLinEcoreFile("custom.oclstdlib", customLibrary);
		String testFile =
			"import 'http://www.eclipse.org/ocl/3.1.0/OCL.oclstdlib';\n" + 
			"import 'custom.oclstdlib';\n" +
			"library ocl : ocl = 'http://www.eclipse.org/ocl/3.1.0/OCL.oclstdlib' {\n" +
			"type MyType conformsTo OclAny{\n" +
			"operation mixIn(r : Real, z : Complex, t : MyType) : Boolean;\n" +
			"operation mixOut(q : WhatsThis) : Boolean;\n" +
			"}\n" +
			"}\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(NLS.bind(OCLMessages.UnresolvedType_ERROR_, "WhatsThis"));
		doBadLoadFromString("string.oclstdlib", testFile, bag);
	}
	
	public void testImport_OCLstdlib_NoSuchFile() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String testFile =
			"import 'http://www.eclipse.org/ocl/3.1.0/OCL.oclstdlib';\n" + 
			"import 'NoSuchFile1';\n" + 
			"import 'NoSuchFile2.oclstdlib';\n" +
			"import 'NoSuchFile1';\n" +
			"library anotherOne : xxx = 'http://www.eclipse.org/ocl/3.1.0/OCL.oclstdlib'{}\n";
		Bag<String> bag = new BagImpl<String>();
		String template1 = "Cannot create a resource for ''{0}''; a registered resource factory is needed";
		String template2 = getNoSuchFileMessage();
		bag.add(NLS.bind(OCLMessages.UnresolvedLibrary_ERROR_, "NoSuchFile1", NLS.bind(template1, getProjectFileURI("NoSuchFile1"))));
		bag.add(NLS.bind(OCLMessages.UnresolvedLibrary_ERROR_, "NoSuchFile1", NLS.bind(template1, getProjectFileURI("NoSuchFile1"))));
		bag.add(NLS.bind(OCLMessages.UnresolvedLibrary_ERROR_, "NoSuchFile2.oclstdlib", NLS.bind(template2, getProjectFileURI("NoSuchFile2.oclstdlib").toFileString())));
		doBadLoadFromString("string.oclstdlib", testFile, bag);
	}
	
	public void testImport_OCLstdlib_NoURI() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String testFile =
			"library anotherOne{}\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(OCLMessages.MissingLibraryURI_ERROR_);
		doBadLoadFromString("string.oclstdlib", testFile, bag);
	}
	
	public void testImport_OCLstdlib_WrongURI() throws Exception {
		rootLogger.removeAppender(testCaseAppender);
		String testFile =
			"import 'http://www.eclipse.org/ocl/3.1.0/OCL.oclstdlib';\n" + 
			"library anotherOne : xxx = 'http://www.eclipse.org/ocl/3.1/OCL.oclstdlib'{}\n";
		Bag<String> bag = new BagImpl<String>();
		bag.add(OCLMessages.EmptyLibrary_ERROR_);
		bag.add(NLS.bind(OCLMessages.UnresolvedLibrary_ERROR_, "http://www.eclipse.org/ocl/3.1.0/OCL.oclstdlib",
			NLS.bind(OCLMessages.ImportedLibraryURI_ERROR_, "http://www.eclipse.org/ocl/3.1.0/OCL.oclstdlib", "http://www.eclipse.org/ocl/3.1/OCL.oclstdlib")));
		doBadLoadFromString("string.oclstdlib", testFile, bag);
	}
}
