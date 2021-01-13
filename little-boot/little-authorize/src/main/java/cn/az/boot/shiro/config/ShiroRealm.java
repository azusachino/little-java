package cn.az.boot.shiro.config;

import cn.az.boot.shiro.common.Constants;
import cn.az.boot.shiro.service.UserService;
import cn.az.boot.shiro.model.Permission;
import cn.az.boot.shiro.model.Role;
import cn.az.boot.shiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h3>MySpringBoot</h3>
 *
 * @author : azchino
 * @date : 2019-07-20 17:43
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        String username = user.getUsername();

        System.out.println("用户" + username + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<Role> roles = user.getRoles();
        Set<String> roleSet = new HashSet<>();
        Set<String> permissionSet = new HashSet<>();
        for (Role role : roles) {
            roleSet.add(role.getName());
            for (Permission permission : role.getPermissions()) {
                permissionSet.add(permission.getName());
            }
        }
        info.setRoles(roleSet);
        info.setStringPermissions(permissionSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        System.out.println("用户" + username + "认证-----ShiroRealm.doGetAuthenticationInfo");
        User user = userService.findByUsername(username);

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

