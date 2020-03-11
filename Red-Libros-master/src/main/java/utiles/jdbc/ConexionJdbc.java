package utiles.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import utiles.excepciones.ConnectionException;

public class ConexionJdbc {
	private static Connection con;
	
	private String driver;
	private String url;
	private String usr;
	private String pwd;
	
	private String ficheroConfiguracion;
	
	
	
	public ConexionJdbc(String driver, String url, String usr, String pwd) {
		this.driver = driver;
		this.url = url;
		this.usr = usr;
		this.pwd = pwd;
	}

	
	
	public ConexionJdbc(String ficheroConfiguracion) {
		this.ficheroConfiguracion = ficheroConfiguracion;
	}



	private void conectar(String driver,String url,String usr,String pwd) throws ConnectionException{
		try {
			Class.forName(driver).newInstance();
			con =  DriverManager.getConnection(url, usr, pwd);
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger(ConexionJdbc.class.getName()) .log(Level.SEVERE, "Error al conectar usando DriverManager", e); 
		}
	}
	
	private void conectar(String ficheroConfiguracion) throws ConnectionException{
		try {
			Properties propiedades = new Properties();
			propiedades.load(new FileInputStream(ficheroConfiguracion));
			
			DataSource ds = BasicDataSourceFactory.createDataSource(propiedades);
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger(ConexionJdbc.class.getName()) .log(Level.SEVERE, "Error al conectar por fichero", e); 
		}
	}
	
	public void conectar() {
		if(ficheroConfiguracion != null) {
			conectar(ficheroConfiguracion);
		} else {
			conectar(driver,url,usr,pwd);
		}
	}
	
	public static Connection getConnection() {
		return con;
		
	}
	
	public void desconectar() {
		try {
			if(con != null && !con.isClosed()) {
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger(ConexionJdbc.class.getName()) .log(Level.SEVERE, "error al desconectar", e); 
		}
		
	}
	
	public static void cerrar(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger(ConexionJdbc.class.getName()) .log(Level.SEVERE, "error al cerrar ResultSet", e); 
			
		}
		
	}
	
	public static void cerrar(Statement stm) {
		try {
			if(stm != null && !stm.isClosed()) {
				stm.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger(ConexionJdbc.class.getName()) .log(Level.SEVERE, "error al cerrar Statement", e); 
			
		}
	}
	
}
