package kosa.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // ���� ���Ϸ� ����Ѵ�.
public class Factory {
	
	@Bean
	public Dao oracleDao() { // �޼ҵ���� ���̵���� �ȴ�.
		return new OracleDao();
	}
	
	@Bean
	public Service writeService2() {
		return new WriteService();
	}
}
