package com.repair.repair.dto.response.board;

import lombok.Getter;

@Getter
public class BoardDetailResponseDto {
    private Long num;
    private String writer;
    private String content;

    public BoardDetailResponseDto(Long num, String writer, String content) {
        this.num = num;
        this.writer = writer;
        this.content = content;
    }
}
