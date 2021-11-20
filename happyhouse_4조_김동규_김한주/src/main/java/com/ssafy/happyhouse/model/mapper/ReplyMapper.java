package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;

import com.ssafy.happyhouse.model.ReplyDto;

public interface ReplyMapper {

	int writeReply(ReplyDto replyDto) throws SQLException;

}
