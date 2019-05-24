package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.IPizzaDao;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.Validator;

public class ModifierPizzaService extends MenuService {

	@Override
	public void executeUC(Scanner input, IPizzaDao dao) throws PizzaException {
		Pizza pizzaInput;
		String codeInput;
		
		//Demande le code de la pizza
		System.out.println("Mise à jour d'une pizza");
		System.out.println("Entrez le code de la pizza à modifier : ");
		codeInput = input.next();
		
		//Si la pizza n'existe pas on lève une exception
		if(!dao.pizzaExists(codeInput)) {
			throw new UpdatePizzaException("/!\\ La pizza à modifier est introuvable.");
		}
		
		//Demande la nouvelle pizza
		System.out.println("Entrez la nouvelle pizza");
		pizzaInput = demandePizza(input);
		
		//Check de la pizza
		try {
			Validator.check(pizzaInput);
		}catch(PizzaException e) {
			throw new UpdatePizzaException(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Maj
		dao.updatePizza(codeInput, pizzaInput);
	}
}
