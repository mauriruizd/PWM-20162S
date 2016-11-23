package br.edu.udc.sistemas.poo2_20161S.entity;

import br.edu.udc.sistemas.poo2_20161S.annotation.Column;
import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.GeneratedValue;
import br.edu.udc.sistemas.poo2_20161S.annotation.Id;
import br.edu.udc.sistemas.poo2_20161S.annotation.Table;

@Entity
@Table(name="itemcompra")
public class ItemCompra {
	
	@Id
	@GeneratedValue
	@Column(name="iditemcompra", type=Column.INTEGER)
	private Integer idItemCompra;
	
	@Column(name="idcompra", type=Column.OBJECT)
	private Compra compra;
	
	@Column(name="idproduto", type=Column.OBJECT)
	private Produto produto;
	
	@Column(name="quantidade", type=Column.FLOAT)
	private Float quantidade;
	
	@Column(name="valor", type=Column.FLOAT)
	private Float valor;

	public Integer getIdItemCompra() {
		return idItemCompra;
	}

	public void setIdItemCompra(Integer idItemCompra) {
		this.idItemCompra = idItemCompra;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
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
