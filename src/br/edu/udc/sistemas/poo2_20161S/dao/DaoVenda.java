package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;

public class DaoVenda extends Dao {
	
	public DaoVenda() throws Exception {
		super();
	}
	
	public DaoVenda(Connection con) throws Exception {
		super(con);
	}
}
