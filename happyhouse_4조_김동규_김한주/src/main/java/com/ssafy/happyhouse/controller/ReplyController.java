package com.ssafy.happyhouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.ReplyDto;
import com.ssafy.happyhouse.model.service.ReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/reply")
@Api("댓글 컨트롤러")
public class ReplyController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ReplyService replyService;
	
	@ApiOperation(value = "댓글 작성", notes = "작성할 댓글 내용을 입력한다. 그리고 DB입력 성공 여부에 따라 'success' or 'fail'을 리턴한다.")
	@PostMapping
	public ResponseEntity<String> writeReply(@RequestBody @ApiParam(value = "댓글 정보.", required = true) ReplyDto replyDto) throws Exception {
		if(replyService.writeReply(replyDto)) {
			new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}
