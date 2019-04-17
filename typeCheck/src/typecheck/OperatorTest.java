package typecheck;

import static org.junit.Assert.*;

import org.junit.Test;

public class OperatorTest {
	
	Operator plus = Operator.PLUS;
	Operator minus = Operator.MINUS;
	Operator times = Operator.TIMES;
	Operator divide = Operator.DIVIDE;
	Operator exp = Operator.EXPONENTIAL;

	/* getType tests */
	@Test
	public void testGetType() {
		assertEquals(plus.getType(), Operator.PLUS);
		assertEquals(minus.getType(), Operator.MINUS);
		assertEquals(times.getType(), Operator.TIMES);
		assertEquals(divide.getType(), Operator.DIVIDE);
		assertEquals(exp.getType(), Operator.EXPONENTIAL);
	}

	/* toString tests */
	@Test
	public void testToString() {
		assertEquals(Operator.PLUS.toString(), "+");
		assertEquals(Operator.MINUS.toString(), "-");
		assertEquals(Operator.DIVIDE.toString(), "/");
		assertEquals(Operator.TIMES.toString(), "*");
		assertEquals(Operator.EXPONENTIAL.toString(), "^");
	}

}
