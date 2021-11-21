package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.util.PageNavigation;
import com.ssafy.happyhouse.model.CatDogDto;

public interface CatDogService {

	boolean registerArticle(CatDogDto catDogDto) throws Exception;
	List<CatDogDto> listArticle(Map<String, String> map) throws Exception;
	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	CatDogDto getArticle(int articleNo) throws Exception;
	void updateArticle(CatDogDto catDogDto) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
}
