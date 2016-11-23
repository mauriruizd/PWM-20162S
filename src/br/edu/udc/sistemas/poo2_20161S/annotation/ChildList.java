package br.edu.udc.sistemas.poo2_20161S.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChildList {

	public String reflectedItem();
	public String className();
	public String[] fields();
}
