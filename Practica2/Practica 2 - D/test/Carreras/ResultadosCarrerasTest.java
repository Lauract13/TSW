package Carreras;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class ResultadosCarrerasTest {

	@Test
	public void clienteNoSuscritoTest() {
		
		Cliente cliente = mock(Cliente.class);
		Mensaje mensaje = mock(Mensaje.class);
		ResultadosCarreras results = new ResultadosCarreras();
		
		results.enviar(mensaje);
		
		verify(cliente, never()).recibe(mensaje);
	}
	
	@Test
	public void clienteUnicoSuscritoTest() {
		
		Cliente cliente = mock(Cliente.class);
		Mensaje mensaje = mock(Mensaje.class);
		ResultadosCarreras results = new ResultadosCarreras();
		
		results.nuevaSuscripcion(cliente);
		results.enviar(mensaje);
		
		verify(cliente, times(1)).recibe(mensaje);
	}
	
	@Test
	public void clientesSuscritosTest() {
		
		Cliente cliente1 = mock(Cliente.class);
		Cliente cliente2 = mock(Cliente.class);
		Cliente cliente3 = mock(Cliente.class);
		
		Mensaje mensaje = mock(Mensaje.class);
		ResultadosCarreras results = new ResultadosCarreras();
		
		results.nuevaSuscripcion(cliente1);
		results.nuevaSuscripcion(cliente2);
		results.nuevaSuscripcion(cliente3);
		results.enviar(mensaje);
		
		verify(cliente1, times(1)).recibe(mensaje);
		verify(cliente2, times(1)).recibe(mensaje);
		verify(cliente3, times(1)).recibe(mensaje);
	}
	
	@Test
	public void dobleSuscripcionIgnoradaTest() {
		
		Cliente cliente = mock(Cliente.class);
		Mensaje mensaje = mock(Mensaje.class);
		ResultadosCarreras results = new ResultadosCarreras();
		
		results.nuevaSuscripcion(cliente);
		results.nuevaSuscripcion(cliente); // Doble suscripcion
		results.enviar(mensaje);
		
		verify(cliente, times(1)).recibe(mensaje);
	}
	
	@Test
	public void bajaSuscripcionClienteTest() {
		
		Cliente cliente = mock(Cliente.class);
		Mensaje mensaje = mock(Mensaje.class);
		ResultadosCarreras results = new ResultadosCarreras();
		
		results.nuevaSuscripcion(cliente);
		results.bajaSuscripcion(cliente);
		results.enviar(mensaje);
		
		verify(cliente, never()).recibe(mensaje);
	}

}
