package com.monott.user;


import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupForm {

	@NotEmpty(message = "ID는 필수항목입니다.")
	private String ID;
	
	@NotEmpty(message = "비밀번호를 입력해주세요.")
	private String Password1;
	
	@NotEmpty(message = "비밀번호 확인을 입력해주세요.")
	private String Password2;
	
	@NotEmpty(message = "이름은 필수항목입니다.")
	private String Name;
	
	@NotEmpty(message = "전화번호는 필수항목입니다.")
	private String Phone;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date Birth;
	
	private String Address;
}
