package com.mydesign.mycomputerscm.Controller;

import com.mydesign.mycomputerscm.Service.UserService;
import com.mydesign.mycomputerscm.domain.ResultInfo;
import com.mydesign.mycomputerscm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

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
    @GetMapping("/register")
    public String registerUI() {

        return "users/register";
    }
    @GetMapping("/center")
    public String centerUI() {
        return "users/center";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResultInfo login(User user, HttpSession session) {
        ResultInfo resultInfo = new ResultInfo();
        User userLogin = userService.UserLogin(user);
        System.out.println(userLogin);
        if (userLogin != null) {
            resultInfo.setFlag(true);
            session.setAttribute("user", user);
        } else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("登录失败");
        }
        return resultInfo;
    }
    @PostMapping("/regist")
    @ResponseBody
    public ResultInfo register(User user) {
        ResultInfo resultInfo = new ResultInfo();

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
