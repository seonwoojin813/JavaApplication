import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpSelect {

	public static void main(String[] args) {
		//EMP 테이블의 데이터를 변수에 저장해서 읽기
		//숫자 컬럼인 empno, 문자열인 ename, 날짜형식인 hiredate만 읽기
		
		//파일을 읽고 쓰기를 하거나 네트원크 작업 또는 데이터베이스 연동 시에는 반드시 예외 처리를 해야하고 close를 해야합니다.	
		try(Connection con = DriverManager.getConnection(
			"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1","user06","user06");
		PreparedStatement pstmt = con.prepareStatement(
				
				//emp테이블에서 empno, ename, hiredate 컬의 값만 가져오
				"select empno, ename, hiredate from emp");){	
			
			//SQL실행
			ResultSet rs = pstmt.executeQuery();
			
			//결과사용
			while(rs.next()) {
				//전부 문자열로 읽는 것이 가능합니다.
				String empno = rs.getString("empno");
				String ename = rs.getString("ename");
				//String hiredate = rs.getString("hiredate");
				//날짜로 받기
				//java.util.Date-날짜와 시간 모두 저장, java.sql.Date-날짜만 저
				Date hiredate = rs.getDate("hiredate");
				
				//데이터 출력
				System.out.printf("사번: %s 이름:%s 입사일:%s\n", empno, ename, hiredate);
				
			}
		}catch(Exception e) {
			System.out.printf("%s\n", e.getMessage());
			e.printStackTrace();
		}

	}

}
