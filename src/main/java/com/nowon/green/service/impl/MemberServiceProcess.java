package com.nowon.green.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nowon.green.domain.dto.MemberInsertDTO;
import com.nowon.green.domain.entity.MemberEntityRepository;
import com.nowon.green.security.MyRole;
import com.nowon.green.service.MemberService;


@Service
public class MemberServiceProcess implements MemberService {

	//DAO : mybatis(mapper) - jpa(repository)
	@Autowired
	private MemberEntityRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void save(MemberInsertDTO dto) {
		
		//회원가입정보(dto)를 테이블에 저장 
		repository.save(dto.toEntity(passwordEncoder).addRole(MyRole.USER));
		
		
	}

}
