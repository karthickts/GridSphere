/*
 * @author <a href="mailto:novotny@aei.mpg.de">Jason Novotny</a>
 * @author <a href="mailto:oliver.wehrens@aei.mpg.de">Oliver Wehrens</a>
 * @version $Id$
 */
package org.gridlab.gridsphere.tags.portletui;

import org.gridlab.gridsphere.provider.portletui.beans.ListBoxBean;
import org.gridlab.gridsphere.provider.portletui.beans.ListBoxItemBean;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

public class ListBoxItemTag extends BaseComponentTag {

    protected ListBoxItemBean listboxitem = null;

    public int doEndTag() throws JspException {

        if (!beanId.equals("")) {
            listboxitem = (ListBoxItemBean)pageContext.getAttribute(getBeanKey(), PageContext.REQUEST_SCOPE);
            if (listboxitem == null) {
                System.err.println("did not find checkbox bean!! " + getBeanKey());
                listboxitem = new ListBoxItemBean();

                this.setBaseComponentBean(listboxitem);
            } else {
                System.err.println("found checkbox bean!! " + getBeanKey());
                System.err.println("name = " + listboxitem.getName());
                this.updateBaseComponentBean(listboxitem);
            }
        } else {
            listboxitem = new ListBoxItemBean();
            this.setBaseComponentBean(listboxitem);
        }

        //debug();

        Object parentTag = getParent();
        if (parentTag instanceof ContainerTag) {
            ContainerTag containerTag = (ContainerTag)parentTag;
            containerTag.addTagBean(listboxitem);
        } else {
            try {
                JspWriter out = pageContext.getOut();
                out.print(listboxitem.toString());
            } catch (Exception e) {
                throw new JspException(e.getMessage());
            }
        }
        return EVAL_BODY_INCLUDE;
    }

}
