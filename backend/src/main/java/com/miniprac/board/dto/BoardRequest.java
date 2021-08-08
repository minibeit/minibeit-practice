package com.miniprac.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public class BoardRequest {
    @Setter
    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Create {
        private String title;
        private String content;
        private String place;
        private String phoneNum;
        private String category;
        private int time;
        private int pay;
        private List<MultipartFile> files;

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dueDate;
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime doDate;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class GetListByCategory {
        private String category;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Update {
        private String title;
        private String content;
        private String place;
        private String phoneNum;
        private String category;
        private int pay;
        private int time;

        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dueDate;
        //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime doDate;
    }

}
