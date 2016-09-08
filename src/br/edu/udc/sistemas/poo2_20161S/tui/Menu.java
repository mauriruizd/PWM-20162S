package br.edu.udc.sistemas.poo2_20161S.tui;

import java.util.Collection;
import java.util.Vector;

public class Menu {	
	
	private String title;
	private Vector options;
		
	public Menu() {
		this.options = new Vector();
		this.title = null;
	}
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Vector getOptions() {
		return options;
	}

	public void show() {
		System.out.println("========================");
		System.out.println("| " + this.title);
		System.out.println("========================");
		
		for(int i=0; i<this.options.size(); i++) {
			System.out.println("| " + this.options.get(i));
		}
		System.out.println("========================");
	}		
}