package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample2/*")
@Log4j
public class SampleController2 {

	@GetMapping("/all")
	public void doAll() {
		log.info("all access");
	}
	
	@GetMapping("/member")
	public void doMember() {
		log.info("member");
	}
	
	@GetMapping("/admin")
	public void doAdmin() {
		log.info("admin");
	}
}
