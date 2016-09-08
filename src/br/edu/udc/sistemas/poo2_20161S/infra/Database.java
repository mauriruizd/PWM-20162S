package br.edu.udc.sistemas.poo2_20161S.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe implementada no padr�o de projeto SINGLETON Permite somente uma
 * inst�ncia ativa da classe
 * 
 * 1. Atributo est�tico e privado do tipo da classe para guardar a inst�ncia
 * ativa;
 * 
 * 2. Construtor privado para impedir que os programadores criem inst�ncias;
 * 
 * 3. M�todo est�tico e p�blico para fornecer a inst�ncia.
 */
public class Database {

	private static Database database = null;
	
	//JDBC - JAVA DATABASE CONNECTION
	// * API - APPLICATION INTERFACE
	// * Define interfaces para que os fabricantes de banco de dados
	//   implementem as bibliotecas para o java conversar

	private Connection connection;
	private String host;
	private String port;
	private String userName;
	private String password;
	private String databaseName;
	private String driverClassName;
	

	public static Database getInstance() throws Exception  {
		if (Database.database == null) {
			Database.database = new Database();
		}
		return Database.database;
	}

	private Database() throws Exception {
		this.driverClassName = "org.postgresql.Driver";
		this.userName = "postgres";
		this.password = "postgres";
		this.host = "localhost";
		this.port = "5432";
		this.databaseName = "udc_aplicada5";		
		this.connect();
	}
	
	private void connect() throws Exception {
		//import din�mico
		Class.forName(this.driverClassName);
		String url = "jdbc:postgresql://" + this.host + ":" + this.port + "/" + this.databaseName;		
		this.connection = DriverManager.getConnection(url, this.userName, this.password);
		this.connection.setAutoCommit(false);
	}

	public Connection getConnection() {
		return this.connection;
	}
}