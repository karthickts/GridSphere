/*
 * @author <a href="mailto:novotny@aei.mpg.de">Jason Novotny</a>
 * @version $Id$
 */
package org.gridlab.gridsphere.layout;

import org.gridlab.gridsphere.portlet.impl.SportletResponse;
import org.gridlab.gridsphere.portletcontainer.GridSphereEvent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PortletContainer implements PortletLifecycle {

    protected int COMPONENT_ID = 0;

    // The actual portlet layout components
    protected List components = new ArrayList();

    // The component ID's of each of the layout components
    protected List ComponentIdentifiers = new ArrayList();

    // The list of portlets a user has-- generally contained within a PortletFrame/PortletTitleBar combo
    protected List portlets = new ArrayList();

    protected LayoutManager layoutManager;
    protected String name = "";

    public PortletContainer() {}

    public void setContainerName(String name) {
        this.name = name;
    }

    public String getContainerName() {
        return name;
    }

    public List init(List list) {
        Iterator it = components.iterator();
        PortletLifecycle cycle;
        ComponentIdentifier compId;
        while (it.hasNext()) {
            compId = new ComponentIdentifier();
            cycle = (PortletLifecycle)it.next();
            compId.setPortletLifecycle(cycle);
            compId.setClassName(cycle.getClass().getName());
            compId.setComponentID(list.size());
            list.add(compId);
            ComponentIdentifiers = cycle.init(list);
        }
        System.err.println("Made a components list!!!! " + ComponentIdentifiers.size());
        for (int i = 0; i < ComponentIdentifiers.size(); i++) {
            ComponentIdentifier c = (ComponentIdentifier)ComponentIdentifiers.get(i);
            System.err.println("id: " + c.getComponentID() + " : " + c.getClassName() +  " : " + c.hasPortlet());
        }
        return ComponentIdentifiers;
    }

    public void login(GridSphereEvent event) {
        Iterator it = components.iterator();
        PortletLifecycle cycle;
        while (it.hasNext()) {
            cycle = (PortletLifecycle)it.next();
            cycle.login(event);
        }
    }

    public void logout(GridSphereEvent event) {
        Iterator it = components.iterator();
        PortletLifecycle cycle;
        while (it.hasNext()) {
            cycle = (PortletLifecycle)it.next();
            cycle.logout(event);
        }
    }

    public void destroy() {
        Iterator it = components.iterator();
        while (it.hasNext()) {
            PortletComponent comp = (PortletComponent)it.next();
            comp.destroy();
        }
    }

    public void actionPerformed(GridSphereEvent event) throws PortletLayoutException, IOException {

        // if there is a layout action do it!
        if (event.hasAction()) {
            // off by one calculations for array indexing (because all component id's are .size() which is
            // one more than we use to index the components
            ComponentIdentifier compId = (ComponentIdentifier)ComponentIdentifiers.get(event.getPortletComponentID() - 1);
            PortletLifecycle l = compId.getPortletLifecycle();
            if (l != null) {
                l.actionPerformed(event);
            }
        }
    }

    public void doRender(GridSphereEvent event) throws PortletLayoutException, IOException {
        SportletResponse res = event.getSportletResponse();
        PrintWriter out = res.getWriter();

        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">");
        out.println("<head>");
        out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\"/>");
        out.println("<title>" + name + "</title>");
        out.println("<link type=\"text/css\" href=\"css/default.css\" rel=\"STYLESHEET\"/>");
        out.println("</head><body>");

        // for css title
        out.println("<div id=\"page-logo\">" + name + "</div>");
        out.println("<div id=\"page-tagline\">Solving the World's Problems!</div>");

        /////////////////////////////////////  OLD STUFF /////
        //out.println("<html><head><meta HTTP-EQUIV=\"content-type\" CONTENT=\"text/html; charset=ISO-8859-1\">");
        //out.println("<title>" + name + "</title>");
        Iterator it = components.iterator();
        while (it.hasNext()) {
            PortletRender action = (PortletRender)it.next();
            action.doRender(event);
        }
        out.println("</body></html>");
    }

    public void setPortletComponents(ArrayList components) {
        this.components = components;
    }

    public List getPortletComponents() {
        return components;
    }

    public List getComponentIdentifierList() {
        return ComponentIdentifiers;
    }

    public int getComponentID() {
        return COMPONENT_ID;
    }
}
