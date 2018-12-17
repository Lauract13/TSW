package A;

import static org.mockito.Mockito.*;

import org.junit.Test;

public class MensajeTest {
	
	private static final String TEST_EMAIL = "Test email";
	private static final String TEST_MSG = "Test message";

	@Test
	public void sendMensajeTest() {
		
		MailServer mailServer = mock(MailServer.class);
		TemplateEngine templateEngine = mock(TemplateEngine.class);
		Cliente cliente = mock(Cliente.class);
		Template template = mock(Template.class);
		Mensaje mensaje = new Mensaje(mailServer, templateEngine);
		
		when(cliente.getEmail()).thenReturn(TEST_EMAIL);
		when(templateEngine.preparaMensaje(any(Template.class), any(Cliente.class)))
		     .thenReturn(TEST_MSG);
		
		mensaje.sendMensaje(cliente, template);
		
		verify(mailServer).send(TEST_EMAIL, TEST_MSG);
	}

}
