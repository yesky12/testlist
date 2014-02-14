package com.leo.test.webx.util;

import com.leo.test.webx.model.SiteUser;

/**
 * Created by leo on 1/21/14.
 */

public class UserAuth {

    private static final ThreadLocal<SiteUser> userHolder = new ThreadLocal<SiteUser>();

    public static void setLoginUser(SiteUser user) {
        userHolder.set(user);
    }

    public static SiteUser getLoginUser() {
        return userHolder.get();
    }

    public static boolean isLoggedIn() {
        return getLoginUser() != null;
    }
}
