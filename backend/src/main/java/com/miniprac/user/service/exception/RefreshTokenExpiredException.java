package com.miniprac.user.service.exception;

import com.miniprac.common.exception.InvalidValueException;

public class RefreshTokenExpiredException extends InvalidValueException {
    public RefreshTokenExpiredException() {
        super("만료된 refresh token 입니다.");
    }
}
