package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.Iterator;

public class PizzaMemDao implements IPizzaDao {
	
	private static ArrayList<Pizza> listPizza;
	
	public PizzaMemDao() {
		listPizza = new ArrayList<Pizza>();
		
		//Ajouts des pizzas
		saveNewPizza(new Pizza("PEP","Pépéroni",12.50));
		saveNewPizza(new Pizza("MAR","Margherita",14.00));
		saveNewPizza(new Pizza("REIN","La Reine",11.50));
		saveNewPizza(new Pizza("FRO","La 4 Fromages",12.00));
		saveNewPizza(new Pizza("CAN","La cannibale",12.50));
		saveNewPizza(new Pizza("SAV","La savoyarde",13.00));
		saveNewPizza(new Pizza("ORI","L'orientale",13.50));
		saveNewPizza(new Pizza("IND","L'indienne",14.00));
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
	 */
	public void saveNewPizza(Pizza pizza) {
		//On vérifie si le code pizza est libre
		if(!pizzaExists(pizza.getCode())) {
			//Ajout de la pizza
			listPizza.add(pizza);
		}	
	}

	/**
	 *  Cherche une pizza avec son code et la met à jour
	 * @param Le code recherché
	 * @Param La nouvelle pizza
	 */
	public void updatePizza(String codePizza, Pizza pizza) {
		Pizza oldPizza;
		
		//Recherche de la pizza
		oldPizza = findPizzaByCode(codePizza);
		
		//Si la pizza existe, on la met à jour
		if(oldPizza != null) {
			oldPizza.setCode(pizza.getCode());
			oldPizza.setLibelle(pizza.getLibelle());
			oldPizza.setPrix(pizza.getPrix());
		}
	}

	/**
	 *  Cherche une pizza avec son code et la supprime si elle existe
	 * @param Le code recherché
	 */
	public void deletePizza(String codePizza) {
		listPizza.remove(findPizzaByCode(codePizza));
	}

	/**
	 * Cherche une pizza avec son code et la renvoie si elle existe
	 * @param Le code recherché
	 * @return La pizza si elle est trouvée ou null si on ne trouve rien
	 */
	public Pizza findPizzaByCode(String codePizza) {
		Iterator<Pizza> iterator = listPizza.iterator();
		Pizza pizza;
		
		while(iterator.hasNext()) {
			pizza = iterator.next();
			if(pizza.getCode().equals(codePizza)) {
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
		Iterator<Pizza> iterator = listPizza.iterator();
		Pizza pizza;
		
		while(iterator.hasNext()) {
			pizza = iterator.next();
			if(pizza.getCode().equals(codePizza)) {
				return true;
			}
		}
		
		return false;
	}

	public String toString() {
		String result = "";
		Iterator<Pizza> iterator = listPizza.iterator();
		
		while(iterator.hasNext()) {
			result = result + iterator.next().toString() + '\n';
		}
		
		return result;
	}
}
