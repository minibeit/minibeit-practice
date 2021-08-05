package com.miniprac.board.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miniprac.common.domain.BaseEntity;
import com.miniprac.user.domain.Role;
import com.miniprac.user.domain.SignupProvider;
import com.miniprac.user.domain.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
@Table(name = "board")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;

    private String content;

    private String place;

    private String phoneNum;

    private int pay;

    //모집 기간
    private String recruitmentPeriod;

    // 실험/ 설문 날짜 시간
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;


    public void settingCategoryType(Category category){
         this.category = category;
    }

    public static Board create(String title, String content, String place, String phoneNum, int pay, String recruitmentPeriod, LocalDateTime startDate) {
        return Board.builder()
                .title(title)
                .content(content)
                .place(place)
                .phoneNum(phoneNum)
                .pay(pay)
                .recruitmentPeriod(recruitmentPeriod)
                .startDate(startDate)
                .build();
    }
}
