package com.cbxz.abn.controller.ingredient;

import com.cbxz.abn.controller.ApiMapper;
import com.cbxz.abn.controller.model.request.ingredient.CreateIngredientRequest;
import com.cbxz.abn.controller.model.request.ingredient.UpdateIngredientRequest;
import com.cbxz.abn.controller.model.response.BaseCreatedResponse;
import com.cbxz.abn.controller.model.response.IngredientResponse;
import com.cbxz.abn.controller.model.response.PaginatedIngredientResponse;
import com.cbxz.abn.domain.RecipeIngredient;
import com.cbxz.abn.service.dto.Pagination;
import com.cbxz.abn.service.ingredient.IngredientService;
import com.cbxz.abn.service.recipe.RecipeService;
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
        return ApiMapper.toIngredientResponse(ingredientService.findById(id));
    }

    @Override
    public PaginatedIngredientResponse listIngredients(Integer offset, Integer limit) {
        val pagination = Pagination.builder().limit(limit).offset(offset).build();
        return ApiMapper.toIngredientPaginatedResponse(ingredientService.listIngredients(pagination));
    }

    @Override
    public BaseCreatedResponse createIngredient(CreateIngredientRequest request) {
        return BaseCreatedResponse.of(ingredientService.create(ApiMapper.toIngredientDto(request)));
    }

    @Override
    public void updateIngredient(UpdateIngredientRequest request) {
        ingredientService.update(ApiMapper.toIngredientDto(request));
    }

    @Override
    public void deleteIngredient(Long id) {
        ingredientService.deleteById(id);
    }
}
