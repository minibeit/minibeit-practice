package com.miniprac.user.service.exception;

import com.miniprac.common.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {
    public UserNotFoundException() {
        super("존재하지 않는 유저 입니다.");
    }
}
