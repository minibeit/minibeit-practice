package com.miniprac.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniprac.security.token.Token;
import lombok.*;

import java.time.LocalDateTime;

public class UserResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OnlyId {
        private Long id;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Login {
        private Long id;
        private String name;
        private String accessToken;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime accessTokenExpiredAt;

        public static Login build(Long id, String name, Token accessToken) {
            return Login.builder()
                    .id(id)
                    .name(name)
                    .accessToken(accessToken.getToken())
                    .accessTokenExpiredAt(accessToken.getExpiredAt())
                    .build();
        }
    }
}
