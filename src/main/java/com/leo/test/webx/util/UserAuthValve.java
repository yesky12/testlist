package com.leo.test.webx.util;

/**
 * Created by leo on 1/21/14.
 */
import com.alibaba.service.pipeline.PipelineException;
import com.alibaba.service.pipeline.ValveForward;
import com.alibaba.turbine.pipeline.AbstractValve;
import com.alibaba.turbine.service.rundata.RunData;
import com.leo.test.webx.model.SiteUser;

public class UserAuthValve extends AbstractValve {

    private String action;

    public void setAction(String action) {
        this.action = action;
    }

    public ValveForward invoke(RunData rundata) throws PipelineException {
        if (!"cleanup".equals(action)) {
            SiteUser user = (SiteUser) rundata.getSession().getAttribute("loginUser");
            if (user != null) {
                UserAuth.setLoginUser(user);
            }
        } else {
            UserAuth.setLoginUser(null);
        }
        return null;
    }
}
