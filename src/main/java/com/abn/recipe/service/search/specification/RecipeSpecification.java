package com.abn.recipe.service.search.specification;

import com.abn.recipe.domain.Recipe;
import com.abn.recipe.service.dto.search.Operation;
import com.abn.recipe.service.dto.search.SearchCriteria;
import com.abn.recipe.service.search.predicate.PredicateBuilder;
import com.abn.recipe.service.search.predicate.impl.EqualsPredicateBuilder;
import com.abn.recipe.service.search.predicate.impl.IncludesPredicateBuilder;
import com.abn.recipe.service.search.predicate.impl.NotEqualsPredicateBuilder;
import com.abn.recipe.service.search.predicate.impl.NotIncludesPredicate;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;
import jakarta.persistence.criteria.JoinType;
import java.util.Map;

@RequiredArgsConstructor
public class RecipeSpecification implements Specification<Recipe> {

    private final SearchCriteria searchCriteria;

    private static final Map<Operation, PredicateBuilder> PREDICATE_BUILDER_MAP = Map.of(
            Operation.INCLUDES, new IncludesPredicateBuilder(),
            Operation.NOT_INCLUDES, new NotIncludesPredicate(),
            Operation.EQUALS, new EqualsPredicateBuilder(),
            Operation.NOT_EQUALS, new NotEqualsPredicateBuilder()
    );

    @Override
    public Predicate toPredicate(Root<Recipe> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        val operation = searchCriteria.operator();
        Join<Recipe, Object> join = null;
        if (searchCriteria.key().getJoinTable() != null) {
            join = root.join(searchCriteria.key().getJoinTable(), JoinType.INNER);
        }
        val predicate = PREDICATE_BUILDER_MAP.get(operation);
        return predicate.getPredicate(builder, root, join, searchCriteria);
    }

}
