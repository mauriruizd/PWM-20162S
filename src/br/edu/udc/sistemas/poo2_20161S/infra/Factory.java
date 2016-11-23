package br.edu.udc.sistemas.poo2_20161S.infra;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import br.edu.udc.sistemas.poo2_20161S.annotation.DataType;
import br.edu.udc.sistemas.poo2_20161S.annotation.Transient;
import br.edu.udc.sistemas.poo2_20161S.annotation.ChildList;
import br.edu.udc.sistemas.poo2_20161S.annotation.Column;

public class Factory {

	public static Object[] createByResultSet(ResultSet rst, Class <?> c) throws Exception {
		Field fieldList[] = Reflection.getFields(c, false);
		Collection <Object> result = new Vector <Object> ();
		while (rst.next()) {
			Object obj = c.newInstance();
			for (int i = 0; i < fieldList.length; i++) {
				Column column = fieldList[i].getAnnotation(Column.class);
				switch (column.type()) {
					case DataType.BOOLEAN:
						Reflection.setFieldValue(fieldList[i], obj, rst.getBoolean(column.name()));
						break;
					case DataType.CHAR:
						Reflection.setFieldValue(fieldList[i], obj, rst.getString(column.name()));
						break;
					case DataType.DATE:
						Reflection.setFieldValue(fieldList[i], obj, rst.getDate(column.name()));
						break;
					case DataType.DATETIME:
						Reflection.setFieldValue(fieldList[i], obj, rst.getDate(column.name()));
						break;
					case DataType.TIME:
						Reflection.setFieldValue(fieldList[i], obj, rst.getTime(column.name()));
						break;
					case DataType.FLOAT:
						Reflection.setFieldValue(fieldList[i], obj, rst.getFloat(column.name()));
						break;
					case DataType.LONG:
						Reflection.setFieldValue(fieldList[i], obj, rst.getLong(column.name()));
						break;
					case DataType.INTEGER:
						Reflection.setFieldValue(fieldList[i], obj, rst.getInt(column.name()));
						break;
					case DataType.STRING:
						Reflection.setFieldValue(fieldList[i], obj, rst.getString(column.name()));
						break;
					case DataType.OBJECT:
						Integer idFK = rst.getInt(column.name());
						Object objFK = fieldList[i].getType().newInstance();
						Reflection.setIdFieldValue(objFK,idFK);
						Reflection.setFieldValue(fieldList[i], obj, objFK);						
					default:
						break;
				}
			}
			result.add(obj);
		}
		return (Object[]) result.toArray(new Object[result.size()]);
	}
	
	private static Object getFormatedValue(Field field, String rawValue) {
		Column column = field.getAnnotation(Column.class);
		int type;
		if (column != null) {
			type = column.type();
		} else {
			Transient transientColumn = field.getAnnotation(Transient.class);
			type = transientColumn.type();
		}
		if (rawValue != null) {
			switch (type) {
			case DataType.CHAR:
				if (rawValue.length() > 0) {
					return rawValue.charAt(0);
				}
				break;
			case DataType.STRING:
				return rawValue;
			case DataType.DATE:
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					return sdf.parse(rawValue);
				} catch (Exception e) {
					return null;
				}
			case DataType.TIME:
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
					return sdf.parse(rawValue);
				} catch (Exception e) {
					return null;
				}
			case DataType.DATETIME:
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					return sdf.parse(rawValue);
				} catch (Exception e) {
					return null;
				}
			case DataType.BOOLEAN:
				return rawValue.equals("true") || (!rawValue.equals("0") && (!rawValue.equals("false"))); 
			case DataType.INTEGER:
				try {
					return Integer.parseInt(rawValue);
				} catch (Exception e) {
					return null;
				}
			case DataType.LONG:
				try {
					return Long.parseLong(rawValue);
				} catch (Exception e) {
					return null;
				}
			case DataType.FLOAT:
				try {
					return Float.parseFloat(rawValue);
				} catch (Exception e) {
					return null;
				}
			case DataType.OBJECT:
				try {
					Integer id = Integer.parseInt(rawValue);
					Object objFK = field.getType().newInstance();
					Reflection.setIdFieldValue(objFK, id);
					return objFK;				
				} catch (Exception e) {
					return null;
				}
			default:
				break;
			}
		}
		return null;
	}
	
	public static Object createById(Integer id, Class <?> c) throws Exception {
		Object obj = c.newInstance();
		Reflection.setIdFieldValue(obj, id);
		return obj;
	}
	
	public static Object createByPost(HashMap <String,String> postData,  Class<?> c) throws Exception {
		Object obj = c.newInstance();
		Field fields[] = Reflection.getFields(c,true);
		for (Iterator iterator = postData.keySet().iterator(); iterator.hasNext();) {
			String postFieldName = (String) iterator.next();
			for (int i = 0; i < fields.length; i++) {
				if (postFieldName.contains(fields[i].getName()) && fields[i].isAnnotationPresent(ChildList.class)) {
					ChildList an = fields[i].getAnnotation(ChildList.class);
					Class<?> childClass = Class.forName(an.className());
					String childFields[] = an.fields();
					String index = postFieldName.substring(postFieldName.indexOf("[") + 1, postFieldName.indexOf("]"));
					HashMap <String,String> subPost = new HashMap<String, String>();
					subPost.put(an.reflectedItem(), postData.get(postFieldName));
					for(String fieldName: childFields) {
						subPost.put(fieldName, postData.get(fieldName + "[" + index + "]"));
					}
					Object newChild = Factory.createByPost(subPost, childClass);
					Collection<Object> childList = new Vector<Object>();
					if (Reflection.getFieldValue(fields[i], obj) != null) {
						Object oldChilds[] = (Object[]) Reflection.getFieldValue(fields[i], obj);
						for(int j = 0; j < oldChilds.length; j++) {
							childList.add(oldChilds[j]);
						}
					}
					childList.add(newChild);
					Reflection.setFieldValue(fields[i], obj, childList.toArray());
					break;
				}
				if (postFieldName.equalsIgnoreCase(fields[i].getName())) {
					Reflection.setFieldValue(fields[i], obj, getFormatedValue(fields[i], postData.get(postFieldName)));
					break;
				}
			}
		}
		return obj;
	}
	
}
