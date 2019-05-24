package fr.pizzeria.service;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.IPizzaDao;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.PizzaMemDao;

public class ListerPizzaServiceTest {
	
	/**
	 * Création d'une "Rule" qui va permettre *
	 * de substituer le System.in utilisé
	 * par le Scanner * par un mock: systemInMock
	 */
	@Rule
	public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
	
	/**
	 * Test de la demande de pizza
	 */
	@Test
	public void testDemandePizza() {
		Pizza p = new Pizza("CAL", "Calzone", 12.3, CategoriePizza.VIANDE);
		MenuService s = new ListerPizzasService();
		Scanner scan = new Scanner(System.in);
		
		systemInMock.provideLines("CAL","Calzone","12,3","1");
		
		Assert.assertEquals(p, s.demandePizza(scan));
		scan.close();
	}
	
	/**
	 * Test de la demande de pizza
	 */
	@Test
	public void testDemandeCategorie() {
		MenuService s = new ListerPizzasService();
		Scanner scan = new Scanner(System.in);
		
		systemInMock.provideLines("1");
		Assert.assertEquals(CategoriePizza.VIANDE, s.demandeCategorie(scan));
		systemInMock.provideLines("2");
		Assert.assertEquals(CategoriePizza.POISSON, s.demandeCategorie(scan));
		systemInMock.provideLines("3");
		Assert.assertEquals(CategoriePizza.SANS_VIANDE, s.demandeCategorie(scan));
		
		//Test d'entrées érronées
		systemInMock.provideLines("hgj","coucou salut","7","8","9","3");
		Assert.assertEquals(CategoriePizza.SANS_VIANDE, s.demandeCategorie(scan));
		scan.close();
	}
	
	/**
	 * Test du service
	 */
	@Test
	public void testExecuteUC() {
		MenuService s = new ListerPizzasService();
		Scanner scan = new Scanner(System.in);
		IPizzaDao dao = new PizzaMemDao();
		String[] initPizzaTab = {"PEP Pépéroni (12,50€)",
				"MAR Margherita (14,00€) ", 
				"REIN La Reine (11,50€) ", 
				"FRO La 4 Fromages (12,00€) ",
				"CAN La cannibale (12,50€) ",
				"SAV La savoyarde (13,00€) ", 
				"ORI L'orientale (13,50€) ",
				"IND L'indienne (14,00€)"};

		//Redirection de stdout
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(baos));
		
		try {
			s.executeUC(scan, dao);
		} catch (PizzaException e) {
			Assert.fail();
		}
		
		System.setOut(System.out);
		Assert.assertTrue(Stream.of(initPizzaTab)
				.allMatch(p -> baos.toString().contains(p)));
		Assert.assertTrue(baos.toString().contains("Liste des pizzas"));
		
	}

}
