package com.cbxz.abn.controller.recipe;

import com.cbxz.abn.controller.ApiMapper;
import com.cbxz.abn.controller.model.request.recipe.CreateRecipeRequest;
import com.cbxz.abn.controller.model.request.RecipeSearchRequest;
import com.cbxz.abn.controller.model.request.recipe.RecipeUpdateRequest;
import com.cbxz.abn.controller.model.response.BaseCreatedResponse;
import com.cbxz.abn.controller.model.response.RecipeResponse;
import com.cbxz.abn.service.recipe.RecipeService;
import com.cbxz.abn.service.search.service.RecipeSearchService;
import com.cbxz.abn.service.dto.recipe.PaginatedRecipeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecipeControllerImpl implements RecipeController {

    private final RecipeSearchService recipeSearchService;
    private final RecipeService recipeService;

    @Override
    public PaginatedRecipeDto getRecipesByFilter(@RequestBody @Valid RecipeSearchRequest request) {
        return recipeSearchService.getPaginatedRecipeDtos(request);
    }

    @Override
    public RecipeResponse getById(Long id) {
        return ApiMapper.toRecipeResponse(recipeService.getById(id));
    }

    @Override
    public BaseCreatedResponse createRecipe(@RequestBody @Valid CreateRecipeRequest request) {
        return BaseCreatedResponse.of(recipeService.createRecipe(ApiMapper.toCreateRecipeDto(request)));
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeService.deleteById(id);
    }

    @Override
    public void updateRecipe(@RequestBody @Valid RecipeUpdateRequest request) {
        recipeService.update(ApiMapper.toUpdateRecipeDto(request));
    }
}
