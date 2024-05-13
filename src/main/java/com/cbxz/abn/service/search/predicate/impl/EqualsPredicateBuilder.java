package com.cbxz.abn.service.search.predicate.impl;

import com.cbxz.abn.domain.Recipe;
import com.cbxz.abn.service.dto.search.SearchCriteria;
import com.cbxz.abn.service.search.predicate.EqualsAbstractPredicate;
import com.cbxz.abn.service.search.predicate.PredicateBuilder;
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
