package br.edu.udc.sistemas.poo2_20161S.entity;

public class Veiculo {
	
	private Integer idVeiculo;
	private String placa;
	private String cor;
	private Integer ano;
	private Modelo modelo;
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