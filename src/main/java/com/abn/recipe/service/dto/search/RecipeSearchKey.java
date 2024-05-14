package com.abn.recipe.service.dto.search;

import com.abn.recipe.domain.RecipeIngredient;
import lombok.Getter;

@Getter
public enum RecipeSearchKey {
    NAME("RECIPE", "name", String.class, null, null),
    INGREDIENT_NAME("INGREDIENT", "name", String.class, "ingredient", RecipeIngredient.class),
    SERVES("RECIPE", "serves", Integer.class, null, null),
    INSTRUCTIONS("RECIPE", "instructions", String.class, null, null),
    IS_VEGETARIAN("RECIPE", "isVegetarian", Boolean.class, null, null);

    RecipeSearchKey(String tableName, String keyName, Class<?> type, String joinTable, Class<?> manyToManyTable) {
        this.keyName = keyName;
        this.tableName = tableName;
        this.type = type;
        this.joinTable = joinTable;
        this.manyToManyTable = manyToManyTable;
    }

    private final String tableName;
    private final String keyName;
    private final Class<?> type;
    private final String joinTable;
    private final Class<?> manyToManyTable;

}
