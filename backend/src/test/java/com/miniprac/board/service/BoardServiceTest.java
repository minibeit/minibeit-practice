package com.miniprac.board.service;

import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.Category;
import com.miniprac.board.dto.BoardInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardService boardService;

    @Test
    public void 게시글_생성() throws Exception {
        //given
        BoardInfo boardInfo = BoardInfo.builder()
                .categoryType("설문")
                .content("내용")
                .pay(50000)
                .phoneNum("010-1234-5678")
                .place("성북구")
                .recruitmentPeriod("2021.10.10")
                .startDate(LocalDateTime.MAX)
                .title("새로운 실험")
                .build();

        Board board = Board.builder()
                .id(1L)
                .category(Category.builder().type("설문").build())
                .content("내용")
                .pay(50000)
                .phoneNum("010-1234-5678")
                .place("성북구")
                .recruitmentPeriod("2021.10.10")
                .startDate(LocalDateTime.MAX)
                .title("새로운 실험")
                .build();

        lenient().when(boardService.createBoard(boardInfo))
                .thenReturn(board);

        //when

        //then
        assertEquals(board.getId(), 1L);
        assertEquals(board.getCategory().getType(), boardInfo.getCategoryType());
        assertEquals(board.getContent(), boardInfo.getContent());
        assertEquals(board.getPay(), boardInfo.getPay());
        assertEquals(board.getPhoneNum(), boardInfo.getPhoneNum());
        assertEquals(board.getPlace(), boardInfo.getPlace());
        assertEquals(board.getRecruitmentPeriod(), boardInfo.getRecruitmentPeriod());
        assertEquals(board.getTitle(), boardInfo.getTitle());
        assertEquals(board.getRecruitmentPeriod(), boardInfo.getRecruitmentPeriod());

    }



}