package br.edu.udc.sistemas.poo2_20161S.controller;

import br.edu.udc.sistemas.poo2_20161S.entity.Cliente;
import br.edu.udc.sistemas.poo2_20161S.entity.Modelo;
import br.edu.udc.sistemas.poo2_20161S.session.SessionCliente;
import br.edu.udc.sistemas.poo2_20161S.session.SessionModelo;

public class ControllerVeiculo extends Controller {

	public ControllerVeiculo() throws Exception {
		super("Veiculo");
	}

	@Override
	public void goNew() {
		request.setAttribute("listaCliente", this.getListaCliente());
		request.setAttribute("listaModelo", this.getListaModelo());
		request.setAttribute("nextPage","./veiculo/manter.jsp");
	}

	@Override
	public void goFind() {
		request.setAttribute("listaCliente", this.getListaCliente());
		request.setAttribute("listaModelo", this.getListaModelo());
		request.setAttribute("nextPage","./veiculo/consultar.jsp");
	}

	@Override
	public void save(Object obj) {
		request.setAttribute("msg","Veiculo salvo com sucesso!");
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
	
	private Object[] getListaModelo() {
		SessionModelo sessionModelo;
		try {
			sessionModelo = new SessionModelo();
			Object listaModelo[] = sessionModelo.find(new Modelo());
			return listaModelo;
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

}
