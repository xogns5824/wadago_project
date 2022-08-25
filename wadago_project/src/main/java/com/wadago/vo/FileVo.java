package com.wadago.vo;

import org.springframework.web.multipart.MultipartFile;

public class FileVo {

	private String img_name, user;
	private MultipartFile uploadfile;
	private Integer img_num, grade;

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public MultipartFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}

	public Integer getImg_num() {
		return img_num;
	}

	public void setImg_num(Integer img_num) {
		this.img_num = img_num;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

}
