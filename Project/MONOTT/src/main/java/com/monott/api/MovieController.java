package com.monott.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MovieController {

	@RequestMapping("/movieChart")
	public String movieChart() {
		return "movie_api";
	}
}
