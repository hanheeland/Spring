package org.zerock.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {

	/*
	 * org.zerock.service.SampleService로 시작하는 클래스 호출 전에
	 * logBefore 메서드 실행시키겠다.
	 */
	@Before("execution(* org.zerock.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("---------------------------------------------");
	}
	
}
