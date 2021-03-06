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
 * $Id: OCLinEcoreDocument.java,v 1.11 2011/05/06 10:41:16 ewillink Exp $
 */
package org.eclipse.ocl.examples.xtext.oclinecore.ui.model;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.ocl.examples.common.plugin.OCLExamplesCommonPlugin;
import org.eclipse.ocl.examples.pivot.ecore.Pivot2Ecore;
import org.eclipse.ocl.examples.pivot.uml.Pivot2UML;
import org.eclipse.ocl.examples.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.examples.xtext.base.utilities.CS2PivotResourceAdapter;
import org.eclipse.ocl.examples.xtext.essentialocl.ui.model.BaseDocument;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.DocumentTokenSource;
import org.eclipse.xtext.ui.editor.model.edit.ITextEditComposer;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

import com.google.inject.Inject;

/**
 * An OCLinEcoreDocument refines a document to support generation of an alternate (XMI) content
 * for use during save in place of its normal textual content.
 */
public class OCLinEcoreDocument extends BaseDocument
{
	@Inject
	public OCLinEcoreDocument(DocumentTokenSource tokenSource, ITextEditComposer composer) {
		super(tokenSource, composer);
	}

	protected void checkForErrors(Resource resource) throws CoreException {
		List<Resource.Diagnostic> errors = resource.getErrors();
		if (errors.size() > 0) {
			StringBuffer s = new StringBuffer();
			for (Resource.Diagnostic diagnostic : errors) {
				s.append("\n");
				s.append(diagnostic.toString());
			}
			throw new CoreException(new Status(IStatus.ERROR, OCLExamplesCommonPlugin.PLUGIN_ID, s.toString()));
		}
	}

	protected XMLResource getPivotResouce() throws CoreException {
		return readOnly(new IUnitOfWork<XMLResource, XtextResource>()
			{
				public XMLResource exec(XtextResource resource) throws Exception {
					CS2PivotResourceAdapter adapter = CS2PivotResourceAdapter.findAdapter((BaseCSResource)resource);
					XMLResource pivotResource = (XMLResource) adapter.getPivotResource(resource);
					checkForErrors(pivotResource);
					return pivotResource;
				}
			});
	}

	/**
	 * Write the XMI representation of the Ecore to be saved.
	 */
	public void saveAsEcore(final Writer writer, final URI ecoreURI) throws IOException, CoreException {
		readOnly(new IUnitOfWork<Object, XtextResource>()
			{
				public Object exec(XtextResource resource) throws Exception {
					XMLResource pivotResource = getPivotResouce();
					CS2PivotResourceAdapter adapter = CS2PivotResourceAdapter.findAdapter((BaseCSResource)resource);
					XMLResource ecoreResource = Pivot2Ecore.createResource(adapter.getTypeManager(), pivotResource, ecoreURI);
//					ResourceSetImpl resourceSet = new ResourceSetImpl();
//					XMLResource ecoreResource = (XMLResource) resourceSet.createResource(ecoreURI);
//					ecoreResource.getContents().addAll(ecoreContents);
					ecoreResource.save(writer, null);
					checkForErrors(ecoreResource);
					return null;
				}
			});
	}

	/**
	 * Write the XMI representation of the Pivot to be saved.
	 */
	public void saveAsPivot(StringWriter writer) throws CoreException, IOException {
		XMLResource pivotResource = getPivotResouce();
		pivotResource.save(writer, null);
	}

	/**
	 * Write the XMI representation of the UML to be saved.
	 */
	public void saveAsUML(final Writer writer, final URI umlURI) throws IOException, CoreException {
		readOnly(new IUnitOfWork<Object, XtextResource>()
			{
				public Object exec(XtextResource resource) throws Exception {
					XMLResource pivotResource = getPivotResouce();
					CS2PivotResourceAdapter adapter = CS2PivotResourceAdapter.findAdapter((BaseCSResource)resource);
					List<EObject> umlContents = Pivot2UML.createResource(adapter.getTypeManager(), pivotResource);
					ResourceSetImpl resourceSet = new ResourceSetImpl();
	//				URI umlURI = URI.createURI("internal.uml");
					UMLResource umlResource = (UMLResource) resourceSet.createResource(umlURI);
					umlResource.getContents().addAll(umlContents);
					checkForErrors(umlResource);
					umlResource.save(writer, null);
					return null;
				}
			});
	}
}
