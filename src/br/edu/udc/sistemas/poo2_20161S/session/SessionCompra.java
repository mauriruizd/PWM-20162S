package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoCompra;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoFornecedor;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoItemCompra;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoProduto;
import br.edu.udc.sistemas.poo2_20161S.entity.Compra;
import br.edu.udc.sistemas.poo2_20161S.entity.Fornecedor;
import br.edu.udc.sistemas.poo2_20161S.entity.ItemCompra;
import br.edu.udc.sistemas.poo2_20161S.entity.Produto;

public class SessionCompra extends Session {

	public SessionCompra() throws Exception {
		super(DaoCompra.class);
	}
	
	public Object[] find(Object object, boolean bCommit) throws Exception {
        Object listCompra[] = super.find(object,false);

        DaoFornecedor daoFornecedor = new DaoFornecedor(this.getDao().getConnection());

        for (int i = 0; i < listCompra.length; i++) {
                Compra compraAux = (Compra) listCompra[i];
                compraAux.setFornecedor( (Fornecedor) daoFornecedor.findByPrimary(compraAux.getFornecedor()));
        }
        if (bCommit) {
                this.getDao().commit();
        }
        return listCompra;
	}
	
	@Override
	public void save(Object object, boolean bCommit) throws Exception {
		Compra compra = (Compra) object;
		ItemCompra itens[] = compra.getItens();
		compra.setItens(null);
		super.save(object, false);
		
		DaoItemCompra daoItemCompra = new DaoItemCompra(this.getDao().getConnection());
		if (itens != null) {
			DaoProduto daoProduto = new DaoProduto(this.getDao().getConnection());
			for(int i = 0; i < itens.length; i++) {
				itens[i].setCompra(compra);
				daoItemCompra.save(itens[i]);
				Produto prod = (Produto) daoProduto.findByPrimary(itens[i].getProduto());
				prod.setQuantidade(prod.getQuantidade() + itens[i].getQuantidade());
				daoProduto.save(prod);
			}
		}
		
		if(bCommit) {
			this.getDao().commit();
		}
	}
	
	@Override
	public void remove(Object object) throws Exception {
		DaoItemCompra daoItemCompra = new DaoItemCompra(this.getDao().getConnection());
		ItemCompra item = new ItemCompra();
		item.setCompra((Compra) object);
		Object itens[] = daoItemCompra.find(item);
		for(int i = 0; i < itens.length; i++) {
			daoItemCompra.remove(itens[i]);
		}
		super.remove(object);
	}

}
