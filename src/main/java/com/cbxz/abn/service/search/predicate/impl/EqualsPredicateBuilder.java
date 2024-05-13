package com.cbxz.abn.service.search.predicate.impl;

import com.cbxz.abn.domain.Recipe;
import com.cbxz.abn.service.dto.search.Operation;
import com.cbxz.abn.service.dto.search.SearchCriteria;
import com.cbxz.abn.service.search.predicate.PredicateBuilder;

import jakarta.persistence.criteria.*;

public class EqualsPredicateBuilder implements PredicateBuilder {

    @Override
    public Operation getOperation() {
        return Operation.EQUALS;
    }

    @Override
    public Predicate getPredicate(CriteriaBuilder cb, Root<Recipe> root, Join<Recipe, Object> child, SearchCriteria criteria) {
        if (child != null) {
            return cb.equal(child.get(criteria.key().getKeyName()), criteria.value());
        } else {
            return cb.equal(root.get(criteria.key().getKeyName()), criteria.value());
        }
    }
}
