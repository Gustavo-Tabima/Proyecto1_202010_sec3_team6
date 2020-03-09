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
		datos = new ArregloDinamico(7);
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

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);

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
	public void agregar(K dato)
	{	
		datos.agregar((Comparendo) dato);
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

	public String imprimirArregloEntero(){
return "" + datos.imprimirTodo();
	}
	/** recorre los datos cargados en la cola y da el numero total de cuantos hay
	 * @return numero de comparendos en la cola
	 */
	public int totalComparendos() {
		return datos.darTamano();

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
		return datos.darZonaMinimax(datos.darlowLong(),datos.darlowLat(),datos.darHlong(), datos.darHlat());	}




	public String cargaID() {
		return datos.mostrarMayorId();
	}

	public String tablaFormato(String entrada) {
		String[] formateado = entrada.split(";");
		for (int i = 0; i < formateado.length; i++) {
			String barraTabla = formateado[i] + " | " + formateado[i+1] + "/n";
			return barraTabla;
		}
		return "Fin de la tabla";
	}

	public String darInfraccionesSegunFecha() {
		String respuesta = "";

		return respuesta;
	}
	/**
	 * Consulta y devuelve el primer comparendo que encuentre con la localidad buscada.
	 * @param Plocalidad localidad que debe tner el comparendo
	 *  * @return El primer comparendo que cumpla la condicion
	 */
	//parte A1

	public String consultarPrimerComparendoPorLocalidad(String Plocalidad) {



		return ""+datos.consultarPrimerComparendoPorLocalidad(Plocalidad);
	}


	/**
	 * Consulta y devuelve los comparendos que encuentre segun una fecha de manera ordenada(mayor a menor segun codigo de infraccion).
	 * @param Pfecha Fecha que deben tener los comparendos
	 *  * @return comparendos segun parametro y numero total de comparendo que cumplen la condicion
	 */
	//parte A2

	public String consultarComparendosFecha(K Pfecha) {





		return  ""+datos.consultarComparendosFecha((Date) Pfecha);

	}



	/**
	 * Comparar  los comparendos,por  cada  código de infraccion,
	 * en dos  fechas dadas. La  comparación solicitada consiste  en mostrar  el total de comparendos  de  cada  código   para las 2 fechas.
	 * @param fecha1  Fecha que deben tener los comparendos
	 * * @param fecha2  Fecha que deben tener los comparendos
	 *  * @return total de cada tipo de comparendos en las fechas fecha
	 */
	//parte A3
		public String comparaComparendoCodigoSegunFechas(Date fecha1, Date fecha2) {
			
			
			return ""+datos.comparaComparendoCodigoSegunFechas(fecha1, fecha2);
		}	


	/**
	 * Consulta y devuelve el primer comparendo que encuentre con el codigo de infraccion dado por parametro.
	 * @param Pinfraccion infraccion que debe tener el comparendo
	 *  * @return El primer comparendo que cumpla la condicion
	 */

	//parte B1
	public String consultarPrimerComparendoPorInfraccion(String Pinfraccion) {


		return ""+datos.consultarPrimerComparendoPorInfraccion(Pinfraccion);



	}


	/**
	 * Consulta y devuelve los comparendos que encuentre segun un codigo de infraccion de manera ordenada(menor a mayor segun fecha).
	 * @param pInfrac codigo de infraccion que deben tener los comparendos
	 *  * @return comparendos segun parametro y numero total de comparendo que cumplen la condicion
	 */
	//parte B2
	public String consultarComparendosCodigoInfracc(K pInfrac) {



		return   ""+datos.consultarComparendosCodigoInfracc((String) pInfrac);

	}






	/**
	 * Comparar  los comparendos,por  cada  código de infraccion,en segun su tipo de servicio (particular o publico). 
	 * La  comparación solicitada consiste  en mostrar  el total de  comparendos  de  cada  código   para cada tipo de servicio.
	 *   @return total de comparendos segun tipo de servicio
	 */
	//parte B3
	public String compararComparendoCodigoSegunTipoServi() {


		return ""+datos.compararComparendoCodigoSegunTipoServi();

	}






	/**
	 * Muestra el numero de comparendos por cada código de infraccion en una localidad dada,
	 *  para un periodo de tiempo dado por: fecha inicial y final.
	 *  Los  resultados  deben  ser  presentados  de  manera  alfabeticamente ordenada.
	 * @param fechaInicial Fecha inicial del periodo de tiempo.
	 * @param fechaFinal Fecha final del periodo de tiempo
	 * @param pLocalidad Localidad dada por parametro
	 * @return numero de comparendo dados por localidad en un peiodo de tiempo
	 */
	//parte C1
	public String NumerodeComparendosCodigoSegunLocalidad(Date fechaInicial , Date fechaFinal , String pLocalidad ) {
		return ""+datos.comparedosFechaFechaLocalidad(fechaInicial, fechaFinal, pLocalidad);

	}


	/**
	 * Muestra numero dado de codigos de infraccion mas frecuente en un periodo de tiempo dado,
	 *  Los  resultados  deben  ser  presentados  en formato de tabla
	 * @param fechaInicial Fecha inicial del periodo de tiempo.
	 * @param fechaFinal Fecha final del periodo de tiempo
	 * @param N Numero de codigos que se va a buscar.
	 * @return numero de comparendo mas frecuentes en un peiodo de tiempo
	 */
	//parte C2
	public String consultarNComparendosMayoresSegunPeriodoFecha(int N ,Date fechaInicial, Date fechaFinal) {

		return (String) datos.rankingDeInfractores(fechaInicial, fechaFinal, N);

	}


	/**
	 * Genera una gráfica ASCII (Histograma) que muestra el número total de comparendos por  cada  LOCALIDAD ,representados  por  un  String  de  caracteres  ‘*’. 
	 * Los  nombres  de  las localidades deben aparecer alfabéticamente y deben justificarse a 16 caracteres.
	 * Cada ‘*’corresponde   a   50   comparendos. 
	 * Para   una   localidad,   si   su   residuo del   total   de comparendos al  dividir  entre  50  es  un  número  mayor  a  0,
	 *   este  residuo  corresponde también a  un ‘*’.
	 * @return gráfica ASCII
	 */
	//parte C3

	public String GeneradorHistrogramaASCIINUmTotalSegunLocalidad() {
		int i = 0;
		String r ="";
	while(i<20){
		try {
		r = ""+datos.generarhistograma(datos.darlistaLocalidades())+"\n";
		return r;
		} catch (Exception e) {
			
		}
		i++;
	}
	return r;	

	}




}
