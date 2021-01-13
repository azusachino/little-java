package cn.az.boot.service.impl;

import cn.az.boot.exception.ServiceException;
import cn.az.boot.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author az
 * @since 11/15/20
 */
@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private RedisService redisService;

    /**
     * 创建新token
     *
     * @return token
     */
    @Override
    public String createToken() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisService.set(token, token);
        return token;
    }

    /**
     * 检查token是否有效
     *
     * @param req 请求
     * @return 有效性
     */
    @Override
    public boolean checkToken(HttpServletRequest req) {
        String token = req.getHeader("_token");
        if (StringUtils.isEmpty(token)) {
            token = req.getParameter("_token");
            if (StringUtils.isEmpty(token)) {
                throw new ServiceException("参数错误");
            }
        }

        if (StringUtils.isEmpty(redisService.get(token))) {
            throw new ServiceException("token过期");
        }

        redisService.del(token);

        return true;
    }
}
