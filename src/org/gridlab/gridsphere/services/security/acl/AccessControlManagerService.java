/*
 * @author <a href="mailto:novotny@aei.mpg.de">Jason Novotny</a>
 * @version $Id$
 */
package org.gridlab.gridsphere.services.security.acl;

import org.gridlab.gridsphere.portlet.User;
import org.gridlab.gridsphere.portlet.PortletGroup;
import org.gridlab.gridsphere.portlet.PortletRole;
import org.gridlab.gridsphere.portlet.service.PortletService;

public interface AccessControlManagerService extends PortletService {

    /**
     * Promotes a user to the role of super
     *
     * @param user the User object
     */
    public void addUserToSuperRole(User user);

    /**
     * Creates a new group
     *
     * @param groupName the name of the new group
     */
    public void createNewGroup(String groupName);

    /**
     * Rename an existing group
     *
     * @param oldGroupName the name of the old group
     * @param newGroupName the name of the new group
     */
    public void renameGroup(String oldGroupName, String newGroupName);

    /**
     * Removes a group
     *
     * @param group the PortletGroup
     */
    public void removeGroup(PortletGroup group);

    /**
     * Add a role to a user in a group
     *
     * @param user the User object
     * @param group the PortletGroup
     * @param role the PortletRole
     */
    public void addRoleInGroup(User user, PortletGroup group, PortletRole role);

    /**
     * Add a user to a group with a specified role
     *
     * @param user the User object
     * @param group the PortletGroup
     * @param role the PortletRole
     */
    public void addUserToGroup(User user, PortletGroup group, PortletRole role);

    /**
     * Removes a user from a group
     *
     * @param user the User object
     * @param group the PortletGroup
     */
    public void removeUserFromGroup(User user, PortletGroup group);

    /**
     * Remove a specified user role from a group
     *
     * @param user the User object
     * @param group the PortletGroup
     * @param role the PortletRole
     */
    public void removeUserRoleFromGroup(User user, PortletGroup group, PortletRole role);

}
