package org.iesalandalus.programacion.alquilervehiculos.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Alquileres;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.Clientes;

public class Vista {
	Controlador controlador;

	public void setControlador(Controlador controlador) {
		if (controlador == null) {
			throw new NullPointerException("ERROR: El controlador es nulo");
		}
		this.controlador = controlador;

	}

	public void comenzar() {
		Opcion opcion = null;
		do {
			Consola.mostrarCabecera("Menú principal");
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			ejecutar(opcion);
		} while (opcion != Opcion.SALIR);

	}

	public void terminar() {
		System.out.println("Que tengas un buen día!");
	}

	private void ejecutar(Opcion opcion) {
		switch (opcion) {

		case INSERTAR_CLIENTE: {
			insertarCliente();
			break;
		}

		case INSERTAR_TURISMO: {
			insertarTurismo();
			break;
		}

		case INSERTAR_ALQUILER: {
			insertarAlquiler();
			break;
		}

		case BUSCAR_CLIENTE: {
			buscarCliente();
			break;
		}

		case BUSCAR_TURISMO: {
			buscarTurismo();
			break;
		}

		case BUSCAR_ALQUILER: {
			buscarAlquiler();
			break;
		}

		case MODIFICAR_CLIENTE: {
			modificarCliente();
			break;
		}
		
		case DEVOLVER_ALQUILER: {
			devolverAlquiler();
			break;
		}

		case BORRAR_CLIENTE: {
			borrarCliente();
			break;
		}

		case BORRAR_TURISMO: {
			borrarTurismo();
			break;
		}

		case BORRAR_ALQUILER: {
			borrarAlquiler();
			break;
		}

		case LISTAR_CLIENTES: {
			listarClientes();
			break;
		}

		case LISTAR_TURISMOS: {
			listarTurismos();
			break;
		}

		case LISTAR_ALQUILERES: {
			listarAlquileres();
			break;
		}

		case LISTAR_ALQUILERES_CLIENTE: {
			listarAlquileresCliente();
			break;
		}

		case LISTAR_ALQUILERES_TURISMO: {
			listarAlquileresTurismo();
			break;
		}

		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}
	}

	

	// Crea los métodos asociados a cada una de las opciones. Estos métodos deberán
	// mostrar una cabecera informando en que opción nos encontramos,
	// pedirnos los datos adecuados y realizar la operación adecuada llamando al
	// método correspondiente de nuestro controlador.
	// También deben controlar todas las posibles excepciones.
	
	
	private void devolverAlquiler() {
		Consola.mostrarCabecera("Devolver alquiler");
		try {
			controlador.devolver(Consola.leerAlquiler(), Consola.leerFechaDevolucion());
			System.out.println("El alquiler ha sido devuelto correctamente");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	private void insertarCliente() {
		Consola.mostrarCabecera("Insertar cliente");
		try {
			controlador.insertar(Consola.leerCliente());
			System.out.println("Cliente insertado correctamente.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void insertarTurismo() {
		Consola.mostrarCabecera("Insertar turismo");
		try {
			controlador.insertar(Consola.leerTurismo());
			System.out.println("Turismo insertado correctamente");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void insertarAlquiler() {
		Consola.mostrarCabecera("Insertar alquiler");
		try {
			controlador.insertar(Consola.leerAlquiler());
			System.out.println("Ha insertado un nuevo alquiler");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void buscarCliente() {
		Consola.mostrarCabecera("Buscar cliente");
		Cliente cliente = null;
		try {
			cliente = controlador.buscar(Consola.leerClienteDni());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (cliente == null) {
			System.out.println("El cliente no existe");
		} else {
			System.out.println(cliente);
		}

	}

	private void buscarTurismo() {
		Consola.mostrarCabecera("Buscar turismo");
		Turismo turismo = null;
		try {
			turismo = controlador.buscar(Consola.leerTurismoMatricula());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (turismo == null) {
			System.out.println("El turismo no existe");
		} else {
			System.out.println(turismo);
		}

	}

	private void buscarAlquiler() {
		Consola.mostrarCabecera("Buscar alquiler");
		Alquiler alquiler = null;
		try {
			alquiler = controlador.buscar(Consola.leerAlquiler());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (alquiler == null) {
			System.out.println("El alquiler no existe");
		} else {
			System.out.println(alquiler);
		}

	}

	private void modificarCliente() {
		Consola.mostrarCabecera("Modificar cliente");
		try {
			controlador.modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());
			System.out.println("El cliente ha sido modificado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void borrarCliente() {
		Consola.mostrarCabecera("Borrar cliente");
		try {
			controlador.borrar(Consola.leerClienteDni());
			System.out.println("El cliente ha sido borrado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void borrarTurismo() {
		Consola.mostrarCabecera("Borrar turismo");
		try {
			controlador.borrar(Consola.leerTurismoMatricula());
			System.out.println("El turismo ha sido borrado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void borrarAlquiler() {
		Consola.mostrarCabecera("Borrar Alquiler");
		try {
			controlador.borrar(Consola.leerAlquiler());
			System.out.println("El alquiler ha sido borrado");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private void listarClientes() {
		Consola.mostrarCabecera("Lista de clientes");
		for (Cliente cliente : controlador.getClientes()) {
			System.out.println(cliente);
		}

	}

	private void listarTurismos() {
		Consola.mostrarCabecera("Lista de turismos");
		for (Turismo turismo : controlador.getTurismos()) {
			System.out.println(turismo);
		}
	}

	private void listarAlquileres() {
		Consola.mostrarCabecera("Lista de alquileres");
		for (Alquiler alquiler : controlador.getAlquileres()) {
			System.out.println(alquiler);
		}

	}

	private void listarAlquileresCliente() {
		Cliente cliente = Consola.leerClienteDni();
		Consola.mostrarCabecera("Lista de alquileres del cliente");
		for (Alquiler alquiler : controlador.getAlquileres(cliente)) {
			System.out.println(alquiler);
		}
	}

	private void listarAlquileresTurismo() {
		Turismo turismo = Consola.leerTurismoMatricula();
		Consola.mostrarCabecera("Lista de alquileres del turismo");
		try {
			for (Alquiler alquileres : controlador.getAlquileres(turismo)) {
				System.out.println(alquileres);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
