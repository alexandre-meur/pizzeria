package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.*;

public class AjouterPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner input, PizzaMemDao dao) throws SavePizzaException {
		Pizza inputPizza;
		
		//Demande d'une nouvelle pizza
		inputPizza = demandePizza(input);
		
		//Si le code existe déjà, on lance une exception
		if(dao.pizzaExists(inputPizza.getCode())) {
			throw new SavePizzaException("/!\\ Code de la pizza déjà existant");
		}
		
		//Ajout de la pizza
		dao.saveNewPizza(inputPizza);
	}

}
