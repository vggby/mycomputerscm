package com.mydesign.mycomputerscm.config;

import com.mydesign.mycomputerscm.Service.MenuService;
import com.mydesign.mycomputerscm.Service.UserService;
import com.mydesign.mycomputerscm.domain.ActiverUser;
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
import java.util.List;
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    @Lazy  //只有使用的时候才会加载
    private MenuService menuService;
    @Autowired
    private UserService userService;
        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
            ActiverUser activerUser = (ActiverUser) SecurityUtils.getSubject().getPrincipal();
            SysUser user=activerUser.getUser();
            /*取权限*/
            List<String> permissions = activerUser.getPermissions();
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            if(null!=permissions&&permissions.size()>0) {
                /*授权*/
                info.addStringPermissions(permissions);
            }

            return info;

        }


        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
            System.out.println("-------身份认证方法--------");
            UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
            SysUser sysUser = userService.findbyUsername(token.getUsername());
            ActiverUser activerUser = new ActiverUser();
            //根据用户名从数据库获取密码
            if (sysUser == null) {
                return null;
            }
            activerUser.setUser(sysUser);

            /*从数据库读出权限*/
            List<Menu> menus = menuService.selectpermissionByUserId(sysUser);
            List<String> Permissions = new ArrayList<>();
            for (Menu menu:
                    menus) {
                Permissions.add(menu.getPerms());
            }

            if(null!=Permissions&&Permissions.size()>0) {
                activerUser.setPermissions(Permissions);
            }

            return new SimpleAuthenticationInfo(activerUser, sysUser.getPassword(),
                    ByteSource.Util.bytes(sysUser.getUsername() + "salt"), getName());
        }
}
