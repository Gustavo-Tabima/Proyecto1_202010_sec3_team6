package model.data_structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import model.logic.Comparendo;

/**
 * 2020-01  Gustavo Tabima - Josue Rivera
 * Estructura de Datos Arreglo Dinamico Generico
 *
 */
public class ArregloDinamico <K> implements IArregloDinamico<K> {

	//---------------------------------------------------------------------------------------------
	//--------------------------Atributos----------------------------------------------------------
	//---------------------------------------------------------------------------------------------
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private Comparendo elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */


	//---------------------------------------------------------------------------------------------
	//--------------------------Métodos----------------------------------------------------------
	//---------------------------------------------------------------------------------------------

	/**
	 * Crea un arreglo dinamico con un tamaño establecido.
	 * 
	 */
	public ArregloDinamico( int Max)
	{
		elementos = (Comparendo[]) new Comparable[Max];;
		tamanoMax = Max;
		tamanoAct = 0;
	}

	/**Agrea un elemento al arreglo dinámico y modifica su tamaño 
	 * 
	 */
	public void agregar( K compa)
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			Object [ ] copia = elementos;
			elementos = (Comparendo[]) new Comparable[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = (Comparendo) copia[i];
			} 
			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}	
		elementos[tamanoAct] = (Comparendo) compa;
		tamanoAct++;
	}

	/**
	 *Da la capacidad del arreglo dinámico. 
	 * @return
	 */
	public int darCapacidad() {
		return tamanoMax;
	}

	public int darTamano() {
		return tamanoAct;
	}

	@Override
	public K darElemento(int a) {
		if (elementos[a]!=null) {
			System.out.println(elementos[a]);
			return (K) elementos[a];
		}
		else {
			System.out.println("El elemento en la posicion dada no existe");
			return null;

		}

	}

	@Override
	public K buscar(K dato) {
		boolean encontrado = false;
		for(int i = 0; i<elementos.length && !encontrado; i++)
		{
			if(elementos[i].equals(dato))
			{
				encontrado = true;
				System.out.println(elementos[i]);

				return (K) elementos[i];
			}
		}
		System.out.println("El elemento en la posicion buscada no existe");

		return null;
	}




	@Override
	public K eliminar(K dato) {

		for (int i = 0; i < elementos.length; i++) {
			if(elementos[i].equals((K) dato))
			{
				K Y = (K) elementos[i];
				elementos[i]=null;
				System.out.println(elementos[i]+" Eliminado");
				for (int j = i; j < elementos.length - 1; j++) {
					elementos[j] = elementos[j+1];
				}

				return Y;

			}
		}
		return null;

	}

	/**
	 * Consulta y devuelve el primer comparendo que encuentre con la localidad buscada.
	 * @param Plocalidad localidad que debe tner el comparendo
	 *  * @return El primer comparendo que cumpla la condicion
	 */
	//parte A3

	public Comparendo consultarPrimerComparendoPorLocalidad(String Plocalidad) {

		for (int i = 0; i < elementos.length; i++) {
			Comparendo act = elementos[i];
			String X = act.darLocalidad();
			if (X.equals(Plocalidad)) {
				System.out.println("Primer comparendo con localidad indicada "+act);
				return act;
			}

		}

		System.out.println("No se encontro ningun comparendo con la localidad indicada");
		return null;
	}


	/**
	 * Consulta y devuelve los comparendos que encuentre segun una fecha de manera ordenada(mayor a menor segun codigo de infraccion).
	 * @param Pfecha Fecha que deben tener los comparendos
	 *  * @return comparendos segun parametro y numero total de comparendo que cumplen la condicion
	 */
	//parte A2

	public String consultarComparendosFecha(K Pfecha) {
		arrayComparendofecha(Pfecha);
		ordenadorArrayComparendoFecha(Pfecha);
		String resp ="";
		for (int i = 0; i < arrayComparendofecha(Pfecha).size(); i++) {
			Comparendo act = arrayComparendofecha(Pfecha).get(i);
			resp=resp + act.darObjectId()+"," + act.darFecha()+act.darInfraccion()+"," +act.darClaseVehi()+"," +act.darTipoServi()+"," +act.darLocalidad()+"\n";

		}

		resp=resp+"total de comparendo segun consulta: "+arrayComparendofecha(Pfecha).size();





		return  resp;

	}



	/**
	 * Comparar  los comparendos,por  cada  código de infraccion,
	 * en dos  fechas dadas. La  comparación solicitada consiste  en mostrar  el total de comparendos  de  cada  código   para las 2 fechas.
	 * @param fecha1  Fecha que deben tener los comparendos
	 * * @param fecha2  Fecha que deben tener los comparendos
	 *  * @return total de cada tipo de comparendos en las fechas fecha
	 */
	//parte A3
	//nota no esta terminado este ;v
	@SuppressWarnings("unchecked")
	public String comparaComparendoCodigoSegunFechas(Date fecha1, Date fecha2) {
		String respuesta= "Infracción | "+fecha1+" | "+fecha2+"\n";

		ArrayList<Comparendo> listaRespuesta= new ArrayList<Comparendo>();

		ArrayList<Comparendo> listaFecha1= new ArrayList<Comparendo>();
		ArrayList<Comparendo> listaFecha2= new ArrayList<Comparendo>();



		for (int i = 0; i < elementos.length; i++) {
			Comparendo actual = elementos[i];


			if(actual.darFecha().equals(fecha1)) {
				listaFecha1.add(actual);
			}
			if(actual.darFecha().equals(fecha2)) {
				listaFecha2.add(actual);
			}
		}	

		for (int i = 0; i < listaFecha1.size(); i++) {
			int contador1=0;
			int contador2=0;

			String act = listaFecha1.get(i).darInfraccion();
			if (codigoUsado((K) act)==null) {
				contador1=	consultarNumComparendosCodigoInfracc(listaFecha1, act);
				contador2= consultarNumComparendosCodigoInfracc(listaFecha2, act);
				if (contador1>0 && contador2>0) {
					
					 respuesta = act +"| "+contador1+" | "+contador2+"\n";
					
					
				}
			}





		}



		if (listaFecha1.size()==0  ) {
			
			respuesta="No hay comparendos en la fecha: " +fecha1;
		
		}
		
		if (listaFecha2.size()==0) {
			respuesta="No hay comparendos en la fecha: " +fecha2;

		}
		
		return respuesta;
	}	

	public K codigoUsado(K pcomparendo) {
		K prueba = null;
		ArrayList<Comparendo> listaUsados= new ArrayList<Comparendo>();


		if (listaUsados.contains(pcomparendo)) {
			prueba=(K) "usado";
		}else {
			listaUsados.add((Comparendo) pcomparendo);
		}


		return prueba;

	}
	/**
	 * Consulta y devuelve el primer comparendo que encuentre con el codigo de infraccion dado por parametro.
	 * @param Pinfraccion infraccion que debe tener el comparendo
	 *  * @return El primer comparendo que cumpla la condicion
	 */

	//parte B1
	public Comparendo consultarPrimerComparendoPorInfraccion(String Pinfraccion) {

		for (int i = 0; i < elementos.length; i++) {
			Comparendo act = elementos[i];
			String X = act.darLocalidad();
			if (X.equals(Pinfraccion)) {
				System.out.println("Primer comparendo con localidad indicada "+act);
				return act;
			}

		}

		System.out.println("No se encontro ningun comparendo con la Infraccion indicada");
		return null;



	}


	/**
	 * Consulta y devuelve los comparendos que encuentre segun un codigo de infraccion de manera ordenada(menor a mayor segun fecha).
	 * @param pInfrac codigo de infraccion que deben tener los comparendos
	 *  * @return comparendos segun parametro y numero total de comparendo que cumplen la condicion
	 */
	//parte B2
	public String consultarComparendosCodigoInfracc(K pInfrac) {
		arrayComparendoInfracc(pInfrac);
		ordenadorComparedoInfracc(pInfrac);
		String resp ="";
		for (int i = 0; i < arrayComparendofecha(pInfrac).size(); i++) {
			Comparendo act = arrayComparendofecha(pInfrac).get(i);
			resp=resp + act.darObjectId()+"," + act.darFecha()+act.darInfraccion()+"," +act.darClaseVehi()+"," +act.darTipoServi()+"," +act.darLocalidad()+"\n";

		}

		resp=resp+"total de comparendo segun consulta: "+arrayComparendofecha(pInfrac).size();





		return   resp;

	}
	public int consultarNumComparendosCodigoInfracc(ArrayList X, String act2) {
		ArrayList<Comparendo> comparendoQueConcuerdan= new ArrayList();

		for (int j = 0; j < X.size(); j++) {
			Comparendo act = (Comparendo) X.get(j);

			K Y =(K) act.darInfraccion();

			if (Y.equals(act2)) {
				comparendoQueConcuerdan.add(act);
			}



		}

		return comparendoQueConcuerdan.size();
	}





	/**
	 * Comparar  los comparendos,por  cada  código de infraccion,en segun su tipo de servicio (particular o publico). 
	 * La  comparación solicitada consiste  en mostrar  el total de  comparendos  de  cada  código   para cada tipo de servicio.
	 *   @return total de comparendos segun tipo de servicio
	 */
	//parte B3
	public K compararComparendoCodigoSegunTipoServi() {


		return null;

	}

	/**
	 * Consulta y devuelve  una listalos comparendos que encuentre segun una fecha
	 * @param Pfecha Fecha que deben tener los comparendos
	 *  * @return lista de comaparendos
	 */

	public ArrayList<Comparendo> arrayComparendofecha(K Pfecha) {
		ArrayList<Comparendo> comparendoQueConcuerdan= new ArrayList();

		for (int j = 0; j < elementos.length; j++) {
			Comparendo act = elementos[j];

			K X =(K) act.darFecha();

			if (X.equals(Pfecha)) {
				comparendoQueConcuerdan.add(act);
			}



		}

		return comparendoQueConcuerdan;


	}


	/**
	 * Consulta   una lista  de comparendos y la ordena segun codigo de infraccion de mayor a mneor.
	 * @param Pfecha Fecha que deben tener los comparendos
	 *  * pos: lista de comaparendos ordenada de mayor a menor segun codigo de infraccion
	 */

	public void ordenadorArrayComparendoFecha(K Pfecha) {
		;
		int contador=0;
		for (int i = 0; i < arrayComparendofecha(Pfecha).size() ; i++) {
			Comparendo act = arrayComparendofecha(Pfecha).get(i);
			Comparendo sig = arrayComparendofecha(Pfecha).get(i+1);
			if ((act.darInfraccion().compareTo(sig.darInfraccion()))<0) {
				arrayComparendofecha(Pfecha).set(i, sig);
				arrayComparendofecha(Pfecha).set(i+1, act);
				contador++;

			}



		}
		if (contador!=0) {
			ordenadorArrayComparendoFecha(Pfecha);
		}
	}






	/**
	 * Consulta y devuelve  una listalos comparendos que encuentre segun codigo de infraccion
	 * @param pInfracc codigo de infraccion que deben tener los comparendos
	 *  * @return lista de comaparendos
	 */
	public ArrayList<Comparendo> arrayComparendoInfracc(K pInfracc) {
		ArrayList<Comparendo> comparendoQueConcuerdan= new ArrayList();

		for (int j = 0; j < elementos.length; j++) {
			Comparendo act = elementos[j];

			K X =(K) act.darInfraccion();

			if (X.equals(pInfracc)) {
				comparendoQueConcuerdan.add(act);
			}



		}

		return comparendoQueConcuerdan;
	}



	/**
	 * Consulta   una lista  de comparendos y la ordena segun fecha de menor a mayor.
	 * @param Pfecha Fecha que deben tener los comparendos
	 *  * pos: lista de comaparendos ordenada de mayor a menor segun codigo de infraccion
	 */

	public void ordenadorComparedoInfracc(K pInfracc){

		int contador=0;
		for (int i = 0; i < arrayComparendoInfracc(pInfracc).size() ; i++) {
			Comparendo act = arrayComparendoInfracc(pInfracc).get(i);
			Comparendo sig = arrayComparendoInfracc(pInfracc).get(i+1);
			if ((act.darFecha().compareTo(sig.darFecha()))>0) {
				arrayComparendofecha(pInfracc).set(i, sig);
				arrayComparendofecha(pInfracc).set(i+1, act);
				contador++;

			}



		}
		if (contador!=0) {
			ordenadorArrayComparendoFecha(pInfracc);
		}
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
	public K NumerodeComparendosCodigoSegunLocalidad(K pLocalidad, K fechaInicial, K fechaFinal ) {
		return null;

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
	public K consultarNComparendosMayoresSegunPeriodoFecha(K N ,K fechaInicial, K fechaFinal) {
		return N;

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

	public K GeneradorHistrogramaASCIINUmTotalSegunLocalidad() {
		return null;

	}

	public String mostrarMayorId() {
		String respuesta = "";



		return respuesta;
	}



}

