package org.eclipse.ocl.examples.debug.debugger;
/*******************************************************************************
 * Copyright (c) 2012 Antonio García-Domínguez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Antonio García-Domínguez - initial API and implementation
 ******************************************************************************/


import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.debug.core.model.IValue;
import org.eclipse.debug.ui.IDebugModelPresentation;
import org.eclipse.debug.ui.IValueDetailListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.ocl.examples.debug.plugin.OCLDebugPlugin;
import org.eclipse.ocl.examples.debug.util.EclipseUtil;
import org.eclipse.ocl.examples.domain.elements.DomainExpression;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

/**
 * Presentation model for the E*L debuggers. It's required in order to avoid a
 * NullPointerException when the source is missing.
 */
public class DebugModelPresentation implements IDebugModelPresentation {
	private ListenerList listeners = new ListenerList();
	private Image imgLoopVariable;

	public DebugModelPresentation() {
//		imgLoopVariable = OCLDebugPlugin.getImageDescriptor("/icons/variable_loop.gif").createImage();
	}

	public void addListener(ILabelProviderListener listener) {
		listeners.add(listener);
	}

	public void dispose() {
		if (imgLoopVariable != null) {
			imgLoopVariable.dispose();
			imgLoopVariable = null;
		}
	}

	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	public void removeListener(ILabelProviderListener listener) {
		listeners.remove(listener);
	}

	public IEditorInput getEditorInput(Object element) {
		final IFile file = getIFile(element);
		if (file != null) {
			return new FileEditorInput(file);
		}
		return null;
	}

	public String getEditorId(IEditorInput input, Object element) {
		final IFile file = getIFile(element);
		if (file != null) {
			return IDE.getDefaultEditor(file).getId();
		}
		return null;
	}

	public void setAttribute(String attribute, Object value) {
		// nothing to do
	}

	public Image getImage(Object element) {
		if (element instanceof EolVariable) {
			final EolVariable v = (EolVariable)element;
			if (v.isLoop()) {
				return imgLoopVariable;
			}
		}
		return null;
	}

	public String getText(Object element) {
		return null;
	}

	public void computeDetail(IValue value, IValueDetailListener listener) {
		// nothing to do
	}

	private IFile getIFile(Object element) {
		IFile file = null;
		if (element instanceof EolBreakpoint) {
			final EolBreakpoint eolB = (EolBreakpoint)element;
			final IResource resource = eolB.getMarker().getResource();
			if (resource instanceof IFile) {
				file = (IFile)resource;
			}
		}
		if (element instanceof DomainExpression) {
			file = EclipseUtil.findIFile((DomainExpression)element);
		}
		return file;
	}
}
