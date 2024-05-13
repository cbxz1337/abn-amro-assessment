package com.cbxz.abn.service.ingredient;

import com.cbxz.abn.domain.Ingredient;
import com.cbxz.abn.exception.NotFoundException;
import com.cbxz.abn.repository.IngredientRepository;
import com.cbxz.abn.repository.RecipeIngredientRepository;
import com.cbxz.abn.service.dto.Pagination;
import com.cbxz.abn.service.dto.ingredient.IngredientDto;
import com.cbxz.abn.service.dto.ingredient.PaginatedIngredientDto;
import com.cbxz.abn.service.dto.search.RecipeSearchKey;
import com.cbxz.abn.service.mapper.IngredientMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

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
        val ingredient = IngredientMapper.toIngredient(dto);
        return ingredientRepository.save(ingredient).getId();
    }

    @Override
    public void update(IngredientDto dto) {
        val ingredient = IngredientMapper.toIngredient(dto);
        if(!ingredientRepository.existsById(dto.id())) {
            throw buildNotFoundException(dto.id());
        }
        ingredientRepository.save(ingredient);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
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
