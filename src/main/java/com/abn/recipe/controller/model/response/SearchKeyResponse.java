package com.abn.recipe.controller.model.response;

import com.abn.recipe.service.dto.search.RecipeSearchKey;
import lombok.Builder;

@Builder
public record SearchKeyResponse(RecipeSearchKey key,
                                SearchKeyTypeResponse type){}
