package org.zerock.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;
import org.zerock.domain.Ticket;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class SampleController {
	
	@GetMapping("/getSample")
	public SampleVO getSample() {
		return new SampleVO(100, "홍", "길동");
	}
	
	@GetMapping("/getList")
	public List<SampleVO> getList() {
		return IntStream.range(1, 10).mapToObj(
				i -> new SampleVO(i, "first" + i, "last" + i))
				.collect(Collectors.toList());
	}
	
	// @RequestBody는 json 데이터를 객체로 변환하는 용도
	@PostMapping("/ticket")
	public Ticket convert(@RequestBody Ticket ticket) {
		log.info("convert........." + ticket);
		
		return ticket;
	}
	
}
