package model.data_structures;

import model.logic.Comparendo;

public class Nodo <K>  {

	private Nodo <K> siguiente;
	

	private Comparendo datos;

	public Nodo(Comparendo pDatos) {
		datos = pDatos;
	}
	public Comparendo darComparendo() {
		return datos;
	}

	public Nodo darSiguente() {
		return siguiente;
	}
	
	
	public void setSiguiente(Nodo psiguiente) {
		
		siguiente=psiguiente;
		
	}

	
	
	public void setDato(Comparendo dato) 
	{
		datos = dato;
	}
}