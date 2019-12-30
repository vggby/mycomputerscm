package com.mydesign.mycomputerscm.Controller;

import com.mydesign.mycomputerscm.Service.MenuService;
import com.mydesign.mycomputerscm.Service.UserService;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.domain.SysUser;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @PostMapping("/regist")
    @ResponseBody
    public ResultInfo register(SysUser user) {
        ResultInfo resultInfo = new ResultInfo();
        // 加密算法MD5
        // salt盐 username + salt
        // 迭代次数
        String md5Pwd = new SimpleHash("MD5", user.getPassword(),
                ByteSource.Util.bytes(user.getUsername() + "salt"), 2).toHex();
        user.setPassword(md5Pwd);
        int i = userService.SaveUser(user);
        if(i==0){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败");
        }else{
            resultInfo.setFlag(true);

        }
        return resultInfo;
    }



}
