package com.msb.exception.user;

public class UserPasswordNotMatchException extends UserException {
    public UserPasswordNotMatchException() {
        super(null, null, "密码错误");
    }
}
