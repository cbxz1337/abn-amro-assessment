package com.cbxz.abn.service.search.service;

import com.cbxz.abn.controller.model.request.RecipeSearchRequest;
import com.cbxz.abn.domain.Recipe;
import com.cbxz.abn.repository.RecipeRepository;
import com.cbxz.abn.service.dto.recipe.PaginatedRecipeDto;
import com.cbxz.abn.service.dto.search.RecipeSearchKey;
import com.cbxz.abn.service.dto.search.SearchCriteria;
import com.cbxz.abn.service.mapper.RecipeMapper;
import com.cbxz.abn.service.search.specification.RecipeSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeSearchServiceImpl implements RecipeSearchService {

    private final RecipeRepository recipeRepository;

    @Override
    public PaginatedRecipeDto getPaginatedRecipeDtos(RecipeSearchRequest searchRequest) {
        var filterRequests = searchRequest.filterRequests();
        var pageRequest = PageRequest.of(
                searchRequest.pagination().offset(),
                searchRequest.pagination().limit(),
                Sort.by(RecipeSearchKey.NAME.getKeyName()));
        Page<Recipe> recipes;
        if (!CollectionUtils.isEmpty(filterRequests)) {
            validateParams(filterRequests);
            var specBuilder = new RecipeSpecificationBuilder(filterRequests);
            var specification = specBuilder.build();
            recipes = recipeRepository.findAll(specification, pageRequest);
        } else {
            recipes = recipeRepository.findAll(pageRequest);
        }
        return toPaginatedRecipeDto(searchRequest, recipes);
    }
    
    
    public void validateParams(List<SearchCriteria> searchCriteria) {
        searchCriteria.forEach(RecipeFilterValidationUtils::validateCriteria);
    }
    
    private static PaginatedRecipeDto toPaginatedRecipeDto(RecipeSearchRequest searchRequest, Page<Recipe> result) {
        return new PaginatedRecipeDto(result.getTotalElements(), searchRequest.pagination(),
                result.stream().map(RecipeMapper::toRecipeDto).toList());
    }
}
