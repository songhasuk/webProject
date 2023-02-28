package com.nowon.green;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nowon.green.domain.dto.board.BoardInsertDTO;
import com.nowon.green.domain.entity.BoardEntityRepository;
import com.nowon.green.domain.entity.MemberEntityRepository;
import com.nowon.green.service.impl.BoardServiceProcess;

@SpringBootTest
class WebProjectApplicationTests {

	
	@Autowired
	BoardServiceProcess boardService;
	
	//@Test
	void 게시글_더미데이터삽입() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			BoardInsertDTO dto=new BoardInsertDTO();
			dto.setTitle("제목"+i);
			dto.setContent("내용"+i);
			
			String email="test01@test.com";
			if(i%2==0) {
				email="test02@test.com";
			}
			dto.setEmail(email);
			boardService.save(dto);
		});
		
	}

}
