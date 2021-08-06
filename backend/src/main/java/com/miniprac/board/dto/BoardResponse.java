package com.miniprac.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniprac.board.domain.Board;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BoardResponse {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class OnlyId {
        private Long id;

        public static BoardResponse.OnlyId build(Board board) {
            return BoardResponse.OnlyId.builder().id(board.getId()).build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetList {
        private Long id;

        private String title;

        private String place;

        private String author;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate dueDate;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime doDate;

        public static BoardResponse.GetList build(Board board) {
            return GetList.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .place(board.getPlace())
                    .author(board.getCreatedBy().getName())
                    .dueDate(board.getDueDate())
                    .doDate(board.getDoDate())
                    .build();
        }
    }
}
