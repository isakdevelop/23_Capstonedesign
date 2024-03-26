package com.repair.api.dto.response.board;

import lombok.Getter;

@Getter
public class BoardDetailResponseDto {
    private String writer;
    private String content;

    public BoardDetailResponseDto(String writer, String content) {
        this.writer = writer;
        this.content = content;
    }
}
