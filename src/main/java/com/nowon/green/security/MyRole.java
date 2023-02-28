package com.nowon.green.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MyRole {
	
	USER("ROLE_USER","회원"),//0
	ADMIN("ROLE_ADMIN","관리자");//1

	final String role;
	final String title;
	

}
