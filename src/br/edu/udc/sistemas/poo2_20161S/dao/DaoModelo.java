package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

import br.edu.udc.sistemas.poo2_20161S.entity.Marca;
import br.edu.udc.sistemas.poo2_20161S.entity.Modelo;
import br.edu.udc.sistemas.poo2_20161S.infra.Database;

public class DaoModelo {

	public static void save(Modelo modelo) throws Exception {
		if (modelo != null) {
			Connection con = Database.getInstance().getConnection();
			Statement stmt = con.createStatement();
			ResultSet rst = null;
			String sql = "";
			try {
				if ((modelo.getIdModelo() != null) &&
					(modelo.getIdModelo() > 0)) {
					sql = "update modelo set " +
					      "descricao = '" + modelo.getDescricao() + "', " +
					      "idmarca = " + modelo.getMarca().getIdMarca() + " " +
					      "where idmodelo = " + modelo.getIdModelo();
					System.out.println(sql);
					stmt.execute(sql);
				} else {
					sql = "insert into modelo (descricao,idmarca) " +
						  "values('" + modelo.getDescricao() + "', " +
						   modelo.getMarca().getIdMarca() +  ")";
					System.out.println(sql);
					stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
					rst = stmt.getGeneratedKeys();
					if (rst.next()) {
						modelo.setIdModelo(rst.getInt(1));
					}
				}
			} catch (Exception e) {
				try {
					DaoModelo.rollback();
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

	public static void remove(Modelo modelo) throws Exception {
		if ((modelo != null) && (modelo.getIdModelo() != null)) {
			Connection con = Database.getInstance().getConnection();
			Statement stmt = con.createStatement();
			try {
				String sql = "delete from modelo " + "where idmodelo = " + modelo.getIdModelo();
				System.out.println(sql);
				stmt.execute(sql);
			} catch (Exception e) {
				try {
					DaoModelo.rollback();
				} catch (Exception e2) {}
				throw e;
			} finally {
				try {
					stmt.close();
				} catch (Exception e) {}
			}			
		}
	}

	public static Modelo[] find(Modelo modelo) throws Exception {
		Collection <Modelo> result = new Vector <Modelo> ();
		Connection con = Database.getInstance().getConnection();
		Statement stmt = con.createStatement();
		ResultSet rst = null;
		try {
			String sql = "select idmodelo,descricao,idmarca from modelo";
			if (modelo != null) {
				boolean bWhere = false;
				if (modelo.getIdModelo() != null) {
					sql += " where idmodelo = " + modelo.getIdModelo();
					bWhere = true;
				}
				if ((modelo.getDescricao() != null) && (!modelo.getDescricao().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "descricao like '%" + modelo.getDescricao().replace(" ", "%") + "%'";
				}
				
				if ((modelo.getMarca() != null) && (modelo.getMarca().getIdMarca() != null)) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "idmarca = " + modelo.getMarca().getIdMarca();
				}
				sql += " order by descricao";
			}
			System.out.println(sql);
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				Modelo modeloAux = new Modelo();
				modeloAux.setIdModelo(rst.getInt("idmodelo"));
				modeloAux.setDescricao(rst.getString("descricao"));
				
				Marca marca = new Marca();
				marca.setIdMarca(rst.getInt("idmarca"));
				
				modeloAux.setMarca(marca);
				
				result.add(modeloAux);
			}
		} catch (Exception e) {
			try {
				DaoModelo.rollback();
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
		return result.toArray(new Modelo[result.size()]);
	}

	public static Modelo findByPrimary(Modelo modelo) throws Exception {
		if ((modelo != null) && (modelo.getIdModelo() != null)) {
			Modelo modeloAux = new Modelo();
			modeloAux.setIdModelo(modelo.getIdModelo());
			Modelo list[] = DaoModelo.find(modeloAux);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
	
	public static void rollback() throws Exception {
		Database.getInstance().getConnection().rollback();
	}
	
	public static void commit() throws Exception {
		Database.getInstance().getConnection().commit();
	}

}
