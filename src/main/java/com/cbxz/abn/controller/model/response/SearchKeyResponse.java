package com.cbxz.abn.controller.model.response;

import com.cbxz.abn.service.dto.search.RecipeSearchKey;
import lombok.Builder;

@Builder
public record SearchKeyResponse(RecipeSearchKey key,
                                SearchKeyTypeResponse type){}
