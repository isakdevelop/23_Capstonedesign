package com.repair.api.controller;

import com.repair.api.common.ResultResponseDto;
import com.repair.api.dto.request.board.BoardModifyRequestDto;
import com.repair.api.dto.request.board.BoardWriteRequestDto;
import com.repair.api.dto.request.board.BoardDeleteRequestDto;
import com.repair.api.dto.response.board.BoardDeleteResponseDto;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Page<BoardListResponseDto> list(@PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.list(pageable);
    }

    @GetMapping("/find")
    public Page<BoardListResponseDto> find(@RequestParam("title") String title,
                                           @PageableDefault(size = 10, sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.find(title, pageable);
    }

    @GetMapping("/detail")
    public Optional<BoardDetailResponseDto> detail(@RequestParam("boardId") Long boardId) {
        return boardService.detail(boardId);
    }

    @PatchMapping("/modify")
    public Optional<ResultResponseDto> modify(@RequestBody BoardModifyRequestDto boardModifyRequestDto) {
        return boardService.modify(boardModifyRequestDto);
    }

    @DeleteMapping("/delete")
    public BoardDeleteResponseDto delete(@RequestBody BoardDeleteRequestDto boardDeleteRequestDto) {
        return boardService.delete(boardDeleteRequestDto);
    }
}
