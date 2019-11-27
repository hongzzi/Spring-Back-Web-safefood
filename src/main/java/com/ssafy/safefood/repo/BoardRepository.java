package com.ssafy.safefood.repo;

import java.util.List;

import com.ssafy.safefood.dto.Board;

public interface BoardRepository {

	List<Board> getNoticeBoardList(int page, int condition, String text);

	int getNoticeBoardPageSize(int condition,String text);

	int insertNoticeBoard(Board board);

	int insertNoticeBoardView(int boardId);

}
