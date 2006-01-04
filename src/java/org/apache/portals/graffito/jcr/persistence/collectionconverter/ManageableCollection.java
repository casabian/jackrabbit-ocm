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

package org.apache.portals.graffito.jcr.persistence.collectionconverter;

import java.util.Iterator;

/** 
 * Common interface used to persist any kind of Collection or Map.
 * 
 *
 * @author <a href="mailto:christophe.lombart@gmail.com">Christophe Lombart</a>
 * 
 */
public interface ManageableCollection
{
    /**
     * Add an object into this ManageableCollection
     * @param object the object to add
     */
	public void addObject(Object object);
   
	/**
	 * @return The ManageableCollection iterator
	 */
    public Iterator getIterator();
    
    /** 
     * @return The ManageableCollection size
     */
    public int getSize();
    
    
}