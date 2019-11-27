package com.ssafy.safefood.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Board {
	private int boardId;
	private String userId;
	private String userName;
	private String title;
	private String content;
	private int viewCount;
	private Timestamp insertTime;
	private Timestamp updateTime;
}
