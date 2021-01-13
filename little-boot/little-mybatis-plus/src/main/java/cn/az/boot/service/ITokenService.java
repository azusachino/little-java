package cn.az.boot.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author az
 * @since 11/15/20
 */
public interface ITokenService {

    /**
     * 创建新token
     *
     * @return token
     */
    String createToken();

    /**
     * 检查token是否有效
     *
     * @param req 请求
     * @return 有效性
     */
    boolean checkToken(HttpServletRequest req);
}
