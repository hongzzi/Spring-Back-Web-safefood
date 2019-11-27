package com.ssafy.safefood.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.safefood.dto.Board;

@Repository
public class BoardRepositoryImpl implements BoardRepository{
	private final String MAPPER_STATEMENT = "sql.boardMapper.";
	private final int NOTICE_PAGE_SIZE = 15;
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<Board> getNoticeBoardList(int page, int condition, String text) {
		Map<String, Object> params = new HashMap<>();
		params.put("text", "%"+text+"%");
		params.put("condition", condition);
		params.put("pageStart", (page-1)*NOTICE_PAGE_SIZE);
		params.put("pageSize", NOTICE_PAGE_SIZE);
		return session.selectList(MAPPER_STATEMENT+"getNoticeBoardList", params);
	}

	@Override
	public int getNoticeBoardPageSize(int condition, String text) {
		Map<String, Object> params = new HashMap<>();
		params.put("text", "%"+text+"%");
		params.put("condition", condition);
		return session.selectOne(MAPPER_STATEMENT+"getNoticeBoardPageSize", params);
	}

	@Override
	public int insertNoticeBoard(Board board) {
		session.insert(MAPPER_STATEMENT+"insertNoticeBoard",board);
		return board.getBoardId();
	}

	@Override
	public int insertNoticeBoardView(int boardId) {
		return session.insert(MAPPER_STATEMENT+"insertNoticeBoardView", boardId);
	}

}
