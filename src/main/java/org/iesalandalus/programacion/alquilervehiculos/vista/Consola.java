package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String PATRON_FECHA = "dd/MM/yyyy";

	private Consola() {

	}

	public static void mostrarCabecera(String mensaje) {
		System.out.println(mensaje);
		StringBuilder delineado = new StringBuilder();
		for (int i = 0; i < mensaje.length(); i++) {
			delineado.append("-");
		}

		System.out.println(delineado);
	}

	public static void mostrarMenu() {
		System.out.println("Eliga una de las siguientes opciones: ");
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}

	private static String leerCadena(String mensaje) {
		System.out.printf("Introduce %s:", mensaje);
		return Entrada.cadena();
	}

	private static int leerEntero(String mensaje) {
		System.out.printf("Introduce %s: ", mensaje);
		return Entrada.entero();
	}

	private static LocalDate leerFecha(String mensaje) {
		LocalDate fecha = null;
		System.out.printf("Introduce %s (%s): ", mensaje, PATRON_FECHA);
		try {
			LocalDate.parse(Entrada.cadena(), FORMATO_FECHA);
		} catch (Exception e) {
			throw new IllegalArgumentException("ERROR: La fecha introducida no es válida.");
		}

		return fecha;
	}

	public static Opcion elegirOpcion() {

		Opcion opcion = null;
		do {

			int indice = leerEntero("la opción deseada ");
			try {
				opcion = Opcion.get(indice);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (opcion == null);

		return opcion;
	}

	public static Cliente leerCliente() {
		Cliente cliente = null;

		cliente = new Cliente(leerNombre(), leerCadena("el dni"), leerTelefono());

		return cliente;

	}

	public static Cliente leerClienteDni() {
		Cliente cliente = null;
		cliente = Cliente.getClienteConDni(leerCadena("el dni del cliente"));

		return cliente;

	}

	public static String leerNombre() {
		return leerCadena("el nombre");
	}

	public static String leerTelefono() {
		return leerCadena("el número de telefono");

	}

	public static Turismo leerTurismo() {
		Turismo turismo = null;
		turismo = new Turismo(leerCadena("la marca"), leerCadena("el modelo"), leerEntero("la cilindrada"),
				leerCadena("la matricula"));
		return turismo;

	}

	public static Turismo leerTurismoMatricula() {
		Turismo turismo = null;
		turismo = Turismo.getTurismoConMatricula(leerCadena("la matricula del turismo"));

		return turismo;

	}

//cliente,turismo,fechaAlquiler
	public static Alquiler leerAlquiler() {
		Alquiler alquiler = null;

		alquiler = new Alquiler(leerClienteDni(), leerTurismoMatricula(), leerFecha("la fecha de alquiler"));

		return alquiler;

	}

	public static LocalDate leerFechaDevolucion() {
		return leerFecha("la fecha de devolución");

	}

}
