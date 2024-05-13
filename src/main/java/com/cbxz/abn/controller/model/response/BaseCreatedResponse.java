package com.cbxz.abn.controller.model.response;

import lombok.Builder;

@Builder
public record BaseCreatedResponse(
        Long id
) {

    public static BaseCreatedResponse of(Long id) {
        return new BaseCreatedResponse(id);
    }
}
