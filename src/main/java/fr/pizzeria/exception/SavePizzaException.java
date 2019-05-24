package fr.pizzeria.exception;

public class SavePizzaException extends PizzaException {

	private static final long serialVersionUID = 1L;

	public SavePizzaException() {}
	
	public SavePizzaException(String msg) {
		super(msg);
	}
}
