package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.model.PizzaMemDao;

public class ListerPizzasService extends MenuService {

	/**
	 * Affiche les pizzas
	 */
	public void executeUC(Scanner input, PizzaMemDao dao) {
		System.out.println("Liste des pizzas");
		System.out.println(dao.toString());
	}

}
