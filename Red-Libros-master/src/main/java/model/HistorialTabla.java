package model;

public class HistorialTabla {
	private String curso;
	private String nia;
	private String nombreCompleto;
	private String fechaDevolucion;
	private String estado_inicial;
	private String estado_final;
	
	public String getEstado_inicial() {
		return estado_inicial;
	}
	public void setEstado_inicial(String estado_inicial) {
		this.estado_inicial = estado_inicial;
	}
	public String getEstado_final() {
		return estado_final;
	}
	public void setEstado_final(String estado_final) {
		this.estado_final = estado_final;
	}
	public HistorialTabla() {
		super();
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getNia() {
		return nia;
	}
	public void setNia(String nia) {
		this.nia = nia;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getFechaDevolucion() {
		return fechaDevolucion;
	}
	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}
	
	
}
