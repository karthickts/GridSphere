/*
 * @author <a href="mailto:novotny@aei.mpg.de">Jason Novotny</a>
 * @version $Id$
 */
package org.gridlab.gridsphere.layout;

import org.gridlab.gridsphere.portlet.impl.SportletRequest;
import org.gridlab.gridsphere.portletcontainer.GridSphereEvent;

import java.io.IOException;
import java.util.List;

public abstract class BasePortletComponent implements PortletComponent {

    protected int COMPONENT_ID = -1;
    protected String width;
    protected String height;
    protected String name;
    protected PortletBorder border;
    protected PortletInsets insets;
    protected boolean isVisible = true;

    public  List init(List list) {
        COMPONENT_ID = list.size();
        return list;
    }

    public void login(GridSphereEvent event) {}

    public void logout(GridSphereEvent event) {}

    public void destroy() {}

    public int getComponentID() {
        return COMPONENT_ID;
    }

    public PortletBorder getPortletBorder() {
        return border;
    }

    public PortletInsets getPortletInsets() {
        return insets;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getWidth() {
        return width;
    }

    public void setPortletBorder(PortletBorder border) {
        this.border = border;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setPortletInsets(PortletInsets insets) {
        this.insets = insets;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void actionPerformed(GridSphereEvent event) throws PortletLayoutException, IOException {

    }

    public void doRender(GridSphereEvent event) throws PortletLayoutException, IOException {
        SportletRequest req = event.getSportletRequest();
        String sid = new Integer(COMPONENT_ID).toString();
        req.setAttribute(LayoutProperties.ID, sid);
        req.setAttribute(LayoutProperties.NAME, name);
        req.setAttribute(LayoutProperties.HEIGHT, height);
        req.setAttribute(LayoutProperties.WIDTH, width);
    }

}
