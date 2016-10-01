package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import br.edu.udc.sistemas.poo2_20161S.entity.Cliente;
import br.edu.udc.sistemas.poo2_20161S.entity.Modelo;
import br.edu.udc.sistemas.poo2_20161S.entity.Veiculo;
import br.edu.udc.sistemas.poo2_20161S.infra.DatabasePool;

public class DaoVeiculo extends Dao {
	
	public DaoVeiculo() throws Exception {
		super();
	}
	
	public DaoVeiculo(Connection con) throws Exception {
		super(con);
	}

	public void save(Veiculo veiculo) throws Exception {
		if (veiculo != null) {
			Connection con = DatabasePool.getInstance().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rst = null;
			String sql = "";
			try {
				if ((veiculo.getIdVeiculo() != null) &&
					(veiculo.getIdVeiculo() > 0)) {
					sql = "update veiculos set " +
					      "placa = '" + veiculo.getPlaca() + "', " +
					      "cor = '" + veiculo.getCor() + "', " +
					      "ano = " + veiculo.getAno() + ", " +
					      "idmodelo = " + veiculo.getModelo().getIdModelo() + ", " +
					      "idcliente = " + veiculo.getCliente().getIdCliente() +
					      "where idveiculo = " + veiculo.getIdVeiculo();
					System.out.println(sql);
					stmt.execute(sql);
				} else {
					sql = "insert into veiculos (placa,cor, ano, idmodelo, idcliente) " +
						  "values('" + veiculo.getPlaca() + "', " +
						  "'" + veiculo.getCor() + "', " +
						  veiculo.getAno() + ", " +
						  veiculo.getModelo().getIdModelo() +  ", " +
					veiculo.getCliente().getIdCliente() +  ")";
					System.out.println(sql);
					stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
					rst = stmt.getGeneratedKeys();
					if (rst.next()) {
						veiculo.setIdVeiculo(rst.getInt(1));
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

	public void remove(Veiculo veiculo) throws Exception {
		if ((veiculo != null) && (veiculo.getIdVeiculo() != null)) {
			Connection con = DatabasePool.getInstance().getConnection();
			Statement stmt = con.createStatement();
			try {
				String sql = "delete from veiculos " + "where idveiculo = " + veiculo.getIdVeiculo();
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

	public Veiculo[] find(Veiculo veiculo) throws Exception {
		Collection <Veiculo> result = new Vector <Veiculo> ();
		Connection con = DatabasePool.getInstance().getConnection();
		Statement stmt = con.createStatement();
		ResultSet rst = null;
		try {
			String sql = "select idveiculo,placa,cor, ano, idmodelo, idcliente from veiculos";
			if (veiculo != null) {
				boolean bWhere = false;
				if (veiculo.getIdVeiculo() != null) {
					sql += " where idveiculo = " + veiculo.getIdVeiculo();
					bWhere = true;
				}
				if ((veiculo.getPlaca() != null) && (!veiculo.getPlaca().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "placa like '%" + veiculo.getPlaca().replace(" ", "%") + "%'";
				}
				
				if ((veiculo.getCor() != null) && (!veiculo.getCor().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "cor like '%" + veiculo.getCor().replace(" ", "%") + "%'";
				}
				
				if (veiculo.getAno() != null) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "ano = " + veiculo.getAno();
				}
				
				if ((veiculo.getModelo() != null) && (veiculo.getModelo().getIdModelo() != null)) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "idmodelo = " + veiculo.getModelo().getIdModelo();
				}
				
				if ((veiculo.getCliente() != null) && (veiculo.getCliente().getIdCliente() != null)) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "idcliente = " + veiculo.getCliente().getIdCliente();
				}
				
				sql += " order by placa";
			}
			System.out.println(sql);
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				Veiculo veiculoAux = new Veiculo();
				veiculoAux.setIdVeiculo(rst.getInt("idveiculo"));
				veiculoAux.setPlaca(rst.getString("placa"));
				veiculoAux.setCor(rst.getString("cor"));
				veiculoAux.setAno(rst.getInt("ano"));
				
				Modelo modelo = new Modelo();
				modelo.setIdModelo(rst.getInt("idmodelo"));
				
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rst.getInt("idcliente"));
				
				veiculoAux.setModelo(modelo);
				veiculoAux.setCliente(cliente);
				
				result.add(veiculoAux);
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
		return result.toArray(new Veiculo[result.size()]);
	}

	public Veiculo findByPrimary(Veiculo veiculo) throws Exception {
		if ((veiculo != null) && (veiculo.getIdVeiculo() != null)) {
			Veiculo veiculoAux = new Veiculo();
			veiculoAux.setIdVeiculo(veiculo.getIdVeiculo());
			Veiculo list[] = this.find(veiculoAux);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}

}
