package com.cbxz.abn.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class NotFoundException extends RuntimeException {
    private final String message;
}
