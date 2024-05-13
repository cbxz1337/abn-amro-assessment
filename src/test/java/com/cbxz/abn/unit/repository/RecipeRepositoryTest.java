package com.cbxz.abn.unit.repository;

import com.cbxz.abn.repository.recipe.RecipeRepository;
import com.cbxz.abn.utils.TestDataBuilder;
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
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;


    @Test
    @Transactional
    @Rollback
    @DisplayName("Test save recipe")
    public void saveRecipeSuccess(){
        val recipe = TestDataBuilder.buildRecipe();
        val savedRecipe = recipeRepository.save(recipe);
        assertThat(savedRecipe).isNotNull();
        assertThat(savedRecipe.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Test delete recipe")
    public void deleteRecipe(){
        val recipe = TestDataBuilder.buildRecipe();
        val savedRecipe = recipeRepository.save(recipe);
        recipeRepository.deleteById(savedRecipe.getId());
        assertThat(recipeRepository.findById(savedRecipe.getId())).isEmpty();
    }

}
