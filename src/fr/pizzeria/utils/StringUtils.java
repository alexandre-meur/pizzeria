package fr.pizzeria.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

public class StringUtils {
	
	public static void util(Object myObject){
		Field[] fields = myObject.getClass().getFields();
		boolean uppercase;
		String value = "";
		
		for(Field f : fields) {
			for(Annotation a : f.getAnnotations()) {
				if(a instanceof fr.pizzeria.utils.ToString) {
					uppercase = ((ToString)a).uppercase();
					if(uppercase) {
						try {
							value = (String)f.get(myObject);
							value = value.toUpperCase();
							f.set(myObject, value);
						}catch(Exception e) {
							System.out.println("Pas bien !");
						}
					}
				}
			}
		}
	}
}
