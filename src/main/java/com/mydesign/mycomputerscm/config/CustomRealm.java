package com.mydesign.mycomputerscm.config;

import com.mydesign.mycomputerscm.Service.MenuService;
import com.mydesign.mycomputerscm.Service.UserService;
import com.mydesign.mycomputerscm.domain.Menu;
import com.mydesign.mycomputerscm.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    @Lazy  //只有使用的时候才会加载
    private MenuService menuService;
    @Autowired
    private UserService userService;
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            SysUser principal = (SysUser) SecurityUtils.getSubject().getPrincipal();
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            Set<String> stringSet = new HashSet<>();
            List<Menu> menus = menuService.selectpermissionByUserId(principal);
            List<String> Permissions = new ArrayList<>();
            for (Menu menu:
                    menus) {
                Permissions.add(menu.getPerms());
            }
            if(null!=Permissions&&Permissions.size()>0) {
                info.addStringPermissions(Permissions);
            }

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
