/**
 * <copyright>
 *
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: TupleImpl.java,v 1.1.2.1 2010/10/01 13:51:56 ewillink Exp $
 */

package org.eclipse.ocl.examples.pivot.values;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.ocl.examples.pivot.Property;
import org.eclipse.ocl.examples.pivot.TupleType;

/**
 * UML implementation of a tuple value.
 * 
 * @author Christian W. Damus (cdamus)
 */
public class TupleImpl implements Tuple
{
    private final TupleType type;

    private final Map<String, Object> parts = new java.util.HashMap<String, Object>();

    /**
     * Initializes me with a map of part values.
     * 
     * @param type my type
     * @param values my parts
     */
    public TupleImpl(TupleType type, Map<Property, Object> values) {
        this.type = type;

        for (Map.Entry<Property, Object> entry : values.entrySet()) {
            parts.put(entry.getKey().getName(), entry.getValue());
        }
    }
    
    /**
     * Convenience constructor to initialize me with a pair of part values as
     * required by the Collection::product() operation.
     * 
     * @param type my type
     * @param firstValue my first value
     * @param secondValue my second value
     */
    public TupleImpl(TupleType type, Object firstValue, Object secondValue) {
        this.type = type;						// FIXME use optimised ProductTupleImpl
        parts.put("first", firstValue);			// FIXME define "first" elsewhere
        parts.put("second", secondValue);
    }

    // implements the inherited specification
    public TupleType getTupleType() {
        return type;
    }

    // implements the inherited specification
    public Object getValue(String partName) {
        return parts.get(partName);
    }

    // implements the inherited specification
    public Object getValue(Property part) {
        return getValue(part.getName());
    }

    // overrides the inherited implementation
    @Override
    public boolean equals(Object o) {
        boolean result = o instanceof TupleImpl;

        if (result) {
            TupleImpl other = (TupleImpl) o;

            result &= other.type.equals(type);
            result &= other.parts.equals(parts);
        }

        return result;
    }

    // overrides the inherited implementation
    @Override
    public int hashCode() {
        return 37 * type.hashCode() + 17 * parts.hashCode();
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Tuple{"); //$NON-NLS-1$
        
        for (Iterator<Property> iter =  getTupleType().getOwnedAttributes().iterator();
                iter.hasNext();) {
            
            Property p = iter.next();
            
            result.append(p.getName());
            result.append(" = "); //$NON-NLS-1$
            result.append(toString(getValue(p)));
            
            if (iter.hasNext()) {
                result.append(", "); //$NON-NLS-1$
            }
        }
        
        result.append("}"); //$NON-NLS-1$
        return result.toString();
    }
    
    private String toString(Object o) {
        if (o instanceof String) {
            return "'" + (String) o + "'"; //$NON-NLS-1$ //$NON-NLS-2$
        } else if (o instanceof Collection<?>) {
            return CollectionUtil.toString((Collection<?>) o);
        } else if (o == null) {
            return "null"; //$NON-NLS-1$
        } else {
            return o.toString();
        }
    }
}