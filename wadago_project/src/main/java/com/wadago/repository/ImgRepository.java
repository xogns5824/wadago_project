package com.wadago.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.wadago.vo.ImgVo;

public interface ImgRepository extends CrudRepository<ImgVo, String> {

	@Query("SELECT count(img.img_num) from ImgVo img")
	public Integer getImgCount();

	@Query("SELECT img from ImgVo img where img.img_num=?1")
	public Iterable<ImgVo> modifyImg(@Param("img_num") int img_num);

	public Page<ImgVo> findAll(Pageable pageable);

	@Query("SELECT img from ImgVo img")
	public Iterable<ImgVo> findAll2();

	public Page<ImgVo> findByGrade(Pageable pageable, int i);
}
