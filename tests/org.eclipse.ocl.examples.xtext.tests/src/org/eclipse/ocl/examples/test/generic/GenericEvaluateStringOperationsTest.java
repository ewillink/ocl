/**
 * <copyright>
 * 
 * Copyright (c) 2009,2010 Eclipse Modeling Project and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   L.Goubet, E.D.Willink - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GenericEvaluateStringOperationsTest.java,v 1.1.2.2 2010/12/09 22:15:47 ewillink Exp $
 */

package org.eclipse.ocl.examples.test.generic;


/**
 * Tests for String operations.
 */
@SuppressWarnings("nls")
public abstract class GenericEvaluateStringOperationsTest
	extends GenericTestSuite {

    @Override
    protected void setUp() {
        super.setUp();
//        helper.setContext(getMetaclass(denormalize("%Package")));
        helper.setContext(getMetaclass("Classifier"));
    }

	public void testStringConcat() {
		assertQueryEquals(null, "concatenationTest", "'concatenation'.concat('Test')");
		assertQueryEquals(null, "concatenation\n", "'concatenation'.concat('\\n')");
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in 'concatenation'.concat(s)");
		assertQueryInvalid(null, "let s : String = invalid in s.concat('concatenation')");
		// null
		assertQueryInvalid(null, "let s : String = null in 'concatenation'.concat(s)");
		assertQueryInvalid(null, "let s : String = null in s.concat('concatenation')");
	}

	public void testStringEqual() {
		assertQueryFalse(null, "'test' = 'se'");
		assertQueryTrue(null, "'test' = 'test'");
		// invalid
		assertQueryFalse(null, "let s : String = invalid in s = 'se'");
		assertQueryFalse(null, "let s : String = invalid in 'test' = s");
		assertQueryTrue(null, "let s1 : String = invalid, s2 : String = invalid in s1 = s2");
		// null
		assertQueryFalse(null, "let s : String = null in s = 'se'");
		assertQueryFalse(null, "let s : String = null in 'test' = s");
		assertQueryTrue(null, "let s1 : String = null, s2 : String = null in s1 = s2");
	}

	public void testStringGreaterThan() {
		// FIXME Analyzer-extraOperation String::> should not be defined
		assertQueryFalse(null, "'3' > '4'");
		assertQueryFalse(null, "'a' > 'b'");
		assertQueryFalse(null, "'aardvark' > 'aardvarks'");

		assertQueryTrue(null, "'3.2' > '3.1'");
		assertQueryTrue(null, "'a' > 'A'");
		assertQueryTrue(null, "'aardvark' > 'aardvarK'");

		assertQueryFalse(null, "'3' > '3'");
		assertQueryFalse(null, "'a' > 'a'");
		assertQueryFalse(null, "'aardvark' > 'aardvark'");
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in s > 'se'");
		assertQueryInvalid(null, "let s : String = invalid in 'test' > s");
		assertQueryInvalid(null, "let s1 : String = invalid, s2 : String = invalid in s1 > s2");
		// null
		assertQueryInvalid(null, "let s : String = null in s > 'se'");
		assertQueryInvalid(null, "let s : String = null in 'test' > s");
		assertQueryInvalid(null, "let s1 : String = null, s2 : String = null in s1 > s2");
	}

	public void testStringGreaterThanOrEqual() {
		// FIXME Analyzer-extraOperation String::>= should not be defined
		assertQueryFalse(null, "'3' >= '4'");
		assertQueryFalse(null, "'a' >= 'b'");
		assertQueryFalse(null, "'aardvark' >= 'aardvarks'");

		assertQueryTrue(null, "'3.2' >= '3.1'");
		assertQueryTrue(null, "'a' >= 'A'");
		assertQueryTrue(null, "'aardvark' >= 'aardvarK'");

		assertQueryTrue(null, "'3' >= '3'");
		assertQueryTrue(null, "'a' >= 'a'");
		assertQueryTrue(null, "'aardvark' >= 'aardvark'");
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in s >= 'se'");
		assertQueryInvalid(null, "let s : String = invalid in 'test' >= s");
		assertQueryInvalid(null, "let s1 : String = invalid, s2 : String = invalid in s1 >= s2");
		// null
		assertQueryInvalid(null, "let s : String = null in s >= 'se'");
		assertQueryInvalid(null, "let s : String = null in 'test' >= s");
		assertQueryInvalid(null, "let s1 : String = null, s2 : String = null in s1 >= s2");
	}

	public void testStringLessThan() {
		// FIXME Analyzer-extraOperation String::< should not be defined
		assertQueryTrue(null, "'3' < '4'");
		assertQueryTrue(null, "'a' < 'b'");
		assertQueryTrue(null, "'aardvark' < 'aardvarks'");

		assertQueryFalse(null, "'3.2' < '3.1'");
		assertQueryFalse(null, "'a' < 'A'");
		assertQueryFalse(null, "'aardvark' < 'aardvarK'");

		assertQueryFalse(null, "'3' < '3'");
		assertQueryFalse(null, "'a' < 'a'");
		assertQueryFalse(null, "'aardvark' < 'aardvark'");
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in s < 'se'");
		assertQueryInvalid(null, "let s : String = invalid in 'test' < s");
		assertQueryInvalid(null, "let s1 : String = invalid, s2 : String = invalid in s1 < s2");
		// null
		assertQueryInvalid(null, "let s : String = null in s < 'se'");
		assertQueryInvalid(null, "let s : String = null in 'test' < s");
		assertQueryInvalid(null, "let s1 : String = null, s2 : String = null in s1 < s2");
	}

	public void testStringLessThanOrEqual() {
		// FIXME Analyzer-extraOperation String::<= should not be defined
		assertQueryTrue(null, "'3' <= '4'");
		assertQueryTrue(null, "'a' <= 'b'");
		assertQueryTrue(null, "'aardvark' <= 'aardvarks'");

		assertQueryFalse(null, "'3.2' <= '3.1'");
		assertQueryFalse(null, "'a' <= 'A'");
		assertQueryFalse(null, "'aardvark' <= 'aardvarK'");

		assertQueryTrue(null, "'3' <= '3'");
		assertQueryTrue(null, "'a' <= 'a'");
		assertQueryTrue(null, "'aardvark' <= 'aardvark'");
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in s <= 'se'");
		assertQueryInvalid(null, "let s : String = invalid in 'test' <= s");
		assertQueryInvalid(null, "let s1 : String = invalid, s2 : String = invalid in s1 <= s2");
		// null
		assertQueryInvalid(null, "let s : String = null in s <= 'se'");
		assertQueryInvalid(null, "let s : String = null in 'test' <= s");
		assertQueryInvalid(null, "let s1 : String = null, s2 : String = null in s1 <= s2");
	}

	public void testStringNotEqual() {
		assertQueryTrue(null, "'test' <> 'se'");
		assertQueryFalse(null, "'test' <> 'test'");
		// invalid
		assertQueryTrue(null, "let s : String = invalid in s <> 'se'");
		assertQueryTrue(null, "let s : String = invalid in 'test' <> s");
		assertQueryFalse(null, "let s1 : String = invalid, s2 : String = invalid in s1 <> s2");
		// null
		assertQueryTrue(null, "let s : String = null in s <> 'se'");
		assertQueryTrue(null, "let s : String = null in 'test' <> s");
		assertQueryFalse(null, "let s1 : String = null, s2 : String = null in s1 <> s2");
	}

	public void testStringOclAsType() {
		assertQueryInvalid(null, "'test'.oclAsType(Integer)");
		assertQueryEquals(null, "test", "'test'.oclAsType(String)");
		assertQueryEquals(null, "test", "'test'.oclAsType(OclAny)");
		assertQueryInvalid(null, "'test'.oclAsType(OclVoid)");
		assertQueryInvalid(null, "'test'.oclAsType(OclInvalid)");
	}

	public void testStringOclIsInvalid() {
		assertQueryFalse(null, "'test'.oclIsInvalid()");
		assertQueryFalse(null, "''.oclIsInvalid()");
	}

	public void testStringOclIsKindOf() {
		assertQueryFalse(null, "'test'.oclIsKindOf(Integer)");
		assertQueryTrue(null, "'test'.oclIsKindOf(String)");
		assertQueryTrue(null, "'test'.oclIsKindOf(OclAny)");
		assertQueryFalse(null, "'test'.oclIsKindOf(OclVoid)");
		assertQueryFalse(null, "'test'.oclIsKindOf(OclInvalid)");
	}

	public void testStringOclIsTypeOf() {
		assertQueryFalse(null, "'test'.oclIsTypeOf(Integer)");
		assertQueryTrue(null, "'test'.oclIsTypeOf(String)");
		assertQueryFalse(null, "'test'.oclIsTypeOf(OclAny)");
		assertQueryFalse(null, "'test'.oclIsTypeOf(OclVoid)");
		assertQueryFalse(null, "'test'.oclIsTypeOf(OclInvalid)");
	}

	public void testStringOclIsUndefined() {
		assertQueryFalse(null, "'test'.oclIsUndefined()");
		assertQueryFalse(null, "''.oclIsUndefined()");
	}

	public void testStringSize() {
		assertQueryEquals(null, Integer.valueOf(4), "'test'.size()"); //$NON-NLS-2$
		assertQueryEquals(null, Integer.valueOf(0), "''.size()"); //$NON-NLS-2$
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in s.size()"); //$NON-NLS-2$
		// null
		assertQueryInvalid(null, "let s : String = null in s.size()"); //$NON-NLS-2$
	}

	public void testStringSubstring() {
		assertQueryEquals(null, "t", "'test'.substring(1, 1)");
		assertQueryEquals(null, "es", "'test'.substring(2, 3)");
		assertQueryEquals(null, "t", "'test'.substring(4, 4)");
		// illegal
		assertQueryInvalid(null, "'test'.substring(2, 1)");
		assertQueryInvalid(null, "'test'.substring(3, 1)");
		// out of bounds
		assertQueryInvalid(null, "'test'.substring(0, 1)");
		assertQueryInvalid(null, "'test'.substring(4, 5)");
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in s.substring(1, 1)");
		assertQueryInvalid(null, "let s : String = invalid in s.substring(5, 5)");
		// null
		assertQueryInvalid(null, "let s : String = null in s.substring(1, 1)");
		assertQueryInvalid(null, "let s : String = null in s.substring(5, 5)");
	}

	public void testStringToBoolean() {
		assertQueryTrue(null, "'true'.toBoolean()");
		assertQueryFalse(null, "' true'.toBoolean()");
		assertQueryFalse(null, "'true '.toBoolean()");
		assertQueryFalse(null, "'True'.toBoolean()");
		assertQueryFalse(null, "'false'.toBoolean()");
		assertQueryFalse(null, "'-4'.toBoolean()");
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in s.toBoolean()");
		// null
		assertQueryInvalid(null, "let s : String = null in s.toBoolean()");
	}

	public void testStringToInteger() {
		assertQueryEquals(null, Integer.valueOf(4), "'4'.toInteger()");
		assertQueryEquals(null, Integer.valueOf(-4), "'-4'.toInteger()");
		assertQueryInvalid(null, "'4.0'.toInteger()");

		assertQueryInvalid(null, "'2.4.0'.toInteger()");
		assertQueryInvalid(null, "'a'.toInteger()");
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in s.toInteger()");
		// null
		assertQueryInvalid(null, "let s : String = null in s.toInteger()");
	}

	public void testStringToLowerCase() {
//		checkForUTF8Encoding()		
		assertQueryEquals(null, "4", "'4'.toLowerCase()"); //$NON-NLS-2$
		assertQueryEquals(null, "mixed", "'MiXeD'.toLowerCase()"); //$NON-NLS-2$
		assertQueryEquals(null, "upper", "'UPPER'.toLowerCase()"); //$NON-NLS-2$
		// Ensures word-final sigma and regular sigmas are converted as needed
//		assertQueryEquals(null, 
//			"Ã¡Â½â‚¬ÃŽÂ´Ã�â€¦Ã�Æ’Ã�Æ’ÃŽÂµÃ�ï¿½Ã�â€š", "'Ã¡Â½Ë†ÃŽâ€�ÃŽÂ¥ÃŽÂ£ÃŽÂ£ÃŽâ€¢ÃŽÅ½ÃŽÂ£'.toLowerCase()");
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in s.toLowerCase()");
		// null
		assertQueryInvalid(null, "let s : String = null in s.toLowerCase()");
	}

	public void testStringToReal() {
		assertQueryEquals(null, Double.valueOf(4d), "'4'.toReal()");
		assertQueryEquals(null, Double.valueOf(-4d), "'-4'.toReal()");
		assertQueryEquals(null, Double.valueOf(4d), "'4.0'.toReal()");

		assertQueryInvalid(null, "'2.4.0'.toReal()");
		assertQueryInvalid(null, "'a'.toReal()");
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in s.toReal()");
		// null
		assertQueryInvalid(null, "let s : String = null in s.toReal()");
	}

	public void testStringToUpperCase() {
//		checkForUTF8Encoding();
		assertQueryEquals(null, "4", "'4'.toUpperCase()");
		assertQueryEquals(null, "MIXED", "'MiXeD'.toUpperCase()");
		assertQueryEquals(null, "LOWER", "'lower'.toUpperCase()");
		
		// Ensures word-final sigma and regular sigmas are converted as needed
//		assertQueryEquals(null, "Ã¡Â½Ë†ÃŽâ€�ÃŽÂ¥ÃŽÂ£ÃŽÂ£ÃŽâ€¢ÃŽÅ½ÃŽÂ£", "'Ã¡Â½â‚¬ÃŽÂ´Ã�â€¦Ã�Æ’Ã�Æ’ÃŽÂµÃ�ï¿½Ã�â€š'.toUpperCase()");
		
		// Sharp s should be mapped to a double S upper case
//		assertQueryEquals(null, "SS", "'ÃƒÅ¸'.toUpperCase()");
		// invalid
		assertQueryInvalid(null, "let s : String = invalid in s.toUpperCase()");
		// null
		assertQueryInvalid(null, "let s : String = null in s.toUpperCase()");
	}
}