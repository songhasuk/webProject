package com.nowon.green.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	//HttpSessionRequestCache
	private final RequestCache requestCache=new HttpSessionRequestCache();
	
	//Spring Security가 화면 이동에 대한 규칙을 정의
	private final RedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//super.onAuthenticationSuccess(request, response, authentication);
		System.out.println(">>>>>인증성공 후 처리하는 로직");
		//로그인실패시에 저장된 에러 세션제거
		clearAuthenticationAttributes(request);
		
		
		String prevPage=(String) request.getSession().getAttribute("prevPage");
		
		if(prevPage !=null) {
			request.getSession().removeAttribute("prevPage");
		}
		String url="/";
		
		//savedRequest 존재하는경우-> 인증권한이 없는 페이지 접근
		SavedRequest savedRequest=requestCache.getRequest(request, response);
		//시큐리티에의해서 로그인페이지로 이동한경우는 객체가존재하고 직접 로그인 페이지로 이동했을때는 null
		System.out.println("savedRequest:"+savedRequest);
		
		if(savedRequest != null) {
			url=savedRequest.getRedirectUrl();
		}else if(prevPage !=null && !prevPage.equals("")){
			url=prevPage;
		}
		System.out.println("페이지이동 주소:"+url);
		redirectStrategy.sendRedirect(request, response, url);
		
	}

	

}
