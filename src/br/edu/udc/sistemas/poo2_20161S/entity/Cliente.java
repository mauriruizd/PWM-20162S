package br.edu.udc.sistemas.poo2_20161S.entity;

import br.edu.udc.sistemas.poo2_20161S.annotation.Column;
import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.GeneratedValue;
import br.edu.udc.sistemas.poo2_20161S.annotation.Id;
import br.edu.udc.sistemas.poo2_20161S.annotation.Table;

@Entity
@Table(name="cliente")
public class Cliente {
	@Id
	@GeneratedValue
	@Column(name="idcliente", type=Column.INTEGER)
	private Integer idCliente;
	
	@Column(name="nome", type=Column.STRING)
	private String nome;
	
	@Column(name="rg", type=Column.STRING)
	private String rg;
	
	@Column(name="cpf", type=Column.STRING)
	private String cpf;
	
	@Column(name="datanascimento", type=Column.STRING)
	private String dataNascimento;
	
	@Column(name="logradouro", type=Column.STRING)
	private String logradouro;
	
	@Column(name="numero", type=Column.STRING)
	private String numero;
	
	@Column(name="bairro", type=Column.STRING)
	private String bairro;
	
	@Column(name="cidade", type=Column.STRING)
	private String cidade;
	
	@Column(name="estado", type=Column.STRING)
	private String estado;
	
	@Column(name="cep", type=Column.STRING)
	private String cep;
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public String toString() {
		return ((this.idCliente == null) ? "" : String.valueOf(this.idCliente)) + " - " +
			   ((this.nome == null) ? "" : this.nome);
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Cliente)) {
			Cliente cliente = (Cliente) obj;
			if (cliente.getIdCliente() == this.getIdCliente()) {
				return true;
			}
		}
		return false;
	}

}
