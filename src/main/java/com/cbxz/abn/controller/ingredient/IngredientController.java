package com.cbxz.abn.controller.ingredient;

import com.cbxz.abn.controller.model.request.ingredient.CreateIngredientRequest;
import com.cbxz.abn.controller.model.request.ingredient.UpdateIngredientRequest;
import com.cbxz.abn.controller.model.response.BaseCreatedResponse;
import com.cbxz.abn.controller.model.response.IngredientResponse;
import com.cbxz.abn.controller.model.response.PaginatedIngredientResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Ingredient controller")
@RequestMapping("api/v1/ingredient/")
@Schema
public interface IngredientController {

    @GetMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully obtain ingredient"),
            @ApiResponse(responseCode = "404", description = "Ingredient not found")
    })
    IngredientResponse getIngredientById(@PathVariable(name = "id") Long id);

    @GetMapping("offset/{offset}/limit/{limit}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully list ingredients"),
    })
    PaginatedIngredientResponse listIngredients(
            @PathVariable(name = "offset") Integer offset,
            @PathVariable(name = "limit") Integer limit);

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully create ingredient"),
    })
    BaseCreatedResponse createIngredient(@RequestBody @Valid CreateIngredientRequest request);

    @PutMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully update ingredient"),
            @ApiResponse(responseCode = "404", description = "Ingredient not found")
    })
    void updateIngredient(@RequestBody @Valid UpdateIngredientRequest request);

    @DeleteMapping("{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete ingredient"),
            @ApiResponse(responseCode = "404", description = "Ingredient not found")
    })
    void deleteIngredient(@PathVariable(name = "id") Long id);


}
