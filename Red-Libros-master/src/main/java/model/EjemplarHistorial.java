package model;

import pojo.Ejemplare;

public class EjemplarHistorial {
	private String codigoEjemplar;
	private String nombreLibro;
	private String estado;
	private String prestado;
	private Ejemplare ejemplar;
	
	
	public EjemplarHistorial() {
		super();
	}


	public String getCodigoEjemplar() {
		return codigoEjemplar;
	}


	public void setCodigoEjemplar(String codigoEjemplar) {
		this.codigoEjemplar = codigoEjemplar;
	}


	public String getNombreLibro() {
		return nombreLibro;
	}


	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getPrestado() {
		return prestado;
	}


	public void setPrestado(String prestado) {
		this.prestado = prestado;
	}


	public Ejemplare getEjemplar() {
		return ejemplar;
	}


	public void setEjemplar(Ejemplare ejemplar) {
		this.ejemplar = ejemplar;
	}
	
	
	

}
