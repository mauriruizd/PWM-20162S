package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoMarca;
import br.edu.udc.sistemas.poo2_20161S.dao.DaoModelo;
import br.edu.udc.sistemas.poo2_20161S.entity.Modelo;

public class SessionModelo {

	public static void save(Modelo modelo) throws Exception {
		SessionModelo.save(modelo,true);	
	}
	
	public static void save(Modelo modelo, boolean bCommit) throws Exception {
		DaoModelo.save(modelo);
		if (bCommit) {
			DaoModelo.commit();
		}
	}
	
	public static void remove(Modelo modelo) throws Exception {
		SessionModelo.remove(modelo,true);	
	}

	public static void remove(Modelo modelo, boolean bCommit) throws Exception {
		DaoModelo.remove(modelo);
		if (bCommit) {
			DaoModelo.commit();
		}
	}
	
	public static Modelo[] find(Modelo modelo) throws Exception {
		Modelo listModelo[] =  DaoModelo.find(modelo);
		
		for (int i = 0; i < listModelo.length; i++) {
			listModelo[i].setMarca(DaoMarca.findByPrimary(listModelo[i].getMarca()));
		}
		
		return listModelo;
	}
	
	public static Modelo findByPrimary(Modelo modelo) throws Exception {
		return DaoModelo.findByPrimary(modelo);
	}	
}