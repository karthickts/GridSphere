/*
 * @author <a href="oliver.wehrens@aei.mpg.de">Oliver Wehrens</a>
 * @version $Id$
 */

package org.gridlab.gridsphere.tags.portletui;

import org.gridlab.gridsphere.provider.portletui.beans.BaseComponentBean;

public abstract class BaseComponentTag extends BaseBeanTag   {

    protected String name = null;
    protected String value = null;
    protected boolean readonly = false;
    protected boolean disabled = false;
    protected String color = null;
    protected String backgroundcolor = null;
    protected String cssStyle = null;
    protected String font = null;

    /**
     * Sets the name of the bean.
     * @param name the name of the bean
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the bean.
     * @return name of the bean
     */
    public String getName() {
        return this.name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Returns the value of the tag
     * @return label of the beans
     */
    public String getValue() {
        return value;
    }

    /**
     * Returns true if bean is in disabled state.
     * @return state
     */
    public boolean isDisabled() {
        return disabled;
    }

    /**
     * Sets the disabled attribute of the bean to be 'flag' state.
     * @param flag status
     */
    public void setDisabled(boolean flag) {
        this.disabled = flag;
    }

    /**
     * Returns disabled String if bean is disabled
     * @return String depending if bean is disabled
     */
    protected String checkDisabled() {
        if (disabled) {
            return " disabled='disabled' ";
        } else {
            return "";
        }
    }

    /**
     * Sets the bean to readonly.
     * @param flag status of the bean
     */
    public void setReadonly(boolean flag) {
        this.readonly = flag;
    }

    /**
     * Returns the readonly status of the bean
     * @return readonly status
     */
    public boolean isReadonly() {
        return readonly;
    }

    protected String checkReadonly() {
        if (readonly) {
            return " disabled='disabled' ";
        } else {
            return "";
        }
    }

    /**
     * Sets the color of the beans.
     * @param color set the color of the beans
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Gets the color of the beans.
     * @return color of the beans
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets the font of the beans.
     * @return font of the beans
     */
    public String getFont() {
        return font;
    }

    /**
     * Sets the font of the beans.
     * @param font the font to set
     */
    public void setFont(String font) {
        this.font = font;
    }

    /**
     * Returns the backgroundcolor of the beans.
     * @return the backgroundcolor
     */
    public String getBackgroundColor() {
        return backgroundcolor;
    }

    /**
     * Sets the backgoundcolor of the beans.
     * @param backgroundcolor the backgroundcolor to be set
     */
    public void setBackgroundColor(String backgroundcolor) {
        this.backgroundcolor = backgroundcolor;
    }

    /**
     * Returns the CSS style name of the beans.
     * @return the name of the css style
     */
    public String getCssStyle() {
        return cssStyle;
    }

    /**
     * Sets the CSS style of the beans.
     * @param style css style name to set for the beans
     */
    public void setCssStyle(String style) {
        this.cssStyle = style;
    }

    protected void update(BaseComponentBean baseBean) {
        String name = baseBean.getName();
        if (!name.equals("")) {
            String value = pageContext.getRequest().getParameter(name);
            if (value != null) {
                baseBean.setValue(value);
            }
        }
    }

    protected void setBaseComponentBean(BaseComponentBean componentBean) {
        if (backgroundcolor != null) componentBean.setBackgroundColor(backgroundcolor);
        if (color != null) componentBean.setColor(color);
        if (cssStyle != null) componentBean.setCssStyle(cssStyle);
        componentBean.setDisabled(disabled);
        if (font != null) componentBean.setFont(font);
        if (name != null) componentBean.setName(name);
        if (value != null) componentBean.setValue(value);
        componentBean.setReadonly(readonly);
    }

    protected void updateBaseComponentBean(BaseComponentBean componentBean) {
        if ((backgroundcolor != null) && (componentBean.getBackgroundColor() == null)) {
            componentBean.setBackgroundColor(backgroundcolor);
        }
        if ((color != null) && (!componentBean.getColor().equals(""))) {
            if (color != null) componentBean.setColor(color);
        }
        if ((cssStyle != null) && (!componentBean.getCssStyle().equals(""))) {
            componentBean.setCssStyle(cssStyle);
        }
        if ((font != null) && (!componentBean.getFont().equals(""))) {
            componentBean.setFont(font);
        }
        if ((name != null) && (componentBean.getName() == null)) {
            componentBean.setName(name);
        }
        if ((value != null) && (componentBean.getValue() == null)) {
            componentBean.setValue(value);
        }

    }


}
