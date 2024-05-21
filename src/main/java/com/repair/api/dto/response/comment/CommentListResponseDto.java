package com.repair.api.dto.response.comment;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CommentListResponseDto {
    private LocalDateTime createAt;
    private String comment;
    private String userName;

    public CommentListResponseDto(LocalDateTime createAt, String comment, String userName) {
        this.createAt = createAt;
        this.comment = comment;
        this.userName = userName;
    }
}
