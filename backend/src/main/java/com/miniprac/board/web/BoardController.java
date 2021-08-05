package com.miniprac.board.web;

import com.miniprac.board.domain.Board;
import com.miniprac.board.dto.BoardRequest;
import com.miniprac.board.dto.BoardResponse;
import com.miniprac.board.service.BoardService;
import com.miniprac.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    ResponseEntity<BoardResponse> createBoard(@RequestBody BoardRequest boardRequest){

        Board board = boardService.createBoard(boardRequest);

        BoardResponse boardResponse = BoardResponse.builder()
                .categoryType(board.getCategory().getType())
                .content(board.getContent())
                .deadline(board.getDeadline())
                .pay(board.getPay())
                .phoneNum(board.getPhoneNum())
                .place(board.getPlace())
                .startDate(board.getStartDate())
                .title(board.getTitle())
                .build();

        return ResponseEntity.created(URI.create("/api/board/" + board.getId())).body(boardResponse);

    }
}
