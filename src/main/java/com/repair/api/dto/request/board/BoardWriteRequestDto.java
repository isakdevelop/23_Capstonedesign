package com.repair.api.dto.request.board;

import lombok.Getter;

@Getter
public class BoardWriteRequestDto {
    private String title;
    private String content;
    private String password;
    private Long userId;
}
