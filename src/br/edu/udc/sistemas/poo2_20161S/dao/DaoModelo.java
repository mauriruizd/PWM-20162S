package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;

public class DaoModelo extends Dao {
	
	public DaoModelo() throws Exception {
		super();
	}
	
	public DaoModelo(Connection con) throws Exception {
		super(con);
	}

}
