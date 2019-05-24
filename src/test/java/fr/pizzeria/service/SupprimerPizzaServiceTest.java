package fr.pizzeria.service;

import java.util.Scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.Mockito;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.IPizzaDao;

public class SupprimerPizzaServiceTest {
	
	/**
	 * Création d'une "Rule" qui va permettre *
	 * de substituer le System.in utilisé
	 * par le Scanner * par un mock: systemInMock
	 */
	@Rule
	public TextFromStandardInputStream systemInMock = TextFromStandardInputStream.emptyStandardInputStream();
	
	@Test
	public void testExecuteUC() throws PizzaException {
		MenuService service = new SupprimerPizzaService();
		IPizzaDao mockedDao = Mockito.mock(IPizzaDao.class);
		
		Mockito.when(mockedDao.pizzaExists("PEP")).thenReturn(true);
		systemInMock.provideLines("PEP");
		
		service.executeUC(new Scanner(System.in), mockedDao);
	}
	
	/**
	 * Suppression d'une pizza qui n'existe pas
	 * @throws PizzaException
	 */
	@Test(expected = PizzaException.class)
	public void testExecuteUCException() throws PizzaException {
		MenuService service = new SupprimerPizzaService();
		IPizzaDao mockedDao = Mockito.mock(IPizzaDao.class);
		
		Mockito.when(mockedDao.pizzaExists("MAR")).thenReturn(false);
		systemInMock.provideLines("MAR");
		
		service.executeUC(new Scanner(System.in), mockedDao);
	}

}
