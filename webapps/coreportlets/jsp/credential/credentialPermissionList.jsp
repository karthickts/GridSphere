<%@ page import="org.gridlab.gridsphere.services.security.credential.CredentialPermission,
                 org.gridlab.gridsphere.portlets.core.beans.CredentialManagerBean,
                 java.util.List" %>
<%@ taglib uri="/portletWidgets" prefix="gs" %>
<%@ taglib uri="/portletAPI" prefix="portletAPI" %>
<portletAPI:init/>
<jsp:useBean id="credentialManagerBean"
             class="org.gridlab.gridsphere.portlets.core.beans.CredentialManagerBean"
             scope="request"/>
<form name="CredentialPermissionPortlet" method="POST"
      action="<%=credentialManagerBean.getPortletActionURI(CredentialManagerBean.ACTION_CREDENTIAL_PERMISSION_LIST)%>">
  <input type="hidden" name="credentialPermissionID" value=""/>
  <script type="text/javascript">
    function CredentialPermissionPortlet_listCredentialPermission_onClick() {
      document.CredentialPermissionPortlet.action="<%=credentialManagerBean.getPortletActionURI(CredentialManagerBean.ACTION_CREDENTIAL_PERMISSION_LIST)%>";
      document.CredentialPermissionPortlet.submit();
    }

    function CredentialPermissionPortlet_newCredentialPermission_onClick(credentialPermissionID) {
      document.CredentialPermissionPortlet.credentialPermissionID.value="";
      document.CredentialPermissionPortlet.action="<%=credentialManagerBean.getPortletActionURI(CredentialManagerBean.ACTION_CREDENTIAL_PERMISSION_EDIT)%>";
      document.CredentialPermissionPortlet.submit();
    }

    function CredentialPermissionPortlet_viewCredentialPermission_onClick(credentialPermissionID) {
      document.CredentialPermissionPortlet.credentialPermissionID.value=credentialPermissionID;
      document.CredentialPermissionPortlet.action="<%=credentialManagerBean.getPortletActionURI(CredentialManagerBean.ACTION_CREDENTIAL_PERMISSION_VIEW)%>";
      document.CredentialPermissionPortlet.submit();
    }
  </script>
<table class="portlet-pane" cellspacing="1">
  <tr>
    <td>
      <table class="portlet-frame" cellspacing="1" width="100%">
        <tr>
          <td class="portlet-frame-title">
              List Credential Permissions
          </td>
        </tr>
        <tr>
          <td class="portlet-frame-actions">
            <input type="button"
                   name="<%=CredentialManagerBean.ACTION_CREDENTIAL_PERMISSION_LIST%>"
                   value="List Permissions"
                   onClick="javascript:CredentialPermissionPortlet_listCredentialPermission_onClick()"/>
            &nbsp;&nbsp;<input type="button"
                   name="<%=CredentialManagerBean.ACTION_CREDENTIAL_PERMISSION_EDIT%>"
                   value="New Permission"
                   onClick="javascript:CredentialPermissionPortlet_newCredentialPermission_onClick()"/>
          </td>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td>
      <table class="portlet-frame" cellspacing="1" width="100%">
<% List credentialPermissionList = credentialManagerBean.getCredentialPermissionList();
   int numCredentialPermissions = credentialPermissionList.size();
   if (numCredentialPermissions == 0) { %>
        <tr>
          <td class="portlet-frame-text-alert">
              No credentials permitted for use.
          </td>
        </tr>
<% } else { %>
       <tr>
         <td class="portlet-frame-header" width="150">
           Permitted Subjects
         </td>
         <td class="portlet-frame-header" width="200">
           Description
         </td>
       </tr>
<%   for (int ii = 0; ii < numCredentialPermissions; ++ii) {
       CredentialPermission credentialPermission = (CredentialPermission)credentialPermissionList.get(ii); %>
        <tr>
          <td class="portlet-frame-text">
            <a href="javascript:CredentialPermissionPortlet_viewCredentialPermission_onClick('<%=credentialPermission.getPermittedSubjects()%>')">
              <%=credentialPermission.getPermittedSubjects()%>
            </a>
          </td>
          <td class="portlet-frame-text">
            <%=credentialPermission.getDescription()%>
          </td>
        </tr>
<%   }
   } %>
      </table>
    </td>
  </tr>
</table>
</form>
