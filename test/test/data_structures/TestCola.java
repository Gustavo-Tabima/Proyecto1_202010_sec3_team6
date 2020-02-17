package test.data_structures;

import static org.junit.Assert.*;


import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import model.data_structures.Cola;
import model.data_structures.Nodo;

public class TestCola<K> {
	private Cola<K> colaPrueba;

	
	@Before 
	public void SetUp()
	{
		colaPrueba = new Cola<K>();
		
		colaPrueba.instertar(3);
		colaPrueba.instertar(1);
	}

	@Before 
	
	@Test
	public void testEliminar()
	{
		SetUp();


		colaPrueba.sacar();

		assertEquals("NO elimina correctamente",colaPrueba.darTamano(),1);

	}
	
	
	@Test
	public void  testInsertar() {


		SetUp();


		colaPrueba.instertar(2);

		assertEquals("NO agrega correctamente",colaPrueba.darTamano(),3);
	}

	@Test
	public void testSacar(){
		SetUp();
		K deberia = colaPrueba.darPrimero();
		colaPrueba.sacar();
		assertEquals("NO elimino",colaPrueba.darTamano(),1);
		assertEquals("NO devuelve lo que deveria",deberia,3);

	}
	@Test
	public void testBuscar()
	{
		SetUp();


		colaPrueba.buscarElemento(colaPrueba.darPrimero());
		System.out.println(colaPrueba.buscarElemento(colaPrueba.darPrimero()));
		assertEquals("No dio el esperado", 3, colaPrueba.buscarElemento(colaPrueba.darPrimero()));


		;
	}
	@Test
	public void testdarPrimero()
	{
		SetUp();


		colaPrueba.darPrimero();

		assertEquals("NO  correcto",colaPrueba.darPrimero(),3);



	}



}
