package com.dls.function.support.shiro;

import com.dls.arc.utils.ConstantFields;
import com.dls.function.support.shiro.service.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LgbManageSystemRealm extends AuthorizingRealm {
    @Autowired
    private ShiroService shiroService;

    /**
     *  系统用户的权限验证
     *
     * @return 返回权限验证需要的相关信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取当前登录的用户名
        String currentUsername = (String) principals.fromRealm(this.getName()).iterator().next();
        // 和数据库匹配
        User user = shiroService.queryByName(currentUsername);

        if (null != user) {
            // 权限信息对象,用于存储用户的所有角色和权限
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

            // 设置用户角色
            List<Role> roles = shiroService.queryRoles(user.getUserId());
            Set<String> roleNames = new HashSet<>();

            for (Role role : roles) {
                roleNames.add(role.getRoleName());

                // 获得角色权限
                List<Permission> permissions = shiroService.queryRolePermissions(role.getRoleId());

                for (Permission permission : permissions) {
                    info.addStringPermission(permission.getPermissionName());
                }
            }

            info.setRoles(roleNames);

            return info;
        }

        return null;
    }

    /**
     * 用户登录信息的验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        User user = shiroService.queryByName(usernamePasswordToken.getUsername());

        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(ConstantFields.LOGIN_KEY, user);

        if (null != user) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        }

        return null;
    }
}
