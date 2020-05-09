package model;

import java.util.List;

import pojo.Contenido;
import pojo.Ejemplare;

public class StockTabla {

	private String id;

	private String codigo;

	private String isbn;

	private String nombre;

	private byte obsoleto;

	private double precio;

	private int unidades;

	private String curso;

	public StockTabla() {
	}

	public String getId() {
		return this.id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte getObsoleto() {
		return this.obsoleto;
	}

	public void setObsoleto(byte obsoleto) {
		this.obsoleto = obsoleto;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getUnidades() {
		return this.unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

}

