package com.cbxz.abn.repository.search;

import com.cbxz.abn.service.dto.search.Operation;
import com.cbxz.abn.service.dto.search.RecipeSearchKey;
import com.cbxz.abn.service.dto.search.JoinType;

public record SearchRequest(
    RecipeSearchKey key,
    Operation operation,
    Object filterValue,
    JoinType joinType
) { }
