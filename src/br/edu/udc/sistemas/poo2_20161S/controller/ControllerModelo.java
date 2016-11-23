package br.edu.udc.sistemas.poo2_20161S.controller;

import br.edu.udc.sistemas.poo2_20161S.entity.Marca;
import br.edu.udc.sistemas.poo2_20161S.session.SessionMarca;

public class ControllerModelo extends Controller {

	public ControllerModelo() throws Exception {
		super("Modelo");
	}

	@Override
	public void goNew() {
		request.setAttribute("listaMarca", this.getListaMarca());
		request.setAttribute("nextPage","./modelo/manter.jsp");
	}

	@Override
	public void goFind() {
		request.setAttribute("listaMarca", this.getListaMarca());
		request.setAttribute("nextPage","./modelo/consultar.jsp");
	}

	@Override
	public void save(Object obj) {
		request.setAttribute("msg","Modelo salvo com sucesso!");
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
	
	private Object[] getListaMarca() {
		SessionMarca sessionMarca;
		try {
			sessionMarca = new SessionMarca();
			Object listaMarca[] = sessionMarca.find(new Marca());
			return listaMarca;
		} catch (Exception e) {
			e.printStackTrace();
			return new Object[0];
		}
	}
}
