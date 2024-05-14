package com.abn.recipe.controller.recipe;

import com.abn.recipe.controller.mapper.RecipeMapper;
import com.abn.recipe.controller.model.request.recipe.CreateRecipeRequest;
import com.abn.recipe.controller.model.request.RecipeSearchRequest;
import com.abn.recipe.controller.model.request.recipe.RecipeUpdateRequest;
import com.abn.recipe.controller.model.response.BaseCreatedResponse;
import com.abn.recipe.controller.model.response.RecipeResponse;
import com.abn.recipe.controller.model.response.SearchKeyResponse;
import com.abn.recipe.service.dto.search.RecipeSearchKey;
import com.abn.recipe.service.recipe.RecipeService;
import com.abn.recipe.service.search.service.RecipeSearchService;
import com.abn.recipe.service.dto.recipe.PaginatedRecipeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return RecipeMapper.toRecipeResponse(recipeService.getById(id));
    }

    @Override
    public BaseCreatedResponse createRecipe(@RequestBody @Valid CreateRecipeRequest request) {
        return BaseCreatedResponse.of(recipeService.createRecipe(RecipeMapper.toCreateRecipeDto(request)));
    }

    @Override
    public void deleteRecipe(Long id) {
        recipeService.deleteById(id);
    }

    @Override
    public void updateRecipe(@RequestBody @Valid RecipeUpdateRequest request) {
        recipeService.update(RecipeMapper.toUpdateRecipeDto(request));
    }

    @Override
    public List<SearchKeyResponse> listSearchKeys() {
        return RecipeMapper.keyResponseList(RecipeSearchKey.values());
    }
}
