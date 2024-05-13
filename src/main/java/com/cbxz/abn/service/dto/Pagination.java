package com.cbxz.abn.service.dto;

import lombok.Builder;

@Builder
public record Pagination(
        Integer offset,
        Integer limit
) {}
