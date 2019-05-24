package fr.pizzeria.model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.persistence.*;

import fr.pizzeria.utils.*;

@Entity
@Table(name="pizzas")
public class Pizza {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="code", nullable=false)
	@ToString(uppercase = true)
	public String code;
	
	@Column(name="libelle", nullable=false)
	@ToString(uppercase = false)
	public String libelle;
	
	@Column(name="prix", nullable=false)
	@ToString
	@Rule(min=0)
	public double prix;
	
	@Enumerated
	@Column(name="categorie", nullable=false)
	public CategoriePizza categorie;
	
	public Pizza() {
		super();
	}
	
	/**
	 * Constructeur
	 * @param code
	 * @param libelle
	 * @param prix
	 */
	public Pizza(String code, String libelle, double prix, CategoriePizza categorie) {
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
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
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Pizza)) return false;
		Pizza p = (Pizza) o;
		return 	this.getCode().equals(p.getCode()) &&
				this.getLibelle().equals(p.getLibelle()) &&
				this.getPrix() == p.getPrix() &&
				this.getCategorie() == p.getCategorie();
	}
	
	public boolean compareCode(String code) {
		if(code == null) return false;
		return this.getCode().equals(code.toUpperCase());
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
