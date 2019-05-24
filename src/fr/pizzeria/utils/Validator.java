package fr.pizzeria.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

import fr.pizzeria.exception.PizzaException;

public class Validator {
	
	public static void check(Object myObject) throws Exception {
		Field[] fields = myObject.getClass().getFields();

		//Pracours de toute les annotations
		for(Field f : fields) {
			//f.setAccessible(true);
			for(Annotation a : f.getAnnotations()) {
				//Si c'est une annotation Rule, on vérifie que la regle est respéctée
				if(a instanceof Rule && ((Rule)a).min() > (double)f.get(myObject)) {
					throw new PizzaException("Sous la valeur minimum");
				}
			}
		}
	}
	
}
