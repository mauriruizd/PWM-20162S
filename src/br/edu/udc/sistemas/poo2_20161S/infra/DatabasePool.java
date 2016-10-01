package br.edu.udc.sistemas.poo2_20161S.infra;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabasePool {

	private static DatabasePool databasePool = null;

	private String userName;
	private String password;
	private String databaseName;
	private String driverClassName;
	private String url;
	private String host;
	private String port;
	
	private Connection connectionList[];
	private Boolean connectionStatus[];
	private Integer maxConnections;
	private Boolean testOnBorrow;
	private String sqlTest;
	

	public static DatabasePool getInstance() throws Exception  {
		if (DatabasePool.databasePool == null) {
			DatabasePool.databasePool = new DatabasePool();
		}
		return DatabasePool.databasePool;
	}

	private DatabasePool() throws Exception {
		InputStream input = null;
		Properties prop = new Properties();
		try {
			input = new FileInputStream(this.getClass().getResource("/").getFile() + "../config.properties");
			prop.load(input);
			
			this.userName = prop.getProperty("userName");
			this.password = prop.getProperty("password");
			this.databaseName = prop.getProperty("databaseName");
			this.driverClassName = prop.getProperty("driverClassName");
			this.host = prop.getProperty("host");
			this.port = prop.getProperty("port");
			this.url = prop.getProperty("url") + "://" + this.host + ":" + this.port + "/" + this.databaseName;
			
			this.maxConnections = Integer.parseInt(prop.getProperty("maxConnections"));
			this.testOnBorrow = prop.getProperty("testOnBorrow").equals("true");
			this.sqlTest = prop.getProperty("sqlTest");
			this.initialize();
		} catch(Exception e) {
			throw e;
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}
	
	private void initialize() throws Exception {
		this.connectionList = new Connection[this.maxConnections];
		this.connectionStatus = new Boolean[this.maxConnections];
		Class.forName(this.driverClassName);
		for(int i = 0; i < this.maxConnections; i++) {
			this.connect(i);
		}
	}
	
	private void connect(Integer connectionId) throws Exception {
		this.connectionList[connectionId] = DriverManager.getConnection(this.url, this.userName, this.password);
		this.connectionList[connectionId].setAutoCommit(false);
		this.connectionStatus[connectionId] = true;
	}
	
	private void testConnection(Integer connectionId) throws Exception {
		Connection con = this.connectionList[connectionId];
		if (this.testOnBorrow) {
			if (con == null || con.isClosed()) {
				this.connect(connectionId);
			} else {
				try {
					Statement stmt = con.createStatement();
					stmt.execute(this.sqlTest);
				} catch (SQLException e) {
					this.connect(connectionId);
				}
			}
		}
	}

	public Connection getConnection() throws Exception {
		Connection con = null;
		for (int i = 0; i < this.connectionStatus.length; i++) {
			if(this.connectionStatus[i]) {
				this.testConnection(i);
				con = this.connectionList[i];
				this.connectionStatus[i] = false;
				break;
			}
		}
		if (con == null) {
			throw new Exception("Não existem conexões disponíveis. Tente novamente.");
		}
		return con;
	}
	
	public void releaseConnection(Connection con) {
		for (int i = 0; i < this.connectionList.length; i++) {
			if(this.connectionList[i].equals(con)) {
				this.connectionStatus[i] = true;
				break;
			}
		}
	}
}