/*
 * @author <a href="mailto:novotny@aei.mpg.de">Jason Novotny</a>
 * @version $Id$
 */
package org.gridlab.gridsphere.portlets.core.login;

import org.gridlab.gridsphere.portlet.*;
import org.gridlab.gridsphere.provider.portletui.beans.*;
import org.gridlab.gridsphere.provider.portletui.model.DefaultTableModel;
import org.gridlab.gridsphere.provider.ActionPortlet;
import org.gridlab.gridsphere.provider.event.FormEvent;

import org.gridlab.gridsphere.services.core.user.LoginService;
import org.gridlab.gridsphere.services.core.security.auth.LoginAuthModule;

import javax.servlet.UnavailableException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class LoginPortlet extends ActionPortlet {

    public static final String LOGIN_ERROR_FLAG = "LOGIN_FAILED";
    public static final Integer LOGIN_ERROR_UNKNOWN = new Integer(-1);

    public void init(PortletConfig config) throws UnavailableException {
        super.init(config);
        DEFAULT_VIEW_PAGE = "doViewUser";
        DEFAULT_CONFIGURE_PAGE = "doConfigModules";
    }

    public void initConcrete(PortletSettings settings) throws UnavailableException {
        super.initConcrete(settings);
    }

    public void doViewUser(FormEvent event) throws PortletException {
        log.debug("in LoginPortlet: doViewUser");
        PortletRequest request = event.getPortletRequest();
        User user = request.getUser();
        request.setAttribute("user", user);
        setNextPresentation(request, "login/login.jsp");
    }

    public void doConfigModules(FormEvent event) throws PortletException {
        System.err.println("in LoginPortlet: doConfigure");
        PortletRequest request = event.getPortletRequest();
        LoginService loginService = (LoginService)getPortletConfig().getContext().getService(LoginService.class, request.getUser());

        List supportedModules = loginService.getSupportedAuthModules();
        List activeModules = loginService.getActiveAuthModules();

        request.setAttribute("activeModules", activeModules);
        request.setAttribute("supportedModules", supportedModules);
        setNextPresentation(request, "login/configure.jsp");
    }

    public void doTitle(PortletRequest request, PortletResponse response) throws PortletException, IOException {
        User user = request.getUser();
        PrintWriter out = response.getWriter();

        if (user instanceof GuestUser) {

            out.println(getPortletSettings().getTitle(request.getLocale(), null));
        } else {
            getPortletConfig().getContext().include("/jsp/login/login_title.jsp", request, response);
        }
        /*
         ResourceBundle resBundle = ResourceBundle.getBundle("Portlet", locale);
         String welcome = resBundle.getString("LOGIN_SUCCESS");
         out.println(welcome + ", " + user.getFullName());
         */
    }

    public void gs_login(FormEvent event) throws PortletException {
        log.debug("in LoginPortlet: gs_login");
        PortletRequest req = event.getPortletRequest();

        ErrorFrameBean frame = event.getErrorFrameBean("errorFrame");
        String errorKey = (String)req.getAttribute(LoginPortlet.LOGIN_ERROR_FLAG);
        if ((errorKey != null) && (frame != null)) {
            System.err.println("1. setting error key in frame");
            frame.setKey(errorKey);
        }
        setNextPresentation(req, "doViewUser");
    }

    public void configAuthModules(FormEvent event) throws PortletException {
        PortletRequest req = event.getPortletRequest();

        LoginService loginService = (LoginService)getConfig().getContext().getService(LoginService.class, req.getUser());

        CheckBoxBean passCheck = event.getCheckBoxBean("passCheck");
        CheckBoxBean ldapCheck = event.getCheckBoxBean("ldapCheck");
        CheckBoxBean myproxyCheck = event.getCheckBoxBean("myproxyCheck");

        List authModules = new ArrayList();
        Iterator it = loginService.getSupportedAuthModules().iterator();
        while (it.hasNext()) {
            LoginAuthModule authModule = (LoginAuthModule)it.next();
            String modName = authModule.getModuleName();
            if (modName.equals("PASSWORD_AUTH_MODULE")) {
                if (passCheck.isSelected()) {
                    log.debug("adding PASSWORD_AUTH_MODULE");
                    authModules.add(authModule);
                }
            }
            if (modName.equals("LDAP_AUTH_MODULE")) {
                if (ldapCheck.isSelected()) {
                    log.debug("adding LDAP_AUTH_MODULE");
                    authModules.add(authModule);
                }
            }
            if (modName.equals("MYPROXY_AUTH_MODULE")) {
                if (myproxyCheck.isSelected()) {
                    log.debug("adding MYPROXY_AUTH_MODULE");
                    authModules.add(authModule);
                }
            }
        }

        if (!authModules.isEmpty()) {
            loginService.setActiveAuthModules(authModules);
        }

        //setNextPresentation(CONFIGURE_PAGE);
    }

    public void configLdapModule(FormEvent event) {
        //setNextPresentation();
    }
}
