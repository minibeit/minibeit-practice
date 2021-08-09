package com.miniprac.board.domain.repository;

import com.miniprac.board.domain.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
    @Modifying
    @Query("delete from BoardFile bf where bf.file.id in :fileIdList")
    void deleteAllByFileIds(@Param("fileIdList") List<Long> fileIdList);
}
