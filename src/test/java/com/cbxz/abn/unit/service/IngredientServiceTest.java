package com.cbxz.abn.unit.service;

import com.cbxz.abn.domain.Ingredient;
import com.cbxz.abn.exception.NotFoundException;
import com.cbxz.abn.repository.IngredientRepository;
import com.cbxz.abn.service.dto.ingredient.IngredientDto;
import com.cbxz.abn.service.ingredient.IngredientServiceImpl;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;

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
}
