package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoServico;

public class SessionServico extends Session {

	public SessionServico() throws Exception {
		super(DaoServico.class);
	}   

}
