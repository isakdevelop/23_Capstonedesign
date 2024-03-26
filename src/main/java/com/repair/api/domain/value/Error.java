package com.repair.api.domain.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    BRAND_NOT_FOUND(404, "존재하지 않는 브랜드 입니다."),

    ;
    private final int status;
    private final String message;
}
