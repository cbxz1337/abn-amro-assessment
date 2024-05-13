package com.cbxz.abn.integration.controllers;

import com.cbxz.abn.controller.model.request.recipe.CreateRecipeRequest;
import com.cbxz.abn.domain.Ingredient;
import com.cbxz.abn.repository.ingredient.IngredientRepository;
import com.cbxz.abn.repository.recipe.RecipeRepository;
import com.cbxz.abn.utils.TestDataBuilder;
import com.jayway.jsonpath.JsonPath;
import jakarta.transaction.Transactional;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RecipeControllerTest extends BaseTest{

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    @Transactional
    @Rollback
    public void createRecipe() throws Exception {
        val ingredient = Ingredient.builder().name("Ing1").build();
        val ingredient1 = Ingredient.builder().name("Ing2").build();
        val createdIng = ingredientRepository.save(ingredient);
        val createdIng1 = ingredientRepository.save(ingredient1);
        ingredientRepository.save(ingredient);
        val request = new CreateRecipeRequest(
                13,
                "Shaorma",
                "Cook it well",
                List.of(createdIng.getId(), createdIng1.getId()),
                false
        );
        val result = mockMvc.perform(post("/api/v1/recipe/")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        val id = (Integer) JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        Assert.assertTrue(recipeRepository.findById(id.longValue()).isPresent());
    }

    @Test
    @Transactional
    @Rollback
    public void getRecipeById() throws Exception {
        val recipe = TestDataBuilder.buildRecipe();
        val id = recipeRepository.save(recipe).getId();
        mockMvc.perform(get(String.format("/api/v1/recipe/%s", id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(recipe.getId()))
                .andExpect(jsonPath("$.name").value(recipe.getName()))
                .andExpect(jsonPath("$.serves").value(recipe.getServes()))
                .andExpect(jsonPath("$.instructions").value(recipe.getInstructions()));

    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Search request 2 different predicates")
    public void searchRecipeRequest() throws Exception {
        val ingredient = ingredientRepository.save(TestDataBuilder.buildIngredient());
        val recipe = TestDataBuilder.buildRecipe(List.of(ingredient));
        val recipeId = recipeRepository.save(recipe).getId();
        val recipeSearchRequest = TestDataBuilder.recipeSearchRequest(recipe, ingredient.getName());
        mockMvc.perform(post("/api/v1/recipe/getByFilter")
                        .content(objectMapper.writeValueAsString(recipeSearchRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipes[0].id").value(recipeId))
                .andExpect(jsonPath("$.recipes[0].name").value(recipe.getName()))
                .andExpect(jsonPath("$.recipes[0].serves").value(recipe.getServes()))
                .andExpect(jsonPath("$.recipes[0].ingredients[0].id").value(ingredient.getId()))
                .andExpect(jsonPath("$.recipes[0].instructions").value(recipe.getInstructions()));
    }

    @Test
    @DisplayName("Trying to get non existing recipe")
    public void notFoundOnGetRequest() throws Exception {
        val id = recipeRepository.save(TestDataBuilder.buildRecipe(
                List.of(TestDataBuilder.buildIngredient()))
        ).getId();
        recipeRepository.deleteById(id);
        mockMvc.perform(get(String.format("/api/v1/recipe/%s", id)))
                .andExpect(status().isNotFound());

    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Successful delete")
    public void successOnDeleteRequest() throws Exception {
        val recipe = TestDataBuilder.buildRecipe();
        val id = recipeRepository.save(recipe).getId();
        mockMvc.perform(delete(String.format("/api/v1/recipe/%s", id)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Trying to delete non existing recipe")
    @Transactional
    @Rollback
    public void notFoundOnDeleteRequest() throws Exception {
        val recipe = TestDataBuilder.buildRecipe();
        val savedId = recipeRepository.save(recipe).getId();
        recipeRepository.deleteById(savedId);
        mockMvc.perform(delete(String.format("/api/v1/recipe/%s", savedId)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Successful update on recipe")
    @Transactional
    @Rollback
    public void successOnPutRequest() throws Exception {
        val recipe = TestDataBuilder.buildRecipe();
        val ingredient = TestDataBuilder.buildIngredient();
        val ingredientId = ingredientRepository.save(ingredient).getId();
        val id = recipeRepository.save(recipe).getId();
        val request = TestDataBuilder.buildRecipeUpdateRequest(id, List.of(ingredientId));
        mockMvc.perform(put("/api/v1/recipe/")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



    @Test
    @DisplayName("Trying to update non existing recipe")
    @Transactional
    @Rollback
    public void notFoundOnPutRequest() throws Exception {
        val ingredient = TestDataBuilder.buildIngredient();
        val savedIngredient = ingredientRepository.save(ingredient);
        val recipe = TestDataBuilder.buildRecipe(List.of(savedIngredient));
        val savedId = recipeRepository.save(recipe).getId();
        recipeRepository.deleteById(savedId);
        val request = TestDataBuilder.buildRecipeUpdateRequest(savedId, List.of(savedIngredient.getId()));
        mockMvc.perform(put("/api/v1/recipe/")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
