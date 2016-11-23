package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;

public class DaoFornecedor extends Dao {
	
	public DaoFornecedor() throws Exception {
		super();
	}
	
	public DaoFornecedor(Connection con) throws Exception {
		super(con);
	}
}
