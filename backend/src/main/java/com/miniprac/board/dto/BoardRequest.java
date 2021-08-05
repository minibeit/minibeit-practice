package com.miniprac.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniprac.board.domain.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequest {

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
