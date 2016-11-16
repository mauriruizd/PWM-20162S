package br.edu.udc.sistemas.poo2_20161S.infra;

import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.Table;

public class Reflection {

	public static void validate(Class<?> c) throws Exception {
		if(!c.isAnnotationPresent(Entity.class)) {
			throw new Exception("Missing @Entity");
		}
		
		if(!c.isAnnotationPresent(Table.class)) {
			throw new Exception("Missing @Table");
		}
	}
}
