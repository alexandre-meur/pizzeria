package fr.pizzeria.exception;

public class DeletePizzaException extends PizzaException {

	private static final long serialVersionUID = 3L;

	public DeletePizzaException() {}
	
	public DeletePizzaException(String msg) {
		super(msg);
	}
}
