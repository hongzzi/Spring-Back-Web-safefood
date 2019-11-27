package com.ssafy.safefood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.safefood.dto.Board;
import com.ssafy.safefood.repo.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardRepository repo;
	
	@Override
	public List<Board> getNoticeBoardList(int page, int condition, String text) {
		return repo.getNoticeBoardList(page, condition, text);
	}

	@Override
	public int getNoticeBoardPageSize(int condition, String text) {
		return repo.getNoticeBoardPageSize(condition, text);
	}

	@Override
	public int insertNoticeBoard(Board board) {
		int boardId = repo.insertNoticeBoard(board);
		repo.insertNoticeBoardView(boardId);
		return boardId;
	}
	
}
		