package com.miniprac.board.domain.repository;

import com.miniprac.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
    Page<Board> findAllByCategoryId(Long categoryId, Pageable pageable);
}
