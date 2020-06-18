package com.msb.exception.user;

public class UserNotExistsException extends UserException {

    public UserNotExistsException() {
        super(null, null, "用户不存在");
    }
}
