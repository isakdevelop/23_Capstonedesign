package com.repair.api.dto.request.board;

import lombok.Getter;

@Getter
public class BoardModifyRequestDto {
    private Long boardId;
    private String afterTitle;
    private String afterContent;
    private String password;
}
