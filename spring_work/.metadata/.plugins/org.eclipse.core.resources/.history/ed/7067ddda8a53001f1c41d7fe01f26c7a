package kosa.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@org.springframework.stereotype.Service
public class WriteService implements Service {
	
	@Autowired
	@Qualifier("mySQLDao") // mySQLDao를 먼저 선택한다.
	private Dao dao;
	
	public WriteService() {}
	
	public WriteService(Dao dao) {
		super();
		this.dao = dao;
	}

	public void insertService() {
		System.out.println("WriteService insertService 호출");
		dao.insertBoard();
	}

	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

}
