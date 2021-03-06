package com.miniprac.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniprac.board.domain.Board;
import com.miniprac.board.domain.BoardFile;
import com.miniprac.common.dto.FileDto;
import com.miniprac.user.domain.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.miniprac.board.domain.Board.isLikeMine;

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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
        private LocalDateTime doDate;

        public static BoardResponse.GetList build(Board board) {
            return GetList.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .place(board.getPlace())
                    .author(board.getCreatedBy().getName())
                    .dueDate(board.getDueDate())
                    .doDate(board.getDoDate())
                    .contact(board.getPhoneNum())
                    .likes(board.getBoardLikes().size())
                    .build();
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetOne {
        private Long id;
        private String title;
        private String content;
        private String place;
        private String author;
        private String contact;
        private String schoolName;
        private Integer pay;
        private Integer time;
        private Integer likes;
        private Boolean isMine;
        private Boolean isLikeMine;
        private List<FileDto> images;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        private LocalDate dueDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
        private LocalDateTime doDate;

        public static BoardResponse.GetOne build(Board board, User user) {
            return GetOne.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .place(board.getPlace())
                    .author(board.getCreatedBy().getName())
                    .pay(board.getPay())
                    .time(board.getTime())
                    .contact(board.getPhoneNum())
                    .isMine(board.getCreatedBy().getId().equals(user.getId()))
                    .isLikeMine(isLikeMine(board, user))
                    .dueDate(board.getDueDate())
                    .doDate(board.getDoDate())
                    .schoolName(board.getSchool().getName())
                    .likes(board.getBoardLikes().size())
                    .images(board.getBoardFileList().stream()
                            .map(BoardFile::getFile)
                            .map(FileDto::build)
                            .collect(Collectors.toList()))
                    .build();
        }


    }
}

