import org.junit.Test;

public class MySQLTest {
	@Test
	public void method() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.printf("driver class load succeed\n");
		}catch(Exception e) {
			System.out.printf("%s\n", e.getMessage());
			e.printStackTrace();
		}
	}
}
