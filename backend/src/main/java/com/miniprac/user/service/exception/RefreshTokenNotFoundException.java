package com.miniprac.user.service.exception;

import com.miniprac.common.exception.EntityNotFoundException;

public class RefreshTokenNotFoundException extends EntityNotFoundException {
    public RefreshTokenNotFoundException() {
        super("refresh token 이 존재하지 않습니다.");
    }
}
