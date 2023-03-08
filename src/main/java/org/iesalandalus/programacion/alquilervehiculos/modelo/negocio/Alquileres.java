package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class Alquileres {
	private List<Alquiler> coleccionAlquileres;
	
	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}
	
	public List<Alquiler> get() {
		return new ArrayList<>(coleccionAlquileres);
	}
	
	public List<Alquiler> get(Cliente cliente){
		List<Alquiler>alquileresCliente = new ArrayList<>();
		for(Alquiler alquiler : coleccionAlquileres) {
			if(alquiler.getCliente().equals(cliente)){
				alquileresCliente.add(alquiler);
			}
		}
		return alquileresCliente;
	}
	
	public List<Alquiler> get(Vehiculo turismo){
		List<Alquiler>alquileresTurismo = new ArrayList<>();
		for(Alquiler alquiler : coleccionAlquileres) {
			if(alquiler.getTurismo().equals(turismo)){
				alquileresTurismo.add(alquiler);
			}
		}
		return alquileresTurismo;
	}
	
	public int getCantidad() {
		return coleccionAlquileres.size();
	}
	
	private void comprobarAlquiler(Cliente cliente, Vehiculo turismo, LocalDate fechaAlquiler) throws OperationNotSupportedException {
		    // Compruebo que existe algún alquiler sin devolver para el cliente o el turismo
		    for (Alquiler alquiler : coleccionAlquileres) {
		      if(alquiler.getFechaDevolucion() == null) {
		    	  if(alquiler.getCliente().equals(cliente)) {
		    		  throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
		    	  }
		    	  if(alquiler.getTurismo().equals(turismo)) {
		    		  throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
		    	  }
		      } 
		      else if(!alquiler.getFechaDevolucion().isBefore(fechaAlquiler)) {
		    	  if(alquiler.getCliente().equals(cliente)) {
		    		  throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
		    	  }
		    	  if(alquiler.getTurismo().equals(turismo)) {
		    		  throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
		    	  }
		      }
		  }
	}
	
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		} 
		comprobarAlquiler(alquiler.getCliente(), alquiler.getTurismo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);
		
	}

	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException{
			if (alquiler == null) {
				throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
			}
			if (buscar(alquiler) == null) {
				throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
			}
			
			alquiler.devolver(fechaDevolucion);
			
	}


	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		int indice = coleccionAlquileres.indexOf(alquiler);
		return indice == -1 ? null : coleccionAlquileres.get(indice);
	}

	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (coleccionAlquileres.contains(alquiler)) {
			coleccionAlquileres.remove(alquiler);
		} else {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}

	}
	


}
