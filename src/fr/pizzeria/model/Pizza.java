package fr.pizzeria.model;

import java.text.DecimalFormat;

public class Pizza {
	@SuppressWarnings("unused")
	private int id;
	private String code;
	private String libelle;
	private double prix;
	private CategoriePizza categorie;
	
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
	
	/**
	 * Redéfinition d'equals
	 * @param p pizza à comparer à this
	 * @return true si les pizzas sont égales, false sinon
	 */
	public boolean equals(Pizza p) {
		return 	this.getCode().equals(p.getCode()) &&
				this.getLibelle().equals(p.getLibelle()) &&
				this.getPrix() == (p.getPrix());
	}
	
	/**
	 * Redéfinition de toString()
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00"); 
		return code+" -> "+libelle+" ("+df.format(prix)+"€) "+categorie.toString();
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
