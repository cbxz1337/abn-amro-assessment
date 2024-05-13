package com.cbxz.abn.controller.recipe;


import com.cbxz.abn.controller.model.request.recipe.CreateRecipeRequest;
import com.cbxz.abn.controller.model.request.RecipeSearchRequest;
import com.cbxz.abn.controller.model.request.recipe.RecipeUpdateRequest;
import com.cbxz.abn.controller.model.response.BaseCreatedResponse;
import com.cbxz.abn.controller.model.response.RecipeResponse;
import com.cbxz.abn.service.dto.recipe.PaginatedRecipeDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Recipe controller")
@RequestMapping("api/v1/recipe/")
@Schema
public interface RecipeController {

    @PostMapping("getByFilter")
    PaginatedRecipeDto getRecipesByFilter(RecipeSearchRequest request);

    @GetMapping("{id}")
    RecipeResponse getById(@PathVariable Long id);

    @PostMapping
    BaseCreatedResponse createRecipe(CreateRecipeRequest request);

    @DeleteMapping("{id}")
    void deleteRecipe(@PathVariable(name = "id") Long id);

    @PutMapping()
    void updateRecipe(@RequestBody RecipeUpdateRequest request);
}
