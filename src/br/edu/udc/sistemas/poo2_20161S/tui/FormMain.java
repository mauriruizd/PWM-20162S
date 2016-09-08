package br.edu.udc.sistemas.poo2_20161S.tui;

public class FormMain extends Form {

	public FormMain() {
		this.menu = new Menu();
		this.menu.setTitle("MENU PRINCIPAL");
		this.menu.getOptions().add("1 - MARCA");
		this.menu.getOptions().add("2 - MODELO");
		this.menu.getOptions().add("3 - CLIENTE");
		this.menu.getOptions().add("4 - VEICULO");
		this.menu.getOptions().add("5 - FUNCIONARIO");
		this.menu.getOptions().add("6 - PRUDUTO");
		this.menu.getOptions().add("7 - SERVICO");
		this.menu.getOptions().add("0 - SAIR");
	}

	@Override
	public void selectOption(char option) {
		FormMarca formMarca = new FormMarca();
		switch(option) {
			case '1' : formMarca.execute();
			           break;
			case '2' : System.out.println("MODELO");
			           break;
			case '3' : System.out.println("CLIENTE");
			           break;
			case '4' : System.out.println("VEICULO");
			           break;
			case '5' : System.out.println("FUNCIONARIO"); 
			           break;
			case '6' : System.out.println("PRODUTO");
			           break;
			case '7' : System.out.println("SERVICO");
			           break;
			case '0' : break;
			default  : System.out.println("Opcao Invalida!");
					   break;
		}
	}

}