package br.edu.udc.sistemas.poo2_20161S.entity;

import br.edu.udc.sistemas.poo2_20161S.annotation.Column;
import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.GeneratedValue;
import br.edu.udc.sistemas.poo2_20161S.annotation.Id;
import br.edu.udc.sistemas.poo2_20161S.annotation.Table;

@Entity
@Table(name="itemvenda")
public class ItemVenda {
	
	@Id
	@GeneratedValue
	@Column(name="iditemvenda", type=Column.INTEGER)
	private Integer idItemVenda;
	
	@Column(name="idvenda", type=Column.OBJECT)
	private Venda venda;
	
	@Column(name="idproduto", type=Column.OBJECT)
	private Produto produto;
	
	@Column(name="quantidade", type=Column.FLOAT)
	private Float quantidade;
	
	@Column(name="valor", type=Column.FLOAT)
	private Float valor;

	public Integer getIdItemVenda() {
		return idItemVenda;
	}

	public void setIdItemVenda(Integer idItemVenda) {
		this.idItemVenda = idItemVenda;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
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
