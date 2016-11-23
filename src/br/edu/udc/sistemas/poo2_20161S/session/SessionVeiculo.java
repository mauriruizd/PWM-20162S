package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoCliente;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoModelo;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoVeiculo;
import br.edu.udc.sistemas.poo2_20161S.entity.Cliente;
import br.edu.udc.sistemas.poo2_20161S.entity.Modelo;
import br.edu.udc.sistemas.poo2_20161S.entity.Veiculo;

public class SessionVeiculo extends Session {

	public SessionVeiculo() throws Exception {
		super(DaoVeiculo.class);
	}
	
	public Object[] find(Object object, boolean bCommit) throws Exception {
        Object listVeiculo[] = super.find(object,false);

        DaoModelo daoModelo = new DaoModelo(this.getDao().getConnection());
        DaoCliente daoCliente = new DaoCliente(this.getDao().getConnection());

        for (int i = 0; i < listVeiculo.length; i++) {
                Veiculo veiculoAux = (Veiculo) listVeiculo[i];
                veiculoAux.setModelo( (Modelo) daoModelo.findByPrimary(veiculoAux.getModelo()));
                veiculoAux.setCliente( (Cliente) daoCliente.findByPrimary(veiculoAux.getCliente()));
        }
        if (bCommit) {
                this.getDao().commit();
        }
        return listVeiculo;
	} 
}
