package test.logic;

import static org.junit.Assert.*;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {
	
	private Modelo modelo;
	private static int CAPACIDAD=100;
	
	@Before
	public void setUp1() {
		modelo= new Modelo(CAPACIDAD);
	}

	public void setUp2() {
		for(int i =0; i< CAPACIDAD;i++){
			modelo.agregar(""+i);
		}
	}

	@Test
	public void testModelo() {
		
	}

	@Test
	public void testDarTamano() {
	}

	@Test
	public void testAgregar() {
	}

	@Test
	public void testBuscar() {
		setUp2();
	}

	@Test
	public void totalComparendosCola() {
		setUp2();
		
	}
	
	@Test
	public void comparendoMayorObId() {
		setUp2();
		
	}
	
	@Test
	public void zonaMinmax() {
		setUp2();
		
	}

}
