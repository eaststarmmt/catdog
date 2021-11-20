package com.ssafy.happyhouse.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.ReplyDto;
import com.ssafy.happyhouse.model.mapper.ReplyMapper;
@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean writeReply(ReplyDto replyDto) throws Exception {
		// 내용이 없거나 로그인이 풀린 경우
		if(replyDto.getContent() == null || replyDto.getUserid() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(ReplyMapper.class).writeReply(replyDto) == 1;
	}

}
