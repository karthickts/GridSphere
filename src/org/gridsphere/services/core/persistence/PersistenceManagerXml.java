/**
 * @author <a href="mailto:novotny@gridsphere.org">Jason Novotny</a>
 * @version $Id: PersistenceManagerXml.java 4496 2006-02-08 20:27:04Z wehrens $
 */
package org.gridsphere.services.core.persistence;

import java.net.URL;

public interface PersistenceManagerXml {

    public void setMappingPath(String mappingPath);

    public void setMappingPath(URL mappingURL);

    public void setDescriptorPath(String descriptorPath);

    public String getDescriptorPath();

    public void save(Object object) throws PersistenceManagerException;

    public Object load() throws PersistenceManagerException;
}