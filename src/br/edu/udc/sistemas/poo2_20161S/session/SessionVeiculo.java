package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoCliente;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoModelo;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoVeiculo;
import br.edu.udc.sistemas.poo2_20161S.entity.Veiculo;

public class SessionVeiculo {

	public static void save(Veiculo veiculo) throws Exception {
		SessionVeiculo.save(veiculo,true);	
	}
	
	public static void save(Veiculo veiculo, boolean bCommit) throws Exception {
		DaoVeiculo.save(veiculo);
		if (bCommit) {
			DaoVeiculo.commit();
		}
	}
	
	public static void remove(Veiculo veiculo) throws Exception {
		SessionVeiculo.remove(veiculo,true);	
	}

	public static void remove(Veiculo veiculo, boolean bCommit) throws Exception {
		DaoVeiculo.remove(veiculo);
		if (bCommit) {
			DaoVeiculo.commit();
		}
	}
	
	public static Veiculo[] find(Veiculo veiculo) throws Exception {
		Veiculo listVeiculo[] =  DaoVeiculo.find(veiculo);
		
		for (int i = 0; i < listVeiculo.length; i++) {
			listVeiculo[i].setModelo(DaoModelo.findByPrimary(listVeiculo[i].getModelo()));
			listVeiculo[i].setCliente(DaoCliente.findByPrimary(listVeiculo[i].getCliente()));
		}
		
		return listVeiculo;
	}
	
	public static Veiculo findByPrimary(Veiculo veiculo) throws Exception {
		return DaoVeiculo.findByPrimary(veiculo);
	}	
}