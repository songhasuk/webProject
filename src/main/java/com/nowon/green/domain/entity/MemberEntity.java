package com.nowon.green.domain.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.nowon.green.security.MyRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name ="MyMember" )//"my_member"
@Entity
public class MemberEntity {//DB라고 생각합시다.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String pass;
	@Column(nullable = false)
	private String name;
	
	@Column(columnDefinition = "timestamp")
	@CreationTimestamp
	private LocalDateTime createdDate;
	@Column(columnDefinition = "timestamp")
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	//롤(ROLE): 1:N
	@Enumerated(EnumType.STRING)//저장유형 문자열로지정
	@ElementCollection(fetch = FetchType.EAGER) //@CollectionTable(name = "테이블이름")
	@Builder.Default
	private Set<MyRole> roleset=new HashSet<>(); 
	
	//role 저장하는 편의 메서드
	public MemberEntity addRole(MyRole role) {
		roleset.add(role);
		return this;
	}
	
	

}
