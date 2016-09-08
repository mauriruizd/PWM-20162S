package br.edu.udc.sistemas.poo2_20161S.gui;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public abstract class Form extends JPanel {

	private static final long serialVersionUID = 1L;
		
	protected JPanel fieldsPanel;
	protected JPanel buttonsPanel;
	
	public Form() {
		this.setLayout(new BorderLayout());
		
		this.fieldsPanel = new JPanel();
		this.add(fieldsPanel, BorderLayout.NORTH);
		
		this.createFieldsPanel();
		this.createButtonsPanel();		
	}
	
	protected JInternalFrame getInternalFrame() {
		return (JInternalFrame) this.getParent().getParent().getParent();
	}
	
	protected abstract void createFieldsPanel();
	protected abstract void createButtonsPanel();
	protected abstract boolean validateFields();
}