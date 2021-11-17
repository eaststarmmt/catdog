package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserDto : 회원 정보", description = "회원의 정보를 나타낸다.")
public class UserDto {
	@ApiModelProperty(value = "아이디")
	private String userid;
	@ApiModelProperty(value = "비밀번호")
	private String password;
	@ApiModelProperty(value = "이름")
	private String name;
	@ApiModelProperty(value = "전화번호", example="010-1111-1111")
	private String phone;
	@ApiModelProperty(value = "주소")
	private String address;
	
	public UserDto() {
	}
	
	public UserDto(String userid, String password, String name, String phone, String address) {
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}


	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserDto [userid=" + userid + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", address=" + address + "]";
	}
	
}
