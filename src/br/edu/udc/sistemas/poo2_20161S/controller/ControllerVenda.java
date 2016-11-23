package br.edu.udc.sistemas.poo2_20161S.controller;

import br.edu.udc.sistemas.poo2_20161S.entity.Cliente;
import br.edu.udc.sistemas.poo2_20161S.entity.Produto;
import br.edu.udc.sistemas.poo2_20161S.entity.Veiculo;
import br.edu.udc.sistemas.poo2_20161S.session.SessionCliente;
import br.edu.udc.sistemas.poo2_20161S.session.SessionProduto;
import br.edu.udc.sistemas.poo2_20161S.session.SessionVeiculo;

public class ControllerVenda extends Controller {

	public ControllerVenda() throws Exception {
		super("Compra");
	}

	@Override
	public void goNew() {
		request.setAttribute("listaCliente", this.getListaCliente());
		request.setAttribute("listaVeiculo", this.getListaVeiculo());
		request.setAttribute("listaProduto", this.getListaProdutos());
		request.setAttribute("nextPage","./venda/manter.jsp");
	}

	@Override
	public void goFind() {
		request.setAttribute("listaCliente", this.getListaCliente());
		request.setAttribute("nextPage","./venda/consultar.jsp");
	}

	@Override
	public void save(Object obj) {
		request.setAttribute("msg","Venda salva com sucesso!");
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
	
	private Object[] getListaCliente() {
		SessionCliente sessionCliente;
		try {
			sessionCliente = new SessionCliente();
			Object listaCliente[] = sessionCliente.find(new Cliente());
			return listaCliente;
		} catch (Exception e) {
			e.printStackTrace();
			return new Object[0];
		}
	}
	
	private Object[] getListaVeiculo() {
		SessionVeiculo sessionVeiculo;
		try {
			sessionVeiculo = new SessionVeiculo();
			Object listaVeiculo[] = sessionVeiculo.find(new Veiculo());
			return listaVeiculo;
		} catch (Exception e) {
			e.printStackTrace();
			return new Object[0];
		}
	}
}
