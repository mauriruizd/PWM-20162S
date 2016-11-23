package br.edu.udc.sistemas.poo2_20161S.entity;

import br.edu.udc.sistemas.poo2_20161S.annotation.Column;
import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.GeneratedValue;
import br.edu.udc.sistemas.poo2_20161S.annotation.Id;
import br.edu.udc.sistemas.poo2_20161S.annotation.Table;

@Entity
@Table(name="modelo")
public class Modelo {

	@Id
	@GeneratedValue
	@Column(name="idmodelo",type=Column.INTEGER)
	private Integer idModelo;
	
	@Column(name="descricao",type=Column.STRING)
	private String descricao;
	
	@Column(name="idmarca",type=Column.OBJECT)
	private Marca marca;

	public Integer getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return ((this.idModelo == null) ? "" : String.valueOf(this.idModelo)) + " - " +
			   ((this.descricao == null) ? "" : this.descricao) + " - " + 
			   ((this.marca == null) ? "" : ((this.marca.getDescricao() == null) ? "" : this.marca.getDescricao()));
				   
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Modelo)) {
			Modelo modelo = (Modelo) obj;
			if (modelo.getIdModelo() == this.getIdModelo()) {
				return true;
			}
		}
		return false;
	}
}