package fr.pizzeria.model;

import java.util.List;

import fr.pizzeria.exception.*;

public interface IPizzaDao {
	
	List<Pizza> findAllPizzas() throws PizzaException;
	void saveNewPizza(Pizza pizza) throws PizzaException;
	void updatePizza(String codePizza, Pizza pizza) throws PizzaException;
	void deletePizza(String codePizza) throws PizzaException;
	Pizza findPizzaByCode(String codePizza) throws PizzaException;
	boolean pizzaExists(String codePizza) throws PizzaException;
	void reset() throws PizzaException;
	
}
