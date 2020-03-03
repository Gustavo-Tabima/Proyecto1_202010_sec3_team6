package test.data_structures;

import model.data_structures.ArregloDinamico;

import model.logic.Comparendo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestArregloDinamico<K> {

	private ArregloDinamico<Comparendo> arreglo;
	private static int TAMANO=100;
	private K prueba;
	private Date fevcha= new Date();
	private Date fevcha2= new Date();

	private Comparendo Compa =  new Comparendo(0, fevcha, "Lmao", "lapiz", "moto", "Particular", "635", "runaterra", 0, 0);
	private Comparendo Compa2 =  new Comparendo(0, fevcha2, "choque", "lapiz", "carro", "Particular", "C35", "bogota", 0, 0);


	@Before
	public void setUp1() {
		arreglo= new ArregloDinamico(8);
		arreglo.agregar( Compa);
		arreglo.agregar( Compa2);

	}

	

	@Test
	public void testArregloDinamico() {
		setUp1();
		

		assertEquals("NO se inicia correctamente",arreglo.darTamano(),2);

		
	}

	@Test
	public void testDarElemento() {
		setUp1();
		Object x = arreglo.darElemento(0);

		assertEquals("NO se agrega el comparendo de manera correcta",x,Compa);

	}
	
	@Test
	public void testDarfecha() {
		setUp1();
		Object x = arreglo.darElemento(0).darFecha();

		assertEquals("NO se agrega el comparendo de manera correcta",x,fevcha);

	}@Test
	public void testDarcod() {
		setUp1();
		Object x = arreglo.darElemento(0).darInfraccion();

		assertEquals("NO se agrega el comparendo de manera correcta",x,"635");

	}
	
	@Test
	public void testAgregar() {
		setUp1();
		Comparendo compa3= new Comparendo<>(10, fevcha, "pana", "xd", "moto", "particular", "C13", "Bmanga", 12, 11);
		arreglo.agregar(compa3);
		
		
		assertEquals("NO agrega correctamnete",3,arreglo.darTamano());

	}
	
	@Test
	public void testDarEliminar() {
		setUp1();
		arreglo.eliminar(Compa);

		assertEquals("NO elimino correctamente",2,arreglo.darTamano());

	}
	@Test
	//A1
	public void testConsultarPrimerComparendoPorLocalidad() {
		setUp1();
	Comparendo x = arreglo.consultarPrimerComparendoPorLocalidad("runaterra");

		assertEquals("No devuelve el esperado  ",x,arreglo.darElemento(0));

	}
	@Test
	//A2
	public void testConsultarComparendosFecha() {
		setUp1();
		String x = arreglo.consultarComparendosFecha(fevcha);
		assertEquals("NO da lo esperado",x,0);

	}
	@Test
	//A3
	public void testComparaComparendoCodigoSegunFechas() {
		setUp1();
		 arreglo.comparaComparendoCodigoSegunFechas(fevcha, fevcha2);

		assertEquals("NO da lo esperado",arreglo.comparaComparendoCodigoSegunFechas(fevcha, fevcha2),"hola");

	
	}
	@Test
	//B1
	public void testconsultarPrimerComparendoPorInfraccion() {
		setUp1();
	Comparendo x = arreglo.consultarPrimerComparendoPorInfraccion("635");

		assertEquals("NO da elemento correctamente",x,arreglo.darElemento(0));

	}
	@Test
	//B2
	public void testconsultarComparendosCodigoInfracc() {
		setUp1();
		
String X = arreglo.consultarComparendosCodigoInfracc("635");
		assertEquals("NO da resultado esperado",X,1);

	}
	@Test
	//B3
	public void testcompararComparendoCodigoSegunTipoServi() {
		setUp1();
		Comparendo x = arreglo.compararComparendoCodigoSegunTipoServi();

		assertEquals("NO da elemento correctamente",x,x);

	}
//	@Test
//	public void testNumerodeComparendosCodigoSegunLocalidad() {
//		setUp1();
//		arreglo.darElemento(0);
//
//		assertEquals("NO da elemento correctamente",1,1);
//
//	}
//	@Test
//	public void testConsultarNComparendosMayoresSegunPeriodoFecha() {
//		setUp1();
//		arreglo.darElemento(0);
//
//		assertEquals("NO da elemento correctamente",1,1);
//
//	}
//	
//	@Test
//	public void testGeneradorHistrogramaASCIINUmTotalSegunLocalidad() {
//		setUp1();
//		arreglo.darElemento(0);
//
//		assertEquals("NO da elemento correctamente",1,1);
//
//	}
}
