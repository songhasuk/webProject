package com.nowon.green.domain.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.nowon.green.domain.entity.MemberEntity;

import lombok.Setter;

@Setter
public class MemberInsertDTO {
	
	private String email;
	private String pass;
	private String name;
	
	//
	public MemberEntity toEntity(PasswordEncoder pe) {
		return MemberEntity.builder()
				.email(email)
				.pass(pe.encode(pass))//비밀번호 암호화해서 entity에 매핑
				.name(name)
				.build();
	}
	
}
