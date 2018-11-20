package Agenda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SemanaBreakTest {

	private SemanaBreak semanaBreak1;
	private SemanaBreak semanaBreak2;
	private SemanaBreak semanaBreak3;
	private SemanaBreak semanaBreak4;
	private DiaBreak diaCita;

	@Before
	public void setUp() throws Exception {
		try {
			semanaBreak1 = new SemanaBreak(1);
			semanaBreak2 = new SemanaBreak(2);
			semanaBreak3 = new SemanaBreak(3);
			semanaBreak4 = new SemanaBreak(4);
			diaCita = new DiaBreak(1);
		} catch (DatoException e) {
			fail("Inicializacion fallada.");
		}
	}
	/*
	 * El test al constructor de la case nos ha mostrado que el código no tenía en cuenta que la última semana del año sería de un solo día. 
	 * Hemos añadido la siguiente condición para que al introducir la semana 52 no falle.
	 *  if(diaDelAnio== 365) {
	        	dias[0] = new DiaBreak(diaDelAnio);
	        }
	 *  
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
			new SemanaBreak(52);
		} catch (DatoException e) {
			testOk2 = false;
		}
		
		boolean testOk3 = true;
		try {
			new SemanaBreak(0);
		}catch(DatoException e) {
			testOk3 = false;
		}
		assertFalse("Semana 53 no debe ser valida", testOk1);
		assertTrue("Semana 52 debe ser valida", testOk2);
		assertFalse("Semana 0 no debe ser valida", testOk3);
	}
	/*
	 * El test nos mostró que el quinto día de la semana no devolvía viernes, se ha modificado el código añadiendo un break; en el case 4 perteneciente
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
	 * El test no mostró ningún error, no ha sido necesario corregir el código. 
	 */
	@Test
	public void testGetNumeroSemana() {
		int numSemana1 = semanaBreak1.getNumeroSemana();
		int numSemana2 = semanaBreak2.getNumeroSemana();
		

		assertTrue("El número de la semana debe ser 1", numSemana1 == 1);
		assertTrue("El número de la semana debe ser 2", numSemana2 == 2);
		
	}
	/*
	 * El test nos mostró que los días no empezaban según el código implementado en 1 siendo este el Lunes, 2 siendo el Martes etc, si no que 
	 * se el Lunes se consideraba el día 0 , siendo así el Sabado el día 5 y el Domingo el día 6. Se ha modificado el código cambiando la condición del if
	 * y restandole uno al dia de la semana que entra como parámetro. 
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
		
		
		assertTrue("Debe devolver el día de la semana", dia1 != null);
		assertTrue("Debe devolver el día de la semana", dia2 != null);
		assertTrue("No debe devolver el día de la semana", dia3 == null);
		assertTrue("No debe devolver el día de la semana", dia4 == null);
		assertTrue("No debe devolver el día de la semana", dia5 == null);
		assertTrue("No debe devolver el día de la semana", dia6 == null);

		
		
		
	}
	/*
	 * El test nos mostró que el código comenzaba las horas a partir de la hora cero y no a partir de la hora nueve como debería, para arreglarlo 
	 * se ha añadido el siguiente código dentro del condicional 
	 * CODIGO VIEJO
	 * if (hueco!=-1)
	             { 
	    			
	    			disponible= diaSemana(dia) + " " + hueco + ":00";
	    			return disponible;
	             }
	 * CODIGO NUEVO
	 * if (hueco!=-1)
	             { 
	    			hueco = hueco + 9;
	    			disponible= diaSemana(dia) + " " + hueco + ":00";
	    			return disponible;
	             }
	             
	             
	    Además el test nos mostró que el primer día que el código contemplaba era el Martes puesto que el bucle comenzaba en el día 1, el código ha sido modificado
	    para que el bucle comience en el dia 0 -> Lunes
	    for(int dia = 0; dia < DIAS_RESERVABLES; dia++)         
	 */
	@Test 
	public void testPrimerHueco() {
		String huecoLibre = semanaBreak1.primerHueco(1);
		String noDisponibilidad1 = semanaBreak2.primerHueco(9);
		String noDisponibilidad2 = semanaBreak2.primerHueco(0);
		
		assertTrue("Debe mostrar el primer hueco libre de duración 1", huecoLibre.equals("Lunes 9:00"));
		assertTrue("No debe mostrar un hueco para una duración de 9", noDisponibilidad1.equals("No hay disponibilidad"));
		assertTrue("No debe mostrar un hueco para una duración de 0", noDisponibilidad2.equals("No hay disponibilidad"));
		
		
		
	}
	/*
	 * No se ha encontrado ningún error en el código de mostrarCita
	 */
	@Test
	public void testMostrarCita() {
		
			Cita cita = new Cita("", 0);
			diaCita.asignarCita(9, cita);
			
			String cita1 = semanaBreak1.mostrarCita(9, 1); 
			String cita2 = semanaBreak2.mostrarCita(10, 1); 
			String cita3 = semanaBreak1.mostrarCita(18, 1);
			
			String citaEncontrada = "9:00 " + cita.getDescripcion();
			String citaNoEncontrada = "No existe";
			String horaNoValida = "Hora no valida";
			
			
			assertTrue("Cita de las 9 debe ser encontrada", cita1.equals(citaEncontrada));
			assertTrue("Cita de las 10 no debe ser encontrada", cita2.equals(citaNoEncontrada));
			assertTrue("Cita de las 18 no es valida", cita3.equals(horaNoValida));
		
			
			
			
			
		
		
		
	}
}
