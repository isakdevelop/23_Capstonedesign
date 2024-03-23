package com.repair.repair.repository.board;

import com.repair.repair.domain.Board;
import com.repair.repair.dto.response.board.BoardListResponseDto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, String> {
    @Query("select b.num as num, b.title as title, u.name as userName from Board b join b.user u")
    Page<BoardListResponseDto> findAllBoardList(Pageable pageable);
    Optional<Board> findByNum(Long num);

    @Query("select b.num as num, b.title as title, u.name as userName from Board b join b.user u where b.title like %:keyword%")
    Page<BoardListResponseDto> findByTitleContaining(@Param("keyword") String keyword, Pageable pageable);
}