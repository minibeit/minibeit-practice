package com.miniprac.board.web;

import com.miniprac.board.domain.Board;
import com.miniprac.board.dto.BoardRequest;
import com.miniprac.board.dto.BoardResponse;
import com.miniprac.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponse.OnlyId> create(@RequestBody BoardRequest.Create request) {
        Board board = boardService.create(request);
        return ResponseEntity.created(URI.create("/api/board/" + board.getId())).body(BoardResponse.OnlyId.build(board));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponse.GetOne> getOne(@PathVariable Long boardId) {
        Board board = boardService.getOne(boardId);

        return ResponseEntity.ok().body(BoardResponse.GetOne.build(board));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardResponse.GetList>> getList(BoardRequest.GetListByCategory request) {
        List<Board> board = boardService.getList(request);
        List<BoardResponse.GetList> response = board.stream().map(BoardResponse.GetList::build).collect(Collectors.toList());

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<BoardResponse.OnlyId> update(@PathVariable Long boardId, @RequestBody BoardRequest.Update request) {
        Board board = boardService.update(request, boardId);

        return ResponseEntity.ok().body(BoardResponse.OnlyId.build(board));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> delete(@PathVariable Long boardId){
        boardService.deleteBoard(boardId);

        return ResponseEntity.ok().build();
    }
}
