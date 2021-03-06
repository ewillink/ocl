/**
 * <copyright>
 *
 * Copyright (c) 2010,2011 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ValueFactory.java,v 1.6 2011/05/07 16:41:16 ewillink Exp $
 */
package org.eclipse.ocl.examples.pivot.values;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.ocl.examples.pivot.ClassifierType;
import org.eclipse.ocl.examples.pivot.CollectionKind;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.InvalidValueException;
import org.eclipse.ocl.examples.pivot.TupleType;
import org.eclipse.ocl.examples.pivot.TypedElement;
import org.eclipse.ocl.examples.pivot.values.impl.ValueFactoryImpl;

public interface ValueFactory
{
	public static final ValueFactory INSTANCE = new ValueFactoryImpl("Default");
	
	public BooleanValue booleanValueOf(boolean value);
	
    public BagValue createBagOf(Object... objects);
	public BagValue createBagValue(Value... values);
	public BagValue createBagValue(Bag<? extends Value> values);
	public BagValue createBagValue(Collection<? extends Value> values);
    
	/**
	 * Creates a new OCL <tt>Collection</tt> of the specified ordering and uniqueness.
     * 
	 * @param isOrdered the required collection ordering
	 * @param isUnique the required collection uniqueness
	 * @return the new collection
	 */
	public CollectionValue createCollectionValue(boolean isOrdered, boolean isUnique, Value... values);
	public CollectionValue createCollectionValue(boolean isOrdered, boolean isUnique, Collection<Value> values);
	public CollectionValue createCollectionValue(CollectionKind kind, Value... values);
	public CollectionValue createCollectionValue(CollectionKind kind, Collection<Value> values);
	
	public <E extends Element> ElementValue<E> createElementValue(E element);
	
	public ObjectValue createObjectValue(Object object);

    public OrderedSetValue createOrderedSetOf(Object... objects);
	public OrderedSetValue createOrderedSetValue(Value... values);
	public OrderedSetValue createOrderedSetValue(LinkedHashSet<? extends Value> values);
	public OrderedSetValue createOrderedSetValue(Collection<? extends Value> values);

    public SequenceValue createSequenceOf(Object... objects);
	public SequenceValue createSequenceValue(Value... values);
	public SequenceValue createSequenceValue(List<? extends Value> values);
	public SequenceValue createSequenceValue(Collection<? extends Value> values);
	
    public SetValue createSetOf(Object... objects);
	public SetValue createSetValue(Value... values);
	public SetValue createSetValue(Set<? extends Value> values);
	public SetValue createSetValue(Collection<? extends Value> values);
	
	public Value createTupleValue(TupleType type, Map<? extends TypedElement, Value> values);

	public Value createTypeValue(ClassifierType type);

	public Object getEcoreValueOf(Value result);

	public BagValue getEmptyBagValue();
	public OrderedSetValue getEmptyOrderedSetValue();
	public SequenceValue getEmptySequenceValue();	
	public SetValue getEmptySetValue();
	public BooleanValue getFalse();
	public InvalidValue getInvalid();
	public NullValue getNull();
	public BooleanValue getTrue();
	public UnlimitedValue getUnlimited();
	public NumericValue getZero();
	
	public IntegerValue integerValueOf(long value);
	public IntegerValue integerValueOf(BigInteger value);
	public IntegerValue integerValueOf(String aValue) throws InvalidValueException;
	
	public RealValue realValueOf(double value);
	public RealValue realValueOf(BigDecimal value);
	public RealValue realValueOf(IntegerValue integerValue);	
	public RealValue realValueOf(String aValue) throws InvalidValueException;
	
	public StringValue stringValueOf(String value) ;
	
	public InvalidValue throwInvalidValueException(String message, Object... bindings) throws InvalidValueException;

	public Value valueOf(Object object);
	public Value valueOf(Object eValue, ETypedElement eFeature);
}
 