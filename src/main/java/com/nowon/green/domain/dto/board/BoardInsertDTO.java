package com.nowon.green.domain.dto.board;

import com.nowon.green.domain.entity.BoardEntity;
import com.nowon.green.domain.entity.MemberEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardInsertDTO {
	
	private String title;
	private String content;
	private String email;
	
	public BoardEntity toEntity(MemberEntity memberEntity) {
		return BoardEntity.builder()
				.title(title).content(content)
				.member(memberEntity)
				.build();
	}

}
