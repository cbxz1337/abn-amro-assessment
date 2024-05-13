package com.cbxz.abn.unit.controller;

import com.cbxz.abn.controller.ingredient.IngredientControllerImpl;
import com.cbxz.abn.service.dto.ingredient.IngredientDto;
import com.cbxz.abn.service.dto.ingredient.PaginatedIngredientDto;
import com.cbxz.abn.service.ingredient.IngredientService;
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

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerUnitTest {

    @Mock
    private IngredientService ingredientService;

    @InjectMocks
    private IngredientControllerImpl ingredientController;

    @Test
    public void successfulCreationOfIngredient() {
        when(ingredientService.create(Mockito.any(IngredientDto.class))).thenReturn(1L);
        val request = TestDataBuilder.buildIngredientRequest();
        val response = ingredientController.createIngredient(request);
        Assert.assertNotNull(response);
        Assert.assertEquals(response.id().longValue(), 1L);
    }

    @Test
    public void successfulListIngredients() {
        when(ingredientService.listIngredients(Mockito.any())).thenReturn(
                PaginatedIngredientDto.builder()
                        .total(10)
                        .ingredients(List.of(TestDataBuilder.ingredientResponseDto(1L)))
                        .build()
        );
        val response = ingredientController.listIngredients(0, 10);
        Assert.assertEquals(response.ingredients().getFirst().id().longValue(), 1L);
    }
}
