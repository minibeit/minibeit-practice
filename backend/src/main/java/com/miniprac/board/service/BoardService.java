package com.miniprac.board.service;

import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.BoardCategory;
import com.miniprac.board.domain.BoardCategoryType;
import com.miniprac.board.domain.BoardLike;
import com.miniprac.board.domain.repository.BoardCategoryRepository;
import com.miniprac.board.domain.repository.BoardLikeRepository;
import com.miniprac.board.domain.repository.BoardRepository;
import com.miniprac.board.dto.BoardRequest;
import com.miniprac.board.service.exception.BoardCategoryNotFoundException;
import com.miniprac.board.service.exception.BoardNotFoundException;
import com.miniprac.common.dto.PageDto;
import com.miniprac.common.exception.PermissionException;
import com.miniprac.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardCategoryRepository boardCategoryRepository;
    private final BoardLikeRepository boardLikeRepository;

    public Board create(BoardRequest.Create request) {
        BoardCategory category = boardCategoryRepository.findByType(BoardCategoryType.from(request.getCategory())).orElseThrow(BoardCategoryNotFoundException::new);
        Board board = Board.create(request, category);

        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> getList(PageDto pageDto, BoardRequest.GetListByCategory request) {
        BoardCategory category = boardCategoryRepository.findByType(BoardCategoryType.from(request.getCategory())).orElseThrow(BoardCategoryNotFoundException::new);
        return boardRepository.findAllByCategoryId(category.getId(), pageDto.of());
    }

    @Transactional(readOnly = true)
    public Board getOne(Long boardId) {
        return boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public int likesCount(Long boardId){
        return boardLikeRepository.findAllByBoardId(boardId).size();
    }

    public Board update(BoardRequest.Update request, Long boardId, User user){

        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        permissionCheck(user, board);
        BoardCategory category = boardCategoryRepository.findByType(BoardCategoryType.from(request.getCategory())).orElseThrow(BoardCategoryNotFoundException::new);

        board.update(request, category);
        return board;
    }

    public void deleteBoard(Long boardId, User user){
        Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
        permissionCheck(user, board);
        boardRepository.delete(board);
    }

    public void createLike(Long boardId, User user){

        Optional<BoardLike> findBoardLike = boardLikeRepository.findByBoardIdAndCreatedById(boardId, user.getId());

        if(findBoardLike.isEmpty()){
            Board board = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);
            BoardLike boardLike = BoardLike.create(board);
            boardLikeRepository.save(boardLike);
        }else{
            boardLikeRepository.delete(findBoardLike.get());
        }
    }

    private void permissionCheck(User user, Board board) {
        if (!user.getId().equals(board.getCreatedBy().getId())) {
            throw new PermissionException();
        }
    }


}
