package model;

public class EjemplarTabla {
	private String id;

	private String codigo;

	private String estado;

	private String prestado;
	
	

	public EjemplarTabla() {
		super();
	}
	
	

	public EjemplarTabla(String id, String codigo, String estado, String prestado) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.estado = estado;
		this.prestado = prestado;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	
	

}
