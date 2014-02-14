package com.leo.test.webx.model.action;

/**
 * Created by leo on 1/21/14.
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.alibaba.common.lang.ArrayUtil;
import com.alibaba.intl.biz.rfq.commons.utils.URLParamsBuildUtils;
import com.alibaba.service.form.Form;
import com.alibaba.service.form.FormService;
import com.alibaba.service.form.Group;
import com.alibaba.service.template.TemplateContext;
import com.alibaba.service.uribroker.URIBrokerService;
import com.alibaba.service.uribroker.uri.URIBroker;
import com.alibaba.turbine.module.action.TemplateAction;
import com.alibaba.turbine.service.rundata.RunData;
import com.alibaba.webx.WebxException;
import com.leo.test.webx.model.SiteUser;

public abstract class SiteUserAction extends TemplateAction {

    protected abstract URIBrokerService getURIBrokerService();

    protected abstract FormService getFormService();

    public void doRegister(RunData rundata, TemplateContext context) throws WebxException {
        FormService forms = (FormService) getWebxComponent().getService(FormService.SERVICE_NAME);
        Form form = forms.getForm(rundata);
        if (form.isValid()) {
            Group group = form.getGroup("register");
            SiteUser user = new SiteUser();
            group.setProperties(user);
            // FIXME! 保存到数据库中。
            System.out.println("Registered user: " + user);
            // context.put("newUser", user);
            // rundata.setRedirectTarget("viewUser.vm");
            // String viewNewUser = "http://localhost:8080/view_user.htm?id=" + user.getId() + "&new_user=true";
            // try {
            // rundata.setRedirectLocation(viewNewUser);
            // } catch (IOException e) {
            // throw new WebxException("Failed to redirect to " + viewNewUser, e);
            // }
            URIBroker broker = getURIBrokerService().getURIBroker("viewNewUser", rundata);
            broker.addQueryData("id", user.getId());
            String viewNewUser = broker.render();
            System.out.println(viewNewUser);
            try {
                rundata.setRedirectLocation(viewNewUser);
            } catch (IOException e) {
                throw new WebxException("Failed to redirect to " + viewNewUser, e);
            }
        }
    }

    public void doLogin(RunData rundata, TemplateContext context) throws WebxException {
        Form form = getFormService().getForm(rundata);
        if (form.isValid()) {
            Group group = form.getGroup("login");
            String userId = group.getField("id").getStringValue();
            String password = group.getField("password").getStringValue();
            // FIXME! 验证用户名密码
            if (userId.equals("baobao") && password.equals("hello")) {
                SiteUser user = new SiteUser();
                user.setId(userId);
                user.setName("Leo Gong");
                user.setEmail("gonglin.pt@alibaba-inc.com");
                user.setDescription("Hello!");
                rundata.getSession().setAttribute("loginUser", user);
                String helloURL = getURIBrokerService().getURIBroker("hello", rundata).render();
                try {
                    rundata.setRedirectLocation(helloURL);
                } catch (IOException e) {
                    throw new WebxException("Failed to redirect to " + helloURL, e);
                }
            } else {
                Map params = ArrayUtil.toMap(new String[][] { { "id", userId } });
                group.getField("error").setMessage("wrongIdOrPassword", params);
            }
        }
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println("ddddd");
        System.out.println(URLParamsBuildUtils.encodeUrlParams(new String[]{"2458667933"}));
    }
}
