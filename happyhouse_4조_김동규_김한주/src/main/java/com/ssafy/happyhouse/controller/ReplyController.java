package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 목록", notes = "해당 게시글의 모든 댓글 정보를 반환한다.", response = List.class)
	@GetMapping("/{boardid}")
	public ResponseEntity<List<ReplyDto>> listArticle(@PathVariable("boardid") @ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) int boardid) throws Exception {
		System.out.println(boardid);
		return new ResponseEntity<List<ReplyDto>>(replyService.listReply(boardid), HttpStatus.OK);
	}
}
