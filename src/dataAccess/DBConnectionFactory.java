package dataAccess;

public class DBConnectionFactory {
	public JDBConnectionWrapper getConnectionWrapper(boolean test) {
		if (test) {
			return new JDBConnectionWrapper("test_library");
		}
		return new JDBConnectionWrapper("library");
	}

}
