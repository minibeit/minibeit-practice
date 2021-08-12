package com.miniprac.board.domain.repository;

import com.miniprac.board.domain.BoardCategory;
import com.miniprac.board.domain.BoardCategoryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
    Optional<BoardCategory> findByType(BoardCategoryType type);
}
