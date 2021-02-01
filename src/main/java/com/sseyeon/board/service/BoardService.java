package com.sseyeon.board.service;

import java.util.List;
import java.util.Map;

import com.sseyeon.board.model.BoardVO;

public interface BoardService {
	public List<BoardVO> getBoaList() throws Exception;
}
