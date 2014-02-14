package com.leo.test.webx.model.screen;

/**
 * Created by leo on 1/21/14.
 */
import com.alibaba.service.form.Form;
import com.alibaba.service.form.FormService;
import com.alibaba.service.form.Group;
import com.alibaba.turbine.module.AbstractModule;
import com.alibaba.turbine.service.rundata.RunData;
import com.alibaba.webx.WebxException;
import com.alibaba.webx.request.context.parser.ParameterParser;

public abstract class RegisterGateway extends AbstractModule {

    protected abstract FormService getFormService();

    public void execute(RunData rundata) throws WebxException {
        Form form = getFormService().getForm(rundata);
        validate(form, rundata.getParameters());
        rundata.getParameters().setString("action", "siteUserAction");
        rundata.getParameters().setString("eventSubmitDoRegister", "true");
        rundata.setRedirectTarget("register.vm");
    }

    private void validate(Form form, ParameterParser params) {
        Group group = form.getGroup("register");
        group.getField("id").setValue(params.getString("id"));
        group.getField("password").setValue(params.getString("password"));
        group.getField("passwordConfirm").setValue(params.getString("passwordConfirm"));
        group.getField("name").setValue(params.getString("name"));
        group.getField("email").setValue(params.getString("email"));
        group.getField("description").setValue(params.getString("description"));
        form.validate();
    }
}
