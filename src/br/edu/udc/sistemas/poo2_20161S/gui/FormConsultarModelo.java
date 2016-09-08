package br.edu.udc.sistemas.poo2_20161S.gui;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2_20161S.entity.Marca;
import br.edu.udc.sistemas.poo2_20161S.entity.Modelo;
import br.edu.udc.sistemas.poo2_20161S.session.SessionMarca;
import br.edu.udc.sistemas.poo2_20161S.session.SessionModelo;

public class FormConsultarModelo extends FormFind {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdModelo;
	private JTextField tfDescricao;
	private JComboBox <Marca> jcmbMarca;

	@Override
	protected void createFieldsPanel() {
		this.tfIdModelo = new JTextField();		
		this.tfDescricao = new JTextField();
		this.jcmbMarca = new JComboBox<Marca>();
		
		this.jcmbMarca.addItem(new Marca());
		try {
			Marca listMarca[] = SessionMarca.find(new Marca());
			for (int i = 0; i < listMarca.length; i++) {
				this.jcmbMarca.addItem(listMarca[i]);
			}			
		} catch (Exception e) {}
		
		this.fieldsPanel.setLayout(new GridLayout(0,4));
		this.fieldsPanel.add(new JLabel("Código:"));
		this.fieldsPanel.add(this.tfIdModelo);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Descrição:"));
		this.fieldsPanel.add(this.tfDescricao);	
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Marca:"));
		this.fieldsPanel.add(this.jcmbMarca);
	}
	
	@Override
	protected void createFindPanel() {
		super.createFindPanel();
		this.tableModel = new TableModel(new Modelo());
		this.list.setModel(this.tableModel);
	}
	
	@Override
	protected boolean validateFields() {
		return true;
	}
	
	@Override
	protected void find() throws Exception {
		Modelo modelo = new Modelo();
		try {
			modelo.setIdModelo(Integer.parseInt(this.tfIdModelo.getText()));
		} catch (Exception e) {
			modelo.setIdModelo(null);
		}
		
		if (this.tfDescricao.getText().trim().equals("")) {
			modelo.setDescricao(null);
		} else {
			modelo.setDescricao(this.tfDescricao.getText());
		}
		
		modelo.setMarca((Marca) this.jcmbMarca.getSelectedItem());
		
		this.tableModel.setList(SessionModelo.find(modelo));
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdModelo.setText("");
		this.tfDescricao.setText("");
		this.jcmbMarca.setSelectedIndex(0);
		this.tableModel.clear();
	}

	@Override
	protected void goNew() throws Exception {
		getInternalFrame().setTitle("Manter Modelo");
		getInternalFrame().setContentPane(new FormManterModelo());		
	}

	@Override
	protected void detail() throws Exception {
		Modelo modelo = (Modelo) this.tableModel.getList()[this.list.getSelectedRow()];
		FormManterModelo formManterModelo = new FormManterModelo();
		formManterModelo.setObject(modelo);
		getInternalFrame().setTitle("Manter Modelo");
		getInternalFrame().setContentPane(formManterModelo);		
	}

}