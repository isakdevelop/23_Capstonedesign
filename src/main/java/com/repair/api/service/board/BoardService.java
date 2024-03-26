package com.repair.api.service.board;

import com.repair.api.dto.request.board.BoardDetailRequestDto;
import com.repair.api.dto.request.board.BoardSelectRequestDto;
import com.repair.api.dto.request.board.BoardWriteRequestDto;
import com.repair.api.dto.response.board.BoardDetailResponseDto;
import com.repair.api.dto.response.board.BoardListResponseDto;
import com.repair.api.dto.response.board.BoardWriteResponseDto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    BoardWriteResponseDto write(BoardWriteRequestDto boardWriteRequestDto);
    Page<BoardListResponseDto> list(Pageable pageable);
    Optional<BoardDetailResponseDto> detail(BoardDetailRequestDto boardDetailRequestDto);
    Page<BoardListResponseDto> find(BoardSelectRequestDto boardSelectRequestDto, Pageable pageable);
}
