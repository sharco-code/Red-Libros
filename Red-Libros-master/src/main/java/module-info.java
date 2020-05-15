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
	requires java.persistence;
	requires java.logging;
	requires java.desktop;
	requires itextpdf;
	requires barcode4j;
	requires java.xml.bind;
	requires jakarta.activation;
	
	exports utiles.xml;
	opens utiles.xml;
	
	exports utiles.dao;
	opens utiles.dao;
	
	exports utiles.excepciones;
	opens utiles.excepciones;
	
	
	exports utiles.hibernate;
	opens utiles.hibernate;
	
	exports pojo;
	opens pojo;
	
	exports controller;
	opens controller;
	
    exports app;
    opens app;
    
    exports model;
    opens model;
    
    exports xmlModels;
    opens xmlModels;
    
    opens view;
      
}