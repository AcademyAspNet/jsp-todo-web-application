package web.application.database;

import jakarta.servlet.ServletContext;

public class OracleDatabase extends Database {

	private static final int DEFAULT_PORT = 1521;
	
	public OracleDatabase(String ip, int port, Credentials credentials, String databaseName) {
		super(ip, port, credentials, databaseName);
	}
	
	public OracleDatabase(String ip, Credentials credentials, String databaseName) {
		super(ip, DEFAULT_PORT, credentials, databaseName);
	}
	
	public OracleDatabase(ServletContext servletContext) {
		super(servletContext);
	}
	
	protected String getConnectionString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("jdbc:oracle:thin:@");
		builder.append(ip).append(':').append(port);
		builder.append(":XE");
		
		return builder.toString();
	}
	
	protected String getDriver() {
		return "oracle.jdbc.driver.OracleDriver";
	}
}
