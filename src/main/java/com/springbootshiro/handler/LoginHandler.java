package com.springbootshiro.handler;

import com.springbootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Administrator on 2018/6/20.
 */
@Controller
public class LoginHandler {
    private static final Logger logger = LoggerFactory.getLogger(LoginHandler.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/login.action")
    public String login(String username, String password, Map<String, Object> map, HttpSession session) {
        System.out.println(username + "---" + password);
        // 获得当前Subject
        Subject currentUser = SecurityUtils.getSubject();
        // 验证用户是否验证，即是否登录
        if (!currentUser.isAuthenticated()) {
            String msg = "";
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // remembermMe记住密码
            token.setRememberMe(true);
            try {
                // 执行登录.
                currentUser.login(token);

                // 登录成功...
                return "redirect:/LoginSuccess.action";
            } catch (IncorrectCredentialsException e) {
                msg = "登录密码错误";
                logger.info("登录密码错误!!!" + e);
            } catch (ExcessiveAttemptsException e) {
                msg = "登录失败次数过多";
                logger.info("登录失败次数过多!!!" + e);
            } catch (LockedAccountException e) {
                msg = "帐号已被锁定";
                logger.info("帐号已被锁定!!!" + e);
            } catch (DisabledAccountException e) {
                msg = "帐号已被禁用";
                logger.info("帐号已被禁用!!!" + e);
            } catch (ExpiredCredentialsException e) {
                msg = "帐号已过期";
                logger.info("帐号已过期!!!" + e);
            } catch (UnknownAccountException e) {
                msg = "帐号不存在";
                logger.info("帐号不存在!!!" + e);
            } catch (UnauthorizedException e) {
                msg = "您没有得到相应的授权！";
                logger.info("您没有得到相应的授权！" + e);
            } catch (Exception e) {
                logger.info("出错！！！" + e);
            }
            map.put("msg", msg);
            return "/index";
        }

        // 登录成功，重定向到LoginSuccess.action
        return "redirect:/LoginSuccess.action";

    }
}
