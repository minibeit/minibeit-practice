package com.miniprac.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniprac.board.domain.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardInfo {

    private String title;

    private String content;

    private String place;

    private String phoneNum;

    private String categoryType;
    private int pay;

    //모집 기간
    private String recruitmentPeriod;

    // 실험/ 설문 날짜 시간
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
}
