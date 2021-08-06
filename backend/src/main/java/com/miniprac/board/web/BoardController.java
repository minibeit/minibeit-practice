package com.miniprac.board.web;

import com.miniprac.board.domain.Board;
import com.miniprac.board.dto.BoardRequest;
import com.miniprac.board.dto.BoardResponse;
import com.miniprac.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponse.OnlyId> createBoard(@RequestBody BoardRequest.Create request) {
        Board board = boardService.create(request);

        return ResponseEntity.created(URI.create("/api/board/" + board.getId())).body(BoardResponse.OnlyId.build(board));
    }
}
