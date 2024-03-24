package com.repair.repair.service.comment;

import com.repair.repair.dto.request.comment.CommentWriteRequestDto;
import com.repair.repair.dto.response.comment.CommentListResponseDto;
import com.repair.repair.dto.response.comment.CommentWriteResponseDto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    CommentWriteResponseDto write(CommentWriteRequestDto commentWriteRequestDto);

    Page<CommentListResponseDto> list(Pageable pageable);
}
