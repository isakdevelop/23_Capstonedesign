package com.repair.repair.repository.board;

import com.repair.repair.domain.Board;
import com.repair.repair.dto.response.board.BoardListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, String> {
    @Query("select b.num as num, b.title as title, u.name as userName from Board b join b.user u where b.type = 0")
    Page<BoardListResponseDto> findAllBoardList(Pageable pageable);
}