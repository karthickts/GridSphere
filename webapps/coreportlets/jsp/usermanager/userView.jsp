<%@ page import="org.gridlab.gridsphere.portlet.User,
                 org.gridlab.gridsphere.portlets.core.beans.UserManagerBean,
                 java.util.List,
                 org.gridlab.gridsphere.portlet.PortletRole,
                 org.gridlab.gridsphere.portlet.PortletGroup,
                 java.util.Iterator" %>
<%@ taglib uri="/portletWidgets" prefix="gs" %>
<%@ taglib uri="/portletAPI" prefix="portletAPI" %>
<portletAPI:init/>
<jsp:useBean id="userManagerBean"
             class="org.gridlab.gridsphere.portlets.core.beans.UserManagerBean"
             scope="request"/>
<form name="UserManagerPortlet" method="POST"
      action="<%=userManagerBean.getPortletActionURI(UserManagerBean.ACTION_USER_VIEW)%>">
  <input type="hidden" name="userID" value="<%=userManagerBean.getUserID()%>"/>
  <script type="text/javascript">
    function UserManagerPortlet_listUser_onClick() {
      document.UserManagerPortlet.action="<%=userManagerBean.getPortletActionURI(UserManagerBean.ACTION_USER_LIST)%>";
      document.UserManagerPortlet.submit();
    }

    function UserManagerPortlet_newUser_onClick(userID) {
      document.UserManagerPortlet.userID.value="";
      document.UserManagerPortlet.action="<%=userManagerBean.getPortletActionURI(UserManagerBean.ACTION_USER_EDIT)%>";
      document.UserManagerPortlet.submit();
    }

    function UserManagerPortlet_editUser_onClick() {
      document.UserManagerPortlet.action="<%=userManagerBean.getPortletActionURI(UserManagerBean.ACTION_USER_EDIT)%>";
      document.UserManagerPortlet.submit();
    }

    function UserManagerPortlet_deleteUser_onClick() {
      document.UserManagerPortlet.action="<%=userManagerBean.getPortletActionURI(UserManagerBean.ACTION_USER_DELETE)%>";
      document.UserManagerPortlet.submit();
    }
  </script>
<table class="portlet-pane" cellspacing="1">
  <tr>
    <td>
      <table class="portlet-frame" cellspacing="1" width="100%">
        <tr>
          <td class="portlet-frame-title">
              View User [<%=userManagerBean.getUserName()%>]
          </td>
        </tr>
        <tr>
          <td class="portlet-frame-actions">
            <input type="button"
                   name="<%=UserManagerBean.ACTION_USER_LIST%>"
                   value="List Users"
                   onClick="javascript:UserManagerPortlet_listUser_onClick()"/>
            &nbsp;&nbsp;<input type="button"
                   name="<%=UserManagerBean.ACTION_USER_EDIT%>"
                   value="New User"
                   onClick="javascript:UserManagerPortlet_newUser_onClick()"/>
            &nbsp;&nbsp;<input type="button"
                   name="<%=UserManagerBean.ACTION_USER_EDIT%>"
                   value="Edit User"
                   onClick="javascript:UserManagerPortlet_editUser_onClick()"/>
            &nbsp;&nbsp;<input type="button"
                   name="<%=UserManagerBean.ACTION_USER_DELETE%>"
                   value="Delete User"
                   onClick="javascript:UserManagerPortlet_deleteUser_onClick()"/>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>
      <table class="portlet-frame" cellspacing="1" width="100%">
        <tr>
          <td class="portlet-frame-label" width="200">
             User Name:&nbsp;
          </td>
          <td class="portlet-frame-text">
             <%=userManagerBean.getUserName()%>
          </td>
        </tr>
        <tr>
          <td class="portlet-frame-label">
             Family Name:&nbsp;
          </td>
          <td class="portlet-frame-text">
             <%=userManagerBean.getFamilyName()%>
          </td>
        </tr>
        <tr>
          <td class="portlet-frame-label">
             Given Name:&nbsp;
          </td>
          <td class="portlet-frame-text">
             <%=userManagerBean.getGivenName()%>
          </td>
        </tr>
        <tr>
          <td class="portlet-frame-label">
             Email Address:&nbsp;
          </td>
          <td class="portlet-frame-text">
             <%=userManagerBean.getEmailAddress()%>
          </td>
        </tr>
        <tr>
          <td class="portlet-frame-label">
             Organization:&nbsp;
          </td>
          <td class="portlet-frame-text">
             <%=userManagerBean.getOrganization()%>
          </td>
        </tr>
        <tr>
          <td class="portlet-frame-label">
            Base Role:&nbsp;
          </td>
          <td class="portlet-frame-text">
             <%=userManagerBean.getRoleInBaseGroup()%>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form>
