package C;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class WebVerificatorTest {
	
	@Mock Logger logger;
	@Mock Server server;
	@Mock Web web;
	@Mock Result result;
	private WebVerificator webVerificator;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		webVerificator = new WebVerificator(logger);
	}

	@Test
	public void checkWebOkTest() {
		
		when(result.isOk()).thenReturn(true);
		when(server.connect(web)).thenReturn(result);
		
		webVerificator.checkWeb(server, web);
		
		verify(logger).registerWebisOk(web);
	}
	
	@Test
	public void checkWebNotOkTest() {
		
		when(result.isOk()).thenReturn(false);
		when(server.connect(web)).thenReturn(result);
		
		webVerificator.checkWeb(server, web);
		
		verify(logger).registerWebReturnedError(web, result);
	}

}
