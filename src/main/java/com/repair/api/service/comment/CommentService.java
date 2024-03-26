package com.repair.api.service.comment;

import com.repair.api.dto.request.comment.CommentWriteRequestDto;
import com.repair.api.dto.response.comment.CommentListResponseDto;
import com.repair.api.dto.response.comment.CommentWriteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentWriteResponseDto write(CommentWriteRequestDto commentWriteRequestDto);

    Page<CommentListResponseDto> list(Pageable pageable);
}
