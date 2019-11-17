package com.mydesign.mycomputerscm.Controller;

import com.mydesign.mycomputerscm.Service.UserService;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String loginUI() {

        return "users/userlogin";
    }
    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(SysUser user) {
        ResultInfo resultInfo = new ResultInfo();

        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("未知账户");
            resultInfo.setData(null);
            return resultInfo;
        } catch (IncorrectCredentialsException ice) {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("密码不正确");
            return resultInfo;

        }
        if (subject.isAuthenticated()) {
            resultInfo.setFlag(true);
            resultInfo.setData(null);
            return resultInfo;
        }else
        {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("登录不成功");
            resultInfo.setData(null);
            return resultInfo;
        }


    }
    @GetMapping("/register")
    public String registerUI() {

        return "users/register";
    }

    @GetMapping("/center")
    public String centerUI() {
        return "users/center";
    }

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
        SysUser i = userService.SaveUser(user);
        if(i==null){
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败");
        }else{
            resultInfo.setFlag(true);

        }
        return resultInfo;
    }

}
