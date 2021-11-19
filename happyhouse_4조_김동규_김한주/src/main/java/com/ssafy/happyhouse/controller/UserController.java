package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtServiceImpl jwtService;
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@ApiOperation(value = "회원가입. 회원 정보를 입력한다. 아직 성공여부에 따른 반환값은 정해지지 않음 ")
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody @ApiParam(value = "회원가입시 필요한 회원정보", required = true)UserDto userDto) throws Exception {
		userService.registerMember(userDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		
	}
	
//	@ApiOperation(value = "회원가입", notes = "회원 가입")
//	@PostMapping("/register")
//	public ResponseEntity<String> register(@RequestBody @ApiParam(value = "회원가입시 필요한 회원정보", required = true) MemberDto memberDto) throws Exception{
//		
//		memberService.register(memberDto);
//		
//	}
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.login(userDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");	// key, data, subject
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			resultMap.put("로그인 실패: {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userid}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userid") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userid,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if(jwtService.isUsable(request.getHeader("access-token"))) {
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.userInfo(userid);
				resultMap.put("userInfo", userDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		
		return new ResponseEntity<Map<String,Object>> (resultMap, status);
	}
	
	//=======================================================================================
	//여기부터 구현 :  수정 / 탈퇴 , 아이디 중복체크 
	
	@ApiOperation(value = "로그아웃. 세션을 만료해서 회원 정보 접근을 막음")
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// 정보수정
	@ApiOperation(value = "회원 정보 수정. 회원정보를 보낸다. 수정 화면에 기존 정보를 살려서 표현하여 변경하고자 하는 정보만 입력할 수 있도록 제공", response = UserDto.class)
	@PutMapping("/update")
	public ResponseEntity<String> updateUser(@RequestBody @ApiParam(value = "변경할 회원정보", required = true) UserDto userDto) throws Exception {
		System.out.println(userDto.toString());
		userService.updateUser(userDto);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	@ApiOperation(value = "회원정보 삭제. 현재 로그인 된 회원의 정보를 삭제하고 token을 없앤다.")
	@DeleteMapping("/delete/{userid}")
	public ResponseEntity<String> deleteUser(@PathVariable("userid") @ApiParam(value = "삭제할 회원의 아이디.", required = true) String userid) throws Exception {
		userService.deleteUser(userid);		
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
//	ajax로 로그인
//	@PostMapping("/login")
//	@ResponseBody
//	public ResponseEntity<UserDto> login(@RequestBody Map<String, String> map, Model model, HttpSession session,
//			HttpServletResponse response) throws Exception {
//		System.out.println("로그인" + map.get("ssafy"));
//		UserDto userDto = userService.login(map);
////		System.out.println(userDto.toString());
//		if (userDto != null) {
//			session.setAttribute("userinfo", userDto);
//		}
//		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
//	}
}
