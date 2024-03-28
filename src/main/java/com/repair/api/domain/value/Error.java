package com.repair.api.domain.value;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Error {
    ID_CONFLICT(409, "이미 존재하는 아이디 입니다."),
    ID_NOT_FOUND(404, "존재하지 않는 아이디 입니다."),
    ID_BAD_REQUEST(400, "잘못된 아이디 형식 입니다."),
    PW_NOT_FOUND(404, "비밀번호가 일치하지 않습니다."),
    EMAIL_NOT_FOUND(404, "존재하지 않는 이메일 입니다."),
    ADMIN_FORBIDDEN(403, "접근 권한이 존재하지 않습니다."),
    REPAIR_CENTER_NOT_FOUND(404, "존재하지 않는 수리점 입니다."),
    BRAND_NOT_FOUND(404, "존재하지 않는 브랜드 입니다.");

    private final int status;
    private final String message;
}
