package com.ssafy.safefood.service;

import java.util.List;

import com.ssafy.safefood.dto.Board;

public interface BoardService {
	List<Board> getNoticeBoardList(int page, int condition, String text);

	int getNoticeBoardPageSize(int condition, String text);

	int insertNoticeBoard(Board board);

}
