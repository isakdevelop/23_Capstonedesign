package com.repair.api.dto.response.board;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class BoardListResponseDto {
    private LocalDateTime createAt;
    private String title;
    private String userName;

    public BoardListResponseDto(LocalDateTime createAt, String title, String userName) {
        this.createAt = createAt;
        this.title = title;
        this.userName = userName;
    }
}