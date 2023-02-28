package com.nowon.green.security;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.nowon.green.domain.entity.MemberEntity;

import lombok.Getter;

@Getter
public class MyUserDetails extends User{
	
	private static final long serialVersionUID = 1L;
	private String email;// username과동일하나 추가로 만들수 있다.
	private String name;//사용자 기준 :이름정보
	
	public MyUserDetails(MemberEntity e) {
		super(e.getEmail(), e.getPass(), e.getRoleset().stream()
				.map(role->new SimpleGrantedAuthority(role.role))
				.collect(Collectors.toSet()));
		
		email=e.getEmail();
		name=e.getName();
	}

}
