package com.miniprac.board.domain.repository;

import com.miniprac.board.domain.Board;
import com.miniprac.board.dto.BoardRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {
    Page<Board> findAllBySchoolAndDate(BoardRequest.GetListBySchoolAndDate request, Pageable pageable);
}
