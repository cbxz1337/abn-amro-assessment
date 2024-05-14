package com.abn.recipe.service.search.predicate.impl;

import com.abn.recipe.domain.Recipe;
import com.abn.recipe.service.dto.search.SearchCriteria;
import com.abn.recipe.service.search.predicate.EqualsAbstractPredicate;
import com.abn.recipe.service.search.predicate.PredicateBuilder;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class EqualsPredicateBuilder extends EqualsAbstractPredicate implements PredicateBuilder {

    @Override
    public Predicate getPredicate(CriteriaBuilder cb, Root<Recipe> root, Join<Recipe, Object> child, SearchCriteria criteria) {
        Object value = prepareValue(criteria);
        if (child != null) {
            return cb.equal(child.get(criteria.key().getKeyName()), value);
        } else {
            return cb.equal(root.get(criteria.key().getKeyName()), value);
        }
    }
}
