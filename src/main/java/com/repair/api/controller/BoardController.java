package com.repair.api.controller;

import com.repair.api.dto.request.board.BoardDetailRequestDto;
import com.repair.api.dto.request.board.BoardSelectRequestDto;
import com.repair.api.dto.request.board.BoardWriteRequestDto;
import com.repair.api.dto.response.board.BoardDetailResponseDto;
import com.repair.api.dto.response.board.BoardListResponseDto;
import com.repair.api.dto.response.board.BoardWriteResponseDto;
import com.repair.api.service.board.BoardService;
import java.util.Optional;
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

    @GetMapping("/list")
    public Page<BoardListResponseDto> list(@PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.list(pageable);
    }
    @PostMapping("/write")
    public BoardWriteResponseDto write(@RequestBody BoardWriteRequestDto boardWriteRequestDto) {
        return boardService.write(boardWriteRequestDto);
    }

    @PostMapping("/find")
    public Page<BoardListResponseDto> find(@RequestBody BoardSelectRequestDto boardSelectRequestDto,
                                           @PageableDefault(size = 10, sort = "num", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.find(boardSelectRequestDto, pageable);
    }

    @PostMapping("/detail")
    public Optional<BoardDetailResponseDto> detail(@RequestBody BoardDetailRequestDto boardDetailRequestDto) {
        return boardService.detail(boardDetailRequestDto);
    }

}
