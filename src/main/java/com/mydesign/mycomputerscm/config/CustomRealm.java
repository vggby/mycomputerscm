package com.mydesign.mycomputerscm.config;
import com.mydesign.mycomputerscm.Service.UserService;
import com.mydesign.mycomputerscm.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            String username = (String) SecurityUtils.getSubject().getPrincipal();
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> stringSet = new HashSet<>();
            stringSet.add("user:show");
            stringSet.add("user:admin");
            info.setStringPermissions(stringSet);
            return info;
        }


        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            System.out.println("-------身份认证方法--------");
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            SysUser sysUser = userService.findbyUsername(token.getUsername());

            //根据用户名从数据库获取密码

            if (sysUser == null) {
                return null;

            }
            return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(),
                    ByteSource.Util.bytes(sysUser.getUsername() + "salt"), getName());
        }
}
