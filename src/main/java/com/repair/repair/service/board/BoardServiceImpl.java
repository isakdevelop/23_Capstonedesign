package com.repair.repair.service.board;

import com.repair.repair.dto.request.board.BoardDetailRequestDto;
import com.repair.repair.dto.request.board.BoardSelectRequestDto;
import com.repair.repair.dto.request.board.BoardWriteRequestDto;
import com.repair.repair.dto.response.board.BoardDetailResponseDto;
import com.repair.repair.dto.response.board.BoardListResponseDto;
import com.repair.repair.dto.response.board.BoardWriteResponseDto;
import com.repair.repair.domain.Board;
import com.repair.repair.dto.response.user.UserLoginResponseDto;
import com.repair.repair.repository.board.BoardRepository;
import com.repair.repair.repository.user.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public BoardWriteResponseDto write(BoardWriteRequestDto boardWriteRequestDto) {
        Board board = Board.builder()
                .title(boardWriteRequestDto.getTitle())
                .content(boardWriteRequestDto.getContent())
                .type(boardWriteRequestDto.getType())
                .user(userRepository.findById(boardWriteRequestDto.getUserId()).get())
                .build();

        boardRepository.save(board);
        return new BoardWriteResponseDto(board.getId(), "등록 성공");
    }

    @Override
    public Page<BoardListResponseDto> list(Pageable pageable) {
        return boardRepository.findAllBoardList(pageable);
    }

    @Transactional
    @Override
    public Optional<BoardDetailResponseDto> detail(BoardDetailRequestDto boardDetailRequestDto) {
        Optional<Board> findUser = boardRepository.findByNum(Long.valueOf(boardDetailRequestDto.getNum()));

        if (findUser.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new BoardDetailResponseDto(findUser.get().getNum(), findUser.get().getTitle(),
                findUser.get().getContent()));
    }

    @Transactional
    @Override
    public Page<BoardListResponseDto> find(BoardSelectRequestDto boardSelectRequestDto, Pageable pageable) {
        String keyword = boardSelectRequestDto.getKeyword();
        return boardRepository.findByTitleContaining(keyword, pageable);
    }
}
