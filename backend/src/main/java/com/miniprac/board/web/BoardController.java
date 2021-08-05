package com.miniprac.board.web;

import com.miniprac.board.domain.Board;
import com.miniprac.board.dto.BoardInfo;
import com.miniprac.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    ResponseEntity<BoardInfo> createBoard(@RequestBody BoardInfo boardInfo){

        boardService.createBoard(boardInfo);
        return new ResponseEntity<>(boardInfo, HttpStatus.OK);

    }
}
