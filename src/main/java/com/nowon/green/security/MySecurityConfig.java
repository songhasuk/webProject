package com.nowon.green.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
public class MySecurityConfig {
	
	
	//WelcomePageHandlerMapping
	
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorize -> 
			authorize
			//   /*, /**
				.antMatchers("/css/**","/js/**").permitAll()//static 자원들
				.antMatchers("/","/sign/signup","/common/**","/bbs/**").permitAll()//권한이 필요하지 않주 주소 나 주소패턴들..
				//위에서 설정한 주소이외 나머지 주소들은 인증을 하셔야되요::--인증을 하기위해서-->:로그인
				.anyRequest().authenticated()//
				//회원가입을 통해서..
			)
			//.formLogin();
			;
		
		http.formLogin(login->
						login
						.loginPage("/sign/signin")
						.usernameParameter("email")
						.passwordParameter("pass")
						.loginProcessingUrl("/sign/signin")//post security
						.permitAll()
						.successHandler(myAuthenticationSuccessHandler())
						//인증성공시 후처리 하는 핸들러
						//AuthenticationSuccessHandler
				
				);
			//.formLogin(withDefaults())
			//.httpBasic(withDefaults());
		//http.csrf().disable();
		return http.build();
	}
	
	@Bean
	AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		// TODO Auto-generated method stub
		return new MyAuthenticationSuccessHandler();
	}
	
	

}
