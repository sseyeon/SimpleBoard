package com.sseyeon.board.service;

import java.util.List;
import java.util.Map;

import com.sseyeon.board.model.BoardVO;

public interface BoardService {
	public List<BoardVO> getBoaList() throws Exception;
	public void insertBoard(BoardVO boardVO) throws Exception;
	public BoardVO getBoardContent(int bid) throws Exception;
	public void updateBoard(BoardVO boardVO) throws Exception;
}
