package model.data_structures;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import model.logic.Comparendo;

public class Cola <K> implements Icola{

	private Iterator<Nodo> iterador;
	private Nodo inicio;
	private Nodo termino;
	private int tamano;

	public Cola()
	{
		inicio=null;
		termino=null;
		tamano=0;
	}


	public Nodo darInicial() {
		return inicio;
	}

	@Override
	public void enqueue(Object pnuevo) {
		// TODO Auto-generated method stub
		Nodo nuevoNodo=new Nodo((Comparendo) pnuevo);
		nuevoNodo.setSiguiente(null);
		if(inicio==null & termino==null)
		{
			inicio=nuevoNodo;
			termino=nuevoNodo;
			tamano++;

		}
		else {
			termino.setSiguiente(nuevoNodo);
			termino=termino.darSiguente();
			tamano++;	
		}


	}

	@Override
	public K darPrimero() {
		// TODO Auto-generated method stub

		return (K) inicio;
	}

	@Override
	public boolean isEmpty() {
		boolean cola=false;
		if(inicio==null & termino==null)
		{
			cola=true;
			System.out.println("La cola esta vacia");
		}
		else
		{
			System.out.println("La cola no esta vacia");
			cola=false;
		}
		return cola;
	}

	@Override
	public int darTamano() {
		int contador=0;
		Nodo c=inicio;
		while(c!=null)
		{
			contador++;
			c=c.darSiguente();
		}
		System.out.println("Numero de datos en la cola: "+contador);
		tamano=contador;

		return contador;
	}




	@Override
	public Comparendo buscarElemento(Object buscado) {
		// TODO Auto-generated method stub
		if (isEmpty()!=true) {

			Nodo act=inicio;
			boolean encontrado=false;
			while (act!=null && encontrado==false) {
				if (act.darComparendo().equals(buscado)) {
					encontrado=true;
				}else {
					act=act.darSiguente();
				}

			}
			if (encontrado!=false) {
				return act.darComparendo();
			}
			else {
				return null;
			}

		}else {
			return null;
		}


	}




	@Override
	public void eliminar() {

		if (isEmpty()==false) {
			Nodo out = inicio;

			Nodo queue=inicio.darSiguente();
			inicio=queue;
			out.setSiguiente(null);
			tamano--;



			;

		}else {
			System.out.println("No se pudo eliminar,esta vacia la cola");

		}


	}


	public K dequeue(){
		K out = darPrimero();
		eliminar();


		return out;
	}

	public K darFinal() {
		return (K) termino.darComparendo();
	}



	/**
	 * Consulta y devuelve el primer comparendo que encuentre con la localidad buscada.
	 * @param Plocalidad localidad que debe tner el comparendo
	 *  * @return El primer comparendo que cumpla la condicion
	 */

	public Comparendo consultarPrimerComparendoPorLocalidad(String Plocalidad) {
		Nodo act =  inicio;

		while (act!=null) {
			String X = act.darComparendo().darLocalidad();
			if (X.equals(Plocalidad)) {
				System.out.println("Primer comparendo con localidad indicada "+act.darComparendo());
				return act.darComparendo();
			}
			act=act.darSiguente();
		}

		System.out.println("No se encontro ningun comparendo con la localidad indicada");
		return null;

	}


	/**
	 * Consulta y devuelve los comparendos que encuentre segun una fecha de manera ordenada(mayor a menor segun codigo de infraccion).
	 * @param Pfecha Fecha que deben tener los comparendos
	 *  * @return comparendos segun parametro y numero total de comparendo que cumplen la condicion
	 */

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
	 * en dos  fechas dadas. La  comparación solicitada consiste  en mostrar  el total de  comparendos  de  cada  código   para cada fecha.
	 * @param fecha1  Fecha que deben tener los comparendos
	 * * @param fecha2  Fecha que deben tener los comparendos
	 *  * @return total de comparendos segun fecha
	 */
	public String comparaComparendoCodigoSegunFechas(Date fecha1, Date fecha2) {

		Nodo actual = inicio;
		String respuesta = "No hay comparendos entre esas fechas";
		ArrayList listaRespuesta= new ArrayList<Comparendo>();
		while(iterador.hasNext()) {
			if(actual.darComparendo().darFecha().compareTo(fecha1) == 0) {
				listaRespuesta.add(actual);
			}
			else if(actual.darComparendo().darFecha().compareTo(fecha1) == 1 && actual.darComparendo().darFecha().
					compareTo(fecha2) == -1) {
				listaRespuesta.add(actual);
			}
		}	

		if(!listaRespuesta.isEmpty()) {
			int i = 0;
			while (i < listaRespuesta.size()) {
				Comparendo encontrado = (Comparendo) listaRespuesta.get(i);
				respuesta += ""+ encontrado.darInfraccion();

			}
		}
		return respuesta;
	}	
	/**
	 * Consulta y devuelve el primer comparendo que encuentre con el codigo de infraccion dado por parametro.
	 * @param Pinfraccion infraccion que debe tener el comparendo
	 *  * @return El primer comparendo que cumpla la condicion
	 */

	public String consultarPrimerComparendoPorInfraccion(String Pinfraccion) {
		String respuesta = "No se encontró ningún comparendo que cumpla con la infracción requerida.";

		Nodo actual = darInicial();
		if(!(actual.darComparendo().darInfraccion().contentEquals(Pinfraccion))) {
			actual = actual.darSiguente();
			this.consultarPrimerComparendoPorInfraccion(Pinfraccion);
		}
		else {
			respuesta = actual.darComparendo().toString();
		}

		return respuesta;

	}


	/**
	 * Consulta y devuelve los comparendos que encuentre segun un codigo de infraccion de manera ordenada(menor a mayor segun fecha).
	 * @param pInfrac codigo de infraccion que deben tener los comparendos
	 *  * @return comparendos segun parametro y numero total de comparendo que cumplen la condicion
	 */

	public K consultarComparendosCodigoInfracc(K pInfrac) {
		arrayComparendofecha(pInfrac);
		ordenadorArrayComparendoFecha(pInfrac);
		String resp ="";
		for (int i = 0; i < arrayComparendofecha(pInfrac).size(); i++) {
			Comparendo act = arrayComparendofecha(pInfrac).get(i);
			resp=resp + act.darObjectId()+"," + act.darFecha()+act.darInfraccion()+"," +act.darClaseVehi()+"," +act.darTipoServi()+"," +act.darLocalidad()+"\n";

		}

		resp=resp+"total de comparendo segun consulta: "+arrayComparendofecha(pInfrac).size();





		return  (K) resp;

	}

	/**
	 * Comparar  los comparendos,por  cada  código de infraccion,en segun su tipo de servicio (particular o publico). 
	 * La  comparación solicitada consiste  en mostrar  el total de  comparendos  de  cada  código   para cada tipo de servicio.
	 *   @return total de comparendos segun tipo de servicio
	 */

	public K compararComparendoCodigoSegunTipoServi() {
		return null;

	}

	/**
	 * Consulta y devuelve  una listalos comparendos que encuentre segun una fecha
	 * @param Pfecha Fecha que deben tener los comparendos
	 *  * @return lista de comaparendos
	 */

	public ArrayList<Comparendo> arrayComparendofecha(K Pfecha) {
		Nodo act =  inicio;
		ArrayList<Comparendo> comparendoQueConcuerdan= new ArrayList();
		String resp= "";
		while (act!=null) {
			K X =(K) act.darComparendo().darFecha();
			if (X.equals(Pfecha)) {
				comparendoQueConcuerdan.add(act.darComparendo());


			}
			act=act.darSiguente();

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
		Nodo act =  inicio;
		ArrayList<Comparendo> comparendoQueConcuerdan= new ArrayList();
		String resp= "";
		while (act!=null) {
			String X = act.darComparendo().darInfraccion();
			if (X.equals(pInfracc)) {
				comparendoQueConcuerdan.add(act.darComparendo());


			}
			act=act.darSiguente();

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
	public K GeneradorHistrogramaASCIINUmTotalSegunLocalidad() {
		return null;

	}

	public String mostrarMayorId() {
		String respuesta = "";



		return respuesta;
	}

}