package cn.az.boot.auth;

import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.cache.AuthStateCache;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthSource;
import me.zhyd.oauth.enums.AuthUserGender;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthDefaultRequest;
import me.zhyd.oauth.utils.UrlBuilder;

/**
 * @author Liz
 */
public class AuthGitlabRequest extends AuthDefaultRequest {

    public AuthGitlabRequest(AuthConfig config) {
        super(config, AuthCustomSource.GITLAB);
    }

    public AuthGitlabRequest(AuthConfig config, AuthSource source) {
        super(config, source);
    }

    public AuthGitlabRequest(AuthConfig config, AuthSource source, AuthStateCache authStateCache) {
        super(config, source, authStateCache);
    }

    /**
     * 获取access token
     *
     * @param authCallback 授权成功后的回调参数
     * @return token
     * @see AuthDefaultRequest#authorize(String)
     */
    @Override
    protected AuthToken getAccessToken(AuthCallback authCallback) {
        HttpResponse response = doGetAuthorizationCode(authCallback.getCode());
        JSONObject obj = JSONObject.parseObject(response.body());
        checkResponse(obj);
        return AuthToken.builder()
            .accessToken(obj.getString("access_token"))
            .refreshToken(obj.getString("refresh_token"))
            .idToken(obj.getString("id_token"))
            .tokenType(obj.getString("token_type"))
            .scope(obj.getString("scope"))
            .build();
    }

    /**
     * 使用token换取用户信息
     *
     * @param authToken token信息
     * @return 用户信息
     * @see AuthDefaultRequest#getAccessToken(AuthCallback)
     */
    @Override
    protected AuthUser getUserInfo(AuthToken authToken) {
        HttpResponse response = doGetUserInfo(authToken);
        JSONObject object = JSONObject.parseObject(response.body());

        this.checkResponse(object);

        return AuthUser.builder()
            .uuid(object.getString("id"))
            .username(object.getString("username"))
            .nickname(object.getString("name"))
            .avatar(object.getString("avatar_url"))
            .blog(object.getString("web_url"))
            .company(object.getString("organization"))
            .location(object.getString("location"))
            .email(object.getString("email"))
            .remark(object.getString("bio"))
            .gender(AuthUserGender.UNKNOWN)
            .token(authToken)
            .source(source.toString())
            .build();
    }

    private void checkResponse(JSONObject object) {
        // oauth/token 验证异常
        if (object.containsKey("error")) {
            throw new AuthException(object.getString("error_description"));
        }
        // user 验证异常
        if (object.containsKey("message")) {
            throw new AuthException(object.getString("message"));
        }
    }

    /**
     * 返回带{@code state}参数的授权url，授权回调时会带上这个{@code state}
     *
     * @param state state 验证授权流程的参数，可以防止csrf
     * @return 返回授权地址
     * @since 1.11.0
     */
    @Override
    public String authorize(String state) {
        return UrlBuilder.fromBaseUrl(super.authorize(state))
            .queryParam("scope", "read_user+openid")
            .build();
    }
}
