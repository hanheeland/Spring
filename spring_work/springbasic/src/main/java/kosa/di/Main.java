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


	 // 1. ������ �����̳� ���� (xml ���Ϸ� ����)
	 /*
	 * Resource resource = new ClassPathResource("applicationContext.xml");
	 * BeanFactory factory = new GenericXmlApplicationContext(resource);
	 */
	  
	   // 2. ������ �����̳� ���� (�ڹ� ���Ϸ� ����)
//	   ApplicationContext factory = 
//			   new AnnotationConfigApplicationContext(Factory.class); // ������ �����̳� ����
	   
	   String conFiguration[] = {
			   "applicationContext.xml",
			   "commonConcern.xml"
	   };
	   
	   // 3. ������ �����̳� ���� (aop ���Ϸ� ����)
	   ApplicationContext factory =
			   new ClassPathXmlApplicationContext(conFiguration);
	   
//      Service service = (Service)factory.getBean("writeService");
//      service.insertService();
	   
	   Kosa kosa = (Kosa)factory.getBean("kosaService");
	   kosa.insertService();
	   //kosa.list();
   }
}