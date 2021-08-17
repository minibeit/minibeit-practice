package com.miniprac.board.domain;

import com.miniprac.board.dto.BoardRequest;
import com.miniprac.common.domain.BaseEntity;
import com.miniprac.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    private String title;

    private String content;

    private String place;

    @Column(name = "phone_num")
    private String phoneNum;

    private String school;

    private int pay;

    private int time;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "do_date")
    private LocalDateTime doDate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<BoardFile> boardFileList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private BoardCategory category;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<BoardLike> boardLikes = new ArrayList<>();

    private void addFiles(List<BoardFile> boardFiles) {
        boardFiles.forEach(boardFile -> {
            this.boardFileList.add(boardFile);
            boardFile.setBoard(this);
        });
    }

    public static Board create(BoardRequest.Create request, BoardCategory category, List<BoardFile> boardFiles) {
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .place(request.getPlace())
                .phoneNum(request.getPhoneNum())
                .school(request.getSchool())
                .pay(request.getPay())
                .time(request.getTime())
                .dueDate(request.getDueDate())
                .doDate(request.getDoDate())
                .category(category)
                .build();
        board.addFiles(boardFiles);

        return board;
    }

    public void update(BoardRequest.Update request, BoardCategory category) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.place = request.getPlace();
        this.phoneNum = request.getPhoneNum();
        this.pay = request.getPay();
        this.time = request.getTime();
        this.doDate = request.getDoDate();
        this.dueDate = request.getDueDate();
        this.category = category;
    }

    public static Boolean isLikeMine(Board board, User user) {
        return board.getBoardLikes().stream().anyMatch(boardLike -> boardLike.getCreatedBy().getId().equals(user.getId()));
    }
}
