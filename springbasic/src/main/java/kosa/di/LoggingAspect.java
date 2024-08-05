package kosa.di;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

//������ɻ���
public class LoggingAspect {
	//�ٽɰ��ɻ���(insertService()) => ������ɻ��� �Բ� ȣ��(�ٽɰ��ɸ޼��� ȣ�� �ð��� ����)
	//Ÿ�̸� ���� -> Ÿ�̸� �۵� -> ���� �޼��� ȣ�� -> Ÿ�̸� ���� -> �α� ���
	private Log log = LogFactory.getLog(getClass());
	
	//������� �޼���
	//���� ȣ��(advice : around)
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("�α׽���");
		StopWatch stopWatch = new StopWatch();
		
		try {
			stopWatch.start();
			Object obj = joinPoint.proceed(); //insertService() ȣ������� ����.
			return obj;
		} catch (Exception e) {
			throw e;
		} finally {
			stopWatch.stop();
			log.info(joinPoint.getSignature().getName() + "�޼��� �����:" 
					+ stopWatch.getTotalTimeMillis());
		}
	}
}
