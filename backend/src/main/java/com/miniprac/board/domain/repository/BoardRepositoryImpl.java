package com.miniprac.board.domain.repository;

import com.miniprac.board.domain.Board;
import com.miniprac.board.dto.BoardRequest;
import com.miniprac.school.domain.School;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

import static com.miniprac.board.domain.QBoard.board;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Board> findAllBySchoolAndDate(BoardRequest.GetListBySchoolAndDate request, School school, Pageable pageable) {
        JPAQuery<Board> query = queryFactory.selectFrom(board)
                .where(board.school.eq(school)
                        .and((board.doDate.year().eq(request.getDate().getYear())
                                .and(board.doDate.month().eq(request.getDate().getMonthValue()))
                                .and(board.doDate.dayOfMonth().eq(request.getDate().getDayOfMonth())))
                                .and(board.dueDate.after(LocalDate.now()))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        QueryResults<Board> results = query.fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
