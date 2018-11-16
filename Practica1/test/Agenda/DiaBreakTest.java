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
	
	@Test
	public void testGetCita()
	{
		int indexCita1 = diaBreak1.buscaSlot(1);
		int indexCita2 = diaBreak2.buscaSlot(2);
		int indexCita3 = diaBreak2.buscaSlot(2);
		int indexCita4 = diaBreak3.buscaSlot(12);
		
		Cita cita1 = diaBreak1.getCita(indexCita1);
		Cita cita2 = diaBreak2.getCita(indexCita2);
		Cita cita3 = diaBreak2.getCita(indexCita3);
		Cita cita4 = diaBreak3.getCita(indexCita4);
		
		assertTrue("La cita debe ser de 1 hora", cita1.getDuracion() == 1);
		assertTrue("La cita debe ser de 2 horas", cita2.getDuracion() == 2);
		assertTrue("La cita debe ser de 2 horas", cita3.getDuracion() == 2);
		assertTrue("La cita no debe existir", cita4 == null);
	}
	
	@Test
	public void testMuestraCita()
	{
		diaBreak1.buscaSlot(1);
		
		String citaEncontrada = "9:00 ";
		String citaNoEncontrada = "No existe";
		String horaNoValida = "Hora no valida";
		
		assertTrue("Cita de las 9 debe ser encontrada", diaBreak1.muestraCita(9).equals(citaEncontrada));
		assertTrue("Cita de las 10 no debe ser encontrada", diaBreak1.muestraCita(10).equals(citaNoEncontrada));
		assertTrue("Cita de las 25 no es valida", diaBreak1.muestraCita(25).equals(horaNoValida));
	}
	
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
	
	@Test
	public void testHuecoLibre()
	{
		diaBreak1.buscaSlot(3);
		
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

}
