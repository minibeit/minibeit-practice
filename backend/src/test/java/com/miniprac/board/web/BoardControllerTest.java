package com.miniprac.board.web;

import com.miniprac.MvcTest;
import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.BoardCategory;
import com.miniprac.board.domain.BoardCategoryType;
import com.miniprac.board.dto.BoardRequest;
import com.miniprac.board.service.BoardService;
import com.miniprac.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
class BoardControllerTest extends MvcTest {
    @MockBean
    private BoardService boardService;

    private User user;
    private Board board1;
    private Board board2;
    private Board board3;
    private List<Board> boardList = new ArrayList<>();
    private BoardCategory boardCategory;

    @BeforeEach
    public void setup() {
        user = User.builder().id(1L).email("test@test.com").password("1234").name("테스터").build();
        boardCategory = BoardCategory.builder().id(1L).type(BoardCategoryType.SURVEY).build();
        board1 = Board.builder()
                .id(1L)
                .title("피실험자 모집1")
                .content("피실험자 모집합니다~")
                .place("고려대 신공학관")
                .pay(20000)
                .phoneNum("010-1234-5678")
                .category(boardCategory)
                .dueDate(LocalDate.of(2021, 8, 20))
                .doDate(LocalDateTime.of(2021, 8, 10, 9, 30))
                .build();
        board1.setCreatedBy(user);
        board2 = Board.builder()
                .id(2L)
                .title("피실험자 모집2")
                .content("피실험자 모집합니다~")
                .place("고려대 신공학관")
                .pay(50000)
                .phoneNum("010-1234-5678")
                .category(boardCategory)
                .dueDate(LocalDate.of(2021, 8, 20))
                .doDate(LocalDateTime.of(2021, 8, 10, 9, 30))
                .build();
        board2.setCreatedBy(user);
        board3 = Board.builder()
                .id(3L)
                .title("피실험자 모집3")
                .content("피실험자 모집합니다~")
                .place("고려대 신공학관")
                .pay(30000)
                .phoneNum("010-1234-5678")
                .category(boardCategory)
                .dueDate(LocalDate.of(2021, 8, 20))
                .doDate(LocalDateTime.of(2021, 8, 10, 9, 30))
                .build();
        board3.setCreatedBy(user);

        boardList.add(board1);
        boardList.add(board2);
        boardList.add(board3);
    }

    @Test
    @DisplayName("게시물 작성 문서화")
    public void create() throws Exception {
        BoardRequest.Create request = BoardRequest.Create.builder()
                .title("피실험자 모집")
                .content("피실험자 모집합니다~~")
                .place("고려대 신공학관")
                .pay(20000)
                .phoneNum("010-1234-5678")
                .category("SURVEY")
                .dueDate(LocalDate.of(2021, 8, 20))
                .doDate(LocalDateTime.of(2021, 8, 10, 9, 30))
                .build();

        given(boardService.create(any())).willReturn(board1);

        ResultActions results = mvc.perform(
                post("/api/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request))
        );

        results.andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("board-create",
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                fieldWithPath("place").type(JsonFieldType.STRING).description("장소"),
                                fieldWithPath("pay").type(JsonFieldType.NUMBER).description("급여"),
                                fieldWithPath("phoneNum").type(JsonFieldType.STRING).description("연락처"),
                                fieldWithPath("category").type(JsonFieldType.STRING).description("카테고리 SURVEY or EXPERIMENT"),
                                fieldWithPath("dueDate").type(JsonFieldType.STRING).description("마감날짜"),
                                fieldWithPath("doDate").type(JsonFieldType.STRING).description("실험/설문 날짜")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("생성된 게시물 식별자")
                        )
                ));
    }

    @Test
    @DisplayName("게시물 목록 조회 문서화")
    public void getList() throws Exception {
        given(boardService.getList(any())).willReturn(boardList);

        ResultActions results = mvc.perform(
                get("/api/board/list")
                        .param("category", "survey"));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("board-getList",
                        requestParameters(
                                parameterWithName("category").description("조회할 게시물 카테고리 SURVEY or EXPERIMENT")
                        ),
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("게시물 식별자"),
                                fieldWithPath("[].title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("[].place").type(JsonFieldType.STRING).description("장소"),
                                fieldWithPath("[].author").type(JsonFieldType.STRING).description("게시물 작성자 이름"),
                                fieldWithPath("[].dueDate").type(JsonFieldType.STRING).description("마감날짜"),
                                fieldWithPath("[].doDate").type(JsonFieldType.STRING).description("실험/설문 날짜")
                        )
                ));
    }
}