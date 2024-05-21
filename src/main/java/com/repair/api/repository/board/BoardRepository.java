package com.repair.api.repository.board;

import com.repair.api.domain.Board;
import com.repair.api.dto.response.board.BoardListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("select new com.repair.api.dto.response.board.BoardListResponseDto(" +
            "b.createAt, b.title, u.name) " +
            "from Board b join b.user u")
    Page<BoardListResponseDto> findAllBoardList(Pageable pageable);

    @Query("select new com.repair.api.dto.response.board.BoardListResponseDto(b.createAt, b.title, u.name) " +
            "from Board b join b.user u " +
            "where b.title like %:keyword%")
    Page<BoardListResponseDto> findByTitleContaining(@Param("keyword") String keyword, Pageable pageable);
}