package com.leo.test.webx.model.screen;

/**
 * Created by leo on 1/21/14.
 */

import java.io.IOException;
import java.io.PrintWriter;

import com.alibaba.turbine.module.AbstractModule;
import com.alibaba.turbine.service.rundata.RunData;
import com.alibaba.webx.WebxException;

public class SimpleGreeting extends AbstractModule {

    public void execute(RunData rundata) throws WebxException {
        rundata.getResponse().setContentType("text/html");
        PrintWriter out;
        try {
            out = rundata.getResponse().getWriter();
        } catch (IOException e) {
            throw new WebxException("Failed to get writer", e);
        }
        out.println("<html>");
        out.println("<head>");
        out.println(" <title>Hello!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println(" <p>你好，世界！</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
