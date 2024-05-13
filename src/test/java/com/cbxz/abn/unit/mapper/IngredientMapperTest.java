package com.cbxz.abn.unit.mapper;

import com.cbxz.abn.service.mapper.IngredientMapper;
import com.cbxz.abn.utils.TestDataBuilder;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IngredientMapperTest {


    @Test
    public void toIngredientDto() {
        val ingredient = TestDataBuilder.buildIngredient();
        val result = IngredientMapper.toIngredientDto(TestDataBuilder.buildIngredient());
        assertThat(result.id()).isEqualTo(ingredient.getId());
        assertThat(result.name()).isEqualTo(ingredient.getName());
    }

    @Test
    public void toIngredient() {
        val ingredient = TestDataBuilder.buildIngredientDto();
        val result = IngredientMapper.toIngredientDto(TestDataBuilder.buildIngredient());
        assertThat(result.id()).isEqualTo(ingredient.id());
        assertThat(result.name()).isEqualTo(ingredient.name());
    }
}
