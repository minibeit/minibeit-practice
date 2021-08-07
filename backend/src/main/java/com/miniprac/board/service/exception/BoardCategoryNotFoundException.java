package com.miniprac.board.service.exception;

import com.miniprac.common.exception.EntityNotFoundException;

public class BoardCategoryNotFoundException extends EntityNotFoundException {
    public BoardCategoryNotFoundException() {
        super("존재하지 않는 카테고리 입니다.");
    }
}
