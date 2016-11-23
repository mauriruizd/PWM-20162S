package br.edu.udc.sistemas.poo2_20161S.controller;

public class ControllerServico extends Controller {

	public ControllerServico() throws Exception {
		super("Servico");
	}

	@Override
	public void goNew() {
		request.setAttribute("nextPage","./servico/manter.jsp");
	}

	@Override
	public void goFind() {
		request.setAttribute("nextPage","./servico/consultar.jsp");
	}

	@Override
	public void save(Object obj) {
		request.setAttribute("msg","Servico salvo com sucesso!");
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
