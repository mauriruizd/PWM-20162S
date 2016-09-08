package br.edu.udc.sistemas.poo2_20161S.entity;

public class Marca implements Entity {

	private Integer idMarca;
	private String descricao;

	public Integer getIdMarca() {
		return idMarca;
	}

	public void setIdMarca(Integer idMarca) {
		this.idMarca = idMarca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return ((this.idMarca == null) ? "" : String.valueOf(this.idMarca)) + " - " +
			   ((this.descricao == null) ? "" : this.descricao);
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Marca)) {
			Marca marca = (Marca) obj;
			if (marca.getIdMarca() == this.getIdMarca()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String[] getFieldGUINames() {
		String fieldNames[] = new String[2];
		fieldNames[0] = "Código";
		fieldNames[1] = "Descrição";
		return fieldNames;
	}	

	@Override
	public String[] getFieldGUIValues() {
		String fieldValues[] = new String[2];
		fieldValues[0] = String.valueOf(this.idMarca);
		fieldValues[1] = this.descricao;
		return fieldValues;
	}
	
	@Override
	public String[] getFieldDatabaseNames() {
		String fieldNames[] = new String[2];
		fieldNames[0] = "idmarca";
		fieldNames[1] = "descricao";
		return fieldNames;
	}
	
	@Override
	public String[] getFieldDatabaseValues() {
		String fieldValues[] = new String[2];
		fieldValues[0] = String.valueOf(this.idMarca);
		fieldValues[1] = this.descricao;
		return fieldValues;
	}
}