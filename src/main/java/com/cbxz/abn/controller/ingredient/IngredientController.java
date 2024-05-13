package com.cbxz.abn.controller.ingredient;

import com.cbxz.abn.controller.model.request.ingredient.CreateIngredientRequest;
import com.cbxz.abn.controller.model.request.ingredient.UpdateIngredientRequest;
import com.cbxz.abn.controller.model.response.BaseCreatedResponse;
import com.cbxz.abn.controller.model.response.IngredientResponse;
import com.cbxz.abn.controller.model.response.PaginatedIngredientResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Ingredient controller")
@RequestMapping("api/v1/ingredient/")
@Schema
public interface IngredientController {

    @GetMapping("{id}")
    IngredientResponse getIngredientById(@PathVariable(name = "id") Long id);

    @GetMapping("offset/{offset}/limit/{limit}")
    PaginatedIngredientResponse listIngredients(
            @PathVariable(name = "offset") Integer offset,
            @PathVariable(name = "limit") Integer limit);

    @PostMapping
    BaseCreatedResponse createIngredient(@RequestBody @Valid CreateIngredientRequest request);

    @PutMapping
    void updateIngredient(@RequestBody @Valid UpdateIngredientRequest request);

    @DeleteMapping("{id}")
    void deleteIngredient(@PathVariable(name = "id") Long id);


}
