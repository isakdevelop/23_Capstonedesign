package com.repair.api.service.board;

import com.repair.api.common.ResultResponseDto;
import com.repair.api.dto.request.board.BoardModifyRequestDto;
import com.repair.api.dto.request.board.BoardWriteRequestDto;
import com.repair.api.dto.request.board.BoardDeleteRequestDto;
import com.repair.api.dto.response.board.BoardDeleteResponseDto;
import com.repair.api.dto.response.board.BoardDetailResponseDto;
import com.repair.api.dto.response.board.BoardListResponseDto;
import com.repair.api.dto.response.board.BoardWriteResponseDto;
import com.repair.api.domain.Board;
import com.repair.api.repository.board.BoardRepository;
import com.repair.api.repository.user.UserRepository;
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
                .password(boardWriteRequestDto.getPassword())
                .user(userRepository.findById(boardWriteRequestDto.getUserId()).get())
                .build();

        boardRepository.save(board);
        return new BoardWriteResponseDto(200, "등록 성공");
    }

    @Override
    public Page<BoardListResponseDto> list(Pageable pageable) {
        return boardRepository.findAllBoardList(pageable);
    }

    @Transactional
    @Override
    public Optional<BoardDetailResponseDto> detail(Long boardId) {
        Optional<Board> findUser = boardRepository.findById(boardId);

        if (findUser.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(new BoardDetailResponseDto(findUser.get().getTitle(),
                findUser.get().getContent()));
    }

    @Transactional
    @Override
    public Page<BoardListResponseDto> find(String title, Pageable pageable) {
        return boardRepository.findByTitleContaining(title, pageable);
    }

    @Override
    @Transactional
    public Optional<ResultResponseDto> modify(BoardModifyRequestDto boardModifyRequestDto) {
        Optional<Board> optionalBoard = boardRepository.findById(boardModifyRequestDto.getBoardId());

        if (optionalBoard.isEmpty()) {
            return Optional.of(new ResultResponseDto(404, "해당 아이디에 존재하는 글이 없습니다."));
        } else {
            Board myBoard = optionalBoard.get();

            if (myBoard.getPassword().equals(boardModifyRequestDto.getPassword())) {
                myBoard.update(boardModifyRequestDto.getAfterTitle(), boardModifyRequestDto.getAfterContent());
                return Optional.of(new ResultResponseDto(200, "성공적으로 수행하였습니다."));
            } else {
                return Optional.of(new ResultResponseDto(404, "비밀번호가 일치하지 않습니다."));
            }
        }
    }

    @Override
    public BoardDeleteResponseDto delete(BoardDeleteRequestDto boardDeleteRequestDto) {
        boardRepository.deleteById(boardDeleteRequestDto.getBoardId());
        return new BoardDeleteResponseDto(200, "삭제 성공");
    }
}
