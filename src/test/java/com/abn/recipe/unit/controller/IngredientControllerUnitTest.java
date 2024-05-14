package com.abn.recipe.unit.controller;

import com.abn.recipe.controller.ingredient.IngredientControllerImpl;
import com.abn.recipe.service.dto.ingredient.IngredientDto;
import com.abn.recipe.service.dto.ingredient.PaginatedIngredientDto;
import com.abn.recipe.service.ingredient.IngredientService;
import com.abn.recipe.utils.TestDataBuilder;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerUnitTest {

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private IngredientControllerImpl ingredientController;

   private final long id = 1L;

    @Test
    public void successfulCreationOfIngredient() {
        when(ingredientService.create(Mockito.any(IngredientDto.class))).thenReturn(id);
        val request = TestDataBuilder.buildIngredientRequest();
        val response = ingredientController.createIngredient(request);
        assertThat(response).isNotNull();
        assertThat(response.id().longValue()).isEqualTo(id);
    }

    @Test
    public void successfulListIngredients() {
        when(ingredientService.listIngredients(Mockito.any())).thenReturn(
                PaginatedIngredientDto.builder()
                        .total(10)
                        .ingredients(List.of(TestDataBuilder.ingredientResponseDto(id)))
                        .build()
        );
        val response = ingredientController.listIngredients(0, 10);
        assertThat(response.ingredients().getFirst().id().longValue()).isEqualTo(id);
    }
}
