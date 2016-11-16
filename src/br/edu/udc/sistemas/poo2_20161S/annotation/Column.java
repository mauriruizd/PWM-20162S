package br.edu.udc.sistemas.poo2_20161S.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	public static int CHAR = 0;
	public static int STRING = 1;
	public static int DATE = 2;
	public static int TIME = 3;
	public static int DATETIME = 4;
	public static int BOOLEAN = 5;
	public static int INTEGER = 6;
	public static int LONG = 7;
	public static int FLOAT = 8;
	public static int OBJECT = 9;
	
	public String name() default "";
	public int type() default Column.STRING;
	public int length() default 255;
	public boolean orderBy() default false;
	public boolean unique() default false;
	public boolean nullable() default true;
	public boolean insertable() default true;
	public boolean updatable() default true;
}
