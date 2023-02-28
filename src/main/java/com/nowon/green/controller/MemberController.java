package com.nowon.green.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nowon.green.domain.dto.MemberInsertDTO;
import com.nowon.green.service.MemberService;


@Controller
public class MemberController {

	
	@Autowired	
	private  MemberService service;
	

	@GetMapping("/sign/signin")
	public String login(HttpServletRequest request) {
		String prevPageUrl=request.getHeader("Referer");
		System.out.println("로그인이전페이지정보 :"+prevPageUrl);
		
		//다이렉트로 주소입력하고 들어온경우null:로그인실패후 재시도시or 회원가입시
		if(prevPageUrl!=null && !prevPageUrl.contains("/sign")) {
			request.getSession().setAttribute("prevPage", prevPageUrl);
		}
		
		return "sign/signin";//View - thymeleaf엔진
		//spring.thymeleaf.prefix=classpath:/templates/
		//spring.thymeleaf.suffix=.html
	}
	
	@GetMapping("/sign/signup")
	public void signup() {}
	
	@PostMapping("/sign/signup")
	public String signup(MemberInsertDTO dto) {
		
		service.save(dto);
		
		return "redirect:/sign/signin";
	}

}
