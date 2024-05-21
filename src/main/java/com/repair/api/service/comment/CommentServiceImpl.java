package com.repair.api.service.comment;

import com.repair.api.domain.Comment;
import com.repair.api.dto.request.comment.CommentDeleteRequestDto;
import com.repair.api.dto.request.comment.CommentWriteRequestDto;
import com.repair.api.dto.response.comment.CommentDeleteResponseDto;
import com.repair.api.dto.response.comment.CommentListResponseDto;
import com.repair.api.dto.response.comment.CommentWriteResponseDto;
import com.repair.api.exception.RepairException;
import com.repair.api.repository.board.BoardRepository;
import com.repair.api.repository.comment.CommentRepository;
import com.repair.api.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentWriteResponseDto write(CommentWriteRequestDto commentWriteRequestDto) {
        Comment comment = Comment.builder()
                .comment(commentWriteRequestDto.getContent())
                .user(userRepository.findById(commentWriteRequestDto.getUserId()).get())
                .board(boardRepository.findById(commentWriteRequestDto.getBoardId()).get())
                .build();

        commentRepository.save(comment);
        return new CommentWriteResponseDto(200, "댓글 작성 성공");
    }

    @Override
    public Page<CommentListResponseDto> list(Pageable pageable) {
        return commentRepository.findAllBoardList(pageable);
    }

    @Override
    @Transactional
    public CommentDeleteResponseDto delete(CommentDeleteRequestDto commentDeleteRequestDto) {
        Comment comment = commentRepository.findById(commentDeleteRequestDto.getCommentId())
                .orElseThrow(() -> new RepairException(404, "댓글을 찾을 수 없습니다."));

        comment.updateComment("삭제된 댓글 입니다.");

        return new CommentDeleteResponseDto(200, "댓글 삭제 성공");
    }
}
