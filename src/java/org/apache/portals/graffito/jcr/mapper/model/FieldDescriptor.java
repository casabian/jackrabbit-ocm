/*
 * Copyright 2000-2005 The Apache Software Foundation.
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
package org.apache.portals.graffito.jcr.mapper.model;

/**
 * 
 *
 * FieldDescriptor is used by the mapper to read general information on a atomic field
 * 
 * @author <a href="mailto:christophe.lombart@sword-technologies.com">Lombart Christophe </a>
 *
 */
public class FieldDescriptor
{
     private String fieldName;
     private String fieldType;
     private Class fieldTypeClass;
     private String jcrName;
     private String jcrType;
     private boolean jcrAutoCreated;
     private boolean jcrMandatory;
     private String jcrOnParentVersion;
     private boolean jcrProtected;
     private boolean jcrMultiple;
     private ClassDescriptor classDescriptor;
     private boolean id;
     private boolean path;
     
    /**
     * @return Returns the fieldName.
     */
    public String getFieldName()
    {
        return fieldName;
    }

    /**
     * @param fieldName The fieldName to set.
     */
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    /**
     * @return the primitive or fully qualified class of the field
     * or <tt>null</tt> if not specified in the mapping
     */
    public String getFieldType()
    {
        return this.fieldType;
    }
    
    /**
     * Sets the type of the field. It supports primitive types, specified as
     * int, long, etc or fully qualified class names.
     * 
     * @param fieldType the type of the field
     */
    public void setFieldType(String fieldType)
    {
        this.fieldType = fieldType;
    }
    
    /**
     * @return the field class of the field
     * or <tt>null</tt> if not specified in the mapping
     * or if the class was not found
     */
    public Class getFieldTypeClass() 
    {
        if (this.fieldType == null) {
            return null;
        }
        if (this.fieldTypeClass == null) {
            this.fieldTypeClass = loadFieldTypeClass();
        }
        
        return this.fieldTypeClass;
    }
    
    /**
     * @return Returns the jcrName.
     */
    public String getJcrName()
    {
        return jcrName;
    }

    /**
     * @param jcrName The jcrName to set.
     */
    public void setJcrName(String jcrName)
    {
        this.jcrName = jcrName;
    }
    
    /**
     * 
     * @return the associated class descriptor
     */
    public ClassDescriptor getClassDescriptor()
    {
        return classDescriptor;
    }
    
    /**
     * Set the associated class descriptor 
     * @param classDescriptor  the class descriptor to set
     */
    public void setClassDescriptor(ClassDescriptor classDescriptor)
    {
        this.classDescriptor = classDescriptor;
    }
    
    /**    
     * @return true if the field is the class ID 
     */
    public boolean isId()
    {
        return id;
    }
    
    /**
     * 
     * @param id
     */
    public void setId(boolean id)
    {
        this.id = id;
    }

	/**
	 * @return Returns true if the field is the object JCR path.
	 */
	public boolean isPath()
	{
		return path;
	}

	/**
	 * @param path The path to set.
	 */
	public void setPath(boolean path)
	{
		this.path = path;
	}

    /** Getter for property jcrType.
     * 
     * @return jcrType
     */
    public String getJcrType()
    {
        return jcrType;
    }

    /** Setter for property jcrType.
     * 
     * @param value jcrType
     */
    public void setJcrType(String value)
    {
        this.jcrType = value;
    }

    /** Getter for propery jcrAutoCreated.
     * 
     * @return jcrAutoCreated
     */
    public boolean isJcrAutoCreated()
    {
        return jcrAutoCreated;
    }

    /** Setter for property jcrAutoCreated.
     * 
     * @param value jcrAutoCreated
     */
    public void setJcrAutoCreated(boolean value)
    {
        this.jcrAutoCreated = value;
    }

    /** Getter for property jcrMandatory.
     * 
     * @return jcrMandatory
     */
    public boolean isJcrMandatory()
    {
        return jcrMandatory;
    }

    /** Setter for property jcrMandatory.
     * 
     * @param value jcrMandatory
     */
    public void setJcrMandatory(boolean value)
    {
        this.jcrMandatory = value;
    }

    /** Getter for property jcrOnParentVersion.
     * 
     * @return jcrOnParentVersion
     */
    public String getJcrOnParentVersion()
    {
        return jcrOnParentVersion;
    }

    /** Setter for property jcrOnParentVersion.
     * 
     * @param value jcrOnParentVersion
     */
    public void setJcrOnParentVersion(String value)
    {
        this.jcrOnParentVersion = value;
    }

    /** Getter for property jcrProtected.
     * 
     * @return jcrProtected
     */
    public boolean isJcrProtected()
    {
        return jcrProtected;
    }

    /** Setter for property jcrProtected.
     * 
     * @param value jcrProtected
     */
    public void setJcrProtected(boolean value)
    {
        this.jcrProtected = value;
    }

    /** Getter for property jcrMultiple.
     * 
     * @return jcrMultiple
     */
    public boolean isJcrMultiple()
    {
        return jcrMultiple;
    }

    /** Setter for property jcrMultiple.
     * 
     * @param value jcrMultiple
     */
    public void setJcrMultiple(boolean value)
    {
        this.jcrMultiple = value;
    }
    
    /**
     * Initialize the fieldTypeClass.
     * 
     * @return the primitive class or the class accordign to fieldType
     */
    private Class loadFieldTypeClass() {
        if (this.fieldType == null) 
        {
            return null;
        }
        if ("byte".equals(this.fieldType)) 
        {
            return byte.class;
        }
        else if ("short".equals(this.fieldType)) 
        {
            return short.class;
        }
        else if ("int".equals(this.fieldType)) 
        {
            return int.class;
        }
        else if ("long".equals(this.fieldType)) 
        {
            return long.class;
        }
        else if ("float".equals(this.fieldType)) 
        {
            return float.class;
        }
        else if ("double".equals(this.fieldType)) 
        {
            return double.class;
        }
        else if ("char".equals(this.fieldType)) 
        {
            return char.class;
        }
        else if ("boolean".equals(this.fieldType))
        {
            return boolean.class;
        }
        else 
        {
            try {
                return Class.forName(this.fieldType);
            }
            catch (ClassNotFoundException cnfe) 
            {
                ; // nothing to do; it will be dynamically determined
            }
        }
        
        return null;
    }
}