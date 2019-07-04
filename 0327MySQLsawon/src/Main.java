
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.sql.Date;
import dao.SawonDAO;
import dao.SawonDAOImpl;
import domain.Sawon;

public class Main {

	public static void main(String[] args) {
		//DAO인스턴스 생성
		SawonDAO sawonDAO = SawonDAOImpl.getInstance();
		
		/*
		Sawon sawon = new Sawon();
		sawon.setName("ㅇ");
		sawon.setPhone("01022224444");
		sawon.setAddr("MU");
		Calendar cal = new GregorianCalendar(1994,5,17);
		Date birth = new Date(cal.getTimeInMillis());
		sawon.setBirth(birth);
		sawon.setEmail("ddd@gmail.com");
		
		int r = sawonDAO.insertSawon(sawon);
		
		if(r>0) {
			System.out.printf("Data insert Succeed\n");
		}else {
			System.out.printf("Data insert Failed\n");
			}
			*/
		
		/*
		//전체데이터 가져오기 테스트
		List<Sawon> list = sawonDAO.getAllSawon();
		//테스트 할 때는 바로 출력]
		//System.out.printf("%s\n", list);
		
		//세부내용을 확인할 때는 빠른 열거 사용
		for(Sawon sawon : list) {
			System.out.printf("%s\n", sawon);
		}
		*/
		
		/*
		//이름으로 가져오기 테스트
		List<Sawon> list = sawonDAO.getNameSawon("나");
		//테스트 할 때는 바로 출력
		//System.out.printf("%s\n", list);
		
		//세부내용을 확인할 때는 빠른 열거 사용
		for(Sawon sawon : list) {
			System.out.printf("%s\n", sawon);
		}	
		*/
		
		/*
		Sawon sawon = new Sawon();
		sawon.setNum(6);
		sawon.setName("피글");
		sawon.setPhone("77722224444");
		sawon.setAddr("P");
		Calendar cal = new GregorianCalendar(1984,5,17);
		Date birth = new Date(cal.getTimeInMillis());
		sawon.setBirth(birth);
		sawon.setEmail("ddd@gmail.com");
		
		int r = sawonDAO.updateSawon(sawon);
		
		if(r>=0) {
			System.out.printf("Data update Succeed\n");
		}else {
			System.out.printf("Data update Failed\n");
			}
		*/
		
		int r = sawonDAO.deleteSawon(4);
		
		if(r>=0) {
			System.out.printf("Data update Succeed\n");
		}else {
			System.out.printf("Data update Failed\n");
			}
		
		
		
		
		
	}

}
