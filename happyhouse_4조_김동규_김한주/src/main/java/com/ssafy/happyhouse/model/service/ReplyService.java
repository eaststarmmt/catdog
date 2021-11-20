package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.ReplyDto;

public interface ReplyService {

	boolean writeReply(ReplyDto replyDto) throws Exception;

}
