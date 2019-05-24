package fr.pizzeria.exception;

public class UpdatePizzaException extends PizzaException {

	private static final long serialVersionUID = 2L;

	public UpdatePizzaException() {}
	
	public UpdatePizzaException(String msg) {
		super(msg);
	}
}
