package com.nowon.green.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.nowon.green.domain.dto.board.BoardUpdateDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@ToString
public class BoardEntity {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private long no;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false, columnDefinition = "text")
	private String content;
	
	private int readCount;
	
	@Column(columnDefinition = "timestamp")
	@CreationTimestamp
	private LocalDateTime createdDate;
	@Column(columnDefinition = "timestamp")
	@UpdateTimestamp
	private LocalDateTime updatedDate;
		
	//N:1
	@JoinColumn(name = "mno")//fk
	@ManyToOne(cascade = CascadeType.DETACH)//상대Entity에 영향, 읽기옵션
	private MemberEntity member;

	//제목,내용 수정하는 편의 메서드
	public BoardEntity update(BoardUpdateDTO dto) {
		title=dto.getTitle();
		content=dto.getContent();
		return this;
	}
	//조회수 증가 편의 메서드
	public BoardEntity incrementReadCount() {
		readCount++;
		return this;
	}

}
