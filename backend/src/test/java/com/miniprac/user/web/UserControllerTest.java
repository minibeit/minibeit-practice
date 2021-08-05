package com.miniprac.user.web;

import com.miniprac.MvcTest;
import com.miniprac.security.token.Token;
import com.miniprac.user.domain.User;
import com.miniprac.user.dto.UserRequest;
import com.miniprac.user.dto.UserResponse;
import com.miniprac.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest extends MvcTest {
    @MockBean
    private UserService userService;

    @Test
    @DisplayName("회원가입 문서화")
    public void signup() throws Exception {
        User user = User.builder().id(1L).build();
        UserRequest.Signup request = UserRequest.Signup.builder()
                .email("test@test.com")
                .name("테스터")
                .password("1234")
                .build();

        given(userService.create(any())).willReturn(user);

        ResultActions results = mockMvc.perform(
                post("/api/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request))
        );

        results.andExpect(status().isCreated())
                .andDo(document("user-signup",
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("회원가입할 이메일"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("회원가입할 이름"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("회원가입할 비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("회원가입한 유저 식별자")
                        )
                ));
    }

    @Test
    @DisplayName("로그인 테스트")
    public void login() throws Exception {
        UserRequest.Login request = UserRequest.Login.builder().email("test@test.com").password("1234").build();
        UserResponse.Login response = UserResponse.Login.build(1L, "테스터", Token.builder().token("accessToken").expiredAt(LocalDateTime.now()).build());

        given(userService.login(any())).willReturn(response);

        ResultActions results = mockMvc.perform(
                post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(request))
        );

        results.andExpect(status().isOk())
                .andDo(print())
                .andDo(document("user-login",
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("비밀번호")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("로그인한 유저 식별자"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("로그인한 유저 식별자"),
                                fieldWithPath("accessToken").type(JsonFieldType.STRING).description("accessToken"),
                                fieldWithPath("accessTokenExpiredAt").type(JsonFieldType.STRING).description("accessToken 만료일")
                        )
                ));
    }
}