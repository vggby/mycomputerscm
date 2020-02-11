package com.mydesign.mycomputerscm.Controller;

import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.domain.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
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
}
