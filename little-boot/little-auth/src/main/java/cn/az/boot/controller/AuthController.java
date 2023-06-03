package cn.az.boot.controller;

import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.exception.AuthException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.request.*;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Liz
 */
@RestController
@RequestMapping("/auth")
public class AuthController {


    @RequestMapping("/render/{source}")
    public void renderAuth(@PathVariable("source") String source, HttpServletResponse response) throws IOException {
        System.out.println("进入render：" + source);
        AuthRequest authRequest = getAuthRequest(source);
        String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
        System.out.println(authorizeUrl);
        response.sendRedirect(authorizeUrl);
    }

    /**
     * auth平台中配置的授权回调地址，以本项目为例，在创建github授权应用时的回调地址应为：http://127.0.0.1:8443/auth/callback/github
     */
    @RequestMapping("/callback/{source}")
    public Object login(@PathVariable("source") String source, AuthCallback callback) {
        System.out.println("进入callback：" + source + " callback params：" + JSONObject.toJSONString(callback));
        AuthRequest authRequest = getAuthRequest(source);
        AuthResponse<?> response = authRequest.login(callback);
        System.out.println(JSONObject.toJSONString(response));
        return response;
    }

    @RequestMapping("/revoke/{source}/{token}")
    public Object revokeAuth(@PathVariable("source") String source, @PathVariable("token") String token) {
        AuthRequest authRequest = getAuthRequest(source);
        return authRequest.revoke(AuthToken.builder().accessToken(token).build());
    }

    @RequestMapping("/refresh/{source}")
    public Object refreshAuth(@PathVariable("source") String source, String token) {
        AuthRequest authRequest = getAuthRequest(source);
        return authRequest.refresh(AuthToken.builder().refreshToken(token).build());
    }

    /**
     * 根据具体的授权来源，获取授权请求工具类
     *
     * @param source source
     * @return AuthRequest
     */
    private AuthRequest getAuthRequest(String source) {
        AuthRequest authRequest = null;
        switch (source) {
            case "dingtalk":
                authRequest = new AuthDingTalkRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/dingtalk")
                    .build());
                break;
            case "baidu":
                authRequest = new AuthBaiduRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/baidu")
                    .build());
                break;
            case "github":
                authRequest = new AuthGithubRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/github")
                    .build());
                break;
            case "gitee":
                authRequest = new AuthGiteeRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/gitee")
                    .build());
                break;
            case "weibo":
                authRequest = new AuthWeiboRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/weibo")
                    .build());
                break;
            case "coding":
                authRequest = new AuthCodingRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/tencentCloud")
                    .build());
                break;
            case "tencentCloud":
                authRequest = new AuthTencentCloudRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/tencentCloud")
                    .build());
                break;
            case "oschina":
                authRequest = new AuthOschinaRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/oschina")
                    .build());
                break;
            case "alipay":
                // 支付宝在创建回调地址时，不允许使用localhost或者127.0.0.1，所以这儿的回调地址使用的局域网内的ip
                authRequest = new AuthAlipayRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .alipayPublicKey("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/alipay")
                    .build());
                break;
            case "qq":
                authRequest = new AuthQqRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/qq")
                    .build());
                break;
            case "wechat":
                authRequest = new AuthWeChatRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/wechat")
                    .build());
                break;
            case "csdn":
                authRequest = new AuthCsdnRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/csdn")
                    .build());
                break;
            case "taobao":
                authRequest = new AuthTaobaoRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/taobao")
                    .build());
                break;
            case "google":
                authRequest = new AuthGoogleRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/google")
                    .build());
                break;
            case "facebook":
                authRequest = new AuthFacebookRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/facebook")
                    .build());
                break;
            case "douyin":
                authRequest = new AuthDouyinRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/douyin")
                    .build());
                break;
            case "linkedin":
                authRequest = new AuthLinkedinRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/linkedin")
                    .build());
                break;
            case "microsoft":
                authRequest = new AuthMicrosoftRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/microsoft")
                    .build());
                break;
            case "mi":
                authRequest = new AuthMiRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/mi")
                    .build());
                break;
            case "toutiao":
                authRequest = new AuthToutiaoRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/toutiao")
                    .build());
                break;
            case "teambition":
                authRequest = new AuthTeambitionRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/teambition")
                    .build());
                break;
            case "pinterest":
                authRequest = new AuthPinterestRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/pinterest")
                    .build());
                break;
            case "renren":
                authRequest = new AuthRenrenRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/teambition")
                    .build());
                break;
            case "stackoverflow":
                authRequest = new AuthStackOverflowRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/login_success")
                    .stackOverflowKey("")
                    .build());
                break;
            case "huawei":
                authRequest = new AuthHuaweiRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/huawei")
                    .build());
                break;
            case "wechatEnterprise":
                authRequest = new AuthHuaweiRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/wechatEnterprise")
                    .agentId("")
                    .build());
                break;
            case "kujiale":
                authRequest = new AuthKujialeRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/kujiale")
                    .build());
                break;
            case "gitlab":
                authRequest = new AuthGitlabRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://localhost:8443/auth/callback/gitlab")
                    .build());
                break;
            case "meituan":
                authRequest = new AuthMeituanRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://localhost:8443/auth/callback/meituan")
                    .build());
                break;
            case "eleme":
                authRequest = new AuthElemeRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://dblog-web.zhyd.me/auth/callback/eleme")
                    .build());
                break;
            case "mygitlab":
                authRequest = new AuthGitlabRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/mygitlab")
                    .build());
                break;
            case "twitter":
                authRequest = new AuthTwitterRequest(AuthConfig.builder()
                    .clientId("")
                    .clientSecret("")
                    .redirectUri("http://127.0.0.1:8443/auth/callback/twitter")
                    .build());
                break;
            default:
                break;
        }
        if (null == authRequest) {
            throw new AuthException("未获取到有效的Auth配置");
        }
        return authRequest;
    }
}
