package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@GetMapping("/basic")
	public void basicGet() {
		log.info("basic..........");
	}
	
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info(dto);
		return "ex01";
	}
	
   @GetMapping("/ex02")
   public String ex02(@RequestParam("name") String name,
         @RequestParam("age") int age) {
      log.info("이름 : "+name);
      log.info("나이 : "+age);
      
      return "ex02";
   }
	
	@GetMapping("/ex03")
	public String ex03(RedirectAttributes rttr) {
		rttr.addAttribute("name", "bbb");
		rttr.addAttribute("age", 30);
		rttr.addAttribute("page", 100);
		
		return "redirect:/sample/ex04";
	}
	
	// View => Controller => View (가공하지 않은 데이터 전달할때)
	// 객체는 전달되지만, 파라미터는 전달 안된다. 그래서 @ModelAttribute 사용한다.
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("ex04.......");
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "ex04";
	}
	
}
