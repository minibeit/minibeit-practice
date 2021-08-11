package com.miniprac.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.BoardLike;
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
        private String contact;
        private int likes;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate dueDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime doDate;

        public static BoardResponse.GetList build(Board board, int like) {
            return GetList.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .place(board.getPlace())
                    .author(board.getCreatedBy().getName())
                    .dueDate(board.getDueDate())
                    .doDate(board.getDoDate())
                    .contact(board.getPhoneNum())
                    .likes(like)
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetOne {
        private Long id;
        private String category;
        private String title;
        private String content;
        private String place;
        private String author;
        private Integer pay;
        private Integer time;
        private Integer likes;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate dueDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        private LocalDateTime doDate;

        public static BoardResponse.GetOne build(Board board, int likes) {
            return GetOne.builder()
                    .id(board.getId())
                    .category(board.getCategory().getType().name())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .place(board.getPlace())
                    .author(board.getCreatedBy().getName())
                    .pay(board.getPay())
                    .time(board.getTime())
                    .dueDate(board.getDueDate())
                    .doDate(board.getDoDate())
                    .likes(likes)
                    .build();
        }
    }
}

