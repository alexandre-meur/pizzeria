package fr.pizzeria.model;

import java.text.DecimalFormat;

public class Pizza {
	private int id;
	private String code;
	private String libelle;
	private double prix;
	
	//Compteur
	private static int compteur = 0;
	
	/**
	 * Constructeur sans id. l'id est géré avec un compteur
	 * @param code
	 * @param libelle
	 * @param prix
	 */
	public Pizza(String code, String libelle, double prix) {
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
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
	public Pizza(int id, String code, String libelle, double prix) {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
	}
	
	/**
	 * Surcharge de toString()
	 */
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00"); 
		return code+" -> "+libelle+" ("+df.format(prix)+"€)";
	}
	
	public String getCode() {
		return code;
	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public double getPrix() {
		return prix;
	}	
}
