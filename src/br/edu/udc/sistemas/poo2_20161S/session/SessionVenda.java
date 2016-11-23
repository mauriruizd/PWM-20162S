package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoCliente;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoItemVenda;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoVeiculo;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoVenda;
import br.edu.udc.sistemas.poo2_20161S.entity.Cliente;
import br.edu.udc.sistemas.poo2_20161S.entity.ItemVenda;
import br.edu.udc.sistemas.poo2_20161S.entity.Veiculo;
import br.edu.udc.sistemas.poo2_20161S.entity.Venda;

public class SessionVenda extends Session {

	public SessionVenda() throws Exception {
		super(DaoVenda.class);
	}
	
	public Object[] find(Object object, boolean bCommit) throws Exception {
        Object listVenda[] = super.find(object,false);

        DaoCliente daoCliente = new DaoCliente(this.getDao().getConnection());
        DaoVeiculo daoVeiculo = new DaoVeiculo(this.getDao().getConnection());

        for (int i = 0; i < listVenda.length; i++) {
                Venda vendaAux = (Venda) listVenda[i];
                vendaAux.setCliente( (Cliente) daoCliente.findByPrimary(vendaAux.getCliente()));
                vendaAux.setVeiculo( (Veiculo) daoVeiculo.findByPrimary(vendaAux.getVeiculo()));
        }
        if (bCommit) {
                this.getDao().commit();
        }
        return listVenda;
	}
	
	@Override
	public void save(Object object, boolean bCommit) throws Exception {
		Venda venda = (Venda) object;
		ItemVenda itens[] = venda.getItens();
		venda.setItens(null);
		super.save(object, bCommit);
		
		DaoItemVenda daoItemVenda = new DaoItemVenda(this.getDao().getConnection());
		if (itens != null) {
			for(int i = 0; i < itens.length; i++) {
				itens[i].setVenda(venda);
				daoItemVenda.save(itens[i]);
			}
		}
	}
	
	@Override
	public void remove(Object object) throws Exception {
		DaoItemVenda daoItemVenda = new DaoItemVenda(this.getDao().getConnection());
		ItemVenda item = new ItemVenda();
		item.setVenda((Venda) object);
		Object itens[] = daoItemVenda.find(item);
		for(int i = 0; i < itens.length; i++) {
			daoItemVenda.remove(itens[i]);
		}
		super.remove(object);
	}

}
