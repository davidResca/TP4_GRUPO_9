package dominio;



public class Seguro {

	private int idSeguro;
	private String descripcion;
	private TipoSeguro TipoSeguro;
	private double costoContratacion;
	private double costoAsegurado;
	
	public Seguro() {
		
	}

	public Seguro(int idSeguro, String descripcion, dominio.TipoSeguro tipoSeguro, double costoContratacion,
			double costoAsegurado) {
		this.idSeguro = idSeguro;
		this.descripcion = descripcion;
		TipoSeguro = tipoSeguro;
		this.costoContratacion = costoContratacion;
		this.costoAsegurado = costoAsegurado;
	}

	public int getIdSeguro() {
		return idSeguro;
	}

	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public TipoSeguro getTipoSeguro() {
		return TipoSeguro;
	}

	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		TipoSeguro = tipoSeguro;
	}

	public double getCostoContratacion() {
		return costoContratacion;
	}

	public void setCostoContratacion(double costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public double getCostoAsegurado() {
		return costoAsegurado;
	}

	public void setCostoAsegurado(double costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}
	
	
}