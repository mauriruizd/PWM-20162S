package br.edu.udc.sistemas.poo2_20161S.gui;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2_20161S.entity.Marca;
import br.edu.udc.sistemas.poo2_20161S.entity.Modelo;
import br.edu.udc.sistemas.poo2_20161S.session.SessionMarca;
import br.edu.udc.sistemas.poo2_20161S.session.SessionModelo;

public class FormManterModelo extends FormCreate {

	private static final long serialVersionUID = 1L;

	private JTextField tfIdModelo;
	private JTextField tfDescricao;
	private JComboBox <Marca> jcmbMarca;
	
	@Override
	protected void createFieldsPanel() {
		this.tfIdModelo = new JTextField();	
		this.tfIdModelo.setEnabled(false);
		this.tfIdModelo.setEditable(false);		
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
	protected boolean validateFields() {
		if (this.tfDescricao.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this,
					"Descrição Inválida!",
					"Aviso!",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		if (this.jcmbMarca.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this,
					"Marca Inválida!",
					"Aviso!",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	@Override
	protected void save() throws Exception {
		Modelo modelo = new Modelo();
		
		try {
			modelo.setIdModelo(Integer.parseInt(this.tfIdModelo.getText()));
		} catch (Exception e) {	}
		
		modelo.setDescricao(this.tfDescricao.getText());
		modelo.setMarca((Marca) this.jcmbMarca.getSelectedItem());
		SessionModelo.save(modelo);
		this.tfIdModelo.setText(String.valueOf(modelo.getIdModelo()));
	}

	@Override
	protected void remove() throws Exception {
		Modelo modelo = new Modelo();
		try {
			modelo.setIdModelo(Integer.parseInt(this.tfIdModelo.getText()));
			SessionModelo.remove(modelo);
			this.clean();
		} catch (Exception e) {
		}
	}

	@Override
	protected void clean() throws Exception {
		this.tfIdModelo.setText("");
		this.tfDescricao.setText("");
		this.jcmbMarca.setSelectedIndex(0);
	}

	@Override
	protected void goFind() throws Exception {
		this.getInternalFrame().setTitle("Consultar Modelo");
		this.getInternalFrame().setContentPane(new FormConsultarModelo());
	}

	@Override
	protected void setObject(Object object) throws Exception {
		if (object instanceof Modelo) {
			Modelo modelo = (Modelo) object;
			this.tfIdModelo.setText(String.valueOf(modelo.getIdModelo()));
			this.tfDescricao.setText(modelo.getDescricao());
			this.jcmbMarca.setSelectedItem(modelo.getMarca());
		}
	}
}