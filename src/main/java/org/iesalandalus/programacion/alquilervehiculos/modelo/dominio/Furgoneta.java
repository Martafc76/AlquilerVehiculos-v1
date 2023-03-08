package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

public class Furgoneta extends Vehiculo {

	private static final int FACTOR_PMA = 100;
	private static final int FACTOR_PLAZAS = 1;
	int plazas;
	int pma;

	public Furgoneta(String marca, String modelo, int pma, int plazas, String matricula) {
		super(marca, modelo, matricula);
		setPlazas(plazas);
		setPma(pma);
	}

	public Furgoneta(Furgoneta furgoneta) {
		super(furgoneta);
		plazas = furgoneta.getPlazas();
		pma = furgoneta.getPma();

	}


	public int getPma() {
		return pma;
	}

	private void setPma(int pma) {
		if( pma < 0 | pma > 100) {
			throw new IllegalArgumentException("ERROR: El pma no es válido. ");
		}
		this.pma = pma;
	}

	public int getPlazas() {
		return plazas;
	}

	private void setPlazas(int plazas) {
		if (plazas < 1 | plazas > 9) {
			throw new IllegalArgumentException("ERROR: El número de plazas no es válido.");
		}
		this.plazas = plazas;

	}

	@Override
	public int getFactorPrecio() {
		return ((pma/FACTOR_PMA) + (plazas*FACTOR_PLAZAS));
	}
	
	@Override
	public String toString() {
		return String.format("%s %s %s %s - %s", getMarca(), getModelo(), pma, plazas, getMatricula());
	}

}
