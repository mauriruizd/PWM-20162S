package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoFornecedor;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoItemCompra;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoProduto;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoProduto;
import br.edu.udc.sistemas.poo2_20161S.entity.Compra;
import br.edu.udc.sistemas.poo2_20161S.entity.Fornecedor;
import br.edu.udc.sistemas.poo2_20161S.entity.Produto;
import br.edu.udc.sistemas.poo2_20161S.entity.ItemCompra;

public class SessionItemCompra extends Session {

	public SessionItemCompra() throws Exception {
		super(DaoItemCompra.class);
	}     
	
	public Object[] find(Object object, boolean bCommit) throws Exception {
		Object listItemCompra[] = super.find(object,false);

        DaoProduto daoProduto = new DaoProduto(this.getDao().getConnection());

        for (int i = 0; i < listItemCompra.length; i++) {
                ItemCompra modeloAux = (ItemCompra) listItemCompra[i];
                modeloAux.setProduto( (Produto) daoProduto.findByPrimary(modeloAux.getProduto()));
        }
        if (bCommit) {
                this.getDao().commit();
        }
        return listItemCompra;
	}

}
