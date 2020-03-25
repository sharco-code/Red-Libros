package model;

public class Options {
	
	private String ip;
	private String port;
	private String user;
	private String password;
	private String columnas;
	private String filas;
	
	
	public Options() {
		super();
	}



	public Options(String ip, String port, String user, String password) {
		super();
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.password = password;
	}
	
	



	public Options(String ip, String port, String user, String password, String columnas, String filas) {
		super();
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.password = password;
		this.columnas = columnas;
		this.filas = filas;
	}



	public String getIp() {
		return ip;
	}



	public void setIp(String ip) {
		this.ip = ip;
	}



	public String getPort() {
		return port;
	}



	public void setPort(String port) {
		this.port = port;
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getColumnas() {
		return columnas;
	}



	public void setColumnas(String columnas) {
		this.columnas = columnas;
	}



	public String getFilas() {
		return filas;
	}



	public void setFilas(String filas) {
		this.filas = filas;
	}
	
	

}
