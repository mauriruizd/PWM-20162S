package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;

public class DaoProduto extends Dao {
	
	public DaoProduto() throws Exception {
		super();
	}
	
	public DaoProduto(Connection con) throws Exception {
		super(con);
	}
}
