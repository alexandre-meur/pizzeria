package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDao;

public class ModifierPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner input, PizzaMemDao dao) {
		Pizza pizzaInput;
		String codeInput;
		
		//Demande le code de la pizza
		System.out.println("Mise à jour d'une pizza");
		System.out.println("Entrez le code de la pizza à modifier : ");
		codeInput = input.next();
		
		//Demande la nouvelle pizza
		System.out.println("Entrez la nouvelle pizza");
		pizzaInput = demandePizza(input);
		
		//Maj
		dao.updatePizza(codeInput, pizzaInput);
	}
}
