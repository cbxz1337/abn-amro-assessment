package com.abn.recipe.unit.service;

import com.abn.recipe.domain.Ingredient;
import com.abn.recipe.exception.NotFoundException;
import com.abn.recipe.repository.ingredient.IngredientRepository;
import com.abn.recipe.repository.recipe.RecipeIngredientRepository;
import com.abn.recipe.service.dto.Pagination;
import com.abn.recipe.service.dto.ingredient.IngredientDto;
import com.abn.recipe.service.ingredient.IngredientServiceImpl;
import com.abn.recipe.utils.TestDataBuilder;
import lombok.val;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

    @Mock
    private RecipeIngredientRepository recipeIngredientRepository;

    @InjectMocks
    private IngredientServiceImpl ingredientService;


    @Test
    public void successDelete() {
        Mockito.when(ingredientRepository.existsById(
                        Mockito.anyLong()))
                .thenReturn(true);
        ingredientService.deleteById(1L);
        Mockito.verify(ingredientRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    @Test
    public void successUpdate() {
        Mockito.when(ingredientRepository.existsById(
                        Mockito.anyLong()))
                .thenReturn(true);
        ingredientService.update(IngredientDto.builder().id(10L).name(RandomStringUtils.random(10)).build());
        Mockito.verify(
                ingredientRepository,
                Mockito.times(1)).save(Mockito.any(Ingredient.class)
        );
    }

    @Test
    public void notFoundForDelete() {
        Mockito.when(ingredientRepository.existsById(Mockito.anyLong())).thenReturn(false);
        Assertions.assertThrows(NotFoundException.class, () -> ingredientService.deleteById(1L));
    }

    @Test
    public void listIngredients() {
        Mockito.when(ingredientRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(
                new PageImpl<>(List.of(TestDataBuilder.buildIngredient()))
        );
        val res = ingredientService.listIngredients(Pagination.builder().limit(10).offset(0).build());
        assertThat(res.total()).isEqualTo(1);
        assertThat(res.pagination().limit()).isEqualTo(10);
        assertThat(res.pagination().offset()).isEqualTo(0);
    }
}
