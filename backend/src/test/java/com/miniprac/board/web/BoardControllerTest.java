package com.miniprac.board.web;

import com.miniprac.MvcTest;
import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.BoardFile;
import com.miniprac.board.service.BoardService;
import com.miniprac.file.domain.File;
import com.miniprac.school.domain.School;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
class BoardControllerTest extends MvcTest {
    @MockBean
    private BoardService boardService;

    private User user;
    private School school;
    private Board board1;
    private Board board2;
    private Board board3;
    private List<Board> boardList = new ArrayList<>();

    @BeforeEach
    public void setup() {
        user = User.builder().id(1L).email("test@test.com").password("1234").name("?????????").build();
        school = School.builder().id(1L).name("???????????????").build();
        board1 = Board.builder()
                .id(1L)
                .title("???????????? ??????1")
                .content("???????????? ???????????????~")
                .place("????????? ????????????")
                .pay(20000)
                .time(20)
                .school(school)
                .phoneNum("010-1234-5678")
                .dueDate(LocalDate.of(2021, 8, 10))
                .doDate(LocalDateTime.of(2021, 8, 15, 9, 30))
                .boardFileList(Collections.singletonList(BoardFile.create(File.builder().url("image url").build())))
                .build();
        board1.setCreatedBy(user);

        board2 = Board.builder()
                .id(2L)
                .title("???????????? ??????2")
                .content("???????????? ???????????????~")
                .place("????????? ????????????")
                .pay(50000)
                .time(30)
                .school(school)
                .phoneNum("010-1234-5678")
                .dueDate(LocalDate.of(2021, 8, 10))
                .doDate(LocalDateTime.of(2021, 8, 15, 9, 30))
                .boardFileList(Collections.singletonList(BoardFile.create(File.builder().url("image url").build())))
                .build();
        board2.setCreatedBy(user);

        board3 = Board.builder()
                .id(3L)
                .title("???????????? ??????3")
                .content("???????????? ???????????????~")
                .place("????????? ????????????")
                .pay(30000)
                .time(40)
                .school(school)
                .phoneNum("010-1234-5678")
                .dueDate(LocalDate.of(2021, 8, 10))
                .doDate(LocalDateTime.of(2021, 8, 15, 9, 30))
                .boardFileList(Collections.singletonList(BoardFile.create(File.builder().url("image url").build())))
                .build();
        board3.setCreatedBy(user);

        boardList.add(board1);
        boardList.add(board2);
        boardList.add(board3);
    }

    @Test
    @DisplayName("????????? ?????? ?????????")
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
                        .param("title", "???????????? ??????")
                        .param("content", "???????????? ???????????????~~")
                        .param("place", "????????? ????????????")
                        .param("phoneNum", "010-1234-5678")
                        .param("category", "SURVEY")
                        .param("schoolId", "1")
                        .param("pay", "20000")
                        .param("time", "20")
                        .param("dueDate", "2021-08-20")
                        .param("doDate", "2021-08-10T09:30")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .characterEncoding("UTF-8")
        );

        results.andExpect(status().isCreated())
                .andDo(print())
                .andDo(document("board-create",
                        requestParameters(
                                parameterWithName("title").description("??????"),
                                parameterWithName("content").description("??????"),
                                parameterWithName("place").description("??????"),
                                parameterWithName("schoolId").description("?????? ?????????"),
                                parameterWithName("pay").description("??????"),
                                parameterWithName("time").description("????????????(?????????)"),
                                parameterWithName("phoneNum").description("?????????"),
                                parameterWithName("category").description("???????????? SURVEY or EXPERIMENT"),
                                parameterWithName("dueDate").description("????????????"),
                                parameterWithName("doDate").description("??????/?????? ??????")),
                        requestParts(
                                partWithName("files").description("???????????? ????????? ??????")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("????????? ????????? ?????????")
                        )
                ));
    }

    @Test
    @DisplayName("??????,????????? ???????????? ????????? ?????? ?????? ?????????")
    public void getList() throws Exception {
        Page<Board> boardPage = new PageImpl<>(boardList, PageRequest.of(1, 5), boardList.size());
        given(boardService.getListBySchoolAndDate(any(), any(), any())).willReturn(boardPage);

        ResultActions results = mvc.perform(RestDocumentationRequestBuilders
                .get("/api/board/school/{schoolId}/list", 1L)
                .param("date", "2021-08-15")
                .param("page", "1")
                .param("size", "5")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("board-getList-schoolAndDate",
                        pathParameters(
                                parameterWithName("schoolId").description("?????? ?????????")
                        ),
                        requestParameters(
                                parameterWithName("page").description("????????? ?????????"),
                                parameterWithName("size").description("????????? ?????????"),
                                parameterWithName("date").description("????????? ????????? ?????? ??????(doDate)")
                        ),
                        relaxedResponseFields(
                                fieldWithPath("content[].id").type(JsonFieldType.NUMBER).description("????????? ?????????"),
                                fieldWithPath("content[].title").type(JsonFieldType.STRING).description("??????"),
                                fieldWithPath("content[].place").type(JsonFieldType.STRING).description("??????"),
                                fieldWithPath("content[].contact").type(JsonFieldType.STRING).description("????????? ??????"),
                                fieldWithPath("content[].author").type(JsonFieldType.STRING).description("????????? ????????? ??????"),
                                fieldWithPath("content[].dueDate").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("content[].doDate").type(JsonFieldType.STRING).description("??????/?????? ??????"),
                                fieldWithPath("content[].likes").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                fieldWithPath("totalElements").description("?????? ??????"),
                                fieldWithPath("last").description("????????? ??????????????? ??????"),
                                fieldWithPath("totalPages").description("?????? ?????????")
                        )
                ));
    }

    @Test
    @DisplayName("????????? ?????? ?????? ?????????")
    public void getOne() throws Exception {
        given(boardService.getOne(any())).willReturn(board1);

        ResultActions results = mvc.perform(RestDocumentationRequestBuilders
                .get("/api/board/{boardId}", 1));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("board-getOne",
                        pathParameters(
                                parameterWithName("boardId").description("????????? ????????? ?????????")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("????????? ?????????"),
                                fieldWithPath("title").type(JsonFieldType.STRING).description("??????"),
                                fieldWithPath("content").type(JsonFieldType.STRING).description("??????"),
                                fieldWithPath("place").type(JsonFieldType.STRING).description("??????"),
                                fieldWithPath("contact").type(JsonFieldType.STRING).description("?????????"),
                                fieldWithPath("author").type(JsonFieldType.STRING).description("????????? ????????? ??????"),
                                fieldWithPath("pay").type(JsonFieldType.NUMBER).description("????????? ????????? ??????"),
                                fieldWithPath("time").type(JsonFieldType.NUMBER).description("????????????(?????????)"),
                                fieldWithPath("schoolName").type(JsonFieldType.STRING).description("?????? ??????"),
                                fieldWithPath("dueDate").type(JsonFieldType.STRING).description("????????????"),
                                fieldWithPath("doDate").type(JsonFieldType.STRING).description("??????/?????? ??????"),
                                fieldWithPath("likes").type(JsonFieldType.NUMBER).description("????????? ??????"),
                                fieldWithPath("isMine").type(JsonFieldType.BOOLEAN).description("????????? ????????? ????????? ???????????? ????????? true"),
                                fieldWithPath("isLikeMine").type(JsonFieldType.BOOLEAN).description("????????? ????????? ????????? ???????????? ???????????? true"),
                                fieldWithPath("images[].url").type(JsonFieldType.STRING).description("????????? url")
                        )
                ));
    }

    @Test
    @DisplayName("????????? ?????? ?????????")
    public void update() throws Exception {
        InputStream is1 = new ClassPathResource("mock/images/enjoy.png").getInputStream();
        MockMultipartFile mockFile1 = new MockMultipartFile("files", "mock_file1.jpg", "image/jpg", is1.readAllBytes());

        given(boardService.update(any(), any(), any())).willReturn(board1);

        ResultActions results = mvc.perform(RestDocumentationRequestBuilders
                .fileUpload("/api/board/{boardId}", 1)
                .file(mockFile1)
                .param("title", "???????????? ?????? ??????!!!")
                .param("content", "?????? ????????????")
                .param("place", "????????? ????????????")
                .param("phoneNum", "010-1234-1234")
                .param("schoolId", "1")
                .param("pay", "33000")
                .param("time", "60")
                .param("dueDate", "2021-08-21")
                .param("doDate", "2021-08-25T09:30")
                .param("fileChanged", "true")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("board-update",
                        pathParameters(
                                parameterWithName("boardId").description("????????? ????????? ?????????")
                        ),
                        requestParameters(
                                parameterWithName("title").description("??????"),
                                parameterWithName("content").description("??????"),
                                parameterWithName("place").description("??????"),
                                parameterWithName("pay").description("??????"),
                                parameterWithName("time").description("????????????(?????????)"),
                                parameterWithName("phoneNum").description("?????????"),
                                parameterWithName("schoolId").description("?????? ?????????"),
                                parameterWithName("fileChanged").description("?????????????????? ???????????? ?????????????????? true ????????? ????????? false"),
                                parameterWithName("dueDate").description("????????????"),
                                parameterWithName("doDate").description("??????/?????? ??????")),
                        requestParts(
                                partWithName("files").description("???????????? ????????? ??????")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("????????? ????????? ?????????")
                        )
                ));
    }

    @Test
    @DisplayName("????????? ?????? ?????????")
    public void delete() throws Exception {
        ResultActions results = mvc.perform(RestDocumentationRequestBuilders
                .delete("/api/board/{boardId}", 1));

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("board-delete",
                        pathParameters(
                                parameterWithName("boardId").description("????????? ????????? ?????????")
                        )
                ));
    }

    @Test
    @DisplayName("????????? ????????? ?????????")
    public void createLike() throws Exception {
        ResultActions result = mvc.perform(RestDocumentationRequestBuilders
                .post("/api/board/like/{boardId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
        );

        result.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("board-like",
                        pathParameters(
                                parameterWithName("boardId").description("????????? ??? ????????? ?????????")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("????????? ?????? ????????? ?????????")
                        ))
                );
    }
}