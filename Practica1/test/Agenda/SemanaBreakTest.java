package Agenda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SemanaBreakTest {

	private SemanaBreak semanaBreak1;
	private SemanaBreak semanaBreak2;
	private SemanaBreak semanaBreak3;
	private SemanaBreak semanaBreak4;

	@Before
	public void setUp() throws Exception {
		try {
			semanaBreak1 = new SemanaBreak(1);
			semanaBreak2 = new SemanaBreak(2);
			semanaBreak3 = new SemanaBreak(3);
			semanaBreak4 = new SemanaBreak(4);
		} catch (DatoException e) {
			fail("Inicializacion fallada.");
		}
	}
	/*
	 * El test al constructor de la case nos ha mostrado que el c�digo no ten�a en cuenta que la �ltima semana del a�o ser�a de un solo d�a. 
	 * Hemos a�adido la siguiente condici�n para que al introducir la semana 52 no falle.
	 *  if(diaDelAnio== 365) {
	        	dias[0] = new DiaBreak(diaDelAnio);
	        }
	 *  Adem�s el test nos ha mostrado que la semana 0 no estaba contemplada como v�lida, se ha modificado el if quit�ndole el igual a la primera condici�n
	 *  CODIGO ANTIGUO:
	 *  if (numeroSemana <= 0 || numeroSemana > 52)
	 *  CODIGO NUEVO 
	 *  if (numeroSemana < 0 || numeroSemana > 52)
	 */
	@Test
	public void testSemanaBreak()
	{
		boolean testOk1 = true;
		try {
			new SemanaBreak(53);
		} catch (DatoException e) {
			testOk1 = false;
		}
	
		boolean testOk2 = true;
		try {
			new SemanaBreak(0);
			new SemanaBreak(52);
		} catch (DatoException e) {
			testOk2 = false;
		}
		assertFalse("Semana 53 no debe ser valida", testOk1);
		assertTrue("Semanas 0 y 52 deben ser validas", testOk2);
	}
	/*
	 * El test nos mostr� que el quinto d�a de la semana no devolv�a viernes, se ha modificado el c�digo a�adiendo un break; en el case 4 perteneciente
	 * al viernes.
	 */
	@Test
	public void testDiaSemana() {
		String lunes = semanaBreak1.diaSemana(0);
		String martes = semanaBreak1.diaSemana(1);
		String miercoles = semanaBreak1.diaSemana(2);
		String jueves = semanaBreak1.diaSemana(3);
		String viernes = semanaBreak1.diaSemana(4);
		String noValido = semanaBreak1.diaSemana(5);
		
		assertTrue("El dia 1 de la semana debe ser Lunes", lunes.equals("Lunes"));
		assertTrue("El dia 2 de la semana debe ser Martes", martes.equals("Martes"));
		assertTrue("El dia 3 de la semana debe ser Miercoles", miercoles.equals("Miercoles"));
		assertTrue("El dia 4 de la semana debe ser Jueves", jueves.equals("Jueves"));
		assertTrue("El dia 5 de la semana debe ser Viernes", viernes.equals("Viernes"));
		assertTrue("El dia introducido no debe ser valido", noValido.equals("No citable"));
		
		
		
	}
	/*
	 * El test no mostr� ning�n error, no ha sido necesario corregir el c�digo. 
	 */
	@Test
	public void testGetNumeroSemana() {
		int numSemana1 = semanaBreak1.getNumeroSemana();
		int numSemana2 = semanaBreak2.getNumeroSemana();
		

		assertTrue("El n�mero de la semana debe ser 1", numSemana1 == 1);
		assertTrue("El n�mero de la semana debe ser 2", numSemana2 == 2);
		
	}
	/*
	 * El test nos mostr� que los d�as no empezaban seg�n el c�digo implementado en 1 siendo este el Lunes, 2 siendo el Martes etc, si no que 
	 * se el Lunes se consideraba el d�a 0 , siendo as� el Sabado el d�a 5 y el Domingo el d�a 6. Se ha modificado el c�digo cambiando la condici�n del if
	 * y restandole uno al dia de la semana que entra como par�metro. 
	 * 
	 * CODIGO ANTIGUO: 
	 *  if(diaSemana >= 1 && diaSemana <= DIAS_RESERVABLES)
	 * CODIGO NUEVO: 
	 *  diaSemana--;
	        if(diaSemana >= 0 && diaSemana < DIAS_RESERVABLES)
	 */
	@Test
	public void testGetDia(){
		
		
		DiaBreak dia1 = semanaBreak1.getDia(1);
		DiaBreak dia2 = semanaBreak1.getDia(5);
		DiaBreak dia3 = semanaBreak1.getDia(0);
		DiaBreak dia4 = semanaBreak1.getDia(6);
		DiaBreak dia5 = semanaBreak1.getDia(7);
		DiaBreak dia6 = semanaBreak1.getDia(8);
		
		
		assertTrue("Debe devolver el d�a de la semana", dia1 != null);
		assertTrue("Debe devolver el d�a de la semana", dia2 != null);
		assertTrue("No debe devolver el d�a de la semana", dia3 == null);
		assertTrue("No debe devolver el d�a de la semana", dia4 == null);
		assertTrue("No debe devolver el d�a de la semana", dia5 == null);
		assertTrue("No debe devolver el d�a de la semana", dia6 == null);

		
		
		
	}
}
