package org.gridsphere.layout.view.brush;

import org.gridsphere.layout.view.BaseRender;
import org.gridsphere.layout.view.TabbedPaneView;
import org.gridsphere.layout.PortletNavMenu;
import org.gridsphere.layout.PortletTab;
import org.gridsphere.layout.PortletComponent;
import org.gridsphere.layout.PortletMenu;
import org.gridsphere.portletcontainer.GridSphereEvent;
import org.gridsphere.portlet.impl.SportletProperties;

import javax.portlet.RenderResponse;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import java.util.StringTokenizer;

public class Menu extends BaseRender implements TabbedPaneView {

    /**
     * Constructs an instance of PortletTabbedPane
     */
    public Menu() {
    }

    /**
     * Replace blank spaces in title with '&nbsp;'
     *
     * @param title the tab title
     * @return a title without blank spaces
     */
    private static String replaceBlanks(String title) {
        String result = "&nbsp;";
        StringTokenizer st = new StringTokenizer(title);
        while (st.hasMoreTokens()) {
            result += st.nextToken() + "&nbsp;";
        }
        return result;
    }

    public StringBuffer doStart(GridSphereEvent event, PortletComponent comp) {
        StringBuffer result = new StringBuffer();
        result.append("<div class=\"gridsphere-menu\">\n");
        result.append("<ul>\n");
        return result;
    }

    public StringBuffer doRenderTab(GridSphereEvent event, PortletNavMenu tabPane, PortletTab tab) {
        // this really creates the individual tabs
        StringBuffer pane = new StringBuffer();
        String link = tab.createTabTitleLink(event);
        String lang = event.getRenderRequest().getLocale().getLanguage();
        String title = tab.getTitle(lang);
        if (tab.isSelected()) {
            pane.append("\n<li class=\"selected\">");
        } else {
            pane.append("\n<li>");
        }
        pane.append("<a href=\"").append(link).append("\">");
        if (title != null) {
            pane.append(replaceBlanks(title));
        }
        pane.append("</a></li>\n");
        return pane;
    }

    public StringBuffer doRenderEditTab(GridSphereEvent event, PortletNavMenu menu, boolean isSelected) {
        RenderResponse res = event.getRenderResponse();
        RenderRequest req = event.getRenderRequest();
     
        PortletURL portletURL = res.createActionURL();
        portletURL.setParameter("newmenutab", "true");
        req.setAttribute(SportletProperties.COMPONENT_ID, String.valueOf(menu.getComponentID()));
        String link = portletURL.toString();
        StringBuffer pane = new StringBuffer();


        pane.append("<li><a href=\"").append(link).append("\">");
        pane.append(replaceBlanks("New Tab"));
        pane.append("</a></li>");
        return pane;
    }


    public StringBuffer doEndBorder(GridSphereEvent event, PortletComponent comp) {
        PortletMenu menu = (PortletMenu)comp;
        StringBuffer buffer = new StringBuffer();

        buffer.append("\n</ul>\n");
        buffer.append("</div><!--  END LAYOUT NAVIGATION -->");
        buffer.append("<div style=\"clear: both;\"></div>");
        buffer.append("<div id=\"gridsphere-layout-body\"> <!-- start the main portlets -->\n");

        return buffer;
    }

    public StringBuffer doEnd(GridSphereEvent event, PortletComponent comp) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n</div> <!-- END gridsphere-layout-body -->\n");
        return buffer;
    }
}