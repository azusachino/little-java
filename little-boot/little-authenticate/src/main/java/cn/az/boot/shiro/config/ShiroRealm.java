package cn.az.boot.shiro.config;

import cn.az.boot.shiro.repository.UserRepository;
import cn.az.boot.shiro.common.Constants;
import cn.az.boot.shiro.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 * @date : 2019-07-20 10:22
 **/
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        System.out.println("User: " + username + "Authentication----ShiroRealm.doGetAuthenticationInfo");
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("用户名或密码错误！");
        }
        if (Constants.MAGICZERO.equals(user.getStatus())) {
            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
