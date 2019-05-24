package fr.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import fr.pizzeria.exception.PizzaException;

public class JPAPizzaDao implements IPizzaDao {

	private static EntityManager entityManager = null;
	
	public JPAPizzaDao() {
		super();
		entityManager = Persistence.createEntityManagerFactory("pizza-jpa").createEntityManager();
	}
	
	public void finalize() {
		entityManager.close();
	}
	
	@Override
	public List<Pizza> findAllPizzas() throws PizzaException {
		TypedQuery<Pizza> query = entityManager.createQuery("select p from Pizza p", Pizza.class);
		return query.getResultList();
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws PizzaException {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(pizza);
		transaction.commit();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws PizzaException {
		Pizza oldPizza = findPizzaByCode(codePizza);
		pizza.setId(oldPizza.getId());
		
		//Envoi
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(pizza);
		transaction.commit();

	}

	@Override
	public void deletePizza(String codePizza) throws PizzaException {
		Query query = entityManager.createQuery("DELETE FROM Pizza WHERE code=:code");
		query.setParameter("code", codePizza);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		query.executeUpdate();
		transaction.commit();
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) throws PizzaException {
		List<Pizza> result;
		TypedQuery<Pizza> query = entityManager.createQuery("select p from Pizza p where p.code=:code", Pizza.class);
		query.setParameter("code", codePizza);
		
		result = query.getResultList();
		if(result.isEmpty()) return null;
		else return result.get(0);
	}

	@Override
	public boolean pizzaExists(String codePizza) throws PizzaException {
		TypedQuery<Pizza> query = entityManager.createQuery("select p from Pizza p where p.code=:code", Pizza.class);
		query.setParameter("code", codePizza);
		
		if(query.getResultList().size() == 0) return false;
		else return true;
	}

	@Override
	public void reset() throws PizzaException {
		//suppression des pizzas dans la table
		Query query = entityManager.createQuery("DELETE FROM Pizza");
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		query.executeUpdate();
		transaction.commit();
		
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
	
	public String toString() {
		ArrayList<Pizza> listPizza = null;
		try {
			listPizza = (ArrayList<Pizza>) findAllPizzas();
		} catch (PizzaException e) {
			System.out.println(e.getMessage());
		}
		
		String result = "";
		if(listPizza != null) {
			for(Pizza p : listPizza) {
				result = result + p.toString() + '\n';
			}
		}
		return result;
	}

}
