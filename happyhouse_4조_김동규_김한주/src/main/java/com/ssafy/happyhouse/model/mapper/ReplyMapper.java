package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.ReplyDto;

public interface ReplyMapper {

	int writeReply(ReplyDto replyDto) throws SQLException;
	List<ReplyDto> listArticle(int boardid) throws SQLException;
	int deleteReply(int replyno) throws SQLException;

}
