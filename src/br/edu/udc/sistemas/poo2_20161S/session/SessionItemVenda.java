package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoItemVenda;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoProduto;
import br.edu.udc.sistemas.poo2_20161S.entity.ItemVenda;
import br.edu.udc.sistemas.poo2_20161S.entity.Produto;

public class SessionItemVenda extends Session {

	public SessionItemVenda() throws Exception {
		super(DaoItemVenda.class);
	}
	
	public Object[] find(Object object, boolean bCommit) throws Exception {
		Object listItemVenda[] = super.find(object,false);

        DaoProduto daoProduto = new DaoProduto(this.getDao().getConnection());

        for (int i = 0; i < listItemVenda.length; i++) {
                ItemVenda modeloAux = (ItemVenda) listItemVenda[i];
                modeloAux.setProduto( (Produto) daoProduto.findByPrimary(modeloAux.getProduto()));
        }
        if (bCommit) {
                this.getDao().commit();
        }
        return listItemVenda;
	}

}
