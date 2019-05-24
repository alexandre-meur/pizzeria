package fr.pizzeria.model;

public enum CategoriePizza {
	VIANDE ("Viande"), 
	POISSON ("Poisson"), 
	SANS_VIANDE ("Sans viande");
	
	private String prop;
	
	private CategoriePizza(String prop) {
		this.prop = prop;
	}

	public String getProp() {
		return prop;
	}
	
	public String toString() {
		return getProp();
	}
}
