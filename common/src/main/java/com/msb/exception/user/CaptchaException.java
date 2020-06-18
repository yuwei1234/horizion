package com.msb.exception.user;

public class CaptchaException extends UserException{

    private static final long serialVersionUID = 1L;

    public CaptchaException() {
        super(null, null, "验证码错误或者已过期，请重新输入");
    }
}
