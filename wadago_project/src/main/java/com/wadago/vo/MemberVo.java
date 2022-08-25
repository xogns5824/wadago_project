package com.wadago.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "member")
public class MemberVo {
	
	@Id
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	@Column(nullable = true, insertable = false, updatable = false)
	private Date signup_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birth;
	private int grade;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getSignup_time() {
		return signup_time;
	}

	public void setSignup_time(Date signup_time) {
		this.signup_time = signup_time;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", pw=" + pw + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", signup_time=" + signup_time + ", birth=" + birth + ", grade=" + grade + "]";
	}

}
