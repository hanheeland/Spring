package kosa.di;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Main {
   public static void main(String[] args) {


	 // 1. 스프링 컨테이너 생성 (xml 파일로 생성)
	 /*
	 * Resource resource = new ClassPathResource("applicationContext.xml");
	 * BeanFactory factory = new GenericXmlApplicationContext(resource);
	 */
	  
	   // 2. 스프링 컨테이너 생성 (자바 파일로 생성)
//	   ApplicationContext factory = 
//			   new AnnotationConfigApplicationContext(Factory.class); // 스프링 컨테이너 생성
	   
	   String conFiguration[] = {
			   "applicationContext.xml",
			   "commonConcern.xml"
	   };
	   
	   // 3. 스프링 컨테이너 생성 (aop 파일로 생성)
	   ApplicationContext factory =
			   new ClassPathXmlApplicationContext(conFiguration);
	   
//      Service service = (Service)factory.getBean("writeService");
//      service.insertService();
	   
	   Kosa kosa = (Kosa)factory.getBean("kosaService");
	   kosa.insertService();
	   //kosa.list();
   }
}