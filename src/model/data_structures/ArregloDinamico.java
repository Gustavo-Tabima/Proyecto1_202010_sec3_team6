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
	//--------------------------Mï¿½todos----------------------------------------------------------
	//---------------------------------------------------------------------------------------------

	/**
	 * Crea un arreglo dinamico con un tamaï¿½o establecido.
	 * 
	 */
	public ArregloDinamico( int Max)
	{

		elementos =  new Comparendo[Max];;
		tamanoMax = Max;

		elementos =  new Comparendo[20];


		tamanoAct = 0;
	}

	/**Agrea un elemento al arreglo dinï¿½mico y modifica su tamaï¿½o 
	 * 
	 */
	public void agregar( K compa)
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			Object [ ] copia = elementos;
			elementos = (Comparendo[]) new Comparendo[tamanoMax];
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
	 *Da la capacidad del arreglo dinï¿½mico. 
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
	//------------------------------------------------------
	//Metodos parte A
	//-----------------------------------
	/**
	 * Consulta y devuelve el primer comparendo que encuentre con la localidad buscada.
	 * @param Plocalidad localidad que debe tner el comparendo
	 *  * @return El primer comparendo que cumpla la condicion
	 */
	//parte A1

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
	 * @param fevcha Fecha que deben tener los comparendos
	 *  * @return comparendos segun parametro y numero total de comparendo que cumplen la condicion
	 */
	//parte A2

	public String consultarComparendosFecha(Date fevcha) {
		arrayComparendofecha((K) fevcha);
		ordenadorArrayComparendoFecha((K) fevcha);
		String resp ="";
		for (int i = 0; i < arrayComparendofecha((K) fevcha).size(); i++) {
			Comparendo act = arrayComparendofecha((K) fevcha).get(i);
			resp=resp + act.darObjectId()+"," + act.darFecha()+act.darInfraccion()+"," +act.darClaseVehi()+"," +act.darTipoServi()+"," +act.darLocalidad()+"\n";

		}

		resp=resp+"total de comparendo segun consulta: "+arrayComparendofecha((K) fevcha).size();





		return  resp;

	}



	/**
	 * Comparar  los comparendos,por  cada  cï¿½digo de infraccion,
	 * en dos  fechas dadas. La  comparaciï¿½n solicitada consiste  en mostrar  el total de comparendos  de  cada  cï¿½digo   para las 2 fechas.
	 * @param fevcha  Fecha que deben tener los comparendos
	 * * @param fecha2  Fecha que deben tener los comparendos
	 *  * @return total de cada tipo de comparendos en las fechas fecha
	 */
	//parte A3
	//nota no esta terminado este ;v
	@SuppressWarnings("unchecked")
	public String comparaComparendoCodigoSegunFechas(Date fevcha, Date fevcha2) {
		String respuesta= "Infraccion | "+fevcha+" | "+fevcha2+"\n";


		ArrayList<Comparendo> listaFecha1= new ArrayList();
		ArrayList<Comparendo> listaFecha2= new ArrayList();



		for (int i = 0; i < darTamano(); i++) {
			Comparendo actual = elementos[i];
			if(actual.darFecha().equals(fevcha)) {
				listaFecha1.add(actual);
			}
			if(actual.darFecha().equals(fevcha2)) {
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

			respuesta="No hay comparendos en la fecha: " +fevcha;

		}

		if (listaFecha2.size()==0) {
			respuesta="No hay comparendos en la fecha: " +fevcha2;

		}

		return respuesta;
	}	

	public K codigoUsado(K pcomparendo) {
		K prueba = null;
		ArrayList listaUsados= new ArrayList();


		if (listaUsados.contains(pcomparendo)) {
			prueba=(K) "usado";
		}else {
			listaUsados.add( pcomparendo);
		}


		return prueba;

	}

	//------------------------------------------------------
	//Metodos parte B
	//-----------------------------------
	/**
	 * Consulta y devuelve el primer comparendo que encuentre con el codigo de infraccion dado por parametro.
	 * @param Pinfraccion infraccion que debe tener el comparendo
	 *  * @return El primer comparendo que cumpla la condicion
	 */

	//parte B1
	public Comparendo consultarPrimerComparendoPorInfraccion(String Pinfraccion) {

		for (int i = 0; i < elementos.length; i++) {
			Comparendo act = elementos[i];

			if (elementos[i].darInfraccion().contentEquals(Pinfraccion)) {
				System.out.println("Primer comparendo con localidad indicada "+act);
				return act;
			}

		}

		System.out.println("No se encontro ningun comparendo con la Infraccion indicada");
		return null;



	}


	/**
	 * Consulta y devuelve los comparendos que encuentre segun un codigo de infraccion de manera ordenada(menor a mayor segun fecha).
	 * @param string codigo de infraccion que deben tener los comparendos
	 *  * @return comparendos segun parametro y numero total de comparendo que cumplen la condicion
	 */
	//parte B2
	public String consultarComparendosCodigoInfracc(String string) {
		arrayComparendoInfracc((K) string);
		ordenadorComparedoInfracc((K) string);
		String resp ="";
		for (int i = 0; i < arrayComparendoInfracc((K) string).size(); i++) {
			Comparendo act = arrayComparendoInfracc((K) string).get(i);
			resp=resp + act+"\n";

		}

		resp=resp+"total de comparendo segun consulta: "+arrayComparendoInfracc((K) string).size();





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
	 * Comparar  los comparendos,por  cada  cï¿½digo de infraccion,en segun su tipo de servicio (particular o publico). 
	 * La  comparaciï¿½n solicitada consiste  en mostrar  el total de  comparendos  de  cada  cï¿½digo   para cada tipo de servicio.
	 *   @return total de comparendos segun tipo de servicio
	 */
	//parte B3
	public K compararComparendoCodigoSegunTipoServi() {

		String respuesta= "Infraccion | Particular |Publico "+"\n";


		ArrayList<Comparendo> listaParticular= new ArrayList<Comparendo>();
		ArrayList<Comparendo> listaPublic= new ArrayList<Comparendo>();



		for (int i = 0; i < darTamano(); i++) {
			Comparendo actual = elementos[i];


			if(actual.darClaseVehi().equals("Particular")) {
				listaParticular.add(actual);
			}
			if(actual.darClaseVehi().equals("Publico")) {
				listaPublic.add(actual);
			}
		}	

		for (int i = 0; i < listaParticular.size(); i++) {
			int contador1=0;
			int contador2=0;

			String act = listaParticular.get(i).darInfraccion();
			if (codigoUsado((K) act)==null) {
				contador1=	consultarNumComparendosCodigoInfracc(listaParticular, act);
				contador2= consultarNumComparendosCodigoInfracc(listaPublic, act);
				if (contador1>0 && contador2>0) {

					respuesta = act +"| "+contador1+" | "+contador2+"\n";


				}
			}





		}



		if (listaParticular.size()==0  ) {

			respuesta="No hay comparendos comunes";

		}

		if (listaPublic.size()==0) {
			respuesta="No hay comparendos comunes";

		}


		return null;
	}

	/**
	 * Consulta y devuelve  una listalos comparendos que encuentre segun una fecha
	 * @param Pfecha Fecha que deben tener los comparendos
	 *  * @return lista de comaparendos
	 */

	public ArrayList<Comparendo> arrayComparendofecha(K Pfecha) {
		ArrayList<Comparendo> comparendoQueConcuerdan= new ArrayList();

		for (int j = 0; j < darTamano(); j++) {

			if (elementos[j].darFecha().equals(Pfecha)) {
				comparendoQueConcuerdan.add(elementos[j]);
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
		ArrayList<Comparendo> x = arrayComparendofecha(Pfecha);
		int contador=0;
		for (int i = 0; i < x.size()-1; i++) {
			Comparendo act =  x.get(i);
			Comparendo sig = x.get(i+1);
			
			if (act.darInfraccion().compareTo(sig.darInfraccion())<0) {
				x.set(i+1, act);

				x.set(i, sig);

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

		for (int j = 0; j < darTamano(); j++) {
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
		for (int i = arrayComparendoInfracc(pInfracc).size() ; i > 1; i--) {
			Comparendo act = arrayComparendoInfracc(pInfracc).get(i);
			Comparendo ant = arrayComparendoInfracc(pInfracc).get(i-1);
			if ((act.darInfraccion().compareTo(ant.darInfraccion()))>0) {
				arrayComparendoInfracc(pInfracc).set(i, ant);
				arrayComparendoInfracc(pInfracc).set(i-1, act);
				contador++;

			}



		}
		if (contador!=0) {
			ordenadorComparedoInfracc(pInfracc);
		}
	}

	//------------------------------------------------------
	//Metodos parte Carga
	//-----------------------------------
	/**
	 * 
	 * Organiza los comparendos en orden de ID y al final retorna el String con mayor id como respuesta
	 */
	public String mostrarMayorId() {
		String respuesta = "";

		int tam = elementos.length;
		for (int i = 0; i < elementos.length; i++) {
			int minInfrac = i; 
			for (int j = i+1; j < tam; j++) 
				if (elementos[j].darObjectId() < elementos[minInfrac].darObjectId()) 
					minInfrac = j; 


			Comparendo temporal = elementos[minInfrac]; 
			elementos[minInfrac] = elementos[i]; 
			elementos[i] = temporal; 
		}





		return respuesta;
	}
	/**
	 * Retorna un String con la zona MiniMax que hay entre las longitudes y latitudes de los comparendos
	 * @param lowLong Menor longitud
	 * @param LowLat Menor Latitud
	 * @param hLong Mayor Longitud
	 * @param hLat Mayor Latitud
	 */
	public String darZonaMinimax(double lowLong, double LowLat, double hLong, double hLat) {
		double base = hLat - LowLat;
		double altura = hLong - lowLong;
		double areaMiniMax = base*altura;	
		String miniMax = "Los puntos que marcan la ZonaMinimax son " + lowLong+ "," + LowLat + "," + hLong + "," + hLat
				+ "El area de la zona minimax es: " + areaMiniMax;	

		return miniMax;
	}

	/**Dar la menor longitud de todos los comparendos del arreglo.
	 *
	 * @return la menor longitud de los comparendos
	 */
	public double darlowLong() {
		double lowLong = elementos[0].darLongitud();
		int tam = elementos.length;
		for (int i = 0; i < elementos.length; i++) {
			int minInfrac = i; 
			for (int j = i+1; j < tam; j++) 
				if (elementos[j].darLongitud()< elementos[minInfrac].darLongitud()) 
					minInfrac = j; 


			Comparendo temporal = elementos[minInfrac]; 
			elementos[minInfrac] = elementos[i]; 
			elementos[i] = temporal; 

		}
		lowLong = elementos[0].darLongitud();
		return lowLong;

	}


	/**Dar la menor latitud de todos los comparendos del arreglo.
	 *
	 * @return la menor latitud de los comparendos
	 */

	public double darlowLat() {
		double lowlat = elementos[0].darLongitud();
		int tam = elementos.length;
		for (int i = 0; i < elementos.length; i++) {
			int minInfrac = i; 
			for (int j = i+1; j < tam; j++) 
				if (elementos[j].darLatitud()< elementos[minInfrac].darLatitud()) 
					minInfrac = j; 


			Comparendo temporal = elementos[minInfrac]; 
			elementos[minInfrac] = elementos[i]; 
			elementos[i] = temporal; 
		}
		lowlat = elementos[0].darLatitud();
		return lowlat;
	}

	/**Dar la mayor latitud de todos los comparendos del arreglo.
	 *
	 * @return la mayor latitud de los comparendos
	 */
	public double darHlat() {
		double hlat = elementos[0].darLatitud();
		int tam = elementos.length;
		for (int i = 0; i < elementos.length; i++) {
			int minInfrac = i; 
			for (int j = i+1; j < tam; j++) 
				if (elementos[j].darLatitud()> elementos[minInfrac].darLatitud()) 
					minInfrac = j; 


			Comparendo temporal = elementos[minInfrac]; 
			elementos[minInfrac] = elementos[i]; 
			elementos[i] = temporal; 
		}
		hlat = elementos[0].darLatitud();
		return hlat;
	}



	//------------------------------------------------------
	//Metodos parte C
	//-----------------------------------
	public String comparedosFechaFechaLocalidad(K dato, K dato2,String local) {
		String respuesta = "";
		int contador = 0;
		ArrayList<Comparendo> parametros = new ArrayList<Comparendo>();
		for (int i = 0; i < elementos.length; i++) {
			if(elementos[i].darFecha().compareTo((Date) dato) == 0 && elementos[i].darFecha().compareTo((Date) dato2) == -1  ||
					elementos[i].darFecha().compareTo((Date) dato) == 1 || elementos[i].darFecha().compareTo((Date) dato2) == -1 ||
					elementos[i].darFecha().compareTo((Date) dato) == 1 || elementos[i].darFecha().compareTo((Date) dato2) == 0) {
				parametros.add(elementos[i]);
			}
		}

		while (parametros.size()> contador) {
			if(parametros.get(contador).darLocalidad().compareTo(local) != 0) {
				parametros.remove(contador);
			}
			contador++;

		}

		if(!parametros.isEmpty()) {
			contador = 0;
			while(contador < parametros.size()) {
				respuesta += parametros.get(contador).darInfraccion() +";"+this.darnumerodeInfracciones(parametros.get(contador).darInfraccion(),parametros);
			}
		}
		return respuesta;
	}




	/**Dar la mayor longitud de todos los comparendos del arreglo.
	 *
	 * @return la mayor longitud de los comparendos
	 */
	public double darHlong() {
		double hlong = elementos[0].darLongitud();
		int tam = elementos.length;
		for (int i = 0; i < elementos.length; i++) {
			int minInfrac = i; 
			for (int j = i+1; j < tam; j++) 
				if (elementos[j].darLongitud()> elementos[minInfrac].darLongitud()) 
					minInfrac = j; 


			Comparendo temporal = elementos[minInfrac]; 
			elementos[minInfrac] = elementos[i]; 
			elementos[i] = temporal; 
		}
		hlong = elementos[0].darLongitud();
		return hlong;
	}

	public int darnumerodeInfracciones(String codigoInfrac, ArrayList<Comparendo> entrada) {
		int numero = 0;
		for (int i = 0; i < entrada.size(); i++) {
			if(entrada.get(i).darInfraccion().equalsIgnoreCase(codigoInfrac)) {
				numero++;
			}

		}
		return numero;
	}

	public String generarhistograma(ArrayList<String> listalocalidades){
		String respuesta = "";
		int z = 0;
		int reps = 0;
		String asteriscos = "";
		while (z < listalocalidades.size()) {
			int j = 0;
			reps =	darRepeticionesLocal(listalocalidades.get(z));
			int numeroasteristicos = reps%50;
			while (j < numeroasteristicos) {

				asteriscos += "*";
			}

			return listalocalidades.get(z) + asteriscos;
		}
		return respuesta;		}


	public int darRepeticionesLocal(String local) {
		int reps = 0;
		for (int i = 0; i < elementos.length; i++) {
			if(elementos[i].darLocalidad().equals(local)) {
				reps++;
			}
		}



		return reps;
	}
	public int contadordeInfraccionesPorLocalidad(){
		int numero = 0;
		for (int i = 0; i < elementos.length; i++) {
			if(elementos[i].darLocalidad().equals(darlistaLocalidades().get(i))){

			}
		}

		return numero;
	}

	public ArrayList<String> darlistaLocalidades(){
		ArrayList<String> lista = new ArrayList<>();

		for (int i = 0; i < elementos.length; i++) {
			if(NoestaRepetida(elementos[i].darLocalidad(),lista)){
				lista.add(elementos[i].darLocalidad());
			}
		}
		return lista;
	}

	public Boolean NoestaRepetida(String entrada,ArrayList<String> comparador){
		Boolean respuesta = true;
		for (int i = 0; i < comparador.size(); i++) {
			if(entrada.equals(comparador.get(i))){
				respuesta = false;
			}
		}
		return respuesta;
	}



	public String rankingDeInfractores(K dato, K dato2,int rank){
		String respuesta = "";


		int contador = 0;
		ArrayList<Comparendo> parametros = new ArrayList<Comparendo>();
		for (int i = 0; i < elementos.length; i++) {
			if(elementos[i].darFecha().compareTo((Date) dato) == 0 && elementos[i].darFecha().compareTo((Date) dato2) == -1  ||
					elementos[i].darFecha().compareTo((Date) dato) == 1 || elementos[i].darFecha().compareTo((Date) dato2) == -1 ||
					elementos[i].darFecha().compareTo((Date) dato) == 1 || elementos[i].darFecha().compareTo((Date) dato2) == 0) {
				parametros.add(elementos[i]);
			}
		}
		int[] ranks = new int[rank-1];
		int k = 0;
		int topeInfracción = 0;
		int posMayorInfrac = 0;
		String[] ranksTipo = new String[rank-1];

		int n = parametros.size(); 
		for (int i = 0; i < parametros.size()- 1; i++) {
			int pos = i;

			for (int j = i + 1; j <= parametros.size(); j++) {
				if (darnumerodeInfracciones(parametros.get(j).darInfraccion(),parametros) < darnumerodeInfracciones(parametros.get(pos).darInfraccion(),parametros))
					pos = j;


				Comparendo min = parametros.get(pos);
				parametros.set(i,min);
				min = parametros.get(i);
			}
		}
		int z = 0;
		while(z < rank){
			ranks[z] = darnumerodeInfracciones(parametros.get(z).darInfraccion(),parametros);
			ranksTipo[z] = parametros.get(z).darInfraccion();
			z++;
			respuesta += ranksTipo[z] + ";" + ranks[z];
		}

		return respuesta;
	}

	public String imprimirTodo() {
		// TODO Auto-generated method stub
		String resp="";
		for (int i = 0; i < elementos.length; i++) {
			resp=resp +elementos[i]+"\n";
		}
		return resp;
	}


}

