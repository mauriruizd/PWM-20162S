package br.edu.udc.sistemas.poo2_20161S.controller;

public class ControllerFornecedor extends Controller {

	public ControllerFornecedor() throws Exception {
		super("Fornecedor");
	}

	@Override
	public void goNew() {
		request.setAttribute("nextPage","./fornecedor/manter.jsp");
	}

	@Override
	public void goFind() {
		request.setAttribute("nextPage","./fornecedor/consultar.jsp");
	}

	@Override
	public void save(Object obj) {
		request.setAttribute("msg","Fornecedor salvo com sucesso!");
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
