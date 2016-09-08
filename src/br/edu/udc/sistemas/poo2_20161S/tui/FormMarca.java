package br.edu.udc.sistemas.poo2_20161S.tui;

import br.edu.udc.sistemas.poo2_20161S.entity.Marca;
import br.edu.udc.sistemas.poo2_20161S.session.SessionMarca;

public class FormMarca extends FormCadastro {
	
	public FormMarca() {
		this.menu.setTitle("Menu Marca");
		this.load();
	}

	public void add() {
		IOTools.clear();
		System.out.println("========================");
		System.out.println("|Inserir Marca");
		System.out.println("========================");
		
		System.out.print("Entre com o id: ");
		int id = IOTools.readInteger(); 
		
		System.out.print("Entre com a descricao: ");
		String descricao = IOTools.readString();
		
		Marca marca = new Marca();
		marca.setIdMarca(id);
		marca.setDescricao(descricao);
		
		try {
			SessionMarca.save(marca);
		} catch (Exception e) {

		}
		
		System.out.println("Marca inserida com sucesso!");
		
		IOTools.readChar();	
	}
	
	public void find() {
		IOTools.clear();
		System.out.println("========================");
		System.out.println("|Buscar Marca");
		System.out.println("========================");
		
		System.out.print("Entre com o id: ");
		int id = IOTools.readInteger(); 
		
		Marca marca = new Marca();
		marca.setIdMarca(id);
		
		Marca marcaFind = null;
		try {
			marcaFind = SessionMarca.findByPrimary(marca);
		} catch (Exception e) {

		} 
				
		if (marcaFind == null){
			System.out.println("Marca nao encontrada!");
		} else {
			System.out.println(marcaFind);
		}
		
		IOTools.readChar();
	}
	
	public void print() {
		IOTools.clear();
		System.out.println("========================");
		System.out.println("|Lista de Marcas");
		System.out.println("========================");
		Marca list[] = new Marca[0];
		try {
			list = SessionMarca.find(new Marca());
		} catch (Exception e) {
		}
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i]);
		}
		IOTools.readChar();
	}
	
	public void remove() { 
		IOTools.clear();
		System.out.println("========================");
		System.out.println("|Excluir Marca");
		System.out.println("========================");
		
		System.out.print("Entre com o id: ");
		int id = IOTools.readInteger(); 
		
		Marca marca = new Marca();
		marca.setIdMarca(id);
		
		try {
			SessionMarca.remove(marca);
		} catch (Exception e) {

		}

		IOTools.readChar();	
	}
}