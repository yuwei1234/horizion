package com.msb.exception.user;

import com.msb.exception.BaseException;

public class UserException extends BaseException {

    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args, String message) {
        super("user", code, args, message);
    }
}
