package com.miniprac.school.dto;

import com.miniprac.school.domain.School;
import lombok.*;

public class SchoolResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetList {
        private Long id;
        private String name;

        public static SchoolResponse.GetList build(School school) {
            return SchoolResponse.GetList.builder()
                    .id(school.getId())
                    .name(school.getName())
                    .build();
        }
    }
}
