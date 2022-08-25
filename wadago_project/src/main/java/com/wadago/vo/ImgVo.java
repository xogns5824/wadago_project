package com.wadago.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "img")
public class ImgVo {

	@Id
	private Integer img_num;
	private String user;
	private String img_name;
	@Column(name = "post_time", nullable = true, insertable = false, updatable = false)
	private Date postTime;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	private Integer grade;

	public Integer getImg_num() {
		return img_num;
	}

	public void setImg_num(Integer img_num) {
		this.img_num = img_num;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

}
