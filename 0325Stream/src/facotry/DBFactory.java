package facotry;

public class DBFactory {
	public static DB create (String db) {
		switch(db) {
		case "Oracle":
			return new Oracle();
		case "MySQL":
			return new MySQL();
		default:
			return null;
		}
	}

}
