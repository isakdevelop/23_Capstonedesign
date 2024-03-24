package com.repair.repair.controller;

import com.repair.repair.dto.request.comment.CommentWriteRequestDto;
import com.repair.repair.dto.response.board.BoardListResponseDto;
import com.repair.repair.dto.response.comment.CommentListResponseDto;
import com.repair.repair.dto.response.comment.CommentWriteResponseDto;
import com.repair.repair.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/list")
    public Page<CommentListResponseDto> list(@PageableDefault(size = 10, sort = "num", direction = Sort.Direction.DESC) Pageable pageable) {
        return commentService.list(pageable);
    }

    @PostMapping("/write")
    public CommentWriteResponseDto write(@RequestBody CommentWriteRequestDto commentWriteRequestDto) {
        return commentService.write(commentWriteRequestDto);
    }
}
