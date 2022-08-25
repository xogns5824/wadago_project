package com.wadago.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wadago.vo.BoardVo;

public interface BoardRepository extends CrudRepository<BoardVo, String> {

	public Page<BoardVo> findAll(Pageable pageable);

	@Query("SELECT board from BoardVo board where board.board_num=?1")
	public Iterable<BoardVo> modifyQuestion(@Param("board_num") int board_num);

	@Query("SELECT board.board_num from BoardVo board where board.board_num=?1")
	public Integer modifyBoardNum(@Param("board_num") int board_num);

	@Query("SELECT count(board.board_num) from BoardVo board")
	public Integer getBoardCount();

	@Modifying
	@Transactional
	@Query("DELETE FROM BoardVo b where b.board_num =?1")
	void deleteQuestion(@Param("board_num") int board_num);
}
