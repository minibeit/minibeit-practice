package com.miniprac.user.dto;

import com.miniprac.security.token.Token;
import lombok.*;

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
        private String refreshToken;

        public static Login build(Long id, String name, Token accessToken,Token refreshToken) {
            return Login.builder()
                    .id(id)
                    .name(name)
                    .accessToken(accessToken.getToken())
                    .refreshToken(refreshToken.getToken())
                    .build();
        }
    }
}
