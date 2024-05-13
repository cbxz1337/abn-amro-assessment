package com.cbxz.abn.unit.search;

import com.cbxz.abn.controller.model.request.PaginationRequest;
import com.cbxz.abn.controller.model.request.RecipeSearchRequest;
import com.cbxz.abn.repository.recipe.RecipeRepository;
import com.cbxz.abn.service.dto.search.JoinType;
import com.cbxz.abn.service.dto.search.Operation;
import com.cbxz.abn.service.dto.search.RecipeSearchKey;
import com.cbxz.abn.service.dto.search.SearchCriteria;
import com.cbxz.abn.service.search.service.RecipeSearchServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class RecipeSearchServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeSearchServiceImpl service;

    RecipeSearchRequest toFailFilter = new RecipeSearchRequest(
            List.of(
                    new SearchCriteria(RecipeSearchKey.SERVES, Operation.EQUALS, "ASD", JoinType.AND)
            ),
            new PaginationRequest(0, 10)
    );

    RecipeSearchRequest emptySearch = new RecipeSearchRequest(
            Collections.emptyList(),
            new PaginationRequest(0, 10)
    );

    RecipeSearchRequest toSuccessFilter = new RecipeSearchRequest(
            List.of(
                    new SearchCriteria(RecipeSearchKey.SERVES, Operation.EQUALS, "4", JoinType.AND)
            ),
            new PaginationRequest(0, 10)
    );

    @org.junit.Test
    @DisplayName(value = "wrong param type provided")
    public void wrongTypeProvidedTest() {
        Assert.assertThrows(IllegalArgumentException.class, () -> service.getPaginatedRecipeDtos(toFailFilter));
    }

    @Test
    @DisplayName(value = "param validation should pass")
    public void validationShouldPass() {
        Mockito.when(recipeRepository.findAll(
                Mockito.any(Specification.class), Mockito.any(PageRequest.class))).thenReturn(Page.empty());
        Assertions.assertDoesNotThrow(() -> service.getPaginatedRecipeDtos(toSuccessFilter));
    }

    @Test
    @DisplayName(value = "empty searchCriteria passed")
    public void emptySearchCriteria() {
        Mockito.when(recipeRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(Page.empty());
        service.getPaginatedRecipeDtos(emptySearch);
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll(Mockito.any(Pageable.class));
        Mockito.verify(recipeRepository, Mockito.never()).findAll(
                Mockito.any(Specification.class), Mockito.any(Pageable.class)
        );
    }

}
