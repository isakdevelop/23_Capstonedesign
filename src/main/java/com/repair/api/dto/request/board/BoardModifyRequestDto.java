package com.repair.api.dto.request.board;

import lombok.Getter;

@Getter
public class BoardModifyRequestDto {
    private String BoardId;
    private String afterTitle;
    private String afterContent;
    private String password;
}