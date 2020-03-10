module proyectoFinal.redLibros {
    requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires fontawesomefx;
	
    exports proyectoFinal.redLibros;
    opens proyectoFinal.redLibros;
    exports app;
    opens app;
    
    
    opens view;
    
    
}