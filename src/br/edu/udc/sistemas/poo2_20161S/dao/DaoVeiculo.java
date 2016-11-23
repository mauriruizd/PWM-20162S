package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;

public class DaoVeiculo extends Dao {
	
	public DaoVeiculo() throws Exception {
		super();
	}
	
	public DaoVeiculo(Connection con) throws Exception {
		super(con);
	}
}
