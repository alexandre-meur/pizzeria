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
	
	public int toInt() {
		switch(this) {
		case VIANDE:
			return 0;
		case POISSON:
			return 1;
		case SANS_VIANDE:
			return 2;
		default:
			return -1;
		}
	}
}
