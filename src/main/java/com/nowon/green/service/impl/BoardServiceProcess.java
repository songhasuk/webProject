package com.nowon.green.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.nowon.green.domain.dto.board.BoardDetailDTO;
import com.nowon.green.domain.dto.board.BoardInsertDTO;
import com.nowon.green.domain.dto.board.BoardListDTO;
import com.nowon.green.domain.dto.board.BoardUpdateDTO;
import com.nowon.green.domain.entity.BoardEntity;
import com.nowon.green.domain.entity.BoardEntityRepository;
import com.nowon.green.domain.entity.MemberEntityRepository;
import com.nowon.green.service.BoardService;
import com.nowon.green.utils.PaggingDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@RequiredArgsConstructor
@Service
public class BoardServiceProcess implements BoardService {

	//dao
	private final BoardEntityRepository repository;
	private final MemberEntityRepository mRepository;
	
	@Override
	public void delete(long no) {
		repository.deleteById(no);
	}
	
	//수정처리
	@Transactional
	@Override
	public void update(long no, BoardUpdateDTO dto) {
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		log.debug(">>>>> 조회 이후 수정 쿼리가 처리되어야합니다.");
		repository.findById(no).map(e->e.update(dto));
		log.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	
	}
	
	@Override
	public void save(BoardInsertDTO dto) {
		//??작성자는 어떻게 하나요? 
		repository.save(dto.toEntity(
				mRepository.findByEmail(dto.getEmail()).orElseThrow()
				));
		
	}
	
	@Value("${pageblock}")
	private int blockSize;
	
	@Override
	public void list(Model model, int page, int size) {
		model.addAttribute("today", LocalDate.now());
		
		//page,size 동적처리하기위해 url ? parameter를 활요합시다.
		//int page=0; //zero-based page index.
		//int size=5; //the size of the page to be returned.
		Sort sort=Sort.by(Direction.DESC, "no");
		Pageable pageable=PageRequest.of(page-1, size, sort);
		
		Page<BoardEntity> result=repository.findAll(pageable);
		
		int totalPages=result.getTotalPages();//전체 페이지수
		
		//int blockSize=5;
		
		model.addAttribute("pd", PaggingDto.createPagging(page, blockSize, totalPages));
//		1 2 3 4 5 6 7 8 9 10 : 1
//		11 12 13 14 15 16 17 18 19 20 : 2
      //21 22
		
		//if(totalPages)
		
		
		model.addAttribute("list", repository.findAll(pageable).stream()
				.map(BoardListDTO::new)
				.collect(Collectors.toList()));
		
	}
	
	
	public void list2(Model model, String name) {
		
		List<BoardEntity> searchlist = repository.findByTitleContaining(name);
		System.out.println("[불러온 데이터]"+searchlist);
		model.addAttribute("searchlist", searchlist.stream().map(BoardListDTO::new).collect(Collectors.toList()));
		
	}
	
	//상세페이지 구현
	@Transactional
	@Override
	public void detail(long no, Model model) {
		//no - Board의 pk
		Optional<BoardEntity> result=repository.findById(no);
		model.addAttribute("detail", result.map(BoardDetailDTO::new).get());
		result.map(e->e.incrementReadCount());
		System.out.println("[update 데이터]"+result);
		
	}

	

	

}
