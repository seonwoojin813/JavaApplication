package dao;

import java.util.List;

import domain.Sawon;

public interface SawonDAO {
	
	//데이터를 삽입하는 메소드 
 	public int insertSawon(Sawon sawon);
 	
	//전체데이터를 가져오는 메소드(where 조건(매개변수) 없음) 
	public List<Sawon>getAllSawon();
	
	//name을 가지고 전체데이터 가져오기
	public List<Sawon> getNameSawon(String name);
	
	//데이터를 수정하는 메소드
	public int updateSawon(Sawon sawon);
	
	//데이터를 삭제하는 메소드
	public int deleteSawon(int num);

}
