package com.leo.test.webx.model.screen;

/**
 * Created by leo on 1/21/14.
 *
 */
import com.alibaba.service.template.TemplateContext;
import com.alibaba.turbine.module.screen.TemplateScreen;
import com.alibaba.turbine.service.rundata.RunData;
import com.alibaba.webx.WebxException;
import com.leo.test.webx.model.SiteUser;

public class ViewUser extends TemplateScreen {

    protected void execute(RunData rundata, TemplateContext context) throws WebxException {
        SiteUser user = null;
        if (context.containsKey("newUser")) {
            user = (SiteUser) context.get("newUser");
            context.put("newUser", Boolean.TRUE);
        }
        if (user == null) {
            String userId = rundata.getParameters().getString("id");
            boolean newUser = rundata.getParameters().getBoolean("newUser", false);
            // FIXME! 从数据库中读userId。
            user = new SiteUser();
            user.setId(userId);
            user.setPassword("helloworld");
            user.setName("Leo Gong");
            user.setEmail("gonglin.pt@alibaba-inc.com");
            user.setDescription("Hello!");
            context.put("newUser", newUser);
        }
        context.put("user", user);
    }
}
