package typecheck;

import static org.junit.Assert.*;

import org.junit.Test;

public class BridgerTest {
	
	Bridger open = Bridger.OPEN;
	Bridger close = Bridger.CLOSE;

	/* getType test */
	@Test
	public void testGetType() {
		assertEquals(open.getType(), Bridger.OPEN);
		assertEquals(close.getType(), Bridger.CLOSE);
	}

	/* toString test */
	@Test
	public void testToString() {
		assertEquals(Bridger.CLOSE.toString(), ")");
		assertEquals(Bridger.OPEN.toString(), "(");
	}

}
