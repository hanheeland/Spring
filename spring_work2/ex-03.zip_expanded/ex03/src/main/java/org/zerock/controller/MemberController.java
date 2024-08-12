package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.MemberDTO;
import org.zerock.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@AllArgsConstructor
@Log4j
@RequestMapping("/member/*")
public class MemberController {
	
	private MemberService service;
	
	@GetMapping("/login")
	public String loginForm() {
		return "/member/loginForm";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam("id") String id, @RequestParam("password") String password , RedirectAttributes rttr) {
		rttr.addFlashAttribute("member", service.login(id, password));
		return "redirect:/cart/selProduct";
	}
	
	@GetMapping("/join")
	public String  joinForm() {
		return "/member/joinForm";
	}
	
	@PostMapping("/join")
	public String join(MemberDTO member, RedirectAttributes rttr) {
		if (service.join(member)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/member/login";
	}
	
}
