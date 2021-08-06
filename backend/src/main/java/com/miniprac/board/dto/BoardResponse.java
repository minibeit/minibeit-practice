package com.miniprac.board.dto;

import com.miniprac.board.domain.Board;
import lombok.*;

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

//    private String title;
//
//    private String content;
//
//    private String place;
//
//    private String phoneNum;
//
//    private String categoryType;
//
//    private int pay;
//
//    //마감 날짜
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//    private LocalDate dueDate;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
//    private LocalDateTime doDate;
}
