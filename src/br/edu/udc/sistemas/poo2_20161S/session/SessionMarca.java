package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoMarca;
import br.edu.udc.sistemas.poo2_20161S.entity.Marca;

public class SessionMarca {

	public static void save(Marca marca) throws Exception {
		SessionMarca.save(marca,true);	
	}
	
	public static void save(Marca marca, boolean bCommit) throws Exception {
		DaoMarca dao = new DaoMarca();
		dao.save(marca);
		if (bCommit) {
			dao.commit();
		}
		dao.endTransaction();
	}
	
	public static void remove(Marca marca) throws Exception {
		SessionMarca.remove(marca,true);	
	}

	public static void remove(Marca marca, boolean bCommit) throws Exception {
		DaoMarca dao = new DaoMarca();
		dao.remove(marca);
		if (bCommit) {
			dao.commit();
		}
		dao.endTransaction();
	}
	
	public static Marca[] find(Marca marca) throws Exception {
		DaoMarca dao = new DaoMarca();
		Marca list[] = dao.find(marca);
		dao.endTransaction();
		return list;
	}
	
	public static Marca findByPrimary(Marca marca) throws Exception {
		DaoMarca dao = new DaoMarca();
		Marca marcaRetorno = dao.findByPrimary(marca);
		dao.endTransaction();
		return marcaRetorno;
	}	
}