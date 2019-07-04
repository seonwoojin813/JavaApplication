import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ProcedureMain {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user06","user06");
				PreparedStatement pstmt = con.prepareStatement(
						"insert into sample(num, message, weitedate) values(?, ?, ?)");
				CallableStatement cstmt = con.prepareCall(
						"{call insertsample(?,?,?)}");) {
			//?에 값을 바인딩
			cstmt.setInt(1, 2);;
			cstmt.setString(2, "도로공사가 지다니");
			//java.sql.Date는 바로 만들 수가 없어서 Calendar 객체를 만들고 Date로 변
			Calendar calendar = new GregorianCalendar();
			java.sql.Date today = new java.sql.Date(calendar.getTimeInMillis());
			cstmt.setDate(3, today);
			
			//cstmt 실행
			int r = cstmt.executeUpdate();
			if(r>0) {
				System.out.printf("삽입성공");
			}

		} catch (Exception e) {
			System.out.printf("%s\n", e.getMessage());
			e.printStackTrace();
		}

	}

}
