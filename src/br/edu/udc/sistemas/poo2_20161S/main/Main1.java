package br.edu.udc.sistemas.poo2_20161S.main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.udc.sistemas.poo2_20161S.gui.FormMain;

public class Main1 {

	public static void mainGridLayOut(String[] args) throws Exception {
		
		JFrame frame = new JFrame("TITULO");
		
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		
		frame.setContentPane(panel);
		
		panel.setLayout(new GridLayout(0,4));
		
		for (int i = 0; i < 8; i++) {
			panel.add(new JButton("Botão" + i));
		}
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(0,4));
		for (int i = 0; i < 8; i++) {
			panel2.add(new JButton("Botão" + i));
		}
		
		panel.add(panel2);
		
		JPanel panel3 = new JPanel();
		panel3.setLayout(new GridLayout(0,4));
		for (int i = 0; i < 9; i++) {
			panel3.add(new JButton("Botão" + i));
		}
		
		panel2.add(panel3);
		
	}
	
	public static void mainFlowLayout(String[] args) throws Exception {
		
		JFrame frame = new JFrame("TITULO");
		
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		
		frame.setContentPane(panel);
		
		panel.setLayout(new FlowLayout());
		
		for (int i = 0; i < 2; i++) {
			panel.add(new JButton("Botão" + i));
		}
		
	}
	
	public static void mainBorderLayout(String[] args) throws Exception {
		
		JFrame frame = new JFrame("TITULO");
		
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		
		frame.setContentPane(panel);
		
		panel.setLayout(new BorderLayout());
		
//		panel.add(new JButton("NORTE"),BorderLayout.NORTH);
//		panel.add(new JButton("SUL"),BorderLayout.SOUTH);
//		panel.add(new JButton("LESTE"),BorderLayout.EAST);
//		panel.add(new JButton("OESTE"),BorderLayout.WEST);
//		panel.add(new JButton("CENTRO"),BorderLayout.CENTER);
		
		JPanel painelSul = new JPanel();
		painelSul.setLayout(new FlowLayout());
		painelSul.add(new JButton("Consultar"));
		painelSul.add(new JButton("Novo"));
		painelSul.add(new JButton("Excluir"));
		painelSul.add(new JButton("Limpar"));		
		panel.add(painelSul,BorderLayout.SOUTH);
		
		JPanel painelCentral = new JPanel();
		painelCentral.setLayout(new GridLayout(0,3));
		painelCentral.add(new JLabel("Código:"));
		painelCentral.add(new JTextField());
		painelCentral.add(new JLabel(""));
		painelCentral.add(new JLabel("Descrição:"));
		painelCentral.add(new JTextField());
		panel.add(painelCentral,BorderLayout.NORTH);
		
		
		JFrame frame2 = new JFrame("TITULO2");
		
		frame2.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new FormMain();
	}
}