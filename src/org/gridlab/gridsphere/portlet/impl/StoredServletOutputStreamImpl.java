package org.gridlab.gridsphere.portlet.impl;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.Writer;

public class StoredServletOutputStreamImpl extends ServletOutputStream {

    protected Writer writer;

    public StoredServletOutputStreamImpl(Writer writer) {
        this.writer = writer;
    }

    public void write(int b) throws IOException {
        writer.write(b);
    }

    public String toString() {
        return writer.toString();    
    }

}

