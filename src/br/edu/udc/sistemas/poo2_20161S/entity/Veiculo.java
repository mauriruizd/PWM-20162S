package br.edu.udc.sistemas.poo2_20161S.entity;

import br.edu.udc.sistemas.poo2_20161S.annotation.Column;
import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.GeneratedValue;
import br.edu.udc.sistemas.poo2_20161S.annotation.Id;
import br.edu.udc.sistemas.poo2_20161S.annotation.Table;

@Entity
@Table(name="veiculo")
public class Veiculo {
	
	@Id
	@GeneratedValue
	@Column(name="idveiculo",type=Column.INTEGER)
	private Integer idVeiculo;
	
	@Column(name="placa",type=Column.STRING)
	private String placa;
	
	@Column(name="cor",type=Column.STRING)
	private String cor;
	
	@Column(name="ano",type=Column.INTEGER)
	private Integer ano;
	
	@Column(name="idmodelo",type=Column.OBJECT)
	private Modelo modelo;
	
	@Column(name="idcliente",type=Column.OBJECT)
	private Cliente cliente;
	
	public Integer getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(Integer idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public Integer getAno() {
		return ano;
	}
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	public Modelo getModelo() {
		return modelo;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public String toString() {
		return ((this.idVeiculo == null) ? "" : String.valueOf(this.idVeiculo)) + " - " +
			   ((this.placa == null) ? "" : this.placa) +
			   ((this.modelo == null) ? "" : ((this.modelo.getDescricao() == null) ? "" : this.modelo.getDescricao())) + 
			   ((this.cliente == null) ? "" : ((this.cliente.getNome() == null) ? "" : this.cliente.getNome()));
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Veiculo)) {
			Veiculo veiculo = (Veiculo) obj;
			if (veiculo.getIdVeiculo() == this.getIdVeiculo()) {
				return true;
			}
		}
		return false;
	}
	
}