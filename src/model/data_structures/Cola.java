package model.data_structures;

import java.util.ArrayList;

import model.logic.Comparendo;

public class Cola <K> implements Icola{


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
	public void instertar(Object pnuevo) {
		// TODO Auto-generated method stub
		Nodo nuevoNodo=new Nodo((Comparendo) pnuevo);
		nuevoNodo.setSiguiente(null);
		if(inicio==null & termino==null)
		{
			inicio=nuevoNodo;
			termino=nuevoNodo;
			tamano++;

		}
		termino.setSiguiente(nuevoNodo);
		termino=termino.darSiguente();
		tamano++;

	}

	@Override
	public K darPrimero() {
		// TODO Auto-generated method stub
		K dato=(K) inicio.darComparendo();

		return dato;
	}

	@Override
	public boolean estaVacio() {
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
	public K buscarElemento(Object buscado) {
		// TODO Auto-generated method stub
		if (estaVacio()!=true) {

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
				return (K) act.darComparendo();
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

		if (estaVacio()==false) {
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


	public K sacar(){
		K out = darPrimero();
		eliminar();


		return out;
	}

	public K darFinal() {
		return (K) termino.darComparendo();
	}
	public Comparendo consultarPrimerComparendoPorLocalidad(K Plocalidad) {
		Nodo act =  inicio;

		while (act!=null) {
			K X =(K) act.darComparendo().darLocalidad();
			if (X.equals(Plocalidad)) {
				System.out.println("Primer comparendo con localidad indicada "+act.darComparendo());
				return act.darComparendo();
			}
			act=act.darSiguente();
		}

		System.out.println("No se encontro ningun comparendo con la localidad indicada");
		return null;

	}


	//registrados  en  el  archivo dadaunaFECHA_HORA.
	//Los resultados debenser presentados de manera ordenada, para este caso de mayor a menor por el  códigoINFRACCION. 
	//Mostrar  los  resultados  indicando  para  cada comparendo  su código OBJECTID, FECHA_HORA, INFRACCION,   CLASE_VEHI,   TIPO_SERVI,   LOCALIDAD.
	//Incluir el total de comparendos de la consulta

	public K consultarComparendosFecha(K Pfecha) {
		arrayComparendofecha(Pfecha);
		organizador(Pfecha);
		String resp ="";
		for (int i = 0; i < arrayComparendofecha(Pfecha).size(); i++) {
			Comparendo act = arrayComparendofecha(Pfecha).get(i);
			resp=resp + act.darObjectId()+"," + act.darFecha()+act.darInfraccion()+"," +act.darClaseVehi()+"," +act.darTipoServi()+"," +act.darLocalidad()+"\n";
			
		}
		
		resp=resp+"total de comparendo segun consulta: "+arrayComparendofecha(Pfecha).size();





		return (K) resp;

	}

	public K comparaComparendoCodigoSegunFechas(K fecha1, K fecha2) {
		return null;

	}
	public K consultarPrimerComparendoPorInfraccion(K Pinfraccion) {
		return null;

	}

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

	public void organizador(K Pfecha) {
		;
		int contador=0;
		for (int i = 0; i < arrayComparendofecha(Pfecha).size() ; i++) {
			Comparendo act = arrayComparendofecha(Pfecha).get(i);
			Comparendo sig = arrayComparendofecha(Pfecha).get(i+1);
			if ((act.darInfraccion().compareTo(sig.darInfraccion()))>0) {
				arrayComparendofecha(Pfecha).set(i, sig);
				arrayComparendofecha(Pfecha).set(i+1, act);
				contador++;

			}
			if (contador!=0) {
				organizador(Pfecha);
			}


		}
	}



}