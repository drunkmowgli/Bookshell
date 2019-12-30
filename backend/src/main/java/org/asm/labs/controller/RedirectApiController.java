package org.asm.labs.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class RedirectApiController {

	@RequestMapping(value = "/books/**")
	public String redirectApi() {
		return "forward:/";
	}
}
