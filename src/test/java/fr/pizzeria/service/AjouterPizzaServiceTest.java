package fr.pizzeria.service;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.IPizzaDao;
import fr.pizzeria.model.PizzaMemDao;

public class AjouterPizzaServiceTest {
	
	/**
	 * Création d'une "Rule" qui va permettre *
	 * de substituer le System.in utilisé
	 * par le Scanner * par un mock: systemInMock
	 */
	@Rule
	public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
	
	/**
	 * Test du service d'ajout de pizza
	 */
	@Test
	public void executeUCTest() {
		MenuService s = new AjouterPizzaService();
		Scanner scan = new Scanner(System.in);
		IPizzaDao dao = new PizzaMemDao();
		int sizeStart = 0;
		try {
			sizeStart = dao.findAllPizzas().size();
			systemInMock.provideLines("CAL","Calzone","12","2");
			s.executeUC(scan, dao);
			Assert.assertEquals(sizeStart+1, dao.findAllPizzas().size());
		} catch (PizzaException e) {
			Assert.fail();
		}
	}

}
