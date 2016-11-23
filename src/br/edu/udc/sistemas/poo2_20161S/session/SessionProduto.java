package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoProduto;

public class SessionProduto extends Session {

	public SessionProduto() throws Exception {
		super(DaoProduto.class);
	}
}

