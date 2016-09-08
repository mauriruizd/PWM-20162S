package br.edu.udc.sistemas.poo2_20161S.tui;

import java.util.Vector;

public class FormCadastro extends Form {
	protected Vector list;

	public FormCadastro() {
		this.menu = new Menu();
		this.menu.getOptions().add("1 - INSERIR");
		this.menu.getOptions().add("2 - BUSCAR");
		this.menu.getOptions().add("3 - LISTAR");
		this.menu.getOptions().add("4 - EXCLUIR");
		this.menu.getOptions().add("5 - SALVAR");
		this.menu.getOptions().add("0 - SAIR");
	}
	
	public void selectOption(char option) {
		switch(option) {
			case '1' : this.add();
			           break;
			case '2' : this.find();
			           break;
			case '3' : this.print();
			           break;
			case '4' : this.remove();
			           break;
			case '5' : this.save(); 
			           break;
			case '0' : break;
			default  : System.out.println("Opcao Invalida!");
			           IOTools.readChar();
					   break;
		}
	}
	
	public void add() { 
		
	}
	public void find() { 
		
	}
	public void print() { 
		
	}
	public void remove() { 
		
	}
	public void save() { 
		
	}
	public void load() { 
		
	}
	
}
