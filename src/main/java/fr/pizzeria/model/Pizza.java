package fr.pizzeria.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import fr.pizzeria.utils.*;

public class Pizza {
	
	@SuppressWarnings("unused")
	private int id;
	@ToString(uppercase = true)
	public String code;
	@ToString(uppercase = false)
	public String libelle;
	@ToString
	@Rule(min=0)
	public double prix;
	public CategoriePizza categorie;
	
	//Compteur
	private static int compteur = 0;
	
	/**
	 * Constructeur sans id. l'id est géré avec un compteur
	 * @param code
	 * @param libelle
	 * @param prix
	 */
	public Pizza(String code, String libelle, double prix, CategoriePizza categorie) {
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
		this.id = compteur;
		compteur++;
	}
	
	/**
	 * Constructeur avec id fourni.
	 * @param id
	 * @param code
	 * @param libelle
	 * @param prix
	 */
	public Pizza(int id, String code, String libelle, double prix, CategoriePizza categorie) {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.categorie = categorie;
		this.prix = prix;
	}
	
	public Pizza(ResultSet data) {
		
		try {
			id = data.getInt("id");
			code = data.getString("code");
			libelle = data.getString("libelle");
			prix = data.getDouble("prix");
		
			switch(data.getInt("categorie")) {
			case 0:
				categorie = CategoriePizza.VIANDE;
				break;
			case 1:
				categorie = CategoriePizza.SANS_VIANDE;
				break;
			case 2:
				categorie = CategoriePizza.POISSON;
				break;
			default:
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Redéfinition d'equals
	 * @param p pizza à comparer à this
	 * @return true si les pizzas sont égales, false sinon
	 */
	public boolean equals(Pizza p) {
		return 	this.getCode().equals(p.getCode());/* &&
				this.getLibelle().equals(p.getLibelle()) &&
				this.getPrix() == (p.getPrix());*/
	}
	
	public boolean compareCode(String code) {
		return this.getCode().equals(code);
	}
	
	/**
	 * Redéfinition de toString()
	 */
	public String toString() {
		//Passage en majuscule des champs annotés
		StringUtils.util(this);
		String result = "";
		Object fieldValue = new Object();
		DecimalFormat df = new DecimalFormat("0.00");
		
		for(Field f : this.getClass().getFields()) {
			for(Annotation a : f.getAnnotations()) {
				//On affiche que les attributs annotés par @ToString
				if(a instanceof ToString) {
					try {
						fieldValue = f.get(this);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						System.out.println("Problème dans Pizza.toString()");
					}
					//Mise en forme du prix si la valeur de l'attribut est de type double
					if(fieldValue instanceof Double) {
						result = result + '(' + df.format((Double) fieldValue).toString()+"€) " ;
					}else {
						result = result + fieldValue.toString()+" ";
					}
				}
			}
		}
		return result;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getLibelle() {
		return libelle;
	}
	
	public double getPrix() {
		return prix;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}
}
