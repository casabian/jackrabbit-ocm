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
package org.apache.portals.graffito.jcr.exception;

/** This exception is thrown during repository setup while creating the
 * custom graffito node types.
 *
 * @author <a href="mailto:okiessler@apache.org">Oliver Kiessler</a>
 * @version $Id: Exp $
 */
public class CustomNodeTypeCreationException extends JcrMappingException {
    
    /**
     * Default Constructor without information.
     */
    public CustomNodeTypeCreationException() 
    {
        super();
    }
    
    /**
     * Constructor with message.
     * 
     * @param message the message associated to the exception
     */
    public CustomNodeTypeCreationException(String message) 
    {
        super(message);
    }
    
    /**
     * Constructor with throwable object.
     * 
     * @param nested the associated throwable object
     */    
    public CustomNodeTypeCreationException(Throwable nested)
    {
        super(nested);
    }
    
    /**
     * Constructor with message and throwable object.
     * 
     * @param message the message associated to the exception
     * @param nested the associated throwable object
     */    
    public CustomNodeTypeCreationException(String message, Throwable nested)
    {
        super(message, nested);
    }
}