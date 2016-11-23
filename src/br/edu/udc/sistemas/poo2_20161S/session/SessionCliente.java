package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoCliente;

public class SessionCliente extends Session {

	public SessionCliente() throws Exception {
		super(DaoCliente.class);
	}
}

