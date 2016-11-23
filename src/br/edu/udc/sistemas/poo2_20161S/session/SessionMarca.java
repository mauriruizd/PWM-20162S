package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoMarca;

public class SessionMarca extends Session {

	public SessionMarca() throws Exception {
		super(DaoMarca.class);
	}
}
