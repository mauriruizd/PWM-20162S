package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;

public class DaoServico extends Dao {
	
	public DaoServico() throws Exception {
		super();
	}
	
	public DaoServico(Connection con) throws Exception {
		super(con);
	}
}
