package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import br.edu.udc.sistemas.poo2_20161S.entity.Cliente;
import br.edu.udc.sistemas.poo2_20161S.entity.Marca;
import br.edu.udc.sistemas.poo2_20161S.infra.DatabasePool;

public class DaoCliente extends Dao {
	
	public DaoCliente() throws Exception {
		super();
	}
	
	public DaoCliente(Connection con) throws Exception {
		super(con);
		// TODO Auto-generated constructor stub
	}

	public void save(Cliente cliente) throws Exception {
		if (cliente != null) {
			Connection con = DatabasePool.getInstance().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rst = null;
			String sql = "";
			try {
				if ((cliente.getIdCliente() != null) &&
					(cliente.getIdCliente() > 0)) {
					sql = "update clientes set " +
					      "nome = '" + cliente.getNome() + "' " +
					      "rg = '" + cliente.getRg() + "' " +
					      "cpf = '" + cliente.getCpf() + "' " +
					      "data_nascimento = '" + cliente.getDataNascimento() + "' " +
					      "logradouro = '" + cliente.getLogradouro() + "' " +
					      "numero = '" + cliente.getNumero() + "' " +
					      "bairro = '" + cliente.getBairro() + "' " +
					      "cidade = '" + cliente.getCidade() + "' " +
					      "estado = '" + cliente.getEstado() + "' " +
					      "cep = '" + cliente.getCep() + "' " +
					      "where idcliente = " + cliente.getIdCliente();
					System.out.println(sql);
					stmt.execute(sql);
				} else {
					sql = "insert into clientes (nome, rg, cpf, data_nascimento, logradouro," +
							"numero, bairro, cidade, estado, cep) " +
							"values('" + cliente.getNome() + "','" + 
							cliente.getRg() + "','" + 
							cliente.getCpf() + "','" + 
							cliente.getDataNascimento().toString() + "','" + 
							cliente.getLogradouro() + "','" + 
							cliente.getNumero() + "','" + 
							cliente.getBairro() + "','" + 
							cliente.getCidade() + "','" + 
							cliente.getEstado() + "','" + 
							cliente.getCep() + "')";
					System.out.println(sql);
					stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
					rst = stmt.getGeneratedKeys();
					if (rst.next()) {
						cliente.setIdCliente(rst.getInt(1));
					}
				}
			} catch (Exception e) {
				try {
					this.rollback();
				} catch (Exception e2) {}
				throw e;
			} finally {
				try {
					stmt.close();
					if (rst != null) {
						rst.close();
					}
				} catch (Exception e) {}
			}
		}
	}

	public void remove(Cliente cliente) throws Exception {
		if ((cliente != null) && (cliente.getIdCliente() != null)) {
			Connection con = DatabasePool.getInstance().getConnection();
			Statement stmt = con.createStatement();
			try {
				String sql = "delete from clientes " + "where idcliente = " + cliente.getIdCliente();
				System.out.println(sql);
				stmt.execute(sql);
			} catch (Exception e) {
				try {
					this.rollback();
				} catch (Exception e2) {}
				throw e;
			} finally {
				try {
					stmt.close();
				} catch (Exception e) {}
			}			
		}
	}

	public Cliente[] find(Cliente cliente) throws Exception {
		Collection <Cliente> result = new Vector <Cliente> ();
		Connection con = DatabasePool.getInstance().getConnection();
		Statement stmt = con.createStatement();
		ResultSet rst = null;
		try {
			String sql = "select idcliente,nome, rg, cpf, data_nascimento, logradouro," +
							"numero, bairro, cidade, estado, cep from clientes";
			if (cliente != null) {
				boolean bWhere = false;
				if (cliente.getIdCliente() != null) {
					sql += " where idcliente = " + cliente.getIdCliente();
					bWhere = true;
				}
				if ((cliente.getNome() != null) && (!cliente.getNome().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "nome like '%" + cliente.getNome().replace(" ", "%") + "%'";
				}
				if ((cliente.getRg() != null) && (!cliente.getRg().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "rg = '" + cliente.getRg() + "'";
				}
				if ((cliente.getCpf() != null) && (!cliente.getCpf().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "cpf = '" + cliente.getCpf() + "'";
				}
				if ((cliente.getDataNascimento() != null) && (!cliente.getDataNascimento().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "data_nascimento = '" + cliente.getDataNascimento().toGMTString() + "'";
				}
				if ((cliente.getLogradouro() != null) && (!cliente.getLogradouro().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "logradouro like '%" + cliente.getLogradouro().replace(" ", "%") + "%'";
				}
				if ((cliente.getNumero() != null) && (!cliente.getNumero().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "numero like '%" + cliente.getNumero().replace(" ", "%") + "%'";
				}
				if ((cliente.getBairro() != null) && (!cliente.getBairro().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "bairro like '%" + cliente.getBairro().replace(" ", "%") + "%'";
				}
				if ((cliente.getCidade() != null) && (!cliente.getCidade().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "cidade like '%" + cliente.getCidade().replace(" ", "%") + "%'";
				}
				if ((cliente.getEstado() != null) && (!cliente.getEstado().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "estado like '%" + cliente.getEstado().replace(" ", "%") + "%'";
				}
				if ((cliente.getCep() != null) && (!cliente.getCep().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "cep like '%" + cliente.getCep().replace(" ", "%") + "%'";
				}
				sql += " order by nome";
			}
			System.out.println(sql);
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				Cliente clienteAux = new Cliente();
				clienteAux.setIdCliente(rst.getInt("idcliente"));
				clienteAux.setNome(rst.getString("nome"));
				clienteAux.setRg(rst.getString("rg"));
				clienteAux.setCpf(rst.getString("cpf"));
				clienteAux.setDataNascimento(rst.getDate("data_nascimento"));
				clienteAux.setLogradouro(rst.getString("logradouro"));
				clienteAux.setNumero(rst.getString("numero"));
				clienteAux.setBairro(rst.getString("bairro"));
				clienteAux.setCidade(rst.getString("cidade"));
				clienteAux.setEstado(rst.getString("estado"));
				clienteAux.setCep(rst.getString("cep"));
				result.add(clienteAux);
			}
		} catch (Exception e) {
			try {
				this.rollback();
			} catch (Exception e2) {}
			throw e;
		} finally {
			try {
				stmt.close();
				if (rst != null) {
					rst.close();
				}
			} catch (Exception e) {}
		}
		return result.toArray(new Cliente[result.size()]);
	}

	public Cliente findByPrimary(Cliente cliente) throws Exception {
		if ((cliente != null) && (cliente.getIdCliente() != null)) {
			Cliente clienteAux = new Cliente();
			clienteAux.setIdCliente(cliente.getIdCliente());
			Cliente list[] = this.find(clienteAux);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
