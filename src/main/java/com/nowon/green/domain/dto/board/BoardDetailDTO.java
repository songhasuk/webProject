package com.nowon.green.domain.dto.board;

import java.time.LocalDateTime;

import com.nowon.green.domain.entity.BoardEntity;

import lombok.Getter;

@Getter
public class BoardDetailDTO{
	private long no;
	private String title;
	private String content;
	private int readCount;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	private String email;
	private String userName;
	
	public BoardDetailDTO(BoardEntity e) {
		this.no = e.getNo();
		this.title = e.getTitle();
		this.content = e.getContent();
		this.readCount = e.getReadCount();
		this.createdDate = e.getCreatedDate();
		this.updatedDate = e.getUpdatedDate();
		//작성자 MemberEntity 로 연결되어있어서..
		this.email = e.getMember().getEmail();
		this.userName = e.getMember().getName();
	}

}
