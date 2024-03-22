package com.repair.repair.dto.response.board;

import lombok.Getter;

import java.time.LocalDateTime;

public interface BoardListResponseDto {
    Long getNum();
    String getTitle();
    String getUserName();
}