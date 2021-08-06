package com.miniprac.board.service.exception;

import com.miniprac.common.exception.EntityNotFoundException;

public class BoardNotFoundException extends EntityNotFoundException {
    public BoardNotFoundException() {
        super("존재하지 않는 게시글입니다.");
    }
}
