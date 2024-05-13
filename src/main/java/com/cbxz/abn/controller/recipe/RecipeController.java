package com.cbxz.abn.controller.recipe;


import com.cbxz.abn.controller.model.request.recipe.CreateRecipeRequest;
import com.cbxz.abn.controller.model.request.RecipeSearchRequest;
import com.cbxz.abn.controller.model.request.recipe.RecipeUpdateRequest;
import com.cbxz.abn.controller.model.response.BaseCreatedResponse;
import com.cbxz.abn.controller.model.response.RecipeResponse;
import com.cbxz.abn.service.dto.recipe.PaginatedRecipeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Recipe controller")
@RequestMapping("api/v1/recipe/")
@Schema
public interface RecipeController {

    @PostMapping("getByFilter")
    @Operation(description = """
            Returns pageable result of search query using column and predicates.
            List of fields to query on:
                NAME name of recipe,
                INGREDIENT_NAME name of ingredient,
                SERVES number of serves,
                INSTRUCTIONS instructions,
                IS_VEGETARIAN true or false.
            Available operations:
                INCLUDES (only applicable for String params),
                NOT_INCLUDES (only applicable for String params),
                EQUALS,
                NOT_EQUALS
            JoinOptions:
                AND,
                OR
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful search"),
            @ApiResponse(responseCode = "400", description = "Validation of arguments failed")
    })
    PaginatedRecipeDto getRecipesByFilter(RecipeSearchRequest request);

    @GetMapping("{id}")
    @Operation(description = """
                gets recipe by Id.
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recipe obtained"),
            @ApiResponse(responseCode = "404", description = "Recipe not found")
    })
    RecipeResponse getById(@PathVariable Long id);

    @PostMapping
    BaseCreatedResponse createRecipe(CreateRecipeRequest request);

    @DeleteMapping("{id}")
    @Operation(description = """
                deletes recipe by Id.
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful deletion"),
            @ApiResponse(responseCode = "404", description = "Recipe not found")
    })
    void deleteRecipe(@PathVariable(name = "id") Long id);

    @Operation(description = """
                updates recipe by Id.
            """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful update"),
            @ApiResponse(responseCode = "404", description = "Recipe not found")
    })
    @PutMapping()
    void updateRecipe(@RequestBody RecipeUpdateRequest request);
}
