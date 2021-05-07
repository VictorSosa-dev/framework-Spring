package mx.uam.is.practicadiseno.datos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MapeadorDatosImpl implements MapeadorDatos {

	FileWriter fichero;
	PrintWriter imprimir;
	FileReader lector;
	BufferedReader lectorLinea;
	// La estructura que contiene los datos
	private List <String> datos = new ArrayList <String> ();

	// private String[] datos = new String[10];
	/**
	 * Constructor que inicializa con algunos datos
	 *
	 */
	public MapeadorDatosImpl() {
		datos.add("Entrada 1");
		datos.add("Entrada 2");
		datos.add("Entrada 3");
	}

	/**
	 * Recupera los datos
	 *
	 * @return la lista de datos
	 */
	public String[] dameDatos() {
		
		String arregloDatos[] = new String[datos.size()];
		datos.toArray(arregloDatos);
		return arregloDatos;
	}
	
	
	/**
	 * Agrega un dato mientras no este vacio y no este ya en el fichero
	 *
	 * @param dato el dato a agregar
	 * @return true si se agrego exitosamente false sino
	 */
	public boolean agrega(String dato) {
		if(!dato.equals("") && !datos.contains(dato)) {
			datos.add(dato);
			return true;
		}
		return false;
	}
	
	/**
	 * Agrega un dato mientras no este vacio y no este ya en el fichero
	 *
	 * @param dato el dato a agregar
	 * @return true si se agrego exitosamente false sino
	 */
	public boolean fichero(String dato) {
		String auxDato; //variable para guardar el dato recibido

		try {
			fichero = new FileWriter("Datos.txt", true);
			imprimir = new PrintWriter(fichero);

			lector = new FileReader("Datos.txt");
			lectorLinea = new BufferedReader(lector);

			boolean existe = false; // Bandera que controla si existe la linea
			String nuevoDato = dato;
			while ((auxDato = lectorLinea.readLine()) != null) {
				if (auxDato.equals(nuevoDato)) {// Verifica que la linea exista
					existe = true;
					
					System.out.println("Este '" + dato + "' dato ya exite.");
					break; // Si se encuentra al menos una coinciencia detenemos la iteración y cambiamos
							// el estado de exist
				}
			}
			if (!existe && !nuevoDato.equals("")) { // Si no existe agregamos la linea
				
				imprimir.println(nuevoDato);
				System.out.println("Se agrego el nuevo dato " + nuevoDato);
				return true;
			} else {
				System.err.println("Existe un dato igual " + nuevoDato);
				return false;
			}
		} catch (IOException e) {
			System.out.println("No se pudo crear el archivo");
			return false;
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	
		

	}

	/**
	 * Borra un dato de la list
	 *
	 * @param dato el dato a borrar
	 * @return true si se borro exitosamente, false sino
	 */
	public boolean borra(String dato) {
		return datos.remove(dato);
		
	}

	/**
	 * Metodo llamado cuando se cierra la ventana
	 *
	 */
	public void finaliza() {
		System.exit(0);
	}

	@Override
	public boolean contiene(String dato) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
