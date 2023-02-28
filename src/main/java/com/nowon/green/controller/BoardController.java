package com.nowon.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nowon.green.domain.dto.board.BoardInsertDTO;
import com.nowon.green.domain.dto.board.BoardUpdateDTO;
import com.nowon.green.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	
	
	private final BoardService service;
	
	
	//ajax 요청
	@ResponseBody
	@DeleteMapping("/bbs/boards/{no}")
	public void update(@PathVariable long no) {
		service.delete(no);
	}
	
	@PutMapping("/bbs/boards/{no}")
	public String update(@PathVariable long no, BoardUpdateDTO dto) {
		service.update(no, dto);
		return "redirect:/bbs/boards/"+no;
	}
	
	
	//상세페이지 
	@GetMapping(path = {"/bbs/boards/{no}"})
	public String detail(@PathVariable long no ,Model model) {
		service.detail(no, model);
		return "bbs/board/detail";
	}
	
	
	//게시글 페이지이동 및 게시글 데이터 읽어오기
	@GetMapping("/bbs/boards")///bbs/boards?page=0&size=5
	public String list(Model model, 
			@RequestParam(defaultValue = "1") int page, 
			@RequestParam(defaultValue = "5") int size) {
		service.list(model, page, size);
		return "bbs/board/list";
		//spring.thymeleaf.prefix=classpath:/templates/
		//spring.thymeleaf.suffix=.html
	}
	
	@GetMapping("bbs/searchs")
	public String search(String name, Model model) {
		System.out.println("게시글"+name);
		service.list2(model, name);
		return "bbs/board/search";
	}
	
	//게시글 저장하기
	@PostMapping("/bbs/board")
	public String save(BoardInsertDTO dto) {
		
		service.save(dto);
		
		return"redirect:/bbs/boards";
	}
	
	@GetMapping("/auth/board")
	public String write() {
		return "bbs/board/write";
	}

}
