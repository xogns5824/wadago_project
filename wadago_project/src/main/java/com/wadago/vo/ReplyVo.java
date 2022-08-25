package com.wadago.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reply")
public class ReplyVo {

	@Id
	private Integer comment_num;
	private String writer;
	private String contents;
	private Integer board_num;
	@Column(nullable = true, insertable = false, updatable = false)
	private Date post_time;
	private Integer count;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getComment_num() {
		return comment_num;
	}

	public void setComment_num(Integer comment_num) {
		this.comment_num = comment_num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public Integer getBoard_num() {
		return board_num;
	}

	public void setBoard_num(Integer board_num) {
		this.board_num = board_num;
	}

	public Date getPost_time() {
		return post_time;
	}

	public void setPost_time(Date post_time) {
		this.post_time = post_time;
	}

	@Override
	public String toString() {
		return "ReplyVo [comment_num=" + comment_num + ", writer=" + writer + ", contents=" + contents + ", board_num="
				+ board_num + ", post_time=" + post_time + ", count=" + count + "]";
	}

}
