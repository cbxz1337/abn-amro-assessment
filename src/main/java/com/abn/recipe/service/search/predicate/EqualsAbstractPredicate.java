package com.abn.recipe.service.search.predicate;

import com.abn.recipe.service.dto.search.SearchCriteria;

public abstract class EqualsAbstractPredicate {

    protected Object prepareValue(SearchCriteria criteria) {
        Object value = criteria.value();
        if (criteria.key().getType().equals(Integer.class)) {
            value = Integer.valueOf(criteria.value());
        }
        if (criteria.key().getType().equals(Boolean.class)) {
            value = Boolean.valueOf(criteria.value());
        }
        return value;
    }
}
