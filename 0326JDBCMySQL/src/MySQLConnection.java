import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MySQLConnection {

	public static void main(String[] args) {
		//1.드라이버 클래스를 로드-처음에 한번만 수행
		try {
			//이 부분에서 오류가 발생하면 드라이버 클래스 이름 오류이거나 드라이버 파일을 프로젝트의 build path에 추가하지 않은 것입니다.
			Class.forName("com.mysql.jdbc.Driver");
		}catch(Exception e) {
			System.out.printf("Driver class load:%s\n", e.getMessage());
			e.printStackTrace();
		}
		
		/*
		//2.데이터베이스 연결
		//try()({}: try with resource 구문으로 AutoCloeable 인터페이스를 implements 한 클래스의 인스턴스를 생성하는 구문을 삽입할 수 있는데 이 경우에는
		//close()를 호출하지 않아도 영역을 어나면 자동으로 호출합니다.
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8","root","1111");
				//MySQL은 인코딩 방식을 설정하지 않으면 에러는 뜨지 않지만 한글이 제대로 출력되지 않습니다.
				//SQL 실행 객체 - 입력받아서 넣는 자리는 ?로 설정 
				PreparedStatement pstmt = con.prepareStatement(
						"insert into usertbl(userid, name, birthyear, addr, mobile, mdate) "
						+ "values(?,?,?,?,?,?)");){
		//SQL 실행 객체에 ?가 있으면 ?에 실제 데이터를 대입 - 바인딩
			pstmt.setString(1,"moon");
			pstmt.setString(2, "문정원");
			pstmt.setInt(3, 1992);
			pstmt.setString(4,"광");
			pstmt.setString(5, "01022115578");
			Calendar cal = new GregorianCalendar(1992,3,24);
			Date date = new Date(cal.getTimeInMillis());
			pstmt.setDate(6, date);
			
			//SQL실행
			//select는 ResultSet(List 나 일반 객체 또는 스칼라 데이터)으로 리턴
			//나머지 SQL은 전부 정수(영향받은 행의 개수)로 리턴되기 때문에 정수의 값을 가지고 성공여부를 판단해야 합니다.
			
			//삽입은 0보다 크면 성공입니다.
			int result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.printf("Succeed\n");
			}else {
				System.out.printf("Failed\n");
			}
		*/
		
		/*
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8","root","1111");
				PreparedStatement pstmt = con.prepareStatement(
						"update usertbl set mobile = ? where userid = ?");){
			
				pstmt.setString(1, "01000000000");
				pstmt.setString(2, "moon");
				
				int result = pstmt.executeUpdate();
				//수정과 삭제는 0보다 크면 수정할 데이터가 있어서 수정을 한 것이고 0이 리턴되면 수정할 데이터가 없어서 수정을 안 한 것입니다.
				if(result > 0) {
					System.out.printf("Succeed\n");
				}else {
					System.out.printf("there is no data\n");
				}
			*/
		
		/*
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8","root","1111");
				PreparedStatement pstmt = con.prepareStatement(
						"select avg(price) from buytbl");){
			
			double avg = -1;
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				avg = rs.getDouble("avg(price)");
			}
			System.out.printf("average: %f\n", avg);
			
			rs.close();
					*/
		
		/*
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8","root","1111");
				PreparedStatement pstmt = con.prepareStatement(
						"select userid from buytbl group by userid");){
		List<String> list = new ArrayList<String>();
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String temp = rs.getString("userid");
			list.add(temp);
		}
		rs.close();
		System.out.printf("%s\n", list);		
		*/
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sample?useUnicode=yes&characterEncoding=UTF-8","root","1111");
				PreparedStatement pstmt = con.prepareStatement(
						"select userid, sum(price*amount) buy from buytbl group by userid");){
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("userid", rs.getString("userid"));
				map.put("sum", rs.getInt("buy"));
				
				list.add(map);
			}
			rs.close();
			System.out.printf("%s\n", list);
		
		
		
		}catch(Exception e) {
			System.out.printf("Database use exception:%s\n", e.getMessage());
			e.printStackTrace();
		}	
		
	}

}





















