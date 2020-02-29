package model.logic;

import java.io.FileNotFoundException;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;


/**
 * Definicion del modelo del mundo
 * @param <K>
 *
 */
public class Modelo<K> {
	/**
	 * Atributos del modelo del mundo
	 */
	private ArregloDinamico datos;
	public static String PATH = "./data/comparendos_dei_2018_small.geojson";
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datos = new ArregloDinamico();
	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{

		datos = cargarDatos();
		
	}



	public ArregloDinamico cargarDatos() {




		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparable c = (Comparable) new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);

				datos.agregar(c);
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return datos;	}
	
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darTamano();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(String dato)
	{	
		datos.agregar(dato);
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public String buscar(String dato)
	{ String x =""+datos.buscar(dato);
	return x;
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 * @return dato eliminado
	 */
	public String eliminar(String dato)

	{
		String resp="";

		if (datos.eliminar(dato)==null) {
			resp="NO se pudo eliminar";
		}else {
			resp =""+datos.eliminar(dato);

		}

		return resp;


	}

	/** recorre los datos cargados en la cola y da el numero total de cuantos hay
	 * @return numero de comparendos en la cola
	 */
	public int totalComparendosCola() {
		return 0;

	}
	/** recorre los datos cargados en la cola y da el comparendo con mayor object ID
	 * @return retorna el comparendo con mayor object id
	 */
	public String comparendoMayorObId() {
	return datos.mostrarMayorId();
	}


	/** analiza los datos de la cola y da la zona min-max
	 * La zona Minimax de los comparendos es definida como límites inferior y superior de  latitud  y  longitud  en  todo  el  archivo.
	 * El  Minimax  se  define  como  una  zona rectangular  con  dos  puntos  extremos:(la menor  latitud, la menor  longitud) 
	 *  y  (la mayor latitud, la mayor longitud).
	 * @return retorna la zona min max
	 */
	public String zonaMinmax() {
		return "";
	}

}
