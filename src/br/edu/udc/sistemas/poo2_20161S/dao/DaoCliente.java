package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;

public class DaoCliente extends Dao {
	
	public DaoCliente() throws Exception {
		super();
	}
	
	public DaoCliente(Connection con) throws Exception {
		super(con);
	}
}
