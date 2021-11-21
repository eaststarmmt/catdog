package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.ReplyDto;

public interface ReplyService {

	boolean writeReply(ReplyDto replyDto) throws Exception;
	List<ReplyDto> listReply(int boardid) throws Exception;

}
