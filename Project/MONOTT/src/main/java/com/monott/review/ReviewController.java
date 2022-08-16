package com.monott.review;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReviewController {
	
	@RequestMapping("/review/list")
	public String reviewList() {
		return "review_list";
	}
}
