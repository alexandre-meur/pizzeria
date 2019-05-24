/**
 * TP1 Java objet
 * @author Alexandre Meur
 */

package fr.pizzeria.console;

import java.util.Arrays;
import java.util.Scanner;
import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {
	
	public static Scanner input = new Scanner(System.in);
	public static Pizza[] tabPizza = {
			new Pizza("PEP","Pépéroni",12.50),
			new Pizza("MAR","Margherita",14.00),
			new Pizza("REIN","La Reine",11.50),
			new Pizza("FRO","La 4 Fromages",12.00),
			new Pizza("CAN","La cannibale",12.50),
			new Pizza("SAV","La savoyarde",13.00),
			new Pizza("ORI","L'orientale",13.50),
			new Pizza("IND","L'indienne",14.00)
		};
	//Taille par laquelle on agrandit le tableau quand il est plein
	public static final int TAB_AGRANDIR = 5; 

	public static void main(String[] args) throws Exception {
		boolean quitte = false;
		int choixUtilisateur = 0;
		
		while(!quitte) {
			//Affichage du menu
			afficheMenu();
			
			//Récupération du choix de l'utilisateur
			choixUtilisateur = recupereChoixUtilisateur();
			
			//gestion du choix de l'utilisateur
			switch(choixUtilisateur){
				//Liste des pizzas
				case 1:
					affichePizzas();
					break;
				//Ajout d'une pizza
				case 2:
					ajoutePizza();
					break;
				//Modification d'une pizza
				case 3:
					majPizza();
					break;
				//Suppression d'une pizza
				case 4:
					supprimePizza();
					break;
				//Fin du programme
				case 99:
					System.out.println("Au revoir :(");
					quitte = true;
					break;
				default:
					System.out.println("/!\\ Ne devrait pas arriver ici /!\\");
					throw new Exception("Default switch ne devrait pas être accessible");
			}
		}
	}
	
	/**
	 * Affiche le menu
	 */
	public static void afficheMenu() {
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
	}
	
	/**
	 * Récupère le choix de l'utilisateur pour le menu. Ne peut pas retourner un choix qui n'existe pas.
	 * @return Un choix valide pour le menu
	 */
	public static int recupereChoixUtilisateur() {
		boolean inputOk = false;
		int choix = 0;
		while(!inputOk) {
			if(input.hasNextInt()) {
				choix = input.nextInt();
				//Si l'entrée n'est pas bonne on demande une option valide
				if(choix != 1 && choix != 2 && choix != 3 && choix != 4 && choix != 99) {
					System.out.println("Entrez une option valide");
				}else {
					inputOk = true;
				}
			}else {
				System.out.println("Entrez une option valide");
				input.next();
			}
		}
		return choix;
	}
	
	/**
	 * Affiche les pizzas
	 */
	public static void affichePizzas() {
		System.out.println("Liste des pizzas");
		for(int i=0; i<tabPizza.length; i++) {
			if(tabPizza[i] != null) {
				System.out.println(tabPizza[i].toString());
			}
		}
	}
	
	/**
	 * Demande une nouvelle pizza à l'utilisateur
	 * @return la pizza saisie par l'utilisateur
	 */
	public static Pizza demandePizza() {
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
	
	/**
	 * Ajoute une pizza dans le tableau et gère la taille du tableau si besoin
	 */
	public static void ajoutePizza() {
		Pizza pizzaAjout;
		
		//Demandes des infos à l'utilisateur
		System.out.println("Ajout d'une nouvelle pizza");
		pizzaAjout = demandePizza();
		
		//Si le tableau est plein on l'agrandit
		if(tabPizza[tabPizza.length-1] != null) {
			tabPizza = Arrays.copyOf(tabPizza, tabPizza.length + TAB_AGRANDIR);
		}
		
		//Ajout de la pizza au premier emplacement libre
		for(int i=0; i<tabPizza.length; i++) {
			if(tabPizza[i] == null) {
				tabPizza[i] = pizzaAjout;
				break;
			}
		}	
	}
	
	/**
	 * Recherche une pizza correspondante au code fourni et retourne son id dans le
	 * tableau (pas l'id de l'instance de la classe pizza)
	 * @param code Le code permettant de rechercher la pizza
	 * @return l'id de la pizza dans le tableau. -1 si la pizza n'est pas trouvée.
	 */
	public static int recherchePizza(String code) {
		for(int i=0; i<tabPizza.length; i++) {
			if(tabPizza[i] != null && tabPizza[i].getCode().equals(code)) {
				return i;
			}
		}
		//On affiche que la pizza n'a pas été trouvée
		System.out.println("Pizza non trouvée");
		return -1;
	}
	
	/**
	 * Met à jour une pizza du tableau
	 */
	public static void majPizza() {
		String codeRecherche;
		int idTabPizza = -1; //-1 = pizza non trouvée
		Pizza nouvellePizza;
		
		//Demande le code de la pizza
		System.out.println("Mise à jour d'une pizza");
		System.out.println("Entrez le code de la pizza à modifier : ");
		codeRecherche = input.next();
		
		//Recherche de la pizza
		idTabPizza = recherchePizza(codeRecherche);
		
		if(idTabPizza >= 0) {
			//demande de la nouvelle pizza
			nouvellePizza = demandePizza();
			
			//ajout
			tabPizza[idTabPizza] = nouvellePizza;
		}
	}
	
	public static void supprimePizza() {
		String codeRecherche;
		int idTabPizza = 0;
		
		//affichage du menu
		affichePizzas();
		System.out.println("Suppression d'une pizza");
		
		//Demande le code de la pizza
		System.out.println("Entrez le code de la pizza à supprimer : ");
		codeRecherche = input.next();
		idTabPizza = recherchePizza(codeRecherche);
		
		//Suppression si on a trouvé une pizza
		if(idTabPizza >= 0) {
			tabPizza[idTabPizza] = null;
		}
	}
}




