package kosa.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 파일로 사용한다.
public class Factory {
	
	@Bean
	public Dao oracleDao() { // 메소드명이 아이디명이 된다.
		return new OracleDao();
	}
	
	@Bean
	public Service writeService2() {
		return new WriteService();
	}
}
