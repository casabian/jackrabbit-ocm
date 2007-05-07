/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.portals.graffito.jcr.repository;

import java.util.Hashtable;

import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jackrabbit.core.jndi.RegistryHelper;
import org.apache.jackrabbit.util.ISO9075;
import org.apache.jackrabbit.util.Text;
import org.apache.portals.graffito.jcr.exception.JcrMappingException;
import org.apache.portals.graffito.jcr.exception.PersistenceException;
import org.apache.portals.graffito.jcr.exception.RepositoryException;

/**
* Utility class for managing JCR repositories.
* <b>Note</b>: most of the utility methods in this class can be used only with Jackrabbit.
*
* @author <a href="mailto:christophe.lombart@sword-technologies.com">Lombart Christophe </a>
* @version $Id: Exp $
*/
public class RepositoryUtil
{
    
    /** Graffito namespace prefix constant.
     */
    public static final String GRAFFITO_NAMESPACE_PREFIX   = "graffito";

    /** Graffito namespace constant.
     */
    public static final String GRAFFITO_NAMESPACE          = "http://incubator.apache.org/graffito";    
    /** Item path separator */
    public static final String PATH_SEPARATOR = "/";
    
    private final static Log log = LogFactory.getLog(RepositoryUtil.class);  
    /**
     * Register a new repository 
     * 
     * @param repositoryName The repository unique name
     * @param configFile The JCR config file
     * @param homeDir The directory containing the complete repository settings (workspace, node types, ...)
     * 
     * @throws RepositoryException when it is not possible to register the repository
     */
    public static void registerRepository(String repositoryName, String configFile, String homeDir) throws RepositoryException
    {
        try
        {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.jackrabbit.core.jndi.provider.DummyInitialContextFactory");
            env.put(Context.PROVIDER_URL, "localhost");
            InitialContext ctx = new InitialContext(env);

            RegistryHelper.registerRepository(ctx, repositoryName, configFile, homeDir, true);
        }
        catch (Exception e)
        {        
            throw new RepositoryException("Impossible to register the respository : " + 
                                           repositoryName + " - config file : " + configFile, e);
        }        
        
    }
    
    
    /**
     * Unregister a repository 
     * 
     * @param repositoryName The repository unique name
     * 
     * @throws RepositoryException when it is not possible to unregister the repository
     */
    public static void unRegisterRepository(String repositoryName) throws RepositoryException
    {
        try
        {
        	Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.jackrabbit.core.jndi.provider.DummyInitialContextFactory");
            env.put(Context.PROVIDER_URL, "localhost");
            InitialContext ctx = new InitialContext(env);

            RegistryHelper.unregisterRepository(ctx, repositoryName);
        }
        catch (Exception e)
        {
            throw new RepositoryException("Impossible to unregister the respository : " + 
                                           repositoryName , e);
        }        
        
    }
    
    /**
     * Get a repository
     * 
     * @param repositoryName The repository name
     * @return a JCR repository reference
     * 
     * @throws RepositoryException when it is not possible to get the repository. 
     *         Before calling this method, the repository has to be registered (@see RepositoryUtil#registerRepository(String, String, String)
     */
    public static Repository getRepository(String repositoryName) throws RepositoryException
    {
        try
        {
            Hashtable env = new Hashtable();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.jackrabbit.core.jndi.provider.DummyInitialContextFactory");
            env.put(Context.PROVIDER_URL, "localhost");
            InitialContext ctx = new InitialContext(env);
            
            Repository repository = (Repository) ctx.lookup(repositoryName);
            return repository;
        }
        catch (Exception e)
        {
            throw new RepositoryException("Impossible to get the repository : " + repositoryName, e);
        }        
    }
    
    /**
     * Connect to a JCR repository
     * 
     * @param repository The JCR repository 
     * @param user The user name
     * @param password The password
     * @return a valid JCR session 
     * 
     * @throws RepositoryException when it is not possible to connect to the JCR repository 
     */
    public static Session login(Repository repository, String user, String password) throws RepositoryException
    {
        try
        {
            Session session = repository.login(new SimpleCredentials(user, password.toCharArray()), null);
            setupSession(session);
           
            return session; 
        }
        catch (Exception e)
        {
            throw new RepositoryException("Impossible to login ", e);
        }
    }
    
    /**
     * Check if a path is valid 
     * 
     * @param path The path to validate
     * @return true if the path is valid, else false
     */
    public static boolean isValidPath(String path)
    {
        if ((path == null) ||
            (path.equals(PATH_SEPARATOR)) ||
            (path.endsWith(PATH_SEPARATOR)) ||
            (! path.startsWith(PATH_SEPARATOR)) || 
            (path.equals("")))
        {
            return false; 
        }
        return true;
    }    
    
    /**
     * Get the parent path
     * @param path The path from wich the parent path has to be returned
     * @return The parent path
     * 
     * @throws PersistenceException when the path is invalid
     */
    public static String getParentPath(String path) throws PersistenceException
    {
        String parentPath = "";
        
        if (!isValidPath(path))
        {
            throw new JcrMappingException("Invalid path : " + path);
        }
        
        String[] pathElements = path.split(PATH_SEPARATOR);         
        
        // Firts path element should be = empty string because a uri always start with '/'
        // So, if len=2, means it is a root folder like '/foo'. 
        // In this case the uri has not parent folder => return "/"
        if (pathElements.length == 2)
        {
            return PATH_SEPARATOR;
        }
        
        for(int i=0; i < pathElements.length -1; i++)
        {   
            if (! pathElements[i].equals(""))
            {    
               parentPath += PATH_SEPARATOR + pathElements[i];
            }
        }                  
        return parentPath;
    }

    /**
     * Get the node name
     * @param path  The path from which the node name has to be retrieved
     * @return The node name
     * 
     * @throws PersistenceException when the path is invalid
     */
    public static String getNodeName(String path)  throws PersistenceException
    {
        
        String[] pathElements = path.split(PATH_SEPARATOR);
        
        if (! isValidPath(path))
        {
            throw new JcrMappingException("Invalid path : " + path);
        }        
        return pathElements[pathElements.length-1];
    }
    
    /**
     * Setup the session. 
     * Until now, we check only if the Graffito namespace prefix exist in the repository
     * 
     */
    private static void setupSession(Session session) throws RepositoryException
    {
         try
         {
            String[] jcrNamespaces = session.getWorkspace().getNamespaceRegistry().getPrefixes();
            boolean createGraffitoNamespace = true;
            for (int i = 0; i < jcrNamespaces.length; i++)
            {
                if (jcrNamespaces[i].equals(GRAFFITO_NAMESPACE_PREFIX))
                {
                    createGraffitoNamespace = false;
                    log.debug("Graffito namespace exists.");
                }
            }
             
            if (createGraffitoNamespace)
            {
                session.getWorkspace().getNamespaceRegistry().registerNamespace(GRAFFITO_NAMESPACE_PREFIX, GRAFFITO_NAMESPACE);
                log.info("Successfully created graffito namespace.");
            }
            
            if (session.getRootNode() != null)
            {
                log.info("Jcr repository setup successfull.");
            }
            

        }
        catch (Exception e)
        {
            log.error("Error while setting up the jcr repository.", e);
            throw new RepositoryException(e.getMessage());
        }
    }

    /**
     * Encode a path 
     * @TODO : drop Jackrabbit dependency
     * 
     * @param path the path to encode
     * @return the encoded path 
     * 
     */
    public static String encodePath(String path)
    {
    	String[] pathElements = Text.explode(path, '/');
    	for (int i=0;i<pathElements.length;i++)
    	{
    		pathElements[i] = ISO9075.encode(pathElements[i]);
    	}
    	return "/" + Text.implode(pathElements, "/");
    }
}