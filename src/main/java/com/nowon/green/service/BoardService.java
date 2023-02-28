package com.nowon.green.service;

import org.springframework.ui.Model;

import com.nowon.green.domain.dto.board.BoardInsertDTO;
import com.nowon.green.domain.dto.board.BoardUpdateDTO;

public interface BoardService {

	void save(BoardInsertDTO dto);

	void list(Model model, int page, int size);

	void detail(long no, Model model);

	void update(long no, BoardUpdateDTO dto);

	void delete(long no);

	void list2(Model model, String name);

}
