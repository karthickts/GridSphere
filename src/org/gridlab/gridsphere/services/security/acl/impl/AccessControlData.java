/*
 * @author <a href="mailto:novotny@aei.mpg.de">Jason Novotny</a>
 * @version $Id$
 */
package org.gridlab.gridsphere.services.security.acl.impl;

import java.util.Vector;

/**
 * AccessControlData maintains the access control information in the form of a generic nested hashtable of hashtables
 * The keys of the hashtable are the group names and the values are hashtables representing users as keys and the
 * values are hashtables respresenting roles. Only the outmost data structure needs to be synchronized.
 * Only one instance of the AccessControlData may be obtained
 */
public class AccessControlData {

    private Vector groups = null;
    private Vector superusers = null;

    public AccessControlData(Vector superusers) {
        groups = new Vector();
        this.superusers = superusers;
    }

    public Vector getSuperUsers() {
        return superusers;
    }

    public GroupACL createGroup() {
        return new GroupACL();
    }

    public Vector getGroups() {
        return groups;
    }

    public void addGroup(GroupACL group) {
        groups.add(group);
    }

    public void removeGroup(GroupACL group) {
        if (groups.contains(group)) {
            groups.remove(group);
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < groups.size(); i++) {
            sb.append(groups.get(i).toString());
        }
        return sb.toString();
    }

}
