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
		
	System.out.println("Bienvenido al arreglo dinámico de Comparendos, porfavor seleccione una opción para continuar");
	System.out.println("1. Crear Arreglo Dinamico de Comparendos, junto con datos adicionales");
	System.out.println("2. Agregar Comparendo");
	System.out.println("3. Buscar Comparendo");
	System.out.println("4. Eliminar Comparendo");
	System.out.println("5. Total de comparendos en el archivo");
	System.out.println("6. comparendo con mayor ID");
	System.out.println("7. Zona min-max");
	System.out.println("8. A1)Primer Comparendo Por Localidad dada");
	System.out.println("9. A2)consultar Comparendos con Fecha");
	System.out.println("10. A3) comparar Codigo Comparendos con Fechas dadas");
	System.out.println("11. B1)consulta PrimerComparendo Por codigo de Infraccion dado");
	System.out.println("12. B2)consultar Comparendos  con  CodigoInfraccion dado");
	System.out.println("13. B3)comparar Comparendo SegunTipo Servicio");
	System.out.println("14. C1) número de comparendos por cada código INFRACCION en una LOCALIDAD dada, para un periodo de tiempo dado ");
	System.out.println("15. C2)Consultar   la   informacion   de   los   N códigos   INFRACCION   con   más   infracciones ordenados  de  mayor  a  menor  en  un  periodo  de  tiempo  dado  ");
	System.out.println("16. C3)Generar una gráfica ASCII (Histograma) que muestre el numero total de comparendos por  cada  LOCALIDAD");
	System.out.println("17. Imprimir el Arreglo");
	System.out.println("18. Exit");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}		

	public void printModelo(Modelo modelo)
	{
		// TODO implementar
	}
}
