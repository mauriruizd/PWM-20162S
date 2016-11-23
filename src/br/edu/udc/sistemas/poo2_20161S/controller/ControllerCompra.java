package br.edu.udc.sistemas.poo2_20161S.controller;

import br.edu.udc.sistemas.poo2_20161S.entity.Fornecedor;
import br.edu.udc.sistemas.poo2_20161S.entity.Produto;
import br.edu.udc.sistemas.poo2_20161S.session.SessionFornecedor;
import br.edu.udc.sistemas.poo2_20161S.session.SessionProduto;

public class ControllerCompra extends Controller {

	public ControllerCompra() throws Exception {
		super("Compra");
	}

	@Override
	public void goNew() {
		request.setAttribute("listaFornecedor", this.getListaFornecedor());
		request.setAttribute("listaProduto", this.getListaProdutos());
		request.setAttribute("nextPage","./compra/manter.jsp");
	}

	@Override
	public void goFind() {
		request.setAttribute("listaFornecedor", this.getListaFornecedor());
		request.setAttribute("nextPage","./compra/consultar.jsp");
	}

	@Override
	public void save(Object obj) {
		request.setAttribute("msg","Compra salva com sucesso!");
		request.setAttribute("object", obj);
		this.goNew();
	}

	@Override
	public void remove(Object obj) {
		this.goFind();
	}

	@Override
	public void detail(Object obj) {
		request.setAttribute("object",obj);
		this.goNew();
	}
	
	@Override
	public void find(Object obj) {
		request.setAttribute("list",obj);
		this.goFind();
	}
	
	private Object[] getListaProdutos() {
		SessionProduto sessionProduto;
		try {
			sessionProduto = new SessionProduto();
			Object listaProduto[] = sessionProduto.find(new Produto());
			return listaProduto;
		} catch (Exception e) {
			e.printStackTrace();
			return new Object[0];
		}
	}
	
	private Object[] getListaFornecedor() {
		SessionFornecedor sessionFornecedor;
		try {
			sessionFornecedor = new SessionFornecedor();
			Object listaFornecedor[] = sessionFornecedor.find(new Fornecedor());
			return listaFornecedor;
		} catch (Exception e) {
			e.printStackTrace();
			return new Object[0];
		}
	}
}
