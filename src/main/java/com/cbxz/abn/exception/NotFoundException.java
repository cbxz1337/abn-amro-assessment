package com.cbxz.abn.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {
    private final String message;
}
