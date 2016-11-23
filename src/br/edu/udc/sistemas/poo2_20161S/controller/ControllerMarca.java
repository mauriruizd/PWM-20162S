package br.edu.udc.sistemas.poo2_20161S.controller;

public class ControllerMarca extends Controller {

	public ControllerMarca() throws Exception {
		super("Marca");
	}

	@Override
	public void goNew() {
		request.setAttribute("nextPage","./marca/manter.jsp");
	}

	@Override
	public void goFind() {
		request.setAttribute("nextPage","./marca/consultar.jsp");
	}

	@Override
	public void save(Object obj) {
		request.setAttribute("msg","Marca salva com sucesso!");
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
}
