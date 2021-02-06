package com.sseyeon.board.controller;

// URL �ּҸ� �����ϴ� ���

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sseyeon.board.model.BoardVO;
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
	
	// �۾��� ȭ�� 
	@RequestMapping(value="/boardForm")
	public String boardForm() {
		return "boardUI/boardForm";
	}
	
	// �� ����
	@RequestMapping(value="/saveBoard", method= RequestMethod.POST)
	// @ModelAttribute("BoardVO") BoardVO boardVO : ȭ�鿡�� �Ѱ��ִ� ���� BoardVO�� ��Ī���� �����͸� �޾ƿ�
	// RedirectAttributes rttr : �۾��� ���� ���ư����� �������� �����͸� �����ϱ� ���� ����
	public String saveBoard(@ModelAttribute("BoardVO") BoardVO boardVO, RedirectAttributes rttr) throws Exception{
		boardService.insertBoard(boardVO);
		return "redirect:/boardUI/getBoardList";
	}
	
	// �� ����ȸ
	@RequestMapping(value="/getBoardContent", method=RequestMethod.GET)
	public String getBoardContent(Model model, @RequestParam("bid") int bid) throws Exception{
		// service���� �޾ƿ� �����ʹ� model.addAttribute�� ���� ȭ������ ���� ����
		// "boardContent"��� �̸����� ȭ�鿡 �����͸� ����
		model.addAttribute("boardContent", boardService.getBoardContent(bid));
		return "boardUI/boardContent";
	}
	
	
}
