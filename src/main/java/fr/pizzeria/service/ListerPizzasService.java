package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.IPizzaDao;

public class ListerPizzasService extends MenuService {

	/**
	 * Affiche les pizzas
	 */
	@Override
	public void executeUC(Scanner input, IPizzaDao dao) throws PizzaException{
		System.out.println("Liste des pizzas");
		System.out.println(dao.toString());
	}

}
