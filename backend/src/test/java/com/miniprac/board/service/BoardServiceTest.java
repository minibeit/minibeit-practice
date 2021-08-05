package com.miniprac.board.service;

import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.Category;
import com.miniprac.board.dto.BoardRequest;
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
        BoardRequest boardRequest = BoardRequest.builder()
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

        lenient().when(boardService.createBoard(boardRequest))
                .thenReturn(board);

        //when

        //then
        assertEquals(board.getId(), 1L);
        assertEquals(board.getCategory().getType(), boardRequest.getCategoryType());
        assertEquals(board.getContent(), boardRequest.getContent());
        assertEquals(board.getPay(), boardRequest.getPay());
        assertEquals(board.getPhoneNum(), boardRequest.getPhoneNum());
        assertEquals(board.getPlace(), boardRequest.getPlace());
        assertEquals(board.getRecruitmentPeriod(), boardRequest.getRecruitmentPeriod());
        assertEquals(board.getTitle(), boardRequest.getTitle());
        assertEquals(board.getRecruitmentPeriod(), boardRequest.getRecruitmentPeriod());

    }



}