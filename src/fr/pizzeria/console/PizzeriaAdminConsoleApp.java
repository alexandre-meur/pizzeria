package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		boolean inputOk = false, quitte = false;
		int choixUtilisateur = 0;
		
		while(!quitte) {
			//Affichage du menu
			afficheMenu();
			
			//Récupération du choix de l'utilisateur
			inputOk=false;
			while(!inputOk) {
				if(input.hasNextInt()) {
					choixUtilisateur = input.nextInt();
					//Si l'entrée n'est pas bonne on demande une option valide
					if(choixUtilisateur != 1 && choixUtilisateur != 2 
							&& choixUtilisateur != 3 && choixUtilisateur != 4
							&& choixUtilisateur != 99) {
						System.out.println("Entrez une option valide");
					}else {
						inputOk = true;
					}
				}else {
					System.out.println("Entrez une option valide");
					input.next();
				}
			}
			
			switch(choixUtilisateur){
			case 1:
				System.out.println("Liste des pizzas");
				break;
			case 2:
				System.out.println("Ajout d'une nouvelle pizza");
				break;
			case 3:
				System.out.println("Mise à jour d'une pizza");
				break;
			case 4:
				System.out.println("Suppression d'une pizza");
				break;
			case 99:
				System.out.println("Au revoir :(");
				quitte = true;
				break;
			default:
				System.out.println("/!\\ Ne devrait pas arriver ici /!\\");
			}
		}
	}
	
	/**Affiche le menu **/
	public static void afficheMenu() {
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
	}

}
