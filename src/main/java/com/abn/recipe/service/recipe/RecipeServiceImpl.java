package com.abn.recipe.service.recipe;

import com.abn.recipe.exception.GlobalExceptionHandler;
import com.abn.recipe.domain.Ingredient;
import com.abn.recipe.exception.NotFoundException;
import com.abn.recipe.repository.recipe.RecipeIngredientRepository;
import com.abn.recipe.repository.recipe.RecipeRepository;
import com.abn.recipe.service.dto.recipe.CreateRecipeDto;
import com.abn.recipe.service.dto.recipe.RecipeDto;
import com.abn.recipe.service.dto.recipe.UpdateRecipeDto;
import com.abn.recipe.service.ingredient.IngredientService;
import com.abn.recipe.service.mapper.RecipeMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final RecipeRepository recipeRepository;
    private final IngredientService ingredientService;
    private final RecipeIngredientRepository recipeIngredientRepository;

    private static final String RECIPE_NOT_FOUND_MESSAGE = "Recipe with id = %s not found";

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.info(String.format("Deleting Recipe with id = %s", id));
        if (!recipeRepository.existsById(id)) {
            throw notFoundException(id);
        }
        recipeIngredientRepository.deleteByRecipeId(id);
        recipeRepository.deleteById(id);
    }

    @Override
    public Long update(UpdateRecipeDto dto) {
        logger.info(String.format("Updating Recipe with id = %s", dto.id()));
        if (!recipeRepository.existsById(dto.id())) {
            throw notFoundException(dto.id());
        }
        val ingredients = ingredientService.findByIds(dto.ingredients());
        if (ingredients.size() != dto.ingredients().size()) {
            val passedIngredients = dto.ingredients();
            passedIngredients.removeAll(ingredients.stream().map(Ingredient::getId).toList());
            throw notFoundException(String.format(
                            "Ingredients with ids %s not exist.",
                            passedIngredients
                    )
            );
        }
        return recipeRepository.save(RecipeMapper.toRecipe(dto, ingredients)).getId();
    }

    @Override
    public Long createRecipe(CreateRecipeDto dto) {
        logger.info(String.format("Creating Recipe %s", dto.toString()));
        val ingredients = ingredientService.findByIds(dto.ingredients());
        if (ingredients.size() != dto.ingredients().size()) {
            val passedIngredients = dto.ingredients();
            passedIngredients.removeAll(ingredients.stream().map(Ingredient::getId).toList());
            throw notFoundException(String.format(
                    "Ingredients with ids %s not exist.",
                    passedIngredients)
            );
        }
        return recipeRepository.save(RecipeMapper.toRecipe(dto, ingredients)).getId();
    }

    @Override
    public RecipeDto getById(Long id) {
        return recipeRepository.findById(id)
                .map(RecipeMapper::toRecipeDto)
                .orElseThrow(() -> notFoundException(id));
    }

    private static NotFoundException notFoundException(Long id) {
        return new NotFoundException(
                String.format(RECIPE_NOT_FOUND_MESSAGE, id));
    }

    private static NotFoundException notFoundException(String message) {
        return new NotFoundException(message);
    }
}
