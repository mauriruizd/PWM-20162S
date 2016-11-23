package br.edu.udc.sistemas.poo2_20161S.controller;

public class ControllerProduto extends Controller {

	public ControllerProduto() throws Exception {
		super("Produto");
	}

	@Override
	public void goNew() {
		request.setAttribute("nextPage","./produto/manter.jsp");
	}

	@Override
	public void goFind() {
		request.setAttribute("nextPage","./produto/consultar.jsp");
	}

	@Override
	public void save(Object obj) {
		request.setAttribute("msg","Produto salvo com sucesso!");
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
