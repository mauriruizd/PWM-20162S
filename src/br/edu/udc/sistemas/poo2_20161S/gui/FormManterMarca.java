package br.edu.udc.sistemas.poo2_20161S.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2_20161S.entity.Marca;
import br.edu.udc.sistemas.poo2_20161S.session.SessionMarca;

public class FormManterMarca extends FormCreate {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdMarca;
	private JTextField tfDescricao;
	
	@Override
	protected void createFieldsPanel() {
		this.tfIdMarca = new JTextField();	
		this.tfIdMarca.setEnabled(false);
		this.tfIdMarca.setEditable(false);		
		this.tfDescricao = new JTextField();
		
		this.fieldsPanel.setLayout(new GridLayout(0,4));
		this.fieldsPanel.add(new JLabel("C�digo:"));
		this.fieldsPanel.add(this.tfIdMarca);
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel(""));
		this.fieldsPanel.add(new JLabel("Descri��o:"));
		this.fieldsPanel.add(this.tfDescricao);		
	}
	
	@Override
	protected boolean validateFields() {
		if (this.tfDescricao.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Descri��o Inv�lida!",
					"Aviso!",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	@Override
	protected void save() throws Exception {
		Marca marca = new Marca();
		
		try {
			marca.setIdMarca(Integer.parseInt(this.tfIdMarca.getText()));
		} catch (Exception e) {	}
		
		marca.setDescricao(this.tfDescricao.getText());
		SessionMarca.save(marca);
		this.tfIdMarca.setText(String.valueOf(marca.getIdMarca()));
	}

	@Override
	protected void remove() throws Exception {
		Marca marca = new Marca();
		try {
			marca.setIdMarca(Integer.parseInt(this.tfIdMarca.getText()));
			SessionMarca.remove(marca);
			this.clean();
		} catch (Exception e) {
		}
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdMarca.setText("");
		this.tfDescricao.setText("");
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Marca");
		this.getInternalFrame().setContentPane(new FormConsultarMarca());
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Marca) {
			Marca marca = (Marca) object;
			this.tfIdMarca.setText(String.valueOf(marca.getIdMarca()));
			this.tfDescricao.setText(marca.getDescricao());
		}
	}
}