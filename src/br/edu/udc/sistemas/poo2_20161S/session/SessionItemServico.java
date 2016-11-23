package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoItemServico;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoProduto;
import br.edu.udc.sistemas.poo2_20161S.entity.ItemServico;
import br.edu.udc.sistemas.poo2_20161S.entity.Produto;

public class SessionItemServico extends Session {

	public SessionItemServico() throws Exception {
		super(DaoItemServico.class);
	}
	
	public Object[] find(Object object, boolean bCommit) throws Exception {
		Object listItemServico[] = super.find(object,false);

        DaoProduto daoProduto = new DaoProduto(this.getDao().getConnection());

        for (int i = 0; i < listItemServico.length; i++) {
                ItemServico modeloAux = (ItemServico) listItemServico[i];
                modeloAux.setProduto( (Produto) daoProduto.findByPrimary(modeloAux.getProduto()));
        }
        if (bCommit) {
                this.getDao().commit();
        }
        return listItemServico;
	}

}
