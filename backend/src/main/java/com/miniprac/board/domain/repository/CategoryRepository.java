package com.miniprac.board.domain.repository;

import com.miniprac.board.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
