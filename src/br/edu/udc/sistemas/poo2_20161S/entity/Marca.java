package br.edu.udc.sistemas.poo2_20161S.entity;

import br.edu.udc.sistemas.poo2_20161S.annotation.Column;
import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.Table;

@Entity
@Table(name="marca")
public class Marca {

	@Column(name="idmarca",type=Column.INTEGER)
	private Integer idMarca;
	
	@Column(name="descricao",type=Column.STRING)
	private String descricao;

	public Integer getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return ((this.idMarca == null) ? "" : String.valueOf(this.idMarca)) + " - " +
			   ((this.descricao == null) ? "" : this.descricao);
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Marca)) {
			Marca marca = (Marca) obj;
			if (marca.getIdMarca() == this.getIdMarca()) {
				return true;
			}
		}
		return false;
	}
}