package com.monott.user;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@GetMapping("/login")
    public String login() {
        return "login";
    }

	@GetMapping("/signup")
	public String signup(UserSignupForm userSignupForm) {
		return "signup_form";
	}

	@PostMapping("/signup")
	public String signup(@Valid UserSignupForm userSignupForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		if (!userSignupForm.getPassword1().equals(userSignupForm.getPassword2())) {
			bindingResult.rejectValue("Password2", "passwordInCorrect", "2개의 비밀번호가 일치하지 않습니다.");

			return "signup_form";
		}

		userService.create(userSignupForm.getID(), userSignupForm.getPassword1(), userSignupForm.getName(),
				userSignupForm.getPhone(), userSignupForm.getBirth(), userSignupForm.getAddress());

		return "redirect:/";
	}
}
