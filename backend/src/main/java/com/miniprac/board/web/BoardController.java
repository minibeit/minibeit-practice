package com.miniprac.board.web;

import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.BoardLike;
import com.miniprac.board.dto.BoardRequest;
import com.miniprac.board.dto.BoardResponse;
import com.miniprac.board.service.BoardService;
import com.miniprac.common.dto.PageDto;
import com.miniprac.security.userdetails.CurrentUser;
import com.miniprac.security.userdetails.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
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
        int count = boardService.likesCount(boardId);

        return ResponseEntity.ok().body(BoardResponse.GetOne.build(board,count));
    }

    @GetMapping("/list")
    public Page<BoardResponse.GetList> getList(PageDto pageDto, BoardRequest.GetListByCategory request) {
        Page<Board> list = boardService.getList(pageDto, request);

        List<Board> content = list.getContent();
        List<BoardResponse.GetList> response = new ArrayList<>();

        for (int i = 0; i < content.size(); ++i) {
            int count = boardService.likesCount(content.get(i).getId());
            response.add(BoardResponse.GetList.build(content.get(i), count));
        }

        return new PageImpl<>(response, pageDto.of(), list.getTotalElements());
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<BoardResponse.OnlyId> update(@PathVariable Long boardId,
                                                       @RequestBody BoardRequest.Update request,
                                                       @CurrentUser CustomUserDetails userDetails) {

        Board board = boardService.update(request, boardId, userDetails.getUser());

        return ResponseEntity.ok().body(BoardResponse.OnlyId.build(board));
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> delete(@PathVariable Long boardId,
                                       @CurrentUser CustomUserDetails userDetails){
        boardService.deleteBoard(boardId, userDetails.getUser());

        return ResponseEntity.ok().build();
    }
    @PostMapping("/like/{boardId}")
    public ResponseEntity<BoardResponse.OnlyId> likes(@PathVariable Long boardId,
                                                      @CurrentUser CustomUserDetails userDetails){
        boardService.createLike(boardId, userDetails.getUser());
        return ResponseEntity.ok().body(BoardResponse.OnlyId.builder().id(boardId).build());
    }
}
