package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeService {

	public boolean writeArticle(NoticeDto NoticeDto) throws Exception;
	public List<NoticeDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	public BoardDto getArticle(int articleno) throws Exception;
	public void updateHit(int articleno) throws Exception;
	public boolean modifyArticle(NoticeDto NoticeDto) throws Exception;
	public boolean deleteArticle(int articleno) throws Exception;
	public Integer totalArticle(BoardParameterDto boardParameterDto) throws Exception;
	
}
