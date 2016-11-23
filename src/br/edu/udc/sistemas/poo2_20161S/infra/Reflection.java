package br.edu.udc.sistemas.poo2_20161S.infra;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Vector;

import br.edu.udc.sistemas.poo2_20161S.annotation.Column;
import br.edu.udc.sistemas.poo2_20161S.annotation.Entity;
import br.edu.udc.sistemas.poo2_20161S.annotation.Id;
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
	
	public static String getTableName(Class<?> c) throws Exception {
		validate(c);
		Table table = c.getAnnotation(Table.class);
		return table.name();
	}
	
	public static Field getIdField(Class<?> c) throws Exception {
		validate(c);
		Field fieldsList[] = c.getDeclaredFields();
		for(int i = 0; i < fieldsList.length; i++) {
			Field field = fieldsList[i];
			if ( field.isAnnotationPresent(Id.class) && (field.isAnnotationPresent(Column.class)) ) {
				return field;
			}
		}
		throw new Exception("Id field not found");
	}
	
	public static Object getIdFieldValue(Object obj) throws Exception {
		return getFieldValue(getIdField(obj.getClass()), obj);
	}
	
	public static void setIdFieldValue(Object obj, Object value) throws Exception {
		setFieldValue(getIdField(obj.getClass()), obj, value);
	}
	
	public static Object getFieldValue(Field field, Object obj) throws Exception {
		Class<?> c = obj.getClass();
		Method methodsList[] = c.getDeclaredMethods();
		for(int i = 0; i < methodsList.length; i++) {
			if(methodsList[i].getName().equalsIgnoreCase("get" + field.getName())) {
				return methodsList[i].invoke(obj, new Object[0]);
			}
		}
		throw new Exception("Getter method not found");
	}
	
	public static Field[] getFields(Class<?> c, boolean bTransient) throws Exception {
		validate(c);
		Field fieldsList[] = c.getDeclaredFields();
		Collection<Field> result = new Vector<Field>();
		for(int i = 0; i < fieldsList.length; i++) {
			Field field = fieldsList[i];
			if ((field.isAnnotationPresent(Column.class)) && 
				((bTransient) || (!field.isAnnotationPresent(Transient.class)))) {
				result.add(field);
			}
		}
		return (Field[]) result.toArray(new Field[result.size()]);
	}
	
	public static void setFieldValue(Field field, Object obj, Object value) throws Exception {
		Class<?> c = obj.getClass();
		Method methodsList[] = c.getDeclaredMethods();
		boolean bOk = false;
		for(int i = 0; i < methodsList.length; i++) {
			if(methodsList[i].getName().equalsIgnoreCase("set" + field.getName())) {
				methodsList[i].invoke(obj, value);
				bOk = true;
				break;
			}
		}
		if (!bOk) {
			throw new Exception("Setter method not found");
		}
	}
}
