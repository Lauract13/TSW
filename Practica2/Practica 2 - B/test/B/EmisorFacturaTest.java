package B;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EmisorFacturaTest {
	
	private static final String TEST_EMAIL = "email@mail.com";
	@Mock PrinterService printerService;
	@Mock EmailService emailService;
	@Mock Factura factura;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void emitirFacturaEmailTest() {
		
		Cliente cliente = new Cliente(TEST_EMAIL, true);
		EmisorFactura emisorFactura = new EmisorFactura(printerService, emailService);
		
		emisorFactura.emitirFactura(factura, cliente);
		
		verify(emailService).sendFactura(factura, TEST_EMAIL);
	}
	
	@Test
	public void emitirFacturaImpresoTest() {
		
		Cliente cliente = new Cliente(TEST_EMAIL, false);
		EmisorFactura emisorFactura = new EmisorFactura(printerService, emailService);
		
		emisorFactura.emitirFactura(factura, cliente);
		
		verify(printerService).printFactura(factura);
	}

}
