package br.edu.udc.sistemas.poo2_20161S.entity;

public class Modelo implements Entity {

	private Integer idModelo;
	private String descricao;
	private Marca marca;

	public Integer getIdModelo() {
		return idModelo;
	}

	public void setIdModelo(Integer idModelo) {
		this.idModelo = idModelo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return ((this.idModelo == null) ? "" : String.valueOf(this.idModelo)) + " - " +
			   ((this.descricao == null) ? "" : this.descricao) + " - " + 
			   ((this.marca == null) ? "" : ((this.marca.getDescricao() == null) ? "" : this.marca.getDescricao()));
				   
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Modelo)) {
			Modelo modelo = (Modelo) obj;
			if (modelo.getIdModelo() == this.getIdModelo()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String[] getFieldGUINames() {
		String fieldNames[] = new String[3];
		fieldNames[0] = "C�digo";
		fieldNames[1] = "Descri��o";
		fieldNames[2] = "Marca";
		return fieldNames;
	}

	@Override
	public String[] getFieldGUIValues() {
		String fieldValues[] = new String[3];
		fieldValues[0] = String.valueOf(this.idModelo);
		fieldValues[1] = this.descricao;
		fieldValues[2] = (this.marca != null) ? this.marca.getDescricao() : "";

		return fieldValues;
	}
	
	@Override
	public String[] getFieldDatabaseNames() {
		String fieldNames[] = new String[3];
		fieldNames[0] = "idmodelo";
		fieldNames[1] = "descricao";
		fieldNames[2] = "idmarca";
		return fieldNames;
	}
	
	@Override
	public String[] getFieldDatabaseValues() {
		String fieldValues[] = new String[3];
		fieldValues[0] = String.valueOf(this.idModelo);
		fieldValues[1] = this.descricao;
		fieldValues[2] = (this.marca != null) ? String.valueOf(this.marca.getIdMarca()) : "";

		return fieldValues;
	}
}