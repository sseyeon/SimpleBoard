package com.sseyeon.board.controller;

// URL 연결

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
		// return 은 페이지가 있는 곳으로 연결해야 함 
		return "boardUI/index";
	}
	
	// 글쓰기 화면
	@RequestMapping(value="/boardForm")
	public String boardForm(@ModelAttribute("boardVO") BoardVO vo, Model model) {
		return "boardUI/boardForm";
	}
	
	// 글 저장
	// @ModelAttribute("BoardVO") BoardVO boardVO : 화면에서 넘겨주는 값을 BoardVO와 매칭시켜 데이터를 받아옴
	// RedirectAttributes rttr : 글쓰기 이후 돌아가야할 페이지의 데이터를 전달하기 위한 인자
	// 저장버튼을 누를때 동작하는 페이지
	@RequestMapping(value="/saveBoard", method= RequestMethod.POST)
	public String saveBoard(@ModelAttribute("boardVO") BoardVO boardVO, @RequestParam("mode") String mode, RedirectAttributes rttr) throws Exception{
		
		if (mode.equals("edit")) {
			boardService.updateBoard(boardVO);
		} else {
			boardService.insertBoard(boardVO);
		}
		
		return "redirect:/boardTest/getBoardList";
	}
	
	// 글 상세조회
	@RequestMapping(value="/getBoardContent", method=RequestMethod.GET)
	public String getBoardContent(Model model, @RequestParam("bid") int bid) throws Exception{
		// service에서 받아온 데이터는 model.addAttribute를 통해 화면으로 전달 가능
		// "boardContent"라는 이름으로 화면에 데이터 전달
		model.addAttribute("boardContent", boardService.getBoardContent(bid));
		return "boardUI/boardContent";
	}
	
	// 입력폼의 수정을 위해 사용
	// 입력폼과 연계되는 페이지
	// controller의 메서드는 Model 이라는 타입의 객체를 파라미터로 받을 수 있다.
	// jsp 에서 request.setAttribute()와 비슷한 역할
	@RequestMapping(value="/editForm", method=RequestMethod.GET)
	public String editForm(@RequestParam("bid") int bid, @RequestParam("mode") String mode, Model model) throws Exception{
		model.addAttribute("boardContent", boardService.getBoardContent(bid));
		model.addAttribute("mode", mode);
		model.addAttribute("boardVO", new BoardVO());
		return "boardUI/boardForm";
	}
	
	
}
