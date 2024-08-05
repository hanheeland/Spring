package kosa.di;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Main {
   public static void main(String[] args) {

	/*
	 * Resource resource = new ClassPathResource("applicationContext.xml");
	 * BeanFactory factory = new GenericXmlApplicationContext(resource);
	 */
	  
	   ApplicationContext factory = 
			   new AnnotationConfigApplicationContext(Factory.class); // 스프링 컨테이너 생성
      
      // writeService 객체를 가져온다.
      // 객체간의 의존성 주입되서 가져온다.(DI)
      Service service = (Service)factory.getBean("writeService2");
      service.insertService();
   }
}