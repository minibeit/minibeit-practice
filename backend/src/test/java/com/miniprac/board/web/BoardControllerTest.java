package com.miniprac.board.web;

import com.miniprac.MvcTest;
import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.BoardCategory;
import com.miniprac.board.domain.BoardCategoryType;
import com.miniprac.board.domain.BoardFile;
import com.miniprac.board.dto.BoardRequest;
import com.miniprac.board.service.BoardService;
import com.miniprac.file.domain.File;
import com.miniprac.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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
                .time(20)
                .phoneNum("010-1234-5678")
                .category(boardCategory)
                .dueDate(LocalDate.of(2021, 8, 20))
                .doDate(LocalDateTime.of(2021, 8, 10, 9, 30))
                .boardFileList(Collections.singletonList(BoardFile.create(File.builder().url("image url").build())))
                .build();
        board1.setCreatedBy(user);
        board2 = Board.builder()
                .id(2L)
                .title("피실험자 모집2")
                .content("피실험자 모집합니다~")
                .place("고려대 신공학관")
                .pay(50000)
                .time(30)
                .phoneNum("010-1234-5678")
                .category(boardCategory)
                .dueDate(LocalDate.of(2021, 8, 20))
                .doDate(LocalDateTime.of(2021, 8, 10, 9, 30))
                .boardFileList(Collections.singletonList(BoardFile.create(File.builder().url("image url").build())))
                .build();
        board2.setCreatedBy(user);
        board3 = Board.builder()
                .id(3L)
                .title("피실험자 모집3")
                .content("피실험자 모집합니다~")
                .place("고려대 신공학관")
                .pay(30000)
                .time(40)
                .phoneNum("010-1234-5678")
                .category(boardCategory)
                .dueDate(LocalDate.of(2021, 8, 20))
                .doDate(LocalDateTime.of(2021, 8, 10, 9, 30))
                .boardFileList(Collections.singletonList(BoardFile.create(File.builder().url("image url").build())))
                .build();
        board3.setCreatedBy(user);

        boardList.add(board1);
        boardList.add(board2);
        boardList.add(board3);
    }

    @Test
    @DisplayName("게시물 작성 문서화")
    public void create() throws Exception {
        InputStream is1 = new ClassPathResource("mock/images/enjoy.png").getInputStream();
        InputStream is2 = new ClassPathResource("mock/images/enjoy2.png").getInputStream();
        MockMultipartFile mockFile1 = new MockMultipartFile("files", "mock_file1.jpg", "image/jpg", is1.readAllBytes());
        MockMultipartFile mockFile2 = new MockMultipartFile("files", "mock_file2.jpg", "image/jpg", is2.readAllBytes());

        given(boardService.create(any())).willReturn(board1);

        ResultActions results = mvc.perform(
                multipart("/api/board")
                        .file(mockFile1)
                        .file(mockFile2)
                        .param("title", "피실험자 모집")
                        .param("content", "피실험자 모집합니다~~")
                        .param("place", "고려대 신공학관")
                        .param("phoneNum", "010-1234-5678")
                        .param("category", "SURVEY")
                        .param("pay", "20000")
                        .param("time", "20")
                        .param("dueDate", "2021-08-20")
                        .param("doDate", "2021-08-10T09:30:00")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .characterEncoding("UTF-8")
        );

        results.andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("board-create",
                        requestParameters(
                                parameterWithName("title").description("제목"),
                                parameterWithName("content").description("내용"),
                                parameterWithName("place").description("장소"),
                                parameterWithName("pay").description("급여"),
                                parameterWithName("time").description("소요시간(분단위)"),
                                parameterWithName("phoneNum").description("연락처"),
                                parameterWithName("category").description("카테고리 SURVEY or EXPERIMENT"),
                                parameterWithName("dueDate").description("마감날짜"),
                                parameterWithName("doDate").description("실험/설문 날짜")
                        ), requestParts(
                                partWithName("files").description("게시물에 추가할 파일")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("생성된 게시물 식별자")
                        )
                ));
    }

    @Test
    @DisplayName("게시물 목록 조회 문서화")
    public void getList() throws Exception {

        Page<Board> boardPage = new PageImpl<>(boardList, PageRequest.of(1, 2), boardList.size());
        given(boardService.getList(any(), any())).willReturn(boardPage);

        ResultActions results = mvc.perform(
                get("/api/board/list")
                        .param("category", "survey")
                        .param("page", "1")
                        .param("size", "2")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("board-getList",
                        requestParameters(
                                parameterWithName("category").description("조회할 게시물 카테고리 SURVEY or EXPERIMENT"),
                                parameterWithName("page").description("조회할 페이지"),
                                parameterWithName("size").description("조회할 사이즈")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("게시물 식별자"),
                                fieldWithPath("content[].title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("content[].place").type(JsonFieldType.STRING).description("장소"),
                                fieldWithPath("content[].contact").type(JsonFieldType.STRING).description("핸드폰 번호"),
                                fieldWithPath("content[].author").type(JsonFieldType.STRING).description("게시물 작성자 이름"),
                                fieldWithPath("content[].dueDate").type(JsonFieldType.STRING).description("마감날짜"),
                                fieldWithPath("content[].doDate").type(JsonFieldType.STRING).description("실험/설문 날짜"),
                                fieldWithPath("totalElements").description("전체 개수"),
                                fieldWithPath("last").description("마지막 페이지인지 식별"),
                                fieldWithPath("totalPages").description("전체 페이지")
                        )
                ));
    }

    @Test
    @DisplayName("게시물 단건 조회 문서화")
    public void getOne() throws Exception {
        given(boardService.getOne(any())).willReturn(board1);

        ResultActions results = mvc.perform(RestDocumentationRequestBuilders
                .get("/api/board/{boardId}", 1));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("board-getOne",
                        pathParameters(
                                parameterWithName("boardId").description("조회할 게시물 식별자")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("게시물 식별자"),
                                fieldWithPath("category").type(JsonFieldType.STRING).description("카테고리"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                fieldWithPath("place").type(JsonFieldType.STRING).description("장소"),
                                fieldWithPath("contact").type(JsonFieldType.STRING).description("연락처"),
                                fieldWithPath("author").type(JsonFieldType.STRING).description("게시물 작성자 이름"),
                                fieldWithPath("pay").type(JsonFieldType.NUMBER).description("게시물 작성자 이름"),
                                fieldWithPath("time").type(JsonFieldType.NUMBER).description("소요시간(분단위)"),
                                fieldWithPath("dueDate").type(JsonFieldType.STRING).description("마감날짜"),
                                fieldWithPath("doDate").type(JsonFieldType.STRING).description("실험/설문 날짜"),
                                fieldWithPath("isMine").type(JsonFieldType.BOOLEAN).description("로그인 유저와 게시물 작성자가 같다면 true"),
                                fieldWithPath("images[].url").type(JsonFieldType.STRING).description("이미지 url")
                        )
                ));
    }

    @Test
    @DisplayName("게시물 수정 문서화")
    public void edit() throws Exception {
        BoardRequest.Update request = BoardRequest.Update.builder()
                .title("피실험자 모집 수정 버전")
                .content("피실험자 모집합니다~~ (수정했습니다!!)")
                .place("고려대 신공학관")
                .pay(50000)
                .phoneNum("010-1234-5678")
                .category("SURVEY")
                .dueDate(LocalDate.of(2021, 8, 20))
                .doDate(LocalDateTime.of(2021, 8, 10, 9, 30))
                .build();

        given(boardService.update(any(), any(), any())).willReturn(board1);


        ResultActions results = mvc.perform(RestDocumentationRequestBuilders
                .put("/api/board/{boardId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(objectMapper.writeValueAsString(request))
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("board-update",
                        pathParameters(
                                parameterWithName("boardId").description("수정할 게시물 식별자")
                        ),
                        requestFields(
                                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("내용"),
                                fieldWithPath("place").type(JsonFieldType.STRING).description("장소"),
                                fieldWithPath("pay").type(JsonFieldType.NUMBER).description("급여"),
                                fieldWithPath("time").type(JsonFieldType.NUMBER).description("소요 시간"),
                                fieldWithPath("phoneNum").type(JsonFieldType.STRING).description("연락처"),
                                fieldWithPath("category").type(JsonFieldType.STRING).description("카테고리 SURVEY or EXPERIMENT"),
                                fieldWithPath("dueDate").type(JsonFieldType.STRING).description("마감날짜"),
                                fieldWithPath("doDate").type(JsonFieldType.STRING).description("실험/설문 날짜")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("수정된 게시물 식별자")
                        )
                ));
    }

    @Test
    @DisplayName("게시글 삭제 문서화")
    public void deleteBoard() throws Exception {
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders
                .delete("/api/board/{boardId}", 1));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("board-delete",
                        pathParameters(
                                parameterWithName("boardId").description("수정할 게시물 식별자")
                        )
                ));
    }
}