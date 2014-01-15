/**
 * <copyright>
 *
 * Copyright (c) 2013 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *	E.D.Willink (CEA LIST) - initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.emf.validation.debug.ui.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public abstract class SideBySideImageDecorator extends LabelProvider implements ILabelDecorator
{
	protected final int gap;

	public SideBySideImageDecorator(int gap) {
		this.gap = gap;
	}

	public Image composeImages(Image image, Object image2) {
		if (image == null) {
			return ExtendedImageRegistry.INSTANCE.getImage(image2);
		} else {
			List<Object> images = new ArrayList<Object>(2);
			images.add(image2);
			images.add(image);
			ComposedImage composedImage = new ComposedImage(images) {
				@Override
				public List<Point> getDrawPoints(Size size) {
					List<Point> result = new ArrayList<Point>();
					result.add(new Point());
					Point overlay = new Point();
					overlay.x = (size.width - gap) / 2;
					overlay.y = 0;
					result.add(overlay);
					return result;
				}

				@Override
				public Size getSize(Collection<? extends Size> imageSizes) {
				    this.imageSizes = new ArrayList<Size>(imageSizes);
				    Size result = new Size();
				    for (Size size : imageSizes)
				    {
				      result.width += size.width + (result.width > 0 ? gap : 0);
				      result.height = Math.max(result.height, size.height);
				    }
				    return result;
				}
			};
			return ExtendedImageRegistry.INSTANCE.getImage(composedImage);
		}
	}

	public String decorateText(String text, Object element) {
		return text;
	}
}
