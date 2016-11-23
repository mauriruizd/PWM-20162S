package br.edu.udc.sistemas.poo2_20161S.entity;

import br.edu.udc.sistemas.poo2_20161S.annotation.ChildList;
import br.edu.udc.sistemas.poo2_20161S.annotation.Column;
import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.GeneratedValue;
import br.edu.udc.sistemas.poo2_20161S.annotation.Id;
import br.edu.udc.sistemas.poo2_20161S.annotation.Table;
import br.edu.udc.sistemas.poo2_20161S.annotation.Transient;

@Entity
@Table(name="compra")
public class Compra {

	@Id
	@GeneratedValue
	@Column(name="idcompra", type=Column.INTEGER)
	private Integer idCompra;
	
	@Column(name="idfornecedor", type=Column.OBJECT)
	private Fornecedor fornecedor;
	
	@Column(name="data", type=Column.STRING)
	private String data;
	
	@Column(name="descricao", type=Column.STRING)
	private String descricao;
	
	@Column(name="valor", type=Column.FLOAT)
	private Float valor;
	
	@Column(name="", type=Column.HIDDEN)
	@ChildList(className="br.edu.udc.sistemas.poo2_20161S.entity.ItemCompra", reflectedItem="produto", fields={"quantidade", "valor"})
	private ItemCompra itens[];

	public Integer getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(Integer idCompra) {
		this.idCompra = idCompra;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public ItemCompra[] getItens() {
		return itens;
	}

	public void setItens(Object[] itens) {
		ItemCompra localItens[] = new ItemCompra[itens.length];
		for(int i = 0; i < itens.length; i++) {
			localItens[i] = (ItemCompra) itens[i];
		}
		this.itens = localItens;
	}
	
	public void setItens(ItemCompra[] itens) {
		this.itens = itens;
	}
	
	
}
