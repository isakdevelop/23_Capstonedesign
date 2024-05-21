package com.repair.api.repository.comment;

import com.repair.api.domain.Comment;
import com.repair.api.dto.response.comment.CommentListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select new com.repair.api.dto.response.comment.CommentListResponseDto(" +
            "c.createAt, c.comment, u.name) " +
            "from Comment c join c.user u")
    Page<CommentListResponseDto> findAllBoardList(Pageable pageable);
}
