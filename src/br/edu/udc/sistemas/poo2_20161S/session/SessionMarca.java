package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoMarca;
import br.edu.udc.sistemas.poo2_20161S.entity.Marca;

public class SessionMarca {

	public static void save(Marca marca) throws Exception {
		SessionMarca.save(marca,true);	
	}
	
	public static void save(Marca marca, boolean bCommit) throws Exception {
		DaoMarca.save(marca);
		if (bCommit) {
			DaoMarca.commit();
		}
	}
	
	public static void remove(Marca marca) throws Exception {
		SessionMarca.remove(marca,true);	
	}

	public static void remove(Marca marca, boolean bCommit) throws Exception {
		DaoMarca.remove(marca);
		if (bCommit) {
			DaoMarca.commit();
		}
	}
	
	public static Marca[] find(Marca marca) throws Exception {
		return DaoMarca.find(marca);
	}
	
	public static Marca findByPrimary(Marca marca) throws Exception {
		return DaoMarca.findByPrimary(marca);
	}	
}