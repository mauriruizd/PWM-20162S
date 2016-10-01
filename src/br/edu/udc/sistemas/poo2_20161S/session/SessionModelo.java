package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoMarca;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoModelo;
import br.edu.udc.sistemas.poo2_20161S.entity.Modelo;

public class SessionModelo {

	public static void save(Modelo modelo) throws Exception {
		SessionModelo.save(modelo,true);	
	}
	
	public static void save(Modelo modelo, boolean bCommit) throws Exception {
		DaoModelo dao = new DaoModelo();
		dao.save(modelo);
		if (bCommit) {
			dao.commit();
		}
		dao.endTransaction();
	}
	
	public static void remove(Modelo modelo) throws Exception {
		SessionModelo.remove(modelo,true);	
	}

	public static void remove(Modelo modelo, boolean bCommit) throws Exception {
		DaoModelo dao = new DaoModelo();
		dao.remove(modelo);
		if (bCommit) {
			dao.commit();
		}
		dao.endTransaction();
	}
	
	public static Modelo[] find(Modelo modelo) throws Exception {
		DaoModelo dao = new DaoModelo();
		Modelo listModelo[] =  dao.find(modelo);
		for (int i = 0; i < listModelo.length; i++) {
			listModelo[i].setMarca(SessionMarca.findByPrimary(listModelo[i].getMarca()));
		}
		dao.endTransaction();
		return listModelo;
	}
	
	public static Modelo findByPrimary(Modelo modelo) throws Exception {
		DaoModelo dao = new DaoModelo();
		Modelo modeloRetorno = dao.findByPrimary(modelo);
		dao.endTransaction();
		return modeloRetorno;
	}	
}