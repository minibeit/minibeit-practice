package com.miniprac.user.service.exception;

import com.miniprac.common.exception.InvalidValueException;

public class EmailExistedException extends InvalidValueException {
    public EmailExistedException() {
        super("이메일이 중복되었습니다.");
    }
}
