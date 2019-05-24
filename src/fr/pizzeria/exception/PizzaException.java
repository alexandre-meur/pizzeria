package fr.pizzeria.exception;

public class PizzaException extends Exception{

	private static final long serialVersionUID = 4L;

	public PizzaException() {}
	
	public PizzaException(String msg) {
		super(msg);
	}
}
