package com.miniprac.board.service;

import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.Category;
import com.miniprac.board.domain.repository.BoardRepository;
import com.miniprac.board.domain.repository.CategoryRepository;
import com.miniprac.board.dto.BoardInfo;
import com.miniprac.board.dto.CategoryDto;
import com.miniprac.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    public Board createBoard(BoardInfo request){

        Board board = Board.create(request.getTitle(), request.getContent(),
                request.getPlace(), request.getPhoneNum(),
                request.getPay(), request.getRecruitmentPeriod(), request.getStartDate());

        Category category = categoryRepository.save(Category.builder().type(request.getCategoryType()).build());

        board.settingCategoryType(category);

        return boardRepository.save(board);
    }


}
