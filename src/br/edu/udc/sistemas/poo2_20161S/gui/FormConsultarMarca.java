package br.edu.udc.sistemas.poo2_20161S.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2_20161S.entity.Marca;
import br.edu.udc.sistemas.poo2_20161S.session.SessionMarca;

public class FormConsultarMarca extends FormFind {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdMarca;
	private JTextField tfDescricao;

	@Override
	protected void createFieldsPanel() {
		this.tfIdMarca = new JTextField();		
		this.tfDescricao = new JTextField();
		
		this.fieldsPanel.setLayout(new GridLayout(0,4));
		this.fieldsPanel.add(new JLabel("Código:"));
		this.fieldsPanel.add(this.tfIdMarca);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Descrição:"));
		this.fieldsPanel.add(this.tfDescricao);		
	}
	
	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModel = new TableModel(new Marca());
		this.list.setModel(this.tableModel);
	}
	
	@Override
	protected boolean validateFields() {
		return true;
	}
	
	@Override
	protected void find() throws Exception {
		Marca marca = new Marca();
		try {
			marca.setIdMarca(Integer.parseInt(this.tfIdMarca.getText()));
		} catch (Exception e) {
			marca.setIdMarca(null);
		}
		
		if (this.tfDescricao.getText().trim().equals("")) {
			marca.setDescricao(null);
		} else {
			marca.setDescricao(this.tfDescricao.getText());
		}
		
		this.tableModel.setList(SessionMarca.find(marca));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdMarca.setText("");
		this.tfDescricao.setText("");
		this.tableModel.clear();
	}

	@Override
	protected void goNew() throws Exception {
		getInternalFrame().setTitle("Manter Marca");
		getInternalFrame().setContentPane(new FormManterMarca());		
	}

	@Override
	protected void detail() throws Exception {
		Marca marca = (Marca) this.tableModel.getList()[this.list.getSelectedRow()];
		FormManterMarca formManterMarca = new FormManterMarca();
		formManterMarca.setObject(marca);
		getInternalFrame().setTitle("Manter Marca");
		getInternalFrame().setContentPane(formManterMarca);		
	}

}