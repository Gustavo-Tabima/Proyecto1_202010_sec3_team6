package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Crear Arreglo Dinamico de Comparendos, junto con datos adicionales");
			System.out.println("2. Agregar Comparendo");
			System.out.println("3. Buscar Comparendo");
			System.out.println("4. Eliminar Comparendo");
			System.out.println("5. Imprimir el Arreglo");
			System.out.println("6. Exit");
			System.out.println("Bienvenido al arreglo dinámico de Comparendos, porfavor seleccione una opción para continuar");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
		}
}
