package com.cbxz.abn.service.search.specification;


import com.cbxz.abn.domain.Recipe;
import com.cbxz.abn.service.dto.search.SearchCriteria;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.List;

@RequiredArgsConstructor
public class RecipeSpecificationBuilder {
    private final List<SearchCriteria> criteriaList;

    public Specification<Recipe> build() {
        if (CollectionUtils.isEmpty(criteriaList)) {
            return null;
        }
        Specification<Recipe> specification = new RecipeSpecification(criteriaList.getFirst());
        for (int i = 1; i < criteriaList.size(); i++) {
            val criteria = criteriaList.get(i);
            switch (criteria.joinType()) {
                case OR -> specification = Specification.where(specification)
                        .or(new RecipeSpecification(criteriaList.get(i)));
                case AND -> specification = Specification.where(specification)
                        .and(new RecipeSpecification(criteriaList.get(i)));
            }
        }
        return specification;
    }
}
