package org.zerock.controller;


import java.net.http.HttpHeaders;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*") // 경로를 sample로 한정짓고, 공통으로 사용하기위해 사용한다.
@Log4j
public class SampleController {
	
	@GetMapping("/basic")
	public void basicGet() {
		log.info("basic..........");
	}
	
	// 1. 객체로 받는다.
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("ex01...............");
		log.info("dto:" + dto);
		return "ex01";
	}
	
	// 2. @RequestParam으로 받는다.
	// 리턴 타입 : String
   @GetMapping("/ex02")
   public String ex02(@RequestParam("name") String name,
		   					@RequestParam("age") int age) {
      log.info("이름 : "+name);
      log.info("나이 : "+age);
      
      return "ex02";
   }
	
   // 리다이렉트 (페이지 이동시 데이터도 같이 전달하고싶을때)
	@GetMapping("/ex03")
	public String ex03(RedirectAttributes rttr) {
		rttr.addAttribute("name", "bbb");
		rttr.addAttribute("age", 30);
		rttr.addAttribute("page", 100);
		
		return "redirect:/sample/ex04";
	}
	
	// View => Controller => View (데이터 그대로 다른 view로 전달할때)
	// 객체는 전달되지만, 파라미터는 전달 안된다. 그래서 @ModelAttribute 사용한다.
	@GetMapping("/ex04")
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("ex04.......");
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "ex04";
	}
	
	// 리턴 타입 : void (@GetMapping이 return 페이지다.)
	// 컨트롤러가 void 일때는 sample 폴더를 만들어서 ex05 파일을 찾아야한다.
	@GetMapping("/ex05")
	public void ex05() {
		log.info("ex05.....");
	}
	
	// 리턴 타입 : 객체
	// json으로 데이터 반환하기 위해 @ResponseBody 사용.
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		SampleDTO dto = new SampleDTO();
		dto.setName("홍길동");
		dto.setAge(20);
		
		return dto; // json
	}
	
	// json 데이터에 메시지, 헤더, 상태 정보까지 추가로 전달 가능.
	@GetMapping("/ex07")
	public ResponseEntity<String> ex07() {
		log.info("ex07..........");
		
		// {"name":"홍길동"}
		String message = "{\"name\":\"홍길동\",\"age\":\"20\"}";
		
		org.springframework.http.HttpHeaders header = new org.springframework.http.HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(message, header, HttpStatus.OK);
	}
	
	//리턴타입: void exUpload.jsp 출력
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("exUpload....");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(List<MultipartFile> files) {
		files.forEach(file -> {
			log.info("----------------------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
		});
	}
	
	
}




