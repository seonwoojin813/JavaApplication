import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UseTransaction {

	public static void main(String[] args) {
			try (Connection con = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user06","user06");
					PreparedStatement pstmt = con.prepareStatement(
							"insert into sample(num, message) values(?, ?)");){
				
				//commit이나 rollback을 직접 수행할 수 있도록 설
				con.setAutoCommit(false);
				
				pstmt.setInt(1, 12);
				pstmt.setString(2, "키움");
				
				pstmt.executeUpdate();
				System.out.printf("삽입성공");
				//현재까지의 작업 내용을 바로 반영
				con.commit();
				Thread.sleep(30000);
				
				 
				//작업취소
				//con.rollback();

			} catch (Exception e) {
				System.out.printf("%s\n", e.getMessage());
				e.printStackTrace();
			}

		}


	

}
