package com.sseyeon.board.controller;

// URL 주소를 조합하는 방법

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sseyeon.board.service.BoardService;

@Controller
@RequestMapping(value="/boardTest")
// localhost:8081/board/boardTest/getBoardList
public class BoardController {
	@Inject
	private BoardService boardService;
	
	@RequestMapping(value="/getBoardList", method = RequestMethod.GET)
	public String getBoardList(Model model) throws Exception{
		model.addAttribute("boardList", boardService.getBoaList());
		return "boardUI/index";
	}
	
	
}
