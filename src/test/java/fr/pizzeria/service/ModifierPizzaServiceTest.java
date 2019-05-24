package fr.pizzeria.service;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.Mockito;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaServiceTest {

	/**
	 * Création d'une "Rule" qui va permettre *
	 * de substituer le System.in utilisé
	 * par le Scanner * par un mock: systemInMock
	 */
	@Rule
	public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
	
	//la méthode findAllPizzas() retourne NULL
	@Test(expected = PizzaException.class)
	public void testExecuteUC1() throws PizzaException {
		MenuService service = new ModifierPizzaService();
		IPizzaDao mockedDao = Mockito.mock(IPizzaDao.class);
		
		Mockito.when(mockedDao.findAllPizzas()).thenReturn(null);
		Mockito.when(mockedDao.pizzaExists("PEP")).thenReturn(false);
		systemInMock.provideLines("PEP");
		
		service.executeUC(new Scanner(System.in), mockedDao);
	}
	
	//la méthode findAllPizzas() retourne une liste de pizzas contenant des données incohérentes
	@Test(expected = PizzaException.class)
	public void testExecuteUC2() throws PizzaException {
		MenuService service = new ModifierPizzaService();
		IPizzaDao mockedDao = Mockito.mock(IPizzaDao.class);
		ArrayList<Pizza> incoherentList = new ArrayList<Pizza>();
		
		incoherentList.add(null);
		incoherentList.add(new Pizza(null, "Bad Pizza", 666.66, CategoriePizza.POISSON));
		Mockito.when(mockedDao.findAllPizzas()).thenReturn(incoherentList);
		systemInMock.provideLines("BLA");
		
		service.executeUC(new Scanner(System.in), mockedDao);
	}
}
