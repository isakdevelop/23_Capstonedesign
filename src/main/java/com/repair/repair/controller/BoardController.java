package com.repair.repair.controller;

import com.repair.repair.dto.request.board.BoardWriteRequestDto;
import com.repair.repair.dto.response.board.BoardListResponseDto;
import com.repair.repair.dto.response.board.BoardWriteResponseDto;
import com.repair.repair.service.board.BoardService;
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
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/write")
    public BoardWriteResponseDto write(@RequestBody BoardWriteRequestDto boardWriteRequestDto) {
        return boardService.write(boardWriteRequestDto);
    }

    @GetMapping("/list")
    public Page<BoardListResponseDto> list(@PageableDefault(size = 10, sort = "num", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.list(pageable);
    }

}
