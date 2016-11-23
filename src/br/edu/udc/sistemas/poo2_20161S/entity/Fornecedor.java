package br.edu.udc.sistemas.poo2_20161S.entity;

import br.edu.udc.sistemas.poo2_20161S.annotation.Column;
import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.GeneratedValue;
import br.edu.udc.sistemas.poo2_20161S.annotation.Id;
import br.edu.udc.sistemas.poo2_20161S.annotation.Table;

@Entity
@Table(name="fornecedor")
public class Fornecedor {
	
	@Id
	@GeneratedValue
	@Column(name="idfornecedor", type=Column.INTEGER)
	private Integer idFornecedor;
	
	@Column(name="razaosocial", type=Column.STRING)
	private String razaoSocial;
	
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

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
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
}
