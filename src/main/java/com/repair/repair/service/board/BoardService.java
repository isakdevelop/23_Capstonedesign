package com.repair.repair.service.board;

import com.repair.repair.dto.request.board.BoardWriteRequestDto;
import com.repair.repair.dto.response.board.BoardListResponseDto;
import com.repair.repair.dto.response.board.BoardWriteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    BoardWriteResponseDto write(BoardWriteRequestDto boardWriteRequestDto);
    Page<BoardListResponseDto> list(Pageable pageable);
}
