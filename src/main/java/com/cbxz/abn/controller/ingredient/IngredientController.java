package com.cbxz.abn.controller.ingredient;

import com.cbxz.abn.controller.model.request.ingredient.CreateIngredientRequest;
import com.cbxz.abn.controller.model.request.ingredient.UpdateIngredientRequest;
import com.cbxz.abn.controller.model.response.BaseCreatedResponse;
import com.cbxz.abn.controller.model.response.IngredientResponse;
import com.cbxz.abn.controller.model.response.PaginatedIngredientResponse;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(description = "gets ingredient by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully obtain ingredient"),
            @ApiResponse(responseCode = "404", description = "Ingredient not found")
    })
    IngredientResponse getIngredientById(@PathVariable(name = "id") Long id);

    @GetMapping("offset/{offset}/limit/{limit}")
    @Operation(description = "Gets paginated ingredients by pages of size limit, starting from offset")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully list ingredients"),
    })
    PaginatedIngredientResponse listIngredients(
            @PathVariable(name = "offset") Integer offset,
            @PathVariable(name = "limit") Integer limit);

    @PostMapping
    @Operation(description = "Creates ingredient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully create ingredient"),
    })
    BaseCreatedResponse createIngredient(@RequestBody @Valid CreateIngredientRequest request);

    @PutMapping
    @Operation(description = "Updates ingredient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully update ingredient"),
            @ApiResponse(responseCode = "404", description = "Ingredient not found")
    })
    void updateIngredient(@RequestBody @Valid UpdateIngredientRequest request);

    @DeleteMapping("{id}")
    @Operation(description = "Deletes ingredient by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully delete ingredient"),
            @ApiResponse(responseCode = "404", description = "Ingredient not found")
    })
    void deleteIngredient(@PathVariable(name = "id") Long id);


}
