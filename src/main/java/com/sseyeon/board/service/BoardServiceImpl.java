package com.sseyeon.board.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sseyeon.board.dao.BoardDAO;
import com.sseyeon.board.model.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> getBoaList() throws Exception {
		return boardDAO.getBoardList();
	}

	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		boardDAO.insertBoard(boardVO);
	}

	@Override
	public BoardVO getBoardContent(int bid) throws Exception {
		boardDAO.updateViewCnt(bid);
		return boardDAO.getBoardContent(bid);
	}

}
