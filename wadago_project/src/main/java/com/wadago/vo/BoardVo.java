package com.wadago.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "board")
public class BoardVo {

	@Id
	private Integer board_num;
	private String writer;
	private String contents;
	@Column(name = "post_time", nullable = true, insertable = false, updatable = false)
	private Date postTime;

	@Formula(value = "(SELECT COUNT(r.count) FROM reply r WHERE r.board_num=board_num)")
	Integer replyCount;

	public Integer getBoard_num() {
		return board_num;
	}

	public void setBoard_num(Integer board_num) {
		this.board_num = board_num;
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

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	@Override
	public String toString() {
		return "BoardVo [board_num=" + board_num + ", writer=" + writer + ", contents=" + contents + ", postTime="
				+ postTime + ", replyCount=" + replyCount + "]";
	}

}
