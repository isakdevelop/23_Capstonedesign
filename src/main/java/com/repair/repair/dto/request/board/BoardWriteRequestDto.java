package com.repair.repair.dto.request.board;

import lombok.Getter;

@Getter
public class BoardWriteRequestDto {
    private String title;
    private String content;
    private String userId;
    private int type;
}
