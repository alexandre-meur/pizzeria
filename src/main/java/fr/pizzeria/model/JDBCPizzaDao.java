package fr.pizzeria.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.pizzeria.exception.*;

public class JDBCPizzaDao implements IPizzaDao {
	
	private static String DB_URL = "";
	private static String DB_USER = "";
	private static String DB_PASSWORD = "";

	/**
	 * Récupère les infos de connection
	 */
	public JDBCPizzaDao() {
		DB_URL = ResourceBundle.getBundle("jdbc").getString("DB_URL");
		DB_USER = ResourceBundle.getBundle("jdbc").getString("DB_USER");
		DB_PASSWORD = ResourceBundle.getBundle("jdbc").getString("DB_PASSWORD");
	}
	
	/**
	 * Fournit une connection
	 * @return la connection
	 * @throws PizzaException
	 */
	public Connection connect() throws PizzaException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			throw new PizzaException("Ne peut pas se connecter à la Base de données.");
		}
	}

	public List<Pizza> findAllPizzas() throws PizzaException {
		ArrayList<Pizza> result = new ArrayList<Pizza>();
		ResultSet data = null;
		Statement requete = null;
		Connection connection = null;
		Pizza currentPizza = null;

		try {
			//Récupération des données
			connection = connect();
			requete = connection.createStatement();
			data = requete.executeQuery("SELECT * FROM pizzas");
		
		
			//Traitement des données
			while(data.next()) {
				currentPizza = new Pizza(data);
				result.add(currentPizza);
			}
		
			//Fermeture du ResultSet et du Statement
			data.close();
			requete.close();
			connection.close();
		} catch (SQLException e) {
			throw new PizzaException("Problème SQL lors de la liste des pizzas");
		}
		
		return result;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws PizzaException {
		Connection connection = null;
		
		try {
			connection = connect();
			PreparedStatement requete = connection.prepareStatement(
					"INSERT INTO pizzas (code, libelle, prix, categorie)"
					+ "VALUES (?, ?, ?, ?)");
			//Préparation et lancement de la requête
			requete.setString(1, pizza.getCode());
			requete.setString(2, pizza.getLibelle());
			requete.setDouble(3, pizza.getPrix());
			requete.setInt(4, pizza.getCategorie().toInt());
			requete.executeUpdate();
			requete.close();
			connection.close();
		} catch (SQLException e) {
			throw new SavePizzaException("Problème SQL lors de l'ajout d'une pizza");
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws PizzaException {
		Connection connection = null;
		
		try {
			connection = connect();
			PreparedStatement requete = connection.prepareStatement(
					"UPDATE pizzas "
					+ "SET code=?, libelle=?, prix=?, categorie=? "
					+ "WHERE code = ?");
			//Préparation et lancement de la requête
			requete.setString(5, codePizza);
			requete.setString(1, pizza.getCode());
			requete.setString(2, pizza.getLibelle());
			requete.setDouble(3, pizza.getPrix());
			requete.setInt(4, pizza.getCategorie().toInt());
			requete.executeUpdate();
			requete.close();
			connection.close();
		} catch (SQLException e) {
			throw new UpdatePizzaException("Problème SQL lors de la liste des pizzas");
		}

	}

	@Override
	public void deletePizza(String codePizza) throws PizzaException {
		Connection connection = null;
		
		try {
			connection = connect();
			PreparedStatement requete = connection.prepareStatement(
					"DELETE FROM pizzas "
					+ "WHERE code = ?");
			requete.setString(1, codePizza);
			requete.executeUpdate();
			requete.close();
			connection.close();
		} catch (SQLException e) {
			throw new DeletePizzaException("Problème SQL lors de la supression d'une pizza");
		}
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) throws PizzaException {
		Pizza result = null;
		ResultSet data = null;
		Statement requete = null;
		Connection connection = null;
		
		try {
			//Récupération de la pizza dans la base de donnée
			connection = connect();
			requete = connection.createStatement();
			data = requete.executeQuery("SELECT * FROM pizzas "
					+ "WHERE code = \""+codePizza+"\"");
			
			if(data.next() == false) result = null;
			else result = new Pizza(data);
			
			data.close();
			requete.close();
			connection.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return result;
	}

	@Override
	public boolean pizzaExists(String codePizza) throws PizzaException {
		ResultSet data = null;
		Statement requete = null;
		Connection connection = null;
		boolean answer = false;
		
		try {
			//Récupération de la pizza dans la base de donnée
			connection = connect();
			requete = connection.createStatement();
			data = requete.executeQuery("SELECT * FROM pizzas "
					+ "WHERE code = \""+codePizza+"\"");
			
			if(data.next() == false) answer = false;
			else answer = true;
			
			data.close();
			requete.close();
			connection.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return answer;
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

	@Override
	public void reset() throws PizzaException {
		Statement requete = null;
		Connection connection = null;
		
		try {
			//On vide la table
			connection = connect();
			requete = connection.createStatement();
			requete.executeUpdate("TRUNCATE TABLE pizzas");
			
			requete.executeUpdate("insert into pizzas (code, libelle, prix, categorie)\r\n" + 
					"values 	(\"PEP\",\"Pépéroni\",12.50, 0),\r\n" + 
					"		(\"MAR\",\"Margherita\",14.00, 1),\r\n" + 
					"        (\"REIN\",\"La Reine\",11.50, 0),\r\n" + 
					"        (\"FRO\",\"La 4 Fromages\",12.00, 1),\r\n" + 
					"        (\"CAN\",\"La cannibale\",12.50, 0),\r\n" + 
					"        (\"SAV\",\"La savoyarde\",13.00, 1),\r\n" + 
					"        (\"ORI\",\"L'orientale\",13.50, 1),\r\n" + 
					"        (\"IND\",\"L'indienne\",14.00, 1);");
			
			requete.close();
			connection.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
