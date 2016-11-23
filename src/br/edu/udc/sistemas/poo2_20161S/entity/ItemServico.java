package br.edu.udc.sistemas.poo2_20161S.entity;

import br.edu.udc.sistemas.poo2_20161S.annotation.Column;
import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.GeneratedValue;
import br.edu.udc.sistemas.poo2_20161S.annotation.Id;
import br.edu.udc.sistemas.poo2_20161S.annotation.Table;

@Entity
@Table(name="itemservico")
public class ItemServico {
	
	@Id
	@GeneratedValue
	@Column(name="iditemservico", type=Column.INTEGER)
	private Integer idItemServico;
	
	@Column(name="idservico", type=Column.OBJECT)
	private Servico servico;
	
	@Column(name="idproduto", type=Column.OBJECT)
	private Produto produto;
	
	@Column(name="quantidade", type=Column.FLOAT)
	private Float quantidade;
	
	@Column(name="valor", type=Column.FLOAT)
	private Float valor;

	public Integer getIdItemServico() {
		return idItemServico;
	}

	public void setIdItemServico(Integer idItemServico) {
		this.idItemServico = idItemServico;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
	
	
	
}
