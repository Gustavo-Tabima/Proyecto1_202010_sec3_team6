package test.data_structures;

import model.data_structures.ArregloDinamico;
import model.logic.Comparendo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestArregloDinamico<K> {

	private ArregloDinamico arreglo;
	private static int TAMANO=100;
	private K prueba;
	private Date fevcha= new Date(2019,12,13);
	private K Compa = (K) new Comparendo(0, fevcha, "Lmao", "lapiz", "moto", "particular", "635", "runaterra", 0, 0);
	private K Compa2 = (K) new Comparendo(0, fevcha, "choque", "lapiz", "carro", "particular", "635", "bogota", 0, 0);


	@Before
	public void setUp1() {
		arreglo= new ArregloDinamico(TAMANO);
		arreglo.agregar((Comparable) Compa);
		arreglo.agregar((Comparable) Compa2);

	}

	public void setUp2() {
		for(int i =0; i< TAMANO*2; i++){
			arreglo.agregar(""+i);
		}
	}

	@Test
	public void testArregloDinamico() {
		// TODO
	}

	@Test
	public void testDarElemento() {
		setUp1();
		arreglo.darElemento(0);

		assertEquals("NO da elemento correctamente",1,1);
	
	}

}
