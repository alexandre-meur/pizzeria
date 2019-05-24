package fr.pizzeria.model;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

public class PizzaMemDaoTest {
	
	/**
	 * Test de la recherche de pizza.
	 */
	@Test
	public void testFindPizzaByCode() {
		PizzaMemDao dao = new PizzaMemDao();
		Pizza p1 = new Pizza("PEP","Pépéroni",12.50, CategoriePizza.VIANDE);
		Pizza p1Dao = dao.findPizzaByCode("PEP");
		Pizza p2 = new Pizza("CAN","La cannibale",12.50, CategoriePizza.VIANDE);
		Pizza p2Dao = dao.findPizzaByCode("CAN");
		Pizza p3 = new Pizza("IND","L'indienne",14.00, CategoriePizza.SANS_VIANDE);
		Pizza p3Dao = dao.findPizzaByCode("IND");
		
		Assert.assertEquals(p1, p1Dao);
		Assert.assertEquals(p2, p2Dao);
		Assert.assertEquals(p3, p3Dao);
		//Test Majuscules
		Assert.assertEquals(p2, dao.findPizzaByCode("can"));
		Assert.assertNull(dao.findPizzaByCode("NNN"));
	}
	
	@Test
	public void testFindPizzaByCodeNull() {
		PizzaMemDao dao = new PizzaMemDao();
		Assert.assertTrue(null == dao.findPizzaByCode(null));
	}
	
	@Test
	public void testExistPizza() {
		PizzaMemDao dao = new PizzaMemDao();
		
		Assert.assertTrue(dao.pizzaExists("ORI"));
		Assert.assertTrue(dao.pizzaExists("IND"));
		Assert.assertTrue(dao.pizzaExists("MAR"));
		
		//Après ajout
		dao.saveNewPizza(new Pizza("CAL", "Calzone", 13.00, CategoriePizza.SANS_VIANDE));
		Assert.assertTrue(dao.pizzaExists("CAL"));
		
		//Après suppression
		dao.deletePizza("ORI");
		dao.deletePizza("IND");
		dao.deletePizza("MAR");
		Assert.assertFalse(dao.pizzaExists("ORI"));
		Assert.assertFalse(dao.pizzaExists("IND"));
		Assert.assertFalse(dao.pizzaExists("MAR"));
	}
	
	@Test
	public void testExistPizzaNull() {
		PizzaMemDao dao = new PizzaMemDao();
		Assert.assertFalse(dao.pizzaExists(null));
	}
	
	/**
	 * Test de l'ajout de pizza
	 */
	@Test
	public void testSaveNewPizza() {
		PizzaMemDao dao = new PizzaMemDao();
		Pizza p1 = new Pizza("CAL","Calzone",13.20, CategoriePizza.SANS_VIANDE);
		Pizza p2 = new Pizza("MER","La Marine",14.00, CategoriePizza.POISSON);
		int sizeStart = dao.findAllPizzas().size();
		
		dao.saveNewPizza(p1);
		Assert.assertEquals(sizeStart+1, dao.findAllPizzas().size());
		dao.saveNewPizza(p2);
		Assert.assertEquals(sizeStart+2, dao.findAllPizzas().size());
		Assert.assertEquals(p1, dao.findPizzaByCode("CAL"));
		Assert.assertEquals(p2, dao.findPizzaByCode("MER"));
		
		//Ajout d'un pizza déjà existante
		dao.saveNewPizza(p1);
		Assert.assertEquals(sizeStart+2, dao.findAllPizzas().size());
	}
	
	@Test
	public void testSaveNewPizzaNull() {
		PizzaMemDao dao = new PizzaMemDao();
		int sizeStart = dao.findAllPizzas().size();
		
		dao.saveNewPizza(null);
		Assert.assertEquals(sizeStart, dao.findAllPizzas().size());
		Assert.assertTrue(dao.findAllPizzas().stream()
				.anyMatch(p -> p != null));
	}
	
	/**
	 * Test de la modification de pizza
	 */
	@Test
	public void testUpdatePizza1() {
		PizzaMemDao dao = new PizzaMemDao();
		Pizza p1 = new Pizza("CAL","Calzone",2.00, CategoriePizza.SANS_VIANDE);
		Pizza p2 = new Pizza("FRO","La 4 fromage",16.52, CategoriePizza.SANS_VIANDE);
		
		//Modification par une nouvelle pizza
		dao.updatePizza("MAR", p1);
		Assert.assertFalse(dao.pizzaExists("MAR"));
		Assert.assertTrue(dao.pizzaExists("CAL"));
		Assert.assertEquals(p1, dao.findPizzaByCode("CAL"));
		
		//Modification d'une pizza déjà existante, changement du prix
		dao.updatePizza("FRO", p2);
		Assert.assertTrue(dao.pizzaExists("FRO"));
		Assert.assertTrue(12 != dao.findPizzaByCode("FRO").getPrix());
		Assert.assertTrue(16.52 == dao.findPizzaByCode("FRO").getPrix());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testUpdatePizza2() {
		PizzaMemDao dao = new PizzaMemDao();
		Pizza p1 = new Pizza("CAL","Calzone",2.00, CategoriePizza.SANS_VIANDE);
		ArrayList<Pizza> oldList = null;
		
		//Cas où la recherche échoue
		oldList = (ArrayList<Pizza>) dao.findAllPizzas().clone();
		dao.updatePizza("N/A", p1);
		Assert.assertEquals(oldList, dao.findAllPizzas());
	}
	
	@Test
	public void testUpdatePizzaNull1() {
		PizzaMemDao dao = new PizzaMemDao();
		Pizza p1 = new Pizza("CAL","Calzone",2.00, CategoriePizza.SANS_VIANDE);
		int sizeStart = dao.findAllPizzas().size();
		
		dao.updatePizza(null, p1);
		Assert.assertEquals(sizeStart, dao.findAllPizzas().size());
	}
	
	@Test
	public void testUpdatePizzaNull2() {
		PizzaMemDao dao = new PizzaMemDao();
		int sizeStart = dao.findAllPizzas().size();
		
		dao.updatePizza("MAR", null);
		Assert.assertEquals(sizeStart, dao.findAllPizzas().size());
		Assert.assertTrue(dao.pizzaExists("MAR"));
	}
	
	/**
	 * Test de la suppression
	 */
	@Test
	public void testDeletePizza() {
		PizzaMemDao dao = new PizzaMemDao();
		int sizeStart = dao.findAllPizzas().size();
		
		//Suppression
		dao.deletePizza("PEP");
		Assert.assertEquals(sizeStart-1, dao.findAllPizzas().size());
		Assert.assertFalse(dao.pizzaExists("PEP"));
		
		//Suppression d'une pizza qui n'existe pas
		sizeStart = dao.findAllPizzas().size();
		dao.deletePizza("N/A");
		Assert.assertEquals(sizeStart, dao.findAllPizzas().size());
		Assert.assertFalse(dao.pizzaExists("N/A"));
	}
	
	@Test
	public void testDeletePizzaNull() {
		PizzaMemDao dao = new PizzaMemDao();
		int sizeStart = dao.findAllPizzas().size();
		
		//Utilisation avec null
		dao.deletePizza(null);
		Assert.assertEquals(sizeStart, dao.findAllPizzas().size());
	}
	
	/**
	 * Test de la remise à zéro
	 */
	@Test
	public void testReset() {
		PizzaMemDao dao = new PizzaMemDao();
		
		//reset() est appelé dans le constructeur
		Assert.assertEquals(8, dao.findAllPizzas().size());
		Assert.assertTrue(dao.findAllPizzas().contains(dao.findPizzaByCode("PEP")));
		Assert.assertTrue(dao.findAllPizzas().contains(dao.findPizzaByCode("MAR")));
		Assert.assertTrue(dao.findAllPizzas().contains(dao.findPizzaByCode("REIN")));
		Assert.assertTrue(dao.findAllPizzas().contains(dao.findPizzaByCode("FRO")));
		Assert.assertTrue(dao.findAllPizzas().contains(dao.findPizzaByCode("CAN")));
		Assert.assertTrue(dao.findAllPizzas().contains(dao.findPizzaByCode("SAV")));
		Assert.assertTrue(dao.findAllPizzas().contains(dao.findPizzaByCode("ORI")));
		Assert.assertTrue(dao.findAllPizzas().contains(dao.findPizzaByCode("IND")));
	}
}
