package br.edu.udc.sistemas.poo2_20161S.tui;

public class Form {

	protected Menu menu;

	public Form() {
		this.menu = null;
	}

	public void selectOption(char option) {

	}

	public void execute() {
		IOTools.clear();
		this.menu.show();
		System.out.print("Entre com a Opcao: ");
		Character option = IOTools.readChar();
		while (option != '0') {
			this.selectOption(option);
			IOTools.clear();
			this.menu.show();
			System.out.print("Entre com a Opcao: ");
			option = IOTools.readChar();
		}
	}

}
