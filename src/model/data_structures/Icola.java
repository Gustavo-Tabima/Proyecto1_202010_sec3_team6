package model.data_structures;

public interface Icola <K> {

public void enqueue(K pnuevo);
	
	public K darPrimero();
	
	public boolean isEmpty();
	
	public int darTamano();
	
	public K buscarElemento(K buscado);
		
	public K dequeue();
	
	public K darFinal();

	public void eliminar();
	public Nodo darInicial();
	
	
}
