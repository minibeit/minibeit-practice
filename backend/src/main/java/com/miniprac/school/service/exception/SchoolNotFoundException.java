package com.miniprac.school.service.exception;

import com.miniprac.common.exception.EntityNotFoundException;

public class SchoolNotFoundException extends EntityNotFoundException {
    public SchoolNotFoundException() {
        super("존재하지 않는 학교입니다.");
    }
}
