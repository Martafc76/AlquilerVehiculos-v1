package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Clientes;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Turismos;


public class Modelo {
	
	Clientes clientes;
	Turismos turismos;
	Alquileres alquileres;
	
	public Modelo() {
		
		}

	public void comenzar() {
	    clientes = new Clientes();
	    turismos = new Turismos();
	    alquileres = new Alquileres();
	}

	public void terminar() {
	    System.out.println("El modelo ha terminado");
	}

	public void insertar(Cliente cliente) throws OperationNotSupportedException {
	    clientes.insertar(new Cliente(cliente));
	}

	public void insertar(Turismo turismo) throws OperationNotSupportedException {
	   turismos.insertar(new Turismo(turismo));
	}

	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if(alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		
		Cliente cliente = clientes.buscar(alquiler.getCliente());
		
		if(cliente == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}
		
		Turismo turismo = turismos.buscar(alquiler.getTurismo());
		
		if(turismo == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		}
		
		alquileres.insertar(new Alquiler(cliente,turismo,alquiler.getFechaAlquiler()));
	  
	}

	public Cliente buscar(Cliente cliente) {
	  return new Cliente(clientes.buscar(cliente));
	}

	public Turismo buscar(Turismo turismo) {
	    return new Turismo(turismos.buscar(turismo));
	}

	public Alquiler buscar(Alquiler alquiler) {
	    return new Alquiler(alquileres.buscar(alquiler));
	    
	}

	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
	    clientes.modificar(cliente, nombre, telefono);
	}
	
	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if(alquiler == null) {
			throw new NullPointerException("El alquiler no puede ser nulo");
		}
		Alquiler alquilerEncontrado = alquileres.buscar(alquiler);
		if(alquilerEncontrado == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
		}
		
		alquilerEncontrado.devolver(fechaDevolucion);
	}
	
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for(Alquiler alquiler : alquileres.get(cliente)) {
			alquileres.borrar(alquiler);
		}
		clientes.borrar(cliente);
	}
	
	public void borrar(Turismo turismo) throws OperationNotSupportedException {
		for(Alquiler alquiler : alquileres.get(turismo)) {
			alquileres.borrar(alquiler);
		}
		turismos.borrar(turismo);
	}
	
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		alquileres.borrar(alquiler);
	}
	public List<Cliente> getClientes(){
		List<Cliente> clienteDevuelve = new ArrayList<>();
		for(Cliente cliente : clientes.get()) {
			clienteDevuelve.add(new Cliente(cliente));
			
			}
		return clienteDevuelve;
		}
	
	public List<Turismo> getTurismos(){
		List<Turismo> turismoDevuelve = new ArrayList<>();
		for(Turismo turismo : turismos.get()) {
			turismoDevuelve.add(new Turismo(turismo));
			
			}
		return turismoDevuelve;
		}
	
	public List<Alquiler> getAlquileres(){
		List<Alquiler> alquileresADevolver = new ArrayList<>();
		for(Alquiler alquiler : alquileres.get()) {
			alquileresADevolver.add(new Alquiler(alquiler));
			
			}
		return alquileresADevolver;
		}
		
	public List<Alquiler> getAlquileres(Cliente cliente){
		List<Alquiler> alquileresADevolver = new ArrayList<>();
		for(Alquiler alquiler : alquileres.get(cliente)) {
			alquileresADevolver.add(new Alquiler(alquiler));
			}
		return alquileresADevolver;
	}
	
	public List<Alquiler> getAlquileres(Turismo turismo){
		List<Alquiler> alquileresADevolver = new ArrayList<>();
		for(Alquiler alquiler : alquileres.get(turismo)) {
			alquileresADevolver.add(new Alquiler(alquiler));
			
			}
		return alquileresADevolver;
	}
	



}
