package com.miniprac.board.service;

import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.BoardCategory;
import com.miniprac.board.domain.BoardCategoryType;
import com.miniprac.board.domain.repository.BoardCategoryRepository;
import com.miniprac.board.domain.repository.BoardRepository;
import com.miniprac.board.dto.BoardRequest;
import com.miniprac.board.service.exception.BoardCategoryNotFoundException;
import com.miniprac.board.service.exception.BoardNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardCategoryRepository boardCategoryRepository;

    public Board create(BoardRequest.Create request) {
        BoardCategory category = boardCategoryRepository.findByType(BoardCategoryType.from(request.getCategory())).orElseThrow(BoardCategoryNotFoundException::new);
        Board board = Board.create(request, category);

        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public List<Board> getList(BoardRequest.GetListByCategory request) {
        BoardCategory category = boardCategoryRepository.findByType(BoardCategoryType.from(request.getCategory())).orElseThrow(BoardCategoryNotFoundException::new);
        return boardRepository.findAllByCategoryId(category.getId());
    }

    @Transactional(readOnly = true)
    public Board getOne(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }

    public Board update(BoardRequest.Update request, Long boardId){
        BoardCategory category = boardCategoryRepository.findByType(BoardCategoryType.from(request.getCategory())).orElseThrow(BoardCategoryNotFoundException::new);
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        board.update(request, category);
        return board;
    }

    public void deleteBoard(Long boardId){
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        boardRepository.delete(board);
    }
}
