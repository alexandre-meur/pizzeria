package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.Iterator;

public class PizzaMemDao implements IPizzaDao {
	
	private static ArrayList<Pizza> listPizza;
	
	public PizzaMemDao() {
		listPizza = new ArrayList<Pizza>();
		
		//Ajouts des pizzas
		reset();
	}
	
	/**
	 * Renvoie le tableau de pizzas
	 * @return le tableau de pizzas
	 */
	public ArrayList<Pizza> findAllPizzas() {
		return listPizza;
	}

	/**
	 * Ajout d'une pizza
	 * @param la pizza à ajouter
	 * @throws Exception 
	 */
	public void saveNewPizza(Pizza pizza) {
		if(pizza != null) {
			//Si la pizza n'existe pas déjà on l'ajoute
			if(!pizzaExists(pizza.getCode())) listPizza.add(pizza);
		}
	}

	/**
	 *  Cherche une pizza avec son code et la met à jour
	 * @param Le code recherché
	 * @Param La nouvelle pizza
	 */
	public void updatePizza(String oldCode, Pizza newPizza) {
		Pizza oldPizza;
		
		//Recherche de la pizza
		oldPizza = findPizzaByCode(oldCode);
		
		//Si la pizza existe, on la met à jour
		if(oldPizza != null && newPizza != null) {
			oldPizza.setCode(newPizza.getCode());
			oldPizza.setLibelle(newPizza.getLibelle());
			oldPizza.setPrix(newPizza.getPrix());
		}
	}

	/**
	 *  Cherche une pizza avec son code et la supprime si elle existe
	 * @param Le code recherché
	 */
	public void deletePizza(String codePizza) {
		Iterator<Pizza> it = listPizza.iterator();
		Pizza p;
		boolean continueRecherche = true;
		
		while(it.hasNext() && continueRecherche) {
			p = it.next();
			if(p.compareCode(codePizza)) {
				it.remove();
				continueRecherche = false;
			}
		}
	}

	/**
	 * Cherche une pizza avec son code et la renvoie si elle existe
	 * @param Le code recherché
	 * @return La pizza si elle est trouvée ou null si on ne trouve rien
	 */
	public Pizza findPizzaByCode(String codePizza) {
		Iterator<Pizza> it = listPizza.iterator();
		Pizza pizza;
		
		while(it.hasNext()) {
			pizza = it.next();
			if(pizza.compareCode(codePizza)) {
				return pizza;
			}
		}

		return null;
	}

	/**
	 * Cherche une pizza avec son code et indique si elle existe
	 * @param Le code recherché
	 * @return true si la pizza existe, false sinon
	 */
	public boolean pizzaExists(String codePizza) {
		Iterator<Pizza> it = listPizza.iterator();
		Pizza pizza;
		
		while(it.hasNext()) {
			pizza = it.next();
			if(pizza.compareCode(codePizza)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Redéfinition de toString
	 * @return une String représentant la liste de pizza
	 */
	public String toString() {
		String result = "";
		for(Pizza p : listPizza) {
			result = result + p.toString() + '\n';
		}
		
		return result;
	}

	@Override
	public void reset(){
		listPizza = new ArrayList<Pizza>();
		
		//Ajouts des pizzas
		saveNewPizza(new Pizza("PEP","Pépéroni",12.50, CategoriePizza.VIANDE));
		saveNewPizza(new Pizza("MAR","Margherita",14.00, CategoriePizza.SANS_VIANDE));
		saveNewPizza(new Pizza("REIN","La Reine",11.50, CategoriePizza.VIANDE));
		saveNewPizza(new Pizza("FRO","La 4 Fromages",12.00, CategoriePizza.SANS_VIANDE));
		saveNewPizza(new Pizza("CAN","La cannibale",12.50, CategoriePizza.VIANDE));
		saveNewPizza(new Pizza("SAV","La savoyarde",13.00, CategoriePizza.SANS_VIANDE));
		saveNewPizza(new Pizza("ORI","L'orientale",13.50, CategoriePizza.VIANDE));
		saveNewPizza(new Pizza("IND","L'indienne",14.00, CategoriePizza.SANS_VIANDE));
		
	}
}
