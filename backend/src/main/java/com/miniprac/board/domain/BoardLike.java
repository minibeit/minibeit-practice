package com.miniprac.board.domain;

import com.miniprac.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "board_like")
public class BoardLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private void addBoard(Board board) {
        board.getBoardLikes().add(this);
        this.board = board;
    }

    public static BoardLike create(Board board) {
        BoardLike boardLike = BoardLike.builder().board(board).build();
        boardLike.addBoard(board);

        return boardLike;
    }
}
