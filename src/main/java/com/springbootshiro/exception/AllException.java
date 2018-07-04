package com.springbootshiro.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/6/20.
 */
@ControllerAdvice
public class AllException {

    private static final Logger logger = LoggerFactory.getLogger(AllException.class);
    // 角色权限异常扑捉
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public String roleException(UnauthorizedException e) {
        logger.info("------------>" + e);
        return "角色权限不够!!!";
    }

    // 其他异常捕捉
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String allException (Exception e) {
        logger.info("----------->" + e);
        return "系统出现异常!!!";
    }
}
