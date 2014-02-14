package com.leo.test.webx.model.control;

import com.alibaba.service.template.TemplateContext;
import com.alibaba.turbine.module.control.TemplateControl;
import com.alibaba.turbine.service.rundata.RunData;
import com.alibaba.webx.WebxException;

/**
 * Created by leo on 1/26/14.
 */
public class Menu extends TemplateControl {
    @Override
    protected void execute(RunData rundata, TemplateContext context) throws WebxException {
        context.put("data","OK");
    }
}
