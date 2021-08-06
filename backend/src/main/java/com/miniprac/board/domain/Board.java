package com.miniprac.board.domain;

import com.miniprac.board.dto.BoardRequest;
import com.miniprac.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "board")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private BoardCategory category;

    private String title;

    private String content;

    private String place;

    @Column(name = "phone_num")
    private String phoneNum;

    private int pay;

    //마감 날짜
    @Column(name = "due_date")
    private LocalDate dueDate;

    // 실험/ 설문 날짜 시간
    @Column(name = "do_date")
    private LocalDateTime doDate;

    public static Board create(BoardRequest.Create request, BoardCategory category) {
        return Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .place(request.getPlace())
                .phoneNum(request.getPhoneNum())
                .pay(request.getPay())
                .dueDate(request.getDueDate())
                .doDate(request.getDoDate())
                .category(category)
                .build();
    }

    public Board update(BoardRequest.Update request, BoardCategory category) {
        return Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .place(request.getPlace())
                .phoneNum(request.getPhoneNum())
                .pay(request.getPay())
                .dueDate(request.getDueDate())
                .doDate(request.getDoDate())
                .category(category)
                .build();
    }
}
