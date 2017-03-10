package cn.xlink.common.http.restful.xlink;

import cn.xlink.common.http.restful.xlink.mode.UserIdentity;

/**
 * Auth 失效处理
 *
 * @author sswukang on 2016/11/8 12:13
 * @version 1.0
 */
public interface AuthProvider {
    String getAccessToken();

    UserIdentity.UserAuthRequest authRequest();

    void authResponse(UserIdentity.UserAuthResponse userAuthResponse);

    void onAuthFailure();
}
