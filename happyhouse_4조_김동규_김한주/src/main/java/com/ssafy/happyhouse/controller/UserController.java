package com.ssafy.happyhouse.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@ApiOperation(value = "회원가입. 회원 정보를 입력한다. 아직 성공여부에 따른 반환값은 정해지지 않음 ")
	@PostMapping("/register")
	public String register(UserDto userDto, Model model) throws Exception {
		userService.registerMember(userDto);
		return "redirect:/";
	}
	
	@ApiOperation(value = "로그인. 아이디와 비밀번호를 입력한다. 세션에 UserDto 정보를 넣어서 보낸다")
	@PostMapping("/login")
	public String login(@RequestParam Map<String, String> map, Model model, HttpSession session,
			HttpServletResponse response) throws Exception {
		System.out.println("로그인" + map.get("ssafy"));
		UserDto userDto = userService.login(map);
//		System.out.println(userDto.toString());
		if (userDto != null) {
			session.setAttribute("userinfo", userDto);
		}
		return "redirect:/";
	}
	@ApiOperation(value = "로그아웃. 세션을 만료해서 회원 정보 접근을 막음")
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	// 정보수정
	@ApiOperation(value = "회원 정보 수정. 회원정보를 보낸다. 수정 화면에 기존 정보를 살려서 표현하여 변경하고자 하는 정보만 입력할 수 있도록 제공", response = UserDto.class)
	@PutMapping("/update")
	@ResponseBody
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, Model model, HttpSession session,
			HttpServletResponse response) throws Exception {
		System.out.println(userDto.toString());
		userService.updateUser(userDto);
		session.setAttribute("userinfo", userDto);
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	@ApiOperation(value = "회원정보 삭제. 현재 로그인 된 회원의 정보를 삭제하고 세션을 만료시켜 로그인 전 화면으로 돌아가게 한다.")
	@DeleteMapping("/delete/{userid}")
	@ResponseBody
	public ResponseEntity<String> deleteUser(@PathVariable("userid") String userid, HttpSession session) throws Exception {
		userService.deleteUser(userid);
		session.invalidate();
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
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
