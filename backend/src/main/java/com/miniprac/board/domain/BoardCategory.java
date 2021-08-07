package com.miniprac.board.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BoardCategoryType type;

    public static BoardCategory create(String type) {
        return BoardCategory.builder().type(BoardCategoryType.from(type)).build();
    }
}
