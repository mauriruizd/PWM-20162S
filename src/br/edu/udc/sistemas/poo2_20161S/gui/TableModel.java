package br.edu.udc.sistemas.poo2_20161S.gui;

import javax.swing.table.AbstractTableModel;

import br.edu.udc.sistemas.poo2_20161S.entity.Entity;

public class TableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private Entity reference;
	private Entity list[] = new Entity[0];
	
	public TableModel(Entity reference) {
		this.reference = reference; 
	}
	
	public Entity[] getList() {
		return list;
	}
	
	public void clear() {
		this.setList(new Entity[0]);
	}

	public void setList(Entity[] list) {
		this.list = list;
		this.fireTableDataChanged();
	}
	
	@Override
	public int getColumnCount() {
		return this.reference.getFieldGUINames().length;
	}
	
	@Override
	public String getColumnName(int column) {
		return this.reference.getFieldGUINames()[column];
	}

	@Override
	public int getRowCount() {
		return this.list.length;
	}	
	
	@Override
	public Object getValueAt(int row, int column) {
		return this.list[row].getFieldGUIValues()[column];
	}
}