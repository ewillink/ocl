/**
 * <copyright>
 *
 * Copyright (c) 2011 E.D.Willink and others.
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
 * $Id$
 */
package org.eclipse.ocl.examples.xtext.markup;

import java.io.Reader;
import java.io.StringReader;

import org.eclipse.ocl.examples.pivot.utilities.TypeManager;
import org.eclipse.ocl.examples.xtext.markup.parser.antlr.MarkupParser;
import org.eclipse.ocl.examples.xtext.markup.util.MarkupSwitch;
import org.eclipse.xtext.parser.IParseResult;

import com.google.inject.Injector;

public class MarkupUtils extends MarkupSwitch<StringBuffer>
{
/*	public static Markup decode(String text) {
//		System.out.println("decode: " + text);	
		Injector injector = MarkupStandaloneSetup.getInjector();
		MarkupParser parser = injector.getInstance(MarkupParser.class);
		Reader reader = new StringReader(text);
		IParseResult parseResult = parser.parse(reader);
		Iterable<INode> parseErrors = parseResult.getSyntaxErrors();
//		List<SyntaxError> parseErrors = parseResult.getParseErrors();
		EObject rootASTElement = parseResult.getRootASTElement();
//		System.out.println("decoded: " + reader.debug);
		for (INode parseError : parseErrors) {
			System.out.println("error : " + parseError.getSyntaxErrorMessage());
		}
		return (Markup)rootASTElement;
	} */

	public static IParseResult decode(String text) {
//		System.out.println("decode: " + text);	
		Injector injector = MarkupStandaloneSetup.getInjector();
		MarkupParser parser = injector.getInstance(MarkupParser.class);
		Reader reader = new StringReader(text);
		return parser.parse(reader);
	}

	public static int getNewlineCount(NewLineElement element) {
		int lineCount = 0;
		String s = element.getText();
		int iMax = s.length();
		for (int i = 0; i < iMax; ) {
			int c = s.charAt(i++);
			if (c == '\n') {
				lineCount++;
				if (i < iMax) {
					c = s.charAt(i);
					if (c == '\r') {
						i++;
					}
				}
			}
			else if (c == '\r') {
				lineCount++;
				if (i < iMax) {
					c = s.charAt(i);
					if (c == '\n') {
						i++;
					}
				}
			}
		}
		return lineCount;
	}

	public static String toHTML(TypeManager typeManager, Object context, Markup markup) throws Exception {
		return MarkupToHTML.toString(typeManager, context, markup);
	}
}

