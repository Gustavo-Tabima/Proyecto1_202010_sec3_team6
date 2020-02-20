package test.data_structures;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import model.data_structures.Cola;
import model.data_structures.Nodo;
import model.logic.Comparendo;

public class TestCola<K> {
	private Cola<K> colaPrueba;
	private K prueba;
	private Date fevcha= new Date(2019,12,13);
	private K Compa = (K) new Comparendo(0, fevcha, "Lmao", "lapiz", "moto", "particular", "635", "runaterra", 0, 0);

	@Before 
	public void SetUp()
	{
		
		
		colaPrueba = new Cola<K>();
		
		colaPrueba.instertar(Compa);
	}

	@Before 
	
	
	
	@Test
	public void  testInsertar() {


		SetUp();
		Date fevcha= new Date(2019,12,13);

		Comparendo prueba = new Comparendo(1, fevcha, "Lmao2", "lapiz2", "moto2", "particular2", "645", "runaterra2", 0.2, 0.2);
		colaPrueba.instertar(prueba);

		assertEquals("NO agrega correctamente",colaPrueba.darTamano(),2);
	}
	@Test
	public void testdarPrimero()
	{
		SetUp();


		colaPrueba.darPrimero();

		assertEquals("NO  correcto",colaPrueba.darPrimero(),Compa);



	}
	@Test
	public void testEliminar()
	{
		SetUp();


		colaPrueba.sacar();

		assertEquals("NO elimina correctamente",colaPrueba.darTamano(),1);

	}


	

	@Test
	public void testSacar(){
		SetUp();
		K deberia = colaPrueba.darPrimero();
		colaPrueba.sacar();
		assertEquals("NO elimino",colaPrueba.darTamano(),1);
		assertEquals("NO devuelve lo que deveria",deberia,Compa);

	}
	@Test
	public void testBuscar()
	{
		SetUp();


		colaPrueba.buscarElemento(colaPrueba.darPrimero());
		System.out.println(colaPrueba.buscarElemento(colaPrueba.darPrimero()));
		assertEquals("No dio el esperado", colaPrueba.buscarElemento(colaPrueba.darPrimero()),Compa );


		;
	}

	


	@Test
	public void testConsultarPrimerComparendoPorLocalidad()
	{



	}

	@Test
	public void testConsultarComparendosFecha()
	{



	}


	@Test
	public void testComparaComparendoCodigoSegunFechas()
	{



	}
	@Test
	public void testConsultarPrimerComparendoPorInfraccion()
	{




	}

	@Test
	public void testConsultarComparendosCodigoInfracc()
	{
		


	}

	@Test
	public void testCompararComparendoCodigoSegunTipoServi()
	{
		



	}
	@Test
	public void testArrayComparendofecha()
	{
		


	}
	@Test
	public void testOrdenadorArrayComparendoFecha()
	{
		


	}
	@Test
	public void testArrayComparendoInfracc()
	{
		


	}
	
	

	
	public void testOrdenadorComparedoInfracc()
	{



	}
	
	public void testNumerodeComparendosCodigoSegunLocalidad()
	{



	}
	
	public void testConsultarNComparendosMayoresSegunPeriodoFecha()
	{



	}
	
	public void testGeneradorHistrogramaASCIINUmTotalSegunLocalidad()
	{



	}

	
}
