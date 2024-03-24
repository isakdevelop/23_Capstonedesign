package com.repair.repair.repository.comment;

import com.repair.repair.domain.Comment;
import com.repair.repair.dto.response.comment.CommentListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    @Query("select c.id as comment_id, c.comment as comment, u.name as userName from Comment c join c.user u")
    Page<CommentListResponseDto> findAllBoardList(Pageable pageable);
}
