package com.repair.repair.dto.response.board;

import lombok.Getter;

@Getter
public class BoardWriteResponseDto {
    private String id;
    private String title;

    public BoardWriteResponseDto(String id, String title) {
        this.id = id;
        this.title = title;
    }
}