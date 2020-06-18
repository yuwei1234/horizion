package com.msb.controller.system;

import com.msb.constant.Constants;
import com.msb.core.domain.AjaxResult;
import com.msb.utils.RedisUtils;
import com.msb.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class LoginController {

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult submitLoginForm(HttpServletRequest httpServletRequest, LoginVo loginVo){
        String code = loginVo.getValidateCode();
        String uuid = loginVo.getUuid();
        String verifyCodeCache = (String) redisUtils.get(Constants.VERIFY_CODE_KEY + uuid);
        redisUtils.del(Constants.VERIFY_CODE_KEY + uuid);
        if(StringUtils.isBlank(verifyCodeCache)){
            return AjaxResult.success(Constants.SUCCESS_CODE, "验证码错误或者已过期，请重新输入");
        }
        if(StringUtils.isNoneBlank(code)){
            if(code.equalsIgnoreCase(verifyCodeCache)){
                Subject subject = SecurityUtils.getSubject();
                UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUsername(), loginVo.getPassword(), loginVo.isRememberMe());
                try {
                    subject.login(token);
                    return AjaxResult.success(Constants.SUCCESS_CODE, "登录成功");
                }catch (AuthenticationException e){
                    return AjaxResult.error(Constants.ERROR_CODE, e.getMessage());
                }
            }
            return AjaxResult.error(Constants.ERROR_CODE, "验证码错误，请重新输入");
        }
        return AjaxResult.error(Constants.ERROR_CODE, "请输入验证码");
    }
}
