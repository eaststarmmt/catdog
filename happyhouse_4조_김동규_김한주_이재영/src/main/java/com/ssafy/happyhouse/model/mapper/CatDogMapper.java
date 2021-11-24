package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.CatDogDto;
import com.ssafy.happyhouse.model.FileInfoDto;

public interface CatDogMapper {

	int writeArticle(CatDogDto catDogDto) throws Exception;
	void registerFile(CatDogDto catDogDto) throws Exception;
	List<CatDogDto> listArticle(Map<String, Object> map) throws Exception;
	int getTotalCount(Map<String, String> map) throws Exception;
	CatDogDto getArticle(int articleNo) throws Exception;
	void updateArticle(CatDogDto catDogDto) throws Exception;
	void deleteFile(int articleNo) throws Exception;
	void deleteArticle(int articleNo) throws Exception;
	List<FileInfoDto> fileInfoList(int articleNo) throws Exception;
}
