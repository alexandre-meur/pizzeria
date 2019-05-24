Create database pizzaDb;

create table pizzas
(	
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    code varchar(4),
    libelle varchar(60),
    prix double,
    categorie int
);

insert into pizzas (code, libelle, prix, categorie)
values 	("PEP","Pépéroni",12.50, 0),
		("MAR","Margherita",14.00, 1),
        ("REIN","La Reine",11.50, 0),
        ("FRO","La 4 Fromages",12.00, 1),
        ("CAN","La cannibale",12.50, 0),
        ("SAV","La savoyarde",13.00, 1),
        ("ORI","L'orientale",13.50, 1),
        ("IND","L'indienne",14.00, 1);
        
SELECT * FROM pizzas;
        