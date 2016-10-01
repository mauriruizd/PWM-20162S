package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;

import br.edu.udc.sistemas.poo2_20161S.infra.DatabasePool;

public class Dao {

	protected Connection con;
	
	public Dao() throws Exception {
		this.con = DatabasePool.getInstance().getConnection();
	}
	
	public Dao(Connection con) throws Exception {
		this.con = con;
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	public void rollback() throws Exception {
		this.con.rollback();
	}
	
	public void commit() throws Exception {
		this.con.commit();
	}
	
	public void endTransaction() throws Exception {
		System.out.println("RELEASE");
		DatabasePool.getInstance().releaseConnection(this.con);
	}
	
}
