/**
 * @author <a href="novotny@aei.mpg.de">Jason Novotny</a>
 * @version $Id$
 */

package org.gridlab.gridsphere.provider.portletui.beans;

import org.gridlab.gridsphere.portlet.PortletRequest;

/**
 * The <code>TextAreaBean</code> represents a text area element
 */
public class TextAreaBean extends BaseComponentBean implements TagBean {

    public static final String NAME = "ta";

    private int cols = 0;
    private int rows = 0;

    /**
     * Constructs a default text area bean
     */
    public TextAreaBean() {
        super(NAME);
        this.cssStyle = TextBean.MSG_INFO;
    }

    /**
     * Constructs a text area bean from a supplied portlet request and bean identifier
     *
     * @param req the portlet request
     * @param beanId the bean identifier
     */
    public TextAreaBean(PortletRequest req, String beanId) {
        super(NAME);
        this.cssStyle = TextBean.MSG_INFO;
        this.request = req;
        this.beanId = beanId;
    }

    /**
     *  Gets the number of columns of the TextArea.
     * @return number of columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Sets the number of columns of the TextArea.
     * @param cols number of cols
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * Return the number of rows of the textarea.
     * @return number of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Sets the number of rows of the textarea.
     * @param rows number of rows
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    public String toStartString() {
        StringBuffer sb = new StringBuffer();
        sb.append("<textarea ");

        String pname = (name == null) ? "" : name;
        String sname = pname;
        if (!beanId.equals("")) {
            sname = "ui_" + vbName + "_" + beanId + "_" + pname;
        }

        sb.append("name=\"" + sname + "\" ");
        if (cols != 0) sb.append(" cols=\"" + cols + "\" ");
        if (rows != 0) sb.append(" rows=\"" + rows + "\" ");
        sb.append(" " + checkDisabled());
        sb.append(" " + checkReadOnly());
        sb.append(">");
        return sb.toString();
    }

    public String toEndString() {
        String result = (value != null) ? value : "";
        return result + " </textarea>";
    }

}
