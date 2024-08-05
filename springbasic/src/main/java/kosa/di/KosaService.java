package kosa.di;

import org.springframework.stereotype.Service;

@Service
public class KosaService implements Kosa{
	public void insertService() {
		System.out.println("Kosa insertService()");
	}
	
	public void list() {
		System.out.println("kosa list()");
	}
}
