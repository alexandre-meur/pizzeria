package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDao;

public abstract class MenuService {
	public abstract void executeUC(Scanner input, PizzaMemDao dao);
	
	/**
	 * Demande une nouvelle pizza à l'utilisateur
	 * @return la pizza saisie par l'utilisateur
	 */
	protected Pizza demandePizza(Scanner input) {
		String codeAjout, libelleAjout;
		double prixAjout=0;
		boolean inputOk = false;
		
		System.out.println("Veuillez saisir le code : ");
		codeAjout = input.next();
		System.out.println("Veuillez saisir le nom (sans espace): ");
		libelleAjout = input.next();
		while(!inputOk) {
			System.out.println("Veuillez saisir le prix:");
			if(input.hasNextDouble()) {
				prixAjout = input.nextDouble();
				inputOk = true;
			}else {
				input.next();
			}
		}
		return new Pizza(codeAjout, libelleAjout, prixAjout);
	}
}
