package com.wadago.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wadago.vo.ReplyVo;

public interface ReplyRepository extends CrudRepository<ReplyVo, String> {

	@Query("SELECT reply from ReplyVo reply where reply.board_num=?1")
	public Iterable<ReplyVo> modifyReply(@Param("board_num") int board_num);

	@Query("SELECT count(reply.comment_num) from ReplyVo reply")
	public Integer getCommentCount();

	@Modifying
	@Transactional
	@Query("DELETE FROM ReplyVo r where r.comment_num =?1")
	void deleteReply(@Param("comment_num") int comment_num);
}
