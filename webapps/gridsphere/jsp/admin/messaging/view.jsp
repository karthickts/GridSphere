<%@ taglib uri="/portletUI" prefix="ui" %>
<%@ taglib uri="/portletAPI" prefix="portletAPI" %>

<portletAPI:init/>
<jsp:useBean id="services" class="java.lang.Integer" scope="request"/>


<%    
    if (services.intValue()>0) {
%>

    <ui:text key="MESSAGING_SERVICE_CONFIGTEXT"/>

    <ui:form>
    <ui:frame beanId="serviceframe"/>

    <ui:actionsubmit action="doSaveValues" key="MESSAGING_SERVICE_SAVE"/>
    <ui:actionsubmit action="restartServices" key="MESSAGING_SERVICE_RESTART"/>

</ui:form>

<%
    } else {
%>

<ui:text key="MESSAGING_NO_SERVICE_CONFIGURED"/>

<%
    }
%>