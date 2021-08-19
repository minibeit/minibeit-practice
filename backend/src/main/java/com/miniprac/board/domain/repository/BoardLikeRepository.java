package com.miniprac.board.domain.repository;

import com.miniprac.board.domain.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {

    Optional<BoardLike> findByBoardIdAndCreatedById(Long boardId, Long userId);

    List<BoardLike> findAllByBoardId(Long boardId);
}
