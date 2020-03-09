package controller;

import java.util.Date;
import java.util.Scanner;

import model.data_structures.ArregloDinamico;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String dato2 = "";
		String dato3 = "";


		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			int datoi;
			switch(option){
			case 1:
				view.printMessage("--------- \nCrear Arreglo \nDar capacidad inicial del arreglo: ");

				modelo = new Modelo(); 
				ArregloDinamico x = modelo.cargarDatos();
				view.printMessage("Arreglo Dinamico creado");
				view.printMessage("Numero actual de elementos " + x.darTamano() + "\n---------");

				break;

			case 2:
				view.printMessage("--------- \nDar cadena (simple) a ingresar: ");
				dato = lector.next();
				modelo.agregar(dato);
				view.printMessage("Dato agregado");
				view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
				break;

			case 3:
				view.printMessage("--------- \nDar cadena (simple) a buscar: ");
				dato = lector.next();
				respuesta = modelo.buscar(dato);
				if ( respuesta != null)
				{
					view.printMessage("Dato encontrado: "+ respuesta);
				}
				else
				{
					view.printMessage("Dato NO encontrado");
				}
				view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
				break;

			case 4:
				view.printMessage("--------- \nDar cadena (simple) a eliminar: ");
				dato = lector.next();
				respuesta = modelo.eliminar(dato);
				if ( respuesta != null)
				{
					view.printMessage("Dato eliminado "+ respuesta);
				}
				else
				{
					view.printMessage("Dato NO eliminado");							
				}
				view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
				break;

			case 5: 
				view.printMessage("--------- \nTotal de comparendos en el archivo: ");
				view.printModelo(modelo);
				view.printMessage(modelo.totalComparendos()+"");
				break;	

			case 6: 
				view.printMessage("--------- \nComparendo con mayor ID: ");
				view.printModelo(modelo);
				view.printMessage(modelo.comparendoMayorObId());
				break;

			case 7: 
				view.printMessage("--------- \nZona min-max: ");
				view.printModelo(modelo);
				view.printMessage(modelo.zonaMinmax());
				break;

			case 8: 
				view.printMessage("--------- \n A1)Primer Comparendo Por Localidad dada:\n---------"); 
				view.printModelo(modelo);
				dato = lector.next();
				view.printMessage(modelo.consultarPrimerComparendoPorLocalidad(dato));
				break;	

			case 9: 
				view.printMessage("--------- \n consultarComparendos Fecha dada: \n---------"); 
				view.printModelo(modelo);
				dato = lector.next();
				view.printMessage(modelo.consultarComparendosFecha(dato));
				break;		

			case 10: 
				view.printMessage("--------- \n A3) comparar Codigo Comparendos con Fechas dadas:\n---------"); 
				view.printModelo(modelo);
				view.printMessage("fecha1");

				dato = lector.next();
				view.printMessage("fecha2");

				dato2= lector.next();

				view.printMessage("");

				break;				

			case 11: 
				view.printMessage("--------- \n B1)consulta PrimerComparendo Por codigo de Infraccion dado\n---------"); 
				view.printModelo(modelo);
				dato = lector.next();

				view.printMessage(modelo.consultarPrimerComparendoPorInfraccion(dato));

				break;

			case 12: 
				view.printMessage("--------- \n B2)consultar Comparendos  con  CodigoInfraccion dado: \n---------"); 
				view.printModelo(modelo);
				dato = lector.next();
				view.printMessage(modelo.consultarPrimerComparendoPorInfraccion(dato));
				break;

			case 13: 
				view.printMessage("--------- \n B3)comparar Comparendo SegunTipo Servicio: \n---------"); 
				view.printMessage(modelo.compararComparendoCodigoSegunTipoServi());

				break;	

			case 14: 
				view.printMessage("--------- \n C1) número de comparendos por cada código INFRACCION en una LOCALIDAD dada, para un periodo de tiempo dado  \n---------"); 
				datoi= lector.nextInt();
				String fechag = lector.next();
				Date g= new Date(fechag);
				String fechap = lector.next();
				Date p= new Date(fechap);
				dato=lector.next();

				view.printMessage(modelo.NumerodeComparendosCodigoSegunLocalidad(g, p, dato));

				break;	

			case 15: 
				view.printMessage("--------- \n C2)Consultar   la   informacion   de   los   N códigos   INFRACCION   con   más   infracciones ordenados  de  mayor  a  menor  en  un  periodo  de  tiempo  dado \n---------"); 

				view.printMessage("numero del top:");

				datoi= lector.nextInt();
				fechag = lector.next();
				g= new Date(fechag);
				fechap = lector.next();
				p= new Date(fechap);
				dato=lector.next();
				view.printMessage(modelo.consultarNComparendosMayoresSegunPeriodoFecha(datoi, g, p));


				break;

			case 16: 
				view.printMessage("--------- \n C3)Generar una gráfica ASCII (Histograma) que muestre el numero total de comparendos por  cada  LOCALIDAD \n---------"); 
				view.printMessage(modelo.GeneradorHistrogramaASCIINUmTotalSegunLocalidad());
				break;	

			case 17: 
				view.printMessage("--------- \n IMpresion del arreglo: ");
				view.printModelo(modelo);
				view.printMessage(modelo.imprimirArregloEntero());
				view.printMessage("Numero actual de elementos " + modelo.darTamano() + "\n---------");						
				break;	


			case 18:
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	




			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
