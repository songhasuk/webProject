package com.nowon.green.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nowon.green.domain.entity.MemberEntity;
import com.nowon.green.domain.entity.MemberEntityRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

	//DAO
	@Autowired
	private MemberEntityRepository memberEntityRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("email:"+email);
		//DB에 회원이 존재하는지 확인
		//쿼리메서드
		Optional<MemberEntity> result=memberEntityRepository.findByEmail(email);
		if(result.isEmpty()) throw new UsernameNotFoundException("존재하지 않는 회원");
		
		return new MyUserDetails(result.get());
	}
	
}
