package com.miniprac.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponse {

    private String title;

    private String content;

    private String place;

    private String phoneNum;

    private String categoryType;
    private int pay;

    //마감 날짜
    private String deadline;

    private LocalDateTime startDate;
}
