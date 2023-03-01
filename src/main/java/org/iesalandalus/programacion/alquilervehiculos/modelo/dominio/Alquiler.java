package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler {
	static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final int PRECIO_DIA = 20;
	private LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;
	private Cliente cliente;
	private Turismo turismo;
	
	public Alquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler) {
		setCliente(cliente);
		setTurismo(turismo);
		setFechaAlquiler(fechaAlquiler);
		
	}
	
	public Alquiler(Alquiler alquiler) {
		if(alquiler == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		}
		setCliente(new Cliente(alquiler.getCliente()));
		setTurismo(new Turismo(alquiler.getTurismo()));
		setFechaAlquiler(alquiler.getFechaAlquiler());
		this.fechaDevolucion = alquiler.getFechaDevolucion();
		
	}
	
	public void devolver(LocalDate fechaDevolucion)throws OperationNotSupportedException{
		if(this.fechaDevolucion != null) {
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		}
		setFechaDevolucion(fechaDevolucion);
	}
	
	public int getPrecio() {
		int factorCilindrada = (turismo.getCilindrada())/10;
		int numDias = 0;
		//si la fechaDevolucion no es nula calculo el numero de días, sino su valor es 0 y el precio final se queda en 0
		if(fechaDevolucion != null) {
			numDias = (int) ChronoUnit.DAYS.between(fechaAlquiler,fechaDevolucion);
		}
		return (PRECIO_DIA + factorCilindrada)* numDias;		
	}
	
	public Turismo getTurismo() {
		return turismo;
	}

	private void setTurismo(Turismo turismo) {
		if(turismo == null) {
			throw new NullPointerException("ERROR: El turismo no puede ser nulo.");
		}
		this.turismo = turismo;
	}
	
	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}
	private void setFechaAlquiler(LocalDate fechaAlquiler) {
		if(fechaAlquiler == null) {
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		}
		if(fechaAlquiler.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		}
		this.fechaAlquiler = fechaAlquiler;
	}
	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}
	private void setFechaDevolucion(LocalDate fechaDevolucion) {
		if(fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}
		if(fechaDevolucion.isBefore(fechaAlquiler)) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		}
		if(fechaDevolucion.isEqual(fechaAlquiler)) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		}
		if(fechaDevolucion.isAfter(LocalDate.now())) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}
		this.fechaDevolucion = fechaDevolucion;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	private void setCliente(Cliente cliente) {
		if(cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, fechaDevolucion, turismo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(fechaDevolucion, other.fechaDevolucion) && Objects.equals(turismo, other.turismo);
	}

	@Override
	public String toString() {
		return String.format("%s <---> %s, %s - %s (%d€)", cliente, turismo, fechaAlquiler.format(FORMATO_FECHA),fechaDevolucion == null ? "Aún no devuelto" : fechaDevolucion.format(FORMATO_FECHA), getPrecio());
	}


	

}
