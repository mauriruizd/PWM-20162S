package br.edu.udc.sistemas.poo2_20161S.entity;

import java.sql.Date;

public class Cliente implements Entity {
	private Integer idCliente;
	private String nome;
	private String rg;
	private String cpf;
	private Date dataNascimento;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
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

	@Override
	public String[] getFieldGUINames() {
		String fieldNames[] = new String[2];
		fieldNames[0] = "Código";
		fieldNames[1] = "Nome";
		return fieldNames;
	}	

	@Override
	public String[] getFieldGUIValues() {
		String fieldValues[] = new String[2];
		fieldValues[0] = String.valueOf(this.idCliente);
		fieldValues[1] = this.nome;
		return fieldValues;
	}
	
	@Override
	public String[] getFieldDatabaseNames() {
		String fieldNames[] = new String[2];
		fieldNames[0] = "idcliente";
		fieldNames[1] = "nome";
		return fieldNames;
	}
	
	@Override
	public String[] getFieldDatabaseValues() {
		String fieldValues[] = new String[2];
		fieldValues[0] = String.valueOf(this.idCliente);
		fieldValues[1] = this.nome;
		return fieldValues;
	}

}
