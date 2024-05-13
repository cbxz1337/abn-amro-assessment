package com.cbxz.abn.service.search.predicate.impl;

import com.cbxz.abn.domain.Recipe;
import com.cbxz.abn.service.dto.search.Operation;
import com.cbxz.abn.service.dto.search.SearchCriteria;
import com.cbxz.abn.service.search.predicate.PredicateBuilder;
import jakarta.persistence.criteria.*;
import lombok.val;

import static com.cbxz.abn.service.search.predicate.PredicateBuilderUtils.*;

public class NotIncludesPredicate implements PredicateBuilder {

    @Override
    public Operation getOperation() {
        return Operation.NOT_INCLUDES;
    }

    @Override
    public Predicate getPredicate(CriteriaBuilder cb, Root<Recipe> root, Join<Recipe, Object> child, SearchCriteria criteria) {
        val value = PERCENT_SIGN + criteria.value() + PERCENT_SIGN;
        if (child != null) {
            Subquery<Long> subquery = cb.createQuery(Long.class).subquery(Long.class);
            Root<?> subRoot = subquery.from(criteria.key().getManyToManyTable());
            subquery.select(subRoot.get(RECIPE_TABLE_NAME).get(ID))
                    .where(
                            cb.and(
                                    cb.equal(subRoot.get(RECIPE_TABLE_NAME).get(ID), root.get(ID)),
                                    cb.like(
                                            cb.lower(subRoot.get(criteria.key().getJoinTable())
                                                    .get(criteria.key().getKeyName())),
                                            value.toLowerCase())
                            )
                    );
            return cb.not(cb.exists(subquery));
        } else {
            return cb.notLike(cb.lower(root.get(criteria.key().getKeyName())),
                    value.toLowerCase());
        }
    }
}
