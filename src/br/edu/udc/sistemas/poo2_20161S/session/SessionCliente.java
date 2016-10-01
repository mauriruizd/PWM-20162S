package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoCliente;
import br.edu.udc.sistemas.poo2_20161S.entity.Cliente;

public class SessionCliente {
	public static void save(Cliente cliente) throws Exception {
		SessionCliente.save(cliente,true);	
	}
	
	public static void save(Cliente cliente, boolean bCommit) throws Exception {
		DaoCliente dao = new DaoCliente();
		dao.save(cliente);
		if (bCommit) {
			dao.commit();
		}
		dao.endTransaction();
	}
	
	public static void remove(Cliente cliente) throws Exception {
		SessionCliente.remove(cliente,true);	
	}

	public static void remove(Cliente cliente, boolean bCommit) throws Exception {
		DaoCliente dao = new DaoCliente();
		dao.remove(cliente);
		if (bCommit) {
			dao.commit();
		}
		dao.endTransaction();
	}
	
	public static Cliente[] find(Cliente cliente) throws Exception {
		DaoCliente dao = new DaoCliente();
		Cliente list[] = dao.find(cliente);
		dao.endTransaction();
		return list;
	}
	
	public static Cliente findByPrimary(Cliente cliente) throws Exception {
		DaoCliente dao = new DaoCliente();
		Cliente clienteRetorno = dao.findByPrimary(cliente);
		dao.endTransaction();
		return clienteRetorno;
	}	
}
