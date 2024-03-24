package com.repair.repair.service.comment;

import com.repair.repair.domain.Comment;
import com.repair.repair.dto.request.comment.CommentWriteRequestDto;
import com.repair.repair.dto.response.comment.CommentListResponseDto;
import com.repair.repair.dto.response.comment.CommentWriteResponseDto;
import com.repair.repair.repository.board.BoardRepository;
import com.repair.repair.repository.comment.CommentRepository;
import com.repair.repair.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
                .parentComment(commentRepository.findById(commentWriteRequestDto.getCommentId()).get())
                .build();

        commentRepository.save(comment);
        return new CommentWriteResponseDto(comment.getId(), "댓글 작성 성공");
    }

    @Override
    public Page<CommentListResponseDto> list(Pageable pageable) {
        return commentRepository.findAllBoardList(pageable);
    }
}
