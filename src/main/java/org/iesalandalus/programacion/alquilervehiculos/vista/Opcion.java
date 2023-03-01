package org.iesalandalus.programacion.alquilervehiculos.vista;

public enum Opcion {
	
	SALIR("Salir"), 
    INSERTAR_CLIENTE("Insertar cliente"), 
    INSERTAR_TURISMO("Insertar turismo"), 
    INSERTAR_ALQUILER("Insertar alquiler"),
    BUSCAR_CLIENTE("Buscar cliente"),
    BUSCAR_TURISMO("Buscar turismo"),
    BUSCAR_ALQUILER("Buscar alquiler"),
    MODIFICAR_CLIENTE("Modificar cliente"),
    DEVOLVER_ALQUILER("Devolver alquiler"),
    BORRAR_CLIENTE("Borrar cliente"),
    BORRAR_TURISMO("Borrar turismo"),
    BORRAR_ALQUILER("Borrar alquiler"),
    LISTAR_CLIENTES("Listar clientes"),
    LISTAR_TURISMOS("Listar turismos"),
    LISTAR_ALQUILERES("Listar alquileres"),
    LISTAR_ALQUILERES_CLIENTE("Listar alquileres de cliente"),
    LISTAR_ALQUILERES_TURISMO("Listar alquileres de turismo");


	private String cadenaAMostrar;

	private Opcion(String nombre) {
		cadenaAMostrar = nombre;
	}
	
	private static boolean esOrdinalValido(int ordinal) {
		return ordinal >= 0 && ordinal < Opcion.values().length;
	}
	
	public static Opcion get(int ordinal) {
		if(!esOrdinalValido(ordinal)) {
			throw new IllegalArgumentException("ERROR: El ordinal pasado no es válido");
		}
		
		return Opcion.values()[ordinal];
	}
	
	@Override
	public String toString() {
		return String.format("%d) %s",ordinal(), cadenaAMostrar );
	}

}
