package br.edu.udc.sistemas.poo2_20161S.tui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.Vector;

public class IOTools {

	public static void clear() {
//		Robot robbie;
//		try {
//			robbie = new Robot();
//
//			// clears the console
//			robbie.keyPress(KeyEvent.VK_SHIFT);
//			robbie.keyPress(KeyEvent.VK_F10);
//			robbie.keyRelease(KeyEvent.VK_SHIFT);
//			robbie.keyRelease(KeyEvent.VK_F10);
//			robbie.keyPress(KeyEvent.VK_R);
//			robbie.keyRelease(KeyEvent.VK_R);
//		} catch (AWTException e) {
//		}
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	public static Character readChar() {
		Scanner sc = new Scanner(System.in);
		return sc.next().charAt(0);
	}

	public static String readString() {
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}

	public static Integer readInteger() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}
	
	public static boolean listToFile(Vector list, String path) {
		try {
			PrintWriter out = new PrintWriter(path);			
			if (list.size() > 0) {
				out.println(list.get(0).getClass().getName());
			}
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i);
				String values[] = obj.toString().split(";");
				
				for (int j = 0; j < values.length; j++) {
					out.println(values[j]);
				}
			}			
			out.close();
			return true;
		} catch (FileNotFoundException e) {
		}
		return false;
	}
	
	public static Vector fileToList(String path) {
		Vector result = new Vector();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
		    String classe = br.readLine();
		    Class c = Class.forName(classe); //importa a classe e tempo de execução
		    Field listFields[] = c.getDeclaredFields();

		    String line = br.readLine();
		    while (line != null) {
		    	Object obj = c.newInstance();
		    	listFields[0].setAccessible(true); //quebrar o encapsulamento
		    	try {
		    		listFields[0].set(obj, line);
		    	} catch(Exception e) {
		    		listFields[0].set(obj, Integer.parseInt(line));
		    	}
		    	
		    	for (int i = 1; i < listFields.length; i++) {
		    		line = br.readLine();
			    	listFields[i].setAccessible(true); //quebrar o encapsulamento
			    	try {
			    		listFields[i].set(obj, line);
			    	} catch(Exception e) {
			    		listFields[i].set(obj, Integer.parseInt(line));
			    	}
				}
		    	result.add(obj);
		    	line = br.readLine();
		    }		    
		    br.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}
}