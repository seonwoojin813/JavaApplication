import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CharMain {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@192.168.0.100:1521/XEPDB1", "user06","user06");
				PreparedStatement pstmt = con.prepareStatement(
						"SELECT * FROM sample WHERE lower(MESSAGE) = 'hi'");){
		ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					System.out.printf(
							"num: %d message: %s date: %s\n",
							rs.getInt("num"), rs.getString("message").trim(),rs.getString("writedate"));
				}

		} catch (Exception e) {
			System.out.printf("%s\n", e.getMessage());
			e.printStackTrace();
		}

	}

}

