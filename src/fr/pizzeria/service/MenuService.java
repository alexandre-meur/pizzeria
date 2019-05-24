package fr.pizzeria.service;

import java.util.Scanner;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDao;

public abstract class MenuService {
	public abstract void executeUC(Scanner input, PizzaMemDao dao) throws PizzaException;
	
	/**
	 * Demande une nouvelle pizza à l'utilisateur
	 * @return la pizza saisie par l'utilisateur
	 */
	protected Pizza demandePizza(Scanner input) {
		String codeAjout, libelleAjout;
		double prixAjout=0;
		boolean inputOk = false;
		CategoriePizza categorie;
		
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
		categorie = demandeCategorie(input);
		return new Pizza(codeAjout, libelleAjout, prixAjout, categorie);
	}
	
	/**
	 * Demande une catégorie de pizza à l'utilisateur
	 * @param input Le scanner
	 * @return une catégorie de pizza
	 */
	protected CategoriePizza demandeCategorie(Scanner input) {
		int choix = 0;
		
		System.out.println("Entrez la catégorie de la pizza");
		System.out.println("1.Avec viande");
		System.out.println("2.Avec poisson");
		System.out.println("3.Sans viande");
		while(true) {
			if(input.hasNextInt()) {
				choix = input.nextInt();
				switch(choix) {
				case 1:
					return CategoriePizza.VIANDE;
				case 2:
					return CategoriePizza.POISSON;
				case 3:
					return CategoriePizza.SANS_VIANDE;
				default:
					System.out.println("Entrez 1, 2 ou 3");
				}
			}else {
				System.out.println("Entrez un choix correct.");
				input.next();
			}
		}
	}
}
