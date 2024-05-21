package com.repair.api.service.board;

import com.repair.api.common.ResultResponseDto;
import com.repair.api.dto.request.board.BoardModifyRequestDto;
import com.repair.api.dto.request.board.BoardWriteRequestDto;
import com.repair.api.dto.request.board.BoardDeleteRequestDto;
import com.repair.api.dto.response.board.BoardDeleteResponseDto;
import com.repair.api.dto.response.board.BoardDetailResponseDto;
import com.repair.api.dto.response.board.BoardListResponseDto;
import com.repair.api.dto.response.board.BoardWriteResponseDto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    BoardWriteResponseDto write(BoardWriteRequestDto boardWriteRequestDto);
    Page<BoardListResponseDto> list(Pageable pageable);
    Optional<BoardDetailResponseDto> detail(Long boardId);
    Page<BoardListResponseDto> find(String title, Pageable pageable);
    Optional<ResultResponseDto> modify(BoardModifyRequestDto boardModifyRequestDto);
    BoardDeleteResponseDto delete(BoardDeleteRequestDto boardDeleteRequestDto);
}
