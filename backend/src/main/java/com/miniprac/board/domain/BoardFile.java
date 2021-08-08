package com.miniprac.board.domain;

import com.miniprac.common.domain.BaseEntity;
import com.miniprac.file.domain.File;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "board_file")
public class BoardFile extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id")
    private File file;

    public void setBoard(Board board) {
        this.board = board;
    }

    public static BoardFile create(File file) {
        return BoardFile.builder()
                .file(file)
                .build();
    }
}
