package com.abn.recipe.controller.model.response;

import lombok.Getter;

import java.util.Map;

@Getter
public enum SearchKeyTypeResponse {
    INTEGER(Integer.class),
    STRING(String.class),
    BOOLEAN(Boolean.class);

    private final Class<?> type;

    private static final Map<Class<?>, SearchKeyTypeResponse> TYPE_MAP = Map.of(
            Integer.class, INTEGER,
            String.class, STRING,
            Boolean.class, BOOLEAN
    );

    SearchKeyTypeResponse(Class<?> type) {
        this.type = type;
    }

    public static SearchKeyTypeResponse valueBy(Class<?> type) {
        return TYPE_MAP.get(type);
    }
}
