package br.edu.udc.sistemas.poo2_20161S.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.Vector;

import br.edu.udc.sistemas.poo2_20161S.entity.Marca;

public class DaoMarca extends Dao {
	
	public DaoMarca() throws Exception {
		super();
	}
	
	public DaoMarca(Connection con) throws Exception {
		super(con);
	}

	public void save(Marca marca) throws Exception {
		if (marca != null) {
			Statement stmt = this.con.createStatement();
			ResultSet rst = null;
			String sql = "";
			try {
				if ((marca.getIdMarca() != null) &&
					(marca.getIdMarca() > 0)) {
					sql = "update marcas set " +
					      "descricao = '" + marca.getDescricao() + "' " +
					      "where idmarca = " + marca.getIdMarca();
					System.out.println(sql);
					stmt.execute(sql);
				} else {
					sql = "insert into marcas (descricao) " +
						  "values('" + marca.getDescricao() + "')";
					System.out.println(sql);
					stmt.execute(sql,Statement.RETURN_GENERATED_KEYS);
					rst = stmt.getGeneratedKeys();
					if (rst.next()) {
						marca.setIdMarca(rst.getInt(1));
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

	public void remove(Marca marca) throws Exception {
		if ((marca != null) && (marca.getIdMarca() != null)) {
			Statement stmt = this.con.createStatement();
			try {
				String sql = "delete from marcas " + "where idmarca = " + marca.getIdMarca();
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

	public Marca[] find(Marca marca) throws Exception {
		Collection <Marca> result = new Vector <Marca> ();
		Statement stmt = this.con.createStatement();
		ResultSet rst = null;
		try {
			String sql = "select idmarca,descricao from marcas";
			if (marca != null) {
				boolean bWhere = false;
				if (marca.getIdMarca() != null) {
					sql += " where idmarca = " + marca.getIdMarca();
					bWhere = true;
				}
				if ((marca.getDescricao() != null) && (!marca.getDescricao().equals(""))) {
					if (bWhere) {
						sql += " and ";
					} else {
						sql += " where ";
						bWhere = true;
					}
					sql += "descricao like '%" + marca.getDescricao().replace(" ", "%") + "%'";
				}
				sql += " order by descricao";
			}
			System.out.println(sql);
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				Marca marcaAux = new Marca();
				marcaAux.setIdMarca(rst.getInt("idmarca"));
				marcaAux.setDescricao(rst.getString("descricao"));
				result.add(marcaAux);
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
		return result.toArray(new Marca[result.size()]);
	}

	public Marca findByPrimary(Marca marca) throws Exception {
		if ((marca != null) && (marca.getIdMarca() != null)) {
			Marca marcaAux = new Marca();
			marcaAux.setIdMarca(marca.getIdMarca());
			Marca list[] = this.find(marcaAux);
			if (list.length > 0) {
				return list[0];
			}
		}
		return null;
	}
}
