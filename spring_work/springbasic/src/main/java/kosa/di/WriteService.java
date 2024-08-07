package kosa.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class WriteService implements Service {
	
	@Autowired
	@Qualifier("mySQLDao") // mySQLDao�� ���� �����Ѵ�.
	// ������Ʈ ��ĵ�� ������ �����̳ʰ� Ŭ���� �̸����� �����. ù�ڴ� �ҹ���.
	private Dao dao;
	
	public WriteService() {}
	
	public WriteService(Dao dao) {
		super();
		this.dao = dao;
	}

	public void insertService() {
		System.out.println("WriteService insertService ȣ��");
		dao.insertBoard();
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

}
