package Agenda;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DiaBreakTest {
	private DiaBreak diaBreak1;
	private DiaBreak diaBreak2;
	private DiaBreak diaBreak3;
	private DiaBreak diaBreak4;

	@Before
	public void setUp() throws Exception {
		try {
			diaBreak1 = new DiaBreak(1);
			diaBreak2 = new DiaBreak(2);
			diaBreak3 = new DiaBreak(3);
			diaBreak4 = new DiaBreak(4);
		} catch (DatoException e) {
			fail("Inicializacion fallada.");
		}
	}
	
	/*
	 * El test DiaBreak() se ha hecho para comprobar que se cumplen los requisitos
	 * de la clase DiaBreak. 
	 * - TestOk1 comprueba que no se pueda introducir 0 como día válido para la cita. 
	 * - TestOk2 comprueba que no se pueda introducir 366 como día válido para la cita.
	 * - TestOk3 comprueba que tanto los dias 1 como 365 se puedan introducir como días válidos para la cita. 
	 * 
	 * El constructor de la clase contemplaba el día 0 como válido y el día 365 como no válido
	 * lo que no cumplía los requisitos de implementación. El código ha sido modificado de la siguiente forma
	 * para resolver dichos errores. 
	 * CODIGO ANTIGUO: 
	 * if (diaNumero < 0 || diaNumero >= 365)
	 * CODIGO NUEVO: 
	 * if (diaNumero <= 0 || diaNumero > 365)
	 */
	@Test
	public void testDiaBreak()
	{
		boolean testOk1 = false;
		try {
			new DiaBreak(0);
		} catch (DatoException e) {
			testOk1 = true;
		}
		
		boolean testOk2 = false;
		try {
			new DiaBreak(366);
		} catch (DatoException e) {
			testOk2 = true;
		}
		
		boolean testOk3 = true;
		try {
			new DiaBreak(1);
			new DiaBreak(365);
		} catch (DatoException e) {
			testOk3 = false;
		}
		assertTrue("Dia 0 no debe ser valido", testOk1);
		assertTrue("Dia 366 no debe ser valido", testOk2);
		assertTrue("Dias 1 y 365 deben ser validos", testOk3);
	}
	/*
	 *  El código anterior no pasaba ninguno de los test ya que no modificaba el array de citas en ningún momento. Por esto se ha añadido 
	 *  este bucle for que modifica si hay espacio suficiente para la cita los slots. 
	 *  for(int i = 0; i < duracion; i++) {
			citas[i] = new Cita("", duracion);
		}
		Se ha modificado la siguiente comparación numSlots < MAX_CITAS_POR_DIA añadiendole un = ( numSlots <= MAX_CITAS_POR_DIA) 
		para que se pudiesen reservar sesiones de ocho horas que es el máximo permitido. 
		Además se ha añadido esta comparación if(duracion > 0) para que no fuese posible reservar sesiones de 0 horas. 
	 */
	@Test
	public void testBuscaSlot()
	{
		int cita1 = diaBreak1.buscaSlot(1);
		int cita2 = diaBreak2.buscaSlot(8);
		int cita3 = diaBreak2.buscaSlot(2);
		int cita4 = diaBreak3.buscaSlot(12);
		int cita5 = diaBreak4.buscaSlot(0);
		
		assertTrue("Cita de 1 hora debe ser reservada", cita1 == 0);
		assertTrue("Cita de 8 horas debe ser reservada", cita2 == 0);
		assertTrue("Cita de 2 horas con día ocupado debe ser rechazada", cita3 == -1);
		assertTrue("Cita de 12 horas debe ser rechazada", cita4 == -1);
		assertTrue("Cita de 0 horas debe ser rechazada", cita5 == -1);
	}
	/*
	 * El test comprueba que al coger una cita devuelva null en caso de no existir o en caso de que la hora no sea correcta. 
	 * No ha sido necesario cambiar el código original. 
	 */
	@Test
	public void testGetCita()
	{
		Cita cita1aux = new Cita("", 1);
		Cita cita2aux = new Cita("", 2);
		
		
		diaBreak1.asignarCita(9, cita1aux);
		diaBreak2.asignarCita(9, cita2aux);
		
		
		Cita cita1 = diaBreak1.getCita(9);
		Cita cita2 = diaBreak2.getCita(9);
		Cita cita3 = diaBreak2.getCita(8);
		Cita cita4 = diaBreak3.getCita(19);
		
		assertTrue("La cita de las 9 debe ser encontrada.", cita1.getDuracion() == 1);
		assertTrue("La cita de las 9 debe ser encontrada.", cita2.getDuracion() == 2);
		assertTrue("La cita de las 8 no debe ser encontrada.", cita3 == null);
		assertTrue("La cita de las 19 no debe ser encontrada.", cita4 == null);
	}
	/*
	 * El test nos mostró que el mensaje devuelto cuando la hora no era valida era incorrecto. Este error ha sido modificado en el 
	 * código original añadiendo "Hora no valida" en lugar de "Hora valida";
	 */
	@Test
	public void testMuestraCita()
	{
		Cita cita = new Cita("", 1);
		diaBreak1.asignarCita(9, cita);
		
		String citaEncontrada = "9:00 " + cita.getDescripcion();
		String citaNoEncontrada = "No existe";
		String horaNoValida = "Hora no valida";
		
		assertTrue("Cita de las 9 debe ser encontrada", diaBreak1.muestraCita(9).equals(citaEncontrada));
		assertTrue("Cita de las 10 no debe ser encontrada", diaBreak1.muestraCita(10).equals(citaNoEncontrada));
		assertTrue("Cita de las 25 no es valida", diaBreak1.muestraCita(25).equals(horaNoValida));
	}
	/*
	 * No ha sido necesario corregir el código original puesto que pasa el test. 
	 */
	@Test
	public void testValidaHora()
	{
		boolean horaValida1 = diaBreak1.validaHora(9);
		boolean horaValida2 = diaBreak1.validaHora(17);
		boolean horaNoValida1 = diaBreak1.validaHora(8);
		boolean horaNoValida2 = diaBreak1.validaHora(18);
		
		assertTrue("Las 9 debe ser una hora valida", horaValida1);
		assertTrue("Las 17 debe ser una hora valida", horaValida2);
		assertFalse("Las 8 no debe ser una hora valida", horaNoValida1);
		assertFalse("Las 18 no debe ser una hora valida", horaNoValida2);
	}
	/*
	 * El test nos mostró que el código no contemplaba la posibilidad de que se buscase un hueco libre
	 * a una hora fuera del rango. Esto ha sido arreglado en el código añadiendo la siguiente condición
	 * 
	 * if(horaIni >= 0 && horaIni <= 7)
	 */
	@Test
	public void testHuecoLibre()
	{
		Cita cita1 = new Cita("", 2);
		diaBreak1.asignarCita(9, cita1);
		
		
		boolean huecoLibre1 = diaBreak1.huecoLibre(13, 1);
		boolean huecoOcupado1 = diaBreak1.huecoLibre(9, 2);
		boolean huecoOcupado2 = diaBreak1.huecoLibre(9, 5);
		boolean huecoNoValido1 = diaBreak1.huecoLibre(18, 3);
		boolean huecoNoValido2 = diaBreak1.huecoLibre(9, 12);
		
		assertTrue("El hueco de 13:00 a 14:00 debe estar libre", huecoLibre1);
		assertFalse("El hueco de 9:00 a 11:00 debe estar ocupado", huecoOcupado1);
		assertFalse("El hueco de 9:00 a 14:00 debe estar ocupado", huecoOcupado2);
		assertFalse("El hueco de 18:00 a 21:00 no debe ser valido", huecoNoValido1);
		assertFalse("El hueco de 9:00 a 21:00 no debe ser valido", huecoNoValido2);
	}
	 /*
	  *  No ha sido necesario corregir el código original puesto que pasa el test. 
	  */
	@Test
	public void testAsignarCita() {
		Cita cita1 = new Cita("",2);
		Cita cita2 = new Cita("",2);
		Cita cita3 = new Cita("", 8);
		
		boolean citaAsignada1 = diaBreak3.asignarCita(9, cita1);
		boolean citaAsignada2 = diaBreak3.asignarCita(11, cita2);
		boolean citaNoAsignada1 = diaBreak3.asignarCita(18, cita1);
		boolean citaNoAsignada2 = diaBreak3.asignarCita(8, cita2);
		boolean citaAsignada3 = diaBreak4.asignarCita(9, cita3);
		boolean citaNoAsignada3 = diaBreak4.asignarCita(9, cita1);
		
		assertTrue("La cita debe ser asignada.", citaAsignada1);
		assertTrue("La cita debe ser asignada.", citaAsignada2);
		assertTrue("La cita debe ser asignada.", citaAsignada3);
		assertFalse("La cita no debe ser asignada.", citaNoAsignada1);
		assertFalse("La cita no debe ser asignada.", citaNoAsignada2);
		assertFalse("La cita no debe ser asignada.", citaNoAsignada3);
		
		
		
	}

}

