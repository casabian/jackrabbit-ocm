/*
 * Copyright 2000-2004 The Apache Software Foundation.
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
package org.apache.portals.graffito.jcr.persistence.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.apache.portals.graffito.jcr.exception.CustomNodeTypeCreationException;
import org.apache.portals.graffito.jcr.persistence.CustomNodeTypeCreator;
import org.apache.portals.graffito.jcr.persistence.PersistenceManager;

/** Default implementation of the jackrabbit custom node creator.
 *
 * @author <a href="mailto:okiessler@apache.org">Oliver Kiessler</a>
 * @version $Id: Exp $
 */
public class CustomNodeTypeCreatorImpl implements CustomNodeTypeCreator {
    
    /** Logger.
     */
    private final static Log log = LogFactory.getLog(CustomNodeTypeCreatorImpl.class); 
    
    /** Session to the jcr repository.
     */
    private PersistenceManagerImpl jcrSession;
    
    /** Creates a new instance of CustomNodeTypeCreatorImpl. */
    public CustomNodeTypeCreatorImpl()
    {
    }
    
    /** Creates a new instance of CustomNodeTypeCreatorImpl with a jcr session. 
     * @param jcrSession JcrSession
     */
    public CustomNodeTypeCreatorImpl(PersistenceManagerImpl jcrSession)
    {
        this.jcrSession = jcrSession;
    }

    /** This method is supposed to create custom node types on repository
     * setup.
     * 
     * @throws org.apache.portals.graffito.jcr.exception.CustomNodeTypeCreationException 
     * @return true/false
     */
    public boolean createInitialJcrCustomNodeTypes() throws CustomNodeTypeCreationException
    {
       
        // TODO
        
        return true;
    }

    /** Method to add a jcr custom node type to an existing jcr repository.
     * 
     * @throws org.apache.portals.graffito.jcr.exception.CustomNodeTypeCreationException 
     * @return true/false
     */
    public boolean addJcrCustomNodeType() throws CustomNodeTypeCreationException
    {
        
        // TODO
        
        return false;
    }
    
    /** Setter for property jcrSession.
     * 
     * @param jcrSession JcrSession
     */
    public void setJcrSession(PersistenceManagerImpl jcrSession)
    {
        this.jcrSession = jcrSession;
    }

    /** Getter for property jcrSession.
     * 
     * @return jcrSession
     */
    public PersistenceManager getJcrSession()
    {
        return jcrSession;
    }
}