package com.abn.recipe.unit.repository;

import com.abn.recipe.repository.ingredient.IngredientRepository;
import com.abn.recipe.utils.TestDataBuilder;
import jakarta.transaction.Transactional;
import lombok.val;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    @Transactional
    @Rollback
    @DisplayName("Test save ingredient")
    public void saveRecipeSuccess(){
        val ingredient = TestDataBuilder.buildIngredient();
        val savedIngredient = ingredientRepository.save(ingredient);
        assertThat(savedIngredient).isNotNull();
        assertThat(savedIngredient.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Test delete ingredient")
    public void deleteRecipe(){
        val ingredient = TestDataBuilder.buildIngredient();
        val savedIngredient = ingredientRepository.save(ingredient);
        ingredientRepository.deleteById(savedIngredient.getId());
        assertThat(ingredientRepository.findById(savedIngredient.getId())).isEmpty();
    }
}
