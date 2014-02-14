package com.leo.test.webx.util;

/**
 * Created by leo on 1/21/14.
 */
import com.alibaba.common.logging.MappedDiagnosticContext;
import com.alibaba.turbine.pipeline.SetLoggingContextValve;
import com.alibaba.turbine.service.rundata.RunData;

public class UserAuthMdcValve extends SetLoggingContextValve {

    protected void setupContext(MappedDiagnosticContext mdc, RunData rundata) {
        if (UserAuth.isLoggedIn()) {
            mdc.putMDC("loginUser", UserAuth.getLoginUser().getId());
        }
    }
}
