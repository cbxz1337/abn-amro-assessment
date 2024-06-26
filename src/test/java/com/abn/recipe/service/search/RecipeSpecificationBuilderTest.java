package com.abn.recipe.service.search;


import com.abn.recipe.service.dto.search.JoinType;
import com.abn.recipe.service.dto.search.Operation;
import com.abn.recipe.service.dto.search.RecipeSearchKey;
import com.abn.recipe.service.dto.search.SearchCriteria;
import com.abn.recipe.service.search.specification.RecipeSpecificationBuilder;
import lombok.val;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeSpecificationBuilderTest {

    private static final List<SearchCriteria> CRITERIA = List.of(
            SearchCriteria.builder()
                    .key(RecipeSearchKey.NAME)
                    .value(RandomStringUtils.random(10))
                    .joinType(JoinType.AND)
                    .operator(Operation.INCLUDES)
                    .build(),
            SearchCriteria.builder()
                    .key(RecipeSearchKey.INGREDIENT_NAME)
                    .value(RandomStringUtils.random(10))
                    .joinType(JoinType.AND)
                    .operator(Operation.INCLUDES)
                    .build()
    );

    @Test
    public void successfulBuildSpecification() {
        val spec = new RecipeSpecificationBuilder(CRITERIA);
        assertThat(spec.build()).isNotNull();
    }

    @Test
    public void emptyBuildSpecification() {
        val spec = new RecipeSpecificationBuilder(Collections.emptyList());
       assertThat(spec.build()).isNull();
    }

}