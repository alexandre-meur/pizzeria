package fr.pizzeria.utils;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ToString {
	
	public boolean uppercase() default false;
	
}
