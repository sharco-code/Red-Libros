module proyectoFinal.redLibros {
    requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires fontawesomefx;
	requires json.simple;
	requires java.xml;
	requires org.hibernate.orm.core;
	requires java.sql;
	requires commons.dbcp2;
	
	exports utiles.xml;
	opens utiles.xml;
	
	
	exports controller;
	opens controller;
	
    exports app;
    opens app;
    
    opens view;
      
}