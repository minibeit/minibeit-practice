package com.miniprac.board.domain.repository;

import com.miniprac.board.domain.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardCategoryRepository extends JpaRepository<BoardCategory, Long> {
}
