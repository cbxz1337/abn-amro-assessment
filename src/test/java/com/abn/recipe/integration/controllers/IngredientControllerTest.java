package com.abn.recipe.integration.controllers;

import com.abn.recipe.repository.ingredient.IngredientRepository;
import com.abn.recipe.utils.TestDataBuilder;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import jakarta.transaction.Transactional;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class IngredientControllerTest extends BaseTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    @Transactional
    @Rollback
    public void createIngredientSuccess() throws Exception{
        val ingredientCreateRequest = TestDataBuilder.buildIngredientRequest();
        val mvcResult = mockMvc.perform(post("/api/v1/ingredient/")
                        .content(objectMapper.writeValueAsString(ingredientCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        val id = (Integer) JsonPath.read(mvcResult.getResponse().getContentAsString(), "$.id");
        assertThat(ingredientRepository.findById(id.longValue())).isPresent();
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Deleting non existing ingredient")
    public void deleteNonExistingIngredient() throws Exception {
        val ingredient = TestDataBuilder.buildIngredient();
        val savedId = ingredientRepository.save(ingredient).getId();
        ingredientRepository.deleteById(savedId);
        mockMvc.perform(delete(String.format("/api/v1/ingredient/%s", savedId)))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Success deleting ingredient")
    public void deleteSuccessIngredient() throws Exception{
        val ingredient = TestDataBuilder.buildIngredient();
        val ingredientId = ingredientRepository.save(ingredient).getId();
        mockMvc.perform(delete(String.format("/api/v1/ingredient/%s", ingredientId)))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Success listing ingredient")
    public void listIngredients() throws Exception {
        val total = ingredientRepository.findAll().size();
        mockMvc.perform(get("/api/v1/ingredient/offset/{%s}/limit/{%s}", 0, 10))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total").value(total))
                .andReturn();
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Success deleting ingredient")
    public void updateIngredient() throws Exception {
        val ingredient = TestDataBuilder.buildIngredient();
        val ingredientId = ingredientRepository.save(ingredient).getId();
        val request = TestDataBuilder.buildUpdateIngredientRequest(ingredientId);
        mockMvc.perform(put("/api/v1/ingredient/")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("Success deleting ingredient")
    public void nonExistingIngredient() throws Exception {
        val ingredient = TestDataBuilder.buildIngredient();
        val savedId = ingredientRepository.save(ingredient).getId();
        ingredientRepository.deleteById(savedId);
        val request = TestDataBuilder.buildUpdateIngredientRequest(savedId);
        mockMvc.perform(put("/api/v1/ingredient/")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
