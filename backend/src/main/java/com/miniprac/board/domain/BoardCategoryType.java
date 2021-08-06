package com.miniprac.board.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum BoardCategoryType {
    SURVEY, EXPERIMENT;

    @JsonCreator
    public static BoardCategoryType from(String category) {
        return BoardCategoryType.valueOf(category.toUpperCase());
    }
}
