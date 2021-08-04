package com.miniprac.user.dto;

import lombok.*;

public class UserRequest {
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Signup {
        private String name;

        private String email;

        private String password;
    }
}
