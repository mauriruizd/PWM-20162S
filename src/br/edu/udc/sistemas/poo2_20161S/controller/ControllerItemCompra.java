package br.edu.udc.sistemas.poo2_20161S.controller;

public class ControllerItemCompra extends Controller {

	public ControllerItemCompra() throws Exception {
		super("ItemCompra");
	}

	@Override
	public void goNew() {
		// request.setAttribute("nextPage","./modelo/manter.jsp");
	}

	@Override
	public void goFind() {
		request.setAttribute("nextPage","./itemcompra/consultar.jsp");
	}

	@Override
	public void save(Object obj) {
		request.setAttribute("msg","Item Compra salvo com sucesso!");
		request.setAttribute("object", obj);
		this.goNew();
	}

	@Override
	public void remove(Object obj) {
		// sthis.goFind();
	}

	@Override
	public void detail(Object obj) {
		// request.setAttribute("object",obj);
		// this.goNew();
	}
	
	@Override
	public void find(Object obj) {
		request.setAttribute("list",obj);
		this.goFind();
	}
	
}
