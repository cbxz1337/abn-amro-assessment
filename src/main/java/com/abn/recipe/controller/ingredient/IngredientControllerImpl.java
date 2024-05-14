package com.abn.recipe.controller.ingredient;

import com.abn.recipe.controller.mapper.IngredientMapper;
import com.abn.recipe.controller.model.request.ingredient.CreateIngredientRequest;
import com.abn.recipe.controller.model.request.ingredient.UpdateIngredientRequest;
import com.abn.recipe.controller.model.response.BaseCreatedResponse;
import com.abn.recipe.controller.model.response.IngredientResponse;
import com.abn.recipe.controller.model.response.PaginatedIngredientResponse;
import com.abn.recipe.service.dto.Pagination;
import com.abn.recipe.service.ingredient.IngredientService;
import com.abn.recipe.service.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IngredientControllerImpl implements IngredientController {

    private final IngredientService ingredientService;
    private final RecipeService recipeService;

    @Override
    public IngredientResponse getIngredientById(Long id) {
        return IngredientMapper.toIngredientResponse(ingredientService.findById(id));
    }

    @Override
    public PaginatedIngredientResponse listIngredients(Integer offset, Integer limit) {
        val pagination = Pagination.builder().limit(limit).offset(offset).build();
        return IngredientMapper.toIngredientPaginatedResponse(ingredientService.listIngredients(pagination));
    }

    @Override
    public BaseCreatedResponse createIngredient(CreateIngredientRequest request) {
        return BaseCreatedResponse.of(ingredientService.create(IngredientMapper.toIngredientDto(request)));
    }

    @Override
    public void updateIngredient(UpdateIngredientRequest request) {
        ingredientService.update(IngredientMapper.toIngredientDto(request));
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientService.deleteById(id);
    }
}
