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
		DaoVeiculo dao = new DaoVeiculo();
		dao.save(veiculo);
		if (bCommit) {
			dao.commit();
		}
		dao.endTransaction();
	}
	
	public static void remove(Veiculo veiculo) throws Exception {
		SessionVeiculo.remove(veiculo,true);	
	}

	public static void remove(Veiculo veiculo, boolean bCommit) throws Exception {
		DaoVeiculo dao = new DaoVeiculo();
		dao.remove(veiculo);
		if (bCommit) {
			dao.commit();
		}
		dao.endTransaction();
	}
	
	public static Veiculo[] find(Veiculo veiculo) throws Exception {
		DaoVeiculo dao = new DaoVeiculo();
		Veiculo listVeiculo[] =  dao.find(veiculo);
		for (int i = 0; i < listVeiculo.length; i++) {
			listVeiculo[i].setModelo(SessionModelo.findByPrimary(listVeiculo[i].getModelo()));
			listVeiculo[i].setCliente(SessionCliente.findByPrimary(listVeiculo[i].getCliente()));
		}
		dao.endTransaction();
		return listVeiculo;
	}
	
	public static Veiculo findByPrimary(Veiculo veiculo) throws Exception {
		DaoVeiculo dao = new DaoVeiculo();
		Veiculo veiculoRetorno = dao.findByPrimary(veiculo);
		dao.endTransaction();
		return veiculoRetorno;
	}	
}