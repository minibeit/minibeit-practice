package com.miniprac.board.domain.repository;

import com.miniprac.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByCategoryId(Long categoryId, Pageable pageable);
}
