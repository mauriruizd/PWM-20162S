package br.edu.udc.sistemas.poo2_20161S.entity;

import br.edu.udc.sistemas.poo2_20161S.annotation.ChildList;
import br.edu.udc.sistemas.poo2_20161S.annotation.Column;
import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.GeneratedValue;
import br.edu.udc.sistemas.poo2_20161S.annotation.Id;
import br.edu.udc.sistemas.poo2_20161S.annotation.Table;

@Entity
@Table(name="venda")
public class Venda {

	@Id
	@GeneratedValue
	@Column(name="idvenda", type=Column.INTEGER)
	private Integer idVenda;
	
	@Column(name="idcliente", type=Column.OBJECT)
	private Cliente cliente;
	
	@Column(name="idveiculo", type=Column.OBJECT)
	private Veiculo veiculo;
	
	@Column(name="data", type=Column.STRING)
	private String data;
	
	@Column(name="descricao", type=Column.STRING)
	private String descricao;
	
	@Column(name="valor", type=Column.FLOAT)
	private Float valor;
	
	@Column(name="", type=Column.HIDDEN)
	@ChildList(className="br.edu.udc.sistemas.poo2_20161S.entity.ItemVenda", reflectedItem="produto", fields={"quantidade", "valor"})
	private ItemVenda itens[];
	
	@Column(name="", type=Column.HIDDEN)
	@ChildList(className="br.edu.udc.sistemas.poo2_20161S.entity.ItemServico", reflectedItem="produto", fields={"quantidade", "valor"})
	private ItemServico itensServico[];

	public Integer getIdCompra() {
		return idVenda;
	}

	public void setIdCompra(Integer idVenda) {
		this.idVenda = idVenda;
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

	public ItemVenda[] getItens() {
		return itens;
	}

	public void setItens(Object[] itens) {
		ItemVenda localItens[] = new ItemVenda[itens.length];
		for(int i = 0; i < itens.length; i++) {
			localItens[i] = (ItemVenda) itens[i];
		}
		this.itens = localItens;
	}
	
	public void setItens(ItemVenda[] itens) {
		this.itens = itens;
	}

	public Integer getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public ItemServico[] getItensServico() {
		return itensServico;
	}

	public void setItensServico(ItemServico[] itensServico) {
		this.itensServico = itensServico;
	}
	
	
}
