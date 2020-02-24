package model.logic;

import java.io.FileReader;

import com.google.gson.stream.JsonReader;


import model.data_structures.Cola;

import model.data_structures.Icola;

import model.data_structures.Nodo;


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

import model.logic.Comparendo;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {

	/**
	 * Constante que representa los datos a buscar
	 */
	public static String PATH = "./data/comparendos_dei_2018_small.geojson";

	/**
	 * Atributos del modelo del Mundo
	 */
	private Cola datosCola;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		datosCola = new Cola<Nodo>();
	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datosCola = new Cola<Comparendo>();
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datosCola.darTamano();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param dato
	 */
	public void agregar(String dato)
	{	
		datosCola.enqueue(dato);
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public String buscar(String dato)
	{
		return datosCola.buscarElemento(dato).toString();
	}

	/**
	 * Requerimiento eliminar dato
	 * @param dato Dato a eliminar
	 */
	public String eliminar(String dato)
	{ 	String resp = null;
	if (!datosCola.isEmpty()) {

		if(datosCola.darPrimero().equals(dato)) {
			datosCola.eliminar();
			resp ="se elimino";
		}
		else {
			resp="No se pido elminar";

		}
	}
	return resp;
	}


	public Cola<Comparendo> cargarDatosCola() {


	

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

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);

				datosCola.enqueue(c);
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return datosCola;	}
	
	
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
		return null;
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