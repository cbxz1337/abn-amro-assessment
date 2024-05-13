package com.cbxz.abn.unit.controller;

import com.cbxz.abn.controller.recipe.RecipeControllerImpl;
import com.cbxz.abn.service.dto.recipe.CreateRecipeDto;
import com.cbxz.abn.service.dto.recipe.PaginatedRecipeDto;
import com.cbxz.abn.service.recipe.RecipeService;
import com.cbxz.abn.service.search.service.RecipeSearchService;
import com.cbxz.abn.utils.TestDataBuilder;
import lombok.val;
import org.junit.Assert;
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
public class RecipeControllerUnitTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private RecipeSearchService recipeSearchService;

    @InjectMocks
    private RecipeControllerImpl recipeController;

    private final long id = 1L;

    @Test
    public void successfulCreationOfRecipe() {
        when(recipeService.createRecipe(Mockito.any(CreateRecipeDto.class))).thenReturn(id);
        val request = TestDataBuilder.buildCreateRecipeRequest();
        val response = recipeController.createRecipe(request);
        assertThat(response).isNotNull();
        assertThat(response.id().longValue()).isEqualTo(id);
    }

    @Test
    public void successfulSearchForRecipe() {
        val request = TestDataBuilder.recipeSearchRequest();
        when(recipeSearchService.getPaginatedRecipeDtos(Mockito.any())).thenReturn(
                PaginatedRecipeDto.builder()
                        .total(10)
                        .recipes(List.of(TestDataBuilder.recipeResponseDto(id)))
                        .build()
        );
        val response = recipeController.getRecipesByFilter(request);
        Assert.assertEquals(response.recipes().getFirst().id().longValue(), id);
    }
}
