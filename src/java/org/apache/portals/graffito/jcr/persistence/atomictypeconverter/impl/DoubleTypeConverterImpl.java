/*
 * Copyright 2004-2005 The Apache Software Foundation or its licensors,
 *                     as applicable.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.portals.graffito.jcr.persistence.atomictypeconverter.impl;

import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.ValueFactory;

import org.apache.portals.graffito.jcr.exception.IncorrectAtomicTypeException;

/**
 * Double Type Converter 
 * 
 * @author <a href="mailto:christophe.lombart@gmail.com">Christophe Lombart</a>
 *
 */
public class DoubleTypeConverterImpl extends AbstractAtomicTypeConverterImpl
{
	/**
	 * Constructor
	 * @param factory The JCR Value factory to used
	 */

	public DoubleTypeConverterImpl(ValueFactory factory)
	{
		super(factory);

	}

	/**
	 * 
	 * @see org.apache.portals.graffito.jcr.persistence.atomictypeconverter.AtomicTypeConverter#getValue(java.lang.Object)
	 */
	public Value getValue(Object propValue)
	{
		if (propValue == null)
		{
			return null;
		}
		double value = ((Double) propValue).doubleValue();
		return this.getValueFactory().createValue(value);
	}

    /**
     * 
     * @see org.apache.portals.graffito.jcr.persistence.atomictypeconverter.AtomicTypeConverter#getObject(javax.jcr.Value)
     */
	public Object getObject(Value value)
    {
		try
		{
			double beanPropValue = value.getDouble();
			return new Double(beanPropValue);	
		}
		catch (RepositoryException e)
		{
			throw new IncorrectAtomicTypeException("Impossible to convert the value : " + value.toString(), e);
		}
	}
	
	/**
	 * 
	 * @see org.apache.portals.graffito.jcr.persistence.atomictypeconverter.AtomicTypeConverter#getStringValue(java.lang.Object)
	 */
	public String getStringValue(Object object)
	{
		
		return object.toString();
	}	


}