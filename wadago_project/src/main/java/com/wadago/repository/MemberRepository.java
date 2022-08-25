package com.wadago.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wadago.vo.MemberVo;

public interface MemberRepository extends CrudRepository<MemberVo, String> {

	Page<MemberVo> findAllByGrade(PageRequest of, int i);

	@Query("SELECT m FROM MemberVo m where m.id=?1 and m.pw=?2")
	public MemberVo selectMemCheck(@Param("id") String id, @Param("pw") String pw);

	@Modifying
	@Transactional
	@Query("update MemberVo m set m.grade = 2 where m.id =?1")
	void updateGrade(String ajaxMsg);

	@Query("SELECT m FROM MemberVo m where m.id =?1")
	MemberVo selectMeberId(String id);

}
