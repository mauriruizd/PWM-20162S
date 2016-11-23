package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoFornecedor;

public class SessionFornecedor extends Session {

	public SessionFornecedor() throws Exception {
		super(DaoFornecedor.class);
	}
}

