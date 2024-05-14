package com.abn.recipe.service.ingredient;

import com.abn.recipe.exception.GlobalExceptionHandler;
import com.abn.recipe.domain.Ingredient;
import com.abn.recipe.exception.NotFoundException;
import com.abn.recipe.repository.ingredient.IngredientRepository;
import com.abn.recipe.repository.recipe.RecipeIngredientRepository;
import com.abn.recipe.service.dto.Pagination;
import com.abn.recipe.service.dto.ingredient.IngredientDto;
import com.abn.recipe.service.dto.ingredient.PaginatedIngredientDto;
import com.abn.recipe.service.dto.search.RecipeSearchKey;
import com.abn.recipe.service.mapper.IngredientMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    @Override
    public List<Ingredient> findByIds(List<Long> ids) {
        return ingredientRepository.findAllById(ids);
    }

    @Override
    public IngredientDto findById(Long id) {
        val ingredient = ingredientRepository.findById(id).orElseThrow(
                () -> buildNotFoundException(id));
        return IngredientMapper.toIngredientDto(ingredient);
    }

    @Override
    public PaginatedIngredientDto listIngredients(Pagination pagination) {
        val pageable = PageRequest.of(pagination.offset(),
                pagination.limit(),
                Sort.by(RecipeSearchKey.NAME.getKeyName()));
        val ingredients = ingredientRepository.findAll(pageable);
        return PaginatedIngredientDto.builder()
                .pagination(pagination)
                .total(ingredients.getTotalElements())
                .ingredients(ingredients.stream().map(IngredientMapper::toIngredientDto).toList())
                .build();
    }

    @Override
    public Long create(IngredientDto dto) {
        logger.info(String.format("Creating ingredient %s", dto));
        val ingredient = IngredientMapper.toIngredient(dto);
        return ingredientRepository.save(ingredient).getId();
    }

    @Override
    public void update(IngredientDto dto) {
        logger.info(String.format("Updating ingredient %s", dto));
        val ingredient = IngredientMapper.toIngredient(dto);
        if(!ingredientRepository.existsById(dto.id())) {
            throw buildNotFoundException(dto.id());
        }
        ingredientRepository.save(ingredient);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.info(String.format("Deleting ingredient id = %s", id));
        if (!ingredientRepository.existsById(id)) {
            throw buildNotFoundException(id);
        }
        recipeIngredientRepository.deleteByIngredientId(id);
        ingredientRepository.deleteById(id);
    }

    private static NotFoundException buildNotFoundException(Long id) {
        return new NotFoundException(String.format("Ingredient with id = %s not exists.", id));
    }
}
