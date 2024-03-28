package com.repair.api.dto.response.board;

import lombok.Getter;

@Getter
public class BoardWriteResponseDto {
    private Long id;
    private String title;

    public BoardWriteResponseDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}