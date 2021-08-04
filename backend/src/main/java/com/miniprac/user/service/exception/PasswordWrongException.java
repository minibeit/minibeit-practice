package com.miniprac.user.service.exception;

import com.miniprac.common.exception.InvalidValueException;

public class PasswordWrongException extends InvalidValueException {
    public PasswordWrongException() {
        super("비밀번호가 잘못 되었습니다.");
    }
}
