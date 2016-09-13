package br.edu.udc.sistemas.poo2_20161S.session;

import br.edu.udc.sistemas.poo2_20161S.dao.DaoCliente;
import br.edu.udc.sistemas.poo2_20161S.entity.Cliente;

public class SessionCliente {
	public static void save(Cliente cliente) throws Exception {
		SessionCliente.save(cliente,true);	
	}
	
	public static void save(Cliente cliente, boolean bCommit) throws Exception {
		DaoCliente.save(cliente);
		if (bCommit) {
			DaoCliente.commit();
		}
	}
	
	public static void remove(Cliente cliente) throws Exception {
		SessionCliente.remove(cliente,true);	
	}

	public static void remove(Cliente cliente, boolean bCommit) throws Exception {
		DaoCliente.remove(cliente);
		if (bCommit) {
			DaoCliente.commit();
		}
	}
	
	public static Cliente[] find(Cliente cliente) throws Exception {
		return DaoCliente.find(cliente);
	}
	
	public static Cliente findByPrimary(Cliente cliente) throws Exception {
		return DaoCliente.findByPrimary(cliente);
	}	
}
