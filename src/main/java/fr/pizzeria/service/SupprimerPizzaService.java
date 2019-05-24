package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.IPizzaDao;

public class SupprimerPizzaService extends MenuService{

	@Override
	public void executeUC(Scanner input, IPizzaDao dao) throws PizzaException{
		String codeInput;
		ListerPizzasService listeService = new ListerPizzasService();
		
		//Listage des pizzas
		try {
			listeService.executeUC(input, dao);
		} catch (PizzaException e) {
			throw new DeletePizzaException("Problème pendant l'affichage des pizzas avant la suppression.");
		}
		
		//Demande le code de la pizza
		System.out.println("Entrez le code de la pizza à supprimer : ");
		codeInput = input.next();
		
		//Exception si le code n'est pas présent dans le DAO
		if(!dao.pizzaExists(codeInput)) {
			throw new DeletePizzaException("/!\\ La pizza à supprimer est introuvable.");
		}
		
		//Supression
		dao.deletePizza(codeInput);
	}

}
